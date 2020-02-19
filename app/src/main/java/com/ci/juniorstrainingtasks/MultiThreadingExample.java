package com.ci.juniorstrainingtasks;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MultiThreadingExample extends AppCompatActivity implements View.OnClickListener {

    private Handler handler;
    private Runnable runnable;
    private TextView tvThreadInfo;
    private Button btnStopHandler;
    private ProgressDialog pbLoading;
    private TextView tvAsynInfo;
    private AsyncTask exampleTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threading_example);
        init();
    }

    private void init() {
        pbLoading = new ProgressDialog(this);
        pbLoading.setTitle("Asyntask running");
        tvThreadInfo = findViewById(R.id.tvThreadInfo);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                String strDate = getDateString();
                tvThreadInfo.setText(strDate);
                handler.postDelayed(this, 3000);
            }
        };
        findViewById(R.id.btnHandlerPost).setOnClickListener(this);
        findViewById(R.id.btnHandlerDelay).setOnClickListener(this);
        btnStopHandler = findViewById(R.id.btnStopHandler);
        btnStopHandler.setOnClickListener(this);
        findViewById(R.id.btnAsyntask).setOnClickListener(this);
        tvAsynInfo = findViewById(R.id.tvAsynInfo);
    }

    private String getDateString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHandlerPost:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String strDate = getDateString();
                        tvThreadInfo.setText(strDate);
                    }
                });
                break;
            case R.id.btnHandlerDelay:
                btnStopHandler.setVisibility(View.VISIBLE);
                tvThreadInfo.setText(getDateString());
                handler.postDelayed(runnable, 3000);
                break;

            case R.id.btnStopHandler:
                handler.removeCallbacks(runnable);
                break;

            case R.id.btnAsyntask:
                exampleTask = new AsynExample().execute();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (btnStopHandler.getVisibility() == View.VISIBLE){
            handler.removeCallbacks(runnable);
        }
        if (exampleTask != null && exampleTask.getStatus() != AsyncTask.Status.FINISHED) {
            pbLoading.dismiss();
            exampleTask.cancel(true);
        }
        super.onBackPressed();
    }

    class AsynExample extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            handleBackground();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pbLoading.dismiss();
        }
    }

    private void handleBackground() {
        for (int i =0; i<4; i++){
            updateText(i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateText(final int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvAsynInfo.setText("Iteration "+ index + " done at "+getDateString());
            }
        });
    }
}
