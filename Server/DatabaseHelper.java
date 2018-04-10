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
	/**
	 * deletes a course from the database.
	 * @param course
	 */
	synchronized public void deleteCourse(Course course)
	{
		
		String sql = "DELETE FROM " + "CourseTable" + " WHERE ID=" + course.getId();
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Returns user that fits email from login info.
	 * @param info
	 * @return User
	 */
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
	/**
	 * Gets all courses that corrispond to a profs ID
	 * @param prof
	 * @return courses
	 */
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
	/**
	 * Activates specific course
	 * @param course
	 */
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
	/**
	 * deactivates specific course
	 * @param course
	 */
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
	/**
	 * gets all students attending the university
	 * @return all students
	 */
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
	/**
	 * checks to see if a student enrollment is in the table.
	 * @param Enrollment
	 * @return Enrollment
	 */
	synchronized public StudentEnrollment isEnrolled(StudentEnrollment x)
	{
		String sql = "SELECT * FROM " + "StudentEnrollmentTable" + " WHERE COURSE_ID=" +
					  x.getCourse_id() + " AND STUDENT_ID=" + x.getStudent_id() ;
		ResultSet enrollment;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			enrollment = statement.executeQuery(sql);
			if(enrollment.next())
			{
				return new StudentEnrollment(enrollment.getInt("ID"),
											 enrollment.getInt("STUDENT_ID"),
											 enrollment.getInt("COURSE_ID"),
											 true);
			}
		}
		catch(SQLException e) { e.printStackTrace(); }
		return null;
	}
	/**
	 * Enrolls student in a course.
	 * @param x
	 */
	synchronized public void enroll(StudentEnrollment x)
	{
		String sql = "INSERT INTO " + "StudentEnrollmentTable" +
				" VALUES ( " + x.getId() + ", '" + 
				x.getStudent_id() + "', '" + 
				x.getCourse_id() + "'); ";
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * unEnrolls student from course
	 * @param x
	 */
	synchronized public void unenroll(StudentEnrollment x)
	{
		String sql = "DELETE FROM " + "StudentEnrollmentTable" + " WHERE STUDENT_ID="
					+ x.getStudent_id() + " AND COURSE_ID=" + x.getCourse_id();
		try{
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Searches students with certain ID
	 * @param id
	 * @return student
	 */
	synchronized public Student searchCourseByID(int id)
    {
        ResultSet sqlResult;
        
        try{
            
            String sql = "SELECT * FROM " + "UserTable" + " WHERE ID= " + id+" AND TYPE= 'S'"+";";
            
            statement = jdbc_connection.prepareStatement(sql);
            sqlResult = statement.executeQuery(sql);
            
            if(sqlResult.next())
            {
                return new Student(sqlResult.getInt("ID"),
                                   sqlResult.getString("FIRSTNAME"),
                                   sqlResult.getString("LASTNAME"),
                                   sqlResult.getString("EMAIL"),
                                   sqlResult.getString("PASSWORD"));
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
    /**
     * search Students by last name
     * @param name
     * @return students
     */
    synchronized public ArrayList <Student> searchStudentsByLastName(String name)
    {
        ResultSet sqlResult;
        ArrayList<Student> students= new ArrayList<>();
        
        try{
            
            String sql = "SELECT * FROM " + "UserTable" + " WHERE LASTNAME= '" + name+"' AND TYPE= 'S'"+";";
            
            statement = jdbc_connection.prepareStatement(sql);
            sqlResult = statement.executeQuery(sql);
            
            while(sqlResult.next())
            {
                students.add(new Student(sqlResult.getInt("ID"),
                                         sqlResult.getString("FIRSTNAME"),
                                         sqlResult.getString("LASTNAME"),
                                         sqlResult.getString("EMAIL"),
                                         sqlResult.getString("PASSWORD")));
            }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return students;
    }
    /**
     * gets all assignments for specific course
     * @param course
     * @return all assignments
     */
	synchronized public ArrayList<Assignment> getCourseAssignments(Course course) {
		String sql = "SELECT * FROM " + "AssignmentTable" + " WHERE COURSE_ID=" + course.getId();
		ResultSet assignment;
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			assignment = statement.executeQuery(sql);
			while(assignment.next())
			{
				
				assignments.add(new Assignment(assignment.getInt("ID"),
								assignment.getInt("COURSE_ID"), 
								assignment.getString("TITLE"), 
								assignment.getString("PATH"),
								assignment.getBoolean("ACTIVE"),
								assignment.getString("DUE_DATE")
								));			
			}
		return assignments;
		} catch (SQLException e) { e.printStackTrace(); }
	
		return null;
	}
	/**
	 * activates specific assignment
	 * @param assignment
	 */
	synchronized public void activateAssignment(Assignment assignment)
	{
		
		String sql = "UPDATE " + "AssignmentTable" + " SET ACTIVE = b'1'"
				+ " WHERE ID = " + assignment.getId();

		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * deactivates specific assignment
	 * @param assignment
	 */
	synchronized public void deactivateAssignment(Assignment assignment)
	{
		
		String sql = "UPDATE " + "AssignmentTable" + " SET ACTIVE = b'0'"
				+ " WHERE ID = " + assignment.getId();

		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * deletes specific assignment
	 * @param assignment
	 */
	synchronized public void deleteAssignment(Assignment assignment) {
		String sql = "DELETE FROM " + "AssignmentTable" + " WHERE ID=" + assignment.getId();
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * adds specific assignment
	 * @param assignment
	 */
	synchronized public void addAssignment(Assignment assignment) {
		int bit;
		if(assignment.isActive())
		{
			bit = 1;
		}
		else
		{
			bit = 0;
		}
		String sql = "INSERT INTO " + "AssignmentTable" +
				" VALUES ( " + assignment.getId() + ", '" + 
				assignment.getCourse_id() + "', '" + 
				assignment.getTitle() + "', '" + 
				assignment.getPath() + "', b'" + 
				bit + "', '" + 
				assignment.getDue_date()
				+ "'); ";
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	synchronized public ArrayList<Course> getStudentsCourses(Student stud) {
		String sql = "SELECT * FROM " + "StudentEnrollmentTable" + " WHERE STUDENT_ID=" + stud.getId();
		ResultSet enrollments;
		ResultSet course;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			enrollments = statement.executeQuery(sql);
			while(enrollments.next())
			{
				sql = "SELECT * FROM " + "CourseTable" + " WHERE ID=" + enrollments.getInt("COURSE_ID") + " AND ACTIVE= b'1'";
				statement = jdbc_connection.prepareStatement(sql);
				course = statement.executeQuery(sql);
				if(course.next())
				{
				courses.add(new Course(course.getInt("ID"),
								course.getInt("PROF_ID"), 
								course.getString("NAME"), 
								course.getBoolean("ACTIVE") 
								));			
				}
			}
		return courses;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	synchronized public ArrayList<Assignment> getActiveCourseAssignments(Course course) {
		String sql = "SELECT * FROM " + "AssignmentTable" + " WHERE COURSE_ID=" + course.getId() + " AND ACTIVE= b'1'";
		ResultSet assignment;
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		try {
			statement = jdbc_connection.prepareStatement(sql);
			assignment = statement.executeQuery(sql);
			while(assignment.next())
			{
				
				assignments.add(new Assignment(assignment.getInt("ID"),
								assignment.getInt("COURSE_ID"), 
								assignment.getString("TITLE"), 
								assignment.getString("PATH"),
								assignment.getBoolean("ACTIVE"),
								assignment.getString("DUE_DATE")
								));			
			}
		return assignments;
		} catch (SQLException e) { e.printStackTrace(); }
	
		return null;
	}
	synchronized public void addGrade(Grade grade) {
		
		String sql = "INSERT INTO " + "GradeTable" +
				" VALUES ( " + grade.getId() + ", '" + 
				grade.getAssign_id()+ "', '" + 
				grade.getStudent_id() + "', '" + 
				grade.getCourse_id() + "', '" + 
				grade.getGrade() + "'); ";
		try{
			
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
    
    /**
     * gets all students enrolled in a course
     * @param course
     */
    synchronized public ArrayList<Student> getCourseStudents(Course course) {
        String sql = "SELECT * FROM StudentEnrollmentTable WHERE COURSE_ID= " + course.getId()+";";
        ResultSet students;
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            statement = jdbc_connection.prepareStatement(sql);
            students = statement.executeQuery(sql);
            
            while(students.next()) {
                studentList.add(searchCourseByID(students.getInt("STUDENT_ID")));
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return studentList;
    }
    
    synchronized private Student getStudent(int id){
        
        String sql= "SELECT * FROM UserTable WHERE ID= "+id;
        ResultSet student;
        
        try{
            statement= jdbc_connection.prepareStatement(sql);
            student= statement.executeQuery();
            
            return new Student(student.getInt("ID"),
                               student.getString("PASSWORD"),
                               student.getString("EMAIL"),
                               student.getString("FIRSTNAME"),
                               student.getString("LASTNAME"));
            
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        
        return null;
    }
    synchronized public ArrayList<Grade> getStudentGrades(Student student, int profid) {
		
	    ArrayList<Course> courses = getProfsCourses(new Professor(profid,"","","",""));
	    String sql = "SELECT * FROM " + "GradeTable" + " WHERE STUDENT_ID=" + student.getId();
        ResultSet grade;
        ArrayList<Grade> grades = new ArrayList<>();
        try {
            statement = jdbc_connection.prepareStatement(sql);
            grade = statement.executeQuery(sql);
            
            while(grade.next()) {
                grades.add(new Grade(grade.getInt("ID"),
						grade.getInt("ASSIGNMENT_GRADE"), 
						grade.getInt("STUDENT_ID"), 
						grade.getInt("ASSIGN_ID"),
						grade.getInt("COURSE_ID")
						));	
                
            }
            ArrayList<Grade> toReturn = new ArrayList<>();
            for(int i = 0; i < grades.size(); i++)
            {
            	for(int j =0; j < courses.size(); j++)
            	{
            		if(grades.get(i).getCourse_id() == courses.get(j).getId()) {
            			Assignment assigns = getAssignment(grades.get(i).getAssign_id());
            			grades.get(i).setAssign_name(assigns.getTitle());
            			toReturn.add(grades.get(i));
            		}
            	}
            }
            return toReturn;
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;	}
	synchronized public Assignment getAssignment(int id) {
		String sql = "SELECT * FROM " + "AssignmentTable" + " WHERE ID=" + id;
		ResultSet assignment;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			assignment = statement.executeQuery(sql);
			if(assignment.next())
			{
				return new Assignment(assignment.getInt("ID"),
                        assignment.getInt("COURSE_ID"),
                        assignment.getString("TITLE"),
                        assignment.getString("PATH"),
                        assignment.getString("DUE_DATE"));
					
			}
		return null;
		} catch (SQLException e) { e.printStackTrace(); }
	
		return null;
	}
	/**
	 * updates grade with id to new grade
	 * @param id
	 * @param grade
	 */
	synchronized public void updateGrade(int id, int grade)
	{
		String sql = "UPDATE " + "GradeTable" + " SET ASSIGNMENT_GRADE = '" +
			 	grade + "' WHERE ID = " + id;
		try {
				statement = jdbc_connection.prepareStatement(sql);
						statement.executeUpdate(sql);
	
			}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
	}
	
}
