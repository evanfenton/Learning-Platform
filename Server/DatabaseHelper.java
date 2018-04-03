package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import SharedDataObjects.Course;
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
		String sql = "INSERT INTO " + "CourseTable" +
				" VALUES ( " + course.getId() + ", '" + 
				course.getProf_id() + "', '" + 
				course.getName() + "', '" + 
				course.isActive() + "'); ";
		
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
	
	
}
