package com.example.entity;

public class Score {
    private String studentID;
    private String subjectID;
    private float score;

    public Score(String studentID, String subjectID, float score) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.score = score;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
