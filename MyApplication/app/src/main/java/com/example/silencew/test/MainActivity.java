package com.example.silencew.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silencew.test.bean.Account;
import com.example.silencew.test.dao.AccountDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private long mPressedTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login;
        login= (Button) findViewById(R.id.login_button);
        login.setSelected(true);
        final EditText user;
        final EditText password;
        user = (EditText) findViewById(R.id.oUsername);
        password = (EditText) findViewById(R.id.oPassword);
        final String strUser = user.getText().toString();
        final String strPass = password.getText().toString();

        //登录验证
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountDao dao = new AccountDao(MainActivity.this);
                try{
                    Account a = dao.query(user.getText().toString());
//                    Toast.makeText(MainActivity.this,user.getText().toString()+"-"+a.getPassword(),Toast.LENGTH_SHORT).show();
                    if(a.getPassword().equals(password.getText().toString())) {
                        Log.i("MainActivity", "login");
                        Toast.makeText(MainActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this, "密码错误，请重新输入!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"无此用户名，请重新输入",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
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

    public void click(View v){
        switch(v.getId()){
//            case R.id.login_button:
//
//
//                break;
            case R.id.register:
//                Toast.makeText(MainActivity.this,"点击注册",Toast.LENGTH_SHORT).show();
                Log.i("MainActivity","register");
                Intent intent2 = new Intent(this,register.class);
                startActivity(intent2);
                finish();
        }

    }
}
