package com.example.payal.adhikaar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;
import java.util.Map;

public class result extends AppCompatActivity {
Button btn;

ListView listView,listView2,listView3,listView4,listView5;

FirebaseAuth firebaseAuth;

User user;
ArrayList<String> list=new ArrayList<>();
    ArrayList<String> list2=new ArrayList<>();
    ArrayList<String> list3=new ArrayList<>();
    ArrayList<String> list4=new ArrayList<>();
    ArrayList<String> list5=new ArrayList<>();

ArrayAdapter<String> adapter,adapter2,adapter3,adapter4,adapter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        listView=(ListView)findViewById(R.id.l1);
        listView2=(ListView)findViewById(R.id.l2);
        listView3=(ListView)findViewById(R.id.l3);
        listView4=(ListView)findViewById(R.id.l4);
        listView5=(ListView)findViewById(R.id.l5);
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,list);



firebaseAuth=FirebaseAuth.getInstance();
        DatabaseReference df=FirebaseDatabase.getInstance().getReference().child("Candidates");
        Query ref=df.child("President").orderByChild("Count");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo1,list);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                   user=ds.getValue(User.class);
                   list.add(user.getName().toString()+" : "+user.getCount());
                }
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference df2=FirebaseDatabase.getInstance().getReference().child("Candidates");
        Query ref2=df.child("Vice President").orderByChild("Count");
        list2=new ArrayList<>();
        adapter2=new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo1,list2);

        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    user=ds.getValue(User.class);
                    list2.add(user.getName().toString()+" : "+user.getCount());
                }
                listView2.setAdapter(adapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference df3=FirebaseDatabase.getInstance().getReference().child("Candidates");
        Query ref3=df.child("Treasurer").orderByChild("Count");
        list3=new ArrayList<>();
        adapter3=new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo1,list3);

        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    user=ds.getValue(User.class);
                    list3.add(user.getName().toString()+" : "+user.getCount());
                }
                listView3.setAdapter(adapter3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference df4=FirebaseDatabase.getInstance().getReference().child("Candidates");
        Query ref4=df.child("Secretary").orderByChild("Count");
        list4=new ArrayList<>();
        adapter4=new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo1,list4);

        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    user=ds.getValue(User.class);
                    list4.add(user.getName().toString()+" : "+user.getCount());
                }
                listView4.setAdapter(adapter4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference df5=FirebaseDatabase.getInstance().getReference().child("Candidates");
        Query ref5=df.child("Joint Secretary").orderByChild("Count");
        list5=new ArrayList<>();
        adapter5=new ArrayAdapter<String>(this,R.layout.user_info,R.id.userInfo1,list5);

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    user=ds.getValue(User.class);
                    list5.add(user.getName().toString()+" : "+user.getCount());
                }
                listView5.setAdapter(adapter5);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addbutoon, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.mybutton:GoogleSignInClient mGoogleSignInClient ;
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(getBaseContext(), gso);
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                firebaseAuth.signOut();
                                finish();
                                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
                break;

            case R.id.enable: DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Enable").child("Value");
                ref1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.getValue() != null) {
                            //String num = (String) dataSnapshot.getValue();
                            //num="Disable";
                            dataSnapshot.getRef().setValue("YES");
                            Toast.makeText(getBaseContext(),"Voting line Enabled",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                            break;
            case R.id.disable: DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("Enable").child("Value");
                ref2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.getValue() != null) {
                            //String num = (String) dataSnapshot.getValue();
                            //num="Enable";
                            dataSnapshot.getRef().setValue("NO");
                            Toast.makeText(getBaseContext(),"Voting line Disabled",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
