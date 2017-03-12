package com.example.silencew.test;

/**
 * 收入账单
 */

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
import android.widget.Toast;

import com.example.silencew.test.bean.Income;
import com.example.silencew.test.dao.IncomeDao;

import java.util.ArrayList;
import java.util.List;

public class list extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private List<Income> mDatas;
    //数据库操作类
    private IncomeDao dao;
    private int id=1;
    private MySimpleAdapter mAdapter;
    private ItemTouchHelper itemTouchHelper;//用于拖拽，滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initDatas();
        initView();
        dao = new IncomeDao(this);
        mDatas=dao.queryAll();
        mAdapter = new MySimpleAdapter(this, mDatas);
        mRecyclerview.setAdapter(mAdapter);
        //设置RecyclerView的布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        //设置点击事件
        mAdapter.setOnItemClickListener(new MySimpleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {//设置点击后跳转到修改界面
//                Toast.makeText(list.this,"click:"+position+"",Toast.LENGTH_SHORT).show();
                final Income a = mDatas.get(position);
                int oId = a.getId();
                double oMoney = a.getMoney();
                String oTime = a.getTime();
                int oType = a.getType();
                String oMark = a.getMark();
                int tabNum = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("id",oId);
                bundle.putDouble("money",oMoney);
                bundle.putString("time",oTime);
                bundle.putInt("type",oType);
                bundle.putString("mark",oMark);
                bundle.putInt("tabNum",tabNum);
                Intent intent = new Intent(list.this,Jizhang.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }

            @Override
            public void onItemLongClick(View view, int position) {
//                Toast.makeText(list.this,"Long click:"+position+"",Toast.LENGTH_SHORT).show();
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

            //侧滑过程中调用删除数据
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                final Income a= mDatas.get(position);
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
                Vibrator vibrator = (Vibrator) list.this.getSystemService(VIBRATOR_SERVICE);
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
    }

    private void initView() {
        mRecyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
    }

    private void initDatas() {

    }


}