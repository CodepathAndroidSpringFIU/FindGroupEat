package com.example.findgroupeat;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;


import com.example.findgroupeat.adapters.LobbyAdapter;
import com.example.findgroupeat.models.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchLobbyActivity extends AppCompatActivity {

    RecyclerView rvLobbies;
    List<Lobby> lobbyList;
    LobbyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_lobby);

        rvLobbies = findViewById(R.id.rvLobbies);
        lobbyList = new ArrayList<>();

        adapter = new LobbyAdapter(this, lobbyList);
        rvLobbies.setAdapter(adapter);
        rvLobbies.setLayoutManager(new LinearLayoutManager(this));
        getLobbyList();
    }

    public void getLobbyList() {
        ParseQuery<Lobby> query = ParseQuery.getQuery(Lobby.class);
        query.findInBackground(new FindCallback<Lobby>() {
            @Override
            public void done(List<Lobby> lobbies, ParseException e) {
                if (e != null) {
                    Log.e("SearchLobbyActivity", "Error in getting lobbies");
                }
                adapter.addAll(lobbies);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}