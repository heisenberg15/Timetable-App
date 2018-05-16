package com.example.fergie.timetable.Fragments;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fergie.timetable.Communicator;
import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Settings;
import com.example.fergie.timetable.Utils.AlarmReceiver;
import com.example.fergie.timetable.Utils.Singleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Fergie on 2/28/2018.
 */

public class CreateSubjFragment extends Fragment
{

    EditText subjectEditText, infoEditText;
    TextView startTextView, endTextView, colorTextView;
    FloatingActionButton fab;
    Communicator comm;
    MainActivity mainActivity;
    public String AM_PM;
    int hours, minutes;
    ArrayList<PendingIntent> intentArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_create_subject, container, false);

        return fragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        subjectEditText = getActivity().findViewById(R.id.subject_edit_text_id);
        infoEditText = getActivity().findViewById(R.id.info_edit_text_id);
        startTextView = getActivity().findViewById(R.id.start_time_id);
        endTextView = getActivity().findViewById(R.id.end_time_id);
        colorTextView = getActivity().findViewById(R.id.choose_color_id);
        fab = getActivity().findViewById(R.id.save_subject_fab_id);
        comm = (Communicator) getActivity();
        mainActivity = (MainActivity) getActivity();
        intentArrayList = new ArrayList<>();

        onSaveSubject();
        showTimePickerDialog();

    }

    private void showTimePickerDialog()
    {
        startTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog builder = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int min)
                            {
                                if (hour < 12)
                                {
                                    AM_PM = "AM";
                                } else
                                {
                                    AM_PM = "PM";
                                }

                                hours = hour;
                                minutes = min;
                                startTextView.setText(hour + "");
                            }
                        }, 2, 8, false);
                builder.show();
            }
        });

    }

    private void onSaveSubject()
    {

        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String subject = subjectEditText.getText().toString();
                String info = infoEditText.getText().toString();
                String startHour = String.valueOf(hours);
                String startMinute = String.valueOf(minutes);
                String end = "12 00";
                String color = colorTextView.getText().toString();
                int currentTimeId = (int) System.currentTimeMillis();

                SubjectModel subjectModel = new SubjectModel(subject, info, startHour, startMinute, end, color, currentTimeId);

                if (Settings.notificationsOn == 3)
                {
//                    startHour = Integer.parseInt(list.get(position).getStartHour());
//                    startMinute = Integer.parseInt(list.get(position).getStartMinute());
                    Intent intent = new Intent(mainActivity.getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("subject", subject);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), currentTimeId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Calendar calendar = Calendar.getInstance();

                    calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));
                    calendar.set(Calendar.HOUR_OF_DAY, hours);
                    calendar.set(Calendar.MINUTE, minutes);
//                    if (AM_PM.equals("AM"))
//                    {
//                        calendar.set(Calendar.AM_PM, Calendar.AM);
//                    } else if (AM_PM.equals("PM"))
//                    {
//                        calendar.set(Calendar.AM_PM, Calendar.PM);
//                    }
                    long data = calendar.getTimeInMillis();

                    AlarmManager alarmManager = (AlarmManager) mainActivity.getApplicationContext().getSystemService(ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC, data, pendingIntent);

                    if (mainActivity.edit == 1)
                    {
                        Bundle getBundle = getArguments();

                        PendingIntent cancelIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), getBundle.getInt("intentId"), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManager.cancel(cancelIntent);
                    }
                }

                Singleton.getInstance().addRequestId(subjectModel.getIntentId());
                comm.respond(subjectModel);
                getActivity().onBackPressed();
            }
        });
    }


}
















