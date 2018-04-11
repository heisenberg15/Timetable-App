package com.example.fergie.timetable.Utils;

import com.example.fergie.timetable.Models.SubjectModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Singleton
{
    private static Singleton uniqueInstance;
    private static ArrayList<SubjectModel> monlist;
    private static ArrayList<SubjectModel> tuelist;

    private Singleton()
    {
    }

    public static Singleton getInstance()
    {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
            monlist = new ArrayList<>();
            tuelist = new ArrayList<>();
        }
        return uniqueInstance;
    }

    public void addMonSubject(SubjectModel subjectModel)
    {
        monlist.add(subjectModel);
    }


    public void addTueSubject(SubjectModel subjectModel)
    {
        tuelist.add(subjectModel);
    }

    public ArrayList<SubjectModel> getMonList()
    {
        return monlist;
    }

    public ArrayList<SubjectModel> getTueList()
    {
        return tuelist;
    }



}
