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

    SubjectModel subjectModel;
    ArrayList<SubjectModel> subjectModelList;
    RecyclerView recyclerView;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_monday, container, false);

        recyclerView = fragmentView.findViewById(R.id.mon_recycler_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

//        mainActivity = (MainActivity) getActivity();


//        ArrayList<String> list = new ArrayList<>();
//        Log.i(TAG, "fragment: " + mainActivity.check);

        subjectModelList = new ArrayList<>();


        return fragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        createSubject();
        super.onActivityCreated(savedInstanceState);
    }

    public void createSubject()
    {
        mainActivity = (MainActivity) getActivity();
//        Bundle bundle = this.getArguments();
        assert mainActivity != null;
            if (mainActivity.subjectModel != null) {
            subjectModel = mainActivity.subjectModel;
            subjectModelList.add(subjectModel);

            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(subjectModelList);
            recyclerView.setAdapter(recyclerAdapter);
        }

        Log.i(TAG, "fragment: " + mainActivity.check);
    }

}
