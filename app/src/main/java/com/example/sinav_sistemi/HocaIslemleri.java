package com.example.sinav_sistemi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HocaIslemleri extends AppCompatActivity {
    EditText ad,sifre,email,brans;
    ListView lst_data;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    private List<hocaClass> user=new ArrayList<>();
    private hocaClass secili;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoca_islemleri);
        ad=(EditText) findViewById(R.id.txtad);
        sifre=(EditText) findViewById(R.id.txtsifre);
        email=(EditText) findViewById(R.id.txtemail);
        brans=(EditText) findViewById(R.id.brans);
        lst_data=(ListView) findViewById(R.id.list_data);
        mAuth=FirebaseAuth.getInstance();  // authentication tablosuna erişim

        Toolbar toolbar=findViewById(R.id.toolbar_menu);
        toolbar.setTitle("Hoca İşlemleri");
        setSupportActionBar(toolbar);
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();

        reference.child("hocalar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(user.size()>0)
                    user.clear();

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    hocaClass user1=postSnapshot.getValue(hocaClass.class);
                    user.add(user1);

                }
                listviewAdapther adapther=new listviewAdapther(HocaIslemleri.this,user);
                lst_data.setAdapter(adapther);
                lst_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        email.setEnabled(false);
                        hocaClass listedekiKullanici=(hocaClass) adapterView.getItemAtPosition(i);
                        secili=listedekiKullanici;
                        ad.setText(listedekiKullanici.getAdsoyad());
                        sifre.setText(listedekiKullanici.getSifre());
                        brans.setText(listedekiKullanici.getBrans());
                        email.setText(listedekiKullanici.getEmail());
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
            hocaEkle();
        }
        else if(item.getItemId()==R.id.guncelid){
            hocaGuncelle();
        }
        else if(item.getItemId()==R.id.silid){
            hocaSil(secili);
        }
        else if(item.getItemId()==R.id.geriid){
            Intent intent = new Intent(HocaIslemleri.this, OgrenciHocaİslemSecmeEkrani.class);
            startActivity(intent);
        }
        return true;
    }
    private void hocaSil(hocaClass secili) {
        reference.child("hocalar").child(secili.getHoca_id()).removeValue();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");
        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User account deleted.");
                                        }
                                    }
                                });

                    }
                });
        temizle();

    }

    private void hocaGuncelle() {
        hocaClass u2=new hocaClass(ad.getText().toString(),email.getText().toString(),brans.getText().toString(),sifre.getText().toString(),secili.getHoca_id());
        reference.child("hocalar").child(u2.getHoca_id()).child("adsoyad").setValue(u2.getAdsoyad());
        reference.child("hocalar").child(u2.getHoca_id()).child("email").setValue(u2.getEmail());
        reference.child("hocalar").child(u2.getHoca_id()).child("brans").setValue(u2.getBrans());
        reference.child("hocalar").child(u2.getHoca_id()).child("sifre").setValue(u2.getSifre());
        temizle();
    }

    private void hocaEkle() {
        hocaClass u1=new hocaClass(ad.getText().toString(),email.getText().toString(),brans.getText().toString(),sifre.getText().toString(),UUID.randomUUID().toString());
        reference.child("hocalar").child(u1.getHoca_id()).setValue(u1);
        String sifre1=sifre.getText().toString();
        String email1=email.getText().toString();
        mAuth.createUserWithEmailAndPassword(email1, sifre1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }
                    }

                    private void updateUI(FirebaseUser user) {
                    }
                });
        temizle();
    }
    private void temizle() {
        ad.setText("");
        sifre.setText("");brans.setText("");
        email.setText("");
        email.setEnabled(false);

    }
}