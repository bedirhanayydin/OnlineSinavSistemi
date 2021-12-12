package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Soru_Hata_Bildir extends AppCompatActivity {
    EditText alici_email,konu_baslik,mesaj;
    Button btn_gönder;
    TextView ders_adi,soru_adi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru__hata__bildir);
        getSupportActionBar().hide();
        alici_email=(EditText) findViewById(R.id.alici_email);
        konu_baslik=(EditText) findViewById(R.id.konu_baslik);
        mesaj=(EditText) findViewById(R.id.mesaj);
        btn_gönder=(Button) findViewById(R.id.btn_gönder);ders_adi=(TextView) findViewById(R.id.ders_adi);soru_adi=(TextView) findViewById(R.id.soru_adi);
        alici_email.setEnabled(false);
        alici_email.setText(Soru_Listele_Sil.secili.getEkleyenhocamail().trim());
        ders_adi.setText("Ders= "+Soru_Listele_Sil.secili.getDers());
        soru_adi.setText("Soru Adi= "+Soru_Listele_Sil.secili.getTb_ekle_soru());

        btn_gönder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(alici_email.getText().toString())||TextUtils.isEmpty(konu_baslik.getText().toString())||TextUtils.isEmpty(mesaj.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Lütfen Bütün Boşlukları Doldurunuz!!!", Toast.LENGTH_LONG).show();

                }
                else {
                    sendMail();
                    Toast.makeText(getApplicationContext(), "Hata Bildirme Mailiniz Başarıyla " +alici_email.getText().toString()+" Mail Adresine Gönderildi.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Soru_Hata_Bildir.this, OgretmenSoruIslemSecmeEkrani.class);
                    startActivity(intent);

                }
            }
        });
    }
    private void sendMail() {
        String mail=alici_email.getText().toString().trim();
        String message=mesaj.getText().toString().trim();
        String subject=konu_baslik.getText().toString().trim();
        JavaMailAPI javaMailAPI=new JavaMailAPI(this,mail, "("+ders_adi.getText().toString()+" Adlı Ders) "+subject,"("+soru_adi.getText().toString()+") Sorusu İçin Hata Mesajınız= "+message +" \nHata Tespit Eden Öğretim Görevlisinin Mail Adresi= "+HocaGiris.mailyolla.toString());
        javaMailAPI.execute();
    }
}