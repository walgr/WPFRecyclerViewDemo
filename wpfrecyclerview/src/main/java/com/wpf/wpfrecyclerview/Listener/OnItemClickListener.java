package com.wpf.wpfrecyclerview.Listener;


/**
 * Created by 王朋飞 on 2017/9/8.
 *
 * @Title 带数据的点击事件
 * @Package com.android.asiainfo.chinapost.module.v360.ui.ShowView.Adapter.Listener
 * @Description: ${TODO} (用一句话描述该文件做什么)
 * @LastModifiedTime 2017/9/8
 */

public interface OnItemClickListener<T> {
    void onClick(T data, int position, int itemType);
}
