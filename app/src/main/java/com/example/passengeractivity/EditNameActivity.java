package com.example.passengeractivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditNameActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    TextInputEditText editFirstname, editLastname, editPassword, editUsername;
    Button saveButton;
    private ImageView userEditProfile;
    private ImageButton buttonEditProfile;
    private String Pid;
    private String URL_dbGetHis = "http://dbgrp52-001-site1.ctempurl.com/ridepay/editpassinfo.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        // Get the user's unique identifier passed from UserProfile activity

        userEditProfile = findViewById(R.id.userEditProfile);
        buttonEditProfile = findViewById(R.id.buttonEditProfile);

        Pid = getIntent().getStringExtra("Pid");
        editPassword = findViewById(R.id.editPassword);


        saveButton = findViewById(R.id.buttonSave);

        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open image selection options (gallery or camera)
                showImageSelectionOptions();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the updated name from the EditText
                //String updatedName = usernameEditText.getText().toString();
                // Call the method to update the user profile
                updateUserProfile();
            }
        });
    }

    private void showImageSelectionOptions() {
        // ... (your code to open gallery or camera)
    }

    private void updateUserProfile() {
        final String Pid = this.Pid;
        final String editPassword = this.editPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_dbGetHis,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Response ", response);
                            JSONObject jsonObject = new JSONObject(response);
                            String Success = jsonObject.getString("Success");

                            if (Success.equals("1")) {
                                String mess = jsonObject.getString("Message");
                                Toast.makeText(EditNameActivity.this, mess, Toast.LENGTH_SHORT).show();

                            } else {
                                String mess = jsonObject.getString("Message");
                                Toast.makeText(EditNameActivity.this, mess, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EditNameActivity.this, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditNameActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Pid", Pid);
                params.put("editPassword", editPassword);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditNameActivity.this);
        requestQueue.add(stringRequest);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // User has successfully selected an image from the gallery
            Uri imageUri = data.getData();

            // Create an Intent to return the updated image URI to UserProfile activity
            Intent intent = new Intent();
            intent.putExtra("updatedImageUri", imageUri.toString());

            // Set the result to indicate success and provide the updated data
            setResult(RESULT_OK, intent);

            // Finish this activity and return to UserProfile
            finish();


        }
    }
}
