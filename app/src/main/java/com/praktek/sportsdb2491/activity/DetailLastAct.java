package com.praktek.sportsdb2491.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.praktek.sportsdb2491.R;
import com.praktek.sportsdb2491.database.DatabaseHelper;
import com.praktek.sportsdb2491.model.Events;

public class DetailLastAct extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";
    TextView league;
    ImageView imgLastDetail;
    ImageView btnFavorite;
    TextView eventDetail;
    TextView homeScore;
    TextView awayScore;
    TextView venueLast;
    TextView countryLast;
    TextView dateDetail;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_last);

        league = findViewById(R.id.leagueLast);
        imgLastDetail = findViewById(R.id.imgLastDetail);
        btnFavorite = findViewById(R.id.btnFavorite);
        eventDetail = findViewById(R.id.txtEventDetail);
        homeScore = findViewById(R.id.homeScore);
        awayScore = findViewById(R.id.awayScore);
        venueLast = findViewById(R.id.venueLast);
        countryLast = findViewById(R.id.countryLast);
        dateDetail = findViewById(R.id.txtDateDetail);
        status = findViewById(R.id.txtStatus);

        final Events events = getIntent().getParcelableExtra(ITEM_EXTRA);

        if (events != null) {
            Glide.with(this)
                    .load(events.getStrThumb())
                    .into(imgLastDetail);
            league.setText(events.getStrLeague());
            eventDetail.setText(events.getStrEvent());
            homeScore.setText(events.getIntHomeScore());
            awayScore.setText(events.getIntAwayScore());
            venueLast.setText(events.getStrVenue());
            countryLast.setText(events.getStrCountry());
            dateDetail.setText(events.getDateEvent());
            status.setText(events.getStrStatus());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Last Events");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event = eventDetail.getText().toString();
                String date = dateDetail.getText().toString();
                String statusDb = status.getText().toString();

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                Events ev = new Events();

                ev.setStrThumb(events.getStrThumb());
                ev.setStrEvent(event);
                ev.setDateEvent(date);
                ev.setStrStatus(statusDb);

                db.addEvents(ev);

                Intent gotoLast = new Intent(DetailLastAct.this, MainActivity.class);
                startActivity(gotoLast);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}