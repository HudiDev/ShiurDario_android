package com.hudiilfeld.shiurdiario.views.daf_hayomi;


import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hudiilfeld.shiurdiario.R;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.AllMasechtot_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_tab;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_tab;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;



/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerContainer_fragment extends Fragment{

    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter pagerAdapter;


    public static ViewPagerContainer_fragment newInstance(String prefix, String dafDate) {
        ViewPagerContainer_fragment fragment = new ViewPagerContainer_fragment();
        Bundle args = new Bundle();
        args.putString(PREFIX, prefix);
        args.putString(DAF_DATE, dafDate);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_view_pager_container_fragment, container, false);

        tabLayout = v.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Video"));
        tabLayout.addTab(tabLayout.newTab().setText("Gemara Text"));
        tabLayout.addTab(tabLayout.newTab().setText("PREVIOUS DAPIM"));
        tabLayout.addTab(tabLayout.newTab().setText("ALL MASECHTOT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        viewPager = v.findViewById(R.id.viewPager);

        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),
                tabLayout.getTabCount(),
                getArguments().getString(PREFIX),  getArguments().getString(DAF_DATE));

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


        return v;
    }

}
