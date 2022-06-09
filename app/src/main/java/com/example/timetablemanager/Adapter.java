package com.example.timetablemanager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    Activity activity;
    List<Model> NotesList;

    public Adapter(Context context, Activity activity, List<Model> notesList) {
        this.context = context;
        this.activity = activity;
        NotesList = notesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,day;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            layout = itemView.findViewById(R.id.Note_layout);
        }
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(NotesList.get(position).getTitle());
        holder.description.setText(NotesList.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,Update_Task.class);
                intent.putExtra("id", NotesList.get(position).getId());
                intent.putExtra("title", NotesList.get(position).getTitle());
                intent.putExtra("description", NotesList.get(position).getDescription());
                intent.putExtra("day", NotesList.get(position).getDay());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NotesList.size();
    }

    public List<Model> getList() {
        return NotesList;
    }

    public void removeItem(int position) {
        NotesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Model item, int position) {
        NotesList.add(position, item);
        notifyItemInserted(position);
    }


}
