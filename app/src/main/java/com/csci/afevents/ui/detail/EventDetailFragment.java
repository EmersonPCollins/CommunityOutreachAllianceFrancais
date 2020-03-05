package com.csci.afevents.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.csci.afevents.R;

public class EventDetailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_eventdetail,container ,false);
        return view;
    }
}
