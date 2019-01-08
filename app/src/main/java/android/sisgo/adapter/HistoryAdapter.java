package android.sisgo.adapter;

import android.sisgo.R;
import android.sisgo.model.HistoryItem;
import android.sisgo.utils.OnItemClick;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private ArrayList<HistoryItem> listHistory;
    private OnItemClick itemClick;

    public HistoryAdapter(ArrayList<HistoryItem> myDataset) {
        this.listHistory = myDataset;
    }

    private ArrayList<HistoryItem> getlistHistory() {
        return listHistory;
    }

    @Override
    public int getItemCount() {
        return listHistory.size();

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
        myViewHolder.tvBarcode.setText(getlistHistory().get(i).getStrBarcode());
        myViewHolder.tvName.setText(getlistHistory().get(i).getStrName());
        myViewHolder.tvStock.setText(getlistHistory().get(i).getIntAddStok());
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvBarcode;
        TextView tvName;
        TextView tvStock;

        MyViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
            tvBarcode = itemView.findViewById(R.id.barcode);
            tvName = itemView.findViewById(R.id.name_goods);
            tvStock = itemView.findViewById(R.id.stock);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if(itemClick != null)
                itemClick.onItemClicked(getPosition());
        }
    }
}