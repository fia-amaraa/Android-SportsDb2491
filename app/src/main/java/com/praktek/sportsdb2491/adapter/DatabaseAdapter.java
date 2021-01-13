package com.praktek.sportsdb2491.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.database.DatabaseHelper;
import com.praktek.sportsdb2491.model.Events;

import java.util.ArrayList;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

    private ArrayList<Events> listEvents;
    private Events events;

    private Context mContext;

    public DatabaseAdapter(Context context, ArrayList<Events> listEvents) {
        this.listEvents = listEvents;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_db,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        events = listEvents.get(position);

        holder.eventsDb.setText(events.getStrEvent());
        holder.dateDb.setText(events.getDateEvent());
        holder.statusDb.setText(events.getStrStatus());

        Glide.with(holder.itemView)
                .load(events.getStrThumb())
                .into(holder.imgDb);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                events = listEvents.get(position);

                DatabaseHelper db = new DatabaseHelper(mContext);
                db.deleteEvents(String.valueOf(events.getIdEvent()));

                notifyItemRemoved(position);
                listEvents.remove(position);

                Toast.makeText(mContext, "Events yang terhapus : " + events.getStrEvent(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listEvents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDb;
        TextView eventsDb;
        TextView dateDb;
        TextView statusDb;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDb = itemView.findViewById(R.id.imgDb);
            eventsDb = itemView.findViewById(R.id.txtEventDb);
            dateDb = itemView.findViewById(R.id.txtDateDb);
            statusDb = itemView.findViewById(R.id.txtStatusDb);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
