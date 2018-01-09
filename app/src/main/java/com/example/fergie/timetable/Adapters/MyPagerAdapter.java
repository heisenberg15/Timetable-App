package com.example.fergie.timetable.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Fergie on 1/9/2018.
 */

public class MyPagerAdapter extends FragmentPagerAdapter
{

    ArrayList <Fragment> fragmentList;

    public MyPagerAdapter(FragmentManager fm)
    {
        super(fm);
        fragmentList = new ArrayList<>();
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
}
