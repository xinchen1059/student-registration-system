package com.xinchen.srs.controller;
import com.xinchen.srs.resource.RecordEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.List;
import com.xinchen.srs.impl.RecordManager;
import com.xinchen.srs.common.*;
import io.jsonwebtoken.*;

//Rest API Layer
@RestController
@RequestMapping("/registration")
public class Registration {

    private final RecordManager _recordManager = new RecordManager();

    //Read: get record info by id
    @GetMapping("/{id}")
    ResponseEntity<RecordEntity> get(@PathVariable int id) {
        Record record = _recordManager.processGetRecord(id);
        if (record == null) {
            return new ResponseEntity<RecordEntity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RecordEntity>(ParseUtil.parseRecord(record),HttpStatus.OK);
    }

    //Read: get all record info
    @RequestMapping("/getstudents/{course_id}")
    ResponseEntity<List<FullRecord>> getall(@PathVariable int course_id) {
        List<FullRecord> fullRecords = _recordManager.processGetAllRecords(course_id);
        return new ResponseEntity<List<FullRecord>>(fullRecords, HttpStatus.OK);
    }

    //Update record info by id, authorization needed
    @PutMapping("/{id}")
    ResponseEntity<Void> put(@PathVariable int id, @RequestBody RecordEntity recordEntity, @RequestHeader(value="Authorization") String token) {
        String authId = null;
        try {
            authId = Jwts.parser().setSigningKey("xinchen".getBytes("UTF-8")).
                    parseClaimsJws(token).getBody().get("id").toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }

        if (authId.equals(ParseUtil.AUTHENTICATION_ID)) {
            Record record = ParseUtil.parseRecordEntity(recordEntity);
            record.setId(id);
            _recordManager.processPutRecord(record);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    //Delete record info by id
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable int id) {
        _recordManager.processDeleteRecord(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    //Create: create new record entry with authentication token
    @PostMapping
    ResponseEntity<Void> post(@RequestBody RecordEntity recordEntity) {
        Record record = ParseUtil.parseRecordEntity(recordEntity);
        record.setScore(0);
        _recordManager.processPostRecord(record);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
}
