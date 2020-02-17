package com.csci.afevents.ui.home;

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
import com.csci.afevents.api.data;
import com.csci.afevents.impl.DummyEventRetriever;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author Adama Camara
 * Class is a list adapter that upload event's data
 * from our data.java to the recyclerView
 */

public class ListAdapter extends RecyclerView.Adapter {

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

        return data.events.size();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView eventName, eventDescription, eventDate;
        //private ImageView mItemImage;

        private ListViewHolder(View itemView){
            super(itemView);
            eventDate = itemView.findViewById(R.id.event_Date);
            eventDescription = itemView.findViewById(R.id.event_Description);
            eventName = itemView.findViewById(R.id.event_Name);
            itemView.setOnClickListener(this);
        }
        private void bindView(int position){
            eventName.setText(data.name[position]);
            eventDescription.setText(data.description[position]);
            eventDate.setText(""+data.date[position]);

        }
        public void onClick(View view){

        }

    }
}
