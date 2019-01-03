package android.sisgo;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.presenter.DetailGoodPresenter;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.view.DetailGoodView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailGoodActivity extends AppCompatActivity implements DetailGoodView {

    private String idGood;
    private APIInterface apiInterface;
    private GoodDetailResponse goodItems;

    private RelativeLayout frameContent;
    private ProgressBar progressBar;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_good);
        Toolbar toolbar = findViewById(R.id.toolbar);

        idGood = getIntent().getStringExtra("id");

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        frameContent = findViewById(R.id.content);
        progressBar = findViewById(R.id.progress_bar);
        tvEmpty = findViewById(R.id.no_data);

        apiInterface = APIService.getClient().create(APIInterface.class);
        DetailGoodPresenter presenter = new DetailGoodPresenter(this, apiInterface);
        presenter.getGoodById(idGood);
    }

    @Override
    public void showLoading() {
        frameContent.setVisibility(View.INVISIBLE);
        tvEmpty.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        frameContent.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmpty() {
        frameContent.setVisibility(View.INVISIBLE);
        tvEmpty.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEvent(GoodDetailResponse data) {
        goodItems = data;

        ImageView img = findViewById(R.id.tv_image);
        Glide.with(this)
                .load(goodItems.getGoodData().getStrImg())
                .into(img);

        TextView barcode = findViewById(R.id.barcode);
        barcode.setText(goodItems.getGoodData().getStrBarcode());

        TextView name = findViewById(R.id.name_goods);
        name.setText(goodItems.getGoodData().getStrName());
    }
}
