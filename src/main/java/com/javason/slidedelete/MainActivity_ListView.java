package com.javason.slidedelete;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.javason.slidedelete.demo.Adapter_ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_ListView extends AppCompatActivity {
    private ListView listView;
    private Adapter_ListView adapter;
    private List<Adapter_ListView.MyDataHolder> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        init();
        listView=findViewById(R.id.myListView);
        adapter=new Adapter_ListView(this,mDataList);
        listView.setAdapter(adapter);
    }
    public void init(){
        mDataList=new ArrayList<>();
        for (int i=0;i<50;i++){
            Adapter_ListView.MyDataHolder item=new Adapter_ListView.MyDataHolder();
            item.title="第"+i+"项";
            mDataList.add(item);
        }
    }


}
