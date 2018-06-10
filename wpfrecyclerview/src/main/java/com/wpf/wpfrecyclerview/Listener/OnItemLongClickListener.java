package com.wpf.wpfrecyclerview.Listener;

/**
 * Created by 王朋飞 on 2018/3/26.
 */

public interface OnItemLongClickListener<T> {
    boolean onLongClick(T data, int position, int itemType);
}
