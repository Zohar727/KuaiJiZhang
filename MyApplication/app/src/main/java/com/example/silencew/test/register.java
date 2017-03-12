package com.example.silencew.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silencew.test.bean.Account;
import com.example.silencew.test.dao.AccountDao;

/**
 * Created by silence.w on 2016-12-06.
 */

public class register extends AppCompatActivity implements TextWatcher, View.OnClickListener{
    private  EditText username;
    private  EditText password;
    private  EditText confirm_password;
    private  EditText email;
    private  Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void initView(){
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        confirm_password = (EditText) findViewById(R.id.et_confirm_password);
        email = (EditText) findViewById(R.id.et_email);
        register = (Button) findViewById(R.id.r_register);
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        confirm_password.addTextChangedListener(this);
        email.addTextChangedListener(this);
        register.setSelected(false);
        register.setEnabled(false);
        register.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!TextUtils.isEmpty(username.getText().toString())&&!TextUtils.isEmpty(password.getText().toString())
                &&!TextUtils.isEmpty(confirm_password.getText().toString())&&!TextUtils.isEmpty(email.getText().toString())){
            register.setSelected(true);
            register.setEnabled(true);
        }else {
            register.setSelected(false);
            register.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
       if(!password.getText().toString().equals(confirm_password.getText().toString())){
           confirm_password.setText("");
           Toast.makeText(register.this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
       }else{
           AccountDao accountDao = new AccountDao(register.this);
           Account account = new Account(username.getText().toString(),password.getText().toString());
           accountDao.add(account);
           Toast.makeText(register.this,"注册成功！",Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
           finish();
       }
    }
}
