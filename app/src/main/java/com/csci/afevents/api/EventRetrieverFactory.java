package com.csci.afevents.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.csci.afevents.impl.ApiEventRetriever;
import com.csci.afevents.impl.DatabaseEventRetriever;

public class EventRetrieverFactory {

    private static final long FIVE_MINUTES_MILLIS = 300000;

    public static EventRetriever getInstance(Context context) {
        if (isConnected(context) && hasStaleData(context)) {
            return new ApiEventRetriever(context);
        }
        return new DatabaseEventRetriever(context);
    }

    private static boolean hasStaleData(Context context) {
        SharedPreferences pref =
                context.getSharedPreferences("afsettings", Context.MODE_PRIVATE);
        long currentTime = System.currentTimeMillis();
        return currentTime - pref.getLong(
                ApiEventRetriever.LAST_DATA_RETRIEVAL_TIME, 0) > FIVE_MINUTES_MILLIS;
    }

    private static boolean isConnected(Context context) {
        boolean connected = false;
        //ensure we are connected to the internet
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

}