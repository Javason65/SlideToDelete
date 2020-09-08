package com.javason.slidedelete.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.javason.slidedelete.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ListView extends BaseAdapter {
    private List<View> ViewTemp;
    private List<Integer> NoTemp;
    private int time;
    private Context context;
    private List<MyDataHolder> dataList;
    public Adapter_ListView(Context context, List<MyDataHolder> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.ViewTemp =new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder viewHolder;
        if (view == null) {
            view = new SlideView(context);
            viewHolder = new MyViewHolder();
            viewHolder.textView = view.findViewById(R.id.title);
            viewHolder.delete=view.findViewById(R.id.tv_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) view.getTag();
        }

        MyDataHolder itemData = dataList.get(i);
        viewHolder.textView.setText(itemData.title);
//        if (ViewTemp.contains(view) && !NoTemp.contains(i)){
//            view.findViewById(R.id.lin_root).scrollTo(0,0);
//        }else{
//            ViewTemp.add(view);
//        }

        return view;
    }

    public static class MyViewHolder {
        public TextView textView;
        public TextView delete;
    }

    public static class MyDataHolder {
        public String title;

    }
}
