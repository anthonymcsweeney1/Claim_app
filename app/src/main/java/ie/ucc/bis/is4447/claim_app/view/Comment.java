package ie.ucc.bis.is4447.claim_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Claim;

public class Comment extends AppCompatActivity {

    TextInputEditText tvPastComment, tvAddComment;
    Button btnUpdate;
    String  ClaimID, InvoiceNum, Status, customer_reason, claim_type, offercode, settlement, amount, invoice_date, creation_date, CusID, Cus_Name, BillTo, BillToAcc, ShipTo, Approver, ApproverEmail, OperatingUnit, Currency, ClaimNum, ShipToAcc, Creator, Overage,  notes, Processor, approval_level, lastupdated_by, lastupdate, request_id ;
    ProgressDialog progressDialog;
    String getCommentText;
    String final_approve;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

    btnUpdate = findViewById(R.id.btnUpdate);

        // Bottom Navigation
        bottomnav = findViewById(R.id.claimnav);
        bottomnav.setSelectedItemId(R.id.item_comment);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // switch case for each activity
                switch (item.getItemId()){
                    case R.id.item_comment:
                        return true;
                    case R.id.item_detail:
                        startActivity(new Intent(getApplicationContext(),ClaimPage.class));
                        overridePendingTransition(0,0);
                        Intent CommentIntent = new Intent(Comment.this, ClaimPage.class);
                        CommentIntent.putExtra("ClaimID", ClaimID);
                        CommentIntent.putExtra("ClaimNum", ClaimNum);
                        CommentIntent.putExtra("InvoiceNum", InvoiceNum);
                        CommentIntent.putExtra("amount", amount);
                        CommentIntent.putExtra("offercode", offercode);
                        CommentIntent.putExtra("Cus_Name", Cus_Name);
                        CommentIntent.putExtra("creation_date", creation_date);
                        CommentIntent.putExtra("Currency", Currency);
                        CommentIntent.putExtra("settlement", settlement);
                        CommentIntent.putExtra("invoice_date", invoice_date);
                        CommentIntent.putExtra("Overage", Overage);
                        CommentIntent.putExtra("claim_type", claim_type);
                        CommentIntent.putExtra("BillTo", BillTo);
                        CommentIntent.putExtra("BillToAcc", BillToAcc);
                        CommentIntent.putExtra("ShipTo", ShipTo);
                        CommentIntent.putExtra("ShipToAcc", ShipToAcc);
                        CommentIntent.putExtra("Processor", Processor);
                        CommentIntent.putExtra("request_id", request_id);
                        CommentIntent.putExtra("approval_level", approval_level);
                        CommentIntent.putExtra("notes", notes);
                        startActivity(CommentIntent);
                        return true;
                    case R.id.item_image:
                        startActivity(new Intent(getApplicationContext(),InvoicePage.class));
                        overridePendingTransition(0,0);
                        Intent ImageIntent = new Intent(Comment.this, InvoicePage.class);
                        ImageIntent.putExtra("ClaimID", ClaimID);
                        ImageIntent.putExtra("ClaimNum", ClaimNum);
                        ImageIntent.putExtra("InvoiceNum", InvoiceNum);
                        ImageIntent.putExtra("amount", amount);
                        ImageIntent.putExtra("offercode", offercode);
                        ImageIntent.putExtra("Cus_Name", Cus_Name);
                        ImageIntent.putExtra("creation_date", creation_date);
                        ImageIntent.putExtra("Currency", Currency);
                        ImageIntent.putExtra("settlement", settlement);
                        ImageIntent.putExtra("invoice_date", invoice_date);
                        ImageIntent.putExtra("Overage", Overage);
                        ImageIntent.putExtra("claim_type", claim_type);
                        ImageIntent.putExtra("BillTo", BillTo);
                        ImageIntent.putExtra("BillToAcc", BillToAcc);
                        ImageIntent.putExtra("ShipTo", ShipTo);
                        ImageIntent.putExtra("ShipToAcc", ShipToAcc);
                        ImageIntent.putExtra("Processor", Processor);
                        ImageIntent.putExtra("request_id", request_id);
                        ImageIntent.putExtra("approval_level", approval_level);
                        ImageIntent.putExtra("notes", notes);
                        startActivity(ImageIntent);
                        return true;
                }
                return false;
            }
        });


        tvPastComment = findViewById(R.id.tvPastComment);
        tvAddComment = findViewById(R.id.tvAddComment);



        approval_level = getIntent().getStringExtra("approval_level");
        notes = getIntent().getStringExtra("notes");
        tvPastComment.setText(notes);

        ClaimID= getIntent().getStringExtra("ClaimID");
        ClaimNum = getIntent().getStringExtra("ClaimNum");
        InvoiceNum = getIntent().getStringExtra("InvoiceNum");
        offercode = getIntent().getStringExtra("offercode");
        Cus_Name = getIntent().getStringExtra("Cus_Name");
        creation_date = getIntent().getStringExtra("creation_date");
        Currency = getIntent().getStringExtra("Currency");
        settlement = getIntent().getStringExtra("settlement");
        invoice_date = getIntent().getStringExtra("invoice_date");
        Overage = getIntent().getStringExtra("Overage");
        claim_type = getIntent().getStringExtra("claim_type");
        BillTo = getIntent().getStringExtra("BillTo");
        BillToAcc = getIntent().getStringExtra("BillToAcc");
        ShipTo = getIntent().getStringExtra("ShipTo");
        ShipToAcc = getIntent().getStringExtra("ShipToAcc");
        Processor = getIntent().getStringExtra("Processor");
        request_id = getIntent().getStringExtra("request_id");
        amount = getIntent().getStringExtra("amount");
        final_approve = getIntent().getStringExtra("final_approve");

        System.out.println(final_approve);

        // Check Approval level for Comment


        String levelcheck = approval_level;
        int iLevel = Integer.parseInt(levelcheck);



        if (iLevel == 1) {
            Log.v(TAG, "Level 1" );

        }

        if (iLevel == 2)  {
            Log.v(TAG, "Level 2");
        }

        if (iLevel == 3) {
            Log.v(TAG, "Level 3");

        }

        Log.v(TAG, ClaimNum+ ", " + tvAddComment.getText().toString()+ ", " + final_approve+ ", " + approval_level);

        btnUpdate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                progressDialog  = ProgressDialog.show(Comment.this, "Updating...", null, true, true);

                StringRequest request = new StringRequest(Request.Method.POST, "https://vendorcentral.000webhostapp.com/ApiComment.php?ClaimNum=" + ClaimNum + "&Comment=" +  tvAddComment.getText().toString() + "&approval_level=" + approval_level+ "&final_approve=" + final_approve, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Comment.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Comment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

                };
                progressDialog.dismiss();
                RequestQueue requestQueue = Volley.newRequestQueue(Comment.this);
                requestQueue.add(request);


            }
        });
    }
}