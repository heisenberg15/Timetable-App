package com.example.fergie.timetable;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fergie.timetable.Fragments.FriFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Fragments.SatFragment;
import com.example.fergie.timetable.Fragments.SunFragment;
import com.example.fergie.timetable.Fragments.ThuFragment;
import com.example.fergie.timetable.Fragments.TueFragment;
import com.example.fergie.timetable.Fragments.WedFragment;
import com.example.fergie.timetable.Utils.Singleton;

public class Settings extends AppCompatActivity
{

    private Toolbar toolbar;
    private Switch switchBtn;
    private TextView weekendDaysView;
    MainActivity mainActivity;
    private TextView resetTableBtn;
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
        resetTableBtn = findViewById(R.id.reset_table_view_id);

        initToolbar();
        toggleSwitch();
        checkSwitch();
        setSwitchUi();
        resetTable();

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

    private void resetTable()
    {
        resetTableBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new AlertDialog.Builder(Settings.this)
                        .setTitle(R.string.reset_table_dialog_title)
                        .setMessage(R.string.reset_table_dialog_message)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Singleton.getInstance().getMonList().clear();
                                Singleton.getInstance().getTueList().clear();
                                Singleton.getInstance().getWedList().clear();
                                Singleton.getInstance().getThuList().clear();
                                Singleton.getInstance().getFriList().clear();
                                Singleton.getInstance().getSatList().clear();
                                Singleton.getInstance().getSunList().clear();

                                Toast.makeText(Settings.this, "All data removed", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }


}