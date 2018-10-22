package com.xinchen.srs.database;

import java.sql.*;
import java.util.*;

import com.xinchen.srs.common.MajorEnum;
import com.xinchen.srs.common.*;

//DB access layer
public class DBAccess {
    //start table students query
	//Query: create new student entry
	public static void insertStudent(Student student) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
	    //int result = 0;
		//int id = 0;
		//Random idGenerator = new Random();
		String query = "INSERT INTO students "
                        + "(first_name, last_name, email, major, school_year) "
                        + "VALUES (?, ?, ?, ?, ?)";
		try {
			
			ps = connection.prepareStatement(query);
			//student.setId(idGenerator.nextInt(10000));
            //ps.setInt(1, student.getId());
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setString(4, student.getMajor().toString());
            ps.setInt(5, student.getSchoolYear());
            
		    ps.executeUpdate();
			//id = student.getId();
		} catch (SQLException e) {
			System.err.println(e);
			//result = 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
	}

	//Query: update student info
	public static void updateStudent(Student student) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
	    //int result = 0;
		
		String query = "UPDATE students "
                        + "SET first_name = ?, last_name = ?, email = ?, major = ?, school_year = ? "
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
	}

    //Query: get all students info
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

	//Query: remove student
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

	//Query: get student info
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

	//start table courses query
    //Query: create new course entry
	public static void insertCourse(Course course) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
		String query = "INSERT INTO courses "
				+ "(course_name, instructor, time, class_room) "
				+ "VALUES (?, ?, ?, ?)";
		try {

			ps = connection.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getInstructor());
			ps.setString(3, course.getTime());
			ps.setString(4, course.getClassRoom());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
	}

	//Query: update course info
	public static void updateCourse(Course course) {
		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
		//int result = 0;

		String query = "UPDATE courses "
				+ "SET course_name = ?, instructor = ?, time = ?, class_room = ? "
				+ "WHERE id = ?";
		try {

			ps = connection.prepareStatement(query);
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getInstructor());
            ps.setString(3, course.getTime());
            ps.setString(4, course.getClassRoom());
			ps.setInt(5, course.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
			//result = 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
	}

	//Query: get all courses info
	public static List<Course> getAllCourses() {

		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<Course>();
		String query = "SELECT * FROM courses";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setInstructor(rs.getString("instructor"));
                course.setTime(rs.getString("time"));
                course.setClassRoom(rs.getString("class_room"));
				courses.add(course);
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
		return courses;
	}

	//Query: remove course
	public static void removeCourse(int id) {

		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;

		String query = "DELETE FROM courses WHERE id = ?";

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

	//Query: get course info
	public static Course getCourse(int id) {

		Connection connection = DBConnectionUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Course course = null;

		String query = "SELECT * FROM courses WHERE id = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setCourseName(rs.getString("course_name"));
				course.setInstructor(rs.getString("instructor"));
				course.setTime(rs.getString("time"));
				course.setClassRoom(rs.getString("class_room"));
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBConnectionUtil.freeConnection(connection);
		}
		return course;
	}

    //start table records query
    //Query: create new record entry
    public static void insertRecord(Record record) {
        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO records "
                + "(student_id, course_id, score) "
                + "VALUES (?, ?, ?)";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getCourseId());
            ps.setInt(3, record.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBConnectionUtil.freeConnection(connection);
        }
    }

    //Query: update record info
    public static void updateRecord(Record record) {
        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement ps = null;
        //int result = 0;

        String query = "UPDATE records "
                + "SET student_id = ?, course_id = ?, score = ?"
                + "WHERE id = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setInt(1, record.getStudentId());
            ps.setInt(2, record.getCourseId());
            ps.setInt(3, record.getScore());
            ps.setInt(4, record.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            //result = 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBConnectionUtil.freeConnection(connection);
        }
    }

    //Query: get all records info
    public static List<FullRecord> getAllRecords(int course_id) {

        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<FullRecord> fullRecords = new ArrayList<FullRecord>();
        String query = "SELECT * FROM records "
                      + "INNER JOIN students ON records.student_id = students.id "
                      + "INNER JOIN courses ON records.course_id = courses.id AND courses.id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, course_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                FullRecord fullRecord = new FullRecord();
                fullRecord.record.setId(rs.getInt("id"));
                fullRecord.record.setStudentId(rs.getInt("student_id"));
                fullRecord.record.setCourseId(rs.getInt("course_id"));
                fullRecord.record.setScore(rs.getInt("score"));
                fullRecord.student.setId(rs.getInt("id"));
                fullRecord.student.setFirstName(rs.getString("first_name"));
                fullRecord.student.setLastName(rs.getString("last_name"));
                fullRecord.student.setEmail(rs.getString("email"));
                fullRecord.student.setMajor(MajorEnum.getTypeForName(rs.getString("major")));
                fullRecord.student.setSchoolYear(rs.getInt("school_year"));
                fullRecord.course.setId(rs.getInt("id"));
                fullRecord.course.setCourseName(rs.getString("course_name"));
                fullRecord.course.setInstructor(rs.getString("instructor"));
                fullRecord.course.setTime(rs.getString("time"));
                fullRecord.course.setClassRoom(rs.getString("class_room"));
                fullRecords.add(fullRecord);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBConnectionUtil.freeConnection(connection);
        }
        return fullRecords;
    }

    //Query: remove record
    public static void removeRecord(int id) {

        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM records WHERE id = ?";

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

    //Query: get record info
    public static Record getRecord(int id) {

        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Record record = null;

        String query = "SELECT * FROM records WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                record = new Record();
                record.setId(rs.getInt("id"));
                record.setStudentId(rs.getInt("student_id"));
                record.setCourseId(rs.getInt("course_id"));
                record.setScore(rs.getInt("score"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBConnectionUtil.freeConnection(connection);
        }
        return record;
    }
}
