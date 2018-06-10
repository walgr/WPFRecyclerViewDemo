package com.wpf.wpfrecyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.wpf.wpfrecyclerview.Listener.OnItemDeleteListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 王朋飞 on 2018/3/27.
 *
 */

public class WPFItemTouchCallBack extends ItemTouchHelper.Callback {

    private boolean isLongPressDragEnabled;
    private List<?> mListData = new ArrayList<>();
    private OnItemDeleteListener onItemDeleteListener;

    /**
     * 拖动标识
     */
    private int dragFlags;
    /**
     * 删除滑动标识
     */
    private int swipeFlags;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        dragFlags = 0;
        swipeFlags = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager
                || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            if (viewHolder.getAdapterPosition() != 0)
                swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (toPosition != 0) {
            if (fromPosition < toPosition)
                //向下拖动
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mListData, i, i + 1);
                }
            else {
                //向上拖动
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mListData, i, i - 1);
                }
            }
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(onItemDeleteListener != null)
            onItemDeleteListener.onDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return isLongPressDragEnabled;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setPressed(true);
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setPressed(false);
    }

    public void setOnItemDeleteListener(OnItemDeleteListener onItemDeleteListener) {
        this.onItemDeleteListener = onItemDeleteListener;
    }

    public void setmListData(List<?> mListData) {
        this.mListData = mListData;
    }

    public void setLongPressDragEnabled(boolean longPressDragEnabled) {
        isLongPressDragEnabled = longPressDragEnabled;
    }
}
