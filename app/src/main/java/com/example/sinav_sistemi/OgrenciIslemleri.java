package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OgrenciIslemleri extends AppCompatActivity {
        EditText ad,sifre,bolum,numara;
        ListView lst_data;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference reference;
        private List<ogrenciClass> ogr=new ArrayList<>();
        private ogrenciClass secili;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ogrenci_islemleri);
            ad=(EditText) findViewById(R.id.txtad);
            sifre=(EditText) findViewById(R.id.txtsifre);
            bolum=(EditText) findViewById(R.id.txtbölüm);
            numara=(EditText) findViewById(R.id.txtnumara);
            lst_data=(ListView) findViewById(R.id.list_data);
            Toolbar toolbar=findViewById(R.id.toolbar_menu);
            toolbar.setTitle("Öğrenci İşlemleri");
            setSupportActionBar(toolbar);

            FirebaseApp.initializeApp(this);
            firebaseDatabase=FirebaseDatabase.getInstance();
            reference=firebaseDatabase.getReference();
            reference.child("ogrenciler").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(ogr.size()>0)
                        ogr.clear();

                    for(DataSnapshot postSnapshot : snapshot.getChildren()){
                        ogrenciClass ogr1=postSnapshot.getValue(ogrenciClass.class);
                        ogr.add(ogr1);
                    }
                    listviewAdapther2 adapther=new listviewAdapther2(OgrenciIslemleri.this,ogr);
                    lst_data.setAdapter(adapther);
                    lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            numara.setEnabled(false);
                            ogrenciClass listedekiKullanici=(ogrenciClass) adapterView.getItemAtPosition(i);
                            secili=listedekiKullanici;
                            ad.setText(listedekiKullanici.getOgradsoyad());
                            sifre.setText(listedekiKullanici.getSifre());
                            bolum.setText(listedekiKullanici.getBolum());
                            numara.setText(listedekiKullanici.getNumara());
                        }

                    });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu,menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId()==R.id.ekleid){
                OgrenciEkle();
            }
            else if(item.getItemId()==R.id.guncelid){
                OgrenciGuncelle();
            }
            else if(item.getItemId()==R.id.silid){
                OgrenciSil(secili);
            }
            else if(item.getItemId()==R.id.geriid){
                Intent intent = new Intent(OgrenciIslemleri.this, OgrenciHocaİslemSecmeEkrani.class);
                startActivity(intent);
            }
            return true;
        }
        private void OgrenciSil(ogrenciClass secili) {
            reference.child("ogrenciler").child(secili.getNumara ()).removeValue();
            temizle();
        }

        private void OgrenciGuncelle() {
            ogrenciClass u2=new ogrenciClass(ad.getText().toString(),bolum.getText().toString(),numara.getText().toString(),sifre.getText().toString());
            reference.child("ogrenciler").child(u2.getNumara()).child("ogradsoyad").setValue(u2.getOgradsoyad());
            reference.child("ogrenciler").child(u2.getNumara()).child("bolum").setValue(u2.getBolum());
            reference.child("ogrenciler").child(u2.getNumara()).child("numara").setValue(u2.getNumara());
            reference.child("ogrenciler").child(u2.getNumara()).child("sifre").setValue(u2.getSifre());
            temizle();

        }

        private void OgrenciEkle() {
            ogrenciClass u1=new ogrenciClass(ad.getText().toString(),bolum.getText().toString(),numara.getText().toString(),sifre.getText().toString());
            reference.child("ogrenciler").child(u1.getNumara()).setValue(u1);
            temizle();
        }

        private void temizle() {
            ad.setText("");
            sifre.setText("");
            bolum.setText("");
            numara.setText("");
            numara.setEnabled(true);

        }
}