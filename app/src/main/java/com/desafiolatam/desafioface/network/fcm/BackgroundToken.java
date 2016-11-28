package com.desafiolatam.desafioface.network.fcm;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by QA on 25-11-2016.
 */

public class BackgroundToken extends AsyncTask<String,Void,Integer> {
    @Override
    protected Integer doInBackground(String... strings) {
       PostToken postToken = new FcmInterceptor().getInterceptor();
        Call<String> call = postToken.postToken(strings[0]);
        try {
            Response<String> response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
