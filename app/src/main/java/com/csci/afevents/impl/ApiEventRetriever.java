package com.csci.afevents.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.db.LocalDatabaseHandler;
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
    private final String EVENT_ENDPOINT = "https://afhalifax.ca/EVLOFFICE/a.php";
    public final static String LAST_DATA_RETRIEVAL_TIME = "last_data_retrieval_time";
    private LocalDatabaseHandler db;

    public ApiEventRetriever(Context context) {
        this.context = context;
        events = new MutableLiveData<>();
        db = new LocalDatabaseHandler(context);
    }

    @Override
    public LiveData<List<Event>> getEvents() {
        RequestQueue reqQueue = Volley.newRequestQueue(context);
        JsonArrayRequest req = new JsonArrayRequest(EVENT_ENDPOINT,
                new EventApiResponseListener(),
                new EventApiResponseListener());
        reqQueue.add(req);
        return events;
    }

    class EventApiResponseListener implements Response.Listener<JSONArray>, Response.ErrorListener {
        @Override
        public void onResponse(JSONArray response) {
            List<Event> list = new ArrayList<>();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonEvent = response.getJSONObject(i);
                    double lat = 0.0, lon = 0.0;
                    if (!jsonEvent.getString("loc_Lat").isEmpty() &&
                            !jsonEvent.getString("loc_Long").isEmpty()) {
                        lat = Double.valueOf(jsonEvent.getString("loc_Lat"));
                        lon = Double.valueOf(jsonEvent.getString("loc_Long"));
                    }
                    Event event = new Event(
                            jsonEvent.getString("id"),
                            jsonEvent.getString("name"),
                            jsonEvent.getString("descriptionfr"),
                            jsonEvent.getString("date"),
                            jsonEvent.getString("image"),
                            lon,
                            lat,
                            jsonEvent.getString("loc_Address"),
                            jsonEvent.getString("descriptionen"),
                            jsonEvent.getString("costfr"),
                            jsonEvent.getString("costen")
                    );
                    list.add(event);
                }
                events.setValue(list);
                if (!list.isEmpty()) {
                    db.dropAndSetEvents(list);
                    SharedPreferences pref = context
                            .getSharedPreferences("afsettings", Context.MODE_PRIVATE);
                    pref.edit().putLong(LAST_DATA_RETRIEVAL_TIME, System.currentTimeMillis()).apply();
                }
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
