package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.views.HomeActivity;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Masechtot_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_fragment;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.DAF;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.MASECHET;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class DafHayomiActivity extends AppCompatActivity implements OnClickListener, Video_fragment.OnFragmentInteractionListener,
        GemaraText_fragment.OnFragmentInteractionListener, Dapim_fragment.OnFragmentInteractionListener,
        Masechtot_fragment.OnFragmentInteractionListener
         {


    String prefix, dafDate, masechet, daf;

    //actionBar properties
    TextView actionBarTitle;
    ImageView backBtnIV, homeBtnIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daf_hayomi);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        actionBarTitle = findViewById(R.id.actionBarTitle);
        backBtnIV = findViewById(R.id.backBtnIV);
        homeBtnIV = findViewById(R.id.homeBtnIV);

        backBtnIV.setOnClickListener(this);
        homeBtnIV.setOnClickListener(this);

        prefix = getIntent().getStringExtra(PREFIX);
        dafDate = getIntent().getStringExtra(DAF_DATE);
        masechet = getIntent().getStringExtra(MASECHET);
        daf = getIntent().getStringExtra(DAF);

        actionBarTitle.setText(masechet + " " + daf);

        moveToFragment(prefix, dafDate);


    }


    private void moveToFragment(String prefix, String dafDate) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, ViewPagerContainer_fragment.newInstance(prefix, dafDate))
                .commit();
    }


     @Override
     public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtnIV:
                super.onBackPressed();
                break;
            case R.id.homeBtnIV:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                break;
        }
     }


     @Override
     public void onFragmentInteraction(Uri uri) {

     }




}
