package com.ci.juniorstrainingtasks;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TextExample extends AppCompatActivity {

    private String strMessage = "Android 10 (API level 29) introduces a number of features and behavior changes to better protect users' privacy. These changes extend the transparency and control that users have over their data and the capabilities they give to apps. These features might mean that specific behaviors or data that your app is depending on may behave differently compared to older versions of the platform. The impacts on your app should be minimal if your app is following current best practices for handling user data.";
    private TextView tvSingleLine,tvParagraph,tvLines,tvTypeface,tvLinksClickable,tvSpannable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_example);
        init();
    }

    private void init() {
        tvSingleLine = findViewById(R.id.tvSingleLine);
        tvSingleLine.setText(strMessage);
        tvParagraph = findViewById(R.id.tvParagraph);
        tvParagraph.setText(strMessage);
        tvLines = findViewById(R.id.tvLines);
        tvLines.setText("This is single line text with line count as 3");
        tvLinksClickable = findViewById(R.id.tvLinksClickable);
        tvLinksClickable.setText("Welcome to developer site https://developers.android.com");
        tvSpannable = findViewById(R.id.tvSpannable);
        applyclickableSpans();
        tvTypeface = findViewById(R.id.tvTypeface);
        applyTypeface();
        addListeners();
    }

    private void addListeners() {
        tvSingleLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TextExample.this, "Text view clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void applyclickableSpans() {
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.e("span", "Clicked");
                Toast.makeText(TextExample.this, "Span Clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                int color = ContextCompat.getColor(TextExample.this, R.color.colorPrimary);
                ds.setColor(color);
                ds.setUnderlineText(true);
            }
        };
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(strMessage);
        ssBuilder.setSpan(clickableSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvSpannable.setMovementMethod(LinkMovementMethod.getInstance());
        tvSpannable.setText(ssBuilder);
    }

    private void applyTypeface() {
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "custom.ttf");
        tvTypeface.setTypeface(custom_font);
        tvTypeface.setText(strMessage);
    }

}
