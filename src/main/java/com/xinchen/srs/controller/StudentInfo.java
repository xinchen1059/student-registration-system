package com.xinchen.srs.controller;

import com.xinchen.srs.resource.StudentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.xinchen.srs.impl.StudentManager;
import com.xinchen.srs.common.*;
import io.jsonwebtoken.*;

//Rest API Layer
@RestController
@RequestMapping("/student")
public class StudentInfo {
	private final StudentManager _studentMgr = new StudentManager();

	//Read: get student info by id
	@GetMapping("/{id}")
	ResponseEntity<StudentEntity> get(@PathVariable int id) {
        Student student = _studentMgr.processGetStudent(id);
        if (student == null) {
        	return new ResponseEntity<StudentEntity>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<StudentEntity>(ParseUtil.parseStudent(student),HttpStatus.OK);
	}

	//Read: get all student info
	@RequestMapping("/getall")
	ResponseEntity<List<StudentEntity>> getall() {
        List<Student> students = _studentMgr.processGetAllStudents();
        return new ResponseEntity<List<StudentEntity>>(ParseUtil.parseAllStudents(students),HttpStatus.OK);
	}

	//Create: create new student entry with authentication token
	@PostMapping
	ResponseEntity<Void> post(@RequestBody StudentEntity studentEntity, @RequestHeader(value="Authorization") String token) {
		String authId = null;
		try {
			authId = Jwts.parser().setSigningKey("xinchen".getBytes("UTF-8")).
					parseClaimsJws(token).getBody().get("id").toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		if (authId.equals(ParseUtil.AUTHENTICATION_ID)) {
			Student student = ParseUtil.parseStudentEntity(studentEntity);
			_studentMgr.processPostStudent(student);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}

    //Delete student info by id
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(@PathVariable int id) {
        _studentMgr.processDeleteStudent(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	//Update student info by id
	@PutMapping("/{id}")
	ResponseEntity<Void> put(@PathVariable int id, @RequestBody StudentEntity studentEntity) {
		Student student = ParseUtil.parseStudentEntity(studentEntity);
		student.setId(id);
		_studentMgr.processPutStudent(student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
