package android.sisgo;


import android.content.DialogInterface;
import android.content.Intent;
import android.sisgo.model.UserResponse;
import android.sisgo.service.APIInterface;
import android.sisgo.service.APIService;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    APIInterface apiInterface;
    EditText textUsername;
    EditText textPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiInterface = APIService.getClient().create(APIInterface.class);
        textUsername = findViewById(R.id.username_field);
        textPassword = findViewById(R.id.password_field);

        Button button = findViewById(R.id.button_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });
    }

    void doLogin(){
        Call<UserResponse> call = apiInterface.doLogin(textUsername.getText().toString(), textPassword.getText().toString());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                UserResponse res = response.body();
                assert res != null;
                Toast.makeText(LoginActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                if(res.getStatus() == 200){
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("TOKEN", res.getToken());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finish();
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
