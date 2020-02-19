package com.ci.juniorstrainingtasks;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvDate, tvMessage;

    public CustomViewHolder(View item) {
        super(item);
        tvName = item.findViewById(R.id.tvName);
        tvDate = item.findViewById(R.id.tvDate);
        tvMessage = item.findViewById(R.id.tvMessage);
    }
}
