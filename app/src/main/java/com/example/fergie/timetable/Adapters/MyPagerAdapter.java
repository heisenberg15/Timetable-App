package com.example.fergie.timetable.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Fergie on 1/9/2018.
 */

public class MyPagerAdapter extends FragmentPagerAdapter
{

    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> tabTitles;

    public MyPagerAdapter(FragmentManager fm)
    {
        super(fm);
        fragmentList = new ArrayList<>();
        tabTitles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title)
    {
        fragmentList.add(fragment);
        tabTitles.add(title);
    }

}
