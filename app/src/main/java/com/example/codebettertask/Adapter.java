package com.example.codebettertask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
LayoutInflater inflater;
List<patientdata> patientdata;
public Adapter(Context ctx, List<patientdata> patientdata)
{
    this.inflater = LayoutInflater.from(ctx);
    this.patientdata = patientdata;
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.customlayout,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.id.setText("Id : "+patientdata.get(position).getId());
        holder.age.setText("Age : "+patientdata.get(position).getAge());
        holder.name.setText("Name : "+patientdata.get(position).getName());
        holder.gender.setText("Gender : "+patientdata.get(position).getGender());

    }

    @Override
    public int getItemCount() {
        return patientdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView id, age, name, gender;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            age = itemView.findViewById(R.id.age);
            name = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}
