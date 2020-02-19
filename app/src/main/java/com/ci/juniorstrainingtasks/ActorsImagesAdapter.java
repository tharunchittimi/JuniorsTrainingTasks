package com.ci.juniorstrainingtasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ActorsImagesAdapter extends ArrayAdapter<HeroModel>  {

    ArrayList<HeroModel> HeroModels, tempHeroModel, suggestions;

    public ActorsImagesAdapter(Context context, ArrayList<HeroModel> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.HeroModels = objects;
        this.tempHeroModel = new ArrayList<HeroModel>(objects);
        this.suggestions = new ArrayList<HeroModel>(objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HeroModel HeroModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hero_model_row, parent, false);
        }
        TextView tvHeroName = (TextView) convertView.findViewById(R.id.tvHeroName);
        ImageView ivHeroImage = (ImageView) convertView.findViewById(R.id.ivHeroImage);
        tvHeroName.setText(HeroModel.strName);
        Glide.with(getContext()).load(HeroModel.imageId).into(ivHeroImage);
        if (position % 2 == 0)
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
        else
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimary));

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return customFilter;
    }

    Filter customFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            HeroModel HeroModel = (HeroModel) resultValue;
            return HeroModel.strName;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (HeroModel people : tempHeroModel) {
                    if (people.strName.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<HeroModel> c = (ArrayList<HeroModel>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (HeroModel cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
