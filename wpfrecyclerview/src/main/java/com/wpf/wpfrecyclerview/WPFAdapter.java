package com.wpf.wpfrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.wpf.wpfrecyclerview.Listener.OnItemClickListener;
import com.wpf.wpfrecyclerview.Listener.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王朋飞 on 2018/3/25.
 *
 */

public class WPFAdapter extends RecyclerView.Adapter<WPFViewHolder> {

    private Context context;
    private View header;

    private List<WPFLayoutData<?>> dataSetList = new ArrayList<>();

    private OnItemClickListener<View> onItemClickListener;
    private OnItemLongClickListener<View> onItemLongClickListener;

    public WPFAdapter() {}

    public WPFAdapter(List<WPFLayoutData<?>> dataSetList) {
        this.dataSetList = dataSetList;
    }

    @Override
    public WPFViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View layoutView;
        layoutView = ViewHolderUtils.getViewById(dataSetList, viewType);
        if (layoutView == null)
            layoutView = LayoutInflater.from(context).inflate(viewType, parent,false);
        WPFViewHolder viewHolder = new WPFViewHolder(layoutView);
        viewHolder.setOnItemClickListener(onItemClickListener);
        viewHolder.setOnItemLongClickListener(onItemLongClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WPFViewHolder holder, int position) {
        ViewHolderUtils.setData(holder, dataSetList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSetList == null ? 0 : dataSetList.size();
    }

    public void addHeaderView(View header) {
        this.header = header;
        if(dataSetList != null) {
            WPFLayoutData wpfLayoutData = new WPFLayoutData();
            wpfLayoutData.setLayoutId(-1);
            wpfLayoutData.setView(header);
            dataSetList.add(0, wpfLayoutData);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return dataSetList.get(position).getLayoutId();
    }

    public void setDataSetList(List<WPFLayoutData<?>> dataSetList) {
        this.dataSetList = dataSetList;
        if(header != null) {
            if(this.dataSetList != null && this.dataSetList.get(0).getView() != this.header) {
                WPFLayoutData wpfLayoutData = new WPFLayoutData();
                wpfLayoutData.setView(header);
                this.dataSetList.add(0, wpfLayoutData);
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<View> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<View> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
