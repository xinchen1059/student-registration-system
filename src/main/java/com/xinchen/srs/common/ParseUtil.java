package com.xinchen.srs.common;

import java.util.ArrayList;
import java.util.List;

import com.xinchen.srs.resource.*;

//ParseUtil to translate between Students and StudentEntity
public class ParseUtil {

	public static String AUTHENTICATION_ID = "123456";

    public static StudentEntity parseStudent(Student student) {
    	StudentEntity studentEntity = new StudentEntity();
    	studentEntity.setEmail(student.getEmail());
    	studentEntity.setFirstName(student.getFirstName());
    	studentEntity.setLastName(student.getLastName());
    	studentEntity.setMajor(student.getMajor().getName());
    	studentEntity.setSchoolYear(String.valueOf(student.getSchoolYear()));
    	studentEntity.setId(String.valueOf(student.getId()));
    	return studentEntity;
    }
    
    public static List<StudentEntity> parseAllStudents(List<Student> students) {
    	List<StudentEntity> studentEntities = new ArrayList<StudentEntity>();
    	for (Student student : students) {
    		StudentEntity studentEntity = new StudentEntity();
			studentEntity.setEmail(student.getEmail());
			studentEntity.setFirstName(student.getFirstName());
			studentEntity.setLastName(student.getLastName());
			studentEntity.setMajor(student.getMajor().getName());
			studentEntity.setSchoolYear(String.valueOf(student.getSchoolYear()));
			studentEntity.setId(String.valueOf(student.getId()));
			studentEntities.add(studentEntity);
    	}
    	return studentEntities;
    }
    
    public static Student parseStudentEntity(StudentEntity studentEntity) {
    	Student student = new Student();
    	student.setEmail(studentEntity.getEmail());
    	student.setFirstName(studentEntity.getFirstName());
    	student.setLastName(studentEntity.getLastName());
    	student.setMajor(MajorEnum.getTypeForName(studentEntity.getMajor()));
    	student.setSchoolYear(Integer.parseInt(studentEntity.getSchoolYear()));
    	return student;
    }

	public static CourseEntity parseCourse(Course course) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setId(String.valueOf(course.getId()));
        courseEntity.setCourseName(course.getCourseName());
        courseEntity.setInstructor(course.getInstructor());
        courseEntity.setTime(course.getTime());
        courseEntity.setClassRoom(course.getClassRoom());
		return courseEntity;
	}

	public static List<CourseEntity> parseAllCourses(List<Course> courses) {
		List<CourseEntity> courseEntities = new ArrayList<CourseEntity>();
		for (Course course : courses) {
			CourseEntity courseEntity = new CourseEntity();
            courseEntity.setId(String.valueOf(course.getId()));
            courseEntity.setCourseName(course.getCourseName());
            courseEntity.setInstructor(course.getInstructor());
            courseEntity.setTime(course.getTime());
            courseEntity.setClassRoom(course.getClassRoom());
			courseEntities.add(courseEntity);
		}
		return courseEntities;
	}

	public static Course parseCourseEntity(CourseEntity courseEntity) {
		Course course = new Course();
        course.setCourseName(courseEntity.getCourseName());
        course.setInstructor(courseEntity.getInstructor());
        course.setTime(courseEntity.getTime());
        course.setClassRoom(courseEntity.getClassRoom());
		return course;
	}
    
	public static RecordEntity parseRecord(Record record) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setId(String.valueOf(record.getId()));
        recordEntity.setStudentId(String.valueOf(record.getStudentId()));
        recordEntity.setCourseId(String.valueOf(record.getCourseId()));
        recordEntity.setScore(String.valueOf(record.getScore()));
        return recordEntity;
    }

    public static List<RecordEntity> parseAllRecords(List<Record> records) {
        List<RecordEntity> recordEntities = new ArrayList<RecordEntity>();
        for (Record record : records) {
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setId(String.valueOf(record.getId()));
            recordEntity.setStudentId(String.valueOf(record.getStudentId()));
            recordEntity.setCourseId(String.valueOf(record.getCourseId()));
            recordEntity.setScore(String.valueOf(record.getScore()));
            recordEntities.add(recordEntity);
        }
        return recordEntities;
    }

    public static Record parseRecordEntity(RecordEntity recordEntity) {
        Record record = new Record();
        record.setStudentId(Integer.parseInt(recordEntity.getStudentId()));
        record.setCourseId(Integer.parseInt(recordEntity.getCourseId()));
        record.setScore(Integer.parseInt(recordEntity.getScore()));
        return record;
    }

}
