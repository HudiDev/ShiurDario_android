package com.hudiilfeld.shiurdiario.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
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
        implements OnClickListener, NavigationView.OnNavigationItemSelectedListener,
        Dapim_fragment.OnFragmentInteractionListener,
        Masechtot_fragment.OnFragmentInteractionListener {

    public static final String TAG = "prefixAccepted";

    String prefix;
    String masechet, daf;

    //ToolBar properties
    TextView homeTitle;
    ImageButton homeBtnIB;

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

        homeTitle = findViewById(R.id.homeTitle);
        homeBtnIB = findViewById(R.id.homeBtnIB);
        homeBtnIB.setOnClickListener(this);

        Log.d(TAG, prefix);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.homeBtnIB) {
            Log.d("TAG", "image button clicked!!");
            FragmentManager fm = getSupportFragmentManager();
            for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                fm.popBackStack();
            }
        }
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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {

            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);

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

            homeTitle.setText("All Masechtot");

        } else if (id == R.id.dedicatorias) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new Dedication_fragment())
                    .addToBackStack(null)
                    .commit();

            homeTitle.setText("Dedicatorias");

        } else if (id == R.id.contato) {
            Toast.makeText(this, "Will be available in upcoming versions of the app", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
