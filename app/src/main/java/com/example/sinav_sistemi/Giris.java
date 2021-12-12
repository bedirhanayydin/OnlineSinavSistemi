package com.example.sinav_sistemi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class    Giris extends AppCompatActivity {

    Button btnadmin,btnogretmen,btnogrenci,btncikisyap;
    @Override
    public void onBackPressed() {
        //Telefondan geri tuşu basıldığında..
        new AlertDialog.Builder(Giris.this)
                .setTitle("Uyarı")
                .setMessage("Uygulamayı Kapatmak İstediğinizden Emin Misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                {
                    @Override public void onClick (DialogInterface dialog ,int which){
                        ActivityCompat.finishAffinity(Giris.this);
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        getSupportActionBar().hide();
        btnadmin=(Button)findViewById(R.id.btnadmin);
        btnogrenci=(Button)findViewById(R.id.btnogrenci);
        btnogretmen=(Button)findViewById(R.id.btnogretmen);
        btncikisyap=(Button)findViewById(R.id.btncikisyap);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Giris.this,"Sistem Yöneticisi Giriş Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Giris.this, YoneticiGiris.class);
                startActivity(intent);
            }
        });
        btnogretmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Giris.this,"Öğretmen Giriş Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Giris.this, HocaGiris.class);
                startActivity(intent);
            }
        });
        btnogrenci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Giris.this,"Öğrenci Giriş Ekranına Yönlendiriliyorsunuz!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Giris.this, OgrenciGiris.class);
                startActivity(intent);
            }
        });
        btncikisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Giris.this)
                        .setTitle("Uyarı")
                        .setMessage("Uygulamayı Kapatmak İstediğinizden Emin Misiniz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick (DialogInterface dialog ,int which){
                                ActivityCompat.finishAffinity(Giris.this);
                            }
                        })
                        .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }
}