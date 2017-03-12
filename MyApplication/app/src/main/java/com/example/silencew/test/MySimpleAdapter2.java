package com.example.silencew.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.silencew.test.bean.Income;
import com.example.silencew.test.bean.Spend;

import java.util.List;

/**
 * Created by silence.w on 2016-12-13.
 * 查询所有账单-数据适配
 */

public class MySimpleAdapter2 extends RecyclerView.Adapter<MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<Spend> mDatas;

    //点击响应事件接口
    public interface onItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


    public MySimpleAdapter2(Context context, List<Spend> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Spend a =mDatas.get(position);
        String type=null;
        if(a.getmType().equals("spend")){
            switch (a.getType()){
                case 1:type="花钱";
                    break;
                case 2:type="餐饮";
                    break;
                case 3:type="交通";
                    break;
                case 4:type="购物";
                    break;
                case 5:type="发红包";
                    break;
                case 6:type="日用品";
                    break;
                case 7:type="买菜";
                    break;
                case 8:type="水果";
                    break;
                case 9:type="零食";
                    break;
                case 10:type="护肤";
                    break;
            }
            holder.tv_money.setText("-"+a.getMoney());
        }else {
            switch (a.getType()){
                case 1:type="赚钱";
                    break;
                case 2:type="工资";
                    break;
                case 3:type="奖金";
                    break;
                case 4:type="兼职";
                    break;
                case 5:type="福利";
                    break;
                case 6:type="投资";
                    break;
                case 7:type="收红包";
                    break;
                case 8:type="生活费";
                    break;
                case 9:type="报销";
                    break;
                case 10:type="退款";
                    break;

            }
            holder.tv_money.setText("+"+a.getMoney());
        }
        holder.tv.setText(type+" ");
        holder.tv_time.setText(a.getTime()+" ");
        holder.tv_beizhu.setText(a.getMark()+" ");
        if(mOnItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onItemLongClick(holder.itemView,position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {

        return mDatas.size();
    }


}

//相当于容器，装载每条信息
class MyViewHolder2 extends RecyclerView.ViewHolder {

    TextView tv_time,tv,tv_beizhu,tv_money;

    public MyViewHolder2(View itemView) {
        super(itemView);
        tv_time = (TextView) itemView.findViewById(R.id.id_tv_time);
        tv = (TextView) itemView.findViewById(R.id.id_tv);
        tv_beizhu = (TextView) itemView.findViewById(R.id.id_tv_beizhu);
        tv_money = (TextView) itemView.findViewById(R.id.id_tv_money);
    }

}
