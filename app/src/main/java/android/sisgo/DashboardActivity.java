package android.sisgo;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.sisgo.fragment.DashboardFragment;
import android.sisgo.fragment.InventoryFragment;
import android.sisgo.utils.WhoIs;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                setNavigationItemSelected(menuItem.getItemId());
                return false;
            }
        });

        setNavigationItemSelected(R.id.dashboard_item);
        View header = navigationView.getHeaderView(0);

        CircleImageView imageNav = header.findViewById(R.id.image_nav);
        TextView titleNav = header.findViewById(R.id.title_nav);

        Glide.with(this)
                .load("http://subarkah.kuy.web.id/storage/profiles/"+WhoIs.idUser+".jpg")
                .into(imageNav);

        titleNav.setText(getIntent().getStringExtra("fullname"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        doExit();
    }

    public void setNavigationItemSelected(int menu) {
        switch (menu) {
            case R.id.dashboard_item:
                setFragment(new DashboardFragment());
                break;
            case R.id.inventory_item:
                setFragment(new InventoryFragment());
                break;
            case R.id.logout:
                doExit();
                break;
        }

        navigationView.setCheckedItem(menu);
        drawerLayout.closeDrawers();
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    void doExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DashboardActivity.this.finish();
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
