package com.example.findgroupeat.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.findgroupeat.R;
import com.example.findgroupeat.ui.LoginActivity;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    Button btnLogout;
    TextView tvName;
    TextView tvEmail;
    ImageView ivAvatar;
    ListView lvHistory;
    List<String> history;
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.btnLogout);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmailProfile);
        ivAvatar = view.findViewById(R.id.ivAvatar);
        lvHistory = view.findViewById(R.id.lvHistory);

        ParseUser user = ParseUser.getCurrentUser();
        tvName.setText(user.getUsername());
        tvEmail.setText(user.getEmail());

        ParseFile profileImage = user.getParseFile("avatar");
        if (profileImage != null)
            Glide.with(getContext())
                    .load(profileImage.getUrl())
                    .transform(new CircleCrop())
                    .into(ivAvatar);
        else
            Glide.with(getContext())
                    .load(R.drawable.ic_baseline_person_24_black)
                    .transform(new CircleCrop())
                    .into(ivAvatar);

        List<String> history = new ArrayList<>();
        adapter = new ArrayAdapter(getContext(), R.layout.history, R.id.tvHistoryName, history);
        lvHistory.setAdapter(adapter);
        queryHistory(user);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                Toast.makeText(getContext(), "Successfully log out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void queryHistory(ParseUser user) {
        List<String> temp = user.getList("restaurants");
        if (temp != null) {
            history.addAll(temp);
            adapter.notifyDataSetChanged();
        }
    }
}