package com.example.prac04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<item_student.Student> studentList;
    private Context context;

    public StudentAdapter(List<item_student.Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        item_student.Student student = studentList.get(position);
        holder.tvStudentName.setText(student.firstName + " " + student.middleName + " " + student.lastName);
        holder.tvStudentId.setText(student.id);
        holder.tvStudentGPA.setText("GPA: " + student.gpa);

        // Hiển thị icon giới tính
        if (student.gender.equals("Nu")) {
            holder.imgGender.setImageResource(R.drawable.female);
        } else {
            holder.imgGender.setImageResource(R.drawable.male);
        }

        // Sự kiện click để hiển thị chi tiết sinh viên
        holder.itemView.setOnClickListener(v -> {
            // Intent để mở activity chi tiết sinh viên
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("student", student); // Chuyển đối tượng sinh viên
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvStudentId, tvStudentGPA;
        ImageView imgGender;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tv_student_name);
            tvStudentId = itemView.findViewById(R.id.tv_student_id);
            tvStudentGPA = itemView.findViewById(R.id.tv_student_gpa);
            imgGender = itemView.findViewById(R.id.img_gender);
        }
    }
}
