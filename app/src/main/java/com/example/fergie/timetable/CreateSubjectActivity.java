package com.example.fergie.timetable;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.fergie.timetable.Fragments.MonFragment;

public class CreateSubjectActivity extends AppCompatActivity
{

    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        toolbar = findViewById(R.id.create_subject_toolbar_id);
        fab = findViewById(R.id.save_subject_fab_id);

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("NAME", "jora");
                startActivity(intent);
            }
        });
    }
}
