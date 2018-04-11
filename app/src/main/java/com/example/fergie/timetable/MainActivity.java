package com.example.fergie.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fergie.timetable.Adapters.MyPagerAdapter;
import com.example.fergie.timetable.Fragments.CreateSubjFragment;
import com.example.fergie.timetable.Fragments.FriFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Fragments.SatFragment;
import com.example.fergie.timetable.Fragments.SunFragment;
import com.example.fergie.timetable.Fragments.ThuFragment;
import com.example.fergie.timetable.Fragments.TueFragment;
import com.example.fergie.timetable.Fragments.WedFragment;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.Utils.Singleton;

import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements Communicator
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    public String check;
    public SubjectModel subjectModel;
    public CreateSubjFragment createSubjFragment;
    FrameLayout frameLayout;
    ConstraintLayout constraintLayout;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar_id);
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        fab = findViewById(R.id.fab_id);
        coordinatorLayout = findViewById(R.id.coordinator_id);
        createSubjFragment = new CreateSubjFragment();
        frameLayout = findViewById(R.id.fragment_container_id);
        constraintLayout = findViewById(R.id.create_subject_fragment);


        setSupportActionBar(toolbar);

        fillPages();

        initTabs();

        fabAnimation();

        clickFab();


        viewPager.setOffscreenPageLimit(7);


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        toolbar.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    // Fill pagerAdapter with Fragments and Titles
    private void fillPages()
    {
        MonFragment monFragment = new MonFragment();
        TueFragment tueFragment = new TueFragment();
        WedFragment wedFragment = new WedFragment();
        ThuFragment thuFragment = new ThuFragment();
        FriFragment friFragment = new FriFragment();
        SatFragment satFragment = new SatFragment();
        SunFragment sunFragment = new SunFragment();

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.addFragment(monFragment, "Mon");
        myPagerAdapter.addFragment(tueFragment, "Tue");
        myPagerAdapter.addFragment(wedFragment, "Wed");
        myPagerAdapter.addFragment(thuFragment, "Thu");
        myPagerAdapter.addFragment(friFragment, "Fri");
        myPagerAdapter.addFragment(satFragment, "Sat");
        myPagerAdapter.addFragment(sunFragment, "Sun");

        viewPager.setAdapter(myPagerAdapter);
    }

    private void initTabs()
    {
        tabLayout.setupWithViewPager(viewPager);
    }


    // Floating action button animation on fragment change
    private void fabAnimation()
    {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {

            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        fab.show();
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        fab.hide();
                        break;
                }

            }
        });
    }

    private void clickFab()
    {
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                fab.setVisibility(View.GONE);

                toolbar.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container_id, createSubjFragment, createSubjFragment.getTag())
                        .addToBackStack("tag")
                        .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .commit();

                frameLayout.bringToFront();
            }
        });
    }

    @Override
    public void respond(SubjectModel subjectModel)
    {
        toolbar.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager_id + ":" + viewPager.getCurrentItem());

        if (viewPager.getCurrentItem() == 0 && fragment != null){
            ((MonFragment)fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 1 && fragment != null){
            ((TueFragment)fragment).createSubject(subjectModel);
        }

    }




}













