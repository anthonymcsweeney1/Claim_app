package ie.ucc.bis.is4447.claim_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class Dashboard extends AppCompatActivity {

    private Button btnConn, btnPending, btnPast;
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

        btnConn = findViewById(R.id.btnConnection);
        btnPending = findViewById(R.id.btnPending);
        btnPast = findViewById(R.id.btnPast);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mEmail = user.get(sessionManager.EMAIL);

        email.setText("Hello "+ mEmail);

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
                        startActivity(new Intent(getApplicationContext(),PendingClaims.class));
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
        btnPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, PendingClaims.class);
                startActivity(intent);
                Log.i(TAG, "View Pending Claims Clicked");

            }
        });
        // Navigate to input page
        btnPast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, OnBoarding.class);
                startActivity(intent);
                Log.d(TAG, "Add new clicked");
            }
        });

        //Check connection to internet
        btnConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    Toast.makeText(Dashboard.this, "Devise is connected to the internet", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Devise is connected to the internet");
                } else {
                    Toast.makeText(Dashboard.this, "Devise is not connected to the internet", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Devise is not connected to the internet");
                }
            }
        });
    }
}