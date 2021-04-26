package com.example.findgroupeat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findgroupeat.models.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {

    TextView tvLobbyName;
    ListView lvUsers;
    Button btnSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        tvLobbyName = findViewById(R.id.tvLobbyName);
        lvUsers = findViewById(R.id.lvUsers);
        btnSwipe = findViewById(R.id.btnSwipe);

        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        tvLobbyName.setText(lobby.getName());


        ParseQuery<ParseUser> query = lobby.getUsers().getQuery();
        List<String> usernames = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.user,usernames);
        lvUsers.setAdapter(adapter);

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e != null) {
                    Log.e("LobbyActivity", "Error in getting users");
                }

                for (ParseUser user : users) {
                    usernames.add(user.getUsername());
                }
                adapter.notifyDataSetChanged();
            }
        });

    }
}