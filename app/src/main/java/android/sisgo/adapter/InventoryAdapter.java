package android.sisgo.adapter;

import android.content.Context;
import android.sisgo.R;
import android.sisgo.model.GoodItem;
import android.sisgo.utils.Konversi;
import android.sisgo.utils.OnItemClick;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {

    private ArrayList<GoodItem> listGoods;
    private Context context;
    private OnItemClick itemClick;
    public InventoryAdapter(ArrayList<GoodItem> myDataset, Context context) {
        this.listGoods = myDataset;
        this.context = context;
    }

    private ArrayList<GoodItem> getListGoods() {
        return listGoods;
    }

    @Override
    public int getItemCount() {
        return listGoods.size();
    }

    public void setItemClick(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_goods, parent, false);
        return new MyViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.barcode.setText(getListGoods().get(i).getStrBarcode());
        myViewHolder.nameGoods.setText(getListGoods().get(i).getStrName());
        myViewHolder.priceList.setText(Konversi.convert(String.valueOf(getListGoods().get(i).getIntPurchase())));
        myViewHolder.stock.setText(String.valueOf(getListGoods().get(i).getIntStock()));
        Glide.with(context)
                .load(getListGoods().get(i).getStrImg())
                .into(myViewHolder.tvImage);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView tvImage;
        TextView barcode;
        TextView nameGoods;
        TextView priceList;
        TextView stock;

        MyViewHolder(View view) {
            super(view);
            view.setClickable(true);
            view.setOnClickListener(this);
            tvImage = itemView.findViewById(R.id.tv_image);
            barcode = itemView.findViewById(R.id.barcode);
            nameGoods = itemView.findViewById(R.id.name_goods);
            priceList = itemView.findViewById(R.id.price_list);
            stock = itemView.findViewById(R.id.stock);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onClick(View v) {
            if(itemClick != null)
                itemClick.onItemClicked(getPosition());
        }
    }
}
