package com.example.silencew.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.silencew.test.bean.Note;

import java.util.List;

/**
 * Created by silence.w on 2016-12-15.
 */

public class note_adapter extends RecyclerView.Adapter<MyViewHolder4> {

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<Note> mDatas;

    //点击响应事件接口
    public interface onItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


    public note_adapter(Context context, List<Note> datas) {
        this.mContext = context;
        this.mDatas = datas;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.note_item, parent, false);
        MyViewHolder4 viewHolder = new MyViewHolder4(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder4 holder, final int position) {
        final Note a =mDatas.get(position);
        holder.tv_content.setText(a.getvNote()+"...");
        holder.tv_time.setText(a.getTime()+" ");
//        holder.tv_content.setText("ceshi");
//        holder.tv_time.setText("shijian");


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
class MyViewHolder4 extends RecyclerView.ViewHolder {

    TextView tv_time,tv_content;

    public MyViewHolder4(View itemView) {
        super(itemView);
        tv_time = (TextView) itemView.findViewById(R.id.note_time);
        tv_content = (TextView) itemView.findViewById(R.id.note_content);

    }

}

