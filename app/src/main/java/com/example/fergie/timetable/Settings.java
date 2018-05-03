package com.example.fergie.timetable;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.fergie.timetable.Fragments.FriFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Fragments.SatFragment;
import com.example.fergie.timetable.Fragments.SunFragment;
import com.example.fergie.timetable.Fragments.ThuFragment;
import com.example.fergie.timetable.Fragments.TueFragment;
import com.example.fergie.timetable.Fragments.WedFragment;

public class Settings extends AppCompatActivity
{

    Toolbar toolbar;
    Switch switchBtn;
    TextView weekendDaysView;
    MainActivity mainActivity;
    static boolean switchState;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = findViewById(R.id.settings_toolbar_id);
        switchBtn = findViewById(R.id.switcher_id);
        weekendDaysView = findViewById(R.id.weekend_days_view_id);
        mainActivity = new MainActivity();

        initToolbar();
        toggleSwitch();
        checkSwitch();
        setSwitchUi();

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (switchState)
        {
            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("switchState", true);
            editor.apply();
        } else
        {
            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("switchState", false);
            editor.apply();
        }
    }

    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void toggleSwitch()
    {
        weekendDaysView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switchBtn.toggle();
            }
        });
    }

    private void checkSwitch()
    {
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                switchState = isChecked;

//                MonFragment monFragment = new MonFragment();
//                TueFragment tueFragment = new TueFragment();
//                WedFragment wedFragment = new WedFragment();
//                ThuFragment thuFragment = new ThuFragment();
//                FriFragment friFragment = new FriFragment();
//                SatFragment satFragment = new SatFragment();
//                SunFragment sunFragment = new SunFragment();
//
//
//                if (isChecked) {
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.satFragment, "Sat");
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.sunFragment, "Sun");
//                } else {
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.monFragment, "Mon");
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.tueFragment, "Tue");
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.wedFragment, "Wed");
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.thuFragment, "Thu");
//                    mainActivity.myPagerAdapter.addFragment(mainActivity.friFragment, "Fri");
//                }
//
//                mainActivity.viewPager.setAdapter(mainActivity.myPagerAdapter);
            }
        });
    }

    private void setSwitchUi()
    {
        if (switchState)
        {
            switchBtn.setChecked(true);
        } else
        {
            switchBtn.setChecked(false);
        }
    }


}