package com.example.thisday;

import android.content.Context;
import android.util.Log;
import android.content.Intent;
import android.text.Layout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.thisday.Event;
import com.example.thisday.R;
import com.parse.ParseFile;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    public static final String TAG = "ADAPTER";
    private Context context;
    private List<Event> events;
    private Boolean feedFragment;
    private Boolean profileFragment;

    public EventsAdapter(Context context, List<Event> events){
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(profileFragment){
            view = LayoutInflater.from(context).inflate(R.layout.activity_item_profile_event, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_feed_event, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEventName;
        private TextView tvEventType;
        private TextView tvEventDate;
        private ImageView ivEventImage;

        private TextView tvOrg;
        private TextView tvDesc;
        private CheckBox cbGoing;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            if(profileFragment == true){
                tvEventName = itemView.findViewById(R.id.tvProfileEventName);
                tvOrg = itemView.findViewById(R.id.tvOrg);
                tvDesc = itemView.findViewById(R.id.tvDescription);
                ivEventImage = itemView.findViewById(R.id.ivProfileEvent);
                tvEventDate = itemView.findViewById(R.id.tvDate);
            }else{
                tvEventName = itemView.findViewById(R.id.tvFeedName);
                tvEventType = itemView.findViewById(R.id.tvFeedType);
                ivEventImage = itemView.findViewById(R.id.ivFeedImage);
                tvEventDate = itemView.findViewById(R.id.tvFeedDate);
            }
            cbGoing = itemView.findViewById(R.id.cbGoing);
        }

        public void bind(Event event) {
            if(profileFragment == true){
                tvEventName.setText(event.getName());
                tvDesc.setText(event.getDescription());
                tvOrg.setText(event.getOrganization().getUsername());
                tvEventDate.setText(event.getDate());
            }else {
                tvEventName.setText(event.getName());
                tvEventType.setText(event.getType());
                tvEventDate.setText(event.getDate());
                if(event.inProfile()){
                    cbGoing.setChecked(true);
                }

                cbGoing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(cbGoing.isChecked()){
                            event.setInProfile(true);
                            cbGoing.setChecked(true);
                            EventsAdapter.super.notifyDataSetChanged();
                        }
                    }
                });
            }

            // may have to format date
            // tvEventDate.setText(Event.getDate().toString());
            ParseFile image = event.getImage();
            if (image != null && feedFragment){
                Glide.with(context).load(event.getImage().getUrl()).circleCrop().into(ivEventImage);
            }else{
                Glide.with(context).load(event.getImage().getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners(14))).into(ivEventImage);
            }

          tvEventName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);
                }
            });

        }


    }

    @Override
    public int getItemViewType(int position) {

        if(events.get(position).inProfile() && profileFragment == true){
            return 1;
        }else {
            return 0;
        }
    }

    public void setFeedFragment(Boolean b){ this.feedFragment = b;}
    public void setProfileFragment(Boolean b){ this.profileFragment = b;}

}
