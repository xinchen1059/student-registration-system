package com.xinchen.srs.common;

import com.xinchen.srs.resource.StudentEntity;

import org.junit.Assert;
import org.junit.Test;


public class TestParseUtil {

    @Test
    public void testParseStudentRecord()
    {
        Student student = null;
        student.setId(1234);
        student.setFirstName("Kate");
        student.setLastName("Chen");
        student.setEmail("KC@gmail.com");
        student.setMajor(MajorEnum.getTypeForName("EE"));
        student.setSchoolYear(1);
        StudentEntity studentEntity = ParseUtil.parseStudent(student);
        Assert.assertEquals(studentEntity.getId(),String.valueOf(student.getId()));
        Assert.assertEquals(studentEntity.getFirstName(),student.getFirstName());
        Assert.assertEquals(studentEntity.getLastName(),student.getLastName());
        Assert.assertEquals(studentEntity.getEmail(),student.getEmail());
        Assert.assertEquals(studentEntity.getMajor(),student.getMajor().getName());
    }
}
