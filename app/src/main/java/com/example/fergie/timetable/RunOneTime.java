package com.example.fergie.timetable;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.Utils.Singleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RunOneTime extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();

        retrySharedPreferences();
    }


    public void retrySharedPreferences()
    {
        SharedPreferences saveSingleton = getSharedPreferences("saveSingleton", MODE_PRIVATE);
        String mondayList = saveSingleton.getString("mondayList", null);
        String tuesdayList = saveSingleton.getString("tuesdayList", null);
        String wednesdayList = saveSingleton.getString("wednesdayList", null);
        String thursdayList = saveSingleton.getString("thursdayList", null);
        String fridayList = saveSingleton.getString("fridayList", null);
        String saturdayList = saveSingleton.getString("saturdayList", null);
        String sundayList = saveSingleton.getString("sundayList", null);

        if (mondayList != null) {
            ArrayList<SubjectModel> getMondayList = new Gson().fromJson(mondayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getMondayList.size(); i++) {
                Singleton.getInstance().addMonSubject(getMondayList.get(i));
            }
        }

        if (tuesdayList != null) {
            ArrayList<SubjectModel> getTuesdayList = new Gson().fromJson(tuesdayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getTuesdayList.size(); i++) {
                Singleton.getInstance().addTueSubject(getTuesdayList.get(i));
            }
        }

        if (wednesdayList != null) {
            ArrayList<SubjectModel> getWednesdayList = new Gson().fromJson(wednesdayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getWednesdayList.size(); i++) {
                Singleton.getInstance().addWedSubject(getWednesdayList.get(i));
            }
        }

        if (thursdayList != null) {
            ArrayList<SubjectModel> getThursdayList = new Gson().fromJson(thursdayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getThursdayList.size(); i++) {
                Singleton.getInstance().addThuSubject(getThursdayList.get(i));
            }
        }

        if (fridayList != null) {
            ArrayList<SubjectModel> getFridayList = new Gson().fromJson(fridayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getFridayList.size(); i++) {
                Singleton.getInstance().addFriSubject(getFridayList.get(i));
            }
        }

        if (saturdayList != null) {
            ArrayList<SubjectModel> getSaturdayList = new Gson().fromJson(saturdayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getSaturdayList.size(); i++) {
                Singleton.getInstance().addSatSubject(getSaturdayList.get(i));
            }
        }

        if (sundayList != null) {
            ArrayList<SubjectModel> getSundayList = new Gson().fromJson(sundayList, new TypeToken<ArrayList<SubjectModel>>()
            {
            }.getType());

            for (int i = 0; i < getSundayList.size(); i++) {
                Singleton.getInstance().addSunSubject(getSundayList.get(i));
            }
        }


//        updateSingletonLists(mondayList);
//        updateSingletonLists(tuesdayList);
//        updateSingletonLists(wednesdayList);
//        updateSingletonLists(thursdayList);
//        updateSingletonLists(fridayList);
//        updateSingletonLists(saturdayList);
//        updateSingletonLists(sundayList);

    }

}
