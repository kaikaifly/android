package com.skipdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * author : dukai
 * date  : 2018/11/7
 * describe:
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    public void Component1(View view) {
        Intent intent = new Intent(getApplicationContext(),MyReactActivity.class);
        //传值
        intent.putExtra("componentName","First");
        startActivity(intent);

    }

    public void Component2(View view) {
        Intent intent = new Intent(getApplicationContext(),MyReactActivity.class);
        //传值
        intent.putExtra("componentName","Second");
        startActivity(intent);
    }
}
