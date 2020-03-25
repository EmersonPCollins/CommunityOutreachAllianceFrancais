package com.csci.afevents.ui.home;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private ProgressBar spinner;
    private RecyclerView recyclerView;
    private HomeViewModel homeViewModel;
    private TextView selectedDate;

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
        selectedDate = view.findViewById(R.id.date_filter_view);
        selectedDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        return view;
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                HomeFragment.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    private void initViews(View view, ListAdapter listAdapter) {
        spinner = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        selectedDate.setText(year+"/"+month+"/"+dayOfMonth);
    }
}