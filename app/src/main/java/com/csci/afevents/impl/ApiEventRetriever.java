package com.csci.afevents.impl;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.entities.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ApiEventRetriever implements EventRetriever {

    private Context context;
    private MutableLiveData<List<Event>> events;
    private final String EVENT_ENDPOINT = "http://dummy.restapiexample.com/api/v1/employees";

    public ApiEventRetriever(Context context) {
        this.context = context;
        events = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Event>> getEvents() {
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        JsonObjectRequest req = new JsonObjectRequest(EVENT_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Event> list = new ArrayList<>();
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject person = jsonArray.getJSONObject(i);
                        String name = person.getString("employee_name");
                        Event ev = new Event("One", name, "Test", 2, "https://www.holland.com/upload_mm/9/c/1/65174_fullimage_joods-bruidje.jpg", 23.24535, 35.3422);
                        list.add(ev);
                    }
                    events.setValue(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        reqQueue.add(req);
        return events;
    }

}
