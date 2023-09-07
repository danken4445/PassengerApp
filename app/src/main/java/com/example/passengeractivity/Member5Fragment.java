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

public class Member5Fragment extends Fragment {

    private ScrollView scrollView;
    private ImageView memberPicture;
    private TextView memberName;
    private TextView memberRole;
    private TextView memberInfo;
    private ImageView facebookIcon;
    private ImageView instagramIcon;
    private ImageView linkedInIcon;

    public Member5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member5, container, false);


        scrollView = view.findViewById(R.id.cardView);
        memberPicture = view.findViewById(R.id.memberPicture);
        memberName = view.findViewById(R.id.memberName);
        memberRole = view.findViewById(R.id.memberRole);
        memberInfo = view.findViewById(R.id.memberInfo);
        facebookIcon = view.findViewById(R.id.facebookIcon);
        instagramIcon = view.findViewById(R.id.instagramIcon);
        linkedInIcon = view.findViewById(R.id.linkedInIcon);

        // Set click listeners for social media icons
        setFacebookLink("https://www.facebook.com/profile.php?id=100069723712361", requireContext());
        setInstagramLink("https://l.facebook.com/l.php?u=https%3A%2F%2Finstagram.com%2Fali.rgsj%3Futm_source%3Dqr%26igshid%3DMzNlNGNkZWQ4Mg%253D%253D%26fbclid%3DIwAR3u-lYZ_f-pau-wKJnHl7BlFAn5IYBvVzd7l1U7h58hKTnlpiUvJ2wkmF0&h=AT2QeAuMws3i_iEqbMQh7q03anV2TeYQ2QZ-CUzWp6y_mZH_X91qR79mNG87m1u2IxXh3UClPk_nBccLCC5Dl6fVGlXYlxZjOZHZJcCSgzOBv_sQyuyXXtXvxKbh39B5sl4_5A", requireContext());
        setLinkedInLink("https://l.facebook.com/l.php?u=https%3A%2F%2Fwww.linkedin.com%2Fin%2Fali-undefined-03b21528b%3Ffbclid%3DIwAR0dfz0k8NoYnJKuRFWnK1O2oNiMcu9yPIrAriPgCMWbz4N-IJ4Q41s_Z-0&h=AT2QeAuMws3i_iEqbMQh7q03anV2TeYQ2QZ-CUzWp6y_mZH_X91qR79mNG87m1u2IxXh3UClPk_nBccLCC5Dl6fVGlXYlxZjOZHZJcCSgzOBv_sQyuyXXtXvxKbh39B5sl4_5A", requireContext());

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
