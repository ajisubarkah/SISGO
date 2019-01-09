package android.sisgo.view;

import android.sisgo.model.InsertResponse;

import java.util.ArrayList;

public interface ScannerView {
    void showLoading();

    void hideLoading();

    void showEvent(ArrayList<InsertResponse> data);
}
