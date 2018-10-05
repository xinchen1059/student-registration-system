package com.xinchen.srs.common;

import com.xinchen.srs.resource.StudentResource;

import org.junit.Assert;
import org.junit.Test;


public class TestStudentRegistrationUtil {

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
        StudentResource studentResource = StudentRegistrationUtil.parseStudentRecord(student);
        Assert.assertEquals(studentResource.getId(),String.valueOf(student.getId()));
        Assert.assertEquals(studentResource.getFirstName(),student.getFirstName());
        Assert.assertEquals(studentResource.getLastName(),student.getLastName());
        Assert.assertEquals(studentResource.getEmail(),student.getEmail());
        Assert.assertEquals(studentResource.getMajor(),student.getMajor().getName());
    }
}
