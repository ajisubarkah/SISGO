package android.sisgo.presenter;

import android.sisgo.model.InsertResponse;
import android.sisgo.model.InsertStockItem;
import android.sisgo.service.APIInterface;
import android.sisgo.utils.WhoIs;
import android.sisgo.view.ScannerView;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerPresenter {
    private ScannerView view;
    private APIInterface apiInterface;
    private String id = "0";
    private ArrayList<InsertResponse> list = new ArrayList<>();
    private ArrayList<InsertStockItem> listStock = new ArrayList<>();

    public ScannerPresenter(ScannerView view, APIInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    public String insertRestock(ArrayList<InsertStockItem> item) {
        view.showLoading();
        this.listStock.clear();
        this.listStock.addAll(item);
        Call<InsertResponse> call = apiInterface.setRestock(WhoIs.idUser);
        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(@NonNull Call<InsertResponse> call, @NonNull Response<InsertResponse> response) {
                InsertResponse res = response.body();
                id = res.getId();
                list.clear();
                insertStock(0);
            }

            @Override
            public void onFailure(@NonNull Call<InsertResponse> call, @NonNull Throwable t) {
                Toast.makeText(null, t.getMessage(), Toast.LENGTH_LONG).show();
                view.hideLoading();
            }
        });
        return id;
    }

    public void insertStock(int index) {
        final int newIndex = index;
        if (index < listStock.size()) {
            Call<InsertResponse> call = apiInterface.setGoodsStock(id, listStock.get(index).getBarcode(), listStock.get(index).getAddStock());
            call.enqueue(new Callback<InsertResponse>() {
                @Override
                public void onResponse(@NonNull Call<InsertResponse> call, @NonNull Response<InsertResponse> response) {
                    list.add(response.body());
                    insertStock(newIndex + 1);
                }

                @Override
                public void onFailure(@NonNull Call<InsertResponse> call, @NonNull Throwable t) {
                    Toast.makeText(null, t.getMessage(), Toast.LENGTH_LONG).show();
                    view.hideLoading();
                }
            });
        } else {
            view.showEvent(list);
            view.hideLoading();
        }
    }
}
