package com.xinchen.srs.resource;

public class RecordEntity {

    private String _id = "";
    private String _studentId = "";
    private String _courseId = "";
    private String _score = "";

    public RecordEntity() {}

    public RecordEntity(String id, String studentId, String courseId, String score){
        _id = id;
        _studentId = studentId;
        _courseId = courseId;
        _score = score;
    }

    public String getId() { return _id; }

    public void setId(String id) { _id = id; }

    public String getStudentId() { return _studentId; }

    public void setStudentId(String studentId) { _studentId = studentId; }

    public String getCourseId() { return _courseId; }

    public void setCourseId(String courseId) { _courseId = courseId; }

    public String getScore() { return _score; }

    public void setScore(String score) {
        _score = score;
    }

}
