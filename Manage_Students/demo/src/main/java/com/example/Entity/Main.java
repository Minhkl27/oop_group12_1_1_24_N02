package com.example.Entity;

import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
                // Tạo danh sách sinh viên, môn học, đăng ký
                ArrayList<Student> students = new ArrayList<>();
                ArrayList<Subject> subjects = new ArrayList<>();
                ArrayList<Registration> registrations = new ArrayList<>();

                // Tạo đối tượng Admin
                Admin admin = new Admin("coLeThu", "123456a@");

                // Thêm sinh viên
                Student student1 = new Student("22010502", "HieuVN", "Vu Ngoc Hieu", "22010502@st.phenikaa-uni.edu.vn",
                                "0327332445");
                Student student2 = new Student("22010058", "VocToc", "Le Thi Voc", "22010058@st.phenikaa-uni.edu.vn",
                                "0886314896");
                Student student3 = new Student("22010049", "Minh123", "Nguyen Van Minh",
                                "22010049@st.phenikaa-uni.edu.vn",
                                "0923919921");
                admin.addStudent(students, student1);
                admin.addStudent(students, student2);
                admin.addStudent(students, student3);
                // Danh sách sinh viên
                System.out.println("List of students:");
                Student.listStudents(students);

                // Thêm môn học
                Subject subject1 = new Subject("sub01", "Lap trinh huong doi tuong", 3);
                Subject subject2 = new Subject("sub02", "Ky thuat phan mem", 4);
                Subject subject3 = new Subject("sub03", "Co so du lieu", 2);
                Subject subject4 = new Subject("sub04", "Lich su Dang", 4);
                subjects.add(subject1);
                subjects.add(subject2);
                subjects.add(subject3);
                subjects.add(subject4);
                // Danh sách môn học
                System.out.println("\nList of subjects:");
                Subject.listSubjects(subjects);

                // Đăng ký môn học
                Registration.registerSubject(registrations, "s01", "sub01");

                // Xóa môn học
                Registration.removeSubject(registrations, "s01", "sub01");
        }
}