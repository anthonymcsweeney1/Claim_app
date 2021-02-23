package ie.ucc.bis.is4447.claim_app.view;
//code use for page display
//https://www.youtube.com/watch?v=d6CfaWW7G5Q

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Connection;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    private CardView cardPending, cardApproved, cardRejected;
    private ImageView imPending, imApproved;
    private TextView email;
    SessionManager sessionManager;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        email = findViewById(R.id.tvemail);


        //defining Cards
        cardPending = findViewById(R.id.cardPending);
        cardApproved =  findViewById(R.id.cardApproved);
        cardRejected =  findViewById(R.id.cardRejected);


        //Add Click listener
        cardPending.setOnClickListener(this);
        cardApproved.setOnClickListener(this);
        cardRejected.setOnClickListener(this);


        HashMap<String, String> user = sessionManager.getUserDetail();
        String mEmail = user.get(sessionManager.EMAIL);

        email.setText(mEmail);

        // Bottom Navigation
        bottomnav = findViewById(R.id.bottomnav);
        bottomnav.setSelectedItemId(R.id.item_home);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // switch case for each activity
                switch (item.getItemId()){
                    case R.id.item_home:
                        return true;
                    case R.id.item_pending:
                        startActivity(new Intent(getApplicationContext(),OnBoarding.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item_logout:
                       sessionManager.logout();
                        return true;
                }
                return false;
            }
        });

        // Navigate to recycler view page
//        btnPending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Dashboard.this, PendingClaims.class);
//                startActivity(intent);
//                Log.i(TAG, "View Pending Claims Clicked");
//
//            }
//        });
        // Navigate to input page
//        btnPast.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Dashboard.this, OnBoarding.class);
//                startActivity(intent);
//                Log.d(TAG, "Add new clicked");
//            }
//        });

        //Check connection to internet
//        btnConn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
//
//                if (networkInfo != null && networkInfo.isConnected()) {
//                    Toast.makeText(Dashboard.this, "Devise is connected to the internet", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Devise is connected to the internet");
//                } else {
//                    Toast.makeText(Dashboard.this, "Devise is not connected to the internet", Toast.LENGTH_LONG).show();
//                    Log.d(TAG, "Devise is not connected to the internet");
//                }
//            }
//        });




    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
        case R.id.cardPending : i = new Intent (this, PendingClaims.class); startActivity(i); break;
        case R.id.cardApproved : i = new Intent (this, Approve.class); startActivity(i); break;
            case R.id.cardRejected : i = new Intent (this, Rejected.class); startActivity(i); break;

            default: break;
    }
    }

    // to check if we are connected to Network
    boolean isConnected = true;

    // to check if we are monitoring Network
    private boolean monitoringConnectivity = false;


    private ConnectivityManager.NetworkCallback connectivityCallback
            = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            isConnected = true;

            Log.d(TAG, "INTERNET CONNECTED");
        }

        @Override
        public void onLost(Network network) {
            isConnected = false;
            startActivity(new Intent(Dashboard.this, Connection.class));
            Log.d(TAG, "INTERNET LOST");
        }
    };

    // Method to check network connectivity in Main Activity
    private void checkConnectivity() {
        // here we are getting the connectivity service from connectivity manager
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);

        // Getting network Info
        // give Network Access Permission in Manifest
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        // isConnected is a boolean variable
        // here we check if network is connected or is getting connected
        isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

        if (!isConnected) {
            startActivity(new Intent(Dashboard.this, Connection.class));
            Log.d(TAG, "NO NETWORK");

// if Network is not connected we will register a network callback to  monitor network
            connectivityManager.registerNetworkCallback(
                    new NetworkRequest.Builder()
                            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                            .build(), connectivityCallback);
            monitoringConnectivity = true;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConnectivity();
    }

    @Override
    protected void onPause() {
        // if network is being moniterd then we will unregister the network callback
        if (monitoringConnectivity) {
            final ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.unregisterNetworkCallback(connectivityCallback);
            monitoringConnectivity = false;
        }
        super.onPause();
    }


}