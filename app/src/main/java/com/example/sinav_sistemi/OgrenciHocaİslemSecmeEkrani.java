package com.example.sinav_sistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OgrenciHocaİslemSecmeEkrani extends AppCompatActivity {
    Button btnhoca,btnogrencii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci_hoca_secme_ekrani);

        getSupportActionBar().hide();
        btnhoca=(Button)findViewById(R.id.btnhoca);
        btnogrencii=(Button)findViewById(R.id.btnogrencii);

        btnhoca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgrenciHocaİslemSecmeEkrani.this,"Öğretmen İşlem Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgrenciHocaİslemSecmeEkrani.this, HocaIslemleri.class);
                startActivity(intent);
            }
        });
        btnogrencii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OgrenciHocaİslemSecmeEkrani.this,"Öğrenci İşlem Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OgrenciHocaİslemSecmeEkrani.this, OgrenciIslemleri.class);
                startActivity(intent);
            }
        });
    }
}