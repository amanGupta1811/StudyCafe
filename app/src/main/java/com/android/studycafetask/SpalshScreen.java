package com.android.studycafetask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SpalshScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currUser==null){
                    startActivity(new Intent(SpalshScreen.this, signIn.class));
                }
                else{
                    startActivity(new Intent(SpalshScreen.this, MainActivity.class));
                }
                finish();
            }
        },1000);
    }
}