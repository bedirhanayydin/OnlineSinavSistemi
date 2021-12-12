package com.example.sinav_sistemi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Grid_Adapter_Sinav_Sirala extends BaseAdapter {
    Activity activity;
    List<sinav_tanimlamaClass> sinav;
    LayoutInflater inflater;
    Context context;

    public Grid_Adapter_Sinav_Sirala(Activity activity, List<sinav_tanimlamaClass> sinav) {
        this.context=activity;
        this.sinav = sinav;
        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return sinav.size();
    }

    @Override
    public Object getItem(int i) {
        return sinav.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View view1=inflater.inflate(R.layout.siralama_cardview,null);
        TextView dersAdi=(TextView)view1.findViewById(R.id.dersAdi);
        TextView tva=(TextView)view1.findViewById(R.id.sinavekleyenhocamail);
        TextView tvb=(TextView)view1.findViewById(R.id.sinavBaslamaSaati);
        TextView tvc=(TextView)view1.findViewById(R.id.sinavBitisSaati);
        TextView tvsinavsüresi=(TextView)view1.findViewById(R.id.sinavSüresi);


        dersAdi.setText("Ders : "+String.valueOf(sinav.get(i).getDers()));
        tva.setText("Ekleyen Hoca : "+String.valueOf(sinav.get(i).getEkleyenhocamail()));
        tvb.setText("Baslama Saati : "+String.valueOf(sinav.get(i).getBaslamaSaati()));
        tvc.setText("Bitiş Saati : "+String.valueOf(sinav.get(i).getBitisSaati()));
        tvsinavsüresi.setText("Sınav Süresi : "+String.valueOf(sinav.get(i).getSinavSuresi())+"dk.");

        return view1;
    }
}
