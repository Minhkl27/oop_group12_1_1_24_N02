package com.example.Entity;

import java.util.ArrayList;

public class Subject {
    private String subjectID;
    private String title;
    private int credits;

    public Subject(String subjectID, String title, int credits) {
        this.subjectID = subjectID;
        this.title = title;
        this.credits = credits;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public static void listSubjects(ArrayList<Subject> subjects) {
        for (Subject subject : subjects) {
            System.out.println("Subject ID: " + subject.getSubjectID() + " Title: " + subject.getTitle() +
                    " Credits: " + subject.getCredits());
        }
    }
}