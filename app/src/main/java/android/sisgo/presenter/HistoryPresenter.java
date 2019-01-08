package android.sisgo.presenter;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.HistoryResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.view.HistoryView;

import retrofit2.Call;

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


    }
}
