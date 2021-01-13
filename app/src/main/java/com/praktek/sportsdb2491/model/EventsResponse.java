package com.praktek.sportsdb2491.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsResponse {
    @SerializedName("events")
    private ArrayList<Events> events;

    @SerializedName("total_events")
    private int total_events;

    public ArrayList<Events> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Events> events) {
        this.events = events;
    }

    public int getTotal_events() {
        return total_events;
    }

    public void setTotal_events(int total_events) {
        this.total_events = total_events;
    }
}
