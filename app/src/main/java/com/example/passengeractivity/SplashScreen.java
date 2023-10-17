package com.example.passengeractivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.passengeractivity.LoginActivity;

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 3300; // Adjust the duration as needed
    private ImageView busImageView;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Assuming you have already defined your TextView in your activity layout XML with an id like "sloganTextView"
        TextView sloganTextView = findViewById(R.id.sloganText);
        busImageView = findViewById(R.id.bus_logo);


        // Set the text of the TextView to your slogan
        String slogan = "Ride Easy, Pay Swiftly with RIDEPAY: Transforming Transportation for a Smarter, Safer, and Greener Future";
        sloganTextView.setText(slogan);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                Pair<View, String>[] pairs = new Pair[]{
                        Pair.create(busImageView, "logo_image"),
                };

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
