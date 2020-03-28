package com.csci.afevents.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;
import com.squareup.picasso.Picasso;

public class EventDetailFragment extends Fragment {

    private ImageView eventImageView;
    private TextView eventTitleView;
    private TextView eventDateView;
    private TextView eventAddressView;
    private TextView eventDescriptionFr;
    private TextView eventDescriptionEn;
    private TextView frenchCostView;
    private TextView englishCostView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        initViews(view);
        Event event = (Event) getArguments().getSerializable("event");
        Picasso.get().load(event.getImageUrl()).fit().centerCrop().into(eventImageView);
        eventTitleView.setText(event.getEventName());
        eventDateView.setText(event.getDate());
        eventAddressView.setText(event.getAddress());
        eventDescriptionFr.setText(event.getFrenchDescriptionFormatted());
        eventDescriptionEn.setText(event.getEnglishDescriptionFormatted());
        frenchCostView.setText(event.getCostFr());
        englishCostView.setText(event.getCostEn());
        return view;
    }

    private void initViews(View view) {
        eventImageView = view.findViewById(R.id.event_detail_image);
        eventTitleView = view.findViewById(R.id.event_title);
        eventDateView = view.findViewById(R.id.date_view);
        eventAddressView = view.findViewById(R.id.event_address_view);
        eventDescriptionFr = view.findViewById(R.id.event_description_fr);
        eventDescriptionEn = view.findViewById(R.id.event_description_en);
        frenchCostView = view.findViewById(R.id.cost_fr_view);
        englishCostView = view.findViewById(R.id.cost_en_view);
    }

}
