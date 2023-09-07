package com.example.passengeractivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Member2Fragment extends Fragment {

    private ScrollView scrollView;
    private ImageView memberPicture;
    private TextView memberName;
    private TextView memberRole;
    private TextView memberInfo;
    private ImageView facebookIcon;
    private ImageView instagramIcon;
    private ImageView linkedInIcon;

    public Member2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member2, container, false);


        scrollView = view.findViewById(R.id.cardView);
        memberPicture = view.findViewById(R.id.memberPicture);
        memberName = view.findViewById(R.id.memberName);
        memberRole = view.findViewById(R.id.memberRole);
        memberInfo = view.findViewById(R.id.memberInfo);
        facebookIcon = view.findViewById(R.id.facebookIcon);
        instagramIcon = view.findViewById(R.id.instagramIcon);
        linkedInIcon = view.findViewById(R.id.linkedInIcon);

        // Set click listeners for social media icons
        setFacebookLink("https://www.facebook.com/dankenshen.penera", requireContext());
        setInstagramLink("https://www.instagram.com/dnknshn/", requireContext());
        setLinkedInLink("https://www.linkedin.com/in/dan-ken-shen-uriarte-penera-864199290/", requireContext());

        // You can set the member's details like name, role, and picture here

        return view;
    }

    private void setInstagramLink(final String instagramUrl, final Context context) {
        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + instagramUrl));
                    context.startActivity(intent);
                } catch (Exception e) {
                    // If Facebook app is not installed, open in the browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
                    context.startActivity(intent);
                }
            }
        });
    }

    private void setLinkedInLink(final String linkedInUrl, final Context context) {
        instagramIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + linkedInUrl));
                    context.startActivity(intent);
                } catch (Exception e) {
                    // If Facebook app is not installed, open in the browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl));
                    context.startActivity(intent);
                }
            }
        });
    }


    // Rest of your methods for setting member details and handling social media links

    private void setFacebookLink(final String facebookUrl, final Context context) {
        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=" + facebookUrl));
                    context.startActivity(intent);
                } catch (Exception e) {
                    // If Facebook app is not installed, open in the browser
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
                    context.startActivity(intent);
                }
            }
        });
    }

    // Implement setInstagramLink and setLinkedInLink similarly

}
