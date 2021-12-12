package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class listviewAdapther4 extends BaseAdapter {
    Activity activity;
    List<sinav_tanimlamaClass> users;
    LayoutInflater inflater;

    public listviewAdapther4(Activity activity, List<sinav_tanimlamaClass> users) {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater=(LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1=inflater.inflate(R.layout.kartlist,null);
        TextView tvdersadi=(TextView)view1.findViewById(R.id.tvsinavadi);


        tvdersadi.setText(users.get(i).getDers());
        return view1;
    }
}
