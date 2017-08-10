package com.vpdev.ajp.vachetaureaugame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task; //
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by saifeddine on 10/08/17.
 */

public class RegisterActivity extends AppCompatActivity{
    private Button button_save;
    private EditText editText_register_email;
    private EditText editText_register_password;
    private EditText editText_register_name;
    private FirebaseAuth mAuth ;
    private ProgressDialog progressDialog ;
    private DatabaseReference databaseReference ;

    @Override
    //
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_save=(Button) findViewById(R.id.button_save) ;
        editText_register_email=(EditText)findViewById(R.id.editText_register_email);
        editText_register_name=(EditText)findViewById(R.id.editText_register_name);
        editText_register_password=(EditText)findViewById(R.id.editText_register_password);
        progressDialog=new ProgressDialog(RegisterActivity.this) ;
        progressDialog.setMessage("Registering ....");
        FirebaseApp.initializeApp(RegisterActivity.this);
        databaseReference =FirebaseDatabase.getInstance().getReference().child("users") ;
        mAuth=FirebaseAuth.getInstance() ;
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_user() ;


            }
        });


    }

    private void save_user() {
        progressDialog.show();
        final String email =editText_register_email.getText().toString().trim() ;
        final String password=editText_register_password.getText().toString().trim() ;
        final String name=editText_register_name.getText().toString().trim() ;
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                        databaseReference.child(mAuth.getCurrentUser().getUid()).child("name").setValue(name) ;
                        databaseReference.child(mAuth.getCurrentUser().getUid()).child("email").setValue(email) ;
                        databaseReference.child(mAuth.getCurrentUser().getUid()).child("score").setValue("0") ;



                   progressDialog.dismiss();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Operation failed",Toast.LENGTH_SHORT) ;


                }


            }
        });




    }


}


