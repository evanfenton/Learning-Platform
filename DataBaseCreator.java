import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 
 * Class that can be used to initialize the default database for the Learning Platform
 * so everyone in the group has a uniform database.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since March 31st, 2018
 *
 */
public class DataBaseCreator {

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
	private String databaseName = "LearningPlatform";
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
	public DataBaseCreator()
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
	 * Creates a new database with the name of the variable databaseName
	 */
	public void createDB()
	{
		try {
			statement = jdbc_connection.prepareStatement("CREATE DATABASE " + databaseName);
			statement.executeUpdate("CREATE DATABASE " + databaseName);
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Creates the UserTable of the database
	 */
	public void createUserTable()
	{
		String sql = "CREATE TABLE " + "UserTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PASSWORD VARCHAR(20) NOT NULL, " + 
				     "EMAIL VARCHAR(50) NOT NULL, " + 
				     "FIRSTNAME VARCHAR(30) NOT NULL," + 
				     "LASTNAME VARCHAR(30) NOT NULL, " + 
				     "TYPE CHAR(1) NOT NULL, "  +
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "UserTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Creates the CourseTable of the database
	 */
	public void createCourseTable()
	{
		String sql = "CREATE TABLE " + "CourseTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "PROF_ID INT(8) NOT NULL, " + 
				     "NAME VARCHAR(50) NOT NULL, " + 
				     "ACTIVE BIT(1) NOT NULL," + 
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "UserCourseTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Creates the StudentEnrollmentTable for the database
	 */
	public void createStudentEnrollmentTable()
	{
		String sql = "CREATE TABLE " + "StudentEnrollmentTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "COURSE_ID INT(8) NOT NULL, " + 
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "EnrollmentTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * Creates the AssignmentTable for the database
	 */
	public void createAssignmentTable()
	{
		String sql = "CREATE TABLE " + "AssignmentTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "COURSE_ID INT(8) NOT NULL, " + 
				     "TITLE VARCHAR(50) NOT NULL, " + 
				     "PATH VARCHAR(100) NOT NULL," + 
				     "ACTIVE BIT(1) NOT NULL, " + 
				     "DUE_DATE CHAR(16) NOT NULL, "  +
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "AssignmentTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * creates the SubmissionTable for the database
	 */
	public void createSubmissionTable()
	{
		String sql = "CREATE TABLE " + "SubmissionTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "ASSIGN_ID INT(8) NOT NULL, " + 
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "PATH VARCHAR(100) NOT NULL," + 
				     "TITLE VARCHAR(50) NOT NULL, " + 
				     "SUBMISSION_GRADE INT(3) NOT NULL, "  +
				     "COMMENTS VARCHAR(140) NOT NULL, "  +
				     "TIMESTAMP CHAR(16) NOT NULL, "  +
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "SubmissionTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * creates the GradeTable for the database
	 */
	public void createGradeTable()
	{
		String sql = "CREATE TABLE " + "GradeTable" + "(" +
				     "ID INT(8) NOT NULL, " +
				     "ASSIGN_ID INT(8) NOT NULL, " + 
				     "STUDENT_ID INT(8) NOT NULL, " + 
				     "COURSE_ID INT(8) NOT NULL," + 
				     "ASSIGNMENT_GRADE INT(3) NOT NULL," +
				     "PRIMARY KEY ( id ))";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			System.out.println("Created Table " + "GradeTable");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * To create the database delete "LearningPlatform" in connectionInfo,
	 * then uncomment creator.createDB(); to have the database created.
	 * then add "LearningPlatform" back into connectionInfo and comment createDB()
	 * uncomment the table creators and run.
	 */
	public static void main(String[] args) {
		//DataBaseCreator creator = new DataBaseCreator();
		//creator.createDB();
		
		//creator.createUserTable();
		//creator.createCourseTable();
		//creator.createStudentEnrollmentTable();
		//creator.createAssignmentTable();
		//creator.createSubmissionTable();
		//creator.createGradeTable();
	}

}
