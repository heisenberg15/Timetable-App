package com.example.fergie.timetable.Utils;

import android.app.PendingIntent;

import com.example.fergie.timetable.Models.SubjectModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Singleton
{
    private static Singleton uniqueInstance;
    private static ArrayList<SubjectModel> monList;
    private static ArrayList<SubjectModel> tueList;
    private static ArrayList<SubjectModel> wedList;
    private static ArrayList<SubjectModel> thuList;
    private static ArrayList<SubjectModel> friList;
    private static ArrayList<SubjectModel> satList;
    private static ArrayList<SubjectModel> sunList;
    private static ArrayList<Integer> requestIdList;

    private Singleton()
    {
    }

    public static Singleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new Singleton();
            monList = new ArrayList<>();
            tueList = new ArrayList<>();
            wedList = new ArrayList<>();
            thuList = new ArrayList<>();
            friList = new ArrayList<>();
            satList = new ArrayList<>();
            sunList = new ArrayList<>();
            requestIdList = new ArrayList<>();

        }
        return uniqueInstance;
    }

    public void addMonSubject(SubjectModel subjectModel)
    {
        monList.add(subjectModel);
    }

    public void addTueSubject(SubjectModel subjectModel)
    {
        tueList.add(subjectModel);
    }

    public void addWedSubject(SubjectModel subjectModel)
    {
        wedList.add(subjectModel);
    }

    public void addThuSubject(SubjectModel subjectModel)
    {
        thuList.add(subjectModel);
    }

    public void addFriSubject(SubjectModel subjectModel)
    {
        friList.add(subjectModel);
    }

    public void addSatSubject(SubjectModel subjectModel)
    {
        satList.add(subjectModel);
    }

    public void addSunSubject(SubjectModel subjectModel)
    {
        sunList.add(subjectModel);
    }

    public void addRequestId(int id)
    {
        requestIdList.add(id);
    }


    public ArrayList<SubjectModel> getMonList()
    {
        return monList;
    }

    public ArrayList<SubjectModel> getTueList()
    {
        return tueList;
    }

    public ArrayList<SubjectModel> getWedList()
    {
        return wedList;
    }

    public ArrayList<SubjectModel> getThuList()
    {
        return thuList;
    }

    public ArrayList<SubjectModel> getFriList()
    {
        return friList;
    }

    public ArrayList<SubjectModel> getSatList()
    {
        return satList;
    }

    public ArrayList<SubjectModel> getSunList()
    {
        return sunList;
    }

    public ArrayList<Integer> getIdList()
    {
        return requestIdList;
    }
}
