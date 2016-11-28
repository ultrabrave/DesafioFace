package com.desafiolatam.desafioface.network.sesions;

import com.desafiolatam.desafioface.models.UserInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Michael on 18-11-2016.
 */

public interface Session {
    @FormUrlEncoded
    @POST("mobile_sessions")
    Call<UserInfo> getLogin(@Field("session[email]") String email, @Field("session[password]")String password);
}
