package hu.ait.viewpagerdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.viewpagerdemo.fragments.FragmentOne;
import hu.ait.viewpagerdemo.fragments.FragmentTwo;

/**
 * Created by peter on 2016. 11. 24..
 */

public class MainPagerAdapter extends FragmentPagerAdapter{
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            default:
                fragment = new FragmentOne();
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Main";
            case 1:
                return "Details";
        }

        return "Main";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
