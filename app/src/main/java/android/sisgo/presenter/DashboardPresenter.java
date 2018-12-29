package android.sisgo.presenter;

import android.sisgo.adapter.DashboardAdapter;
import android.sisgo.fragment.DashboardFragment;
import android.sisgo.model.RestockItem;
import android.sisgo.model.RestockResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.view.DashboardView;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

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
