package com.ci.juniorstrainingtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditTextExample extends AppCompatActivity {

    private EditText etFocus,etLineCounter,etMultiLine;
    private ImageView ivClose;
    private TextView tvCountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_example);
        init();
    }

    private void init() {
        etFocus = findViewById(R.id.etFocus);
        ivClose = findViewById(R.id.ivClose);
        addFocusListener();
        etLineCounter = findViewById(R.id.etLineCounter);
        tvCountInfo = findViewById(R.id.tvCountInfo);
        addTextWatcher();
        etMultiLine = findViewById(R.id.etMultiLine);
        addEditorListener();
    }

    private void addEditorListener() {
        etMultiLine.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    Toast.makeText(EditTextExample.this, "Key Board Go Clicked", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void addTextWatcher() {
        etLineCounter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length()>0){
                    tvCountInfo.setVisibility(View.VISIBLE);
                    tvCountInfo.setText(""+charSequence.length()+"/"+"10");
                }else {
                    tvCountInfo.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void addFocusListener() {
        etFocus.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                String str = b?"EditText focused":"EditText lost focus";
//                Toast.makeText(EditTextExample.this, str, Toast.LENGTH_SHORT).show();
                int status = b ? View.VISIBLE : View.GONE;
                ivClose.setVisibility(status);
            }
        });
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etFocus.setText("");
            }
        });
    }
}
