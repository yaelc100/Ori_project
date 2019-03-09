package com.example.cohen.elfa17;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class check extends AppCompatActivity {
EditText input;
Button Create1;
DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Team id");
        builder.setMessage("Enter the team name");

        input=new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TeamName=input.getText().toString();
                Toast.makeText(getApplicationContext(), TeamName, Toast.LENGTH_LONG).show();
                ref = FirebaseDatabase.getInstance().getReference(TeamName);
            }
        });

        builder.setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             dialog.dismiss();
            }
        });
        final AlertDialog ad= builder.create();

       Create1=(Button) findViewById(R.id.create1);
       Create1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ad.show();
                          }
       });


    }

    public void Create(View view) {


    }

    public void next(View view) {
        Intent t= new Intent(this ,Register.class);

        startActivity(t);

    }
}
