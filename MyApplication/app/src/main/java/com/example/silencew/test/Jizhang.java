package com.example.silencew.test;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.silencew.test.bean.Income;
import com.example.silencew.test.bean.Spend;
import com.example.silencew.test.dao.IncomeDao;
import com.example.silencew.test.dao.SpendDao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Logger;

public class Jizhang extends TabActivity {
    //tab标签变量定义
    private TabHost tabhost;
    private TabWidget tabWidget;
    private static final int TabHeight = 120;
    //资源定义
    private String[] names ={
            "花钱","餐饮","交通","购物","发红包","日用品","买菜","水果","零食","护肤"
    };
    private String[] names2 ={
            "赚钱","工资","奖金","兼职","福利","投资","收红包","生活费","报销","退款"
    };
    private Integer[] img={
        R.drawable.cost,R.drawable.food,R.drawable.traffic,
            R.drawable.shopping,R.drawable.lucky,R.drawable.riyong,
            R.drawable.vegetable,R.drawable.fruit,R.drawable.cake,
            R.drawable.beautiful,R.drawable.traffic

    };
    private Integer[] img2={
            R.drawable.income,R.drawable.salary,R.drawable.award,
            R.drawable.job,R.drawable.fuli,R.drawable.invest,
            R.drawable.rlucky,R.drawable.life,R.drawable.baoxiao,
            R.drawable.refund,R.drawable.salary

    };
    private int index;
    //时间选择器变量定义
    int mYear,mMonth,mDay;
    Button btn;
    TextView dateDisplay;
    final int DATE_DIALOG = 1;

    //收入金额
    private EditText m_make;
    //支出金额
    private EditText m_spend;
    //收入类型
    private int type;
    //时间
    private String time;
    //备注
    private EditText mark;
    //编号
    private int id;
    //保存按钮
    Button btnSaveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jizhang);
        //收入数据获取
        m_make = (EditText) findViewById(R.id.m_make);
        //支出数据获取
        m_spend = (EditText) findViewById(R.id.m_spend);
        mark = (EditText) findViewById(R.id.et_beizhu);

        //当传递数据过来时
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //传递数据赋值
        final int oId = bundle.getInt("id");
        double oMoney = bundle.getDouble("money");
        String oTime = bundle.getString("time");
        final int oType = bundle.getInt("type");
        String oMark = bundle.getString("mark");
        int oTabNum = bundle.getInt("tabNum");
//        Toast.makeText(this,"传递的ID："+oId+"-"+oMoney
//                +"-"+oTime+"-"+oType+"-"
//                +oMark+"-"+oTabNum,Toast.LENGTH_SHORT).show();
        //传递数据后初始化界面
        if(oType != 0){//判断界面是由listview跳转过来还是menu跳转过来的
            if(oTabNum ==0){//判断进入的是收入界面还是支出界面
                //支出界面
                m_spend.setText(Double.toString(oMoney));
                mark.setText(oMark);
            }else{
                //收入界面
//                m_spend.setText(" "+oMoney);
//                mark.setText(""+oMark);
//                btn.setText(""+oTime);
                m_make.setText(Double.toString(oMoney));
                mark.setText(oMark);
//              btn.setText(new StringBuffer().append(oMark));
            }
        }
//        initView(oId);
        //实现Tab切换效果
        //从TabActivity上面获取放置Tab的TabHost
        tabhost = getTabHost();
        TabHost.TabSpec tab1 = tabhost.newTabSpec("one");
        tab1.setIndicator("支出");
        tab1.setContent(R.id.widget_layout_red);
        
        //TabSpec相当于一个分页面
        TabHost.TabSpec tab2 = tabhost.newTabSpec("two");
        tab2.setIndicator("收入");
        tab2.setContent(R.id.widget_layout_yellow);
        
        tabhost.addTab(tab1);
        tabhost.addTab(tab2);

        tabWidget = (TabWidget) findViewById(android.R.id.tabs);

        for(int i = 0; i<tabWidget.getChildCount();i++){
            View view = tabWidget.getChildAt(i);
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            view.getLayoutParams().height = TabHeight;
        }
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
        if(oTabNum == 0) {
            tabhost.setCurrentTab(0);
            tabWidget.getChildAt(0).setBackgroundColor(Color.parseColor("#1dcac8"));
            TextView tv = (TextView) tabWidget.getChildAt(0).findViewById(android.R.id.title);
            TextView tv2 = (TextView) tabWidget.getChildAt(1).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv2.setTextColor(Color.parseColor("#1dcac8"));
        }else{
            tabhost.setCurrentTab(1);
            tabWidget.getChildAt(1).setBackgroundColor(Color.parseColor("#1dcac8"));
            TextView tv = (TextView) tabWidget.getChildAt(0).findViewById(android.R.id.title);
            TextView tv2 = (TextView) tabWidget.getChildAt(1).findViewById(android.R.id.title);
            tv2.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextColor(Color.parseColor("#1dcac8"));
        }

        //实现N宫格效果
        GridView gridview = (GridView) findViewById(R.id.GridView);
        GridView gridview2 = (GridView) findViewById(R.id.GridView2);
        ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
        ArrayList<HashMap<String, Object>> meumList2 = new ArrayList<HashMap<String, Object>>();
        for(int i = 0;i < 10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", img[i]);
            map.put("ItemText", ""+names[i]);
            meumList.add(map);
        }
        for(int i = 0;i < 10;i++)
        {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", img2[i]);
            map.put("ItemText", ""+names2[i]);
            meumList2.add(map);
        }
        SimpleAdapter saItem = new SimpleAdapter(this,
                meumList, //数据源
                R.layout.jizhang_cat, //Item布局资源ID
                new String[]{"ItemImage","ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage,R.id.ItemText});  //对应R的Id
        SimpleAdapter saItem2 = new SimpleAdapter(this,
                meumList2, //数据源
                R.layout.jizhang_cat, //Item布局资源ID
                new String[]{"ItemImage","ItemText"}, //对应map的Key
                new int[]{R.id.ItemImage,R.id.ItemText});  //对应R的Id

        //添加Item到网格中
        gridview.setAdapter(saItem);
        gridview2.setAdapter(saItem2);
        //添加点击事件
        gridview.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {   //arg0=parent, arg1=view, arg2=position, arg3=id
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                    {
                        index=arg2+1;//id是从0开始的，所以需要+1

//                        Toast.makeText(getApplicationContext(), "你按下了选项："+index, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<arg0.getCount();i++){
                            View v =arg0.getChildAt(i);
                            if(arg2 == i){
                                //设置选中的item背景颜色
//                                arg1.setBackgroundColor(Color.parseColor("#E15344"));
                                arg1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                            }else {
                                v.setBackgroundColor(Color.parseColor("#ffffff"));
                            }
                        }
                    }
                }
        );
        gridview2.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {   //arg0=parent, arg1=view, arg2=position, arg3=id
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
                    {
                        index=arg2+1;//id是从0开始的，所以需要+1
//                        Toast.makeText(getApplicationContext(), "你按下了选项："+index, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<arg0.getCount();i++){
                            View v =arg0.getChildAt(i);
                            if(arg2 == i){
                                //设置选中的item背景颜色
//                                arg1.setBackgroundColor(Color.parseColor("#59DFD6"));
                                arg1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
                            }else {
                                v.setBackgroundColor(Color.parseColor("#ffffff"));
                            }
                        }
                    }
                }
        );

        //时间选择器实现
        btn = (Button) findViewById(R.id.data_select);
//        dateDisplay = (TextView) findViewById(R.id.data_display);

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        //默认显示当前日期
//        dateDisplay.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));
        btn.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));

        //数据库操作
        //获取类别

        //获取时间
        int mdMonth = mMonth+1;
        final String strTime=(mYear+"-"+mdMonth+"-"+mDay);

        //收入保存按钮获取
        btnSaveButton =(Button) findViewById(R.id.save);
        final String testTime =btnSaveButton.getText().toString();
        btnSaveButton.setOnClickListener(new OnClickListener() {

            private String textMark;
            @Override
            //收入支出处理
            public void onClick(View view) {
                int tabNum = tabhost.getCurrentTab();//判断当前页面
                textMark = mark.getText().toString();
                if(tabNum == 0){//当前页面为支出页面
                    String strMoney = m_spend.getText().toString();
                    if (!strMoney.isEmpty()) {
                        //创建spenddao对象
                        double d_Money = Double.parseDouble(strMoney);
                        SpendDao spendDao = new SpendDao(Jizhang.this);
                        if(oType!=0){
                            //修改模式
                            Spend spend = new Spend(
                                    oId,d_Money,strTime,index,textMark,"income"
                            );
                            spendDao.update(spend);
                            Toast.makeText(Jizhang.this,"更新支出成功",Toast.LENGTH_LONG).show();
                        }else{
                            //增加模式
                            //获取最大编号
                            id = spendDao.getMaxId("spend")+1;
                            //创建spend对象
                            Spend spend = new Spend(
                                    id,d_Money,strTime,index,textMark,"spend"
                            );
                            spendDao.add(spend);
                            Toast.makeText(Jizhang.this,"新增支出成功",Toast.LENGTH_LONG).show();
                        }
                        //保存后清空数据并跳到账单页面
                        m_spend.setText("");
                        mark.setText("");
                        Intent intent = new Intent(Jizhang.this,list_main.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Jizhang.this,"请输入支出金额",Toast.LENGTH_SHORT).show();
                    }
                }else {//当前页面为收入页面
                    String strMoney = m_make.getText().toString();
                    if(!strMoney.isEmpty()){
                        //创建incomedao对象
                        double d_Money=Double.parseDouble(strMoney);
                        IncomeDao incomeDao= new IncomeDao(Jizhang.this);

                        if(oType!=0){
                            //修改模式
                            //创建income对象
                            Income income = new Income(
                                    oId,d_Money,strTime,index,textMark,"income"
                            );
                            incomeDao.update(income);
                            Toast.makeText(Jizhang.this,"更新收入成功",Toast.LENGTH_LONG).show();
                        }else {//添加模式
                            id = incomeDao.getMaxId("income")+1;
                            //创建income对象
                            Income income = new Income(
                                    id,d_Money,strTime,index,textMark,"income"
                            );
                            incomeDao.add(income);
                            Toast.makeText(Jizhang.this,"新增收入成功",Toast.LENGTH_LONG).show();
                        }
                        //保存好清空数据
                        m_make.setText("");
                        mark.setText("");
                        Intent intent = new Intent(Jizhang.this,list_main.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Jizhang.this,"请输入收入金额",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });





    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
//        dateDisplay.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));
        btn.setText(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日"));
//        Toast.makeText(this,mYear+"-"+mMonth+"-"+mDay,Toast.LENGTH_SHORT).show();

    }

//    public String getTime(){
//        String time =(new StringBuffer().append(mYear).append("年").append(mMonth + 1).append("月").append(mDay).append("日")).toString();
//        Toast.makeText(this,time,Toast.LENGTH_SHORT).show();
//        return time;
//    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
//            getTime();
            display();
        }
    };

//    public void initView(int id){
//        Toast.makeText(this,"传递的ID是："+id,Toast.LENGTH_SHORT);
//    };







}
