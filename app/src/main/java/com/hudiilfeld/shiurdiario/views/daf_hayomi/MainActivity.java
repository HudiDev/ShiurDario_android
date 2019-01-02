package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.AllMasechtot_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_tab;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class MainActivity extends AppCompatActivity implements Video_tab.OnFragmentInteractionListener,
        GemaraText_tab.OnFragmentInteractionListener, Dapim_tab.OnFragmentInteractionListener,
        AllMasechtot_tab.OnFragmentInteractionListener
         {


    String prefix, dafDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefix = getIntent().getStringExtra(PREFIX);
        dafDate = getIntent().getStringExtra(DAF_DATE);

        moveToFragment(prefix, dafDate);
    }


    private void moveToFragment(String prefix, String dafDate) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, ViewPagerContainer_fragment.newInstance(prefix, dafDate))
                .addToBackStack(null)
                .commit();
    }




     @Override
     public void onFragmentInteraction(Uri uri) {

     }

}
