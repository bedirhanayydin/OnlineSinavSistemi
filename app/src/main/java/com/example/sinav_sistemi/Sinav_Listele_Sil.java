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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sinav_Listele_Sil extends AppCompatActivity {

    ListView lst_data;
    EditText mail, ders;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private List<sinav_tanimlamaClass> sinav = new ArrayList<>();
    private sinav_tanimlamaClass secili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav__listele__sil);
        lst_data = (ListView) findViewById(R.id.list_data);
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        mail = (EditText) findViewById(R.id.mailhoca);
        ders = (EditText) findViewById(R.id.ders);
        toolbar.setTitle("Sınav Silme İşlemi");
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.silid) {
            sinavSil(secili);
        } else if (item.getItemId() == R.id.list) {
            reference.child("sinavlar").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (sinav.size() > 0)
                        sinav.clear();

                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        sinav_tanimlamaClass user1 = postSnapshot.getValue(sinav_tanimlamaClass.class);

                        sinav.add(user1);

                    }
                    lstSinavsil adapther = new lstSinavsil(Sinav_Listele_Sil.this, sinav);
                    lst_data.setAdapter(adapther);
                    lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            sinav_tanimlamaClass listedekiKullanici = (sinav_tanimlamaClass) adapterView.getItemAtPosition(i);
                            secili = listedekiKullanici;
                            mail.setText(listedekiKullanici.getEkleyenhocamail());
                            ders.setText(listedekiKullanici.getDers());

                        }

                    });


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else if (item.getItemId() == R.id.geriid) {
            Intent intent = new Intent(Sinav_Listele_Sil.this, OgretmenSoruIslemSecmeEkrani.class);
            startActivity(intent);
        }
        return true;

    }

    private void sinavSil(sinav_tanimlamaClass secili) {
        reference.child("sinavlar").child(secili.getDers()).removeValue();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mail.setText("");ders.setText("");

    }
}