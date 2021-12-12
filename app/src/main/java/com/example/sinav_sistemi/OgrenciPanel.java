package com.example.sinav_sistemi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OgrenciPanel extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Button göster,sinava_git;
    ImageButton btn_oturum_kapat;
    TextView ogr_isim;
    ListView lst;
    final Context context=this;
    public FirebaseAuth mAuth;
    GridView grid_view_sinav;
    Grid_Adapter_Sinav_Sirala Grid_Adapter_Sinav_Sirala;
    ArrayList<sinav_tanimlamaClass> sinavliste;

    private List<sinav_tanimlamaClass> sinav=new ArrayList<>();
    public static sinav_tanimlamaClass secili;
    public ProgressDialog Progress;
    public String user_id;
    public static String[] bilgiler_array = new String[4];
    public  static String ogrno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrenci_panel);
        lst = (ListView) findViewById(R.id.list_view);
        göster = (Button) findViewById(R.id.göster);
        btn_oturum_kapat=(ImageButton)findViewById(R.id.btn_oturum_kapat);
        ogr_isim=(TextView)findViewById(R.id.label_kullanici_isim);
        sinava_git=(Button)findViewById(R.id.btn_sinava_git);
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();

       ogrno= OgrenciGiris.ogr_numarasi.toString();
        btn_oturum_kapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(OgrenciPanel.this)
                        .setTitle("Uyarı")
                        .setMessage("Oturumu Kapatmak İstediğinizden Emin Misiniz?")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                        {
                            @Override public void onClick (DialogInterface dialog ,int which){
                                mAuth=FirebaseAuth.getInstance();
                                mAuth.getInstance().signOut();
                                Intent intent = new Intent(OgrenciPanel.this, Giris.class);
                                startActivity(intent);
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

        //mevcut kullanıcının ID'sine göre bilgilerini yazdırma
        DatabaseReference IDReferance = FirebaseDatabase.getInstance().getReference("ogrenciler").child(OgrenciGiris.ogr_numarasi);
        IDReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //snapshot veritabanında bilgileri listelemek için kullanılır
                int i = 0;
                for (DataSnapshot snp : snapshot.getChildren()) {
                    bilgiler_array[i] = snp.getValue().toString(); //bilgiler arraya kullanıcının verilerini doldurma
                    i++;
                    if (i==4){
                        ogr_isim.setText(OgrenciGiris.ogr_numarasi+"-"+bilgiler_array[2]);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        göster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child("sinavlar").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (sinav.size() > 0)
                            sinav.clear();

                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            sinav_tanimlamaClass ogr1 = postSnapshot.getValue(sinav_tanimlamaClass.class);
                            sinav.add(ogr1);
                        }
                        Grid_Adapter_Sinav_Sirala adapther = new Grid_Adapter_Sinav_Sirala(OgrenciPanel.this, sinav);
                        lst.setAdapter(adapther);
                        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                sinav_tanimlamaClass listedekiKullanici = (sinav_tanimlamaClass) adapterView.getItemAtPosition(i);
                                secili = listedekiKullanici;
                                System.out.println("log."+secili.getDers());
                                new AlertDialog.Builder(OgrenciPanel.this)
                                        .setTitle("Uyarı")
                                        .setMessage(secili.getDers()+" Sınavına Girmek İstermisiniz?")
                                        .setPositiveButton("Evet", new DialogInterface.OnClickListener()
                                        {
                                            @Override public void onClick (DialogInterface dialog ,int which){
                                                Intent intent = new Intent(OgrenciPanel.this, Sinav_Ekrani.class);
                                                startActivity(intent);
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
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}
