package com.example.silencew.test;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by silence.w on 2016-12-07.
 */

public class MenuActivity extends AppCompatActivity {
    private long mPressedTime=0;
    private String[] names ={
            "记一笔","便签","账单","设置","退出"
    };
    private Integer[] img={
        R.drawable.jizhang,R.drawable.note,R.drawable.bill,
            R.drawable.setting,R.drawable.exit
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //菜单文案设计
        TextView menu_text;
        menu_text = (TextView) findViewById(R.id.menu_tv);
           //获取时间来设计文案
        int year,month,day,hour;
        Calendar c = Calendar.getInstance();
        //取得系统日期:
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        //取得系统时间：
        hour = c.get(Calendar.HOUR_OF_DAY);
//        Toast.makeText(this,year+"-"+month+"-"+day+hour,Toast.LENGTH_SHORT).show();
        if(hour<9){
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("早上好！用记账开启新的一天吧！"));
        }else if (hour>=9 && hour<12){
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("上午好！记得常记账哦！"));
        }else if(hour>=12 && hour<14){
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("午饭时间啦！快记下午饭花了多少钱！"));
        }else if(hour>=14 && hour<18){
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("下午好！保持精力充沛哦！"));
        }else if(hour>=18 && hour<20){
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("晚上好！吃晚饭了吗？"));
        }else {
            menu_text.setText(new StringBuffer().append(month).append("月")
                    .append(day).append("日").append("\r\n").append("夜深了，算算今天的收支吧！"));
        }


        //九宫格布局初始化
        GridView gridview = (GridView) findViewById(R.id.GridView);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
        for(int i = 0;i < 5;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", img[i]);
            map.put("ItemText", ""+names[i]);
            meumList.add(map);
        }
        SimpleAdapter saItem = new SimpleAdapter(this,
                meumList, //数据源
                R.layout.menu_item, //Item布局资源ID
                new String[]{"ItemImage","ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage,R.id.ItemText});  //对应R的Id

        //添加Item到网格中
        gridview.setAdapter(saItem);
        //添加点击事件
        gridview.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {   //arg0=parent, arg1=view, arg2=position, arg3=id
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                    {
                        int index=arg2+1;//id是从0开始的，所以需要+1
//                        Toast.makeText(getApplicationContext(), "你按下了选项："+index, Toast.LENGTH_SHORT).show();
                        switch (arg2){
                            case 0:
                                Intent intent = new Intent(arg1.getContext(),Jizhang.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("other","none");//为了解决listview数据传递的bug
                                intent.putExtras(bundle);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(arg1.getContext(),note.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(arg1.getContext(),list_main.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(arg1.getContext(),setting.class);
                                startActivity(intent3);
                                break;
                            case 4:
                                showNormalDialog();
                        }
                    }
                }
        );
    }

    //定义后退键为退出程序
    public void onBackPressed(){
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if((mNowTime - mPressedTime) > 2000){//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        }
        else{//退出程序
            this.finish();
            System.exit(0);
        }
    }

    //弹出对话框
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(R.drawable.exit)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        finish();
                        System.exit(0);

                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                        dialog.dismiss();
                    }
                }).show();
    }
}



