package com.vpdev.ajp.vachetaureaugame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        button_vt = (Button)findViewById(R.id.button_submit_vt);
        button_number = (Button)findViewById(R.id.button_submit_number);
        edit_number = (EditText)findViewById(R.id.editText_number);
        databaseReference=FirebaseDatabase.getInstance().getReference() ;
        firebaseAuth=FirebaseAuth.getInstance() ;

        edit_vt = (EditText)findViewById(R.id.editText_vt);
        button_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i = r.nextInt(10000 - 65) + 65;
                databaseReference.child("game"+Integer.toString(i)).child("host id").setValue(firebaseAuth.getCurrentUser().getUid());
                databaseReference.child("game"+Integer.toString(i)).child("client id").setValue("0");
                databaseReference.child("game"+Integer.toString(i)).child("VT").setValue("NOTSTARTEDYET");
                databaseReference.child("game"+Integer.toString(i)).child("NUMBER").setValue("NOTSTARTEDYET");
//






            }
        });


    }



}
