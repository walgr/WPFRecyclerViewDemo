package com.wpf.wpfrecyclerview;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.List;

/**
 * Created by 王朋飞 on 2018/3/25.
 *
 */

public class WPFLayoutData<T> {

    @LayoutRes
    private int layoutId = 0;
    private List<WPFViewData> viewDataList;
    private T data;
    private View view;
    private boolean canClick;
    private boolean canLongClick;

    @LayoutRes
    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
    }

    public List<WPFViewData> getViewDataList() {
        return viewDataList;
    }

    public void setViewDataList(List<WPFViewData> viewDataList) {
        this.viewDataList = viewDataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public boolean isCanLongClick() {
        return canLongClick;
    }

    public void setCanLongClick(boolean canLongClick) {
        this.canLongClick = canLongClick;
    }
}
