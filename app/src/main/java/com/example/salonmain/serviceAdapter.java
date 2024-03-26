package com.example.salonmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class serviceAdapter extends RecyclerView.Adapter<serviceAdapter.ViewHolder> {

    Context context;

    private List<service_db> listService;

    public serviceAdapter(Context context, List<service_db> listService) {

        this.listService = listService;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve_viewrows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        service_db s1 = listService.get(position);

        double price = s1.getPrice();
        String priceString = String.valueOf(price);

        holder.tvServiceName.setText(s1.getName());
        holder.tvDesc.setText(s1.getDescription());
        holder.tvDuration.setText(s1.getDuration());
        holder.tvPrice.setText(priceString);
    }

    @Override
    public int getItemCount() {
        return listService.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvServiceName, tvDesc, tvDuration, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvServiceName = itemView.findViewById(R.id.retrieveServiceName);
            tvDesc = itemView.findViewById(R.id.retrieveDesc);
            tvDuration = itemView.findViewById(R.id.retrieveDuration);
            tvPrice = itemView.findViewById(R.id.retrievePrice);

        }
    }

}
