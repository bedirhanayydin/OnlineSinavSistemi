package com.example.sinav_sistemi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class SoruEkle extends AppCompatActivity {
    public Spinner spinner_zorluk,spinner_ders;
    public Button btn_soru_ekle;
    public EditText tb_ekle_soru,tb_soru_dogru_cevap,tb_soru_cevap1,tb_soru_cevap2,tb_soru_cevap3;
    public String soru_id,user_id;
    static String userLogString=HocaGiris.mailyolla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_ekle);
        setTitle("Soru Ekle");
        btn_soru_ekle=(Button)findViewById(R.id.btn_soru_ekle);
        tb_ekle_soru=(EditText)findViewById(R.id.tb_ekle_soru);
        tb_soru_dogru_cevap=(EditText)findViewById(R.id.tb_soru_dogru_cevap);
        tb_soru_cevap1=(EditText)findViewById(R.id.tb_soru_cevap1);
        tb_soru_cevap2=(EditText)findViewById(R.id.tb_soru_cevap2);
        tb_soru_cevap3=(EditText)findViewById(R.id.tb_soru_cevap3);

        spinner_zorluk=(Spinner)findViewById(R.id.spinner_zorluk);
        String[] arraySpinner = new String[] {
                "kolay", "orta", "zor"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_zorluk.setAdapter(adapter);

        spinner_ders=(Spinner)findViewById(R.id.spinner_ders);
        String[] arraySpinnerDers = new String[] {
                "Bilgisayar Programlama", "Veri Yapıları", "Olasılık ve İstatistiğe Giriş","Nesneye Yönelik Programlama",
                "İşletim Sistemleri","Mikroişlemciler","Web Programlama","Veri Tabanı Yönetimi","Programlama Dilleri","Yapay Zeka",
                "Biçimsel Diller ve Otomata Teori","Java Programlamaya Giriş","Derleyici Tasarımı","İleri Java Programlama","Veri Madenciliğine Giriş","Makine Öğrenmesi","Yapay Sinir Ağları"
        };
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnerDers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ders.setAdapter(adapter2);

        btn_soru_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tb_ekle_soru.getText())||TextUtils.isEmpty(tb_soru_cevap1.getText()) || TextUtils.isEmpty(tb_soru_cevap2.getText()) || TextUtils.isEmpty(tb_soru_cevap3.getText()) || TextUtils.isEmpty(tb_soru_dogru_cevap.getText()))
                {
                    Toast.makeText(SoruEkle.this,"Lütfen tüm boşlukları eksiksiz doldurunuz!",Toast.LENGTH_SHORT).show();
                }
                else{
                    String ders=spinner_ders.getSelectedItem().toString();
                    String zorluk=spinner_zorluk.getSelectedItem().toString()+"_sorular";
                    //Databaseimizi tanımladık ve spinnerdan tablo ismimizi aldık.
                    DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child(ders).child(zorluk);
                    soru_id= UUID.randomUUID().toString();
                    dbRef.child(soru_id).setValue(
                            new soruClass(
                                    userLogString,
                                    spinner_ders.getSelectedItem().toString(),
                                    tb_ekle_soru.getText().toString(),
                                    tb_soru_dogru_cevap.getText().toString(),
                                    tb_soru_cevap1.getText().toString(),
                                    tb_soru_cevap2.getText().toString(),
                                    tb_soru_cevap3.getText().toString(),
                                    soru_id
                            )
                    );
                    Toast.makeText(SoruEkle.this,"Soru Başarıyla Eklendi!",Toast.LENGTH_SHORT).show();
                    tb_ekle_soru.setText(" ");
                    tb_soru_cevap3.setText(" ");tb_soru_dogru_cevap.setText(" ");
                    tb_soru_cevap2.setText(" ");tb_soru_cevap1.setText(" ");

                }
            }
        });


    }
}