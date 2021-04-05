package com.example.findgroupeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class LobbyActivity extends AppCompatActivity {

    TextView tvLobbyName;
    ListView lwUsers;
    Button btnSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        tvLobbyName = findViewById(R.id.tvLobbyName);
        lwUsers = findViewById(R.id.lvUsers);
        btnSwipe = findViewById(R.id.btnSwipe);
    }
}