package com.example.cohen.elfa17;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Group extends AppCompatActivity {
    List<Item> itemList;
    ListView list;
    String TeamName;
    DatabaseReference ref;
    Item item;
    EditText input;
    int numofteams;

    String x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent gi=getIntent();
        TeamName=gi.getStringExtra("TeamName");

       AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Teams");
        builder.setMessage("Enter the Number of teams (beatween 1-4)");

        input=new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Sumbit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TeamNum = input.getText().toString();
                numofteams =Integer.parseInt(TeamNum);
                Intent t = new Intent(Group.this, Generate.class);
                t.putExtra("teamname", TeamName);
                if (numofteams>1&&numofteams<5) {
                    t.putExtra("teamNum",numofteams );
                startActivity(t);
            }
            else {

                    Toast.makeText(Group.this, "Enter a valid value", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
            }
        }});



        builder.setNegativeButton("Cancal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
       final AlertDialog ad= builder.create();

        list= (ListView)findViewById(R.id.list);

        ref= FirebaseDatabase.getInstance().getReference("item1");

        itemList=new ArrayList<>();

        findViewById(R.id.generate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
                //Intent t = new Intent(Group.this, Generate.class);
                //t.putExtra("teamname", TeamName);
                //startActivity(t);
            }
        });

        ref=FirebaseDatabase.getInstance().getReference(TeamName);
        registerForContextMenu(list);







    }
    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot itemsnapshot: dataSnapshot.getChildren())
                {
                    Item item= itemsnapshot.getValue(Item.class);
                    itemList.add(item);

                }
                Itemlist adapter=new Itemlist (Group.this, itemList);
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list) {
            ListView lv = (ListView) v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
            item = (Item) lv.getItemAtPosition(acmi.position);
            menu.add("Rate");
            menu.add("Remove");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("Rate")) {
           Intent t=new Intent(Group.this, Rate.class);
           t.putExtra("id" ,this.item.getId());
           t.putExtra("name", this.item.getName());
           t.putExtra("counter", this.item.getCounter());
           t.putExtra("dirug", this.item.getDirug());
           t.putExtra("teamname", TeamName);
           startActivity(t);

        }
        if (title.equals("Remove")) {
            ref.child(this.item.getId()).removeValue();
        }

        return super.onContextItemSelected(item);
    }


    public void cancal1(View view) {
        finish();
    }
}