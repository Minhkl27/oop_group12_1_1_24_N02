package com.example.entity;

import java.util.ArrayList;

public class Registration {
    private String studentID;
    private String subjectID;

    public Registration(String studentID, String subjectID) {
        this.studentID = studentID;
        this.subjectID = subjectID;
    }

    // Đăng ký môn học
    public static void registerSubject(ArrayList<Registration> registrations, String studentID, String subjectID) {
        registrations.add(new Registration(studentID, subjectID));
        System.out.println("Subject registered for student: " + studentID);
    }

    // Xóa môn học đã đăng ký
    public static void removeSubject(ArrayList<Registration> registrations, String studentID, String subjectID) {
        registrations.removeIf(
                registration -> registration.studentID.equals(studentID) && registration.subjectID.equals(subjectID));
        System.out.println("Subject removed for student: " + studentID);
    }

    public String getStudentID() {
        return studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }
}
