package android.sisgo;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
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
                addTableRow(barcodeText.getText().toString());
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
                        addTableRow(result.getText());
                        onResume();
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

    private void addTableRow(String barcode) {
        if(checkDuplicateBarcode(barcode)) {
            RelativeLayout row = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.scan_row, null);
            TextView barcodeView = row.findViewById(R.id.barcode);
            final TextView values = row.findViewById(R.id.values_pick);

            barcodeView.setText(barcode);
            values.setText("1");

            row.findViewById(R.id.number_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPickerListener(values, true);
                }
            });

            row.findViewById(R.id.number_min).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonPickerListener(values, false);
                }
            });
            tableLayout.addView(row);
        }
    }

    private void buttonPickerListener(TextView values, boolean statusAdd) {
        int val = Integer.parseInt(values.getText().toString());
        if (statusAdd) {
            val++;
            values.setText(String.valueOf(val));
        } else {
            if (val == 0)
                values.setText(String.valueOf(val));
            else
                val--;
            values.setText(String.valueOf(val));
        }
    }

    private boolean checkDuplicateBarcode(String barcode){
        for(int i = 0, j = tableLayout.getChildCount(); i < j; i++) {
            View view = tableLayout.getChildAt(i);
            if(view instanceof RelativeLayout) {
                TextView barcodeView = view.findViewById(R.id.barcode);
                if(barcodeView.getText().toString().equals(barcode)) {
                    TextView values = view.findViewById(R.id.values_pick);
                    buttonPickerListener(values, true);
                    return false;
                }
            }
        }
        return true;
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to cancel?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ScanActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
