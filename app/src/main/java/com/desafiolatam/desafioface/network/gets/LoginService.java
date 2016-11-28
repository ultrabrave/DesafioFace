package com.desafiolatam.desafioface.network.gets;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.provider.SyncStateContract;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.desafiolatam.desafioface.network.Constans;

import java.util.HashMap;
import java.util.Map;

public class LoginService extends IntentService {

    private static final String START_DOWNLOAD = "com.desafiolatam.desafioface.network.gets.action.START_DOWNLOAD";
    private int page =  1;

    public LoginService() {
        super("LoginService");
    }

    public static void startActionDownload(Context context) {
        Intent intent = new Intent(context, LoginService.class);
        intent.setAction(START_DOWNLOAD);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (START_DOWNLOAD.equals(action)) {
                handlerStartDownload();
            }
        }
    }

    private void handlerStartDownload() {
        new GetUsers(page).execute();
    }

    private void validation(int result)
    {
        if (Constans.SUCCESSFUL_RESPONSE == result)
        {
            if (page == 1)
            {
                page++;
                new GetUsers(page).execute();
            }
            else
            {
                broadcastSucces();
            }
        }
        else
        {
            Log.d("ERROR",  String.valueOf(result));
        }
    }


    private void broadcastSucces() {
        Intent intent = new Intent();
        intent.setAction(Constans.CONNECTION_SUCCESS);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void broadcastError()    {
        Intent intent = new Intent();
        intent.setAction(Constans.CONNECTION_ERROR);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private class GetUsers extends BackgroundUsers
    {
        private int page;

        public GetUsers(int page) {
            this.page = page;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            Map<String,String> map = new HashMap<>();
            map.put("page",String.valueOf(page));
            setMap(map);
            return getUsers();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            validation(integer);
        }
    }
}
