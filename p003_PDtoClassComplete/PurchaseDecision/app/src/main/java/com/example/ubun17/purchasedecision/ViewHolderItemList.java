package com.example.ubun17.purchasedecision;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ubun17 on 8/29/16.
 */
public class ViewHolderItemList extends RecyclerView.ViewHolder {
    public TextView tvItemName;
    public ImageView imageThumb;

    public ViewHolderItemList(View itemView) {
        super(itemView);

        imageThumb = (ImageView) itemView.findViewById(R.id.imaThumb);
        tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
    }
}
