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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.findgroupeat.adapters.UserAdapter;
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
    RecyclerView rvUsers;
    Button btnSwipe;
    Button btnLeave;
    SwipeRefreshLayout swipeContainer;
    List<ParseUser> userList;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        btnLeave = findViewById(R.id.btnLeave);
        tvLobbyName = findViewById(R.id.tvLobbyName);
        rvUsers = findViewById(R.id.rvUsers);
        btnSwipe = findViewById(R.id.btnSwipe);
        swipeContainer = findViewById(R.id.swipeContainer);

        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        tvLobbyName.setText(lobby.getName());

        ParseQuery<ParseUser> query = lobby.getUsers().getQuery();
        userList = new ArrayList<>();
        adapter = new UserAdapter(this, userList);
        rvUsers.setAdapter(adapter);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));

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
                Intent i = new Intent(LobbyActivity.this, MainActivity.class);
                startActivity(i);
                //Remove the user from looby
                lobby.removeUser(ParseUser.getCurrentUser());
                //Check number of users in the lobby, if it's 0 then delete the lobby
                try {
                    if (getUserNumInLobby() == 0) {
                        lobby.deleteInBackground();
                    }
                } catch (ParseException e) {
                    Log.e("ResultActivity", e.toString());
                }
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               fetchUsers(query);
            }
        });

        queryUsers(query);
    }

    private void fetchUsers(ParseQuery<ParseUser> query) {
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e != null) {
                    Log.e("LobbyActivity", "Error in getting users");
                }
                adapter.clear();
                adapter.addAll(objects);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    private void queryUsers(ParseQuery<ParseUser> query) {
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e != null) {
                    Log.e("LobbyActivity", "Error in getting users");
                }
                userList.addAll(objects);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public int getUserNumInLobby() throws ParseException {
        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        ParseQuery<ParseUser> users = lobby.getUsers().getQuery();
        return users.count();
    }
}