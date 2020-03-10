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

    private static int eventId = 0;
    private Context context;
    private MutableLiveData<List<Event>> events;
    private final String EVENT_ENDPOINT = "https://afhalifax.ca/EVLOFFICE/a.php";

    public ApiEventRetriever(Context context) {
        this.context = context;
        events = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Event>> getEvents() {
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        JsonObjectRequest req = new JsonObjectRequest(EVENT_ENDPOINT,
                null,
                new EventApiResponseListener(),
                new EventApiResponseListener());
        reqQueue.add(req);
        return events;
    }

    class EventApiResponseListener implements Response.Listener<JSONObject>, Response.ErrorListener {
        @Override
        public void onResponse(JSONObject response) {
            List<Event> list = new ArrayList<>();
            eventId++;
            try {
                JSONArray jsonArray = response.getJSONArray("event");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject singleevent = jsonArray.getJSONObject(i);
                    String id = (""+eventId);
                    String name = singleevent.getString("name");
                    String desc = singleevent.getString("description");
                    int date = singleevent.getInt("date");
                    String imgurl = singleevent.getString("image_url");
                    int longit = singleevent.getInt("longitude");
                    int latit = singleevent.getInt("latitude");
                    Event ev = new Event(id, name, desc, date, imgurl, longit, latit);
                    list.add(ev);
                }
                events.setValue(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    }

}
