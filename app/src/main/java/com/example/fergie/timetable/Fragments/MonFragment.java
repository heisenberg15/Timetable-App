package com.example.fergie.timetable.Fragments;

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
import com.example.fergie.timetable.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

/**
 * Created by Fergie on 1/9/2018.
 */

public class MonFragment extends Fragment
{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_monday, container, false);

        RecyclerView recyclerView = fragmentView.findViewById(R.id.mon_recycler_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MainActivity mainActivity = (MainActivity) getActivity();


        ArrayList<String> list = new ArrayList<>();

        Log.i(TAG, "fragment: " + mainActivity.check);

        list.add(mainActivity.check);
        list.add("lakfajs");
        list.add("gsdg");
        list.add("nnghjg");
        list.add("bnmbm");
        list.add("okop");
        list.add("okop");



        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);



        return fragmentView;
    }

}
