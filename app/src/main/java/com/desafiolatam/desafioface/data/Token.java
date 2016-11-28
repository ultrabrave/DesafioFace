package com.desafiolatam.desafioface.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by QA on 25-11-2016.
 */

public class Token {
    private Context context;

    public static final String FCM_NOTIFICATIONS = "FCM_NOTIFICATIONS";
    public static final String FCM_TOKEN = "FCM_TOKEN";

    public Token(Context context) {
        this.context = context;
    }

    public void set(String string) {
        SharedPreferences sPref = context.getSharedPreferences(FCM_NOTIFICATIONS, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putString(FCM_TOKEN, string);
        prefEditor.commit();
    }

    public String get() {
        SharedPreferences sPref = context.getSharedPreferences(FCM_NOTIFICATIONS, Context.MODE_PRIVATE);
        return sPref.getString(FCM_TOKEN, null);
    }
}
