package ie.ucc.bis.is4447.claim_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ie.ucc.bis.is4447.claim_app.R;

public class ClaimPage extends AppCompatActivity {

    TextView tvClaimID, tvClaimNum, tvInvoiceNum, tvAmount, tvCus_Name, tvOffer, tvClaimDate, tvCurrency, tvSettlement, tvCusDate, tvOverage, tvType, tvBillTo, tvBillToAcc, tvShipTo, tvShipToAcc, tvFrom, tvRequestID;
    String  ClaimID, InvoiceNum, Status, customer_reason, claim_type, offercode, settlement, amount, invoice_date, creation_date, CusID, Cus_Name, BillTo, BillToAcc, ShipTo, Approver, ApproverEmail, OperatingUnit, Currency, ClaimNum, ShipToAcc, Creator, Overage,  notes, Processor, approval_level, lastupdated_by, lastupdate, request_id ;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_page);

        // Bottom Navigation
        bottomnav = findViewById(R.id.claimnav);
        bottomnav.setSelectedItemId(R.id.item_detail);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // switch case for each activity
                switch (item.getItemId()){
                    case R.id.item_detail:
                        return true;
                    case R.id.item_image:
                        startActivity(new Intent(getApplicationContext(),InvoicePage.class));
                        overridePendingTransition(0,0);
                        Intent myIntent = new Intent(ClaimPage.this, InvoicePage.class);
                        myIntent.putExtra("notes", notes);
                        myIntent.putExtra("ClaimNum", ClaimNum);
                        myIntent.putExtra("InvoiceNum", InvoiceNum);
                        myIntent.putExtra("amount", amount);
                        myIntent.putExtra("offercode", offercode);
                        myIntent.putExtra("Cus_Name", Cus_Name);
                        myIntent.putExtra("creation_date", creation_date);
                        myIntent.putExtra("Currency", Currency);
                        myIntent.putExtra("settlement", settlement);
                        myIntent.putExtra("invoice_date", invoice_date);
                        myIntent.putExtra("Overage", Overage);
                        myIntent.putExtra("claim_type", claim_type);
                        myIntent.putExtra("BillTo", BillTo);
                        myIntent.putExtra("BillToAcc", BillToAcc);
                        myIntent.putExtra("ShipTo", ShipTo);
                        myIntent.putExtra("ShipToAcc", ShipToAcc);
                        myIntent.putExtra("Processor", Processor);
                        myIntent.putExtra("request_id", request_id);
                        startActivity(myIntent);
                        return true;
                    case R.id.item_comment:
                        startActivity(new Intent(getApplicationContext(),Comment.class));
                        overridePendingTransition(0,0);
                        Intent CommentIntent = new Intent(ClaimPage.this, Comment.class);
                        CommentIntent.putExtra("notes", notes);
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
                        startActivity(CommentIntent);
                        return true;
                }
                return false;
            }
        });

        tvClaimNum = findViewById(R.id.tvClaimNum);
        tvInvoiceNum = findViewById(R.id.tvInvoiceNum);
        tvAmount = findViewById(R.id.tvAmount);
         tvCus_Name = findViewById(R.id.tvCus);
        tvOffer = findViewById(R.id.tvOffer);
        tvClaimDate = findViewById(R.id.tvClaimDate);
        tvCurrency = findViewById(R.id.tvCurrency);
        tvCusDate = findViewById(R.id.tvCusDate);
        tvSettlement = findViewById(R.id.tvSettlement);
        tvOverage = findViewById(R.id.tvOverage);
        tvType = findViewById(R.id.tvType);
        tvBillTo = findViewById(R.id.tvBillTo);
        tvBillToAcc = findViewById(R.id.tvBillToAcc);
        tvShipTo = findViewById(R.id.tvShipTo);
        tvShipToAcc = findViewById(R.id.tvShipToAcc);
        tvFrom = findViewById(R.id.tvFrom);
        tvRequestID = findViewById(R.id.tvRequestID);


        GetAndSetIntentData();
    }



    void GetAndSetIntentData(){
        // Get and set the data


            //getting data
            ClaimID = getIntent().getStringExtra("ClaimID");
            ClaimNum = getIntent().getStringExtra("ClaimNum");
            InvoiceNum = getIntent().getStringExtra("InvoiceNum");
            amount = getIntent().getStringExtra("amount");
            offercode = getIntent().getStringExtra("offercode");
            Cus_Name = getIntent().getStringExtra("Cus_Name");
            creation_date = getIntent().getStringExtra("creation_date");
            Currency = getIntent().getStringExtra("Currency");
            amount = getIntent().getStringExtra("amount");
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
            notes = getIntent().getStringExtra("notes");


            //setting data
            tvClaimNum.setText(ClaimNum);
            tvInvoiceNum.setText(InvoiceNum);
            tvAmount.setText(amount);
            tvOffer.setText(offercode);
            tvCus_Name.setText(Cus_Name);
            tvClaimDate.setText(creation_date);
            tvCurrency.setText(Currency);
            tvCusDate.setText(invoice_date);
            tvSettlement.setText(settlement);
            tvOverage.setText(Overage);
            tvType.setText(claim_type);
            tvBillTo.setText(BillTo);
            tvBillToAcc.setText(BillToAcc);
            tvShipTo.setText(ShipTo);
            tvShipToAcc.setText(ShipToAcc);
            tvFrom.setText(Processor);
            tvRequestID.setText(request_id);


    }
}