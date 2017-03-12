package com.example.silencew.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.silencew.test.bean.Income;
import com.example.silencew.test.bean.Note;
import com.example.silencew.test.dao.NoteDao;

import java.util.Calendar;

/**
 * Created by silence.w on 2016-12-15.
 */

public class note_edit extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add);
        //接受传递的数据
        final Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        //传递数据赋值
        final int oId = bundle.getInt("id");
        final String note = bundle.getString("note");
        final int modeCode = bundle.getInt("modeCode");
//        Toast.makeText(this,"传递"+oId+note+modeCode,Toast.LENGTH_SHORT).show();
        Button BtnSave;
        final EditText noteContent;
        BtnSave = (Button) findViewById(R.id.note_save);
        //最大编号

        //获得便签内容
        noteContent = (EditText) findViewById(R.id.note_add_content);
        //获得时间
        final int year,month,day,hour;
        Calendar c = Calendar.getInstance();
        //取得系统日期:
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH)+1;
        day = c.get(Calendar.DAY_OF_MONTH);
        //根据modeCode来设置界面
        if(modeCode == 1){
            noteContent.setText(note);
        }
        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id;
                String time =year+"-"+month+"-"+day;
                String strNote = noteContent.getText().toString();
                NoteDao noteDao = new NoteDao(note_edit.this);
                if (modeCode == 0){
                    //进入增加模式
                    id = noteDao.getMaxId()+1;
                    Note note1 = new Note(id,time,strNote);
                    noteDao.add(note1);
                    Toast.makeText(note_edit.this,"新增便签成功",Toast.LENGTH_SHORT).show();
                }else {
                    //进入修改模式
                    Note note1 = new Note(
                            oId,time,strNote
                    );
                    noteDao.update(note1);
                    Toast.makeText(note_edit.this,"修改便签成功",Toast.LENGTH_SHORT).show();
                }
                Intent intent2 = new Intent(note_edit.this,note.class);
                startActivity(intent2);
                finish();
            }
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(note_edit.this,note.class);
        startActivity(intent);
        finish();
    }
}
