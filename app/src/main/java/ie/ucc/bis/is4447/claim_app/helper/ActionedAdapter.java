package ie.ucc.bis.is4447.claim_app.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ie.ucc.bis.is4447.claim_app.R;


public class ActionedAdapter extends RecyclerView.Adapter<ActionedAdapter.ClaimViewHolder>{


    private Context mCtx;
    private List<Claim> claimList;
    // animation for recycler view
    Animation recycle_anim;

    private static final String TAG = "MyActivity";


    public ActionedAdapter(Context mCtx, List<Claim> claimList) {
        this.mCtx = mCtx;
        this.claimList = claimList;

    }

    @Override
    public ActionedAdapter.ClaimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.actioned_list, null);
        return new ActionedAdapter.ClaimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimViewHolder holder, int position) {
        Claim claim = claimList.get(position);

        holder.textClaim.setText(claim.getClaimNum());
        holder.textInvoice.setText("Invoice No: " + claim.getInvoiceNum());
        holder.textAmount.setText(String.valueOf("€" + claim.getamount()));
        holder.textOffer.setText(String.valueOf("Action Date: " +claim.getAction_Date4()));
        holder.textCustomer.setText(String.valueOf(claim.getCus_Name()));
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


