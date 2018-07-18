package com.example.fergie.timetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;

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
import com.savvisingh.colorpickerdialog.ColorPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

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
    public int edit = 0;
    public int position;
    public int intentId;
    public int visibleTab = 1;
    public int newSubject = 0;
    MyPagerAdapter myPagerAdapter;
    MonFragment monFragment;
    TueFragment tueFragment;
    WedFragment wedFragment;
    ThuFragment thuFragment;
    FriFragment friFragment;
    SatFragment satFragment;
    SunFragment sunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        monFragment = new MonFragment();
        tueFragment = new TueFragment();
        wedFragment = new WedFragment();
        thuFragment = new ThuFragment();
        friFragment = new FriFragment();
        satFragment = new SatFragment();
        sunFragment = new SunFragment();

        setSupportActionBar(toolbar);

        RetainFragment rf = MainActivity.findOrCreateRetainFragment(getSupportFragmentManager());
        rf.setObject(getSupportActionBar());

        initTabs();
        fabAnimation();
        clickFab();
        getVisibleTab();

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
        String idList = new Gson().toJson(Singleton.getInstance().getIdList());


        editor.putString("mondayList", mondayList);
        editor.putString("tuesdayList", tuesdayList);
        editor.putString("wednesdayList", wednesdayList);
        editor.putString("thursdayList", thursdayList);
        editor.putString("fridayList", fridayList);
        editor.putString("saturdayList", saturdayList);
        editor.putString("sundayList", sundayList);
        editor.putString("idList", idList);

        editor.apply();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
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
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        frameLayout.bringToFront();
        fab.setVisibility(savedInstanceState.getInt("fabVisibility"));
        toolbar.setVisibility(savedInstanceState.getInt("toolbarVisibility"));
        tabLayout.setVisibility(savedInstanceState.getInt("tablayoutVisibility"));
        edit = savedInstanceState.getInt("saveEdit");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.settings_id:
                Intent openSettings = new Intent(getApplicationContext(), Settings.class);
                startActivity(openSettings);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        toolbar.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        edit = 0;
        newSubject = 0;
    }

    // Fill pagerAdapter with Fragments and Titles
//    private void fillPages() {
//
//        myPagerAdapter.clearData();
//        myPagerAdapter.addFragment(monFragment, "Mon");
//        myPagerAdapter.addFragment(tueFragment, "Tue");
//        myPagerAdapter.addFragment(wedFragment, "Wed");
//        myPagerAdapter.addFragment(thuFragment, "Thu");
//        myPagerAdapter.addFragment(friFragment, "Fri");
//
//        if (Settings.switchState) {
//            myPagerAdapter.addFragment(satFragment, "Sat");
//            myPagerAdapter.addFragment(sunFragment, "Sun");
//        }
//
//        viewPager.setAdapter(myPagerAdapter);
//    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // Fill pagerAdapter with Fragments and Titles
        myPagerAdapter.clearData();

        if (Settings.switchState)
        {
            myPagerAdapter.addFragment(monFragment, "Mon");
            myPagerAdapter.addFragment(tueFragment, "Tue");
            myPagerAdapter.addFragment(wedFragment, "Wed");
            myPagerAdapter.addFragment(thuFragment, "Thu");
            myPagerAdapter.addFragment(friFragment, "Fri");
            myPagerAdapter.addFragment(satFragment, "Sat");
            myPagerAdapter.addFragment(sunFragment, "Sun");
        } else
        {
            myPagerAdapter.addFragment(monFragment, "Mon");
            myPagerAdapter.addFragment(tueFragment, "Tue");
            myPagerAdapter.addFragment(wedFragment, "Wed");
            myPagerAdapter.addFragment(thuFragment, "Thu");
            myPagerAdapter.addFragment(friFragment, "Fri");
        }
        viewPager.setAdapter(myPagerAdapter);

        setCurrentTab();
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
                switch (state)
                {
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

                newSubject = 1;
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

        if (viewPager.getCurrentItem() == 0 && fragment != null)
        {
            ((MonFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 1 && fragment != null)
        {
            ((TueFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 2 && fragment != null)
        {
            ((WedFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 3 && fragment != null)
        {
            ((ThuFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 4 && fragment != null)
        {
            ((FriFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 5 && fragment != null)
        {
            ((SatFragment) fragment).createSubject(subjectModel);
        } else if (viewPager.getCurrentItem() == 6 && fragment != null)
        {
            ((SunFragment) fragment).createSubject(subjectModel);
        }

    }

    public void showCreateSubjectFragment(SubjectModel subjectModel)
    {
        fab.setVisibility(View.GONE);
        toolbar.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putInt("intentId", subjectModel.getIntentId());

        if (edit == 1)
        {
            bundle.putString("subject", subjectModel.getSubject());
            bundle.putString("info", subjectModel.getInfo());
            bundle.putString("startTime", subjectModel.getStartTime());
            bundle.putString("endTime", subjectModel.getEndTIme());
            bundle.putInt("color", Integer.parseInt(subjectModel.getColor()));
            bundle.putInt("startHour", Integer.parseInt(subjectModel.getStartHour()));
            bundle.putInt("startMinute", Integer.parseInt(subjectModel.getStartMinute()));
        }
        createSubjFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container_id, createSubjFragment, createSubjFragment.getTag())
                .addToBackStack("tag")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();

        frameLayout.bringToFront();
    }

    private void getVisibleTab()
    {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int pos = tab.getPosition();

                if (pos == 0)
                {
                    visibleTab = 2;
                } else if (pos == 1)
                {
                    visibleTab = 3;
                } else if (pos == 2)
                {
                    visibleTab = 4;
                } else if (pos == 3)
                {
                    visibleTab = 5;
                } else if (pos == 4)
                {
                    visibleTab = 6;
                } else if (pos == 5)
                {
                    visibleTab = 7;
                } else if (pos == 6)
                {
                    visibleTab = 1;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    private void setCurrentTab()
    {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        TabLayout.Tab tab;

        switch (day)
        {
            case Calendar.MONDAY:
                tab = tabLayout.getTabAt(0);
                final TabLayout.Tab finalTab = tab;
                new Handler().postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                finalTab.select();
                            }
                        }, 100);
                break;
            case Calendar.TUESDAY:
                tab = tabLayout.getTabAt(1);
                final TabLayout.Tab finalTab1 = tab;
                new Handler().postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                finalTab1.select();
                            }
                        }, 100);
                break;
            case Calendar.WEDNESDAY:
                tab = tabLayout.getTabAt(2);
                final TabLayout.Tab finalTab2 = tab;
                new Handler().postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                finalTab2.select();
                            }
                        }, 100);
                break;
            case Calendar.THURSDAY:
                tab = tabLayout.getTabAt(3);

                final TabLayout.Tab finalTab3 = tab;
                new Handler().postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                finalTab3.select();
                            }
                        }, 100);
                break;
            case Calendar.FRIDAY:
                tab = tabLayout.getTabAt(4);
                final TabLayout.Tab finalTab4 = tab;
                new Handler().postDelayed(
                        new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                finalTab4.select();
                            }
                        }, 100);
                break;
            case Calendar.SATURDAY:
                tab = tabLayout.getTabAt(5);
                final TabLayout.Tab finalTab5 = tab;
                if (Settings.switchState)
                {
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    finalTab5.select();
                                }
                            }, 100);
                } else
                {
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    tabLayout.getTabAt(0).select();
                                }
                            }, 100);
                }
                break;
            case Calendar.SUNDAY:
                tab = tabLayout.getTabAt(6);
                final TabLayout.Tab finalTab6 = tab;
                if (Settings.switchState)
                {
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    finalTab6.select();
                                }
                            }, 100);
                }else {
                    new Handler().postDelayed(
                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    tabLayout.getTabAt(0).select();
                                }
                            }, 100);
                }
            default:
                break;
        }
    }


    private void retrySavedSettings()
    {
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        Settings.switchState = settings.getBoolean("switchState", true);
        Settings.notificationsOn = settings.getInt("notificationsOn", 3);
        Settings.delayTIme = settings.getInt("delayTime", 0);
    }

    public static RetainFragment findOrCreateRetainFragment(FragmentManager fm)
    {
// Check to see if we have retained the worker fragment.
        RetainFragment mRetainFragment = (RetainFragment) fm.findFragmentByTag("Retain");
// If not retained (or first time running), we need to create and add it.
        if (mRetainFragment == null)
        {
            mRetainFragment = new RetainFragment();
            fm.beginTransaction().add(mRetainFragment, "Retain").
                    commitAllowingStateLoss();
        }
        return mRetainFragment;
    }


}













