package com.example.android.actionbarcompat.styled;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // My Beacons fragment activity
                return new MyBeaconsFragment();
            case 1:
                // Locate fragment activity
                return new LocateFragment();
            case 2:
                // Settings fragment activity
                return new SettingsFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}