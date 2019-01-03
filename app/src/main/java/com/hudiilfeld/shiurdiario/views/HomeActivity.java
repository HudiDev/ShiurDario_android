package com.hudiilfeld.shiurdiario.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.Utils;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.Dedication_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.DafHayomiActivity;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Masechtot_fragment;

import static com.hudiilfeld.shiurdiario.views.LaunchActivity.DAF;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.MASECHET;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        Dapim_fragment.OnFragmentInteractionListener,
        Masechtot_fragment.OnFragmentInteractionListener {

    public static final String TAG = "prefixAccepted";

    ImageView imageView;
    String prefix;
    String masechet, daf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        prefix = getIntent().getStringExtra(PREFIX);
        masechet = getIntent().getStringExtra(MASECHET);
        daf = getIntent().getStringExtra(DAF);

        Log.d(TAG, prefix);

        imageView = findViewById(R.id.imageView);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

        } else if (id == R.id.daf_hayomi) {
            Intent intent = new Intent(this, DafHayomiActivity.class);
            intent.putExtra(PREFIX, prefix);
            intent.putExtra(MASECHET, masechet);
            intent.putExtra(DAF, daf);
            startActivity(intent);
        } else if (id == R.id.shiurim) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer,
                            Masechtot_fragment.newInstance(Utils.getCurrentDate()))
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.dedicatorias) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new Dedication_fragment())
                    .addToBackStack(null)
                    .commit();

        } else if (id == R.id.contato) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
