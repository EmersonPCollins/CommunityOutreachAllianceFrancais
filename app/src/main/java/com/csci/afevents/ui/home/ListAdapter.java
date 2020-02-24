package com.csci.afevents.ui.home;

import android.content.Context;
import android.graphics.ImageFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csci.afevents.R;
import com.csci.afevents.api.EventRetriever;
import com.csci.afevents.api.EventRetrieverFactory;
import com.csci.afevents.entities.Event;
import com.csci.afevents.impl.DummyEventRetriever;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author Adama Camara
 * Class is a list adapter that upload event's data
 * from our data.java to the recyclerView
 */

public class ListAdapter extends RecyclerView.Adapter {

    List<Event> list = EventRetrieverFactory.getInstance(this.getContext()).getEvents();
    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);

    }


    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    private Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView eventName, eventDescription, eventDate;

        private ListViewHolder(View itemView){
            super(itemView);
            eventDate = itemView.findViewById(R.id.event_Date);
            eventDescription = itemView.findViewById(R.id.event_Description);
            eventName = itemView.findViewById(R.id.event_Name);

            itemView.setOnClickListener(this);
        }
        private void bindView(int position){
            String[] name = new String[getItemCount()];
            String[] description = new String[getItemCount()];
            long[] date = new long[getItemCount()];

            for(int i=0; i<getItemCount(); i++){
                Event e = list.get(i);
                name[i] = e.getEventName();
                description[i] = e.getDescription();
                date[i] = e.getDate();
            }
            eventName.setText(name[position]);
            eventDescription.setText(description[position]);
            eventDate.setText(""+date[position]);
        }

        public void onClick(View view){

        }

    }
}
