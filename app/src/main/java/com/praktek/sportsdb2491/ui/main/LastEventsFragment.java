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
import com.praktek.sportsdb2491.activity.DetailLastAct;
import com.praktek.sportsdb2491.adapter.EventsAdapter;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.api.ApiClient;
import com.praktek.sportsdb2491.api.ApiServiceLast;
import com.praktek.sportsdb2491.model.Events;
import com.praktek.sportsdb2491.model.EventsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LastEventsFragment extends Fragment {

    private ArrayList<Events> listEvents = new ArrayList<>();
    private RecyclerView rvLastEvents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_events, container, false);

        rvLastEvents = view.findViewById(R.id.rv_last);
        rvLastEvents.setHasFixedSize(true);

        rvLastEvents.setLayoutManager(new LinearLayoutManager(getContext()));

        ApiServiceLast service = ApiClient.getRetrofitInstance().create(ApiServiceLast.class);
        Call<EventsResponse> call = service.getLastEvents("1");
        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                listEvents = response.body().getEvents();

                EventsAdapter eventsAdapter = new EventsAdapter(listEvents);
                rvLastEvents.setAdapter(eventsAdapter);

                eventsAdapter.setOnItemClickCallback(new OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Events events) {
                        Intent gotoDetailLast = new Intent(getActivity(), DetailLastAct.class);
                        gotoDetailLast.putExtra(DetailLastAct.ITEM_EXTRA, events);
                        startActivity(gotoDetailLast);
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