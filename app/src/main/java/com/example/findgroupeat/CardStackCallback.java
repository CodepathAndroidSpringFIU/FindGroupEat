package com.example.findgroupeat;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.findgroupeat.models.Item;

import java.util.List;

public class CardStackCallback extends DiffUtil.Callback {
    private List<Item> old, newList;

    public CardStackCallback(List<Item> old, List<Item> newList) {
        this.old = old;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getVenue().getId() == newList.get(newItemPosition).getVenue().getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
