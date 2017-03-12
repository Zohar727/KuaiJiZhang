package com.example.silencew.test;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * Created by silence.w on 2016-12-13.
 */

public class list_main extends TabActivity{
    private TabHost tabhost;
    private TabWidget tabWidget;
    private static final int TabHeight = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);

        //tab实现
        //第一个tab为总账单
        tabhost = getTabHost();
        Intent intent;
        intent = new Intent(this,list2.class);
        TabHost.TabSpec tab1 =tabhost.newTabSpec("one");
        tab1.setIndicator("总账单");
        tab1.setContent(intent);

        //第二个tab为支出
        tabhost = getTabHost();
        Intent intent2;
        intent2 = new Intent(this,list3.class);
        TabHost.TabSpec tab2 =tabhost.newTabSpec("one");
        tab2.setIndicator("支出");
        tab2.setContent(intent2);

        //第三个tab为收入
        tabhost = getTabHost();
        Intent intent3;
        intent3 = new Intent(this,list.class);
        TabHost.TabSpec tab3 =tabhost.newTabSpec("one");
        tab3.setIndicator("收入");
        tab3.setContent(intent3);

        //添加tab
        tabhost.addTab(tab1);
        tabhost.addTab(tab2);
        tabhost.addTab(tab3);

        tabWidget = (TabWidget) findViewById(android.R.id.tabs);
        //点击选项卡样式改变
        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for(int i=0;i<tabWidget.getChildCount();i++){
                    View v = tabWidget.getChildAt(i);
                    TextView tv =(TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
                    if(tabhost.getCurrentTab()==i){
                        v.setBackgroundColor(Color.parseColor("#1dcac8"));
                        tv.setTextColor(Color.parseColor("#ffffff"));
                    }
                    else {
                        v.setBackgroundColor(Color.parseColor("#f3fdfe"));
                        tv.setTextColor(Color.parseColor("#1dcac8"));
                        //v.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_bg2));
                    }
                }
            }
        });

        //初始化选项卡的样式
        tabhost.setCurrentTab(0);
        tabWidget.getChildAt(0).setBackgroundColor(Color.parseColor("#1dcac8"));
        TextView tv =(TextView) tabWidget.getChildAt(0).findViewById(android.R.id.title);
        TextView tv2 =(TextView) tabWidget.getChildAt(1).findViewById(android.R.id.title);
        TextView tv3 =(TextView) tabWidget.getChildAt(2).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv2.setTextColor(Color.parseColor("#1dcac8"));
        tv3.setTextColor(Color.parseColor("#1dcac8"));

    }
}
