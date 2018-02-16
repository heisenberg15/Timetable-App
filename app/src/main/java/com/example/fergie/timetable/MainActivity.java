package com.example.fergie.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.fergie.timetable.Adapters.MyPagerAdapter;
import com.example.fergie.timetable.Fragments.FriFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Fragments.SatFragment;
import com.example.fergie.timetable.Fragments.SunFragment;
import com.example.fergie.timetable.Fragments.ThuFragment;
import com.example.fergie.timetable.Fragments.TueFragment;
import com.example.fergie.timetable.Fragments.WedFragment;
import com.example.fergie.timetable.Models.SubjectModel;

public class MainActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    public String check;
    MonFragment monFragment;
    public SubjectModel subjectModel;
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
        monFragment = new MonFragment();


        setSupportActionBar(toolbar);

        fillPages();

        initTabs();

        fabAnimation();

        clickFab();

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null)
        {
            subjectModel = (SubjectModel) bundle.getSerializable("SUBJECT");
//            monFragment.setArguments(bundle);
            monFragment.createSubject();
        }



        check = "jaslkdjfalskdfj";

        Log.i("test", "onCreate: " + check);

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
                Intent createSubjectIntent = new Intent(getApplicationContext(), CreateSubjectActivity.class);
                startActivity(createSubjectIntent);
            }
        });
    }

}
