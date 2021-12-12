package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HocaSinavTanimlama extends AppCompatActivity {
    Spinner spinner_ders;
    NumberPicker timePickerBaslama,timePickerBitis;
    EditText txtkaçKolay,txtkaçOrta,txtkaçZor,kolaysoruPuan,ortasoruPuan,zorsoruPuan,sinavSuresi;
    Button btn_sınav_ekle;
    static String userLogString=HocaGiris.mailyolla;
    public int toplam=0;
    public String[] ID_array_kolaysoru;
    public String[] ID_array_ortasoru;
    public String[] ID_array_zorsoru;

    int toplamkolaysoru=0,toplamortasoru=0,toplamzorsoru=0;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    int i=0,k=0,orta=0,zor=0;
    public int topkol,toport,topzor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoca_sinav_tanimlama);
        setTitle("Sınav Tanımlama Ekranı");
        timePickerBaslama=(NumberPicker)findViewById(R.id.timePickerBaslama);
        timePickerBitis=(NumberPicker)findViewById(R.id.timePickerBitis);
        txtkaçKolay=(EditText)findViewById(R.id.txtkaçKolay);
        sinavSuresi=(EditText)findViewById(R.id.sinavSuresi);
        txtkaçOrta=(EditText)findViewById(R.id.txtkaçOrta);
        txtkaçZor=(EditText)findViewById(R.id.txtkaçZor);
        kolaysoruPuan=(EditText)findViewById(R.id.kolaysoruPuan);
        ortasoruPuan=(EditText)findViewById(R.id.ortasoruPuan);
        zorsoruPuan=(EditText)findViewById(R.id.zorsoruPuan);
        btn_sınav_ekle=(Button)findViewById(R.id.btn_sınav_ekle);


        timePickerBaslama.setMaxValue(24);
        timePickerBaslama.setMinValue(0);

        timePickerBitis.setMaxValue(24);
        timePickerBitis.setMinValue(0);


        spinner_ders=(Spinner)findViewById(R.id.spinner_ders);
        String[] arraySpinnerDers = new String[] {
                "Bilgisayar Programlama", "Veri Yapıları", "Olasılık ve İstatistiğe Giriş","Nesneye Yönelik Programlama",
                "İşletim Sistemleri","Mikroişlemciler","Web Programlama","Veri Tabanı Yönetimi","Programlama Dilleri","Yapay Zeka",
                "Biçimsel Diller ve Otomata Teori","Java Programlamaya Giriş","Derleyici Tasarımı","İleri Java Programlama","Veri Madenciliğine Giriş","Makine Öğrenmesi","Yapay Sinir Ağları"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(arraySpinnerDers));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, plantsList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ders.setAdapter(adapter2);

        btn_sınav_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txtkaçOrta.getText()) || TextUtils.isEmpty(txtkaçKolay.getText()) || TextUtils.isEmpty(txtkaçZor.getText())|| TextUtils.isEmpty(kolaysoruPuan.getText())|| TextUtils.isEmpty(ortasoruPuan.getText())|| TextUtils.isEmpty(zorsoruPuan.getText())||TextUtils.isEmpty(sinavSuresi.getText()))
                {
                    Toast.makeText(HocaSinavTanimlama.this,"Bütün Boşlukları Doldurunuz.Lütfen Dikkat Ediniz!",Toast.LENGTH_SHORT).show();
                }
                else if (timePickerBaslama.getValue()<timePickerBitis.getValue()&&((timePickerBitis.getValue()-timePickerBaslama.getValue())*60)>Integer.parseInt(sinavSuresi.getText().toString())){
                    Integer kackolay=Integer.valueOf(txtkaçKolay.getText().toString().trim());
                    Integer kacorta=Integer.valueOf(txtkaçOrta.getText().toString().trim());
                    Integer kaczor=Integer.valueOf(txtkaçZor.getText().toString().trim());
                    Integer kolayPuan=Integer.valueOf(kolaysoruPuan.getText().toString().trim());
                    Integer ortaPuan=Integer.valueOf(ortasoruPuan.getText().toString().trim());
                    Integer zorPuan=Integer.valueOf(zorsoruPuan.getText().toString().trim());

                    if ((kackolay*kolayPuan)+(ortaPuan*kacorta)+(zorPuan*kaczor)==100)
                    {
                            String ders=spinner_ders.getSelectedItem().toString();
                            //Databaseimizi tanımladık ve spinnerdan tablo ismimizi aldık.
                            DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("sinavlar").child(ders);
                            dbRef.setValue(
                                    new sinav_tanimlamaClass(
                                            userLogString,
                                            spinner_ders.getSelectedItem().toString(),
                                            timePickerBaslama.getValue(),
                                            timePickerBitis.getValue(),
                                            sinavSuresi.getText().toString(),
                                            txtkaçKolay.getText().toString(),
                                            txtkaçOrta.getText().toString(),
                                            txtkaçZor.getText().toString(),
                                            kolaysoruPuan.getText().toString(),
                                            ortasoruPuan.getText().toString(),
                                            zorsoruPuan.getText().toString()
                                    )
                            );
                            Toast.makeText(HocaSinavTanimlama.this,"Sınav Başarıyla Tanımlandı!",Toast.LENGTH_SHORT).show();
                            plantsList.remove(spinner_ders.getSelectedItem());
                            adapter2.notifyDataSetChanged();
                        temizle();

                    }
                    else {
                        Toast.makeText(HocaSinavTanimlama.this,"Toplam Puan 100 Olmalıdır Kontrol Ediniz!!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HocaSinavTanimlama.this, OgretmenSoruIslemSecmeEkrani.class);
                        startActivity(intent);
                    }
                    temizle();
                }
                else {
                    Toast.makeText(HocaSinavTanimlama.this,"Başlama Saati Bitiş Saatinden Küçük Olmalıdır ve Sınav Süresini Kontrol Ediniz!!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HocaSinavTanimlama.this, OgretmenSoruIslemSecmeEkrani.class);
                    startActivity(intent);
                    temizle();
                }
            }
        });
    }
    public  void temizle()
    {
        txtkaçKolay.setText(" ");zorsoruPuan.setText("");
        txtkaçOrta.setText(" ");kolaysoruPuan.setText(" ");
        ortasoruPuan.setText(" ");txtkaçZor.setText(" ");sinavSuresi.setText("");
    }
}