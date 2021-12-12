package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OgretmenSoruIslemSecmeEkrani extends AppCompatActivity {
    Button btnsoruekle,btnsorularıgörüntüle,btnhocasinavtanimlama,btnsınavsil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogretmen_soru_islem_secme_ekrani);

        getSupportActionBar().hide();
        btnsoruekle=(Button)findViewById(R.id.btnsoruekle);
        btnsorularıgörüntüle=(Button)findViewById(R.id.btnsorularıgörüntüle);
        btnhocasinavtanimlama=(Button)findViewById(R.id.btnSınavtanımla);
        btnsınavsil=(Button)findViewById(R.id.btnSınavsil);

        btnsoruekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgretmenSoruIslemSecmeEkrani.this,"Soru Ekleme Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgretmenSoruIslemSecmeEkrani.this, SoruEkle.class);
                startActivity(intent);
            }
        });
        btnsorularıgörüntüle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgretmenSoruIslemSecmeEkrani.this,"Soruları Görüntüle Sil Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgretmenSoruIslemSecmeEkrani.this, Soru_Listele_Sil.class);
                startActivity(intent);
            }
        });
        btnhocasinavtanimlama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgretmenSoruIslemSecmeEkrani.this,"Sınav Tanımlama Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgretmenSoruIslemSecmeEkrani.this, HocaSinavTanimlama.class);
                startActivity(intent);
            }
        });
        btnsınavsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgretmenSoruIslemSecmeEkrani.this,"Sınav Listele Sil Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgretmenSoruIslemSecmeEkrani.this, Sinav_Listele_Sil.class);
                startActivity(intent);
            }
        });
    }
}