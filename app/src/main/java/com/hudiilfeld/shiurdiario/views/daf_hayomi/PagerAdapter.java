package com.hudiilfeld.shiurdiario.views.daf_hayomi;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hudiilfeld.shiurdiario.Utils;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Masechtot_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.GemaraText_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Dapim_fragment;
import com.hudiilfeld.shiurdiario.views.daf_hayomi.tabs.Video_fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private String prefix;
    private String dafDate;
    private int numOfTabs;

    
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
                return Video_fragment.newInstance(prefix);
            case 1:
                return GemaraText_fragment.newInstance(prefix);
            case 2:
                return Dapim_fragment.newInstance(dafDate, null);
            case 3:
                return Masechtot_fragment.newInstance(dafDate);
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
