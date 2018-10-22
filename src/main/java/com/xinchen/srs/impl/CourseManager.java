package com.xinchen.srs.impl;

import com.xinchen.srs.common.Course;
import com.xinchen.srs.database.DBAccess;

import java.util.List;

public class CourseManager {
    public CourseManager() { }

    public Course processGetCourse(int id) {
        return DBAccess.getCourse(id);
    }

    public List<Course> processGetAllCourses() {
        return DBAccess.getAllCourses();
    }

    public void processPostCourse(Course course) { DBAccess.insertCourse(course); }

    public void processDeleteCourse(int id) {
        DBAccess.removeCourse(id);
    }

    public void processPutCourse(Course course) {
        DBAccess.updateCourse(course);
    }
}
