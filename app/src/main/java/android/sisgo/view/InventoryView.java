package android.sisgo.view;

import android.sisgo.model.GoodItem;
import java.util.ArrayList;

public interface InventoryView {
    void showLoading();
    void hideLoading();
    void showEmpty();
    void showEvent(ArrayList<GoodItem> data);
}
