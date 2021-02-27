package ie.ucc.bis.is4447.claim_app.view;
// Code taken from online tutorial modified for my project
//https://androidjson.com/android-server-login-registration-php-mysql/

//Connection Check
// https://stackoverflow.com/questions/21254555/how-to-check-internet-connectivity-continuously-in-background-while-android-appl

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Connection;
import ie.ucc.bis.is4447.claim_app.helper.HttpParse;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class UserLoginActivity extends AppCompatActivity {

    // to check if we are connected to Network
    boolean isConnected = true;

    // to check if we are monitoring Network
    private boolean monitoringConnectivity = false;

    private TextView validation;
    EditText Email, Password;
    Button LogIn ;
    String PasswordHolder, EmailHolder;
    String finalResult ;
    String HttpURL = "https://vendorcentral.000webhostapp.com/UserLogin.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    public static final String UserEmail = "";

    private static final String TAG = "MyActivity";

    SessionManager sessionManager;

    private ConnectivityManager.NetworkCallback connectivityCallback
            = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(Network network) {
            isConnected = true;
            startActivity(new Intent(UserLoginActivity.this, UserLoginActivity.class));
            Log.d(TAG, "INTERNET CONNECTED");
        }

        @Override
        public void onLost(Network network) {
            isConnected = false;
            startActivity(new Intent(UserLoginActivity.this, Connection.class));
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
            startActivity(new Intent(UserLoginActivity.this, Connection.class));
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        sessionManager = new SessionManager(this);

        Email = (EditText)findViewById(R.id.tvUser);
        Password = (EditText)findViewById(R.id.tvPass);
        LogIn = (Button)findViewById(R.id.Login);
        validation = findViewById(R.id.tvValidation);


        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    UserLoginFunction(EmailHolder, PasswordHolder);

                }
                else {
                    validation.setVisibility(View.VISIBLE);
                    validation.setText("Please entry Username and Password to Login");

                    Log.d(TAG, "Fields were empty");
                }

            }
        });
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


    public void CheckEditTextIsEmptyOrNot(){

        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {
            CheckEditText = false;
        }
        else {

            CheckEditText = true ;
        }
    }

    public void UserLoginFunction(final String email, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(UserLoginActivity.this,"Validating Credentials",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                if(httpResponseMsg.equalsIgnoreCase("Data Matched")){

                    sessionManager.createSession(email);

                    finish();

                    Intent intent = new Intent(UserLoginActivity.this, Dashboard.class);

                    intent.putExtra(UserEmail,email);

                    startActivity(intent);
                    Log.d(TAG, "Login Successful");

                }
                else{

                    Toast.makeText(UserLoginActivity.this,httpResponseMsg,Toast.LENGTH_LONG).show();
                    validation.setVisibility(View.VISIBLE);
                    validation.setText("Invalid Credentials used. Please try again");
                    Log.d(TAG, "Login Failed");
                }

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("email",params[0]);

                hashMap.put("password",params[1]);

                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserLoginClass userLoginClass = new UserLoginClass();

        userLoginClass.execute(email,password);
    }

}