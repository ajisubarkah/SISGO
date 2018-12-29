package android.sisgo.presenter;

import android.sisgo.model.GoodsResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.view.InventoryView;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryPresenter {

    private InventoryView view;
    private APIInterface apiInterface;

    public InventoryPresenter(InventoryView view, APIInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    public void getLoad() {
        view.showLoading();
        Call<GoodsResponse> call = apiInterface.getGoods();
        call.enqueue(new Callback<GoodsResponse>() {
            @Override
            public void onResponse(@NonNull Call<GoodsResponse> call, @NonNull Response<GoodsResponse> response) {
                GoodsResponse res = response.body();
                view.hideLoading();
                if(!response.body().getData().isEmpty())
                    view.showEvent(res.getData());
                else
                    view.showEmpty();
            }

            @Override
            public void onFailure(@NonNull Call<GoodsResponse> call, @NonNull Throwable t) {
                view.hideLoading();
                view.showEmpty();
            }
        });
    }
}
