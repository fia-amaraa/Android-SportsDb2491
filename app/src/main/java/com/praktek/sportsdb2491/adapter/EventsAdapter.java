package com.praktek.sportsdb2491.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.praktek.sportsdb2491.OnItemClickCallback;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.model.Events;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ListViewHolder> {

    private ArrayList<Events> listEvents;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public EventsAdapter(ArrayList<Events> listEvents) {
        this.listEvents = listEvents;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ListViewHolder holder, int position) {
            final Events events = listEvents.get(position);

            holder.txtEvent.setText(events.getStrEvent());
            holder.txtDate.setText(events.getDateEvent());

            Glide.with(holder.itemView)
                    .load(events.getStrThumb())
                    .into(holder.imgEvents);

            Log.d("Testing PosterPath", "Testing Link"+ events.getStrThumb());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(events);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgEvents;
        TextView txtEvent;
        TextView txtDate;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEvents = itemView.findViewById(R.id.imgEvents);
            txtEvent = itemView.findViewById(R.id.txtEvent);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
