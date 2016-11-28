package com.desafiolatam.desafioface.views.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desafiolatam.desafioface.R;
import com.desafiolatam.desafioface.data.Token;
import com.desafiolatam.desafioface.data.UserInfoData;
import com.desafiolatam.desafioface.models.UserInfo;
import com.desafiolatam.desafioface.network.Constans;
import com.desafiolatam.desafioface.network.fcm.BackgroundToken;
import com.desafiolatam.desafioface.network.gets.LoginService;
import com.desafiolatam.desafioface.views.main.MainActivity;
import com.github.ybq.android.spinkit.SpinKitView;


public class LoginActivity extends AppCompatActivity implements LoginCallback {
        private TextInputLayout emailTil,passwordTil;
        private EditText emailEt,passwordEt;
        private Button button;
        private SpinKitView loading;
        private BroadcastReceiver broadcastReceiver;
        private IntentFilter intentFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        emailTil = (TextInputLayout) findViewById(R.id.emailHolder);
        passwordTil = (TextInputLayout) findViewById(R.id.passwordHolder);

        emailEt = (EditText) findViewById(R.id.emailInput);
        passwordEt = (EditText) findViewById(R.id.passwordInput);

        loading = (SpinKitView) findViewById(R.id.loginLoading);

        button = (Button) findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                hideViews();
                new LogUser(LoginActivity.this,email,password).validation();
            }
        });

        if (new UserInfoData().isLoged()){

            success();
        }

        broadcastReceiver =  new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (Constans.CONNECTION_SUCCESS.equals(action))
                {
                    goToMain();
                }
                else if (Constans.CONNECTION_ERROR.equals(action))
                {
                    UserInfo.deleteAll(UserInfo.class);
                    showViews();

                }
            }
        };

        intentFilter = new IntentFilter();
        intentFilter.addAction(Constans.CONNECTION_SUCCESS);
        intentFilter.addAction(Constans.CONNECTION_ERROR);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private void goToMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void success() {
        hideViews();
        String token = new Token(this).get();
        if (token != null)
        {
            new BackgroundToken().execute(token);
          //TODO send token
        }
        LoginService.startActionDownload(this);

    }

    @Override
    public void fail(String error) {
        Snackbar.make((View) emailTil.getParent(),error,Snackbar.LENGTH_LONG).show();
        fail("Error de conexión");
    }

    @Override
    public void invalidEmail(String error) {
        emailTil.setError(error);
        showViews();
    }

    @Override
    public void invalidPassword(String error) {
        passwordTil.setError(error);
        showViews();
    }

    private void hideViews()
    {
        if (emailTil.getError() != null)
        {
            emailTil.setError(null);
        }
        if (passwordTil.getError() != null)
        {
            passwordTil.setError(null);
        }
        emailTil.setVisibility(View.GONE);
        passwordTil.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    private void showViews()
    {
        emailTil.setVisibility(View.VISIBLE);
        passwordTil.setVisibility(View.VISIBLE);
        button.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }
}
