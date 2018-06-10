package com.wpf.wpfrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.wpfrecyclerview.Listener.OnItemClickListener;
import com.wpf.wpfrecyclerview.Listener.OnItemLongClickListener;

/**
 * Created by 王朋飞 on 2018/3/25.
 *
 */

public class WPFViewHolder extends RecyclerView.ViewHolder {

    private OnItemClickListener<View> onItemClickListener;
    private OnItemLongClickListener<View> onItemLongClickListener;

    public WPFViewHolder(View itemView) {
        super(itemView);
    }

    public void onClick(View v) {
        if(onItemClickListener != null) {
            onItemClickListener.onClick(v,getAdapterPosition(),getItemViewType());
        }
    }

    public boolean onLongClick(View v) {
        return onItemLongClickListener != null &&
                onItemLongClickListener.onLongClick(v, getAdapterPosition(), getItemViewType());
    }

    public void setOnItemClickListener(OnItemClickListener<View> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<View> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
