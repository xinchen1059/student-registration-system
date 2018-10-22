package com.xinchen.srs.resource;

public class CourseEntity {

    private String _id = "";
    private String _courseName = "";
    private String _instructor = "";
    private String _time = "";
    private String _classRoom = "";

    public CourseEntity() {}

    public CourseEntity(String id, String courseName, String instructor, String time, String classRoom ){
        _id = id;
        _courseName = courseName;
        _instructor = instructor;
        _time = time;
        _classRoom = classRoom;
    }

    public String getId() { return _id; }

    public void setId(String id) { _id = id; }

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
