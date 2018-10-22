package com.xinchen.srs.common;

public class Record {

    private int _id = 0;
    private int _studentId = 0;
    private int _courseId = 0;
    private int _score = 0;

    public Record() {}

    public Record(int id, int studentId, int courseId, int score){
        _id = id;
        _studentId = studentId;
        _courseId = courseId;
        _score = score;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public int getStudentId() {
        return _studentId;
    }

    public void setStudentId(int studentId) {
        _studentId = studentId;
    }

    public int getCourseId() { return _courseId; }

    public void setCourseId(int courseId) {
        _courseId = courseId;
    }

    public int getScore() { return _score; }

    public void setScore(int score) {
        _score= score;
    }

}
