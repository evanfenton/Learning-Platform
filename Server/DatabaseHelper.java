package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import SharedDataObjects.*;
/**
 * helper for the servers database. Has functions to update delete and add
 * objects from multiple tables.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3rd, 2018
 *
 */
public class DatabaseHelper {
	/**
	 * connection to the jdbc
	 */
	private Connection jdbc_connection;
	/**
	 * prepared statement that can be manipulated to send updates to the table.
	 */
	private PreparedStatement statement;
	/**
	 * Strings that can be change that correspond to the database name and the tablename.
	 * As well as a file name that you can use to help add clients to the server.
	 */
	//private String databaseName = "LearningPlatform";
	/**
	 * connection and login info for the database.
	 */
	private String 
		connectionInfo = "jdbc:mysql://localhost:3306/LearningPlatform"  ,
			  login          = "root",
			  password       = "password";
	
	/**
	 * Gets the connection to the jdbc
	 */
	public DatabaseHelper()
	{
		try 
		{
			
			Class.forName("com.mysql.jdbc.Driver");
						
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	/**
	 * Adds a course to the CourseTable in the database
	 * @param course
	 */
	synchronized public void addCourse(Course course)
	{
		int bit;
		if(course.isActive())
		{
			bit = 1;
		}
		else
		{
			bit = 0;
		}
		String sql = "INSERT INTO " + "CourseTable" +
				" VALUES ( " + course.getId() + ", '" + 
				course.getProf_id() + "', '" + 
				course.getName() + "', b'" + 
				bit + "'); ";
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			System.out.println("Attempting to add course...");
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	synchronized public void deleteCourse(Course course)
	{
		
		String sql = "DELETE FROM " + "CourseTable" + " WHERE ID=" + course.getId();
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			System.out.println("Attempting to add course...");
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	synchronized public User LoginUser(LoginInfo info)
	{
		String sql = "SELECT * FROM " + "UserTable" + " WHERE EMAIL= '" + info.getUsername() + "'";
		ResultSet user; 
		try 
		{
			statement = jdbc_connection.prepareStatement(sql);
			System.out.println("Checking Credentials");
			user = statement.executeQuery(sql);
			if(user.next())
			{
				return new User(user.getInt("ID"),
								user.getString("FIRSTNAME"), 
								user.getString("LASTNAME"), 
								user.getString("EMAIL"), 
								user.getString("PASSWORD"),
								user.getString("TYPE")
								);			
			}
		}
		catch(SQLException e)
		{
			return null;
		}
		return null;
	}
	synchronized public ArrayList<Course> getProfsCourses(Professor prof) {
		String sql = "SELECT * FROM " + "CourseTable" + " WHERE PROF_ID=" + prof.getId();
		ResultSet course;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			course = statement.executeQuery(sql);
			while(course.next())
			{
				
				courses.add(new Course(course.getInt("ID"),
								course.getInt("PROF_ID"), 
								course.getString("NAME"), 
								course.getBoolean("ACTIVE") 
								));			
			}
		return courses;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	synchronized public void activateCourse(Course course)
	{
		
		String sql = "UPDATE " + "CourseTable" + " SET ACTIVE = b'1'"
				+ " WHERE ID = " + course.getId();

		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	synchronized public void deactivateCourse(Course course)
	{
		
		String sql = "UPDATE " + "CourseTable" + " SET ACTIVE = b'0'"
				+ " WHERE ID = " + course.getId();

		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	synchronized public ArrayList<Student> getAllStudents() {
		String sql = "SELECT * FROM " + "UserTable" + " WHERE TYPE=" + "'S'";
		ResultSet student;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			student = statement.executeQuery(sql);
			while(student.next())
			{
				
				students.add(new Student(student.getInt("ID"),
								student.getString("FIRSTNAME"), 
								student.getString("LASTNAME"), 
								student.getString("EMAIL"),
								student.getString("PASSWORD")
								));			
			}
		return students;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	
	
}
