package com.ci.juniorstrainingtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView lvList;
    private GridView gvList;
    private RecyclerView rlvList;
    private ArrayList<ListModel> arrItems = new ArrayList<ListModel>();
    private CustomAdapter adapter;
    private GridAdapter gAdapter;
    private RecyclerViewAdapter recyclerAdapter;
    private EditText etUpdate;
    private Button btnUpdate;
    private int intBundleIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        addItems();
        init();
    }

    private void addItems() {
        arrItems.add(new ListModel("SSC Exams", "May 23, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("Group Exams", "June 24, 2015", "All the Best"));
        arrItems.add(new ListModel("UPSC Exams", "July 25, 2015", "Good Luck"));
        arrItems.add(new ListModel("State Exams", "August 26, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("Central Exams", "September 26, 2015", "All the Best"));
        arrItems.add(new ListModel("Karnataka Exams", "October 27, 2015", "Good Luck"));
        arrItems.add(new ListModel("Tamilnadu Exams", "November 28, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("UP Exams", "December 29, 2015", "All the Best"));
        arrItems.add(new ListModel("MP Exams", "January 30, 2015", "Good Luck"));
        arrItems.add(new ListModel("Oddisa Exams", "February 1, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("SSC Exams", "May 23, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("Group Exams", "June 24, 2015", "All the Best"));
        arrItems.add(new ListModel("UPSC Exams", "July 25, 2015", "Good Luck"));
        arrItems.add(new ListModel("State Exams", "August 26, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("Central Exams", "September 26, 2015", "All the Best"));
        arrItems.add(new ListModel("Karnataka Exams", "October 27, 2015", "Good Luck"));
        arrItems.add(new ListModel("Tamilnadu Exams", "November 28, 2015", "Best Of Luck"));
        arrItems.add(new ListModel("UP Exams", "December 29, 2015", "All the Best"));
        arrItems.add(new ListModel("MP Exams", "January 30, 2015", "Good Luck"));
        arrItems.add(new ListModel("Oddisa Exams", "February 1, 2015", "Best Of Luck"));
        adapter = new CustomAdapter(this, arrItems);
        gAdapter = new GridAdapter(this, arrItems);
        recyclerAdapter = new RecyclerViewAdapter(arrItems);
    }

    private void init() {
        lvList = findViewById(R.id.lvList);
        gvList = findViewById(R.id.gvList);
        rlvList = findViewById(R.id.rlvList);
        etUpdate = findViewById(R.id.etUpdate);
        btnUpdate = findViewById(R.id.btnUpdate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rlvList.setLayoutManager(mLayoutManager);
        rlvList.setItemAnimator(new DefaultItemAnimator());
        Bundle bundle = getIntent().getExtras();
        intBundleIndex = bundle.getInt("view_visibility_index");
        if (bundle!= null){
            switch (intBundleIndex){

                case 0:
                    lvList.setVisibility(View.VISIBLE);
                    gvList.setVisibility(View.GONE);
                    rlvList.setVisibility(View.GONE);
                    lvList.setAdapter(adapter);
                    break;

                case 1:
                    lvList.setVisibility(View.GONE);
                    gvList.setVisibility(View.VISIBLE);
                    rlvList.setVisibility(View.GONE);
                    gvList.setAdapter(gAdapter);
                    break;

                case 2:
                    lvList.setVisibility(View.GONE);
                    gvList.setVisibility(View.GONE);
                    rlvList.setVisibility(View.VISIBLE);
                    rlvList.setAdapter(recyclerAdapter);
                    break;

            }

        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strText = etUpdate.getText().toString();
                if (!TextUtils.isEmpty(strText)){
                    if (strText.equalsIgnoreCase("Update")){
                        if (intBundleIndex == 2) {
                            for (int index =0; index<=4; index++){
                                ListModel item = arrItems.get(index);
                                item.strName = "Updated item "+ index;
                            }
                            recyclerAdapter.notifyItemRangeChanged(0, 5);
                            rlvList.scrollToPosition(0);
                        }
                    }else if (strText.equalsIgnoreCase("New")){
                        if (intBundleIndex == 2) {
                            int startIndex = arrItems.size();
                            ArrayList<ListModel> arrNewItems = new ArrayList<ListModel>();
                            for (int index = 0; index<=4; index++){
                                String strName = "Newly item at "+ (startIndex+index);
                                arrNewItems.add(new ListModel(strName, "Sep 13 2019", "Congratulations in Advance"));
                            }
                            recyclerAdapter.addItems(arrNewItems);
                            rlvList.scrollToPosition(startIndex);
                        }
                    }else{
                        ListModel item = arrItems.get(1);
                        item.strName = strText;
                        switch (intBundleIndex){
                            case 0:
                                adapter.updateList(arrItems);
                                break;
                            case 1:
                                gAdapter.updateList(arrItems);
                                break;
                            case 2:
                                recyclerAdapter.notifyItemChanged(1);
                                break;
                        }
                    }

                }
            }
        });
    }
}
