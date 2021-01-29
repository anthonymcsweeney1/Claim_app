package ie.ucc.bis.is4447.claim_app.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import ie.ucc.bis.is4447.claim_app.R;

public class ClaimPage extends AppCompatActivity {

    TextView tvClaimID, tvClaimNum, tvInvoiceNum, tvAmount, tvCus_Name, tvOffer, tvClaimDate, tvCurrency, tvSettlement, tvCusDate, tvOverage, tvType, tvBillTo, tvBillToAcc, tvShipTo, tvShipToAcc, tvFrom;
    String  ClaimID, InvoiceNum, Status, customer_reason, claim_type, offercode, settlement, amount, invoice_date, creation_date, CusID, Cus_Name, BillTo, BillToAcc, ShipTo, Approver, ApproverEmail, OperatingUnit, Currency, ClaimNum, ShipToAcc, Creator, Overage,  notes, Processor, approval_level, lastupdated_by, lastupdate ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_page);



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


        GetAndSetIntentData();
    }



    void GetAndSetIntentData(){
        // Get and set the data
        if(getIntent().hasExtra("ClaimID") && getIntent().hasExtra("ClaimNum") &&
                getIntent().hasExtra("InvoiceNum") &&  getIntent().hasExtra("amount")
                &&  getIntent().hasExtra("offercode")  &&  getIntent().hasExtra("Cus_Name")
                &&  getIntent().hasExtra("creation_date")&&  getIntent().hasExtra("Currency") &&  getIntent().hasExtra("amount") &&  getIntent().hasExtra("settlement")
                &&  getIntent().hasExtra("invoice_date") &&  getIntent().hasExtra("Overage") &&  getIntent().hasExtra("claim_type") &&  getIntent().hasExtra("BillTo")
                &&  getIntent().hasExtra("BillToAcc") &&  getIntent().hasExtra("ShipTo") &&  getIntent().hasExtra("ShipToAcc") &&  getIntent().hasExtra("Processor")){

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

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        }
    }
}