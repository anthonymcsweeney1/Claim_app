package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ie.ucc.bis.is4447.claim_app.R;

public class ClaimPage extends AppCompatActivity {

    TextView tvClaimID, tvClaimNum, tvInvoiceNum, tvamount, tvoffer, tvCus_Name;
    String ClaimID, ClaimNum, InvoiceNum, amount, offercode, Cus_Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_page);



        tvClaimID = findViewById(R.id.tvClaimID);
        tvClaimNum = findViewById(R.id.textView2);
        tvInvoiceNum = findViewById(R.id.tvInvoiceNum);
          tvamount = findViewById(R.id.textView4);
        tvoffer = findViewById(R.id.textView5);
         tvCus_Name = findViewById(R.id.tvCus);

        GetAndSetIntentData();
    }



    void GetAndSetIntentData(){
        // Get and set the data
        if(getIntent().hasExtra("ClaimID") && getIntent().hasExtra("ClaimNum") &&
                getIntent().hasExtra("InvoiceNum") &&  getIntent().hasExtra("amount")
                &&  getIntent().hasExtra("offercode")  &&  getIntent().hasExtra("Cus_Name")){

            //getting data
            ClaimID = getIntent().getStringExtra("ClaimID");
            ClaimNum = getIntent().getStringExtra("ClaimNum");
            InvoiceNum = getIntent().getStringExtra("InvoiceNum");
            amount = getIntent().getStringExtra("amount");
            offercode = getIntent().getStringExtra("offercode");
            Cus_Name = getIntent().getStringExtra("Cus_Name");

            //setting data

            tvClaimID.setText(ClaimID);
            tvClaimNum.setText(ClaimNum);
            tvInvoiceNum.setText(InvoiceNum);
            tvamount.setText(amount);
            tvoffer.setText(offercode);
            tvCus_Name.setText(Cus_Name);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        }
    }
}