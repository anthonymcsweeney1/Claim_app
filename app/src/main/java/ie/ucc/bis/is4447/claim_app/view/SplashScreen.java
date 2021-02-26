package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import ie.ucc.bis.is4447.claim_app.R;

public class SplashScreen extends AppCompatActivity {


    //show page for 5 seconds
    private static int SPLASH_SCREEN = 5000;

    // Variables
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo;
    private static final String TAG = "MyActivity";

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.tvTitle);


        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Preference to check if its the user's first time on the app
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                Boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Log.d(TAG, "First Time - Open On Boarding!");
                    Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Log.d(TAG, "Open Login");
                    Intent intent = new Intent(SplashScreen.this, UserLoginActivity.class);
                    startActivity(intent);

                    finish();

                }

               //Intent intent = new Intent(SplashScreen.this, OnBoarding.class);
              //  startActivity(intent);
               //     finish();
               // Log.d(TAG, "Open on Boarding");

            }
        },SPLASH_SCREEN);
    }
}