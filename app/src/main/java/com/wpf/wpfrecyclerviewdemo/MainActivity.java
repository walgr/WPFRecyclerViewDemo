package com.wpf.wpfrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wpf.wpfrecyclerview.Listener.OnItemClickListener;
import com.wpf.wpfrecyclerview.Listener.OnItemLongClickListener;
import com.wpf.wpfrecyclerview.Listener.WPFOnItemTouchHelper;
import com.wpf.wpfrecyclerview.WPFAdapter;
import com.wpf.wpfrecyclerview.WPFItemTouchCallBack;
import com.wpf.wpfrecyclerview.WPFLayoutData;
import com.wpf.wpfrecyclerview.WPFViewData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        list.setLayoutManager(new LinearLayoutManager(this));
        WPFAdapter wpfAdapter = new WPFAdapter();
        wpfAdapter.setDataSetList(getListData());

        OnItemClickListener click = new OnItemClickListener<View>() {
            @Override
            public void onClick(View data, int position, int itemType) {
                Log.e("onClick",""+position);
            }
        };

        OnItemLongClickListener longClick = new OnItemLongClickListener<View>() {
            @Override
            public boolean onLongClick(View data, int position, int itemType) {
                Log.e("onLongClick",""+position);
                return true;
            }
        };

//        wpfAdapter.setOnItemClickListener(click);
//        wpfAdapter.setOnItemLongClickListener(longClick);
        list.setAdapter(wpfAdapter);
        WPFOnItemTouchHelper wpfOnItemTouchHelper = new WPFOnItemTouchHelper(click,longClick);
        wpfOnItemTouchHelper.attachToRecyclerView(list);
    }

    private List<WPFLayoutData<?>> getListData() {
        List<WPFLayoutData<?>> dataList = new ArrayList<>();

        for(int i = 0;i<50;++i) {
            WPFLayoutData<TestItemData> data = new WPFLayoutData<>();
            data.setLayoutId(R.layout.list_item);
            TestItemData testItemData = new TestItemData();
            testItemData.setText_1("测试"+i);
            data.setData(testItemData);
            data.setCanClick(true);
            data.setCanLongClick(true);
            dataList.add(data);
        }
        return dataList;
    }
}
