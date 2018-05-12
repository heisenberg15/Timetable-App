package com.example.fergie.timetable;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
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
    TextView notificationTime;
    private LinearLayout notificationView;
    public static int notificationsOn = 3;

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
        notificationView = findViewById(R.id.notification_container_id);
        notificationTime = findViewById(R.id.time_before_class_id);

        initToolbar();
        toggleSwitch();
        checkSwitch();
        setSwitchUi();
        setNotificationUi();
        resetTable();
        setNotification();

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

        if (notificationsOn == 1)
        {
            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("notificationsOn", 1);
            editor.apply();
        } else if (notificationsOn == 2)
        {
            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("notificationsOn", 2);
            editor.apply();
        } else if (notificationsOn == 3)
        {
            SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("notificationsOn", 3);
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

    private void setNotificationUi()
    {
        if (notificationsOn == 1)
        {
            notificationTime.setText("No notifications");
        } else if (notificationsOn == 2)
        {
            notificationTime.setText("Instant notifications");
        } else if (notificationsOn == 3)
        {
            notificationTime.setText("5 minutes before class");
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
                        .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

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
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        })
                        .show();
            }
        });
    }

    private void setNotification()
    {
        notificationView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showMenu(v);
            }
        });
    }

    private void showMenu(View v)
    {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.notifications_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.off_id:
                        notificationsOn = 1;
                        notificationTime.setText("No notifications");
                        return true;
                    case R.id.instant_id:
                        notificationsOn = 2;
                        notificationTime.setText("Instant notifications");
                        return true;
                    case R.id.custom_time_id:
                        notificationsOn = 3;
                        final Dialog dialog = new Dialog(Settings.this, R.style.Theme_Dialog);
                        dialog.setContentView(R.layout.dialog_pick_number);
                        dialog.setTitle("Notification time");
                        NumberPicker numberPicker = dialog.findViewById(R.id.number_picker_id);
                        numberPicker.setMinValue(1);
                        numberPicker.setMaxValue(60);
                        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
                        {
                            @Override
                            public void onValueChange(NumberPicker picker, int oldVal, int newVal)
                            {
                                notificationTime.setText(newVal + " minutes before class");

                            }
                        });
                        dialog.show();

                        TextView cancelDialogBtn = dialog.findViewById(R.id.cancel_dialog_id);
                        cancelDialogBtn.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                dialog.dismiss();
                            }
                        });

//                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("Timetable")
//                                .setContentText("Class starts in 5 minutes");
//
//                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                        assert manager != null;
//                        manager.notify(1, builder.build());
                    default:
                        return true;
                }
            }
        });
        popupMenu.show();
    }

}