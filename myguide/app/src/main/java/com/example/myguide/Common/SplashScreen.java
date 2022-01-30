package com.example.myguide.Common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myguide.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    ImageView backgroundImage;
    TextView poweredByLine;

    Animation sideAnim,bottomAnim;
    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.LAST_APPLICATION_WINDOW,WindowManager.LayoutParams.LAST_APPLICATION_WINDOW);
        setContentView(R.layout.splash_screen);

        backgroundImage = findViewById(R.id.background_image);
        poweredByLine = findViewById(R.id.powered_by_line);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);


        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("firstTime",MODE_PRIVATE).edit();
                    editor.putBoolean("firstTime",false);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }

                else{
                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
            }


    },SPLASH_TIMER );
}
}