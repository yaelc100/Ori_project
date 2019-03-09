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

public class Signin extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText Email, Password;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Email= (EditText) findViewById(R.id.Email);
        Password=(EditText) findViewById(R.id.Password);
        progressbar=(ProgressBar) findViewById(R.id.progressBar1);

        mAuth=FirebaseAuth.getInstance();

        findViewById(R.id.tvSignup).setOnClickListener(this);
        findViewById(R.id.buttonsignin).setOnClickListener(this);
    }
  private void userlogin() {
    String UserMail=Email.getText().toString();
    String UserPassword=Password.getText().toString();

      if(UserMail.isEmpty()) {
          Email.setError(" Email is required");
          Email.requestFocus();
          return;
      }
      if(!Patterns.EMAIL_ADDRESS.matcher(UserMail).matches()){
          Email.setError(" Set a valid email");
          Email.requestFocus();
          return;
      }
      if(UserPassword.isEmpty()) {
          Password.setError(" Email is required");
          Password.requestFocus();
          return;
      }
      if(UserPassword.length()<6) {
          Password.setError("Password length most be at least 6");
          Password.requestFocus();
          return;
      }

      progressbar.setVisibility(View.VISIBLE);


      mAuth.signInWithEmailAndPassword(UserMail, UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              progressbar.setVisibility(View.GONE);

              if(task.isSuccessful()) {
                  finish();
                  Intent t=new Intent(Signin.this, Welcome.class);
                 t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  startActivity(t);

              }
              else{
                  Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG);
                  Toast.makeText(getApplicationContext(), "some of your details are wrong ", Toast.LENGTH_LONG).show();

              }
          }
      });



  }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null) {
            startActivity(new Intent(Signin.this, Welcome.class));
            Toast.makeText(getApplicationContext(), "some of your details are wrong ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSignup:
                finish();
                startActivity(new Intent(this ,Register.class));
                break;

            case R.id.buttonsignin:
                userlogin();

        }

    }
}
