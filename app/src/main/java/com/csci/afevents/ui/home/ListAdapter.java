package com.csci.afevents.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.csci.afevents.R;
import com.csci.afevents.entities.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adama Camara
 * <p>
 * Recycler View adapter for displaying events in a list layout
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    List<Event> data = new ArrayList<>();

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
        }

        private void bindView(int position) {
            Event event = data.get(position);
            eventNameView.setText(event.getEventName());
            eventDateView.setText(String.valueOf(event.getDate()));
            Picasso.get().load(event.getImageUrl()).fit().centerCrop().into(eventImageView);
            final Bundle bundle = new Bundle();
            bundle.putSerializable("event", event);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(itemView)
                            .navigate(R.id.action_navigation_home_to_event_detail_fragment, bundle);
                }
            });
        }
    }
}
