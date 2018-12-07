package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.AllMasechtot_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.PreviousDapim_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_tab;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;

public class MainActivity extends AppCompatActivity
        implements Video_tab.OnFragmentInteractionListener,
        GemaraText_tab.OnFragmentInteractionListener, PreviousDapim_tab.OnFragmentInteractionListener,
        AllMasechtot_tab.OnFragmentInteractionListener, Tab5.OnFragmentInteractionListener,
        Tab6.OnFragmentInteractionListener{

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    String prefix, dafDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefix = getIntent().getStringExtra(PREFIX);
        dafDate = getIntent().getStringExtra(DAF_DATE);



        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Gemara Text"));
        tabLayout.addTab(tabLayout.newTab().setText("PREVIOUS DAPIM"));
        tabLayout.addTab(tabLayout.newTab().setText("ALL MASECHTOT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), prefix,  dafDate);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(Tab tab) {}
            @Override
            public void onTabReselected(Tab tab) {}
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
