package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Masechtot_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_fragment;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class DafHayomiActivity extends AppCompatActivity implements Video_fragment.OnFragmentInteractionListener,
        GemaraText_fragment.OnFragmentInteractionListener, Dapim_fragment.OnFragmentInteractionListener,
        Masechtot_fragment.OnFragmentInteractionListener
         {


    String prefix, dafDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daf_hayomi);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        prefix = getIntent().getStringExtra(PREFIX);
        dafDate = getIntent().getStringExtra(DAF_DATE);

        moveToFragment(prefix, dafDate);
    }


    private void moveToFragment(String prefix, String dafDate) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, ViewPagerContainer_fragment.newInstance(prefix, dafDate))
                .commit();
    }




     @Override
     public void onFragmentInteraction(Uri uri) {

     }

}
