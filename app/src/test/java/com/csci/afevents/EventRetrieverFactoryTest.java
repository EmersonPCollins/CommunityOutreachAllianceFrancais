package com.csci.afevents;

import android.content.Context;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.api.EventRetrieverFactory;
import com.csci.afevents.impl.ApiEventRetriever;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class EventRetrieverFactoryTest {
    @Test
    public void testGetApiRetriever() {
        Context appContext = ApplicationProvider.getApplicationContext();
        EventRetriever retriever = EventRetrieverFactory.getInstance(appContext);
        assertTrue(retriever instanceof ApiEventRetriever);
    }
}
