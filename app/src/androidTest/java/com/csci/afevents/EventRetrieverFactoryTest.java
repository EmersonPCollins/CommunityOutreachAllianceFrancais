package com.csci.afevents;

import android.content.Context;

import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.api.EventRetrieverFactory;
import com.csci.afevents.impl.DummyEventRetriever;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class EventRetrieverFactoryTest {
    @Test
    public void testGetDummyData() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        EventRetriever retriever = EventRetrieverFactory.getInstance(appContext);
        assertTrue(retriever instanceof DummyEventRetriever);
    }
}
