package com.xinchen.srs.impl;

import java.util.List;
import com.xinchen.srs.common.Record;
import com.xinchen.srs.common.FullRecord;
import com.xinchen.srs.database.DBAccess;

public class RecordManager {
    
    public RecordManager() { }

    public Record processGetRecord(int id) {
        return DBAccess.getRecord(id);
    }

    public List<FullRecord> processGetAllRecords(int course_id) { return DBAccess.getAllRecords(course_id); }

    public void processPostRecord(Record record) { DBAccess.insertRecord(record); }

    public void processDeleteRecord(int id) {
        DBAccess.removeRecord(id);
    }

    public void processPutRecord(Record record) {
        DBAccess.updateRecord(record);
    }
}
