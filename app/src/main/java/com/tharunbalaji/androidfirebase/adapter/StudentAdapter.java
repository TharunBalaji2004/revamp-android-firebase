package com.tharunbalaji.androidfirebase.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.tharunbalaji.androidfirebase.R;
import com.tharunbalaji.androidfirebase.model.Student;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public void setStudentList(List<Student> studentList){
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
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentName.setText(student.getStudentName());
        holder.studentAge.setText((int) student.getAge());
        holder.collegeName.setText(student.getCollegeName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView studentName, studentAge, collegeName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.tvStudentName);
            studentAge = itemView.findViewById(R.id.tvStudentAge);
            collegeName = itemView.findViewById(R.id.tvCollegeName);
        }
    }
}
