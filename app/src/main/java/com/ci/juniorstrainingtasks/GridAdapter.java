package com.ci.juniorstrainingtasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<ListModel> arrList = new ArrayList<ListModel>();
    private LayoutInflater inflater;

    GridAdapter(Context context, ArrayList<ListModel> list) {
        arrList.addAll(list);
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return arrList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        GridViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, viewGroup, false);
            holder = new GridViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GridViewHolder) convertView.getTag();
        }
        ListModel item = arrList.get(position);
        holder.ivIamge.setImageResource(R.drawable.ic_launcher_foreground);
        holder.tvName.setText(item.strName);
        return convertView;
    }

    private class GridViewHolder {

        private ImageView ivIamge;
        private TextView tvName;

        GridViewHolder(View item) {
            ivIamge = item.findViewById(R.id.ivIamge);
            tvName = item.findViewById(R.id.tvName);
        }
    }

    public void updateList(ArrayList<ListModel> updatedList){
        arrList.clear();
        arrList.addAll(updatedList);
        notifyDataSetChanged();
    }

}
