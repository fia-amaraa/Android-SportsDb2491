package com.praktek.sportsdb2491.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.praktek.sportsdb2491.OnItemClickCallback;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.activity.DetailNextAct;
import com.praktek.sportsdb2491.adapter.EventsAdapter;
import com.praktek.sportsdb2491.api.ApiClient;
import com.praktek.sportsdb2491.api.ApiServiceNext;
import com.praktek.sportsdb2491.model.Events;
import com.praktek.sportsdb2491.model.EventsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextEventsFragment extends Fragment {

    private ArrayList<Events> listNextEvents = new ArrayList<>();
    private RecyclerView rvNextEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_next_events, container, false);

        rvNextEvents = view.findViewById(R.id.rv_next);
        rvNextEvents.setHasFixedSize(true);

        rvNextEvents.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiServiceNext service = ApiClient.getRetrofitInstance().create(ApiServiceNext.class);
        Call<EventsResponse> call = service.getNextEvents("1");
        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                listNextEvents = response.body().getEvents();

                EventsAdapter nextEventsAdapter = new EventsAdapter(listNextEvents);
                rvNextEvents.setAdapter(nextEventsAdapter);

                nextEventsAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Events events) {
                        Intent gotoDetailNext = new Intent(getActivity(), DetailNextAct.class);
                        gotoDetailNext.putExtra(DetailNextAct.ITEM_EXTRA, events);
                        startActivity(gotoDetailNext);
                    }
                });
            }

            @Override
            public void onFailure(Call<EventsResponse> call, Throwable t) {

            }
        });

        return view;
    }
}