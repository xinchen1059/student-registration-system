package com.xinchen.srs.impl;

import java.util.List;

import com.xinchen.srs.common.Student;
import com.xinchen.srs.database.DBAccess;

public class StudentRegistrationMgr {
    
	public StudentRegistrationMgr() {
	}
	
	public Student processGet(int id) {
		return DBAccess.getStudent(id);
	}
	
	public List<Student> processGetAll() {
		return DBAccess.getAllStudents();
	}
	
	public int processPost(Student student) {
		int id = DBAccess.insertStudent(student);
		return id;
	}
	
	public void processDelete(int id) {
		DBAccess.removeStudent(id);
	}
	
	public void processPut(Student student) {
		DBAccess.updateStudent(student);
	}
}
