package com.example.cohen.elfa17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rate extends AppCompatActivity {
    String id, name,Team,xy;
    int counter;
    double dirug, dirug3;
    EditText dirug1;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        dirug1=(EditText) findViewById(R.id.PlayerRate);

        Intent gi=getIntent();
        id=gi.getStringExtra("id");
        name=gi.getStringExtra("name");
        counter=gi.getIntExtra("counter", 0);
        dirug=gi.getDoubleExtra("dirug", 0);
        Item item=new Item(id, name, dirug, counter);
        Team=gi.getStringExtra("teamname");
        ref= FirebaseDatabase.getInstance().getReference(Team);

    }

    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        Item item=new Item(id,name,dirug,counter);
       xy=dirug1.getText().toString();
       dirug3=Double.parseDouble(xy);
       dirug=(((dirug*counter)+dirug3)/(counter+1));
       item.setDirug(dirug);

       item.setCounter(counter+1);
        ref.child(id).setValue(item);
       finish();
    }
}
