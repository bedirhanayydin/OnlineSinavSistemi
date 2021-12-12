package com.example.sinav_sistemi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class Soru_Listele_Sil extends AppCompatActivity {
    EditText hoca,soru,cevap,sik1,sik2,sik3;
    Spinner ders,zorluk;
    ListView lst_data;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private List<soruClass> user=new ArrayList<>();
    public static soruClass secili;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru__listele__sil);
        hoca=(EditText) findViewById(R.id.hoca);
        ders=(Spinner) findViewById(R.id.spinnerders);
        zorluk=(Spinner) findViewById(R.id.spinnerzorluk);
        soru=(EditText) findViewById(R.id.soru);
        cevap=(EditText) findViewById(R.id.cevap);
        sik1=(EditText) findViewById(R.id.sik1);
        lst_data=(ListView) findViewById(R.id.list_data);
        sik2=(EditText) findViewById(R.id.sik2);
        sik3=(EditText) findViewById(R.id.sik3);


        Toolbar toolbar=findViewById(R.id.toolbar_menu);
        toolbar.setTitle("Görüntüle Sil");
        toolbar.setTitleTextColor(Color.RED);
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        String[] arraySpinner = new String[] {
                "kolay", "orta", "zor"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zorluk.setAdapter(adapter);

        String[] arraySpinnerDers = new String[] {
                "Bilgisayar Programlama", "Veri Yapıları", "Olasılık ve İstatistiğe Giriş","Nesneye Yönelik Programlama",
                "İşletim Sistemleri","Mikroişlemciler","Web Programlama","Veri Tabanı Yönetimi","Programlama Dilleri","Yapay Zeka",
                "Biçimsel Diller ve Otomata Teori","Java Programlamaya Giriş","Derleyici Tasarımı","İleri Java Programlama","Veri Madenciliğine Giriş","Makine Öğrenmesi","Yapay Sinir Ağları"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerDers);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ders.setAdapter(adapter2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.silid) {
            OgrenciSil(secili);
        } else if (item.getItemId() == R.id.list) {
            String ders1 = ders.getSelectedItem().toString();
            String zorluk1 = zorluk.getSelectedItem().toString() + "_sorular";
            reference.child(ders1).child(zorluk1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (user.size() > 0)
                        user.clear();

                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        soruClass user1 = postSnapshot.getValue(soruClass.class);

                        user.add(user1);

                    }
                    listviewAdapther3 adapther = new listviewAdapther3(Soru_Listele_Sil.this, user);
                    lst_data.setAdapter(adapther);
                    lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            soruClass listedekiKullanici = (soruClass) adapterView.getItemAtPosition(i);
                            secili = listedekiKullanici;
                            hoca.setText(listedekiKullanici.getEkleyenhocamail());
                            soru.setText(listedekiKullanici.getTb_ekle_soru());
                            cevap.setText(listedekiKullanici.getTb_soru_dogru_cevap());
                            sik1.setText(listedekiKullanici.getTb_soru_cevap1());
                            sik2.setText(listedekiKullanici.getTb_soru_cevap2());
                            sik3.setText(listedekiKullanici.getTb_soru_cevap3());

                        }

                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else if (item.getItemId()==R.id.hatabildir){
            if (secili!=null){
                System.out.println(secili.getEkleyenhocamail());
                Intent intent = new Intent(Soru_Listele_Sil.this, Soru_Hata_Bildir.class);
                startActivity(intent);
            }

            else
                Toast.makeText(getApplicationContext(),"Lütfen hata bildirmek istediğiniz soruyu seçiniz!!!",Toast.LENGTH_LONG).show();

        }

        else if (item.getItemId() == R.id.geriid) {
            Intent intent = new Intent(Soru_Listele_Sil.this, OgretmenSoruIslemSecmeEkrani.class);
            startActivity(intent);
        }
        return true;

    }

    private void OgrenciSil(soruClass secili) {
        String ders2=ders.getSelectedItem().toString();
        String zorluk2=zorluk.getSelectedItem().toString()+"_sorular";
        reference.child(ders2).child(zorluk2).child(secili.getSoru_id()).removeValue();
        temizle();
    }
    private void temizle(){
        hoca.setText("");
        soru.setText("");
        cevap.setText("");
        sik1.setText("");
        sik2.setText("");
        sik3.setText("");
    }

}