package com.example.passengeractivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.passengeractivity.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button signInButton;

    private String defaultUsername = "user";
    private String defaultPassword = "password";
    private String Pid, UserUsername, UserPassword, userBalance, userFirstname, userLastname;

    private String URL_dbPassenger = "http://group5db-001-site1.etempurl.com/ridepay/passengerlogin.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);


        TextView acronymTextView = findViewById(R.id.Acronym);
        String text = "Rapid Integrated Digital E-PAYment";

        SpannableString spannableString = new SpannableString(text);

        // Define the color you want (#d8a13c)
        int customColor = Color.parseColor("#d8a13c");

        // Use regex to split the text by spaces and non-word characters
        String[] words = text.split("[\\s\\W]+");

        for (String word : words) {
            // Get the index of the start of each word
            int startIndex = text.indexOf(word);

            // Apply the custom color span to the entire word
            spannableString.setSpan(new ForegroundColorSpan(customColor), startIndex, startIndex + word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Make the first letter of each word bigger
            spannableString.setSpan(new RelativeSizeSpan(2f), startIndex, startIndex + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // Set the formatted text in the TextView
        acronymTextView.setText(spannableString);


        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 String username = usernameEditText.getText().toString();
                 String password = passwordEditText.getText().toString();

                 if (username.equals(defaultUsername) && password.equals(defaultPassword)) {
                 // Successful login logic
                 Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                 navigateToUserProfile();
                 } else {
                 // Failed login logic
                 Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();

                 // Apply move-right animation to EditTexts
                 ObjectAnimator shakeAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, -10, 10, -10, 10, -5, 5, -2, 2, 0);
                 shakeAnimator.setDuration(500);
                 shakeAnimator.start();

                 }**/
                final String enteredUsername = usernameEditText.getText().toString();
                final String enteredPassword = passwordEditText.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_dbPassenger,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Log.d("Response", response);
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("Success");


                                    if (success.equals("1")) {
                                        String message = jsonObject.getString("Message");
                                        Pid = jsonObject.getString("Pid");
                                        UserUsername = jsonObject.getString("UserUsername");
                                        UserPassword = jsonObject.getString("UserPassword");
                                        userBalance = jsonObject.getString("Balance");
                                        userFirstname = jsonObject.getString("fName");
                                        userLastname = jsonObject.getString("lName");


                                        navigateToUserProfile();
                                        Toast.makeText(LoginActivity.this, Pid, Toast.LENGTH_SHORT).show();
                                    } else {
                                        String message = jsonObject.getString("Message");
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                        ObjectAnimator shakeAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, -10, 10, -10, 10, -5, 5, -2, 2, 0);
                                        shakeAnimator.setDuration(500);
                                        shakeAnimator.start();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(LoginActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle network request errors here
                                Toast.makeText(LoginActivity.this, "Network Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("enteredUsername", enteredUsername);
                        params.put("enteredPassword", enteredPassword);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(stringRequest);
            }

        });


    }

    private void navigateToUserProfile() {
        Intent intent = new Intent(LoginActivity.this, UserProfile.class);
        intent.putExtra("Pid", Pid);
        intent.putExtra("UserUsername", UserUsername);
        intent.putExtra("UserPassword", UserPassword);
        intent.putExtra("userBalance", userBalance);
        intent.putExtra("userFirstname", userFirstname);
        intent.putExtra("userLastname", userLastname);

        startActivity(intent);
    }

    private void applyMoveRightAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bus_move_right);
        view.startAnimation(animation);
    }
}