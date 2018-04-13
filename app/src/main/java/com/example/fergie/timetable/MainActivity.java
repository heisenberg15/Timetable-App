package com.example.fergie.timetable;

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

public class MainActivity extends AppCompatActivity implements Communicator
{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    public SubjectModel subjectModel;
    public CreateSubjFragment createSubjFragment;
    FrameLayout frameLayout;
    ConstraintLayout constraintLayout;
    CoordinatorLayout coordinatorLayout;
    RunOneTime runOneTime;

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
        runOneTime = new RunOneTime();

        setSupportActionBar(toolbar);

        fillPages();

        initTabs();

        fabAnimation();

        clickFab();


        viewPager.setOffscreenPageLimit(7);

    }

    // save subject lists in shared preferences
    @Override
    protected void onStop()
    {
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

    // retry saved subject lists in sharedPreferences for each day
//    private void retrySharedPreferences()
//    {
//        SharedPreferences saveSingleton = getSharedPreferences("saveSingleton", MODE_PRIVATE);
//        String mondayList = saveSingleton.getString("mondayList", null);
//        String tuesdayList = saveSingleton.getString("tuesdayList", null);
//        String wednesdayList = saveSingleton.getString("wednesdayList", null);
//        String thursdayList = saveSingleton.getString("thursdayList", null);
//        String fridayList = saveSingleton.getString("fridayList", null);
//        String saturdayList = saveSingleton.getString("saturdayList", null);
//        String sundayList = saveSingleton.getString("sundayList", null);
//
//        if (mondayList != null) {
//            ArrayList<SubjectModel> getMondayList = new Gson().fromJson(mondayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getMondayList.size(); i++) {
//                Singleton.getInstance().addMonSubject(getMondayList.get(i));
//            }
//        }
//
//        if (tuesdayList != null) {
//            ArrayList<SubjectModel> getTuesdayList = new Gson().fromJson(tuesdayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getTuesdayList.size(); i++) {
//                Singleton.getInstance().addTueSubject(getTuesdayList.get(i));
//            }
//        }
//
//        if (wednesdayList != null) {
//            ArrayList<SubjectModel> getWednesdayList = new Gson().fromJson(wednesdayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getWednesdayList.size(); i++) {
//                Singleton.getInstance().addWedSubject(getWednesdayList.get(i));
//            }
//        }
//
//        if (thursdayList != null) {
//            ArrayList<SubjectModel> getThursdayList = new Gson().fromJson(thursdayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getThursdayList.size(); i++) {
//                Singleton.getInstance().addThuSubject(getThursdayList.get(i));
//            }
//        }
//
//        if (fridayList != null) {
//            ArrayList<SubjectModel> getFridayList = new Gson().fromJson(fridayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getFridayList.size(); i++) {
//                Singleton.getInstance().addFriSubject(getFridayList.get(i));
//            }
//        }
//
//        if (saturdayList != null) {
//            ArrayList<SubjectModel> getSaturdayList = new Gson().fromJson(saturdayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getSaturdayList.size(); i++) {
//                Singleton.getInstance().addSatSubject(getSaturdayList.get(i));
//            }
//        }
//
//        if (sundayList != null) {
//            ArrayList<SubjectModel> getSundayList = new Gson().fromJson(sundayList, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < getSundayList.size(); i++) {
//                Singleton.getInstance().addSunSubject(getSundayList.get(i));
//            }
//        }
//
//
////        updateSingletonLists(mondayList);
////        updateSingletonLists(tuesdayList);
////        updateSingletonLists(wednesdayList);
////        updateSingletonLists(thursdayList);
////        updateSingletonLists(fridayList);
////        updateSingletonLists(saturdayList);
////        updateSingletonLists(sundayList);
//
//    }


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

    // create new subject
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

    // Send subject objects to appropriate fragments
    @Override
    public void respond(SubjectModel subjectModel)
    {
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


    // Check and update singleton subject lists
//    public void updateSingletonLists(String stringDay)
//    {
//        if (stringDay != null) {
//            SharedPreferences saveSingleton = getSharedPreferences("saveSingleton", MODE_PRIVATE);
//            ArrayList<SubjectModel> list = new Gson().fromJson(stringDay, new TypeToken<ArrayList<SubjectModel>>()
//            {
//            }.getType());
//
//            for (int i = 0; i < list.size(); i++) {
//
//                if (saveSingleton.getString("mondayList", null).equals(stringDay)) {
//                    Singleton.getInstance().addMonSubject(list.get(i));
//                } else if(saveSingleton.getString("tuesdayList", null) == stringDay){
//                    Singleton.getInstance().addTueSubject(list.get(i));
//                } else if(saveSingleton.getString("wednesdayList", null) == stringDay) {
//                    Singleton.getInstance().addWedSubject(list.get(i));
//                } else if(saveSingleton.getString("thursdayList", null) == stringDay) {
//                    Singleton.getInstance().addThuSubject(list.get(i));
//                } else if(saveSingleton.getString("fridayList", null) == stringDay) {
//                    Singleton.getInstance().addFriSubject(list.get(i));
//                } else if(saveSingleton.getString("saturdayList", null) == stringDay) {
//                    Singleton.getInstance().addSatSubject(list.get(i));
//                } else if(saveSingleton.getString("sundayList", null) == stringDay) {
//                    Singleton.getInstance().addSunSubject(list.get(i));
//                }
//
//            }
//        }
//    }


}













