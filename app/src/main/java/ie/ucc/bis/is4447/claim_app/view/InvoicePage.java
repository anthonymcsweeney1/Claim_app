package ie.ucc.bis.is4447.claim_app.view;
// https://stackoverflow.com/questions/55816746/filedownloader-at-the-android-it-is-showing-no-errors-but-the-download-does-not
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;

import ie.ucc.bis.is4447.claim_app.BuildConfig;
import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.FileDownloader;

public class InvoicePage extends AppCompatActivity {

    String  ClaimID, InvoiceNum, Status, customer_reason, claim_type, offercode, settlement, amount, invoice_date, creation_date, CusID, Cus_Name, BillTo, BillToAcc, ShipTo, Approver, ApproverEmail, OperatingUnit, Currency, ClaimNum, ShipToAcc, Creator, Overage,  notes, Processor, approval_level, lastupdated_by, lastupdate, request_id ;

    private BottomNavigationView bottomnav;
    private static final String TAG = "MyActivity";

    private static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};


    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_page);

        Log.v(TAG, "onCreate() Method invoked ");

        ActivityCompat.requestPermissions(InvoicePage.this, PERMISSIONS, 112);


        // Bottom Navigation
        bottomnav = findViewById(R.id.claimnav);
        bottomnav.setSelectedItemId(R.id.item_image);
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // switch case for each activity
                switch (item.getItemId()){
                    case R.id.item_image:
                        return true;
                    case R.id.item_detail:
                        startActivity(new Intent(getApplicationContext(),ClaimPage.class));
                        overridePendingTransition(0,0);
                        Intent myIntent = new Intent(InvoicePage.this, ClaimPage.class);
                        myIntent.putExtra("ClaimID", ClaimID);
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
                        myIntent.putExtra("approval_level", approval_level);
                        myIntent.putExtra("notes", notes);
                        startActivity(myIntent);

                        return true;
                    case R.id.item_comment:
                        startActivity(new Intent(getApplicationContext(),Comment.class));
                        overridePendingTransition(0,0);
                        Intent CommentIntent = new Intent(InvoicePage.this, Comment.class);
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
                }
                return false;
            }
        });




        ClaimID = getIntent().getStringExtra("ClaimID");
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
        notes = getIntent().getStringExtra("notes");
        approval_level = getIntent().getStringExtra("approval_level");


    }

    public void request(View view) {

        ActivityCompat.requestPermissions(InvoicePage.this, PERMISSIONS, 112);

    }

    public void view(View view) {
        Log.v(TAG, "view() Method invoked ");

        if (!hasPermissions(InvoicePage.this, PERMISSIONS)) {

            Log.v(TAG, "download() Method DON'T HAVE PERMISSIONS ");

            Toast t = Toast.makeText(getApplicationContext(), "You don't have read access !", Toast.LENGTH_LONG);
            t.show();

        } else {
            File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  // -> filename = maven.pdf
            File pdfFile = new File(d, "Claim "+ClaimNum+".pdf");

            Log.v(TAG, "view() Method pdfFile " + pdfFile.getAbsolutePath());

            Uri path = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", pdfFile);


            Log.v(TAG, "view() Method path " + path);

            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(InvoicePage.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
            }
        }
        Log.v(TAG, "view() Method completed ");

    }

    public void download(View view) {
        Log.v(TAG, "download() Method invoked ");

        if (!hasPermissions(InvoicePage.this, PERMISSIONS)) {

            Log.v(TAG, "download() Method DON'T HAVE PERMISSIONS ");

            Toast t = Toast.makeText(getApplicationContext(), "You don't have write access !", Toast.LENGTH_LONG);
            t.show();

        } else {
            Log.v(TAG, "download() Method HAVE PERMISSIONS ");


            new DownloadFile().execute("https://vendorcentral.000webhostapp.com/downloads.php?file_id="+InvoiceNum, "Claim "+ClaimNum+".pdf");

        }

        Log.v(TAG, "download() Method completed ");

    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Log.v(TAG, "doInBackground() Method invoked ");

            String fileUrl = strings[0];
            String fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File pdfFile = new File(folder, fileName);
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsolutePath());
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsoluteFile());

            try {
                pdfFile.createNewFile();
                Log.v(TAG, "doInBackground() file created" + pdfFile);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground() error" + e.getMessage());
                Log.e(TAG, "doInBackground() error" + e.getStackTrace());


            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            Log.v(TAG, "doInBackground() file download completed");

            return null;
        }
    }
}