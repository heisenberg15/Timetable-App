package com.example.fergie.timetable.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fergie.timetable.Adapters.RecyclerAdapter;
import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Utils.AlarmReceiver;
import com.example.fergie.timetable.Utils.Singleton;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;
import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Fergie on 1/9/2018.
 */

public class MonFragment extends Fragment
{

    RecyclerView recyclerView;
    MainActivity mainActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_monday, container, false);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        mainActivity = (MainActivity) getActivity();

        recyclerView = getActivity().findViewById(R.id.mon_recycler_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        updateSubjectList();
    }

    public void createSubject(SubjectModel subjectModel)
    {
        if (mainActivity.edit == 0) {
            Singleton.getInstance().addMonSubject(subjectModel);

            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), Singleton.getInstance().getMonList());
            recyclerView.setAdapter(recyclerAdapter);
        } else if (mainActivity.edit == 1) {
            Singleton.getInstance().getMonList().set(mainActivity.position, subjectModel);
//
//            AlarmManager mAlarm = (AlarmManager) mainActivity.getSystemService(ALARM_SERVICE);
//            mAlarm.cancel(Singleton.getInstance().getIntentArrayList().get(mainActivity.position));
//
//            Intent intent = new Intent(mainActivity.getApplicationContext(), AlarmReceiver.class);
//            intent.putExtra("subject", subjectModel.getSubject());
//
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), mainActivity.position, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));
//            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(subjectModel.getStartHour()));
//            calendar.set(Calendar.MINUTE, Integer.parseInt(subjectModel.getStartMinute()));
//
//            long data = calendar.getTimeInMillis();
//
//            mAlarm.setInexactRepeating(AlarmManager.RTC, data, TimeUnit.MINUTES.toMillis(2), pendingIntent);
//            Singleton.getInstance().getIntentArrayList().set(mainActivity.position, pendingIntent);

            Log.i("pos", "createSubject: main.position " + mainActivity.position);
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), Singleton.getInstance().getMonList());
            recyclerView.setAdapter(recyclerAdapter);
            mainActivity.edit = 0;
        }
    }

    private void updateSubjectList()
    {
        SharedPreferences saveSingleton = getActivity().getSharedPreferences("saveSingleton", MODE_PRIVATE);

        if (saveSingleton.getString("mondayList", null) != null) {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), Singleton.getInstance().getMonList());
            recyclerView.setAdapter(recyclerAdapter);
        }
    }


}






