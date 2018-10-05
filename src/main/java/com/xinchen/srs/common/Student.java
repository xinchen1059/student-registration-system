package com.xinchen.srs.common;

public class Student {
    
	private int _id = 0;
    private String _firstName = "";
    private String _lastName = "";
    private String _email = "";
    private MajorEnum _major = MajorEnum.UNKNOWN;
    private int _schoolYear = 0;
    
    public Student() {}
    
    public Student(int id, String firstName, String lastName, String email, String major, String schoolYear){
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _major = MajorEnum.getTypeForName(major);
        _schoolYear = Integer.parseInt(schoolYear);
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

    public MajorEnum getMajor() {
        return _major;
    }

    public void setMajor(MajorEnum major) {
        _major = major;
    }

    public int getSchoolYear() {
        return _schoolYear;
    }

    public void setSchoolYear(int year) {
        _schoolYear = year;
    }

	public int getId() {
		return _id;
	}

	public void setId(int id) {
	    _id = id;
	}
}
