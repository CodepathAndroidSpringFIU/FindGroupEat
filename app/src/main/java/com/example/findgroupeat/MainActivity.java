package com.example.findgroupeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findgroupeat.models.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etLobbyName;
    EditText etLobbyPass;
    Button btnEnter;
    Button btnCreateLobby;
    Button btnSearchLobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLobbyName = findViewById(R.id.etLobbyName);
        etLobbyPass = findViewById(R.id.etLobbyPass);
        btnCreateLobby = findViewById(R.id.btnCreateLobby);
        btnSearchLobby = findViewById(R.id.btnSearchLobby);
        btnEnter = findViewById(R.id.btnEnter);

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
                                Intent i = new Intent(MainActivity.this, LobbyActivity.class);
                                i.putExtra("lobby", Parcels.wrap(lobby));
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Join lobby successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                etLobbyName.setText("");
                                etLobbyPass.setText("");
                                Toast.makeText(getApplicationContext(), "Lobby name/password is invalid!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            etLobbyName.setText("");
                            etLobbyPass.setText("");
                            Toast.makeText(getApplicationContext(), "Something goes wrong. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnCreateLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateLobbyActivity.class);
                startActivity(i);
            }
        });

        btnSearchLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchLobbyActivity.class);
                startActivity(i);
            }
        });
    }
}