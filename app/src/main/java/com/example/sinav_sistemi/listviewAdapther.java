package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class listviewAdapther extends BaseAdapter {
    Activity activity;
    List<hocaClass> users;
    LayoutInflater inflater;

    public listviewAdapther(Activity activity, List<hocaClass> users) {
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
        View view1=inflater.inflate(R.layout.kisilist,null);
        TextView tvsoru=(TextView)view1.findViewById(R.id.tvad);
        TextView tvb=(TextView)view1.findViewById(R.id.tvemail);
        TextView tvbrans=(TextView)view1.findViewById(R.id.tvbrans);
        TextView tva=(TextView)view1.findViewById(R.id.tvsifre);

        tvsoru.setText(users.get(i).getAdsoyad());
        tva.setText(users.get(i).getSifre());
        tvbrans.setText(users.get(i).getBrans());
        tvb.setText(users.get(i).getEmail());
        return view1;
    }
}
