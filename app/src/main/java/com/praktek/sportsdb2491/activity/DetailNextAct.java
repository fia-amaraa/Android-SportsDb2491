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

public class DetailNextAct extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";

    TextView league;
    ImageView imgNextDetail;
    ImageView btnFavorite;
    TextView eventDetail;
    TextView round;
    TextView venueNext;
    TextView countryNext;
    TextView dateDetail;
    TextView timeDetail;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_next);

        league = findViewById(R.id.leagueNext);
        imgNextDetail = findViewById(R.id.imgNextDetail);
        btnFavorite = findViewById(R.id.btnFavoriteNext);
        eventDetail = findViewById(R.id.txtEventDetailNext);
        round = findViewById(R.id.round);
        venueNext = findViewById(R.id.venueNext);
        countryNext = findViewById(R.id.countryNext);
        dateDetail = findViewById(R.id.txtDateDetailNext);
        timeDetail = findViewById(R.id.txtTimeDetail);
        status = findViewById(R.id.txtStatusNext);

        final Events events = getIntent().getParcelableExtra(ITEM_EXTRA);

        if (events != null) {
            Glide.with(this)
                    .load(events.getStrThumb())
                    .into(imgNextDetail);
            league.setText(events.getStrLeague());
            eventDetail.setText(events.getStrEvent());
            round.setText(events.getIntRound());
            venueNext.setText(events.getStrVenue());
            countryNext.setText(events.getStrCountry());
            dateDetail.setText(events.getDateEvent());
            timeDetail.setText(events.getStrTime());
            status.setText(events.getStrStatus());
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail Next Events");
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

                Intent gotoLast = new Intent(DetailNextAct.this, MainActivity.class);
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