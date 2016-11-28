package com.desafiolatam.desafioface.network.fcm;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

/**
 * Created by QA on 25-11-2016.
 */

public interface PostToken {
    @FormUrlEncoded
    @PUT("users/fcm_token")
    Call<String> postToken(@Field("users[fcm_token]") String fcmToken);
}
