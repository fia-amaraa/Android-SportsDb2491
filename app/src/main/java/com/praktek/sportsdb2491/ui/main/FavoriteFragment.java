package com.praktek.sportsdb2491.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.adapter.DatabaseAdapter;
import com.praktek.sportsdb2491.database.DatabaseHelper;
import com.praktek.sportsdb2491.model.Events;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecyclerView rvEventsDb;
    private ArrayList<Events> listEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        rvEventsDb = view.findViewById(R.id.rv_db);

        rvEventsDb.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseHelper db = new DatabaseHelper(getContext());
        listEvents = db.getAllEvents();

        if (listEvents.size() != 0) {
            DatabaseAdapter adapter = new DatabaseAdapter(getContext(),listEvents);
            rvEventsDb.setAdapter(adapter);
        }

        return view;
    }
}