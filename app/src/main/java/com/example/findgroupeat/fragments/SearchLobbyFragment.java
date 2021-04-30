package com.example.findgroupeat.fragments;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.example.findgroupeat.R;
import com.example.findgroupeat.adapters.LobbyAdapter;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchLobbyFragment extends Fragment {

    RecyclerView rvLobbies;
    List<Lobby> lobbyList;
    LobbyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.activity_search_lobby, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvLobbies = view.findViewById(R.id.rvLobbies);
        lobbyList = new ArrayList<>();

        adapter = new LobbyAdapter(getContext(), lobbyList);
        rvLobbies.setAdapter(adapter);
        rvLobbies.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        SearchView.SearchAutoComplete searchText = searchView.findViewById(R.id.search_src_text);
        searchText.setTextColor(Color.WHITE);

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
    }
}