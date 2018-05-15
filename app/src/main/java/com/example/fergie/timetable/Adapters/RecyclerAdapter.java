package com.example.fergie.timetable.Adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Settings;
import com.example.fergie.timetable.Utils.AlarmReceiver;
import com.example.fergie.timetable.Utils.Singleton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;
import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Fergie on 1/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private ArrayList<SubjectModel> list;
    private Context context;
    private MainActivity mainActivity;
    public ArrayList<PendingIntent> intentArrayList;


    public RecyclerAdapter(Context context, ArrayList<SubjectModel> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        mainActivity = (MainActivity) context;
        intentArrayList = new ArrayList<>();
        Log.i("pos", "Recycler: position " + position);

        if (list.get(position).getInfo().isEmpty())
        {
            holder.info.setVisibility(View.GONE);
        } else
        {
            holder.info.setText(list.get(position).getInfo());
        }


        holder.moreView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.item_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.edit_item_id:
                                editSubject();
                                mainActivity.position = position;
                                mainActivity.edit = 1;
                                return true;
                            case R.id.delete_item_id:
                                Log.i("pos", "Recycler: position " + position);
                                AlarmManager mAlarm = (AlarmManager) mainActivity.getSystemService(ALARM_SERVICE);
                                Intent intent = new Intent(mainActivity.getApplicationContext(), AlarmReceiver.class);
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(mainActivity.getApplicationContext(), list.get(position).getIntentId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                mAlarm.cancel(pendingIntent);
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenu.show();
            }
        });




        holder.startTime.setText(list.get(position).getStartHour() +  " " +list.get(position).getStartMinute());
        holder.endTime.setText(list.get(position).getEndTIme());
        holder.subject.setText(list.get(position).getSubject());

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    private void editSubject()
    {
        mainActivity.showCreateSubjectFragment();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView subject, info, startTime, endTime;
        LinearLayout parentLayout;
        ImageView moreView;

        ViewHolder(View itemView)
        {
            super(itemView);

            subject = itemView.findViewById(R.id.subject_id);
            info = itemView.findViewById(R.id.info_id);
            startTime = itemView.findViewById(R.id.show_start_time);
            endTime = itemView.findViewById(R.id.show_end_time);
            parentLayout = itemView.findViewById(R.id.recycler_item_id);
            moreView = itemView.findViewById(R.id.more_id);
        }
    }
}
