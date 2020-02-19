package com.ci.juniorstrainingtasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AutoCompleteExample extends AppCompatActivity {
    private AutoCompleteTextView atvActors,atvActorsImages;
    private ArrayList<String> arrActorsList = new ArrayList<String>();
    private ArrayList<HeroModel> arrHeroImagesList = new ArrayList<HeroModel>();
    private RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_example);
        init();
    }

    private void init() {
        addList();
        addHeroImagesList();
        atvActors = findViewById(R.id.atvActors);
        atvActors.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrActorsList);
        atvActors.setAdapter(adapter);
        atvActorsImages = findViewById(R.id.atvActorsImages);
        atvActorsImages.setThreshold(1);
        atvActorsImages.setAdapter(new ActorsImagesAdapter(this, arrHeroImagesList));
        atvActors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object item = adapterView.getItemAtPosition(position);
                String value = atvActors.getText().toString();
                Toast.makeText(AutoCompleteExample.this, "Selected Item is "+ item+ " / " +value , Toast.LENGTH_SHORT).show();
            }
        });
        rbRating = findViewById(R.id.rbRating);
//        showToast();
//        showProgressDialog();
        handleRatinBar();
    }

    private void handleRatinBar() {
        rbRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(AutoCompleteExample.this, "Rating provide is "+v, Toast.LENGTH_SHORT).show();
                Log.e("Rating", ""+rbRating.getRating());
            }
        });
    }

    private void showProgressDialog() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.setMessage("Please wait ....");
        dialog.getWindow().setGravity(Gravity.TOP);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ProgressDialog dialog2 = ProgressDialog.show(AutoCompleteExample.this, "Loading", "This another dialog", false, true);
                if (!dialog2.isShowing()){
                    dialog2.show();
                }
                dialog2.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        showToast();
                    }
                });
            }
        });

    }

    private void showToast() {
        Toast toast = Toast.makeText(AutoCompleteExample.this, "This is Example one", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,50);
        View view = LayoutInflater.from(this).inflate(R.layout.custom_toas,null);
        toast.setView(view);
        toast.show();
    }

    private void addList() {
        arrActorsList.add("Pawan kalyan");
        arrActorsList.add("Prabhas");
        arrActorsList.add("Mahesh babu");
        arrActorsList.add("Nagarjuna");
        arrActorsList.add("Venkatesh");
        arrActorsList.add("Amir Khan");
        arrActorsList.add("Allu Arjun");
        arrActorsList.add("Arvind");
        arrActorsList.add("Arya");
        arrActorsList.add("Arjun");
        arrActorsList.add("Adivi Sheshu");
        arrActorsList.add("Adarsh");
        arrActorsList.add("Charan");
        arrActorsList.add("Vijay");
        arrActorsList.add("Vikram");
        arrActorsList.add("Rana");
        arrActorsList.add("Tarun");
        arrActorsList.add("Dharshan");
    }

    private void addHeroImagesList() {
        arrHeroImagesList.add(new HeroModel("Pawan kalyan", R.drawable.pawan));
        arrHeroImagesList.add(new HeroModel("Prabhas", R.drawable.prabhas));
        arrHeroImagesList.add(new HeroModel("Mahesh babu", R.drawable.mahesh));
        arrHeroImagesList.add(new HeroModel("Nagarjuna", R.drawable.nag));
        arrHeroImagesList.add(new HeroModel("Venkatesh", R.drawable.venky));
        arrHeroImagesList.add(new HeroModel("Amir Khan", R.drawable.amir));
        arrHeroImagesList.add(new HeroModel("Allu Arjun", R.drawable.alluarjun));
        arrHeroImagesList.add(new HeroModel("Arya", R.drawable.arya));
        arrHeroImagesList.add(new HeroModel("Arjun", R.drawable.arjun));
        arrHeroImagesList.add(new HeroModel("Adivi Sheshu", R.drawable.sheshu));
        arrHeroImagesList.add(new HeroModel("Adarsh", R.drawable.adarsh));
        arrHeroImagesList.add(new HeroModel("Ram Charan", R.drawable.ram));
        arrHeroImagesList.add(new HeroModel("Vijay", R.drawable.vijay));
        arrHeroImagesList.add(new HeroModel("Vikram",R.drawable.vikram));
        arrHeroImagesList.add(new HeroModel("Rana", R.drawable.mahesh));
    }
}
