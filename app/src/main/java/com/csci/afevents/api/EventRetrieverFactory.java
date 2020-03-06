package com.csci.afevents.api;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.csci.afevents.impl.ApiEventRetriever;
import com.csci.afevents.impl.DummyEventRetriever;

public class EventRetrieverFactory {

    public static EventRetriever getInstance(Context context) {
        return new ApiEventRetriever(context);
    }

    private static boolean isConnected(Context context) {
        boolean connected = false;
        //ensure we are connected to the internet
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

}