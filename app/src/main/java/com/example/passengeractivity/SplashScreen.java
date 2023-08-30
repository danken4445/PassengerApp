package com.example.passengeractivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.passengeractivity.LoginActivity;

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 3000; // Adjust the duration as needed
    private ImageView busImageView;
    private ImageView logo;
    private TextView welcome;
    private TextView acro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Assuming you have already defined your TextView in your activity layout XML with an id like "sloganTextView"
        TextView sloganTextView = findViewById(R.id.sloganText);

// Set the text of the TextView to your slogan
        String slogan = "Ride Easy, Pay Swiftly with RIDEPAY: Transforming Transportation for a Smarter, Safer, and Greener Future";
        sloganTextView.setText(slogan);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "payment_channel",
                    "Payment Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for successful payments");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        busImageView = findViewById(R.id.bus_logo);
        welcome = findViewById(R.id.companyName);
        acro = findViewById(R.id.sloganText);

        Animation moveLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.bus_move_left);
        Animation moveRightAnimation = AnimationUtils.loadAnimation(this, R.anim.bus_move_right);

        busImageView.setAnimation(moveLeftAnimation);

        moveLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }


            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated (if needed)
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                Pair<View, String>[] pairs = new Pair[]{
                        Pair.create((View) busImageView, "logo_image"),
                        Pair.create((View) welcome, "logo_text"),
                };

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs);
                startActivity(intent, options.toBundle());
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}


