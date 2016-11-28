package com.desafiolatam.desafioface.network.gets;

import com.desafiolatam.desafioface.models.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by QA on 23-11-2016.
 */

public interface Users {
    @GET("users")
    Call<User[]> getUsers(@QueryMap Map<String, String> map);
}
