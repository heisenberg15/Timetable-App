package com.example.fergie.timetable.Fragments;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fergie.timetable.Communicator;
import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Settings;
import com.example.fergie.timetable.Utils.AlarmReceiver;
import com.example.fergie.timetable.Utils.Singleton;
import com.savvisingh.colorpickerdialog.ColorPickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Fergie on 2/28/2018.
 */

public class CreateSubjFragment extends Fragment
{

    EditText subjectEditText, infoEditText;
    TextView startTextView, endTextView, startTimeBtn, endTimeBtn;
    public Button colorBtn;
    FloatingActionButton fab;
    Communicator comm;
    MainActivity mainActivity;
    public String AM_PM;
    int startHours, startMins, endHours, endMins;
    String endTime, startTime;
    private int pickedColor;
    Toolbar toolbar;
    ColorPickerDialog dialog;
    ArrayList<PendingIntent> intentArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.fragment_create_subject, container, false);

//        toolbar = fragmentView.findViewById(R.id.create_subject_toolbar_id);
//        if (toolbar != null)
//        {
//            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        }
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
        colorBtn = getActivity().findViewById(R.id.choose_color_id);
        startTimeBtn = getActivity().findViewById(R.id.start_time_btn_id);
        endTimeBtn = getActivity().findViewById(R.id.end_time_btn_id);
        fab = getActivity().findViewById(R.id.save_subject_fab_id);
        comm = (Communicator) getActivity();
        mainActivity = (MainActivity) getActivity();
        intentArrayList = new ArrayList<>();


        if (savedInstanceState != null)
        {
            mainActivity.edit = savedInstanceState.getInt("edit");
        }

        if (savedInstanceState != null && mainActivity.edit == 1)
        {
            startHours = savedInstanceState.getInt("startHours");
            startMins = savedInstanceState.getInt("startMins");
            startTime = savedInstanceState.getString("startTime");
            endHours = savedInstanceState.getInt("endHours");
            endMins = savedInstanceState.getInt("endMins");
            endTime = savedInstanceState.getString("endTime");
            pickedColor = savedInstanceState.getInt("color");

            startTimeBtn.setText(startTime);
            endTimeBtn.setText(endTime);
            colorBtn.setBackgroundColor(pickedColor);

        }

        onSaveSubject();
        showStartTimePicker();
        showEndTimePicker();


        colorBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                chooseColor();
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getValues();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (dialog != null)
        {
            dialog.dismiss();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (mainActivity.edit == 1)
        {
            outState.putInt("startHours", startHours);
            outState.putInt("startMins", startMins);
            outState.putString("startTime", startTime);
            outState.putInt("endHours", endHours);
            outState.putInt("endMins", endMins);
            outState.putString("endTime", endTime);
            outState.putInt("color", pickedColor);
            outState.putInt("edit", mainActivity.edit);
        }
    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
//    {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                getActivity().onBackPressed();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }

    private void showStartTimePicker()
    {
        startTimeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog builder = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @SuppressLint("DefaultLocale")
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int min)
                            {
                                if (hour < 12)
                                {
                                    AM_PM = "AM";
                                } else
                                {
                                    AM_PM = "PM";
                                }
                                boolean isPM = (hour >= 12);
                                startHours = hour;
                                startMins = min;
                                startTime = String.format("%02d:%02d %s", (hour == 12 || hour == 0) ? 12 : hour % 12, min, isPM ? "PM" : "AM");
                                startTimeBtn.setText(startTime);
                            }
                        }, 12, 8, false);
                builder.show();
            }
        });
    }

    private void showEndTimePicker()
    {
        endTimeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TimePickerDialog builder = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int min)
                            {
                                if (hour < 12)
                                {
                                    AM_PM = "AM";
                                } else
                                {
                                    AM_PM = "PM";
                                }
                                boolean isPM = (hour >= 12);
                                endHours = hour;
                                endMins = min;
                                endTime = String.format("%02d:%02d %s", (hour == 12 || hour == 0) ? 12 : hour % 12, min, isPM ? "PM" : "AM");

                                endTimeBtn.setText(endTime);
                            }
                        }, 12, 8, false);
                builder.show();
            }
        });
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
                String startHour = String.valueOf(startHours);
                String startMinute = String.valueOf(startMins);
                String end = endTime;
                String start = startTime;
                String color = String.valueOf(pickedColor);
                int currentTimeId = (int) System.currentTimeMillis();
                long data;

                SubjectModel subjectModel = new SubjectModel(subject, info, startHour, startMinute, start, end, color, currentTimeId);

                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                calendar.set(Calendar.HOUR, startHours);
                calendar.set(Calendar.MINUTE, startMins);
//                    if (AM_PM.equals("AM"))
//                    {
//                        calendar.set(Calendar.AM_PM, Calendar.AM);
//                    } else if (AM_PM.equals("PM"))
//                    {
//                        calendar.set(Calendar.AM_PM, Calendar.PM);
//                    }
//                long data = calendar.getTimeInMillis() - TimeUnit.MINUTES.toMillis(60);

                if (Settings.notificationsOn == 3)
                {
                    data = calendar.getTimeInMillis() - TimeUnit.MINUTES.toMillis(Settings.delayTIme);
                } else
                {
                    data = calendar.getTimeInMillis();
                }

                Intent intent = new Intent(mainActivity.getApplicationContext(), AlarmReceiver.class);
                intent.putExtra("subject", subject);
                intent.putExtra("testTime", calendar.getTimeInMillis());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), currentTimeId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager alarmManager = (AlarmManager) mainActivity.getApplicationContext().getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC, data, pendingIntent);

                if (mainActivity.edit == 1)
                {
                    Bundle getBundle = getArguments();

//                    Log.i("listSize", "list size before: " + Singleton.getInstance().getIdList().size());
                    PendingIntent cancelIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), getBundle.getInt("intentId"), intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    alarmManager.cancel(cancelIntent);
                    Singleton.getInstance().getIdList().remove(Integer.valueOf(getBundle.getInt("intentId")));
//                    Log.i("listSize", "list size after: " + Singleton.getInstance().getIdList().size());
                }
                Singleton.getInstance().addRequestId(subjectModel.getIntentId());

                mainActivity.newSubject = 0;
                comm.respond(subjectModel);
                getActivity().onBackPressed();
            }
        });
    }

    public void chooseColor()
    {
//        int[] colors = {R.color.colorPrimaryDark, R.color.colorPrimary, R.color.colorAccent};
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FAFAFA"));
        colors.add(Color.parseColor("#2ec4b6"));
        colors.add(Color.parseColor("#ff9f1c"));
        colors.add(Color.parseColor("#70566d"));
        colors.add(Color.parseColor("#26c6da"));
        colors.add(Color.parseColor("#512DA8"));
        colors.add(Color.parseColor("#e71d36"));
        colors.add(Color.parseColor("#d6bda1"));
        colors.add(Color.parseColor("#142737"));
        dialog = ColorPickerDialog.newInstance(
                ColorPickerDialog.SELECTION_SINGLE,
                colors,
                3, // Number of columns
                ColorPickerDialog.SIZE_SMALL);

        dialog.show(getActivity().getFragmentManager(), "some_tag");


        dialog.setOnDialodButtonListener(new ColorPickerDialog.OnDialogButtonListener()
        {
            @Override
            public void onDonePressed(ArrayList<Integer> mSelectedColors)
            {
                if (!mSelectedColors.isEmpty())
                {
                    pickedColor = mSelectedColors.get(0);
                    colorBtn.setBackgroundColor(pickedColor);
                }
            }

            @Override
            public void onDismiss()
            {

            }
        });
    }

    private void getValues()
    {
        if (mainActivity.edit == 1)
        {
            Bundle bundle = getArguments();
            assert bundle != null;
            startTime = bundle.getString("startTime");
            endTime = bundle.getString("endTime");
            subjectEditText.setText(String.valueOf(bundle.getString("subject")), TextView.BufferType.EDITABLE);
            infoEditText.setText(String.valueOf(bundle.getString("info")), TextView.BufferType.EDITABLE);
            startHours = bundle.getInt("startHour");
            startMins = bundle.getInt("startMinute");
            pickedColor = bundle.getInt("color");
            startTimeBtn.setText(startTime);
            endTimeBtn.setText(endTime);
            colorBtn.setBackgroundColor(pickedColor);
        }

        if (mainActivity.newSubject == 1)
        {
            subjectEditText.setText("");
            infoEditText.setText("");
        }
    }


}
















