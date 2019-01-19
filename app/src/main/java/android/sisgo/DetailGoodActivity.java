package android.sisgo;

import android.sisgo.model.GoodDetailResponse;
import android.sisgo.model.StrImgItem;
import android.sisgo.presenter.DetailGoodPresenter;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.sisgo.utils.Konversi;
import android.sisgo.view.DetailGoodView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class DetailGoodActivity extends AppCompatActivity implements DetailGoodView {

    private RelativeLayout frameContent;
    private ProgressBar progressBar;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_good);
        Toolbar toolbar = findViewById(R.id.toolbar);

        String idGood = getIntent().getStringExtra("id");

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        frameContent = findViewById(R.id.content);
        progressBar = findViewById(R.id.progress_bar);
        tvEmpty = findViewById(R.id.no_data);

        APIInterface apiInterface = APIService.getClient().create(APIInterface.class);
        DetailGoodPresenter presenter = new DetailGoodPresenter(this, apiInterface);
        presenter.getGoodById(idGood);
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
    public void showEvent(final GoodDetailResponse data) {
        final ArrayList<StrImgItem> mg = data.getGoodData().getStrImg();

        CarouselView img = findViewById(R.id.tv_image);
        img.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                Glide.with(imageView)
                        .load("http://subarkah.kuy.web.id/"+data.getGoodData().getStrImg().get(position).getUrl())
                        .into(imageView);
            }
        });
        img.setPageCount(mg.size());

        TextView barcode = findViewById(R.id.barcode);
        barcode.setText(data.getGoodData().getStrBarcode());

        TextView name = findViewById(R.id.name_goods);
        name.setText(data.getGoodData().getStrName());

        TextView stock = findViewById(R.id.stock);
        stock.setText(data.getGoodData().getIntStock());

        TextView sell = findViewById(R.id.sell);
        sell.setText(Konversi.convert(data.getGoodData().getIntSelling()));

        TextView buy = findViewById(R.id.buy);
        buy.setText(Konversi.convert(data.getGoodData().getIntPurchase()));

        TextView updateAt = findViewById(R.id.update);
        updateAt.setText(data.getGoodData().getStrCpdatedAt());
    }
}
