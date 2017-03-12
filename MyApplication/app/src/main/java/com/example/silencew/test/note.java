package com.example.silencew.test;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.silencew.test.bean.Income;
import com.example.silencew.test.bean.Note;
import com.example.silencew.test.dao.IncomeDao;
import com.example.silencew.test.dao.NoteDao;

import java.util.List;

/**
 * Created by silence.w on 2016-12-15.
 */

public class note extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private List<Note> mDatas;
    //数据库操作类
    private NoteDao dao;
    private int id=1;
    private note_adapter mAdapter;
    private ItemTouchHelper itemTouchHelper;//用于拖拽，滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        initDatas();
        initView();
        dao = new NoteDao(this);
        mDatas=dao.queryAll();
        mAdapter = new note_adapter(this, mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        //设置点击事件
        mAdapter.setOnItemClickListener(new note_adapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {//设置点击后跳转到修改界面
//                Toast.makeText(note.this,"click:"+position+"",Toast.LENGTH_SHORT).show();
                final Note a = mDatas.get(position);
                int oId = a.getId();
                String oContent = a.getvNote();
                int modeCode = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("id",oId);
                bundle.putString("note",oContent);
                bundle.putInt("modeCode",modeCode);
                Intent intent = new Intent(note.this,note_edit.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(note.this,"Long click:"+position+"",Toast.LENGTH_SHORT).show();
            }
        });

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback(){
            //设置移动方式
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags;//拖动标志
                final int swipeFlags;//滑动标志
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            //移动过程中调用
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                int fromPosition = viewHolder.getAdapterPosition();
//                int toPosition = target.getAdapterPosition();
//                data.add(toPosition, data.remove(fromPosition));
//                adapter.notifyItemMoved(fromPosition, toPosition);
                return false;
            }

            //侧滑过程中调用
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                final Note a= mDatas.get(position);
                mAdapter.notifyItemRemoved(position);
                mDatas.remove(position);
                dao.delete(a.getId());
            }

            //是否可以拖拽移动位置
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            //当长按的时候调用
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
                //震动提示
                Vibrator vibrator = (Vibrator) note.this.getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(35);
                super.onSelectedChanged(viewHolder, actionState);
            }

            //当手指松开的时候调用
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerview);

        //添加便签按钮
        Button addnote;
        addnote = (Button) findViewById(R.id.note_add);
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(note.this,note_edit.class);
                Bundle bundle1 = new Bundle();
                bundle1.putInt("modeCode",0);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview4);
    }

    private void initDatas() {

    }



}