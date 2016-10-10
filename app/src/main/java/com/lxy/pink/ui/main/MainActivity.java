package com.lxy.pink.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lxy.pink.R;
import com.lxy.pink.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public static void Launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
}
