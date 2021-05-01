package com.example.findgroupeat.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.findgroupeat.LobbyActivity;
import com.example.findgroupeat.R;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.List;

public class CreateLobbyFragment extends Fragment {

    EditText etName;
    EditText etPass;
    Button btnCreate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_create_lobby, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.etName);
        etPass = view.findViewById(R.id.etPass);
        btnCreate = view.findViewById(R.id.btnCreate);

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
                            Toast.makeText(getContext(), "This lobby already exists!", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(getContext(), "Failed to create lobby. Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent i = new Intent(getContext(), LobbyActivity.class);
                                    i.putExtra("lobby", Parcels.wrap(lobby));
                                    startActivity(i);
                                    Toast.makeText(getContext(), "Created lobby successfully!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            etName.setText("");
                            etPass.setText("");
                            Toast.makeText(getContext(), "Something went wrong. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}