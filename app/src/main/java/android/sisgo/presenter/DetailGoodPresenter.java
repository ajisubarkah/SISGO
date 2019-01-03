package android.sisgo.presenter;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.GoodItem;
import android.sisgo.service.APIInterface;
import android.sisgo.view.DashboardView;
import android.sisgo.view.DetailGoodView;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailGoodPresenter {
    private DetailGoodView view;
    private APIInterface apiInterface;

    public DetailGoodPresenter(DetailGoodView view, APIInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    public void getGoodById(String id) {
        view.showLoading();
        Call<GoodDetailResponse> call = apiInterface.getDetailGood(id);
        call.enqueue(new Callback<GoodDetailResponse>() {
            @Override
            public void onResponse(Call<GoodDetailResponse> call, Response<GoodDetailResponse> response) {
                GoodDetailResponse res = response.body();
                view.hideLoading();

                if (!response.body().getGoodData().getStrBarcode().isEmpty())
                    view.showEvent(res);
                else
                    view.showEmpty();
            }

            @Override
            public void onFailure(Call<GoodDetailResponse> call, Throwable t) {
                view.hideLoading();
                view.showEmpty();
            }
        });
    }
}
