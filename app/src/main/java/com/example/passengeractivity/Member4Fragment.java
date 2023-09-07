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

public class Member4Fragment extends Fragment {

    private ScrollView scrollView;
    private ImageView memberPicture;
    private TextView memberName;
    private TextView memberRole;
    private TextView memberInfo;
    private ImageView facebookIcon;
    private ImageView instagramIcon;
    private ImageView linkedInIcon;

    public Member4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member4, container, false);


        scrollView = view.findViewById(R.id.cardView);
        memberPicture = view.findViewById(R.id.memberPicture);
        memberName = view.findViewById(R.id.memberName);
        memberRole = view.findViewById(R.id.memberRole);
        memberInfo = view.findViewById(R.id.memberInfo);
        facebookIcon = view.findViewById(R.id.facebookIcon);
        instagramIcon = view.findViewById(R.id.instagramIcon);
        linkedInIcon = view.findViewById(R.id.linkedInIcon);

        // Set click listeners for social media icons
        setFacebookLink("https://web.facebook.com/Argie.Gatdula.28", requireContext());
        setInstagramLink("https://web.facebook.com/Argie.Gatdula.28", requireContext());
        setLinkedInLink("https://l.facebook.com/l.php?u=http%3A%2F%2Fwww.linkedin.com%2Fin%2Fjames-gatdula-275b8b28b%3Ffbclid%3DIwAR1-_Cur0Hf9Gz-rV5FVNX2G_FkVME2dNGLPtPdT_6DcYxsqmieKWv-zptk&h=AT1AJT6vHtWDx4KA7Y2wrjPBHKpfT2OtaPrgO8fH9S64f1CyrpQiL7_dYYmi7RHazWgVFd3PuYoMy9lGj7OJf-6PcYnnNu669w3dSJQAB_1FuYDidBy51CXrMdT-gzZ-4Vqzzg", requireContext());

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
