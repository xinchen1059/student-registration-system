package com.xinchen.srs.impl;

import java.util.List;

import com.xinchen.srs.common.Student;
import com.xinchen.srs.database.DBAccess;

//Business Logic Layer
public class StudentManager {
    
	public StudentManager() { }
	
	public Student processGetStudent(int id) {
		return DBAccess.getStudent(id);
	}
	
	public List<Student> processGetAllStudents() {
		return DBAccess.getAllStudents();
	}
	
	public void processPostStudent(Student student) { DBAccess.insertStudent(student); }
	
	public void processDeleteStudent(int id) {
		DBAccess.removeStudent(id);
	}
	
	public void processPutStudent(Student student) {
		DBAccess.updateStudent(student);
	}

}
