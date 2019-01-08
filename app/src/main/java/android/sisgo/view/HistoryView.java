package android.sisgo.view;

import android.sisgo.model.HistoryItem;

import java.util.ArrayList;

public interface HistoryView {
    void showLoading();
    void hideLoading();
    void showEmpty();
    void showEvent(ArrayList<HistoryItem> data);
}
