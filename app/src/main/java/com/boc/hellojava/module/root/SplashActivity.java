package com.boc.hellojava.module.root;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.boc.hellojava.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        // sleep(3000);
                        Intent intent = MainActivity.newInstance(SplashActivity.this);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                });
            }
        } ).start();
    }
}