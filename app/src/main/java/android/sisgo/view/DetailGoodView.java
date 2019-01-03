package android.sisgo.view;

import android.sisgo.model.GoodDetailResponse;

public interface DetailGoodView {
    void showLoading();
    void hideLoading();
    void showEmpty();
    void showEvent(GoodDetailResponse data);
}
