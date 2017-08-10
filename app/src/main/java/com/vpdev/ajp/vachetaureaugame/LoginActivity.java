package com.vpdev.ajp.vachetaureaugame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText editText_email = (EditText)findViewById(R.id.editText_email);
    private EditText editText_password=(EditText)findViewById(R.id.editText_password) ;
    private Button button_login =(Button) findViewById(R.id.button_login) ;
    private Button button_register =(Button)findViewById(R.id.button_register) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }
}
