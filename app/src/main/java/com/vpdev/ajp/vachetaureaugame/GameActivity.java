package com.vpdev.ajp.vachetaureaugame;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

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
    private TextView textView_host_name ;
    private TextView textView_client_name ;
    private DatabaseReference mData ;
        public static int gamefound ;
        public static String gameId ;
        public String gameid ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        button_vt = (Button) findViewById(R.id.button_submit_vt);
        button_number = (Button) findViewById(R.id.button_submit_number);
        edit_number = (EditText) findViewById(R.id.editText_number);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("games");
        firebaseAuth = FirebaseAuth.getInstance();
        gamefound=0 ;
        edit_vt = (EditText) findViewById(R.id.editText_vt);
        textView_client_name=(TextView)findViewById(R.id.textView_client_name);
        textView_host_name=(TextView)findViewById(R.id.textView_host_name)  ;
        mData=FirebaseDatabase.getInstance().getReference().child("users");
        if (MenuActivity.player_state.equals("host"))
    {
        (mData.child(firebaseAuth.getCurrentUser().getUid()).child("name")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                textView_host_name.setText("Player: "+dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;


      //  Toast.makeText(GameActivity.this,"this is the  host",Toast.LENGTH_LONG).show();
        if (gameId!=null) {


            button_number.setVisibility(View.INVISIBLE);
            button_number.setClickable(false);
            edit_number.setFocusable(false);
            edit_number.setEnabled(false);
            edit_number.setCursorVisible(false);
            edit_number.setKeyListener(null);
            edit_number.setBackgroundColor(Color.TRANSPARENT);
            edit_number.setTextColor(Color.BLACK);
           // edit_number.setText("VOila");

            host_turn();


        }


    }

    else if (MenuActivity.player_state.equals("client"))
    {
        find_game() ;





    }
    else
        { Toast.makeText(GameActivity.this,"Error",Toast.LENGTH_SHORT).show(); }


    }


  private void client_turn(String game) {
        gameId=game ;


       DatabaseReference turn = databaseReference.child(gameId).child("Turn");
      turn.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {

              String Turn = dataSnapshot.getValue(String.class);
              if (Turn.equals("client")) {
                  DatabaseReference vtdatabase = databaseReference.child(gameId).child("VT");
                  vtdatabase.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(DataSnapshot dataSnapshot) {

                          submit_client_turn(dataSnapshot);
                      }

                      @Override
                      public void onCancelled(DatabaseError databaseError) {

                      }
                  });

              }

                  else {
                  button_number.setText("waiting ...");
                  button_number.setClickable(false);

              }

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {

          }
      });
  }

     private void host_turn () {


         DatabaseReference turn = databaseReference.child(gameId).child("Turn");
         turn.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(final DataSnapshot dataSnapshot) {
                final String Turn = dataSnapshot.getValue(String.class);

                 if (Turn.equals("host")) {
                     ( databaseReference.child(gameId).child("client id")).addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {

                         if (!dataSnapshot.getValue(String.class).equals("0"))
                         {
                             (mData.child(dataSnapshot.getValue(String.class)).child("name")) .addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(DataSnapshot dataSnapshot) {
                                     Toast.makeText(GameActivity.this,dataSnapshot.getValue(String.class)+" has joined",Toast.LENGTH_LONG).show();
                                     textView_client_name.setText("Player: "+dataSnapshot.getValue(String.class));
                                 }

                                 @Override
                                 public void onCancelled(DatabaseError databaseError) {

                                 }
                             })     ;






                         }
                         }

                         @Override
                         public void onCancelled(DatabaseError databaseError) {

                         }
                     });





                     DatabaseReference numberdatabase = databaseReference.child(gameId).child("NUMBER");
                     numberdatabase.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {

                             DatabaseReference turn = databaseReference.child(gameId).child("Turn");
                             submit_host_turn(dataSnapshot);


                         }

                         public void onCancelled(DatabaseError databaseError) {

                         }
                     });
                 }
              else
                 {

                     button_vt.setText("WAITING ....");
                     button_vt.setClickable(false);

                 }
                 }



             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });
     }


    public void find_game()
    {
        (mData.child(firebaseAuth.getCurrentUser().getUid()).child("name")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                textView_client_name.setText("Player: "+dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }) ;
        final String[] result = new String[1];
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,HashMap<String,String>> l =(HashMap) dataSnapshot.getValue() ;

//                Toast.makeText(GameActivity.this,Integer.toString(l.size()),Toast.LENGTH_SHORT).show() ;

                if (gamefound==0)
                    for (HashMap.Entry<String, HashMap<String,String>> entry : l.entrySet()) {
                        String key = entry.getKey();
                       // Toast.makeText(GameActivity.this,"looking for a game",Toast.LENGTH_LONG).show();
                        HashMap<String,String> hashmap = entry.getValue();
                        //Log.v("aaaaaaaaaaaaa","the data "+key) ;

                        if (hashmap.get("client id").equals("0"))
                        {

                            gameid=new String(key) ;
                            //Toast.makeText(GameActivity.this,gameid,Toast.LENGTH_LONG).show() ;
                            Log.v("bbbbbbbb","the data "+gameid) ;
                            result[0] =key ;
                            gamefound=1 ;
                            Toast.makeText(GameActivity.this,"Joined "+mData.child(hashmap.get("host id")).child("name"),Toast.LENGTH_SHORT);
// this is the begining
                            Log.v("aaaaaaaaaaaaa","the data "+gameid) ;
                            gameId=gameid ;
                            if (gameId!=null) {

                                databaseReference.child(gameId).child("client id").setValue(firebaseAuth.getCurrentUser().getUid());

                                //Toast.makeText(GameActivity.this, "this is the  client", Toast.LENGTH_LONG).show();
                                button_vt.setVisibility(View.INVISIBLE);
                                button_vt.setClickable(false);
                                edit_vt.setFocusable(false);

                                edit_vt.setEnabled(false);
                                edit_vt.setCursorVisible(false);
                                edit_vt.setKeyListener(null);
                                edit_vt.setBackgroundColor(Color.TRANSPARENT);
                                edit_vt.setTextColor(Color.BLACK);
                                databaseReference.child(gameId).child("Turn").setValue("client");
                                set_host_name(gameId) ;
                                ( mData.child(firebaseAuth.getCurrentUser().getUid()).child("name") ).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        textView_client_name.setText("Player: "+dataSnapshot.getValue(String.class));

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                }) ;

                                client_turn(key);
                            }
                            else
                            {
                                Toast.makeText(GameActivity.this,"gameid = null",Toast.LENGTH_LONG).show() ;
                            }



//this is the end
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
    public void submit_host_turn(DataSnapshot dataSnapshot) {

        String numberString ;
        numberString=dataSnapshot.getValue(String.class) ;
        edit_number.setText(numberString);
        button_vt.setText("submit vt");
        button_vt.setClickable(true);
        button_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vt = edit_vt.getText().toString().trim();
                DatabaseReference numberdata=databaseReference.child(gameId).child("VT") ;
                numberdata.setValue(vt) ;
                DatabaseReference turn = databaseReference.child(gameId).child("Turn");
                turn.setValue("client");

            }
        });



    }


public void submit_client_turn(DataSnapshot dataSnapshot)
{
    String vtString ;
    vtString=dataSnapshot.getValue(String.class) ;
    edit_vt.setText(vtString);

    button_number.setText("submit number");
    button_number.setClickable(true);
    button_number.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String number = edit_number.getText().toString().trim();
            databaseReference.child(gameId).child("NUMBER").setValue(number);
            DatabaseReference turn = databaseReference.child(gameId).child("Turn");
            turn.setValue("host");


        }
    });
}

public void set_host_name(String game)
{
    (databaseReference.child(game).child("host id")).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (!(dataSnapshot.getValue(String.class).equals("0")))
            {
                (mData.child(dataSnapshot.getValue(String.class)).child("name")).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        textView_host_name.setText("Player: "+dataSnapshot.getValue(String.class));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }) ;






            }


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }) ;



}


}












    








 





