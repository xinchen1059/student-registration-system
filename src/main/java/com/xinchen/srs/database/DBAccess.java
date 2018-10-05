package com.xinchen.srs.database;

import java.sql.*;
import java.util.*;

import com.xinchen.srs.common.MajorEnum;
import com.xinchen.srs.common.Student;

public class DBAccess {
	
	public static int insertStudent(Student student) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
	    //int result = 0;
		int id = 0;
		Random idGenerator = new Random();
		String query = "INSERT INTO students "
                        + "(id, first_name, last_name, email, major, school_year) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			
			ps = connection.prepareStatement(query);
			student.setId(idGenerator.nextInt(10000));
            ps.setInt(1, student.getId());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.setString(4, student.getEmail());
			ps.setString(5, student.getMajor().toString());
            ps.setInt(6, student.getSchoolYear());
            
		    ps.executeUpdate();
			id = student.getId();
		} catch (SQLException e) {
			System.err.println(e);
			//result = 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
		return id;
	}
	
	public static int updateStudent(int id, Student student) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
	    //int result = 0;
		
		String query = "UPDATE students "
                        + "SET first_name, last_name, email, major, school_year" 
				        + "VALUES (?, ?, ?, ?, ?)"
                        + "WHERE id = ?";
		try {
			
			ps = connection.prepareStatement(query);

			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getMajor().toString());
            ps.setInt(5, student.getSchoolYear());
            ps.setInt(6, student.getId());
		    ps.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println(e);
			//result = 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
		return id;
	}

	public static List<Student> getAllStudents() {
		
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<Student>();
		String query = "SELECT * FROM students";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();				
				student.setId(rs.getInt("id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setMajor(MajorEnum.getTypeForName(rs.getString("major")));
                student.setSchoolYear(rs.getInt("school_year"));
				students.add(student);
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
		return students;
	}
	
	public static void removeStudent(int id) {
		
		Connection connection = DBConnectionUtil.getConnection();
	    PreparedStatement ps = null;
	    
	    String query = "DELETE FROM students WHERE id = ?";

	    try {
	      ps = connection.prepareStatement(query);
	      ps.setInt(1, id);
	      ps.executeUpdate();
	    } catch (SQLException e) {
	      System.err.println(e);
	    } finally {
	      DBUtil.closePreparedStatement(ps);
	      DBConnectionUtil.freeConnection(connection);
	    }
	}
	
	public static Student getStudent(int id) {
		
		Connection connection = DBConnectionUtil.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    Student student = null;

	    String query = "SELECT * FROM students WHERE id = ?";
	    try {
	    	ps = connection.prepareStatement(query);
	    	ps.setInt(1, id);
	    	rs = ps.executeQuery();
	      
	    	if (rs.next()) {
	    		student = new Student();
	    	  
		    	student.setId(rs.getInt("id"));
		    	student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setMajor(MajorEnum.getTypeForName(rs.getString("major")));
                student.setSchoolYear(rs.getInt("school_year"));
	    	}
	    } catch (SQLException e) {
	      System.err.println(e);
	    } finally {
	      DBUtil.closeResultSet(rs);
	      DBUtil.closePreparedStatement(ps);
	      DBConnectionUtil.freeConnection(connection);
	    }
	    return student;
	}
}
