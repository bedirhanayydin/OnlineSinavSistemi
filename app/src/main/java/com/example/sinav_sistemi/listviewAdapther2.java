package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class listviewAdapther2 extends BaseAdapter {
    Activity activity;
    List<ogrenciClass> users;
    LayoutInflater inflater;

    public listviewAdapther2(Activity activity, List<ogrenciClass> users) {
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
        View view1=inflater.inflate(R.layout.ogrencilist,null);
        TextView tvsoru=(TextView)view1.findViewById(R.id.ad);
        TextView tvb=(TextView)view1.findViewById(R.id.bolum);
        TextView tvc=(TextView)view1.findViewById(R.id.numara);
        TextView tva=(TextView)view1.findViewById(R.id.sifre);

        tvsoru.setText(users.get(i).getOgradsoyad());
        tva.setText(users.get(i).getSifre());
        tvb.setText(users.get(i).getBolum());
        tvc.setText(users.get(i).getNumara());
        return view1;
    }
}