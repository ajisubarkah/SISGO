package android.sisgo.presenter;

import android.sisgo.model.GoodItem;
import android.sisgo.model.GoodResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.view.InventoryView;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

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
        Call<GoodResponse> call = apiInterface.getGoods();
        call.enqueue(new Callback<GoodResponse>() {
            @Override
            public void onResponse(@NonNull Call<GoodResponse> call, @NonNull Response<GoodResponse> response) {
                GoodResponse res = response.body();
                view.hideLoading();
                if(!response.body().getData().isEmpty())
                    view.showEvent(res.getData());
                else
                    view.showEmpty();
            }

            @Override
            public void onFailure(@NonNull Call<GoodResponse> call, @NonNull Throwable t) {
                view.hideLoading();
                view.showEmpty();
            }
        });
    }


}
