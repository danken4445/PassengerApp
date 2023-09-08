package com.example.passengeractivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Find your CardViews by their IDs
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);
        CardView cardView6 = findViewById(R.id.cardView6);
        CardView cardView7 = findViewById(R.id.cardView7);

        // Set an OnClickListener for each CardView
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView1);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView1.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member1Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView2);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView2.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member2Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView3);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView3.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member3Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView4);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView4.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member4Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView5);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView5.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member5Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView6);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView6.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member6Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Animate the CardView when clicked
                animateCardView(cardView7);
                Animation zoomInAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

                // Apply the animation to the CardView
                cardView7.startAnimation(zoomInAnimation);

                // Replace the fragment with Member1Fragment
                // Add a delay of 500 milliseconds (adjust as needed)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Replace the fragment with Member1Fragment
                        replaceFragment(new Member7Fragment());
                    }
                }, 500); // 500 milliseconds delay
            }
        });

    }

    private void animateCardView(CardView cardView) {
        // Define animation parameters
        final int animationDuration = 400; // in milliseconds
        final float startScale = 1.0f;
        final float endScale = 1.1f;

        // Create a ValueAnimator for scaling
        ValueAnimator animator = ValueAnimator.ofFloat(startScale, endScale);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(animationDuration);

        // Update the CardView's scale during animation
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                cardView.setScaleX(scale);
                cardView.setScaleY(scale);
            }
        });

        // Set an animation listener to reset the CardView after the animation completes
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cardView.setScaleX(startScale);
                cardView.setScaleY(startScale);
            }
        });

        // Start the animation
        animator.start();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
