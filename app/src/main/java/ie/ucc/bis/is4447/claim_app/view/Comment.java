package ie.ucc.bis.is4447.claim_app.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.Claim;

public class Comment extends AppCompatActivity {

    TextInputEditText tvPastComment, tvAddComment;
    String  ClaimID, InvoiceNum, Status, customer_reason, claim_type, offercode, settlement, amount, invoice_date, creation_date, CusID, Cus_Name, BillTo, BillToAcc, ShipTo, Approver, ApproverEmail, OperatingUnit, Currency, ClaimNum, ShipToAcc, Creator, Overage,  notes, Processor, approval_level, lastupdated_by, lastupdate, request_id ;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


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
                    case R.id.item_image:
                        startActivity(new Intent(getApplicationContext(),InvoicePage.class));
                        overridePendingTransition(0,0);
                        Intent ImageIntent = new Intent(Comment.this, InvoicePage.class);
                        ImageIntent.putExtra("notes", notes);
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
                        startActivity(ImageIntent);
                        return true;
                }
                return false;
            }
        });

        tvPastComment = findViewById(R.id.tvPastComment);
        tvAddComment = findViewById(R.id.tvAddComment);



        notes = getIntent().getStringExtra("notes");
        tvPastComment.setText(notes);

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
    }
}