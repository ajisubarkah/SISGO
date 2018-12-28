package android.sisgo.adapter;

import android.content.Context;
import android.sisgo.R;
import android.sisgo.model.RestockItem;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<RestockItem> listRestock;

    public DashboardAdapter(ArrayList<RestockItem> myDataset) {
        this.listRestock = myDataset;
    }

    private ArrayList<RestockItem> getListRestock() {
        return listRestock;
    }

    @Override
    public int getItemCount() {
        return listRestock.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_restock, parent, false);
        return new MyViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tvDate.setText(getListRestock().get(i).getUpdatedAt());
        myViewHolder.tvUsername.setText(getListRestock().get(i).getUsername());
        myViewHolder.tvId.setText(getListRestock().get(i).getId());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvUsername;
        TextView tvId;

        MyViewHolder(View view) {
            super(view);
            tvDate = itemView.findViewById(R.id.date_restock);
            tvUsername = itemView.findViewById(R.id.username);
            tvId = itemView.findViewById(R.id.restock_id);
        }
    }
}