package com.example.aaveg2020.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaveg2020.R;
import com.example.aaveg2020.Scoreboard.EventsModel;
import com.example.aaveg2020.events.EventsUtils;

import java.util.ArrayList;

public class RecentEventsAdapter extends RecyclerView.Adapter<RecentEventsAdapter.ViewHolder> {

    ArrayList<EventsModel> events;

    public RecentEventsAdapter(ArrayList<EventsModel> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.hostel_events_card,parent,false);
        ViewHolder holder=new ViewHolder(mView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hostelEventName.setText(events.get(position).getEvent());
        holder.hostelEventPlace.setText(events.get(position).getPosition());
        switch (events.get(position).getPosition())
        {
            case "1":
                //holder.parent.setBackgroundResource(R.drawable.gold);
                holder.img.setImageResource(R.drawable.gold);
                break;
            case "2":
                //holder.parent.setBackgroundResource(R.drawable.silver);
                holder.img.setImageResource(R.drawable.silver);
                break;
            default:
                //holder.parent.setBackgroundResource(R.drawable.bronze);
                holder.img.setBackgroundResource(R.drawable.bronze);
        }
    }

    @Override
    public int getItemCount() {
        if(events!=null)
            return events.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parent;
        TextView hostelEventName, hostelEventPlace;
        CardView card;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hostelEventName = itemView.findViewById(R.id.recent_events_card_name);
            hostelEventPlace = itemView.findViewById(R.id.recent_events_card_place);
            parent=itemView.findViewById(R.id.hostel_events_parent);
            card=itemView.findViewById(R.id.hostel_events_card);
            img=itemView.findViewById(R.id.hostel_events_img);
        }
    }
}
