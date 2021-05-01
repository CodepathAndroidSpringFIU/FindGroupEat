package com.example.findgroupeat;


import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

public class ResultActivity extends AppCompatActivity {

    TextView tvResName;
    TextView tvResAddress;
    ImageView ivPhoto;
    Button btnExit;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResName = findViewById(R.id.tvResName);
        tvResAddress = findViewById(R.id.tvResAddress);
        ivPhoto = findViewById(R.id.ivPhoto);
        btnExit = findViewById(R.id.btnExit);
        toolbar = findViewById(R.id.toolbar_main);

        Intent i = getIntent();
        String photoUrl = i.getStringExtra("restaurantPhotoUrl");
        String name = i.getStringExtra("restaurantName");
        String address = i.getStringExtra("restaurantAddress");

        setSupportActionBar(toolbar);

        tvResAddress.setText(address);
        tvResName.setText(name);
        Glide.with(this)
                .load(photoUrl)
                .centerCrop()
                .into(ivPhoto);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);
                Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
                lobby.removeUser(ParseUser.getCurrentUser());
                try {
                    if (getUserNumInLobby() == 0) {
                        lobby.deleteInBackground();
                    }
                } catch (ParseException e) {
                    Log.e("ResultActivity", e.toString());
                }
            }
        });
    }

    public int getUserNumInLobby() throws ParseException {
        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        ParseQuery<ParseUser> users = lobby.getUsers().getQuery();
        return users.count();
    }

}