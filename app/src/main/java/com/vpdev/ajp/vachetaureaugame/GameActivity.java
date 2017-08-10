package com.vpdev.ajp.vachetaureaugame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by saifeddine on 10/08/17.
 */

public class GameActivity  extends AppCompatActivity {
    private Button button_vt;
    private EditText edit_vt;
    private Button button_number;
    private EditText edit_number;
    private DatabaseReference databaseReference ;
    private FirebaseAuth firebaseAuth ;
        public static int gamefound ;
        public static String gameId ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        button_vt = (Button) findViewById(R.id.button_submit_vt);
        button_number = (Button) findViewById(R.id.button_submit_number);
        edit_number = (EditText) findViewById(R.id.editText_number);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("games");
        firebaseAuth = FirebaseAuth.getInstance();
        edit_vt = (EditText) findViewById(R.id.editText_vt);
    if (MenuActivity.player_state.equals("host"))
    {
        if (gamefound!=0)
        {












        }





    }

    else if (MenuActivity.player_state.equals("client"))
    {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,HashMap<String,String>> l =(HashMap) dataSnapshot.getValue() ;

                Toast.makeText(GameActivity.this,Integer.toString(l.size()),Toast.LENGTH_SHORT).show() ;
                Log.v("aaaaaaaaaaaaa","the data "+l) ;
                          if (gamefound==0)
                       for (HashMap.Entry<String, HashMap<String,String>> entry : l.entrySet()) {
                           String key = entry.getKey();
                            HashMap<String,String> hashmap = entry.getValue();
                              Log.v("aaaaaaaaaaaaa","the data "+key) ;             

                        if (hashmap.get("client id").equals("0"))
                        {
                            gameId=key;
                            gamefound=1 ;
                            databaseReference.child(gameId).child("client id").setValue(firebaseAuth.getCurrentUser().getUid()) ;
                            break ;
                        }


                           // Toast.makeText(GameActivity.this,it.toString(),Toast.LENGTH_SHORT).show() ;

                       }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
    else
        Toast.makeText(GameActivity.this,"Error",Toast.LENGTH_SHORT).show();

    }








}
