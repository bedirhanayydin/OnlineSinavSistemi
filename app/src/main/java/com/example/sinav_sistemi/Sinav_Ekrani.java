package com.example.sinav_sistemi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class Sinav_Ekrani extends AppCompatActivity {
    RelativeLayout sinav_ekrani_layout;
    ProgressBar progress_geri_sayim;
    TextView label_sure_geri_sayim,label_sinav_soru;
    ImageButton btn_sinavdan_ayril;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    Button btn_cevap1, btn_cevap2, btn_cevap3, btn_cevap4;
    Button sonraki_soru,sinavi_bitir;
    public Random r = new Random();
    public String[] puan_guncelle_array = new String[3];
    public CountDownTimer gerisayim;
    public String[] ID_array;
    public String[] soru_array = new String[8];
    public String sinav_asama = "kolay_sorular";
    public int sure=5,toplam_puan=0,mevcut_soru_no=0;

    @Override
    public void onBackPressed() {
        //Telefondan geri tuşu basıldığında..
        btn_sinavdan_ayril.performClick();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav__ekrani);
        sinav_ekrani_layout = (RelativeLayout) findViewById(R.id.sinav_ekrani_layout);
        btn_sinavdan_ayril = (ImageButton) findViewById(R.id.btn_sinavdan_ayril);
        progress_geri_sayim = (ProgressBar) findViewById(R.id.progress_geri_sayim);
        label_sure_geri_sayim = (TextView) findViewById(R.id.label_sure_geri_sayim);
        label_sinav_soru = (TextView) findViewById(R.id.label_sinav_soru);
        btn_cevap1 = (Button) findViewById(R.id.btn_cevap1);
        btn_cevap2 = (Button) findViewById(R.id.btn_cevap2);
        btn_cevap3 = (Button) findViewById(R.id.btn_cevap3);
        btn_cevap4 = (Button) findViewById(R.id.btn_cevap4);
        sonraki_soru = (Button) findViewById(R.id.sonraki_soru);
        sinavi_bitir = (Button) findViewById(R.id.sinavi_bitir);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        sorulari_cek();
        ShowPopup();
        geri_sayim();

        btn_sinavdan_ayril.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Sinav_Ekrani.this)
                        .setTitle("Sınavdan Ayrılmak İstediğinize Emin misiniz?")
                        .setMessage("Mevcut Puanınız Kaydedilmeyecek.")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myRef.child("sinavlar").child(OgrenciPanel.secili.getDers()).removeValue();
                                Intent intent = new Intent(Sinav_Ekrani.this, OgrenciPanel.class);
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
        btn_cevap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cevap1.setBackgroundColor(Color.GREEN);
                btn_cevap2.setBackgroundColor(Color.GRAY);
                btn_cevap3.setBackgroundColor(Color.GRAY);
                btn_cevap4.setBackgroundColor(Color.GRAY);

                secili_button_effect(btn_cevap1);
            }
        });
        btn_cevap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cevap2.setBackgroundColor(Color.GREEN);
                btn_cevap3.setBackgroundColor(Color.GRAY);
                btn_cevap4.setBackgroundColor(Color.GRAY);
                btn_cevap1.setBackgroundColor(Color.GRAY);
                secili_button_effect(btn_cevap2);
            }
        });
        btn_cevap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cevap3.setBackgroundColor(Color.GREEN);
                btn_cevap2.setBackgroundColor(Color.GRAY);
                btn_cevap1.setBackgroundColor(Color.GRAY);
                btn_cevap4.setBackgroundColor(Color.GRAY);
                secili_button_effect(btn_cevap3);
            }
        });
        btn_cevap4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cevap4.setBackgroundColor(Color.GREEN);
                btn_cevap2.setBackgroundColor(Color.GRAY);
                btn_cevap3.setBackgroundColor(Color.GRAY);
                btn_cevap1.setBackgroundColor(Color.GRAY);
                secili_button_effect(btn_cevap4);
            }
        });
        sinavi_bitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Sinav_Ekrani.this)
                        .setTitle("Sınavı Bitirmek İstediğinize Emin misiniz?")
                        .setMessage("Mevcut Puanınız Kaydedilecektir.")
                        .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                myRef.child("sinavlar").child(OgrenciPanel.secili.getDers()).removeValue();
                                puan_kaydet();
                                Intent intent = new Intent(Sinav_Ekrani.this, OgrenciPanel.class);
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
    //SINAV BAŞLIYOR LOADİNG POP UP
    public void ShowPopup() {
        sinav_ekrani_layout.setVisibility(View.INVISIBLE);
        final Dialog dialogpopup = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialogpopup.setCanceledOnTouchOutside(false);
        dialogpopup.setContentView(R.layout.sinav_basliyor);
        getSupportActionBar().hide();


        new CountDownTimer(4950, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {

                new AlertDialog.Builder(Sinav_Ekrani.this)
                        .setCancelable(false)
                        .setTitle("Sınav Başlıyor")
                        .setMessage("Sınav Başlıyor Hazır Ol!")
                        .setPositiveButton("Başla", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                soru_yenile();
                                sinav_ekrani_layout.setVisibility(View.VISIBLE);
                                dialogpopup.dismiss();
                            }
                        })

                        .show();
                dialogpopup.hide();
                getWindow().setBackgroundDrawableResource(R.color.koyu_mavi);

            }
        }.start();
        dialogpopup.show();

    }
    //progress bar sınav süresi geri sayımı
    public void geri_sayim() {
        sure=Integer.parseInt(String.valueOf(OgrenciPanel.secili.getSinavSuresi()))*60000;
        gerisayim = new CountDownTimer(sure, 100) {
            int i = sure/100;

            @Override
            public void onTick(long millisUntilFinished) {
                label_sure_geri_sayim.setText(String.valueOf(i / 10).toString()+"sn.");
                progress_geri_sayim.setProgress((int) i * 100 / (sure / 100));
                i--;
            }
            @Override
            public void onFinish() {
                puan_kaydet();
                progress_geri_sayim.setProgress(0);
                new AlertDialog.Builder(Sinav_Ekrani.this)
                        .setCancelable(false)
                        .setTitle("Süreniz Doldu!")
                        .setMessage(".")
                        .setNegativeButton("Devam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlertDialog.Builder(Sinav_Ekrani.this)
                                        .setCancelable(false)
                                        .setTitle("Sınav Bitti")
                                        .setMessage("Toplam Puan Kazandınız.")
                                        .setNegativeButton("Ana Menüye Dön", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(Sinav_Ekrani.this, OgrenciPanel.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }
                        })
                        .show();


            }
        }.start();
    }
    //
    public void sorulari_cek() {

        //yarışma aşama içerisindeki tablonun uzunluğunu belirleniyor
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        reference.child(OgrenciPanel.secili.getDers()).child(sinav_asama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot snp : snapshot.getChildren()) {
                    i++;
                    ID_array = new String[i];
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //belirlenen uzunluğa göre ID_array dizisine soruların ID'sinin atılması
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        reference.child(OgrenciPanel.secili.getDers()).child(sinav_asama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot snp : snapshot.getChildren()) {
                    ID_array[i] = String.valueOf(snp.getKey());
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //
    public void soru_yenile() {
        int random_soru = r.nextInt(ID_array.length);
        System.out.println("asdasdasd"+ID_array.length);
        //aynı sorunun bir daha gelmeme kontrolü
        if (ID_array[random_soru].equals("0"))
            soru_yenile();
        else {
            int[] random_cevap = {4, 5, 6, 7}; //şıkların indexlerinin diziye atılması
            DatabaseReference mReferance = FirebaseDatabase.getInstance().getReference(OgrenciPanel.secili.getDers()).child(sinav_asama).child(ID_array[random_soru]);
            mReferance.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int i = 0;
                    for (DataSnapshot snp : snapshot.getChildren()) {
                        //tablodaki soru ID'yi, soruyu ve 4 şıkkı  soru_array dizisine atılması
                        soru_array[i] = snp.getValue().toString();
                        i++;
                    }
                    label_sinav_soru.setText("\n" + soru_array[3].toString() + "\n");
                    int random_cevap_id; //şıkların yerlerinin random gelmesi
                    for (int a = 0; a < 4; a++) {
                        random_cevap_id = r.nextInt(4);

                        while (true) { //2 kere aynı şıkkın gelmemesi kontrolü
                            if (random_cevap[random_cevap_id] == 0) {
                                random_cevap_id = r.nextInt(4);
                            }
                            else {
                                if (a == 0)
                                    btn_cevap1.setText(soru_array[random_cevap[random_cevap_id]]);
                                else if (a == 1)
                                    btn_cevap2.setText(soru_array[random_cevap[random_cevap_id]]);
                                else if (a == 2)
                                    btn_cevap3.setText(soru_array[random_cevap[random_cevap_id]]);
                                else if (a == 3)
                                    btn_cevap4.setText(soru_array[random_cevap[random_cevap_id]]);

                                btn_cevap1.setBackgroundColor(0xFF6200ee);
                                btn_cevap2.setBackgroundColor(0xFF6200ee);
                                btn_cevap3.setBackgroundColor(0xFF6200ee);
                                btn_cevap4.setBackgroundColor(0xFF6200ee);
                                btn_cevap1.setEnabled(true);
                                btn_cevap2.setEnabled(true);
                                btn_cevap3.setEnabled(true);
                                btn_cevap4.setEnabled(true);
                                btn_sinavdan_ayril.setEnabled(true);
                                btn_cevap1.setVisibility(View.VISIBLE);
                                btn_cevap2.setVisibility(View.VISIBLE);
                                btn_cevap3.setVisibility(View.VISIBLE);
                                btn_cevap4.setVisibility(View.VISIBLE);

                                //kullanılan şıkkın bir daha gelmemesi için değerini 0 yaptık(yukarıda kontrol ettik)
                                random_cevap[random_cevap_id] = 0;
                                break;
                            }
                        }
                    }

                    //kullanılan sorunun bir daha gelmemesi için değerini 0 yaptık(yukarıda kontrol ettik)
                    ID_array[random_soru] = "0";
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        geri_sayim();
    }
    //
    public void zorluk_kontrol() {
        mevcut_soru_no += 1;
        if (mevcut_soru_no ==Integer.parseInt(OgrenciPanel.secili.getKaçOrta())) {
            sinav_asama = "orta_sorular";
            sorulari_cek();
        } else if (mevcut_soru_no == Integer.parseInt(OgrenciPanel.secili.getKaçZor())) {
            sinav_asama = "zor_sorular";
            sorulari_cek();
        } else if (mevcut_soru_no == Integer.parseInt(OgrenciPanel.secili.getKaçOrta())+Integer.parseInt(OgrenciPanel.secili.getKaçKolay())+Integer.parseInt(OgrenciPanel.secili.getKaçZor())) {
            puan_kaydet();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            new AlertDialog.Builder(Sinav_Ekrani.this)
                    .setCancelable(false)
                    .setTitle("Bütün Soruları Bitti!")
                    .setMessage("Toplam " + toplam_puan + " Puan Kazandınız.")
                    .setNegativeButton("Ana Menüye Dön", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myRef.child("sinavlar").child(OgrenciPanel.secili.getDers()).removeValue();
                            Intent intent = new Intent(Sinav_Ekrani.this, OgrenciPanel.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }

    }
    //
    public void puan_kaydet(){
        DatabaseReference dbRef= FirebaseDatabase.getInstance().getReference().child("ogrenci_puan").child(OgrenciGiris.ogr_numarasi);
        dbRef.setValue(
                new ogrenci_puan(
                        OgrenciPanel.bilgiler_array[2],
                        OgrenciGiris.ogr_numarasi,
                        toplam_puan,
                        OgrenciPanel.secili.getDers()
                )
        );


    }
    //
    public void puan_hesapla() {
        if (sinav_asama == "kolay_sorular")
            toplam_puan += Integer.parseInt(OgrenciPanel.secili.getKolaysoruPuan());
        else if (sinav_asama == "orta_sorular")
            toplam_puan += Integer.parseInt(OgrenciPanel.secili.getOrtasoruPuan());
        else if (sinav_asama == "zor_sorular")
            toplam_puan += Integer.parseInt(OgrenciPanel.secili.getZorsoruPuan());
        System.out.println("Puan log. "+String.valueOf(toplam_puan).toString());

    }
    //
    public void secili_button_effect(Button btn) {
                sonraki_soru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (btn.getText().equals(soru_array[7])) {
                            puan_hesapla();
                        }
                        zorluk_kontrol();
                        button_zorluk_kontrol();
                    }
                });
    }
    //
    public void ShowPopupZorluk() {
        sinav_ekrani_layout.setVisibility(View.INVISIBLE);


        if (sinav_asama.equals("orta_sorular")){

            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Sorular Zorlaşıyor!")
                    .setPositiveButton("Devam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            soru_yenile();
                            sinav_ekrani_layout.setVisibility(View.VISIBLE);
                        }
                    })
                    .show();

            getWindow().setBackgroundDrawableResource(R.color.koyu_mavi);

        }
        else if (sinav_asama.equals("zor_sorular")){

            new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setTitle("Sorular Zorlaşıyor!")
                    .setPositiveButton("Devam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            soru_yenile();
                            sinav_ekrani_layout.setVisibility(View.VISIBLE);
                        }
                    })
                    .show();

            getWindow().setBackgroundDrawableResource(R.color.koyu_mavi);

        }


    }
    //
    public void button_zorluk_kontrol() {
        if (mevcut_soru_no == Integer.parseInt(OgrenciPanel.secili.getKaçKolay()) || mevcut_soru_no == Integer.parseInt(OgrenciPanel.secili.getKaçOrta())) {
            ShowPopupZorluk();
        } else if (mevcut_soru_no==Integer.parseInt(OgrenciPanel.secili.getKaçZor())) {
            ShowPopupZorluk();
        }
        else {//soruyenile
            soru_yenile();
        }

    }

}