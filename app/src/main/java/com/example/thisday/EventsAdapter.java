package com.example.thisday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thisday.Event;
import com.example.thisday.R;
import com.parse.ParseFile;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context context;
    private List<Event> Events;

    public EventsAdapter(Context context, List<Event> Events){
        this.context = context;
        this.Events = Events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event Event = Events.get(position);
        holder.bind(Event);
    }

    @Override
    public int getItemCount() {
        return Events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvEventName;
        private TextView tvEventType;
        private TextView tvEventDate;
        private ImageView ivEventImage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvFeedName);
            tvEventType = itemView.findViewById(R.id.tvFeedType);
            ivEventImage = itemView.findViewById(R.id.ivFeedImage);
        }

        public void bind(Event Event) {
            tvEventName.setText(Event.getName());
            tvEventType.setText(Event.getType());
            // may have to format date
            // tvEventDate.setText(Event.getDate().toString());
            ParseFile image = Event.getImage();
            if (image != null){
                Glide.with(context).load(Event.getImage().getUrl()).circleCrop().into(ivEventImage);
            }
        }
    }
}
