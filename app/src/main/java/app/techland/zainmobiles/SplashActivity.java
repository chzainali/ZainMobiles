package app.techland.zainmobiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
TextView mText1,mText2,mText3;
Animation frombottom,fromtop,righttoleft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mText1 = findViewById(R.id.mText1);
        mText2 = findViewById(R.id.mText2);
        mText3 = findViewById(R.id.mText3);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        righttoleft = AnimationUtils.loadAnimation(this,R.anim.righttoleft);
        mText1.setAnimation(fromtop);
        mText2.setAnimation(frombottom);
        mText3.setAnimation(righttoleft);

        final Thread thread =  new Thread(){
            @Override
            public  void run(){
                try {
                    sleep(5000);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    Toast.makeText(SplashActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }

        };
        thread.start();
    }
}