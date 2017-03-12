package com.example.silencew.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by silence.w on 2016-12-15.
 */

public class setting extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        Button btnUpdate;
        btnUpdate = (Button) findViewById(R.id.update_pwd);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(setting.this,update_pwd.class);
                startActivity(intent);
            }
        });
    }
}
