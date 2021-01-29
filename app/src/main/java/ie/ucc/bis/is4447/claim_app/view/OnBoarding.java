package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.SliderAdapter;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsGetStarted;
    Animation animation;
    int currentPosition;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letsGetStarted = findViewById(R.id.get_started_btn);

        //Call adapter

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);


    }
    //Skip Button: go to Login
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), UserLoginActivity.class));
        finish();
    }

    public void next(View view){
        viewPager.setCurrentItem(currentPosition + 1);
    }

    private void addDots(int position) {
        // change dot color based on the slide position
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.second));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // Display and animation for button when on last page
            addDots(position);
            currentPosition = position;

            if (position == 0) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Welcome slide is visible");
            } else if (position == 1) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Saving slide is visible");
            } else if (position == 2) {
                letsGetStarted.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Tracking slide is visible");
            } else if (position == 3) {
                animation = AnimationUtils.loadAnimation(OnBoarding.this,R.anim.boarding_animation);
                letsGetStarted.setAnimation(animation);
                letsGetStarted.setVisibility(View.VISIBLE);
                Log.d(TAG, "Last slide is visible");
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}