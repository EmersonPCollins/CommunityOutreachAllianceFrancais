package com.csci.afevents;

import android.content.Context;
import android.database.DatabaseUtils;

import com.csci.afevents.db.LocalDatabaseHandler;
import com.csci.afevents.entities.Event;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import androidx.test.core.app.ApplicationProvider;

import static junit.framework.TestCase.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class LocalDatabaseHandlerTest {

    LocalDatabaseHandler db;

    @Before
    public void setUp() {
        Context appContext = ApplicationProvider.getApplicationContext();
        db = new LocalDatabaseHandler(appContext);
        int v = db.getReadableDatabase().getVersion();
        db.onUpgrade(db.getWritableDatabase(), v, v + 1);
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void testInsertEvents_validEventInserted_eventGetsCommitted() {
        long countRows = DatabaseUtils.queryNumEntries(db.getReadableDatabase(), LocalDatabaseHandler.TABLE_NAME);
        String id = String.valueOf(countRows + 1);
        Event event = new Event(id, "event: " + id, "test event", 1583251991, "");
        db.insertEvent(event);
        long newCount = DatabaseUtils.queryNumEntries(db.getReadableDatabase(), LocalDatabaseHandler.TABLE_NAME);
        assertEquals(newCount, countRows + 1);
    }

    @Test
    public void testGetAllEvents_eventsExist_returnsNonEmptyList() {
        Event event = new Event("1", "event: 1", "test event", 1583251991, "");
        db.insertEvent(event);
        List<Event> events = db.getAllEvents();
        assertEquals(1, events.size());
        assertEquals(event, events.get(0));
    }

    @Test
    public void testGetAllEvents_noEventsExist_returnsEmptyList() {
        List<Event> events = db.getAllEvents();
        assertEquals(0, events.size());
    }

    @Test
    public void testGetAllEvents_manyEventsExist_returnsAllPages() {
        for (int i = 1; i <= 2000; i++) {
            Event event = new Event(String.valueOf(i), "event: " + i, "test event", 1583251991 + i, "");
            db.insertEvent(event);
        }

        long countRows = DatabaseUtils.queryNumEntries(db.getReadableDatabase(), LocalDatabaseHandler.TABLE_NAME);
        assertEquals(2000, countRows);
        List<Event> events = db.getAllEvents();
        assertEquals(countRows, events.size());
    }
}
