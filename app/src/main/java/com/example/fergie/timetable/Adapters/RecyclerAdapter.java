package com.example.fergie.timetable.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fergie.timetable.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Fergie on 1/18/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    ArrayList<String> list;

    public RecyclerAdapter(ArrayList<String> list)
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
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            textView = itemView.findViewById(R.id.test_id);
        }
    }
}
