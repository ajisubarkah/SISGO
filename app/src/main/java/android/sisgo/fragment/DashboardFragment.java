package android.sisgo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.sisgo.R;
import android.sisgo.ScanActivity;
import android.sisgo.adapter.DashboardAdapter;
import android.sisgo.model.RestockItem;
import android.sisgo.presenter.DashboardPresenter;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.utils.OnItemClick;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardFragment extends Fragment implements DashboardView, OnItemClick {

    protected Button button;
    protected APIInterface apiInterface;
    protected DashboardAdapter adapter;
    private ArrayList<RestockItem> listItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button = Objects.requireNonNull(getView()).findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = getView().findViewById(R.id.recycler_view);
        progressBar = getView().findViewById(R.id.progress_bar);
        tvEmpty = getView().findViewById(R.id.no_data);

        apiInterface = APIService.getClient().create(APIInterface.class);

        DashboardPresenter presenter = new DashboardPresenter(this, apiInterface);
        presenter.getLoad();
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.INVISIBLE);
        tvEmpty.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        recyclerView.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.INVISIBLE);
        tvEmpty.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEvent(ArrayList<RestockItem> data) {
        listItems.clear();
        listItems.addAll(data);
        adapter = new DashboardAdapter(listItems);
        adapter.setItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getActivity(), listItems.get(position).getStrUsername(), Toast.LENGTH_LONG).show();
        
    }
}
