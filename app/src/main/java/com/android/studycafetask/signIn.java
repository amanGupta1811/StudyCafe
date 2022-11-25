package com.android.studycafetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signIn extends AppCompatActivity {

    EditText emailSignIn, passwordSignIn;
    Button buttonSignIn;
    TextView btnSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailSignIn = findViewById(R.id.emalIdSignIn);
        passwordSignIn = findViewById(R.id.passWordSignIn);
        buttonSignIn = findViewById(R.id.buttonSignIn);
        btnSignUp = findViewById(R.id.signUpbtn);

        buttonSignIn.setOnClickListener((v)->signInUser());
        btnSignUp.setOnClickListener((v)->
                startActivity(new Intent(signIn.this, signUp.class))
                );
    }

    void signInUser(){

        String emailStr = emailSignIn.getText().toString();
        String passwordStr = passwordSignIn.getText().toString();

        boolean isValidate = validateData(emailStr, passwordStr);

        if(!isValidate){
            return;
        }

        signinAccountInFirebase(emailStr, passwordStr);

    }

    void signinAccountInFirebase(String emailStr, String passwordStr){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailStr,passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
//                    if(firebaseAuth.getCurrentUser()){
                        startActivity(new Intent(signIn.this,MainActivity.class));
                        finish();
                   // }
                    //else {
                     //   utility.showToast(signIn.this,"Email not verified, please verify your email");
                    //}
                }
                else{
                    utility.showToast(signIn.this,task.getException().getLocalizedMessage());
                }

            }
        });

    }

    boolean validateData(String emailStr, String passwordStr){

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            emailSignIn.setError("Email is invalid");
            return false;
        }
        if(passwordStr.length()<=6){
            passwordSignIn.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}