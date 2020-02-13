package com.csci.afevents.api;

import android.content.Context;

import com.csci.afevents.impl.DummyEventRetriever;

public class EventRetrieverFactory {

    public static EventRetriever getInstance(Context context) {
        //TODO: Return API or DB retriever when available
        return new DummyEventRetriever();
    }
}
