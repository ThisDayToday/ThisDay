package com.example.thisday.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thisday.Event;
import com.example.thisday.EventsAdapter;
import com.example.thisday.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class FeedFragment extends Fragment {

    public static final String TAG = "FeedFragment";
    protected RecyclerView rvPopularEvents;
    protected RecyclerView rvFriendsEvents;
    private EventsAdapter popularEventsAdapter;
    private EventsAdapter friendsEventsAdapter;
    private List<Event> friendEvents;
    private List<Event> popularEvents;

    private ImageButton btnSeePopular;
    private ImageButton btnSeeFriends;


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
        btnSeePopular = view.findViewById(R.id.btnSeePopular);
        btnSeeFriends = view.findViewById(R.id.btnSeeFriends);

        popularEvents = new ArrayList<>();
        popularEventsAdapter = new EventsAdapter(getContext(), popularEvents);
        popularEventsAdapter.setFeedFragment(true);
        popularEventsAdapter.setProfileFragment(false);

        friendEvents = new ArrayList<>();
        friendsEventsAdapter = new EventsAdapter(getContext(), friendEvents);
        friendsEventsAdapter.setFeedFragment(true);
        friendsEventsAdapter.setProfileFragment(false);


        rvPopularEvents.setNestedScrollingEnabled(false);
        rvPopularEvents.setAdapter(popularEventsAdapter);
        rvPopularEvents.setLayoutManager(new LinearLayoutManager(getContext()));

        rvFriendsEvents.setNestedScrollingEnabled(false);
        rvFriendsEvents.setAdapter(friendsEventsAdapter);
        rvFriendsEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        queryEvents();




        btnSeePopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "See more popular events!", Toast.LENGTH_SHORT).show();
                RecyclerFeedFragment rvf = new RecyclerFeedFragment();
                rvf.setFeedtype(true);
                Fragment fragment = rvf;
                FragmentManager fragmentManager = FeedFragment.super.getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            }
        });

        btnSeeFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "See more friends events!", Toast.LENGTH_SHORT).show();
                RecyclerFeedFragment rvf = new RecyclerFeedFragment();
                rvf.setFeedtype(false);
                Fragment fragment = rvf;
                FragmentManager fragmentManager = FeedFragment.super.getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
            }
        });

    }

    protected void queryEvents() {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.setLimit(3);
        query.addDescendingOrder(Event.KEY_CREATED_KEY);
        query.whereEqualTo(Event.KEY_POPULAREVENT, true);
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
                popularEvents.addAll(events);
                popularEventsAdapter.notifyDataSetChanged();
            }
        });

        query.whereEqualTo(Event.KEY_FRIENDEVENT, true);
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
                friendEvents.addAll(events);
                friendsEventsAdapter.notifyDataSetChanged();
            }
        });


    }
}