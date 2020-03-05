package com.csci.afevents.impl;

import android.app.DownloadManager;
import android.widget.TextView;
import android.util.JsonReader;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.csci.afevents.R;
import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.entities.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ApiEventRetriever implements EventRetriever {

    @Override
    public List<Event> getEvents() {
        String url ="http://dummy.restapiexample.com/api/v1/employees";
        final List<Event> events = new ArrayList<Event>();

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        RequestQueue myqueue = new RequestQueue(null, network);


        JsonObjectRequest req = new JsonObjectRequest(url, null,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jrray = response.getJSONArray("data");
                    for(int i=0;i<jrray.length();i++){
                        JSONObject person = jrray.getJSONObject(i);
                        String name = person.getString("employee_name");
                        Event ev = new Event("One", name, "Test", 2);
                        System.out.println(ev.toString());
                        events.add(ev);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }}, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    error.printStackTrace();
                }
            });
        myqueue.start();
        myqueue.add(req);
        return events;
    }

    private File getCacheDir() {
        return null;
    }

}
