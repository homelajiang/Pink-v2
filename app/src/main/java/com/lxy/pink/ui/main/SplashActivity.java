package com.lxy.pink.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;

/**
 * Created by homelajiang on 2016/10/8 0008.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }

    public void sayHello(View v) {
        TextView textView = (TextView) findViewById(R.id.textView);
        EditText editText = (EditText) findViewById(R.id.editText);
        textView.setText("Hello, " + editText.getText().toString() + "!");
    }
}
