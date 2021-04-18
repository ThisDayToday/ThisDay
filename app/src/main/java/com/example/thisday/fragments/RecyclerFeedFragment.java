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


public class RecyclerFeedFragment extends Fragment {


    public static final String TAG = "FeedFragment";
    private RecyclerView rvFocusedFeed;
    private EventsAdapter eventsAdapter;
    private List<Event> allEvents;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecyclerFeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFeedFragment newInstance(String param1, String param2) {
        RecyclerFeedFragment fragment = new RecyclerFeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_feed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvFocusedFeed = view.findViewById(R.id.rvFocusedFeed);

        allEvents = new ArrayList<>();
        eventsAdapter = new EventsAdapter(getContext(), allEvents);
        eventsAdapter.setFeedFragment(true);
        eventsAdapter.setProfileFragment(false);
        rvFocusedFeed.setAdapter(eventsAdapter);
        rvFocusedFeed.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFocusedFeed.setAdapter(eventsAdapter);
        rvFocusedFeed.setLayoutManager(new LinearLayoutManager(getContext()));
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