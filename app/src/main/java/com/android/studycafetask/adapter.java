package com.android.studycafetask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class adapter extends FirestoreRecyclerAdapter <Task, adapter.TaskViewHolder>{
    Context context;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    public adapter(@NonNull FirestoreRecyclerOptions<Task> options, Context context) {
        super(options);
        this.context = context;
        firestore= FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onBindViewHolder(@NonNull TaskViewHolder holder, int position, @NonNull Task task) {
        holder.titleText.setText(task.title);
        holder.detailText.setText(task.detail);
        holder.completionText.setText(task.completionDate);
        holder.crateText.setText(utility.timestampToString(task.timestamp));
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleriew_task,parent,false);
       return new TaskViewHolder(view);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView titleText, detailText, completionText, crateText;


        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.title_text_view);
            detailText = itemView.findViewById(R.id.detail_text_view);
            completionText = itemView.findViewById(R.id.completion_date);
            crateText = itemView.findViewById(R.id.create_date);

        }
    }
}
