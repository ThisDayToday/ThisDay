package com.example.thisday.fragments;

import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.thisday.Event;
import com.example.thisday.EventsAdapter;
import com.example.thisday.R;
import com.example.thisday.RoundedCornersTransformation;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UserProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    private ImageView ivBackground;
    private ImageView ivUserIMG;
    private TextView tvUsername;
    private TextView tvLocation;
    private TextView tvRecentEvents;
    private RecyclerView rvEvents;
    protected EventsAdapter eventsAdapter;
    private List<Event> allEvents;
    private boolean currentTab;
    private static final String PROFILE_IMG_KEY = "profileImg";
    private static final String BACKGROUND_IMG_KEY = "profileBackground";
    public static int sCorner = 200;
    public static int sMargin = 10;
    public static int sBorder = 12;
    public static String sColor = "#ffffff";



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserProfileFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
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
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivBackground = view.findViewById(R.id.ivBackground);
        ivUserIMG = view.findViewById(R.id.ivUserIMG);
        tvUsername = view.findViewById(R.id.tvUsername);
        tvLocation = view.findViewById(R.id.tvLocation);
        tvRecentEvents = view.findViewById(R.id.tvRecentEvents);

        tvUsername.setText("@" + ParseUser.getCurrentUser().getUsername());

        rvEvents = view.findViewById(R.id.rvEvents);
        allEvents = new ArrayList<>();
        eventsAdapter = new EventsAdapter(getContext(), allEvents);
        eventsAdapter.setProfileFragment(true);
        eventsAdapter.setFeedFragment(false);
        rvEvents.setAdapter(eventsAdapter);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        tvLocation.setText(ParseUser.getCurrentUser().getString("location"));

        queryEvents();


        ParseFile profileImg = (ParseFile) ParseUser.getCurrentUser().get(PROFILE_IMG_KEY);
        if (profileImg != null){
            //Glide.with(view).load(profileImg.getUrl()).circleCrop().into(ivUserIMG);

            // Rounded corners
            Glide.with(this).load(profileImg.getUrl()).circleCrop().apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(getContext(), sCorner, sMargin))).into(ivUserIMG);

            // Rounded corners with border
            Glide.with(this).load(profileImg.getUrl()).circleCrop().apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(getContext(), sCorner, sMargin, sColor, sBorder))).into(ivUserIMG);
        }

        ParseFile bgImg = (ParseFile) ParseUser.getCurrentUser().get(BACKGROUND_IMG_KEY);
        if (bgImg != null){
            Glide.with(view).load(bgImg.getUrl()).into(ivBackground);
        }

    }


    protected void queryEvents() {
        ParseQuery<Event> query = ParseQuery.getQuery(Event.class);
        query.addDescendingOrder(Event.KEY_CREATED_KEY);
        query.whereEqualTo(Event.KEY_INPROFILE, true);
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