package com.example.thisday.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thisday.Event;
import com.example.thisday.EventsAdapter;
import com.example.thisday.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    public static final String TAG = "EventsFragment";
    protected RecyclerView rvPopularEvents;
    protected RecyclerView rvFriendsEvents;
    protected EventsAdapter eventsAdapter;
    protected List<Event> allEvents;


    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPopularEvents = view.findViewById(R.id.rvPopularEvents);
        rvFriendsEvents = view.findViewById(R.id.rvFriendsEvents);

        allEvents = new ArrayList<>();
        eventsAdapter = new EventsAdapter(getContext(), allEvents);

        rvPopularEvents.setAdapter(eventsAdapter);
        rvPopularEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFriendsEvents.setAdapter(eventsAdapter);
        rvFriendsEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        queryEvents();
    }

    protected void queryEvents() {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Event>() {
            @Override
            public void done(List<Event> events, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue with getting Events", e);
                    return;
                }
                for( Event event : events){

                    try {
                        Log.i(TAG, "Event: " + event.getDescription() + event.getOrganization().fetchIfNeeded().toString());
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }

                }
                allEvents.addAll(events);
                eventsAdapter.notifyDataSetChanged();
            }
        });
    }
}