package com.example.cohen.elfa17;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Welcome extends AppCompatActivity {

    TextView tvwelcome;
    String username;
    FirebaseAuth mAuth;
    Button CreateGroup;
    DatabaseReference ref;
    EditText input;
    boolean NewOrNot;
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        mAuth=FirebaseAuth.getInstance();
        tvwelcome=(TextView) findViewById(R.id.tvwelcome);
        UpdateName();
        ref = FirebaseDatabase.getInstance().getReference();

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Team id");
        builder.setMessage("Enter the team name");

        input=new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (NewOrNot) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    String TeamName = input.getText().toString();
                    String id = ref.push().getKey();
                    Item item = new Item(id, user.getDisplayName(), 0,0);
                    ref = FirebaseDatabase.getInstance().getReference(TeamName);
                    ref.child(id).setValue(item);
                    Intent t=new Intent(Welcome.this,Group.class );
                    t.putExtra("TeamName", TeamName);
                    startActivity(t);

                }
                else {
                    Toast.makeText(getApplicationContext(), "djs", Toast.LENGTH_LONG).show();

                    String id = ref.push().getKey();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Item item = new Item(id, user.getDisplayName(), 0,0);
                    String TeamName = input.getText().toString();

                    ref =  FirebaseDatabase.getInstance().getReference(TeamName);
                    ref.child(id).setValue(item);
                    Intent t=new Intent(Welcome.this,Group.class );
                    t.putExtra("TeamName", TeamName);
                    startActivity(t);


                }
            }
        });

        builder.setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final AlertDialog ad= builder.create();

        CreateGroup=(Button) findViewById(R.id.CreateGroup);
        CreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
                NewOrNot=true;

            }
        });
         findViewById(R.id.JoinGroup).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ad.show();
             }
         });

    }


    private void UpdateName() {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null&&user.getDisplayName()!=null) {
            tvwelcome.setText("Welcome " + user.getDisplayName());
        }
        else {
            Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_LONG).show();
        }
    }




}
