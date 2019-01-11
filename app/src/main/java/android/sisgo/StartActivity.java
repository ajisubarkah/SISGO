package android.sisgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartActivity extends AppCompatActivity {

    protected static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new android.os.Handler().postDelayed(new Runnable(){
            public void run(){
                Intent home = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(home);
                StartActivity.this.overridePendingTransition(R.transition.fade_in, R.transition.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
