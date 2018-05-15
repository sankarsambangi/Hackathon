package com.hackathon.nationwide.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.hackathon.nationwide.myapplication.Fragments.ATMFragment;
import com.hackathon.nationwide.myapplication.Fragments.FirstFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    @Override
    public int getCount() {
        return 2;
    }



    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                return FirstFragment.newInstance("","");
            case 1:
                return ATMFragment.newInstance("","");
            default:
                return FirstFragment.newInstance("","");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Branch";
            case 1:
                return "ATM";
            default:
                return "Branch";
        }
    }
}
