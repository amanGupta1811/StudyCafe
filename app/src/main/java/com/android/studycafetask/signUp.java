package com.android.studycafetask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity {

    EditText email, password, confPassword;
    Button btnCreate;
    TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.emalId);
        password = findViewById(R.id.passWord);
        confPassword = findViewById(R.id.confirmPass);
        btnCreate = findViewById(R.id.createBtn);
        btnSignIn = findViewById(R.id.signInbtn);

        btnCreate.setOnClickListener(v->createAccount());
        btnSignIn.setOnClickListener(v->finish());

    }

    void createAccount(){

        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();
        String confPasswordStr = confPassword.getText().toString();

        boolean isValidate = validateData(emailStr, passwordStr, confPasswordStr);

        if(!isValidate){
            return;
        }

        createAccountInFireBase(emailStr, passwordStr);
    }

    void createAccountInFireBase(String emailStr, String passwordStr){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(signUp.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //utility.showToast(signUp.this, "Succesfully create account, check your email to verify");
                            Toast.makeText(signUp.this, "Succesfully create account", Toast.LENGTH_LONG).show();
                            firebaseAuth.getCurrentUser();
                            firebaseAuth.signOut();
                            finish();
                        }
                        else{
                            utility.showToast(signUp.this, task.getException().getLocalizedMessage());
                        }
                    }
                }
        );
    }

    boolean validateData(String emailStr, String passwordStr, String confPasswordStr){

        if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.setError("Email is invalid");
            return false;
        }
        if(passwordStr.length()<=6){
            password.setError("Password length is invalid");
            return false;
        }
        if(!passwordStr.equals(confPasswordStr)){
            confPassword.setError("Password not matched");
            return false;
        }
        return true;
    }
}