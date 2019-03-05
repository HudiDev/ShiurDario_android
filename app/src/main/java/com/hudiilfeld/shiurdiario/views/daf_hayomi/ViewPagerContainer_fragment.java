package com.hudiilfeld.shiurdiario.views.daf_hayomi;


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
import android.widget.LinearLayout;

import com.hudiilfeld.shiurdiario.R;

import static com.hudiilfeld.shiurdiario.adapters.DapimAdapter.DAF_DATE;
import static com.hudiilfeld.shiurdiario.views.LaunchActivity.PREFIX;



/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerContainer_fragment extends Fragment{

    TabLayout mTabLayout;
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

        mTabLayout = v.findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("VÍDEO"));
        mTabLayout.addTab(mTabLayout.newTab().setText("TEXTO DE GUEMARÁ"));
        mTabLayout.addTab(mTabLayout.newTab().setText("AULAS PASSADAS"));
        mTabLayout.addTab(mTabLayout.newTab().setText("LISTA DE TRATADOS"));

        TabsWithEqualWidth();

        viewPager = v.findViewById(R.id.viewPager);

        pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),
                mTabLayout.getTabCount(),
                getArguments().getString(PREFIX),  getArguments().getString(DAF_DATE));

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
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

    private void TabsWithEqualWidth() {

        ViewGroup slidingTabStrip = (ViewGroup) mTabLayout.getChildAt(0);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            View tab = slidingTabStrip.getChildAt(i);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tab.getLayoutParams();
            layoutParams.weight = 1;
            tab.setLayoutParams(layoutParams);
        }

    }

}
