package com.example.findgroupeat.adapters;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.R;
import com.example.findgroupeat.models.Item;


import java.util.ArrayList;





public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>  {


        private List<Item> restaurantList;
        private static final String TAG = "restaurantAdapter";
        ImageView restaurantPhoto;
        TextView restaurantName;
        TextView restaurantAddress;
        private Context mContext;

        public RestaurantsAdapter(List<Item> restaurantList, Context mContext) {
            this.restaurantList = restaurantList;
            this.mContext = mContext;
        }




        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.restaurant_spot, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setData(restaurantList.get(position));
            Item restaurant = getRestaurants().get(position);
            List<String> photoUrl = restaurant.getPhotoUrl();


            if (!(photoUrl == null)) {
                int numOfPhotos = photoUrl.size();
                Log.v(TAG, "num of photos is: " + numOfPhotos);
                System.out.println("numofPhotos in adapter list is " + numOfPhotos);
                for (int i = 0; i < numOfPhotos; i++) {
                    Glide.with(holder.itemView.getContext())
                            .load(photoUrl.get(i))
                            .into(restaurantPhoto);
                }

            }



        }

        @Override
        public int getItemCount() {
            return restaurantList == null ? 0 : restaurantList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                restaurantPhoto = itemView.findViewById(R.id.item_image);
                restaurantName = itemView.findViewById(R.id.item_name);
                restaurantAddress = itemView.findViewById(R.id.item_city);

            }

            public void setData(Item data) {
                ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
                restaurantName.setText(data.getVenue().getName());
                restaurantAddress.setText(data.getVenue().getCategories().get(0).getName());
                Log.v(TAG, "is this null? " + data.getPhotoUrl());
                if (!(data.getPhotoUrl() == null)) {
                    List<String> photoUrls = data.getPhotoUrl();
                    int size = photoUrls.size();
                    Log.v(TAG, "photoUrls size in set data is " + size);


                    Glide.with(mContext)
                            .load(data.getPhotoUrl())
                            .into(restaurantPhoto);
                }












            }
        }
        public List<Item> getRestaurants() {
            return restaurantList;
        }

        public void setRestaurants(List<Item> restaurantList) {
            this.restaurantList = restaurantList;

        }
}
