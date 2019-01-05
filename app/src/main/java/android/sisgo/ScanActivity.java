package android.sisgo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.google.zxing.Result;

import androidx.annotation.NonNull;

public class ScanActivity extends AppCompatActivity {

    public static final int RC_PERMISSION = 10;
    private CodeScanner codeScanner;
    private CodeScannerView scannerView;
    private LinearLayout contentManual;
    private Menu menu;
    private boolean statusBarcode = true;
    protected EditText barcodeText;
    protected Button barcodeButton;
    protected TableLayout tableLayout;
    protected boolean mPermissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        final Activity activity = this;

        scannerView = findViewById(R.id.scanner_view);
        contentManual = findViewById(R.id.content_manual);
        barcodeText = findViewById(R.id.barcode_text);
        tableLayout = findViewById(R.id.table_layout);

        barcodeButton = findViewById(R.id.button_barcode);
        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        codeScanner = new CodeScanner(this, scannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull final Exception error) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = false;
                requestPermissions(new String[]{Manifest.permission.CAMERA}, RC_PERMISSION);
            } else {
                mPermissionGranted = true;
            }
        } else {
            mPermissionGranted = true;
        }
    }

    private void setAddedBarcode(boolean statusBarcode) {
        if (statusBarcode) {
            scannerView.setVisibility(View.INVISIBLE);
            contentManual.setVisibility(View.VISIBLE);
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.barcode));
        } else {
            scannerView.setVisibility(View.VISIBLE);
            contentManual.setVisibility(View.INVISIBLE);
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_keyboard));
        }
        this.statusBarcode = !statusBarcode;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scan, menu);
        this.menu = menu;
        setAddedBarcode(statusBarcode);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.barcode_menu:
                setAddedBarcode(statusBarcode);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == RC_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = true;
                codeScanner.startPreview();
            } else {
                mPermissionGranted = false;
            }
        }
    }
}
