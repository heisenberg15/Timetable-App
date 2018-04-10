package com.example.fergie.timetable.Utils;

import com.example.fergie.timetable.Models.SubjectModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Singleton
{
    private static Singleton uniqueInstance;
    private static ArrayList<SubjectModel> list;

    private Singleton()
    {
    }

    public static Singleton getInstance()
    {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
            list = new ArrayList<>();
        }
        return uniqueInstance;
    }

    public void addSubject(SubjectModel subjectModel)
    {
        list.add(subjectModel);
    }

    public ArrayList<SubjectModel> getList()
    {
        return list;
    }
}
