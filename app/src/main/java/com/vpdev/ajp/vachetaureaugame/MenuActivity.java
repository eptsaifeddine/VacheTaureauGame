package com.vpdev.ajp.vachetaureaugame;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


/**
 * Created by saifeddine on 10/08/17.
 */

public class MenuActivity extends AppCompatActivity {
private Button button_create ;
private Button button_find ;
private DatabaseReference databaseReference ;
private FirebaseAuth firebaseAuth ;
public static  String player_state ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("games");
        firebaseAuth=FirebaseAuth.getInstance() ;
        button_find=(Button) findViewById(R.id.button_findgame ) ;

        button_create =(Button)findViewById(R.id.button_creategame) ;
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i = r.nextInt(10000 - 65) + 65;
                databaseReference.child("game"+Integer.toString(i)).child("host id").setValue(firebaseAuth.getCurrentUser().getUid());
                databaseReference.child("game"+Integer.toString(i)).child("client id").setValue("0");
                databaseReference.child("game"+Integer.toString(i)).child("VT").setValue("NOTSTARTEDYET");
                databaseReference.child("game"+Integer.toString(i)).child("NUMBER").setValue("NOTSTARTEDYET");
                databaseReference.child("game"+Integer.toString(i)).child("Turn").setValue("0");
                player_state="host" ;
                Intent intent =new Intent(MenuActivity.this,GameActivity.class) ;
                MenuActivity.this.startActivity(intent);






            }
        });
        button_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_state="client" ;
                Intent intent =new Intent(MenuActivity.this,GameActivity.class) ;
                MenuActivity.this.startActivity(intent);




            }
        });



    }


}



