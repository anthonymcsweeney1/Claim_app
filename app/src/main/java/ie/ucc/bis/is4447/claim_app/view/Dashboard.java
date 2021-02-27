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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.ActionedAdapter;
import ie.ucc.bis.is4447.claim_app.helper.Claim;
import ie.ucc.bis.is4447.claim_app.helper.Connection;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    private CardView cardPending, cardApproved, cardRejected;
    private TextView email;
    SessionManager sessionManager;
    String mEmail;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mEmail = user.get(sessionManager.EMAIL);

        email = findViewById(R.id.tvemail);


        //defining Cards
        cardPending = findViewById(R.id.cardPending);
        cardApproved =  findViewById(R.id.cardApproved);
        cardRejected =  findViewById(R.id.cardRejected);


        //Add Click listener
        cardPending.setOnClickListener(this);
        cardApproved.setOnClickListener(this);
        cardRejected.setOnClickListener(this);


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


        loadName();

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

    private void loadName() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://vendorcentral.000webhostapp.com/getName.php?user_name="  + mEmail,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        email.setText( "Hello " +response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }





}