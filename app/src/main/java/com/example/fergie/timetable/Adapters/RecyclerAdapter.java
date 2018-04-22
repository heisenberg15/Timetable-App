package com.example.fergie.timetable.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fergie.timetable.Fragments.CreateSubjFragment;
import com.example.fergie.timetable.Fragments.MonFragment;
import com.example.fergie.timetable.MainActivity;
import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;
import com.example.fergie.timetable.Utils.PassSubject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Fergie on 1/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private ArrayList<SubjectModel> list;
    private Context context;
    private MainActivity mainActivity;


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
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.subject.setText(list.get(position).getSubject());
        holder.info.setText(list.get(position).getInfo());
        holder.startTime.setText(list.get(position).getStartTime());
        holder.endTime.setText(list.get(position).getEndTIme());
        holder.color.setText(list.get(position).getColor());

        mainActivity = (MainActivity) context;


        holder.parentLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                list.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, list.size());
                editSubject();
                mainActivity.position = position;
                mainActivity.edit = 1;

            }
        });

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

        TextView subject, info, startTime, endTime, color;
        LinearLayout parentLayout;

        ViewHolder(View itemView)
        {
            super(itemView);

            subject = itemView.findViewById(R.id.subject_id);
            info = itemView.findViewById(R.id.info_id);
            startTime = itemView.findViewById(R.id.show_start_time);
            endTime = itemView.findViewById(R.id.show_end_time);
            color = itemView.findViewById(R.id.show_color_id);
            parentLayout = itemView.findViewById(R.id.recycler_item_id);
        }
    }
}
