package com.xinchen.srs.resource;

public class StudentResource {
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _major;
    private String _schoolYear;
    private String _id;
    
    public String getId() {
    	return _id; 
    }
    
    public void setId(String id) {
    	_id = id;
    }
    
    public String getFirstName() {
    	return _firstName; 
    }
    
    public void setFirstName(String firstName) {
    	_firstName = firstName;
    }
    
    public String getLastName() {
    	return _lastName; 
    }
    
    public void setLastName(String lastName) {
    	_lastName = lastName;
    }
    
    public String getEmail() {
    	return _email; 
    }
    
    public void setEmail(String email) {
    	_email = email;
    }
    
    public String getMajor() {
    	return _major; 
    }
    
    public void setMajor(String major) {
    	_major = major;
    }
    
    public String getSchoolYear() {
    	return _schoolYear; 
    }
    
    public void setSchoolYear(String schoolYear) {
    	_schoolYear = schoolYear;
    }
}
