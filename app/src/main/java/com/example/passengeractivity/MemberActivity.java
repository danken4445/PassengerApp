package com.example.passengeractivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        // Get the fragment manager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a transaction to replace the container with the fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Member1Fragment member1Fragment = new Member1Fragment(); // Instantiate your fragment
        fragmentTransaction.replace(R.id.fragment_container, member1Fragment);
        fragmentTransaction.commit();
    }
}
