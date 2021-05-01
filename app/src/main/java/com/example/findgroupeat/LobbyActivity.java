package com.example.findgroupeat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.findgroupeat.fragments.HomeFragment;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {

    TextView tvLobbyName;
    ListView lvUsers;
    Button btnSwipe;
    Button btnLeave;
    SwipeRefreshLayout swipeContainer;
    List<String> usernames;
    ArrayAdapter adapter;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        btnLeave = findViewById(R.id.btnLeave);
        tvLobbyName = findViewById(R.id.tvLobbyName);
        lvUsers = findViewById(R.id.lvUsers);
        btnSwipe = findViewById(R.id.btnSwipe);
        swipeContainer = findViewById(R.id.swipeContainer);

        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        tvLobbyName.setText(lobby.getName());


        ParseQuery<ParseUser> query = lobby.getUsers().getQuery();
        usernames = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user,usernames);
        lvUsers.setAdapter(adapter);

        btnSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LobbyActivity.this, RestaurantsActivity.class);
                i.putExtra("lobby", Parcels.wrap(lobby));
                startActivity(i);
                finish();
            }
        });

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To-do, add alert dialog asking if they are sure they wish to leave the lobby
                usernames.clear();
                query.findInBackground(new FindCallback<ParseUser>() {
                    @Override
                    public void done(List<ParseUser> objects, ParseException e) {
                        if (e != null) {
                            Log.e("LobbyActivity", "Error in getting users");
                        }
                        ParseUser user = ParseUser.getCurrentUser();
                        objects.remove(user.getUsername());
                        lobby.removeUser(user);
                        Intent i = new Intent(LobbyActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }


                });
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryUsers(query, usernames);
            }
        });

        queryUsers(query, usernames);
    }

    private void queryUsers(ParseQuery<ParseUser> query, List<String> usernames) {
        usernames.clear();
        query.findInBackground((users, e) -> {
            if (e != null) {
                Log.e("LobbyActivity", "Error in getting users");
            }

            for (ParseUser user : users) {
                usernames.add(user.getUsername());
            }
            adapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(false);
        });
    }


}