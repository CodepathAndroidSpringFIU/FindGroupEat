package com.example.findgroupeat.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.findgroupeat.R;
import com.parse.ParseFile;
import com.parse.ParseUser;


import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    Context context;
    List<ParseUser> userList;

    public UserAdapter(Context context, List<ParseUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        ParseUser user = userList.get(position);
        UserHolder userHolder = holder;
        userHolder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void clear() {
        userList.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<ParseUser> list) {
        userList.addAll(list);
        notifyDataSetChanged();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        ImageView ivAvatar;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivAvatar = itemView.findViewById(R.id.ivProfileImage);
        }

        public void bind(ParseUser user) {
            tvUsername.setText(user.getUsername());
            ParseFile profileImage = user.getParseFile("avatar");
            if (profileImage != null)
                Glide.with(context)
                        .load(profileImage.getUrl())
                        .transform(new CircleCrop())
                        .into(ivAvatar);
            else
                Glide.with(context)
                        .load(R.drawable.ic_baseline_person_24_black)
                        .transform(new CircleCrop())
                        .into(ivAvatar);
        }
    }
}
