package com.xinchen.srs.common;

import java.util.ArrayList;
import java.util.List;

import com.xinchen.srs.resource.StudentResource;

//StudentRegistrationUtil to translate between Students and StudentResource
public class StudentRegistrationUtil {
	public static String AUTHENTICATION_ID = "123456";
    public static StudentResource parseStudentRecord(Student student) {
    	StudentResource studentResource = new StudentResource();
    	studentResource.setEmail(student.getEmail());
    	studentResource.setFirstName(student.getFirstName());
    	studentResource.setLastName(student.getLastName());
    	studentResource.setMajor(student.getMajor().getName());
    	studentResource.setSchoolYear(String.valueOf(student.getSchoolYear()));
    	studentResource.setId(String.valueOf(student.getId()));
    	return studentResource;
    }
    
    public static List<StudentResource> parseAllStudentRecord(List<Student> students) {
    	List<StudentResource> studentResources = new ArrayList<StudentResource>();
    	for (Student student : students) {
    		StudentResource studentResource = new StudentResource();
			studentResource.setEmail(student.getEmail());
			studentResource.setFirstName(student.getFirstName());
			studentResource.setLastName(student.getLastName());
			studentResource.setMajor(student.getMajor().getName());
			studentResource.setSchoolYear(String.valueOf(student.getSchoolYear()));
			studentResource.setId(String.valueOf(student.getId()));
			studentResources.add(studentResource);
    	}
    	return studentResources;
    }
    
    public static Student parseStudentResourceRecord(StudentResource studentResource) {
    	Student student = new Student();
    	student.setEmail(studentResource.getEmail());
    	student.setFirstName(studentResource.getFirstName());
    	student.setLastName(studentResource.getLastName());
    	student.setMajor(MajorEnum.getTypeForName(studentResource.getMajor()));
    	student.setSchoolYear(Integer.parseInt(studentResource.getSchoolYear()));
    	return student;
    }
    
    
}
