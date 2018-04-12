package com.example.fergie.timetable.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fergie.timetable.Adapters.RecyclerAdapter;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Utils.Singleton;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Fergie on 1/9/2018.
 */

public class TueFragment extends Fragment
{

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_tuesday, container, false);

        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.tue_recycler_view_id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        updateSubjectList();
    }

    public void createSubject(SubjectModel subjectModel)
    {

        Singleton.getInstance().addTueSubject(subjectModel);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext() ,Singleton.getInstance().getTueList());
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void updateSubjectList()
    {
        SharedPreferences saveSingleton = getActivity().getSharedPreferences("saveSingleton", MODE_PRIVATE);

        if (saveSingleton.getString("tuesdayList", null) != null) {
            RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), Singleton.getInstance().getTueList());
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

}









