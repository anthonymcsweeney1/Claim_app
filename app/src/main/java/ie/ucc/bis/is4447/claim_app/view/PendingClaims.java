package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import com.android.volley.Response;


import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Claim;
import ie.ucc.bis.is4447.claim_app.helper.ClaimAdapter;

public class PendingClaims extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "https://vendorcentral.000webhostapp.com/ApiClaim.php";

    private ClaimAdapter adapter;
    //a list to store all the products
    List<Claim> claimList;

    private TextView no_claims;


    //the recyclerview
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_claims);


        no_claims = findViewById(R.id.no_claims);
        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the claimList
        claimList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();

    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                                        product.getInt("request_id")
                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ClaimAdapter adapter = new ClaimAdapter(PendingClaims.this, claimList);
                            recyclerView.setAdapter(adapter);

                            // if i = 0

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
