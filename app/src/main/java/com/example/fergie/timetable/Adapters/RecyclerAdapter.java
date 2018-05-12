package com.example.fergie.timetable.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;

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
        holder.startTime.setText(list.get(position).getStartTime());
        holder.endTime.setText(list.get(position).getEndTIme());
        holder.subject.setText(list.get(position).getSubject());

        if (list.get(position).getInfo().isEmpty())
        {
            holder.info.setVisibility(View.GONE);
        } else
        {
            holder.info.setText(list.get(position).getInfo());
        }

        mainActivity = (MainActivity) context;


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
