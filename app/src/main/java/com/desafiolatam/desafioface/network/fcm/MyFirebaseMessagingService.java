package com.desafiolatam.desafioface.network.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by QA on 24-11-2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String email = remoteMessage.getData().get("email");
        new FavoriteNotification().notify(this,email);
    }
}
