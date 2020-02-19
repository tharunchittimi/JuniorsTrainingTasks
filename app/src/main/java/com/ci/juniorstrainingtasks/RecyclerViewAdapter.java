package com.ci.juniorstrainingtasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private ArrayList<ListModel> arrList = new ArrayList<ListModel>();

    RecyclerViewAdapter(ArrayList<ListModel> list) {
        arrList.addAll(list);
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View listItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        ListModel item = arrList.get(position);
        holder.tvName.setText(item.strName);
        holder.tvDate.setText(item.strDate);
        holder.tvMessage.setText(item.strMessage);
    }

    public void addItems(ArrayList<ListModel> arrNewItems){
        int startIndex = arrList.size();
        arrList.addAll(arrNewItems);
        notifyItemRangeInserted(startIndex, 5);
    }

}
