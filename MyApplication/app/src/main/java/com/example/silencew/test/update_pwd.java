package com.example.silencew.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silencew.test.bean.Account;
import com.example.silencew.test.dao.AccountDao;

/**
 * Created by silence.w on 2016-12-15.
 */

public class update_pwd extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_pwd);
        Button btn_up;
        btn_up = (Button) findViewById(R.id.up_btn);
        final EditText user;
        final EditText pwd;
        user = (EditText)findViewById(R.id.up_user);
        pwd = (EditText) findViewById(R.id.up_pwd);

        //修改密码
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String strUser = user.getText().toString();
                    String strPwd = pwd.getText().toString();
                    AccountDao accountDao = new AccountDao(update_pwd.this);
                    Account account = new Account(strUser, strPwd);
                    accountDao.update(account);
                    Toast.makeText(update_pwd.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(update_pwd.this,setting.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(update_pwd.this,"用户名输入错误",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
