package android.sisgo.presenter;

import android.sisgo.model.RestockResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.view.DashboardView;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter {

    private DashboardView view;
    private APIInterface apiInterface;

    public DashboardPresenter(DashboardView views, APIInterface apiInterface) {
        this.view = views;
        this.apiInterface = apiInterface;
    }

    public void getLoad() {
        view.showLoading();
        Call<RestockResponse> call = apiInterface.getRestock();
        call.enqueue(new Callback<RestockResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestockResponse> call, @NonNull Response<RestockResponse> response) {
                RestockResponse res = response.body();
                view.hideLoading();
                if(!response.body().getData().isEmpty())
                    view.showEvent(res.getData());
                else
                    view.showEmpty();
            }

            @Override
            public void onFailure(@NonNull Call<RestockResponse> call, @NonNull Throwable t) {
                view.hideLoading();
                view.showEmpty();
            }
        });
    }
}
