package com.example.fergie.timetable;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.Models.SubjectModel;

import java.io.Serializable;

public class CreateSubjectActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText subjectEditText, infoEditText;
    private TextView startTimeTextView, endTimeTextView, colorTextView;
    SubjectModel subjectModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        toolbar = findViewById(R.id.create_subject_toolbar_id);
        fab = findViewById(R.id.save_subject_fab_id);
        subjectEditText = findViewById(R.id.subject_edit_text_id);
        infoEditText = findViewById(R.id.info_edit_text_id);
        startTimeTextView = findViewById(R.id.start_time_id);
        endTimeTextView = findViewById(R.id.end_time_id);
        colorTextView = findViewById(R.id.choose_color_id);

        initToolbar();

        saveSubject();
    }


    private void initToolbar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveSubject()
    {
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String subject = subjectEditText.getText().toString();
                String info = infoEditText.getText().toString();
                String startTime = startTimeTextView.getText().toString();
                String endTime = endTimeTextView.getText().toString();
                String color = colorTextView.getText().toString();

                subjectModel = new SubjectModel(subject, info, startTime, endTime, color);

                Bundle bundle = new Bundle();
                bundle.putSerializable("SUBJECT", subjectModel);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Bundle", bundle);
                startActivity(intent);
            }
        });
    }
}









