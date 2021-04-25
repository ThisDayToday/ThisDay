package com.example.thisday;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
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


        private String attendees;
        private String Location;
        private String eventType;
        private String eventUser;
        private String eventDescription;
       // private View eventContainer;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvEventName = itemView.findViewById(R.id.tvFeedName);
            tvEventType = itemView.findViewById(R.id.tvFeedType);
            ivEventImage = itemView.findViewById(R.id.ivFeedImage);
            //eventContainer = itemView.findViewById(R.id.eventContiner);
        }

        public void bind(Event Event) {
            tvEventName.setText(Event.getName());
            tvEventType.setText(Event.getType());
//            tvEventDate.setText(Event.getDate());

            //tvEventDate.setText(toString(Event.getDate()));

            eventUser = Event.getOrganization().getUsername();
            eventType = Event.getType();
            Location = Event.getLocation();
            attendees = Event.getAttendees();
            eventDescription = Event.getDescription();

            // may have to format date
            // tvEventDate.setText(Event.getDate().toString());
            ParseFile image = Event.getImage();
            if (image != null){
                Glide.with(context).load(Event.getImage().getUrl()).circleCrop().into(ivEventImage);
            }

          tvEventName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap bmp = ivEventImage.getDrawingCache();
                   // ByteArrayOutputStream bs = new ByteArrayOutputStream();
                 //   bmp.compress(Bitmap.CompressFormat.JPEG, 100, bs);
                  //  byte[] byteArray = bs.toByteArray();



                    Intent intent = new Intent(itemView.getContext(),DetailActivity.class );

                    intent.putExtra("user", eventUser);
                    intent.putExtra("type", eventType);
                    intent.putExtra("attendees", attendees);
                    intent.putExtra("date", Event.getDate());
                    intent.putExtra("name", Event.getName());
                    intent.putExtra("PICTURE", bmp);
                    intent.putExtra("description", eventDescription);


                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
