package com.csci.afevents.impl;

import ;
import android.app.DownloadManager;
import android.widget.TextView;
import android.util.JsonReader;

import com.csci.afevents.R;
import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.entities.Event;

import java.util.List;

public class ApiEventRetriever implements EventRetriever {
    @Override
    public List<Event> getEvents() {

        final TextView textView = (TextView) findViewById(R.id.text);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://dummy.restapiexample.com/api/v1/employees";

        StringRequest stringRequest = new StringRequest(DownloadManager.Request.Method.POST, url, new Response.Listener<String>();
    }
}
