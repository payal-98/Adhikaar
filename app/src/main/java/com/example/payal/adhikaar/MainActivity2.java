package com.example.payal.adhikaar;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> names1 = new ArrayList<>();
    ArrayList<String> names2 = new ArrayList<>();
    ArrayList<String> names3 = new ArrayList<>();
    ArrayList<String> names4 = new ArrayList<>();
    ArrayList<String> names5 = new ArrayList<>();
    Button btn;
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioGroup radioGroup3;
    RadioGroup radioGroup4;
    RadioGroup radioGroup5;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    RadioButton radioButton5;
    public static final String Extra_msg="etmessage";
    public static final String rno="abc";
    public String mail,rnum;
    FirebaseAuth firebaseAuth;

     // long num1=0;
     DatabaseReference ref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
      //   mail=i.getStringExtra(Extra_msg);
         rnum=i.getStringExtra(rno);

        firebaseAuth=FirebaseAuth.getInstance();
        btn=(Button)findViewById(R.id.vote);
        radioGroup1 = findViewById(R.id.group1);
        radioGroup2 = findViewById(R.id.group2);
        radioGroup3 = findViewById(R.id.group3);
        radioGroup4 = findViewById(R.id.group4);
        radioGroup5 = findViewById(R.id.group5);
       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),Last.class));
            }
        });*/
       ref=FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Candidates").child("President");
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Candidates").child("Vice President");
        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference().child("Candidates").child("Secretary");
        DatabaseReference ref4 = FirebaseDatabase.getInstance().getReference().child("Candidates").child("Treasurer");
        DatabaseReference ref5 = FirebaseDatabase.getInstance().getReference().child("Candidates").child("Joint Secretary");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                collectnames1((Map<String, Object>) dataSnapshot.getValue());
                createRadiobuttons1();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectnames2((Map<String, Object>) dataSnapshot.getValue());
                createRadiobuttons2();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectnames3((Map<String, Object>) dataSnapshot.getValue());
                createRadiobuttons3();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectnames4((Map<String, Object>) dataSnapshot.getValue());
                createRadiobuttons4();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectnames5((Map<String, Object>) dataSnapshot.getValue());
                createRadiobuttons5();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // startActivity(new Intent(getBaseContext(),Last.class));
                final int radioId = radioGroup1.getCheckedRadioButtonId();
                final int radioId2 = radioGroup2.getCheckedRadioButtonId();
                final int radioId3 = radioGroup3.getCheckedRadioButtonId();
                final int radioId4 = radioGroup4.getCheckedRadioButtonId();
                final int radioId5 = radioGroup5.getCheckedRadioButtonId();
                if ((radioGroup1.getCheckedRadioButtonId() == -1) || (radioGroup2.getCheckedRadioButtonId() == -1) || (radioGroup3.getCheckedRadioButtonId() == -1) || (radioGroup4.getCheckedRadioButtonId() == -1) || (radioGroup5.getCheckedRadioButtonId() == -1))
                {
                          Toast.makeText(getBaseContext(), "Vote for all candidates!!!", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    //final TextView t1=(TextView)findViewById(R.id.text1);
                    DatabaseReference db1 = ref.child("Cs").child(rnum).child("Flag");
                    db1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Long flag1 = (Long) dataSnapshot.getValue();
                            //   t1.setText(rnum);
                            if (flag1 != null) {
                                if (flag1 == -1) {
                                    radioButton = findViewById(radioId);
                                    radioButton2 = findViewById(radioId2);
                                    radioButton3 = findViewById(radioId3);
                                    radioButton4 = findViewById(radioId4);
                                    radioButton5 = findViewById(radioId5);

                                    String name = radioButton.getText().toString();
                                    String name2 = radioButton2.getText().toString();
                                    final String name3 = radioButton3.getText().toString();
                                    String name4 = radioButton4.getText().toString();
                                    String name5 = radioButton5.getText().toString();

                                    DatabaseReference db = ref.child("Candidates").child("President").child(name).child("Count");
                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                Long num = (Long) dataSnapshot.getValue();
                                                Long num1 = num + 1;
                                                dataSnapshot.getRef().setValue(num1);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference db2 = ref.child("Candidates").child("Vice President").child(name2).child("Count");
                                    db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                Long num = (Long) dataSnapshot.getValue();
                                                Long num1 = num + 1;
                                                dataSnapshot.getRef().setValue(num1);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference db3 = ref.child("Candidates").child("Secretary").child(name3).child("Count");
                                    db3.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                Long num = (Long) dataSnapshot.getValue();
                                                Long num1 = num + 1;
                                                dataSnapshot.getRef().setValue(num1);
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference db4 = ref.child("Candidates").child("Treasurer").child(name4).child("Count");
                                    db4.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                Long num = (Long) dataSnapshot.getValue();
                                                Long num1 = num + 1;
                                                dataSnapshot.getRef().setValue(num1);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference db5 = ref.child("Candidates").child("Joint Secretary").child(name5).child("Count");
                                    db5.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.getValue() != null) {
                                                Long num = (Long) dataSnapshot.getValue();
                                                Long num1 = num + 1;
                                                dataSnapshot.getRef().setValue(num1);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    DatabaseReference db6 = ref.child("Cs").child(rnum).child("Flag");
                                    db6.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue() != null) {
                                                Integer num = dataSnapshot.getValue(Integer.class);
                                                num = 1;
                                                dataSnapshot.getRef().setValue(num);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                    firebaseAuth.signOut();
                                    GoogleSignInClient mGoogleSignInClient;
                                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                            .requestIdToken(getString(R.string.default_web_client_id))
                                            .requestEmail()
                                            .build();
                                    mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
                                    mGoogleSignInClient.signOut()
                                            .addOnCompleteListener(MainActivity2.this, new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    // ...
                                                    firebaseAuth.signOut();
                                                    finish();
                                                }
                                            });
                                    Intent intent=new Intent(MainActivity2.this,Last.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   startActivity(intent);
                                }
                                }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

               }


            }
        });


    }

    public void collectnames1(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleuser1 = (Map) entry.getValue();
            names1.add((String )singleuser1.get("Name"));
        }

    }

    public void createRadiobuttons1() {
        RadioGroup group = (RadioGroup) findViewById(R.id.group1);
        for (int i = 0; i < names1.size(); i++)

        {
            String Post = names1.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(Post);
            group.addView(button);


        }
    }

    public void collectnames2(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleuser2 = (Map) entry.getValue();
            names2.add((String) singleuser2.get("Name"));
        }

    }

    public void createRadiobuttons2() {
        RadioGroup group = (RadioGroup) findViewById(R.id.group2);
        for (int i = 0; i < names2.size(); i++)

        {
            String Post = names2.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(Post);
            group.addView(button);


        }
    }

    public void collectnames3(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleuser3 = (Map) entry.getValue();
            names3.add((String) singleuser3.get("Name"));
        }

    }

    public void createRadiobuttons3() {
        RadioGroup group = (RadioGroup) findViewById(R.id.group3);
        for (int i = 0; i < names3.size(); i++)

        {
            String Post = names3.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(Post);
            group.addView(button);


        }
    }

    public void collectnames4(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleuser4 = (Map) entry.getValue();
            names4.add((String) singleuser4.get("Name"));
        }

    }

    public void createRadiobuttons4() {
        RadioGroup group = (RadioGroup) findViewById(R.id.group4);
        for (int i = 0; i < names4.size(); i++)

        {
            String Post = names4.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(Post);
            group.addView(button);


        }
    }

    public void collectnames5(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()) {
            Map singleuser5 = (Map) entry.getValue();
            names5.add((String) singleuser5.get("Name"));
        }

    }

    public void createRadiobuttons5() {
        RadioGroup group = (RadioGroup) findViewById(R.id.group5);
        for (int i = 0; i < names5.size(); i++)

        {
            String Post = names5.get(i);
            RadioButton button = new RadioButton(this);
            button.setText(Post);
            group.addView(button);


        }
    }
}
