package com.example.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;

import java.util.ArrayList;

/**
 * Created by ubun17 on 8/29/16.
 */
public class AdapterRecyItem extends RecyclerView.Adapter<ViewHolderItemList> {
    ArrayList<Item> mItems;

    public AdapterRecyItem(ArrayList<Item> args) {
        mItems = args;
    }

    @Override
    public ViewHolderItemList onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_item_list, parent, false);
        ViewHolderItemList viewHolderItemList = new ViewHolderItemList(parentView);

        return viewHolderItemList;
    }

    @Override
    public void onBindViewHolder(ViewHolderItemList holder, int position) {
        holder.tvItemName.setText((CharSequence) mItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
