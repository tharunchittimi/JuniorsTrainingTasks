package com.ci.juniorstrainingtasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ListModel> arrList = new ArrayList<ListModel>();
    private LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<ListModel> list) {
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        CustomViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, viewGroup, false);
            holder = new CustomViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }
        ListModel item = arrList.get(position);
        holder.tvName.setText(item.strName);
        holder.tvDate.setText(item.strDate);
        holder.tvMessage.setText(item.strMessage);
        holder.tvMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 2){

                }
            }
        });
        return convertView;
    }

    public void updateList(ArrayList<ListModel> updatedList){
        arrList.clear();
        arrList.addAll(updatedList);
        notifyDataSetChanged();
    }
}
