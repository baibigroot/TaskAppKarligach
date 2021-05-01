package com.example.taskappkarligach.adapter;

import android.app.AlertDialog;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskappkarligach.App;
import com.example.taskappkarligach.R;
import com.example.taskappkarligach.TaskModel;
import com.example.taskappkarligach.databinding.ItemTaskBinding;


import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private java.util.List<TaskModel> list;
    private ItemClickListener listener;
    private ItemTaskBinding binding;

    public TaskAdapter() {
        list = new ArrayList<>();
    }
    public void addList(java.util.List<TaskModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addItem(TaskModel title) {
        list.add(title);
        notifyItemInserted(list.size() - 1);
    }

    public void setItem(TaskModel title, int position) {
        list.set(position, title);
        notifyItemChanged(position);
    }

    @androidx.annotation.NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
       return new TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull TaskViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class TaskViewHolder extends RecyclerView.ViewHolder implements android.view.View.OnClickListener {

        private TaskModel title;

        public TaskViewHolder(@androidx.annotation.NonNull ItemTaskBinding itemView) {
            super(itemView.getRoot());

            binding = itemView;

            binding.getRoot().setOnClickListener(this);
            binding.getRoot().setOnLongClickListener(v -> {
                showAlertDialog();
                return true;
            });
        }

        public void onBind(TaskModel title) {
            this.title = title;
            if (getAdapterPosition() % 2 != 0) {
                itemView.findViewById(R.id.colorLayout).setBackgroundColor(android.graphics.Color.LTGRAY);
            }

            binding.itemTitleTextView.setText(title.getTitle());
            binding.itemTimeTextView.setText(DateUtils.formatDateTime
                    (itemView.getContext(), list.get(getAdapterPosition()).getCreatedAt(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
        }

        private void showAlertDialog() {
            AlertDialog alertDialog = new AlertDialog.Builder(itemView.getContext()).setMessage("Вы хотите удалить?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        App.appDataBase.taskModelDao().delete(list.get(getAdapterPosition()).getId());
                        notifyItemRemoved(getAdapterPosition());})
                    .setNegativeButton("Нет", null)
                    .create();
            alertDialog.show();
        }

        @Override
        public void onClick(android.view.View v) {
            if (listener != null) {
                listener.onItemClick(getAdapterPosition(), title);
            }
        }
    }

    public void setOnClickListener(ItemClickListener listener) {
        this.listener = listener;
    }
}
