package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class listviewAdapther3  extends BaseAdapter {
    Activity activity;
    List<soruClass> users;
    LayoutInflater inflater;

    public listviewAdapther3(Activity activity, List<soruClass> users) {
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
        View view1=inflater.inflate(R.layout.sorulist,null);
        TextView tvsoru=(TextView)view1.findViewById(R.id.tvhoca);
        TextView tvc=(TextView)view1.findViewById(R.id.tvsoru);
        TextView tvf=(TextView)view1.findViewById(R.id.tvcevap);
        TextView tvg=(TextView)view1.findViewById(R.id.tvsik1);
        TextView tvh=(TextView)view1.findViewById(R.id.tvsik2);
        TextView tvı=(TextView)view1.findViewById(R.id.tvsik3);

        tvsoru.setText(users.get(i).getEkleyenhocamail());
        tvc.setText(users.get(i).getTb_ekle_soru());
        tvf.setText(users.get(i).getTb_soru_dogru_cevap());
        tvg.setText(users.get(i).getTb_soru_cevap1());
        tvh.setText(users.get(i).getTb_soru_cevap2());
        tvı.setText(users.get(i).getTb_soru_cevap3());

        return view1;
    }
}