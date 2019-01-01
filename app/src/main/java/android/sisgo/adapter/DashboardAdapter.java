package android.sisgo.adapter;

import android.sisgo.R;
import android.sisgo.model.RestockItem;
import android.sisgo.utils.OnItemClick;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private ArrayList<RestockItem> listRestock;
    private OnItemClick itemClick;

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

    public OnItemClick getItemClick() {
        return itemClick;
    }

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
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
        myViewHolder.tvDate.setText(getListRestock().get(i).getStrUpdatedAt());
        myViewHolder.tvUsername.setText(getListRestock().get(i).getStrUsername());
        myViewHolder.tvId.setText(String.valueOf(getListRestock().get(i).getIntId()));
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDate;
        TextView tvUsername;
        TextView tvId;

        MyViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
            tvDate = itemView.findViewById(R.id.date_restock);
            tvUsername = itemView.findViewById(R.id.username);
            tvId = itemView.findViewById(R.id.restock_id);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if(itemClick != null)
                itemClick.onItemClicked(getPosition());
        }
    }
}