package com.desafiolatam.desafioface.network.gets;

import com.desafiolatam.desafioface.data.UserInfoData;
import com.desafiolatam.desafioface.models.User;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.desafiolatam.desafioface.network.Constans.BASE_URL;

/**
 * Created by QA on 23-11-2016.
 */
public class UsersInterceptor {
    private static UsersInterceptor ourInstance = new UsersInterceptor();

    public static UsersInterceptor getInstance() {
        return ourInstance;
    }

    private UsersInterceptor() {
    }

    public Users interceptor()
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request request = originalRequest.newBuilder()
                    /*Common headers*/
                        .header("authtoken", new UserInfoData().authToken())
                        .header("Accept", "application/json")
                    /*Custom header*/
                        .header("Source", "mobile")
                        .build();

                okhttp3.Response response = chain.proceed(request);

            /*If the request fail then you get 3 retrys*/
                int retryCount = 0;
                while (!response.isSuccessful() && retryCount < 3) {
                    retryCount++;
                    response = chain.proceed(request);
                }

                return response;
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit interceptor = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        Users request = interceptor.create(Users.class);

        return request;

    }
}
