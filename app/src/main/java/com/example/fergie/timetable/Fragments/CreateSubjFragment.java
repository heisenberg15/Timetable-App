package com.example.fergie.timetable.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fergie.timetable.Communicator;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;

/**
 * Created by Fergie on 2/28/2018.
 */

public class CreateSubjFragment extends Fragment
{

    EditText subjectEditText, infoEditText;
    TextView startTextView, endTextView, colorTextView;
    FloatingActionButton fab;
    Communicator comm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_create_subject, container, false);

        return fragmentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        subjectEditText = getActivity().findViewById(R.id.subject_edit_text_id);
        infoEditText = getActivity().findViewById(R.id.info_edit_text_id);
        startTextView = getActivity().findViewById(R.id.start_time_id);
        endTextView = getActivity().findViewById(R.id.end_time_id);
        colorTextView = getActivity().findViewById(R.id.choose_color_id);
        fab = getActivity().findViewById(R.id.save_subject_fab_id);
        comm = (Communicator) getActivity();

        onSaveSubject();

    }

    private void onSaveSubject()
    {
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String subject = subjectEditText.getText().toString();
                String info = infoEditText.getText().toString();
                String start = startTextView.getText().toString();
                String end = endTextView.getText().toString();
                String color = colorTextView.getText().toString();

                SubjectModel subjectModel = new SubjectModel(subject, info, start, end, color);

                comm.respond(subjectModel);

            }
        });
    }
}
