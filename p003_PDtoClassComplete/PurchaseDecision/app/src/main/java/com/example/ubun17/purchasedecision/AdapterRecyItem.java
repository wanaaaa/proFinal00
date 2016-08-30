package com.example.ubun17.purchasedecision;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.Item;
import com.example.ubun17.purchasedecision.ResponseObject.WalMartObject.SingleWarSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ubun17 on 8/29/16.
 */
public class AdapterRecyItem extends RecyclerView.Adapter<ViewHolderItemList> {
    ArrayList<Item> mItems;
    private Context mContext;

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
        String thumbURL, thumbURLtwo;

        SingleWarSearch warSearch = SingleWarSearch.getInstance();
        thumbURL = warSearch.getItemList().get(position).getThumbnailImage();
        thumbURLtwo = "https://i5.walmartimages.com/asr/8e0c3fb1-673b-4b29-9b8a-46cae3e0d917_1.c5d745d0e28796c3f8b53893ea6e064c.jpeg?odnHeight=100&odnWidth=100&odnBg=FFFFFF";


        holder.tvItemName.setText((CharSequence) mItems.get(position).getName());

        Picasso.with(holder.imageThumb.getContext()).load(thumbURL).resize(100, 100)
                .into(holder.imageThumb);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
