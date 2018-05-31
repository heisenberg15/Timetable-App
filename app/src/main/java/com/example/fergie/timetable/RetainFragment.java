package com.example.fergie.timetable;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class RetainFragment extends Fragment
{
    private Object mObject;

    /**
     * Empty constructor as per the Fragment documentation
     */
    public RetainFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Make sure this Fragment is retained over a configuration change
        setRetainInstance(true);
    }

    /**
     * Store a single object in this Fragment.
     *
     * @param object The object to store
     */
    public void setObject(Object object)
    {
        mObject = object;
    }

    /**
     * Get the stored object.
     *
     * @return The stored object
     */
    public Object getObject()
    {
        return mObject;
    }
}
