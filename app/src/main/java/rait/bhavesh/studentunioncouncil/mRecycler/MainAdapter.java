package rait.bhavesh.studentunioncouncil.mRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import rait.bhavesh.studentunioncouncil.DataModel.EventModel;
import rait.bhavesh.studentunioncouncil.Picasso.PicassoClient;
import rait.bhavesh.studentunioncouncil.R;

/**
 * Created by Archana on 6/9/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    Context c;
    ArrayList<EventModel> events;

    public MainAdapter(ArrayList<EventModel> events, Context c) {
        this.events = events;
        this.c = c;
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.datamodel,parent,false);
        MainHolder holder=new MainHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
            holder.name.setText(events.get(position).getName());
        holder.name.setText(events.get(position).getDate());
        holder.name.setText(events.get(position).getVenue());
        PicassoClient.downLoadImage(c, events.get(position).getImageUrl(), holder.image);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
