package ie.ucc.bis.is4447.claim_app.helper;

//https://www.androidhive.info/2017/11/android-recyclerview-with-search-filter-functionality/
// Online tutorial code to filter the recyclerview

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.view.ClaimPage;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder>
        implements Filterable {

    private Context mCtx;
    private List<Claim> claimList;
    private List<Claim> claimListFiltered;
    private ClaimAdapterListener listener;


    private static final String TAG = "MyActivity";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textClaim, textInvoice, textAmount, textOffer, textCustomer;
        RelativeLayout row_container;

        public MyViewHolder(View view) {
            super(view);
            textCustomer = itemView.findViewById(R.id.textCustomer);
            textAmount = itemView.findViewById(R.id.textAmount);
            textInvoice = itemView.findViewById(R.id.textInvoice);
            textClaim = itemView.findViewById(R.id.textClaimNum);
            textOffer = itemView.findViewById(R.id.textOffer);

            row_container = itemView.findViewById(R.id.row_container);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected claim in callback
                    listener.onClaimSelected(claimListFiltered.get(getAdapterPosition()));
                }
            });

        }
    }

    public PendingAdapter(Context context, List<Claim> claimList, ClaimAdapterListener listener) {
        this.mCtx = context;
        this.listener = listener;
        this.claimList = claimList;
        this.claimListFiltered = claimList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.claim_list, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       final Claim claim = claimListFiltered.get(position);
        holder.textClaim.setText(claim.getClaimNum());
        holder.textInvoice.setText("Invoice No: " + claim.getInvoiceNum());
        holder.textAmount.setText(String.valueOf("€" + claim.getamount()));
        holder.textOffer.setText(String.valueOf("Offer Code: " +claim.getoffercode()));
        holder.textCustomer.setText(String.valueOf(claim.getCus_Name()));


    }

    @Override
    public int getItemCount() {
        return claimListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    claimListFiltered = claimList;
                } else {
                    List<Claim> filteredList = new ArrayList<>();
                    for (Claim row : claimList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getClaimNum().toLowerCase().contains(charString.toLowerCase()) || row.getCus_Name().contains(charSequence)) {
                            filteredList.add(row);
                            System.out.println(row.getClaimNum());
                        }
                    }

                    claimListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = claimListFiltered;
                filterResults.count = claimListFiltered.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                claimListFiltered = (ArrayList<Claim>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ClaimAdapterListener {
        void onClaimSelected(Claim claim);
    }
}
