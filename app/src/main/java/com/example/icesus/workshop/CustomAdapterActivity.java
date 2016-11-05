package com.example.icesus.workshop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CustomAdapterActivity extends BaseAdapter {

    Context mContext;

    public CustomAdapterActivity (Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder myHolder = null;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            //ตอนเริ่มแสดงผล row แรกๆ
            convertView = mInflater.inflate(R.layout.activity_row_list, null);
            myHolder = new ViewHolder();
            convertView.setTag(myHolder);
        } else {
            // ตอนที่เริ่ม recycle view แล้ว }
            myHolder = (ViewHolder)convertView.getTag();
        }
        return convertView;
    }

    public class ViewHolder{
        //ประกาศว่าภายใน View จะมี widget อะไร ชื่ออะไรบ้าง
    }
}
