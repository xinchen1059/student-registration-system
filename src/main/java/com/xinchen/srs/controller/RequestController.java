package com.xinchen.srs.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.xinchen.srs.resource.StudentResource;
import com.xinchen.srs.impl.StudentRegistrationMgr;
import com.xinchen.srs.common.*;
import io.jsonwebtoken.*;

@RestController
@RequestMapping("/studentRegistrationSystem")
public class RequestController {
	private final StudentRegistrationMgr _studentRegMgr = new StudentRegistrationMgr();

	@GetMapping("/student/{id}")
	ResponseEntity<StudentResource> get(@PathVariable int id) {
        Student student = _studentRegMgr.processGet(id);
        if (student == null) {
        	return new ResponseEntity<StudentResource>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<StudentResource>(StudentRegistrationUtil.parseStudentRecord(student),HttpStatus.OK);
	}
	
	@RequestMapping("/getall")
	ResponseEntity<List<StudentResource>> getall() {
        List<Student> students = _studentRegMgr.processGetAll();
        return new ResponseEntity<List<StudentResource>>(StudentRegistrationUtil.parseAllStudentRecord(students),HttpStatus.OK);
	}
	
	@PostMapping("/student")
	ResponseEntity<Integer> post(@RequestBody StudentResource studentResource, @RequestHeader(value="Authorization") String token) {
		String authId = null;
		try {
			authId = Jwts.parser().setSigningKey("xinchen".getBytes("UTF-8")).
					parseClaimsJws(token).getBody().get("id").toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}

		if (authId.equals(StudentRegistrationUtil.AUTHENTICATION_ID)) {
			Student student = StudentRegistrationUtil.parseStudentResourceRecord(studentResource);
			int id = _studentRegMgr.processPost(student);
			return new ResponseEntity<Integer>(id, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
		}
	}


	@DeleteMapping("/student/{id}")
	ResponseEntity<Void> delete(@PathVariable int id) {
        _studentRegMgr.processDelete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/student/{id}")
	ResponseEntity<Void> put(@PathVariable int id, @RequestBody StudentResource studentResource) {
		Student student = StudentRegistrationUtil.parseStudentResourceRecord(studentResource);
		student.setId(id);
		_studentRegMgr.processPut(id, student);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
