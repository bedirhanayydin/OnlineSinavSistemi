package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HocaGiris extends AppCompatActivity {
    public Button btn_giris;
    public EditText tb_email_giris, tb_sifre_giris;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    static String mailyolla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_hoca_giris);getSupportActionBar().hide();
        btn_giris=(Button)findViewById(R.id.btn_giris);tb_email_giris=(EditText)findViewById(R.id.tb_email_giris);tb_sifre_giris=(EditText)findViewById(R.id.tb_sifre_giris);
        mAuth=FirebaseAuth.getInstance();  // authentication tablosuna erişim
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tb_email_giris.getText()) || TextUtils.isEmpty(tb_sifre_giris.getText()))
                {Toast.makeText(HocaGiris.this,"Lütfen tüm boşlukları eksiksiz doldurunuz!",Toast.LENGTH_SHORT).show();}
                else{ signIn(tb_email_giris.getText().toString(),tb_sifre_giris.getText().toString());}
            }
        });
    }
    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            mailyolla=email;
                            Intent intent = new Intent(HocaGiris.this, OgretmenSoruIslemSecmeEkrani.class);
                            Toast.makeText(HocaGiris.this, "Öğretmen Girişi Başarılı Yönlendiriliyorsunuz!",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(HocaGiris.this, mailyolla,
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(HocaGiris.this, "Öğretmen Girişi Başarısız.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }

                    private void updateUI(FirebaseUser user) {
                    }
                });
        // [END sign_in_with_email]
    }
}
