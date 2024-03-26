package com.example.salonmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class staff_adapter extends RecyclerView.Adapter<staff_adapter.ViewHolder>{
    private Context context;
    private List<userAccount_db> userList;

    public staff_adapter(Context context, List<userAccount_db> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_view_rows, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        userAccount_db u1 = userList.get(position);
        holder.staffName.setText(u1.getName());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView staffName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            staffName = itemView.findViewById(R.id.staff_name);
        }
    }

}
