package com.example.entity;

import java.util.ArrayList;

public class Admin {
    @SuppressWarnings("unused")
    private String adminID;
    @SuppressWarnings("unused")
    private String password;

    public Admin(String adminID, String password) {
        this.adminID = adminID;
        this.password = password;
    }

    // Thêm sinh viên
    public void addStudent(ArrayList<Student> students, Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getName());
    }

    // Cập nhật sinh viên
    public void updateStudent(ArrayList<Student> students, String studentID, String newEmail, String newPhone) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                student = new Student(student.getStudentID(), student.getEmail(), student.getName(), newEmail,
                        newPhone);
                System.out.println("Student updated: " + student.getName());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Xóa sinh viên
    public void removeStudent(ArrayList<Student> students, String studentID) {
        students.removeIf(student -> student.getStudentID().equals(studentID));
        System.out.println("Student removed: " + studentID);
    }

    // Nhập điểm
    public void enterScore() {
        // Tạm thời chưa có chi tiết yêu cầu.
        System.out.println("Enter scores function.");
    }
}