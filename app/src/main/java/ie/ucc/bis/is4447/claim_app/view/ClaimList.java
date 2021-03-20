package ie.ucc.bis.is4447.claim_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Claim;
import ie.ucc.bis.is4447.claim_app.helper.ClaimAdapter;
import ie.ucc.bis.is4447.claim_app.helper.InBackgroundClass;
import ie.ucc.bis.is4447.claim_app.helper.MyDividerItemDecoration;
import ie.ucc.bis.is4447.claim_app.helper.PendingAdapter;
import ie.ucc.bis.is4447.claim_app.helper.SessionManager;

public class ClaimList extends AppCompatActivity implements PendingAdapter.ClaimAdapterListener{

    SessionManager sessionManager;
    String mEmail;

    private RecyclerView recyclerView;
    private List<Claim> claimList;
    private PendingAdapter mAdapter;
    private SearchView searchView;
    private static final String TAG = "MyActivity";

    private TextView no_claims;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_list);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mEmail = user.get(sessionManager.EMAIL);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pending Claims");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerView = findViewById(R.id.recycler_view);
        claimList = new ArrayList<>();
        mAdapter = new PendingAdapter(this, claimList, this);


        // white background notification bar
        whiteNotificationBar(recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(mAdapter);

        InBackgroundClass myClass = new InBackgroundClass(ClaimList.this);
        myClass.execute("https://vendorcentral.000webhostapp.com/ApiClaim.php?approveremail=" + mEmail);

        loadProducts();

        // refreshing recycler view
        mAdapter.notifyDataSetChanged();

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(ClaimList.this, Dashboard.class));
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
                            Toast.makeText(ClaimList.this, "No Claims to Approve.", Toast.LENGTH_LONG).show();
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

                            // refreshing recycler view
                            mAdapter.notifyDataSetChanged();



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

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClaimSelected(Claim claim) {
        //store row data for another activity
        Intent intent = new Intent(ClaimList.this, ClaimPage.class);
        intent.putExtra("ClaimID", String.valueOf(claim.getClaimID()));
        intent.putExtra("ClaimNum", String.valueOf(claim.getClaimNum()));
        intent.putExtra("InvoiceNum", String.valueOf(claim.getInvoiceNum()));
        intent.putExtra("offercode", String.valueOf(claim.getoffercode()));
        intent.putExtra("Cus_Name", String.valueOf(claim.getCus_Name()));
        intent.putExtra("Creator", String.valueOf(claim.getCreator()));
        intent.putExtra("creation_date", String.valueOf(claim.getcreation_date()));
        intent.putExtra("Currency", String.valueOf(claim.getCurrency()));
        intent.putExtra("amount", String.valueOf(claim.getamount()));
        intent.putExtra("invoice_date", String.valueOf(claim.getinvoice_date()));
        intent.putExtra("settlement", String.valueOf(claim.getsettlement()));
        intent.putExtra("Overage", String.valueOf(claim.getOverage()));
        intent.putExtra("claim_type", String.valueOf(claim.getclaim_type()));
        intent.putExtra("BillTo", String.valueOf(claim.getBillTo()));
        intent.putExtra("BillToAcc", String.valueOf(claim.getBillToAcc()));
        intent.putExtra("ShipTo", String.valueOf(claim.getShipTo()));
        intent.putExtra("ShipToAcc", String.valueOf(claim.getShipToAcc()));
        intent.putExtra("Processor", String.valueOf(claim.getProcessor()));
        intent.putExtra("request_id", String.valueOf(claim.getrequestid()));
        intent.putExtra("notes", String.valueOf(claim.getnotes()));
        intent.putExtra("approval_level", String.valueOf(claim.getapproval_level()));

        startActivity(intent);

        Log.d(TAG, "Claim " +  String.valueOf(claim.getClaimNum())+ " Clicked");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }





}

