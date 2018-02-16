package com.example.fergie.timetable.Models;

import java.io.Serializable;

/**
 * Created by Fergie on 1/20/2018.
 */

public class SubjectModel implements Serializable
{
    private String subject, info, startTime, endTIme, color;

    public SubjectModel(String subject, String info, String startTime, String endTIme, String color)
    {
        this.subject = subject;
        this.info = info;
        this.startTime = startTime;
        this.endTIme = endTIme;
        this.color = color;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public String getEndTIme()
    {
        return endTIme;
    }

    public void setEndTIme(String endTIme)
    {
        this.endTIme = endTIme;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}
