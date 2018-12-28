package android.sisgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sisgo.DashboardActivity;
import android.sisgo.LoginActivity;
import android.sisgo.R;
import android.sisgo.ScanActivity;
import android.sisgo.adapter.DashboardAdapter;
import android.sisgo.model.RestockItem;
import android.sisgo.model.RestockResponse;
import android.sisgo.model.UserResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.view.DashboardView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment implements DashboardView {

    private Button button;
    private APIInterface apiInterface;
    private ArrayList<RestockItem> listItems = new ArrayList<>();
    private RecyclerView rcView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = getView().findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
            }
        });
        rcView = getView().findViewById(R.id.recycler_view);
        apiInterface = APIService.getClient().create(APIInterface.class);
        doLoad();
    }

    void doLoad(){
        Call<RestockResponse> call = apiInterface.getRestock();
        call.enqueue(new Callback<RestockResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestockResponse> call, @NonNull Response<RestockResponse> response) {
                RestockResponse res = response.body();
                assert response.body() != null;
                listItems.addAll(res.getData());
                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_LONG).show();
                rcView.setLayoutManager(new LinearLayoutManager(getActivity()));
                DashboardAdapter adapter = new DashboardAdapter(listItems);
                rcView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<RestockResponse> call, @NonNull Throwable t) {

            }
        });
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showEvent() {

    }
}
