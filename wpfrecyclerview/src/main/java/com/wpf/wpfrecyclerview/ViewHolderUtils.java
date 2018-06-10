package com.wpf.wpfrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 王朋飞 on 2018/3/25.
 *
 */

public class ViewHolderUtils {

    public static View getViewById(List<WPFLayoutData<?>> layoutDataList, int layoutId) {
        if(layoutDataList == null) return null;
        for(WPFLayoutData<?> layoutData : layoutDataList) {
            if(layoutData.getView() == null) continue;
            if(layoutId == layoutData.getLayoutId()) return layoutData.getView();
        }
        return null;
    }

    public static void setData(final WPFViewHolder viewHolder, WPFLayoutData<?> layoutData) {
        if(layoutData == null) return;
        setData(viewHolder,layoutData.getData());
        View view = viewHolder.itemView;
        if(layoutData.isCanClick()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.onClick(v);
                }
            });
        }
        if(layoutData.isCanLongClick()) {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return viewHolder.onLongClick(v);
                }
            });
        }
        if(layoutData.getViewDataList() == null
                || layoutData.getViewDataList().isEmpty()) return;

        for(WPFViewData viewData : layoutData.getViewDataList()) {
            View viewById = view.findViewById(viewData.getViewId());
            setDataToView(viewById,viewData);
            if(viewData.isCanClick()) {
                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewHolder.onClick(v);
                    }
                });
            }
            if(viewData.isCanLongClick()) {
                viewById.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return viewHolder.onLongClick(v);
                    }
                });
            }
        }
    }

    public static <T> void setData(RecyclerView.ViewHolder viewHolder,T data) {
        if(data == null) return;
        View view = viewHolder.itemView;
        Context context = view.getContext();
        Field[] fieldList = data.getClass().getDeclaredFields();
        for(Field field : fieldList) {
            String name = field.getName();
            if ("serialVersionUID".equals(name) || "CREATOR".equals(name)) continue;
            View viewById = view.findViewById(context.getResources()
                    .getIdentifier(name, "id", context.getPackageName()));
            if(viewById instanceof TextView) {
                TextView textView = (TextView) viewById;
                try {
                    field.setAccessible(true);
                    Object obj = field.get(data);
                    if(obj instanceof String) {
                        String value = (String) obj;
                        textView.setText(value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setDataToView(View viewById,WPFViewData viewData) {
        if (viewById == null) return;
        if(viewData == null) return;
//        if(viewData.getView() != null) {
//            try {
//                BeanUtils.copyPropertiesByWhiteList(viewById,viewData.getView(),
//                        getMethodNameListByVieDataSetList(viewData.getViewSetList()));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            return;
//        }

        if (viewById instanceof TextView) {
            TextView textView = (TextView) viewById;
            if(viewData.getViewResId() != 0)
                textView.setText(viewData.getViewResId());
            if(viewData.getViewStr() != null)
                textView.setText(viewData.getViewStr());
        } else if (viewById instanceof ImageView) {
            if(viewData.getViewResId() != 0) {
                ImageView imageView = (ImageView) viewById;
                imageView.setImageResource(viewData.getViewResId());
            }
        }

        if(viewData.getViewSetList() == null) return;
        for(WPFViewData.ViewSet viewSet : viewData.getViewSetList()) {
            try {
                Method method = viewById.getClass().getMethod(viewSet.getMethodName(),
                        viewSet.getMethodParameterClass());
                if(method == null) return;
                method.invoke(viewById,viewSet.getMethodParameter());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getMethodNameListByVieDataSetList(List<WPFViewData.ViewSet> viewSetList) {
        if(viewSetList == null) return null;
        List<String> methodNameList = new ArrayList<>();
        for(WPFViewData.ViewSet viewSet : viewSetList) {
            methodNameList.add(viewSet.getMethodName());
        }
        return methodNameList;
    }

    private static Class[] getClassListByObjectList(Object... objects) {
        if(objects == null || objects.length == 0) return null;
        Class[] classes = new Class[objects.length];
        int i = 0;
        for(Object object : objects) {
            classes[i++] = object.getClass();
        }
        return classes;
    }
}
