package com.example.payal.adhikaar;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView img=(ImageView)findViewById(R.id.img1);
        t1=(TextView)findViewById(R.id.id1);

        Typeface type1 = Typeface.createFromAsset(getAssets(),"font/Lobster_1.3.otf");
        t1.setTypeface(type1);


        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        img.startAnimation(animation);
        Thread mythread=new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(5000);
                    Intent intent=new Intent(getBaseContext(),MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        mythread.start();

    }
}
