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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText  editText_email ;
    private EditText  editText_password ;
    private Button   button_login ;
    private Button button_register ;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_email = (EditText)findViewById(R.id.editText_email);
        editText_password=(EditText)findViewById(R.id.editText_password) ;
        button_login =(Button) findViewById(R.id.button_login) ;
        mAuth=FirebaseAuth.getInstance() ;
        button_register =(Button)findViewById(R.id.button_register) ;
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(intent);


            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sign_in() ;
            }
        });


    }

    private void sign_in() {
        final String email=editText_email.getText().toString().trim() ;
        final String password=editText_password.getText().toString().trim() ;

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                    Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this,"Operation failed",Toast.LENGTH_SHORT).show();




            }
        });


    }
}
