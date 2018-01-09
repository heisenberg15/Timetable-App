package com.example.fergie.timetable;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.fergie.timetable.Adapters.MyPagerAdapter;
import com.example.fergie.timetable.Fragments.FriFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Fragments.ThuFragment;
import com.example.fergie.timetable.Fragments.TueFragment;
import com.example.fergie.timetable.Fragments.WedFragment;

import static android.support.design.widget.TabLayout.MODE_FIXED;
import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class MainActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_id);
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);


        setSupportActionBar(toolbar);

        fillPages();

        initTabs();

    }


    // Fill pagerAdapter with Fragments and Titles
    private void fillPages()
    {
        MonFragment monFragment = new MonFragment();
        TueFragment tueFragment = new TueFragment();
        WedFragment wedFragment = new WedFragment();
        ThuFragment thuFragment = new ThuFragment();
        FriFragment friFragment = new FriFragment();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(monFragment, "Mon");
        myPagerAdapter.addFragment(tueFragment, "Tue");
        myPagerAdapter.addFragment(wedFragment, "Wed");
        myPagerAdapter.addFragment(thuFragment, "Thu");
        myPagerAdapter.addFragment(friFragment, "Fri");

        viewPager.setAdapter(myPagerAdapter);
    }

    private void initTabs()
    {
        tabLayout.setupWithViewPager(viewPager);
    }

}
