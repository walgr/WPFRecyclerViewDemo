package com.wpf.wpfrecyclerview.Listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 王朋飞 on 2018/3/26.
 */

public class WPFOnItemTouchHelper implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private GestureDetectorCompat mGestureDetectorCompat;
    private OnItemClickListener<View> onItemClickListener;
    private OnItemLongClickListener<View> onItemLongClickListener;

    public WPFOnItemTouchHelper(OnItemClickListener<View> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public WPFOnItemTouchHelper(OnItemClickListener<View> onItemClickListener, OnItemLongClickListener<View> onItemLongClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),
                new WPFGestureListener());
        this.mRecyclerView.addOnItemTouchListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class WPFGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder VH = mRecyclerView.getChildViewHolder(child);
                if(onItemClickListener != null)
                    onItemClickListener.onClick(VH.itemView,VH.getAdapterPosition(),VH.getItemViewType());
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder VH = mRecyclerView.getChildViewHolder(child);
                if(onItemLongClickListener != null)
                    onItemLongClickListener.onLongClick(VH.itemView,VH.getAdapterPosition(),VH.getItemViewType());
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener<View> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<View> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
