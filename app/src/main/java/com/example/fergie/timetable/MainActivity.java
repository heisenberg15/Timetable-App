package com.example.fergie.timetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements Communicator {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    private FloatingActionButton fab;
    public SubjectModel subjectModel;
    public CreateSubjFragment createSubjFragment;
    FrameLayout frameLayout;
    ConstraintLayout constraintLayout;
    CoordinatorLayout coordinatorLayout;
    RunOneTime runOneTime;
    public int edit = 0;
    public int position;
    public MyPagerAdapter myPagerAdapter;
    SatFragment satFragment;
    SunFragment sunFragment;
    MonFragment monFragment;
    TueFragment tueFragment;
    WedFragment wedFragment;
    ThuFragment thuFragment;
    FriFragment friFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrySavedSettings();


        toolbar = findViewById(R.id.toolbar_id);
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        fab = findViewById(R.id.fab_id);
        coordinatorLayout = findViewById(R.id.coordinator_id);
        createSubjFragment = new CreateSubjFragment();
        frameLayout = findViewById(R.id.fragment_container_id);
        constraintLayout = findViewById(R.id.create_subject_fragment);
        runOneTime = new RunOneTime();
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        satFragment = new SatFragment();
        sunFragment = new SunFragment();
        monFragment = new MonFragment();
        tueFragment = new TueFragment();
        wedFragment = new WedFragment();
        thuFragment = new ThuFragment();
        friFragment = new FriFragment();

        setSupportActionBar(toolbar);

        fillPages();

        initTabs();

        fabAnimation();

        clickFab();


        viewPager.setOffscreenPageLimit(7);

    }

    // save subject lists in shared preferences
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences saveSingleton = getSharedPreferences("saveSingleton", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveSingleton.edit();

        String mondayList = new Gson().toJson(Singleton.getInstance().getMonList());
        String tuesdayList = new Gson().toJson(Singleton.getInstance().getTueList());
        String wednesdayList = new Gson().toJson(Singleton.getInstance().getWedList());
        String thursdayList = new Gson().toJson(Singleton.getInstance().getThuList());
        String fridayList = new Gson().toJson(Singleton.getInstance().getFriList());
        String saturdayList = new Gson().toJson(Singleton.getInstance().getSatList());
        String sundayList = new Gson().toJson(Singleton.getInstance().getSunList());

        editor.putString("mondayList", mondayList);
        editor.putString("tuesdayList", tuesdayList);
        editor.putString("wednesdayList", wednesdayList);
        editor.putString("thursdayList", thursdayList);
        editor.putString("fridayList", fridayList);
        editor.putString("saturdayList", saturdayList);
        editor.putString("sundayList", sundayList);
        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int fabVisibility = fab.getVisibility();
        int toolbarVisibility = toolbar.getVisibility();
        int tablayoutVisibility = tabLayout.getVisibility();
        int saveEdit = edit;

        outState.putInt("fabVisibility", fabVisibility);
        outState.putInt("toolbarVisibility", toolbarVisibility);
        outState.putInt("tablayoutVisibility", tablayoutVisibility);
        outState.putInt("saveEdit", saveEdit);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        frameLayout.bringToFront();
        fab.setVisibility(savedInstanceState.getInt("fabVisibility"));
        toolbar.setVisibility(savedInstanceState.getInt("toolbarVisibility"));
        tabLayout.setVisibility(savedInstanceState.getInt("tablayoutVisibility"));
        edit = savedInstanceState.getInt("saveEdit");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings_id:
                Intent openSettings = new Intent(getApplicationContext(), Settings.class);
                startActivity(openSettings);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        toolbar.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        edit = 0;
    }

    // Fill pagerAdapter with Fragments and Titles
    private void fillPages() {

        myPagerAdapter.addFragment(monFragment, "Mon");
        myPagerAdapter.addFragment(tueFragment, "Tue");
        myPagerAdapter.addFragment(wedFragment, "Wed");
        myPagerAdapter.addFragment(thuFragment, "Thu");
        myPagerAdapter.addFragment(friFragment, "Fri");



        viewPager.setAdapter(myPagerAdapter);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        myPagerAdapter.clearData();
//
//        if (Settings.switchState){
//            myPagerAdapter.addFragment(monFragment, "Mon");
//            myPagerAdapter.addFragment(tueFragment, "Tue");
//            myPagerAdapter.addFragment(wedFragment, "Wed");
//            myPagerAdapter.addFragment(thuFragment, "Thu");
//            myPagerAdapter.addFragment(friFragment, "Fri");
//            myPagerAdapter.addFragment(satFragment, "Sat");
//            myPagerAdapter.addFragment(sunFragment, "Sun");
//        }else {
//            myPagerAdapter.addFragment(monFragment, "Mon");
//            myPagerAdapter.addFragment(tueFragment, "Tue");
//            myPagerAdapter.addFragment(wedFragment, "Wed");
//            myPagerAdapter.addFragment(thuFragment, "Thu");
//            myPagerAdapter.addFragment(friFragment, "Fri");
//        }
//
//        myPagerAdapter.notifyDataSetChanged();
//        viewPager.setAdapter(myPagerAdapter);
//    }

    private void initTabs() {
        tabLayout.setupWithViewPager(viewPager);
    }


    // Floating action button animation on fragment change
    private void fabAnimation() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
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

    // create new subject
    private void clickFab() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateSubjectFragment();
            }
        });
    }

    // Send subject objects to appropriate fragments
    @Override
    public void respond(SubjectModel subjectModel) {
        toolbar.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager_id + ":" + viewPager.getCurrentItem());

        if (viewPager.getCurrentItem() == 0 && fragment != null) {
            ((MonFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 1 && fragment != null) {
            ((TueFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 2 && fragment != null) {
            ((WedFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 3 && fragment != null) {
            ((ThuFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 4 && fragment != null) {
            ((FriFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 5 && fragment != null) {
            ((SatFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 6 && fragment != null) {
            ((SunFragment) fragment).createSubject(subjectModel);
        }

    }

    public void showCreateSubjectFragment() {
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

    private void retrySavedSettings() {
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        Settings.switchState = settings.getBoolean("switchState", false);
    }

//    public void updateViewPaget(boolean isChecked) {
//        myPagerAdapter.clearData();
//
//        if (isChecked){
//            myPagerAdapter.addFragment(monFragment, "Mon");
//            myPagerAdapter.addFragment(tueFragment, "Tue");
//            myPagerAdapter.addFragment(wedFragment, "Wed");
//            myPagerAdapter.addFragment(thuFragment, "Thu");
//            myPagerAdapter.addFragment(friFragment, "Fri");
//            myPagerAdapter.addFragment(satFragment, "Sat");
//            myPagerAdapter.addFragment(sunFragment, "Sun");
//        }else {
//            myPagerAdapter.addFragment(monFragment, "Mon");
//            myPagerAdapter.addFragment(tueFragment, "Tue");
//            myPagerAdapter.addFragment(wedFragment, "Wed");
//            myPagerAdapter.addFragment(thuFragment, "Thu");
//            myPagerAdapter.addFragment(friFragment, "Fri");
//        }
//
//        myPagerAdapter.notifyDataSetChanged();
//        viewPager.setAdapter(myPagerAdapter);
//    }

}













