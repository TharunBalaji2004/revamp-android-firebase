package com.example.android_firebase_series;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentId = student.id;
        holder.studentName.setText(student.getName());
        holder.studentCollege.setText(student.getCollege());
        holder.studentAge.setText(String.valueOf(student.getAge()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, studentCollege, studentAge;
        String studentId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.tv_name);
            studentCollege = itemView.findViewById(R.id.tv_college);
            studentAge = itemView.findViewById(R.id.tv_age);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), DataScreen.class);

                    intent.putExtra("id", studentId);
                    intent.putExtra("name", studentName.getText());
                    intent.putExtra("college", studentCollege.getText());
                    intent.putExtra("age", studentAge.getText());

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
