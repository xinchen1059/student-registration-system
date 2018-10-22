package com.xinchen.srs.controller;

import com.xinchen.srs.resource.CourseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.List;
import com.xinchen.srs.impl.CourseManager;
import com.xinchen.srs.common.*;
import io.jsonwebtoken.*;

//Rest API Layer
@RestController
@RequestMapping("/course")
public class CourseInfo {
    private final CourseManager _courseManager = new CourseManager();

    //Read: get course info by id
    @GetMapping("/{id}")
    ResponseEntity<CourseEntity> get(@PathVariable int id) {
        Course course = _courseManager.processGetCourse(id);
        if (course == null) {
            return new ResponseEntity<CourseEntity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CourseEntity>(ParseUtil.parseCourse(course),HttpStatus.OK);
    }

    //Read: get all course info
    @RequestMapping("/getall")
    ResponseEntity<List<CourseEntity>> getall() {
        List<Course> courses = _courseManager.processGetAllCourses();
        return new ResponseEntity<List<CourseEntity>>(ParseUtil.parseAllCourses(courses),HttpStatus.OK);
    }

    //Create: create new course entry with authentication token
    @PostMapping
    ResponseEntity<Void> post(@RequestBody CourseEntity courseEntity, @RequestHeader(value="Authorization") String token) {
        String authId = null;
        try {
            authId = Jwts.parser().setSigningKey("xinchen".getBytes("UTF-8")).
                    parseClaimsJws(token).getBody().get("id").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        if (authId.equals(ParseUtil.AUTHENTICATION_ID)) {
            Course course = ParseUtil.parseCourseEntity(courseEntity);
            _courseManager.processPostCourse(course);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    //Delete course info by id
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        _courseManager.processDeleteCourse(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //Update course info by id
    @PutMapping("/{id}")
    ResponseEntity<Void> put(@PathVariable int id, @RequestBody CourseEntity courseEntity) {
        Course course = ParseUtil.parseCourseEntity(courseEntity);
        course.setId(id);
        _courseManager.processPutCourse(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
