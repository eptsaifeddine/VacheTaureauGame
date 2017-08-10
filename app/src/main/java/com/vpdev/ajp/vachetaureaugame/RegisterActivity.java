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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by saifeddine on 10/08/17.
 */

public class RegisterActivity extends AppCompatActivity{
    private Button button_save=(Button) findViewById(R.id.button_save) ;
    private EditText editText_register_email=(EditText)findViewById(R.id.editText_register_email);
    private EditText editText_register_name=(EditText)findViewById(R.id.editText_register_name);
    private EditText editText_register_password=(EditText)findViewById(R.id.editText_register_password);
    private FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }





}


