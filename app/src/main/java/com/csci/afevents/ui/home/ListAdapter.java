package com.csci.afevents.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.csci.afevents.R;
import com.csci.afevents.api.EventRetrieverFactory;
import com.csci.afevents.entities.Event;
import com.csci.afevents.ui.detail.EventDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adama Camara
 *
 * Recycler View adapter for displaying events in a list layout
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<Event> data = new ArrayList<>();
    FragmentActivity activity;

    public ListAdapter(FragmentActivity fragmentActivity){
        this.activity = fragmentActivity;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item_view, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView eventNameView, eventDateView;
        private ImageView eventImageView;

        private ListViewHolder(View itemView) {
            super(itemView);
            eventDateView = itemView.findViewById(R.id.event_date);
            eventNameView = itemView.findViewById(R.id.event_name);
            eventImageView = itemView.findViewById(R.id.event_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventDetailFragment eventDetailFragment = new EventDetailFragment();
                    activity.getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container_view_tag, eventDetailFragment).commit();
                }
            });
        }

        private void bindView(int position) {
            Event event = data.get(position);
            eventNameView.setText(event.getEventName());
            eventDateView.setText(String.valueOf(event.getDate()));
            Picasso.get().load(event.getImageUrl()).fit().centerCrop().into(eventImageView);
        }
    }
}
