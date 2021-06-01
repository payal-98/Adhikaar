package com.example.payal.adhikaar;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String ab="etmessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button b1=findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Enable").child("Value");
               // Toast.makeText(getBaseContext(),"button clicked",Toast.LENGTH_SHORT).show();

                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.getValue().equals("YES")) {
                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                            View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                            final EditText editText = (EditText) mView.findViewById(R.id.rollnum);
                            Button mLogin = (Button) mView.findViewById(R.id.send);
                            mBuilder.setView(mView);
                            final AlertDialog dialog = mBuilder.create();
                            dialog.show();
                            mLogin.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if(!editText.getText().toString().isEmpty()){
                                        final String RollNo = editText.getText().toString().trim();
                                        final DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

                                        final DatabaseReference db1=ref.child("Cs").child(RollNo);
                                        db1.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                if(!dataSnapshot.exists())
                                                {
                                                    Toast.makeText(MainActivity.this,"Invalid RollNo",Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                }
                                                else {
                                                    DatabaseReference db2 = db1.child("Flag");
                                                    db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                            Long flag1 = (Long) dataSnapshot.getValue();
                                                            if (flag1 != null) {
                                                                if (flag1 == -1) {
                                                                    DatabaseReference db = db1.child("Email");
                                                                    db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                            String email = dataSnapshot.getValue(String.class);
                                                                            Intent intent = new Intent(MainActivity.this, signin.class);
                                                                            intent.putExtra(signin.Extra_msg, email);
                                                                            intent.putExtra(signin.Rollnum, RollNo);
                                                                            startActivity(intent);
                                                                        }

                                                                        @Override
                                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                        }
                                                                    });

                                                                } else {

                                                                    Toast.makeText(getBaseContext(), "You have already voted", Toast.LENGTH_SHORT).show();
                                                                    dialog.dismiss();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                                        }
                                                    });
                                                }


                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });



                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this,
                                                "you didnot enter roll number",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                        else if(dataSnapshot.getValue().equals("NO"))
                        {
                            Toast.makeText(getBaseContext(),"Voting lines are closed",Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


              /* AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog, null);
                final EditText editText = (EditText) mView.findViewById(R.id.rollnum);
                Button mLogin = (Button) mView.findViewById(R.id.send);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!editText.getText().toString().isEmpty()){
                            final String RollNo = editText.getText().toString().trim();
                            final DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

                            final DatabaseReference db1=ref.child("Cs").child(RollNo);
                            db1.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if(!dataSnapshot.exists())
                                    {
                                        Toast.makeText(MainActivity.this,"Invalid RollNo",Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                    else {
                                        DatabaseReference db2 = db1.child("Flag");
                                        db2.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Long flag1 = (Long) dataSnapshot.getValue();
                                                if (flag1 != null) {
                                                    if (flag1 == -1) {
                                                        DatabaseReference db = db1.child("Email");
                                                        db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                String email = dataSnapshot.getValue(String.class);
                                                                Intent intent = new Intent(MainActivity.this, signin.class);
                                                                intent.putExtra(signin.Extra_msg, email);
                                                                intent.putExtra(signin.Rollnum, RollNo);
                                                                startActivity(intent);
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                                            }
                                                        });

                                                    } else {

                                                        Toast.makeText(getBaseContext(), "You have already voted", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,
                                    "you didnot enter roll number",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }); */
            }
        });

    }


    public void admin(View view)
    {

        Intent intent=new Intent(MainActivity.this,signin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                dialog.cancel();


            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



}
