package com.example.silencew.test;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by silence.w on 2016-12-09.
 */

public class SplashActivity extends AppCompatActivity {
    //设置启动画面显示时间
    private static final long SPLASH_DELAY_MILLIS = 3000;
    private String[] text={
            "“ 君子爱财，取之有道 ”",
            "“ 今天你记账了吗？”",
            "“ 很高兴，遇见你 ”",
            "“ 理财一小步，致富一大步 ”"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //窗口启动延时设置
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(R.layout.start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startApp();
            }
        },SPLASH_DELAY_MILLIS);

        //首页文案设计
        //获取随机文案
        int x =(int)(Math.floor(Math.random()*3));
        TextView txt;
        txt = (TextView) findViewById(R.id.startTxt);
        txt.setText(text[x]);

    }
    public void startApp(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
