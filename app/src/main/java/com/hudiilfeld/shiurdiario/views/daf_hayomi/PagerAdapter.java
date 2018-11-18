package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hudiilfeld.shiurdiario.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PagerAdapter extends FragmentStatePagerAdapter {

    String prefix;
    int numOfTabs;
    String dafDate;


    public PagerAdapter(FragmentManager fm, int numOfTabs, String prefix,  @Nullable String dafDate) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.prefix = prefix;
        if (dafDate != null) {
            this.dafDate = dafDate;
        } else {
            this.dafDate = Utils.getCurrentDate();
        }

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Video_tab.newInstance(prefix);
            case 1:
                return GemaraText_tab.newInstance(prefix);
            case 2:
                return PreviousDapim_tab.newInstance(dafDate);
            case 3:
                return AllMasechtot_tab.newInstance(dafDate);
//            case 4:
//                return new Tab5();
//            case 5:
//                return new Tab6();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
