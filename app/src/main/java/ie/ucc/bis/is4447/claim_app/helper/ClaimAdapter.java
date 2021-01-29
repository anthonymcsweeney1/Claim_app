package ie.ucc.bis.is4447.claim_app.helper;
//https://www.simplifiedcoding.net/retrieve-data-mysql-database-android/

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import java.util.List;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.view.ClaimPage;
import ie.ucc.bis.is4447.claim_app.view.PendingClaims;

public class ClaimAdapter extends RecyclerView.Adapter<ClaimAdapter.ClaimViewHolder> {


    private Context mCtx;
    private List<Claim> claimList;
    // animation for recycler view
    Animation recycle_anim;


    public ClaimAdapter(Context mCtx, List<Claim> claimList) {
        this.mCtx = mCtx;
        this.claimList = claimList;

    }

    @Override
    public ClaimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.claim_list, null);
        return new ClaimViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ClaimViewHolder holder, int position) {
        Claim claim = claimList.get(position);

        holder.textClaim.setText(claim.getClaimNum());
        holder.textInvoice.setText(claim.getInvoiceNum());
        holder.textAmount.setText(String.valueOf(claim.getamount()));
        holder.textOffer.setText(String.valueOf(claim.getoffercode()));
        holder.textCustomer.setText(String.valueOf(claim.getCus_Name()));

        holder.row_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //store row data for another activity
                Intent intent = new Intent(mCtx, ClaimPage.class);
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
                Toast.makeText(mCtx, String.valueOf(claim.getClaimID()), Toast.LENGTH_SHORT).show();
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return claimList.size();
    }

    class ClaimViewHolder extends RecyclerView.ViewHolder{


        TextView textClaim, textInvoice, textAmount, textOffer, textCustomer;
        RelativeLayout row_container;

        public ClaimViewHolder(View itemView) {
            super(itemView);

            textCustomer = itemView.findViewById(R.id.textCustomer);
            textAmount = itemView.findViewById(R.id.textAmount);
            textInvoice = itemView.findViewById(R.id.textInvoice);
            textClaim = itemView.findViewById(R.id.textClaimNum);
            textOffer = itemView.findViewById(R.id.textOffer);

            row_container = itemView.findViewById(R.id.row_container);

            // Animation
            recycle_anim = AnimationUtils.loadAnimation(mCtx, R.anim.recycle_anim);
            row_container.setAnimation(recycle_anim);
        }


    }

}

