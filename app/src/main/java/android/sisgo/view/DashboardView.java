package android.sisgo.view;

import android.sisgo.model.RestockItem;

import java.util.ArrayList;

public interface DashboardView {
    void showLoading();
    void hideLoading();
    void showEmpty();
    void showEvent(ArrayList<RestockItem> data);
}
