package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class lstSinavsil extends BaseAdapter {
    Activity activity;
    List<sinav_tanimlamaClass> users;
    LayoutInflater inflater;

    public lstSinavsil(Activity activity, List<sinav_tanimlamaClass> users) {
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
        View view1=inflater.inflate(R.layout.sinavlist,null);
        TextView tvders=(TextView)view1.findViewById(R.id.tvders);
        TextView tvhoca=(TextView)view1.findViewById(R.id.tvhoca);
        TextView tvc=(TextView)view1.findViewById(R.id.tvbasla);
        TextView tva=(TextView)view1.findViewById(R.id.tvbitis);
        TextView tvt=(TextView)view1.findViewById(R.id.tvsure);

        tvders.setText(users.get(i).getDers());
        tvhoca.setText(users.get(i).getEkleyenhocamail());
        tvc.setText(String.valueOf(users.get(i).getBaslamaSaati()));
        tva.setText(String.valueOf(users.get(i).getBitisSaati()));
        tvt.setText(users.get(i).getSinavSuresi()+"dk.");
        return view1;
    }
}