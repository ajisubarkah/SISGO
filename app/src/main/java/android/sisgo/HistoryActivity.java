package android.sisgo;

import android.sisgo.adapter.HistoryAdapter;
import android.sisgo.model.HistoryItem;
import android.sisgo.presenter.HistoryPresenter;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.utils.WhoIs;
import android.sisgo.view.HistoryView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements HistoryView {

    protected Button button;
    protected APIInterface apiInterface;
    protected HistoryAdapter adapter;
    private ArrayList<HistoryItem> listItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private String idHistory;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        idHistory = getIntent().getStringExtra("idRestock");
        idUser = WhoIs.idUser;

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        tvEmpty = findViewById(R.id.no_data);

        apiInterface = APIService.getClient().create(APIInterface.class);

        HistoryPresenter presenter = new HistoryPresenter(this, apiInterface);
        presenter.getGoodsStockHistory(idHistory);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
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
    public void showEvent(ArrayList<HistoryItem> data) {
        listItems.clear();
        listItems.addAll(data);
        adapter = new HistoryAdapter(listItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
