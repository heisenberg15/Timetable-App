package com.example.fergie.timetable.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

import com.example.fergie.timetable.Communicator;
import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Settings;
import com.example.fergie.timetable.Utils.AlarmReceiver;

import java.util.Calendar;

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

        onSaveSubject();

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
                String start = startTextView.getText().toString();
                String end = endTextView.getText().toString();
                String color = colorTextView.getText().toString();

                SubjectModel subjectModel = new SubjectModel(subject, info, start, end, color);

//                if (Settings.notificationsOn)
//                {
//                    Intent intent = new Intent(mainActivity.getApplicationContext(), AlarmReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), 0, intent,0);
//
//                    Calendar calendar = Calendar.getInstance();
//                    Calendar intervalCalendar = Calendar.getInstance();
//                    calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
//                    calendar.set(Calendar.HOUR, 4);
//                    calendar.set(Calendar.MINUTE, 26);
//                    long data = calendar.getTimeInMillis();
//                    intervalCalendar.set(Calendar.MINUTE, 1);
//
//                    int inter = Integer.parseInt(subject);
//
//                    AlarmManager alarmManager = (AlarmManager) mainActivity.getApplication().getSystemService(ALARM_SERVICE);
//                    alarmManager.setInexactRepeating(AlarmManager.RTC, data, 10000,pendingIntent);
//                }

                comm.respond(subjectModel);
                getActivity().onBackPressed();
            }
        });
    }


}
















