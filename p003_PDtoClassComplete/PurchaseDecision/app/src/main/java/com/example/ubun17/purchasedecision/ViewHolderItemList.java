package com.example.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ubun17 on 8/29/16.
 */
public class ViewHolderItemList extends RecyclerView.ViewHolder {
    public TextView tvItemName;
    public ViewHolderItemList(View itemView) {
        super(itemView);

        tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
    }
}
