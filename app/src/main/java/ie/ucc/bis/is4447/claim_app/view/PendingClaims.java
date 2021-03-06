package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.android.volley.Response;


import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Claim;
import ie.ucc.bis.is4447.claim_app.helper.ClaimAdapter;
import ie.ucc.bis.is4447.claim_app.helper.Connection;
import ie.ucc.bis.is4447.claim_app.helper.InBackgroundClass;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class PendingClaims extends AppCompatActivity {

    SessionManager sessionManager;
    String mEmail;

    private ClaimAdapter adapter;
    //a list to store all the products
    List<Claim> claimList;

    private TextView no_claims;
    private static final String TAG = "MyActivity";

    //the recyclerview
    RecyclerView recyclerView;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_claims);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mEmail = user.get(sessionManager.EMAIL);

        no_claims = findViewById(R.id.no_claims);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the claimList
        claimList = new ArrayList<>();

        InBackgroundClass myClass = new InBackgroundClass(PendingClaims.this);
        myClass.execute("https://vendorcentral.000webhostapp.com/ApiClaim.php?approveremail=" + mEmail);

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();



        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ClaimAdapter adapter = new ClaimAdapter(PendingClaims.this, claimList);
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);

                no_claims = findViewById(R.id.no_claims);


                //initializing the claimList
                claimList = new ArrayList<>();


                loadProducts();
            }
        });
    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://vendorcentral.000webhostapp.com/ApiClaim.php?approveremail=" + mEmail,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("Claim")) {


                        } else {
                            Toast.makeText(PendingClaims.this, "No Claims to Approve.", Toast.LENGTH_LONG).show();
                            no_claims.setVisibility(View.VISIBLE);
                        }
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {



                                //getting claim object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the claim to claim list
                                claimList.add(new Claim(
                                        product.getInt("ClaimID"),
                                        product.getString("InvoiceNum"),
                                        product.getString("Status"),
                                        product.getString("customer_reason"),
                                        product.getString("claim_type"),
                                        product.getString("offercode"),
                                        product.getString("settlement"),
                                        product.getDouble("amount"),
                                        product.getString("invoice_date"),
                                        product.getString("creation_date"),
                                        product.getInt("Cus_ID"),
                                        product.getString("Cus_Name"),
                                        product.getString("BillTo"),
                                        product.getString("BillToAcc"),
                                        product.getString("ShipTo"),
                                        product.getString("Approver"),
                                        product.getString("ApproverEmail"),
                                        product.getString("OperatingUnit"),
                                        product.getString("Currency"),
                                        product.getString("ClaimNum"),
                                        product.getString("ShipToAcc"),
                                        product.getString("Creator"),
                                        product.getString("Overage"),
                                        product.getString("notes"),
                                        product.getString("Processor"),
                                        product.getString("approval_level"),
                                        product.getString("lastupdated_by"),
                                        product.getString("lastupdate"),
                                        product.getInt("request_id"),
                                        product.getString("Action_Date4")
                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ClaimAdapter adapter = new ClaimAdapter(PendingClaims.this, claimList);
                            recyclerView.setAdapter(adapter);



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

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
