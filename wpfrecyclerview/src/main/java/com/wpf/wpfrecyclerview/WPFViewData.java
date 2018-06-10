package com.wpf.wpfrecyclerview;

import android.support.annotation.IdRes;
import android.view.View;

import java.util.List;

/**
 * Created by 王朋飞 on 2018/3/25.
 *
 */

public class WPFViewData {

    private int viewId;
    private Class viewClass;
    private int viewResId;
    private String viewStr;
    private View view;
    private List<ViewSet> viewSetList;
    private boolean canClick;
    private boolean canLongClick;

    public Class getViewClass() {
        return viewClass;
    }

    public void setViewClass(Class viewClass) {
        this.viewClass = viewClass;
    }

    @IdRes
    public int getViewId() {
        return viewId;
    }

    public void setViewId(@IdRes int viewId) {
        this.viewId = viewId;
    }

    public int getViewResId() {
        return viewResId;
    }

    public void setViewResId(int viewResId) {
        this.viewResId = viewResId;
    }

    public String getViewStr() {
        return viewStr;
    }

    public void setViewStr(String viewStr) {
        this.viewStr = viewStr;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public List<ViewSet> getViewSetList() {
        return viewSetList;
    }

    public void setViewSetList(List<ViewSet> viewSetList) {
        this.viewSetList = viewSetList;
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

    public static class ViewSet {
        private String methodName;
        //如果有int需要设置
        private Class<?>[] methodParameterClass;
        private Object[] methodParameter;

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Object[] getMethodParameter() {
            return methodParameter;
        }

        public <T> void setMethodParameter(T... methodParameter) {
            this.methodParameter = methodParameter;
            if(methodParameter != null && methodParameterClass == null) {
                methodParameterClass = new Class[methodParameter.length];
                for(int i = 0;i<methodParameter.length;++i) {
                    methodParameterClass[i] = methodParameter[i].getClass().getComponentType();
                }
            }
        }

        public void setMethodParameterClass(Class<?>... methodParameterClass) {
            this.methodParameterClass = methodParameterClass;
        }

        public Class<?>[] getMethodParameterClass() {
            return methodParameterClass;
        }
    }
}
