package com.example.findgroupeat.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;

import android.widget.TextView;

import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.findgroupeat.LobbyActivity;
import com.example.findgroupeat.R;
import com.example.findgroupeat.models.Lobby;


import org.parceler.Parcels;

import java.util.ArrayList;

import java.util.List;

public class LobbyAdapter extends RecyclerView.Adapter<LobbyAdapter.LobbyViewHolder> implements Filterable{

    Context context;
    List<Lobby> lobbyList;
    List<Lobby> lobbyListFull;

    public LobbyAdapter(Context context, List<Lobby> lobbyList) {
        this.context = context;
        this.lobbyList = lobbyList;
        this.lobbyListFull = new ArrayList<>(lobbyList);
    }

    @NonNull
    @Override
    public LobbyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lobby, parent, false);
        return new LobbyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LobbyViewHolder holder, int position) {
        Lobby lobby = lobbyList.get(position);
        LobbyViewHolder lobbyViewHolder = holder;
        lobbyViewHolder.bind(lobby);
    }

    @Override
    public int getItemCount() {
        return lobbyList.size();
    }

    public void addAll(List<Lobby> list) {
        lobbyList.addAll(list);
        lobbyListFull.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Lobby> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(lobbyListFull);
            }
            else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Lobby lobby: lobbyListFull) {
                    if (lobby.getName().toLowerCase().contains(pattern)) {
                        filteredList.add(lobby);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            lobbyList.clear();
            lobbyList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class LobbyViewHolder extends RecyclerView.ViewHolder {

        TextView tvLobbyName;

        public LobbyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLobbyName = itemView.findViewById(R.id.tvLobbyName);
        }

        public void bind(Lobby lobby) {
            tvLobbyName.setText(lobby.getName());
            tvLobbyName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(lobby.getName());
                    EditText input = new EditText(context);
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    builder.setView(input);

                    // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String pass = input.getText().toString();
                            if (pass.equals(lobby.getPassword())) {
                                Intent i = new Intent(context, LobbyActivity.class);
                                i.putExtra("lobby", Parcels.wrap(lobby));
                                context.startActivity(i);
                            }
                            else {
                                input.setText("");
                                Toast.makeText(context, "Invalid password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });
        }
    }
}
