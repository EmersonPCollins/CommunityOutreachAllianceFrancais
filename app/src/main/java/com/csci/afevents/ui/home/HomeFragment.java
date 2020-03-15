package com.csci.afevents.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;
import com.csci.afevents.impl.DummyEventRetriever;

import java.util.List;

public class HomeFragment extends Fragment {

    private ProgressBar spinner;
    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new HomeViewModel(getContext());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        final ListAdapter listAdapter = new ListAdapter();
        initViews(view, listAdapter);

        homeViewModel.getEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                listAdapter.data = events;
                listAdapter.notifyDataSetChanged();
                spinner.setVisibility(View.GONE);
            }
        });
        return view;
    }

    private void initViews(View view, ListAdapter listAdapter) {
        spinner = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }
}