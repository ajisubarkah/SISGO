package android.sisgo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.sisgo.DetailGoodActivity;
import android.sisgo.R;
import android.sisgo.adapter.InventoryAdapter;
import android.sisgo.model.GoodItem;
import android.sisgo.presenter.InventoryPresenter;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.utils.OnItemClick;
import android.sisgo.view.InventoryView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InventoryFragment extends Fragment implements InventoryView, OnItemClick {

    protected APIInterface apiInterface;
    protected InventoryAdapter adapter;
    private ArrayList<GoodItem> listGoods = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recycler_view);
        progressBar = getView().findViewById(R.id.progress_bar);
        tvEmpty = getView().findViewById(R.id.no_data);

        apiInterface = APIService.getClient().create(APIInterface.class);
        InventoryPresenter presenter = new InventoryPresenter(this, apiInterface);
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
    public void showEvent(ArrayList<GoodItem> data) {
        listGoods.clear();
        listGoods.addAll(data);
        adapter = new InventoryAdapter(listGoods, getActivity());
        adapter.setItemClick(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Toast.makeText(getActivity(), String.valueOf(listGoods.get(position).getIntId()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), DetailGoodActivity.class);
        intent.putExtra("id", String.valueOf(listGoods.get(position).getIntId()));
        startActivity(intent);
    }
}
