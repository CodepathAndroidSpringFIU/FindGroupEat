package com.example.findgroupeat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findgroupeat.models.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.List;

public class CreateLobbyActivity extends AppCompatActivity {

    EditText etName;
    EditText etPass;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lobby);

        etName = findViewById(R.id.etName);
        etPass = findViewById(R.id.etPass);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pass = etPass.getText().toString();
                Lobby lobby = new Lobby();
                ParseQuery<Lobby> query = ParseQuery.getQuery(Lobby.class);
                query.whereEqualTo("name", name);
                query.findInBackground(new FindCallback<Lobby>() {
                    @Override
                    public void done(List<Lobby> objects, ParseException e) {
                        if (e == null && !objects.isEmpty()) {
                            etName.setText("");
                            etPass.setText("");
                            Toast.makeText(getApplicationContext(), "This lobby already exists!", Toast.LENGTH_SHORT).show();
                        } else if (e == null) {
                            lobby.setName(name);
                            lobby.setPasword(pass);
                            lobby.addUser(ParseUser.getCurrentUser());
                            lobby.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException exception) {
                                    if (exception != null) {
                                        etName.setText("");
                                        etPass.setText("");
                                        Toast.makeText(getApplicationContext(), "Failed to create lobby. Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent i = new Intent(CreateLobbyActivity.this, LobbyActivity.class);
                                    i.putExtra("lobby", Parcels.wrap(lobby));
                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Create lobby successfully!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            etName.setText("");
                            etPass.setText("");
                            Toast.makeText(getApplicationContext(), "Something goes wrong. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}