package com.example.entity;

import java.util.ArrayList;

public class Student {
    private String studentID;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public Student(String studentID, String password, String name, String email, String phoneNumber) {
        this.studentID = studentID;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStudentID() {
        return studentID;
    }

    public static void listStudents(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("ID: " + student.getStudentID() + " Name: " + student.getName() +
                    " Email: " + student.getEmail() + " Phone: " + student.getPhoneNumber());
        }
    }
}