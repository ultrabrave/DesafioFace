package com.desafiolatam.desafioface.network.post;

import com.desafiolatam.desafioface.models.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by QA on 25-11-2016.
 */

public interface Favorites {@POST("users/{id}/favorite")
Call<User> postFavorite(@Path("id") long id);
}
