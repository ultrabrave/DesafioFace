package com.desafiolatam.desafioface.views.login;

import android.widget.Toast;

import com.desafiolatam.desafioface.models.BaseSugar;
import com.desafiolatam.desafioface.models.UserInfo;
import com.desafiolatam.desafioface.network.Constans;
import com.desafiolatam.desafioface.network.sesions.Session;
import com.desafiolatam.desafioface.network.sesions.SessionInterceptor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by QA on 22-11-2016.
 */

public class LogUser
{

    private LoginCallback callback;
    private String email,password;


    public LogUser(LoginCallback callback, String email, String password) {
        this.callback = callback;
        this.email = email;
        this.password = password;
    }

    public void validation()
    {
        if (email.trim().length() > 0 && !email.contains(" ") && email.contains(".") && email.contains("@"))
        {
            if (password.trim().length() > 0)
            {
                toServer();
            }
            else
            {
                callback.invalidEmail("Password inválido");
            }
        }else
        {
            callback.invalidEmail("Mail inválido");
        }
    }

    private void toServer()
    {
        Session session = new SessionInterceptor().getInterceptor();
        Call<UserInfo> call = session.getLogin(email, password);
        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (Constans.STATUS_OK ==response.code() && response.isSuccessful() )
                {
                    UserInfo userInfo = response.body();
                    userInfo.create();
                    callback.success();
                }
                else
                {
                    callback.fail("mail o password incorrecto");
                }


            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                callback.fail("Vuelta a intentarlo");
            }
        });
    }
}
