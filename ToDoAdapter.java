package com.andi.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter {
    ArrayList<ToDo> toDos;
    Context ctx;
    LayoutInflater inflater;
    ToDoAdapter(Context ctx, ArrayList<ToDo> toDos) {
        this.ctx = ctx;
        this.toDos = toDos;
        this.inflater = LayoutInflater.from(ctx);
    }

    class ToDoVH extends RecyclerView.ViewHolder {
        private final TextView tvWhat;
        private final TextView tvTime;
        ToDoVH(View iView) {
            super(iView);
            tvWhat = (TextView) iView.findViewById(R.id.tv_what);
            tvTime = (TextView) iView.findViewById(R.id.tv_time);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item,
                parent, false);
// create and return the ViewHolder of this item View
        return new ToDoVH(itemView);

    }
    @Override
    public void onBindViewHolder(
            @NonNull RecyclerView.ViewHolder holder, int position) {
        ToDo c = toDos.get(position);
        ToDoVH vh = (ToDoVH) holder; // cast to ContactVH
        vh.tvWhat.setText(c.what);
        vh.tvTime.setText(c.time);
    }
    @Override
    public int getItemCount() {
        return toDos.size();
    }
}
