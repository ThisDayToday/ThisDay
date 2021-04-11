package com.example.thisday.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.thisday.R;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;


public class UserProfileFragment extends Fragment {
    private ImageView ivBackground;
    private ImageView ivUserIMG;
    private TextView tvUsername;
    private TextView tvLocation;
    private TextView tvRecentEvents;
    private TextView tvFutureEvents;
    private boolean currentTab;
    private static final String PROFILE_IMG_KEY = "profileImg";
    private static final String BACKGROUND_IMG_KEY = "profileBackground";


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
        tvFutureEvents = view.findViewById(R.id.tvLocation);

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());



        ParseFile profileImg = (ParseFile) ParseUser.getCurrentUser().get(PROFILE_IMG_KEY);
        if (profileImg != null){
            Glide.with(view).load(profileImg.getUrl()).circleCrop().into(ivUserIMG);
        }

        ParseFile bgImg = (ParseFile) ParseUser.getCurrentUser().get(BACKGROUND_IMG_KEY);
        if (bgImg != null){
            Glide.with(view).load(bgImg.getUrl()).into(ivBackground);
        }

        currentTab = false;
        tvRecentEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentTab){
                    tvRecentEvents.setBackgroundColor(Color.LTGRAY);
                    tvFutureEvents.setBackgroundColor(Color.WHITE);
                    currentTab = false;
                }
            }
        });

        tvFutureEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!currentTab){
                    tvRecentEvents.setBackgroundColor(Color.WHITE);
                    tvFutureEvents.setBackgroundColor(Color.LTGRAY);
                    currentTab = true;
                }
            }
        });

    }


}