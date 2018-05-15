package com.example.fergie.timetable.Models;

import java.io.Serializable;

/**
 * Created by Fergie on 1/20/2018.
 */

public class SubjectModel implements Serializable
{
    private String subject, info, startHour, startMinute, endTIme, color;
    private int intentId;

    public SubjectModel(String subject, String info, String startHour, String startMinute, String endTIme, String color, int intentId)
    {
        this.subject = subject;
        this.info = info;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endTIme = endTIme;
        this.color = color;
        this.intentId = intentId;
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

    public String getStartHour()
    {
        return startHour;
    }

    public void setStartHour(String startHour)
    {
        this.startHour = startHour;
    }

    public String getStartMinute()
    {
        return startMinute;
    }

    public void setStartMinute(String startMinute)
    {
        this.startMinute = startMinute;
    }

    public String getEndTIme()
    {
        return endTIme;
    }

    public void setEndTIme(String endTIme)
    {
        this.endTIme = endTIme;
    }

    public int getIntentId()
    {
        return intentId;
    }

    public void setIntentId(int intentId)
    {
        this.intentId = intentId;
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
