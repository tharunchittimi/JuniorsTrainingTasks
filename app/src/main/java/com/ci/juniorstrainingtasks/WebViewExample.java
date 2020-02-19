package com.ci.juniorstrainingtasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class WebViewExample extends AppCompatActivity implements View.OnClickListener {

    private SeekBar sbRange;
    private WebView wbSample;
    private ProgressBar pbWebLoading;
    private TextView tvHtmlViewer;
    private String strHtmlContent = "<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'><font face='Source Sans Pro, Helvetica Neue, roboto, Arial' style='font-family: Source Sans Pro, Helvetica,Arial;font-size: 16px;'><div style='color:#555'>Please check the list of Android devices which support this feature:<ul><li>Google - Pixel &amp; XL, Pixel 2 &amp; XL, Pixel 3 &amp; XL, Pixel  3a &amp; XL</li><li>Samsung - Galaxy S7, S8, S9, S10, Tab S3, Tab S4 etc</li><li>LG  - G6, G7, G8, Q6, Q8, V30, V30+, V35, V40, V50 etc</li><li>Huawei - Honor 8X, Honor 10, Honor View 10 Lite, Honor V20, Mate 20, Nova 3, Nova 4 etc</li><li>Motorola  - Moto G5S Plus, G6, G7, X4, One, Z2, Z3 etc</li></ul>Requires Android 8.0 or later for all the Android devices.<br>Please <a href='https://developers.google.com/ar/discover/supported-devices'>click here</a> to see all the supported devices.</div></font>";
    private String strUrl = "https://developer.android.com/guide/platform";

    private TextView tvFontOne, tvFontTwo;
    private Dialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_example);
        init();
    }

    private void init() {
        sbRange = findViewById(R.id.sbRange);
        seekBarListner();
        wbSample = findViewById(R.id.wbSample);
        wbSample.getSettings().setJavaScriptEnabled(true);
        wbSample.getSettings().setUseWideViewPort(true);
        wbSample.getSettings().setSupportZoom(true);
        wbSample.getSettings().setBuiltInZoomControls(true);
        wbSample.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        pbWebLoading = findViewById(R.id.pbWebLoading);
        tvHtmlViewer = findViewById(R.id.tvHtmlViewer);
        setWebViewClient();
        findViewById(R.id.btnLoadUrl).setOnClickListener(this);
        findViewById(R.id.btnHtmlWebview).setOnClickListener(this);
        findViewById(R.id.btnSnackBar).setOnClickListener(this);
        findViewById(R.id.btnActionSnackbar).setOnClickListener(this);
        findViewById(R.id.btnCustomSnackbar).setOnClickListener(this);
        tvFontOne = findViewById(R.id.tvFontOne);
        tvFontTwo = findViewById(R.id.tvFontTwo);
        findViewById(R.id.btnAlertDialog).setOnClickListener(this);
        findViewById(R.id.btnCustomDialog).setOnClickListener(this);
        updateFonts();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnLoadUrl:
                wbSample.loadUrl(strUrl);
                tvHtmlViewer.setText("");
                break;

            case R.id.btnHtmlWebview:
                wbSample.loadData(strHtmlContent, "text/html", "UTF-8");
                tvHtmlViewer.setText(Html.fromHtml(strHtmlContent));
                break;

            case R.id.btnSnackBar:
                showSnackBar();
                break;

            case R.id.btnActionSnackbar:
                showActionSnackbar();
                break;

            case R.id.btnCustomSnackbar:
                showCustomSnackbar();
                break;

            case R.id.btnAlertDialog:
                showAlertDialog();
                break;

            case R.id.btnCustomDialog:
                showCustomDialog();
                break;
        }
    }

    private void updateFonts() {
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "SHC-App-icons.ttf");
        tvFontOne.setTypeface(custom_font);
        tvFontTwo.setTypeface(custom_font);
        tvFontOne.setText(getResources().getText(R.string.icon_arcore));
        tvFontTwo.setText(getResources().getText(R.string.icon_allow_location));
    }

    private void setWebViewClient() {
        wbSample.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pbWebLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbWebLoading.setVisibility(View.GONE);
            }
        });
    }

    private void seekBarListner() {
        sbRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                showToast("seekbar progress: "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.llvRoot), "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void showActionSnackbar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.llvRoot), "SnackBar with Ok button", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("Snack bar ok Clicked");
                    }
                });

        snackbar.show();
    }

    private void showCustomSnackbar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.llvRoot), "SnackBar with custom Color", Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("SnackBar Ok Clicked");
                    }
                });
        snackbar.setActionTextColor(Color.BLUE);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "custom.ttf");
//        textView.setTypeface(custom_font);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }

    private void showToast(String strMessage) {
        Toast.makeText(getApplicationContext(),strMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wbSample.canGoBack()) {
            wbSample.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showCustomDialog() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
        customDialog = new Dialog(this);
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.requestWindowFeature(customDialog.getWindow().FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.custom_dialog);
//        customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        int width = ViewGroup.LayoutParams.WRAP_CONTENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        customDialog.getWindow().setLayout(width, height);
        customDialog.getWindow().setGravity(Gravity.CENTER);
//            customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        customDialog.setCancelable(true);
        ImageView iv_dialogClose = customDialog.findViewById(R.id.iv_dialogClose);
        iv_dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
        Glide.with(this)
                .load(R.drawable.pawan)
                .into((ImageView) customDialog.findViewById(R.id.iv_fullImage));
        customDialog.show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?")
                .setMessage("Do you want to proceed ?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showToast("Ok clicked");
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        showToast("Cancel clicked");
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}
