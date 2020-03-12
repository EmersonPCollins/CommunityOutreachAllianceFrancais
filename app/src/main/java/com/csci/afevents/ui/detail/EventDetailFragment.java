package com.csci.afevents.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;

public class EventDetailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        Event event = (Event) getArguments().getSerializable("event");
        TextView textView = view.findViewById(R.id.tv_test);
        textView.setText(event.getEventName());
        return view;
    }
}
