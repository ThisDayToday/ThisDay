package com.example.thisday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private ImageView eventPicIV;
    private TextView eventNameTV;
    private TextView nameHostTV;
    private TextView eventDateTV;
    private TextView attendeeAmountTV;
    private TextView eventTypeTV;
    private TextView tvDescriptionD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        eventPicIV = findViewById(R.id.eventPicIV);
        eventNameTV =findViewById(R.id.eventNameTV);
        nameHostTV = findViewById(R.id.nameHostTV);
        eventDateTV = findViewById(R.id.eventDateTV);
        attendeeAmountTV = findViewById(R.id.atendeeAmountTV);
        eventTypeTV = findViewById(R.id.eventTypeTV);
        tvDescriptionD = findViewById(R.id.tvDescriptionD);

        Intent intent = getIntent();

         Bitmap bmp = getIntent().getParcelableExtra("PICTURE");


        eventNameTV.setText(intent.getStringExtra("name"));
        nameHostTV.setText(intent.getStringExtra("user"));
        eventDateTV.setText(intent.getStringExtra("date"));
        attendeeAmountTV.setText(intent.getStringExtra("attendees"));
        eventTypeTV.setText(intent.getStringExtra("type"));
        tvDescriptionD.setText(intent.getStringExtra("description"));

        eventPicIV.setImageBitmap(bmp);

    }
}