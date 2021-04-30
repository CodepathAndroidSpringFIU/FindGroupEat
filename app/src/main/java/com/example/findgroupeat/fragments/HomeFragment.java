package com.example.findgroupeat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.findgroupeat.LobbyActivity;
import com.example.findgroupeat.R;

import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class HomeFragment extends Fragment {

    EditText etLobbyName;
    EditText etLobbyPass;
    Button btnEnter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etLobbyName = view.findViewById(R.id.etLobbyName);
        etLobbyPass = view.findViewById(R.id.etLobbyPass);
        btnEnter = view.findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<Lobby> query = ParseQuery.getQuery(Lobby.class);
                String lobbyName = etLobbyName.getText().toString();
                String lobbyPass = etLobbyPass.getText().toString();
                query.whereEqualTo("name", lobbyName);
                query.findInBackground(new FindCallback<Lobby>() {
                    @Override
                    public void done(List<Lobby> objects, ParseException e) {
                        if (e == null) {
                            if (!objects.isEmpty() && lobbyPass.equals(objects.get(0).getPassword())) {
                                Lobby lobby = objects.get(0);
                                // Add current user to the lobby
                                lobby.addUser(ParseUser.getCurrentUser());
                                Intent i = new Intent(getContext(), LobbyActivity.class);
                                i.putExtra("lobby", Parcels.wrap(lobby));
                                startActivity(i);
                                Toast.makeText(getContext(), "Join lobby successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                etLobbyName.setText("");
                                etLobbyPass.setText("");
                                Toast.makeText(getContext(), "Lobby name/password is invalid!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            etLobbyName.setText("");
                            etLobbyPass.setText("");
                            Toast.makeText(getContext(), "Something goes wrong. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}