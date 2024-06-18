package com.example.revamp_redbus_clone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revamp_redbus_clone.R;
import com.example.revamp_redbus_clone.model.Bus;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {

    private List<Bus> busList;

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus, parent, false);
        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusAdapter.BusViewHolder holder, int position) {
        Bus bus = busList.get(position);

        holder.tvBusName.setText(bus.getBusName());
        holder.tvBusType.setText(bus.getBusType());
        holder.tvStartingTime.setText(bus.getStartingTime());
        holder.tvReachingTime.setText(bus.getReachingTime());
        holder.tvPrice.setText(bus.getPrice());
        holder.tvDuration.setText(bus.getDuration());
        holder.tvStarting.setText(bus.getStartingPoint());
        holder.tvDestination.setText(bus.getDestination());
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {
        TextView tvBusName, tvBusType, tvStartingTime, tvReachingTime, tvPrice, tvDuration, tvStarting, tvDestination;
        public BusViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBusName = itemView.findViewById(R.id.tv_busname);
            tvBusType = itemView.findViewById(R.id.tv_bustype);
            tvStartingTime = itemView.findViewById(R.id.tv_startingtime);
            tvReachingTime = itemView.findViewById(R.id.tv_reachingtime);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvStarting = itemView.findViewById(R.id.tv_starting);
            tvDestination = itemView.findViewById(R.id.tv_destination);
        }
    }
}
