package com.example.fergie.timetable.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.zip.Inflater;

import static android.content.ContentValues.TAG;

/**
 * Created by Fergie on 1/9/2018.
 */

public class MonFragment extends Fragment
{


    RecyclerView recyclerView;

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

        recyclerView =getActivity().findViewById(R.id.mon_recycler_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void createSubject(SubjectModel subjectModel)
    {

        ArrayList<SubjectModel> subjectModelList = new ArrayList<>();
        subjectModelList.add(subjectModel);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(subjectModelList);
        recyclerView.setAdapter(recyclerAdapter);


//        Log.i(TAG, "fragment: " + mainActivity.check);
    }

}






