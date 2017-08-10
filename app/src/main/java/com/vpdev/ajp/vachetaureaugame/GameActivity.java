package com.vpdev.ajp.vachetaureaugame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by saifeddine on 10/08/17.
 */

public class GameActivity  extends AppCompatActivity {
    private Button button_vt;
    private EditText edit_vt;
    private Button button_number;
    private EditText edit_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        button_vt = (Button)findViewById(R.id.button_submit_vt);
        button_number = (Button)findViewById(R.id.button_submit_number);
        edit_number = (EditText)findViewById(R.id.editText_number);
        edit_vt = (EditText)findViewById(R.id.editText_vt);



    }



}
