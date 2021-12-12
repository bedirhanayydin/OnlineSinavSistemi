package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OgrenciGiris extends AppCompatActivity {
    EditText numara,sifre;
    Button giris;
    private ogrenciClass ogr;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    static String ogr_numarasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci_giris);
        getSupportActionBar().hide();
        numara=(EditText) findViewById(R.id.et_numara);
        sifre=(EditText) findViewById(R.id.et_sifre);
        giris=(Button) findViewById(R.id.btn_giris);
        ogr=new ogrenciClass();
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference().child("ogrenciler");
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numara1=numara.getText().toString();
                String sifre1=sifre.getText().toString();
                if(TextUtils.isEmpty(sifre1) || TextUtils.isEmpty(numara1)) {
                    Toast.makeText(getApplicationContext(),"Lütfen tüm boşlukları eksiksiz doldurunuz!",Toast.LENGTH_LONG).show();
                }
                else {
                            ref.child(numara1).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    ogrenciClass ogrenci = snapshot.getValue(ogrenciClass.class);
                                    if (sifre1.equals(ogrenci.getSifre())) {
                                        Toast.makeText(getApplicationContext(), "Başarılı Giriş", Toast.LENGTH_LONG).show();
                                        ogr_numarasi=numara1;
                                        Intent intent = new Intent(OgrenciGiris.this, OgrenciPanel.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Şifre Yanlış", Toast.LENGTH_LONG).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
             });
    }
}