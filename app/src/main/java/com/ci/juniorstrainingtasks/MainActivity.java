package com.ci.juniorstrainingtasks;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnList,btnGrid,btnRecycler;
    private ScrollView slvExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnList = findViewById(R.id.btnList);
        btnGrid = findViewById(R.id.btnGrid);
        btnRecycler = findViewById(R.id.btnRecycler);
        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnRecycler.setOnClickListener(this);
        findViewById(R.id.btnTextView).setOnClickListener(this);
        findViewById(R.id.btnEditText).setOnClickListener(this);
        findViewById(R.id.btnImageView).setOnClickListener(this);
        findViewById(R.id.btnAcTextView).setOnClickListener(this);
        findViewById(R.id.btnWebView).setOnClickListener(this);
        findViewById(R.id.btnCalendar).setOnClickListener(this);
        findViewById(R.id.btnMultiThreading).setOnClickListener(this);
        slvExample = findViewById(R.id.slvExample);
        slvExample.scrollTo(0, slvExample.getBottom());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnList:
                navigateToListScreen(0);
                break;

            case R.id.btnGrid:
                navigateToListScreen(1);
                break;

            case R.id.btnRecycler:
                navigateToListScreen(2);
                break;

            case R.id.btnTextView:
                Intent intent = new Intent(MainActivity.this, TextExample.class);
                startActivity(intent);
                break;

            case R.id.btnEditText:
                startActivity(new Intent(MainActivity.this, EditTextExample.class));
                break;

            case R.id.btnImageView:
                startActivity(new Intent(MainActivity.this, ImageExample.class));
                break;

            case R.id.btnAcTextView:
                startActivity(new Intent(MainActivity.this, AutoCompleteExample.class));
                break;

            case R.id.btnWebView:
                startActivity(new Intent(MainActivity.this, WebViewExample.class));
                break;

            case R.id.btnCalendar:
                startActivity(new Intent(MainActivity.this, CalendarExample.class));
                break;

            case R.id.btnMultiThreading:
                startActivity(new Intent(MainActivity.this, MultiThreadingExample.class));
                break;

        }

    }

    private void navigateToListScreen(int index){
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("view_visibility_index", index);
        startActivity(intent);
    }
}
