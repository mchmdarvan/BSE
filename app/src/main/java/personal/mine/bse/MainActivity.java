package personal.mine.bse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import personal.mine.bse.dashboard.FaqFragment;
import personal.mine.bse.dashboard.HomeFragment;
import personal.mine.bse.dashboard.ProfileFragment;
import personal.mine.bse.modul.BukuBaruActivity;
import personal.mine.bse.modul.BukuNonkurikulumActivity;
import personal.mine.bse.modul.BukuPopulerActivity;
import personal.mine.bse.modul.PaketAActivity;
import personal.mine.bse.modul.PaketBActivity;
import personal.mine.bse.modul.PaketCActivity;
import personal.mine.bse.modul.PaudActivity;
import personal.mine.bse.modul.SmaActivity;
import personal.mine.bse.modul.SmkActivity;
import personal.mine.bse.modul.SmpActivity;
import personal.mine.bse.modul.SdActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.hamburger_menu);

        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_open);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.homeFragment:
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;

            case R.id.faqFragment:
                fragment = new FaqFragment();
                loadFragment(fragment);
                break;

            case R.id.profileFragment:
                fragment = new ProfileFragment();
                loadFragment(fragment);
                break;

            case R.id.subitem_buku_terbaru:
                Intent bukuTerbaru = new Intent(MainActivity.this, BukuBaruActivity.class);
                startActivity(bukuTerbaru);
                break;
            case R.id.subitem_buku_terpopuler:
                Intent bukuTerpopuler = new Intent(MainActivity.this, BukuPopulerActivity.class);
                startActivity(bukuTerpopuler);
                break;
            case R.id.subitem_buku_nonkurikulum:
                Intent bukuNonKurikulum = new Intent(MainActivity.this, BukuNonkurikulumActivity.class);
                startActivity(bukuNonKurikulum);
                break;

            case R.id.subitem_paud:
                Intent bukuPaud = new Intent(MainActivity.this, PaudActivity.class);
                startActivity(bukuPaud);
                break;
            case R.id.subitem_buku_sd:
                Intent bukuSD = new Intent(MainActivity.this, SdActivity.class);
                startActivity(bukuSD);
                break;
            case R.id.subitem_buku_SMP:
                Intent bukuSMP = new Intent(MainActivity.this, SmpActivity.class);
                startActivity(bukuSMP);
                break;
            case R.id.subitem_buku_SMA:
                Intent bukuSMA = new Intent(MainActivity.this, SmaActivity.class);
                startActivity(bukuSMA);
                break;
            case R.id.subitem_buku_SMK:
                Intent bukuSMk = new Intent(MainActivity.this, SmkActivity.class);
                startActivity(bukuSMk);
                break;
            case R.id.subitem_buku_paket_a:
                Intent paketA = new Intent(MainActivity.this, PaketAActivity.class);
                startActivity(paketA);
                break;
            case R.id.subitem_buku_paket_b:
                Intent paketB = new Intent(MainActivity.this, PaketBActivity.class);
                startActivity(paketB);
                break;
            case R.id.subitem_buku_paket_c:
                Intent paketC = new Intent(MainActivity.this, PaketCActivity.class);
                startActivity(paketC);
                break;
        }
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}