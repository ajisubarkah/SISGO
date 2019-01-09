package android.sisgo.presenter;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.HistoryResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.view.HistoryView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {
    private HistoryView view;
    private APIInterface apiInterface;

    public HistoryPresenter(HistoryView view, APIInterface apiInterface) {
        this.view = view;
        this.apiInterface = apiInterface;
    }

    public void getGoodsStockHistory(String id) {
        view.showLoading();
        Call<HistoryResponse> call = apiInterface.getGoodsStockHistory(id);
        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                HistoryResponse res = response.body();
                view.hideLoading();
                if(!response.body().getData().isEmpty())
                    view.showEvent(res.getData());
                else
                    view.showEmpty();
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                view.hideLoading();
                view.showEmpty();
            }
        });

    }
}
