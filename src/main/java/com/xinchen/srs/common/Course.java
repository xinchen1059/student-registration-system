package com.xinchen.srs.common;

public class Course {

    private int _id = 0;
    private String _courseName = "";
    private String _instructor = "";
    private String _time = "";
    private String _classRoom = "";

    public Course() {}

    public Course(int id, String courseName, String instructor, String time, String classRoom ){
        _id = id;
        _courseName = courseName;
        _instructor = instructor;
        _time = time;
        _classRoom = classRoom;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getCourseName() {
        return _courseName;
    }

    public void setCourseName(String courseName) {
        _courseName = courseName;
    }

    public String getInstructor() { return _instructor; }

    public void setInstructor(String instructor) {
        _instructor = instructor;
    }

    public String getTime() { return _time; }

    public void setTime(String time) {
        _time = time;
    }

    public String getClassRoom() { return _classRoom; }

    public void setClassRoom(String classRoom) {
        _classRoom = classRoom;
    }
}
