package com.example.sinav_sistemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class YoneticiGiris extends AppCompatActivity {
    public Button btn_giris;
    public EditText tb_kadi_giris, tb_sifre_giris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonetici_giris);
        getSupportActionBar().hide();

        btn_giris=(Button)findViewById(R.id.btn_giris);
        tb_kadi_giris=(EditText)findViewById(R.id.tb_email_giris);
        tb_sifre_giris=(EditText)findViewById(R.id.tb_sifre_giris);

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kadi=tb_kadi_giris.getText().toString();
                String sifre=tb_sifre_giris.getText().toString();
                if (TextUtils.isEmpty(kadi) || TextUtils.isEmpty(sifre))
                {
                    Toast.makeText(YoneticiGiris.this,"Lütfen tüm boşlukları eksiksiz doldurunuz!",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (kadi.equals("admin")&&sifre.equals("admin")){
                        Toast.makeText(YoneticiGiris.this,"Sistem Yöneticisi Paneline Hoşgeldiniz.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(YoneticiGiris.this, OgrenciHocaİslemSecmeEkrani.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(YoneticiGiris.this,"Yanlış Giriş!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(YoneticiGiris.this, Giris.class);
                        startActivity(intent);
                    }

                }

            }
        });
    }
}
