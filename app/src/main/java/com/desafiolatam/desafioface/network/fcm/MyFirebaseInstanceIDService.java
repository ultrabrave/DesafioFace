package com.desafiolatam.desafioface.network.fcm;

import android.widget.Toast;

import com.desafiolatam.desafioface.data.Token;
import com.desafiolatam.desafioface.data.UserInfoData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Created by QA on 24-11-2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
       String token = FirebaseInstanceId.getInstance().getToken();
        new Token(this).set(token);
        if (new UserInfoData().isLoged())
        {
            new BackgroundToken().execute(token);
            //TODO sen the token
        }
    }
}
