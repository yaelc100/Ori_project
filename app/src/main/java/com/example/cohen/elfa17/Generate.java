package com.example.cohen.elfa17;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Random;

public class Generate extends AppCompatActivity {
    String Team;
    DatabaseReference ref;
    String[] players;
    Item[] players1;
    int teamNum;
    TextView tvTeam1, tvTeam2, tvTeam3, tvTeam4;
    Random rnd;
    String sTeam1="Team 1:";
     String sTeam2="Team 2:";
     String sTeam3="Team 3:";
     String sTeam4="Team 4: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);


        tvTeam1 = (TextView) findViewById(R.id.Team1);
        tvTeam2 = (TextView) findViewById(R.id.Team2);
        tvTeam3 = (TextView) findViewById(R.id.Team3);
        tvTeam4 = (TextView) findViewById(R.id.Team4);

        rnd=new Random();
        Intent gi = getIntent();
        Team = gi.getStringExtra("teamname");
        teamNum = gi.getIntExtra("teamNum", 0);
        ref = FirebaseDatabase.getInstance().getReference(Team);


    }


    protected void onStart() {

        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                players1 = new Item[21];
                for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {
                    Item item = itemsnapshot.getValue(Item.class);
                    players1[i] = item;
                    i++;

                }

                for (int x = 0; x <= i - 1; x++) {
                    for (int j = 0; j <= i - 1; j++) {
                        if (players1[x].getDirug() < players1[j].getDirug()) {
                            Item temp = players1[x];
                            players1[x] = players1[j];
                            players1[j] = temp;
                        }
                    }
                }
                switch (teamNum) {
                    case 2:
                        Create2teams(players1);
                        break;
                    case 3:
                        Create3teams(players1, i);
                        break;
                    case 4:
                        Creat4teams(players1, i);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Creat4teams(Item[] players2, int size) {
        String[] Team1 = new String[10];
        String[] Team2 = new String[10];
        String[] Team3=new String[10];
        String[] Team4=new String[10];

        int counter=-1;
        for(int i=0;counter<size;i++) {
            counter++;
            if(counter==0||counter==17) {
                if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                    counter++;
                }

                    if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                        counter++;
                }

                   if (players2[counter] != null) {
                       Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                       counter++;
                   }

                   if (players2[counter] != null) {
                    Team4[i] = players2[counter].getName() + "-" + players2[counter].getDirug();


                }
                 }


            if (counter==4) {
                if (players2[counter] != null) {
                    Team4[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                    counter++;
                }

                  if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                      counter++;
                }

                   if (players2[counter] != null) {
                     Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                       counter++;
                }

                   if (players2[counter] != null) {
                     Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                  }
            }
            if(counter==8) {
                if (players2[counter] != null) {

                    Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                }
                    counter++;
                if (players2[counter] != null) {
                    Team4[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
                counter++;
                if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
                counter++;
                if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
            }

            if(counter==12) {
                if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                }
                counter++;
                if (players2[counter] != null) {
                    Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
                counter++;
                if (players2[counter] != null) {
                    Team4[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
                counter++;
                if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
            }

        }
        for(int x=0;Team1[x]!=null;x++) {
            sTeam1=sTeam1+ System.lineSeparator()+ Team1[x];
        }

        for(int x=0;Team2[x]!=null;x++) {
            sTeam2=sTeam2 +System.lineSeparator()+Team2[x];
        }
        for(int x=0;Team3[x]!=null;x++) {
            sTeam3=sTeam3 +System.lineSeparator()+Team3[x];
        }
        for(int x=0;Team4[x]!=null;x++) {
            sTeam4=sTeam4 +System.lineSeparator()+Team4[x];
        }

        tvTeam1.setText(sTeam1);
        tvTeam2.setText(sTeam2);
        tvTeam3.setText(sTeam3);
        tvTeam4.setText(sTeam4);


    }

    private void Create3teams(Item[] players2, int size) {
        String[] Team1 = new String[10];
        String[] Team2 = new String[10];
        String[] Team3=new String[10];



        int counter =-1;
        for(int i=0;counter<size;i++) {
            counter++;
            if ((counter == 9) || (counter == 0) || (counter == 18)) {
                if (players2[counter] != null) {

                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                     counter++;
                }
                if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();
                    counter++;

                }

                if (players2[counter] != null) {
                    Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                }
            }
            if (counter == 3 || counter == 12) {
                if (players2[counter] != null) {
                    Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                counter++;
                }

                if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                    counter++;

                }

                if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();


                }

            }

            if (counter == 6 || counter == 15) {
                if (players2[counter] != null) {
                    Team2[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                counter++;
                }

                if (players2[counter] != null) {
                    Team3[i] = players2[counter].getName() + "-" + players2[counter].getDirug();

                    counter++;
                }

                if (players2[counter] != null) {
                    Team1[i] = players2[counter].getName() + "-" + players2[counter].getDirug();


                }
            }
        }
            for(int x=0;Team1[x]!=null;x++) {
                sTeam1=sTeam1+ System.lineSeparator()+ Team1[x];
            }

            for(int x=0;Team2[x]!=null;x++) {
                sTeam2=sTeam2 +System.lineSeparator()+Team2[x];
            }
            for(int x=0;Team3[x]!=null;x++) {
                sTeam3=sTeam3 +System.lineSeparator()+Team3[x];
            }
            tvTeam1.setText(sTeam1);
            tvTeam2.setText(sTeam2);
            tvTeam3.setText(sTeam3);

    }

    public void Create2teams(Item[] players2) {
        String[] Team1 = new String[10];
        String[] Team2 = new String[10];
        boolean x=true;
        int Counter=0;
        for (int i=0;players2[Counter]!=null;i++) {

            if (x) {
                Team1[i]=players2[Counter].getName()+players2[Counter].getDirug();
                Counter++;
                Team2[i]=players2[Counter].getName()+players2[Counter].getDirug();
                Counter++;
            }
            else {
                Team2[i]=players2[Counter].getName()+players2[Counter].getDirug();
                Counter++;
                Team1[i]=players2[Counter].getName()+players2[Counter].getDirug();
                Counter++;
            }
            x=!x;
        }
     for(int i=0;Team1[i]!=null;i++) {
            sTeam1=sTeam1+ System.lineSeparator()+ Team1[i];
     }

        for(int i=0;Team2[i]!=null;i++) {
            sTeam2=sTeam2 +System.lineSeparator()+Team2[i];
        }
        tvTeam1.setText(sTeam1);
        tvTeam2.setText(sTeam2);
    }

    public void Finish(View view) {
        finish();
    }
}
