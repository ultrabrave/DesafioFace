package com.desafiolatam.desafioface.network.gets;

import android.os.AsyncTask;

import com.desafiolatam.desafioface.data.UserData;
import com.desafiolatam.desafioface.models.User;
import com.desafiolatam.desafioface.network.Constans;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by QA on 23-11-2016.
 */

public class BackgroundUsers extends AsyncTask<Void,Void,Integer> {
    private Map<String,String> map;

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getUsers(){
        int code = Constans.NOTHING_HAPPENED;
        Users user = UsersInterceptor.getInstance().interceptor();
        Call<User[]> call =  user.getUsers(map);
        try {
            Response<User[]> response = call.execute();
            if (response.isSuccessful() && Constans.SUCCESSFUL_RESPONSE == response.code())
            {
                User[] userArray =  response.body();
                if (userArray != null && userArray.length > 0)
                {
                    code = Constans.SUCCESSFUL_RESPONSE;
                    new UserData().handle(userArray);
                }
                else
                {
                    code = Constans.EMPTY_RESPONSE;
                }
            }
            else
            {
                code = response.code();
            }
        } catch (IOException e) {
            e.printStackTrace();
            code = Constans.JAVA_EXCEPTION;
        }
        return code;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return null;
    }
}
