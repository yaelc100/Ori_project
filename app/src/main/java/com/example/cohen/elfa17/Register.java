package com.example.cohen.elfa17;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity implements View.OnClickListener {
EditText email, pass,  PersonName;
ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        PersonName=(EditText)findViewById(R.id.PersonName) ;
        progressBar =(ProgressBar) findViewById (R.id.progressBar);
        email= (EditText) findViewById(R.id.email);
        pass=(EditText) findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.tvsignin).setOnClickListener(this);
        findViewById(R.id.signupbutton).setOnClickListener(this);



    }
    private  void  registeruser () {
        String usermail=email.getText().toString();
        String userpass= pass.getText().toString();

        if(usermail.isEmpty()) {
            email.setError(" Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(usermail).matches()){
            email.setError(" Set a valid email");
            email.requestFocus();
            return;
        }
        if(userpass.isEmpty()) {
            pass.setError(" Email is required");
            pass.requestFocus();
            return;
        }
        if(userpass.length()<6) {
            pass.setError("Password length most be at least 6");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(usermail, userpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 progressBar.setVisibility(View.GONE);

                 if (task.isSuccessful()) {

                     String FullName=PersonName.getText().toString();

                     if(FullName.isEmpty()) {
                         PersonName.setError("Enter your name");
                         PersonName.requestFocus();
                     }
                     else {
                         FirebaseUser user=mAuth.getCurrentUser();
                         UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                                 .setDisplayName(FullName).build();
                         user.updateProfile(profile);

                     }
                     finish();

                     Toast.makeText(getApplicationContext(), "user Rgister Succsefully", Toast.LENGTH_LONG).show();
                     Intent t=new Intent(Register.this, Welcome.class);
                     t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(t);
                 }
                 else {
                     if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                         Toast.makeText(getApplicationContext(), "You are already registered ", Toast.LENGTH_LONG).show();
                     } else {


                         Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
                     }
                 }
             }
         });
    }

    @Override
    public void onClick(View view) {


        switch(view.getId()) {
            case R.id.signupbutton:
                registeruser();

                break;


            case R.id.tvsignin:
                finish();
                startActivity(new Intent(this ,Signin.class));


                break;
        }

    }
}
