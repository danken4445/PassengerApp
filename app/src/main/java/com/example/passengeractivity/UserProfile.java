package com.example.passengeractivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;

import com.airbnb.lottie.LottieDrawable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import com.airbnb.lottie.LottieAnimationView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView; // Import TextView
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    // Request code for starting EditNameActivity
    static final int EDIT_NAME_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE = 3;
    Button viewQrCodeButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView showMap;
    LinearLayout contentView;
    LottieAnimationView leftCloudAnimationView;
    LottieAnimationView rightCloudAnimationView;
    LinearLayout contentView2;
    ConstraintLayout contentView3;
    String Pid, qrid, UserUsername, UserPassword, userFirstname, userLastname;
    private int userBalance;
    private LottieAnimationView chatbotIcon;
    private String URL_dbgetInfo = "http://group5db-001-site1.etempurl.com/ridepay/userprofile.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        CardView viewQrCodeCardView = findViewById(R.id.viewQrCodeCardView);
        TextView rideCoinBalanceTextView = findViewById(R.id.rideCoinBalance);
        TextView textViewUserName = findViewById(R.id.textViewUserName);
        TextView textViewID = findViewById(R.id.textViewID2);

        chatbotIcon = findViewById(R.id.chatbotIcon);


        // User Information
        Pid = getIntent().getStringExtra("Pid");
        qrid = getIntent().getStringExtra(("QRID"));
        UserUsername = getIntent().getStringExtra("UserUsername");
        UserPassword = getIntent().getStringExtra("UserPassword");
        userBalance = Integer.parseInt(getIntent().getStringExtra("userBalance"));
        userFirstname = getIntent().getStringExtra("userFirstname");
        userLastname = getIntent().getStringExtra("userLastname");
        showMap = findViewById(R.id.showMap);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        contentView = findViewById(R.id.linearLayout);
        contentView2 = findViewById(R.id.linearLayout2);
        contentView3 = findViewById(R.id.constraintLayout);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        rideCoinBalanceTextView.setText("₱" + userBalance + ".00");
        textViewUserName.setText(userFirstname);
        textViewID.setText(Pid);


        LottieAnimationView animationView = findViewById(R.id.locationlottie);
        animationView.setAnimation(R.raw.location3); // Set the animation resource
        animationView.setSpeed(0.5f); // Slow down the animation by half (0.5x speed)
        animationView.playAnimation(); // Start the animation

        LottieAnimationView animationView2;
        animationView2 = findViewById(R.id.qranim);
        animationView2.setAnimation(R.raw.qr6);
        animationView2.setRepeatCount(1);
        animationView2.setSpeed(0.5f); // Slow down the animation by half (0.5x speed)
        animationView2.playAnimation(); // Start the animation


        naviagtionDrawer();

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        checkNotificationPermissionAndShowNotification();

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderPid = headerView.findViewById(R.id.nav_header_pid);
        TextView navHeaderName = headerView.findViewById(R.id.nav_header_name);


// Set the values
        navHeaderPid.setText(Pid);
        navHeaderName.setText(userFirstname + " " + userLastname);

        // Replace with your actual balance

// Check if the rideCoinBalance is below a certain threshold
        if (userBalance <= 50) { // You can adjust the threshold as needed
            // Create a notification builder for low balance
            NotificationCompat.Builder lowBalanceBuilder = new NotificationCompat.Builder(this, "payment_channel")
                    .setSmallIcon(R.drawable.buslogo1)
                    .setContentTitle("Low Balance")
                    .setContentText("Your balance is running low, please Top up.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Create a unique notification ID for the low balance notification
            int lowBalanceNotificationId = 2; // Use a different ID than the payment success notification

            // Issue the low balance notification
            NotificationManagerCompat lowBalanceNotificationManager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Handle permission request or inform the user about the missing permission
            }
            lowBalanceNotificationManager.notify(lowBalanceNotificationId, lowBalanceBuilder.build());
        } else if (userBalance > 0) { // Balance added
            // Create a notification builder for top-up success
            NotificationCompat.Builder topUpSuccessBuilder = new NotificationCompat.Builder(this, "payment_channel")
                    .setSmallIcon(R.drawable.buslogo1)
                    .setContentTitle("Top Up Successful")
                    .setContentText("Your top-up was successful.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Create a unique notification ID for the top-up success notification
            int topUpSuccessNotificationId = 3; // Use a different ID than the low balance notification

            // Issue the top-up success notification
            NotificationManagerCompat topUpSuccessNotificationManager = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Handle permission request or inform the user about the missing permission
            }
            topUpSuccessNotificationManager.notify(topUpSuccessNotificationId, topUpSuccessBuilder.build());
        }


        viewQrCodeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the ViewQrCodeActivity when the card view is clicked
                Intent intent = new Intent(UserProfile.this, ViewQrCodeActivity.class);
                // Pass any necessary data to ViewQrCodeActivity using intent extras
                // For example, you can pass the user's ID (Pid) if needed:
                intent.putExtra("QRID", qrid);
                startActivity(intent);
            }
        });
        chatbotIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, RPChatBotActivity.class);
                // Pass any necessary data to ViewQrCodeActivity using intent extras
                // For example, you can pass the user's ID (Pid) if needed:
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            // Show a confirmation dialog before logging out
            Log.d("UserProfile", "Log Out menu item clicked.");
            showLogoutConfirmationDialog();
            return true;
        }

        // Handle other menu items here

        return super.onOptionsItemSelected(item);
    }

    // Function to show a logout confirmation dialog
    private void showLogoutConfirmationDialog() {
        Log.d("UserProfile", "Showing logout confirmation dialog.");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Log Out", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform logout actions here (e.g., clear user session data)
                Log.d("UserProfile", "Logging out...");

                // Start the LoginActivity
                Intent intent = new Intent(UserProfile.this, LoginActivity.class);
                startActivity(intent);

                // Finish the current activity to prevent the user from returning
                // to the UserProfileActivity when pressing the back button.
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User canceled the logout, do nothing
                Log.d("UserProfile", "Logout canceled.");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void onCardViewClick(View view) {

        Intent intent = new Intent(UserProfile.this, MapsActivity.class);

        // Set up the shared element transition
        Pair<View, String> pair = Pair.create(view.findViewById(R.id.showMap), "mapTransition");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair);

        // Start the MapsActivity with the shared element transition
        startActivity(intent, options.toBundle());
    }

    private void startMapsActivity() {
        // Transition to the MapsActivity here
        Intent intent = new Intent(UserProfile.this, MapsActivity.class);
        startActivity(intent);
    }


    private void naviagtionDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);


            }
        });
        animateNavigationDrawer();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a notification channel
            NotificationChannel channel = new NotificationChannel(
                    "payment_channel",
                    "Payment Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for successful payments");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

// Create a notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "payment_channel")
                .setSmallIcon(R.drawable.buslogo1)
                .setContentTitle("Payment Success")
                .setContentText("Your payment was successful.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

// Create a unique notification ID
        int notificationId = 1;

// Issue the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(notificationId, builder.build());

    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            static final float END_SCALE = 0.7f;

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView3.setScaleX(offsetScale);
                contentView3.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView3.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_changeName) {
            // Handle the "Edit Name and Info" menu item click
            startEditNameActivity();
        } else if (id == R.id.nav_home) {
            // Handle the "Home" menu item click
            // You can add code here to navigate to the Home activity or perform any other action
        } else if (id == R.id.nav_history) {
            // Handle the "History" menu item click
            // You can add code here to navigate to the History activity or perform any other action
            startHistoryActivity();
        } else if (id == R.id.nav_logout) {
            // Handle the "History" menu item click
            // You can add code here to navigate to the History activity or perform any other action
            showLogoutConfirmationDialog();
        }else if (id == R.id.nav_aboutUs) {
            // Handle the "History" menu item click
            // You can add code here to navigate to the History activity or perform any other action
            startAboutUsActivity();
        }

        // Close the drawer after handling the click
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
        private void startAboutUsActivity() {
            Intent intent = new Intent(UserProfile.this, AboutUsActivity.class);
            startActivity(intent);
        }


    private void startEditNameActivity() {
        // Create an Intent to start the EditNameActivity
        Intent intent = new Intent(this, EditNameActivity.class);
        intent.putExtra("Pid", Pid);
        // Start the activity and specify a request code
        startActivityForResult(intent, EDIT_NAME_REQUEST_CODE);
    }

    // Handle the result from EditNameActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_NAME_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Handle the updated data here, including the updated image URI
                String updatedName = data.getStringExtra("updatedName");
                String updatedImageUriString = data.getStringExtra("updatedImageUri");

                // Update the UI with the updated name
                TextView textViewUserName = findViewById(R.id.textViewUserName);
                textViewUserName.setText(updatedName);

                // Update the profile picture with the updated image URI
                ImageView userProfileImageView = findViewById(R.id.userProfile);

                if (updatedImageUriString != null) {
                    Uri updatedImageUri = Uri.parse(updatedImageUriString);
                    userProfileImageView.setImageURI(updatedImageUri);
                }
            } else {
                // Handle the case where the user canceled the operation
            }
        }
    }


    private void checkNotificationPermissionAndShowNotification() {
        // Check notification permissions and show notifications
        Dexter.withContext(this)
                .withPermission(Manifest.permission.POST_NOTIFICATIONS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        // Permission granted, you can proceed with creating notifications
                        createAndShowNotification();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        // Permission denied, handle it (e.g., inform the user)
                        // Optionally, you can redirect the user to app settings
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                })
                .check();

    }

    private void createAndShowNotification() {
        // Create and show the notification here
        // ...
    }

    private void createAndShowLowBalanceNotification() {
        // Create and show the low balance notification here
        // ...
    }

    private void startChatWithChatbot() {
        Intent intent = new Intent(UserProfile.this, RPChatBotActivity.class);
        startActivity(intent);
    }
}




