package com.example.fergie.timetable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fergie.timetable.Models.SubjectModel;
import com.example.fergie.timetable.R;

import java.util.ArrayList;

/**
 * Created by Fergie on 1/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private ArrayList<SubjectModel> list;

    public RecyclerAdapter(ArrayList<SubjectModel> list)
    {
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
    public void onBindViewHolder(ViewHolder holder, int position)
    {
//        SubjectModel subjectModel = list.get(position);
        holder.subject.setText(list.get(position).getSubject());
        holder.info.setText(list.get(position).getInfo());
        holder.startTime.setText(list.get(position).getStartTime());
        holder.endTime.setText(list.get(position).getEndTIme());
        holder.color.setText(list.get(position).getColor());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView subject, info, startTime, endTime, color;

        public ViewHolder(View itemView)
        {
            super(itemView);

            subject = itemView.findViewById(R.id.subject_id);
            info = itemView.findViewById(R.id.info_id);
            startTime = itemView.findViewById(R.id.show_start_time);
            endTime = itemView.findViewById(R.id.show_end_time);
            color = itemView.findViewById(R.id.show_color_id);

        }
    }
}
