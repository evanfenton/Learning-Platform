package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import SharedDataObjects.*;


/**
 * Worker issued to each client connected to the server.
 * is able to read server messages from client and acts 
 * accordingly.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3rd, 2018
 *
 */
public class Worker implements Runnable {
	/**
	 * input stream of ServerMessage Objects
	 */
	private ObjectInputStream in;
	/**
	 * output stream of ServerMessage Objects
	 */
	private ObjectOutputStream out;
	/**
	 * the server file manager
	 */
	private FileHelper filemanager;
	/**
	 * the servers database
	 */
	private DatabaseHelper database;
	/**
	 * the servers email service
	 */
	private EmailHelper emailservice;
	/**
	 * Creates a worker by receiving the socket and accessing their IO streams as well
	 * as the servers database, file manager, and email service.
	 * @param socket for client server communication
	 * @param database of the server
	 * @param file manager of the server
	 * @param email service of the server
	 */
	public Worker(Socket socket, DatabaseHelper database, FileHelper file, EmailHelper email)
	{
		try
		{
			in =  new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			this.database = database;
			filemanager = file;
			emailservice = email;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * When running the worker will wait for a message from the client.
	 * It will then determine the object and message that the client sent
	 * and will act accordingly. 
	 */
	@Override
	public void run() {
		try 
		{
			while(true)
			{
				ServerMessage<?> message = (ServerMessage<?>) in.readObject();
				/**
				 * Adds a course
				 */
				if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Add"))
				{
					database.addCourse((Course) message.getObject());
					out.writeObject(null);
				}
				/**
				 * deletes a course
				 */
				if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Delete"))
				{
					database.deleteCourse((Course) message.getObject());
					out.writeObject(null);
				}
				/**
				 * code for logging onto the system.
				 */
				if(message.getObject().getClass().toString().contains("LoginInfo") && message.getMessage().equals("Login"))
				{
					LoginInfo info = (LoginInfo) message.getObject();
					User user = database.LoginUser(info);
					if(user.getLogininfo().getPassword().equals(info.getPassword()))
					{
						ServerMessage<User> returnmessage = new ServerMessage<User>(user, "");
						out.writeObject(returnmessage);
					}
					else
					{
						ServerMessage <User> returnmessage = new ServerMessage<User>(null,"");
						out.writeObject(returnmessage);
					}
				}
				/**
				 * Code that returns all courses that correspond with a specific profs ID
				 */
				if(message.getObject().getClass().toString().contains("Professor") && message.getMessage().equals("GetCourses"))
				{
					ArrayList<Course> list = database.getProfsCourses((Professor) message.getObject());
					ServerMessage<ArrayList<Course>> returnmessage = new ServerMessage<ArrayList<Course>>(list, "");
					out.writeObject(returnmessage);
					
				}
				/**
				 * Activates a course
				 */
				if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Activate"))
				{
					Course course = (Course) message.getObject();
					database.activateCourse(course);
					out.writeObject(null);
				}
				/**
				 * Deactivates a course
				 */
				if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Deactivate"))
				{
					Course course = (Course) message.getObject();
					database.deactivateCourse(course);
					out.writeObject(null);
				}
				/**
				 * Gets all students from DB and returns them
				 */
				if(message.getMessage().equals("GetAllStudents"))
				{
					ArrayList<Student> list = database.getAllStudents();
					ServerMessage<ArrayList<Student>> returnmessage = new ServerMessage<ArrayList<Student>>(list, "");
					out.writeObject(returnmessage);
				}
				/**
				 * checks enrollment of a student
				 */
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("CheckEnroll"))
				{
					StudentEnrollment enrolled = database.isEnrolled((StudentEnrollment) message.getObject());
					ServerMessage<StudentEnrollment> returnmessage = new ServerMessage<StudentEnrollment>(enrolled, "");
					out.writeObject(returnmessage);
				}
				/**
				 * enrolls a student to a class
				 */
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("Enroll"))
				{
					database.enroll((StudentEnrollment) message.getObject());
					out.writeObject(null);
				}
				/**
				 * unenrolls a student from a class
				 */
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("Unenroll"))
				{
					database.unenroll((StudentEnrollment) message.getObject());
					out.writeObject(null);
				}
				/**
				 * uploads a file
				 */
				if(message.getMessage().contains("FileUpload")){
					byte[] input = (byte[]) message.getObject();
					filemanager.uploadFile(input,message.getMessage());
					out.writeObject(null);
				}
				 /**
                 * Searches a course for a student by ID
                 */
                if(message.getObject().getClass().toString().contains("Course") && message.getMessage().contains("SearchID")){
                    
                    int searchID= Integer.parseInt(message.getMessage().split(" ")[1]);
                    Student student= database.searchCourseByID(searchID);
                    ServerMessage<Student> returnMessage= new ServerMessage<>(student, "");
                    out.writeObject(returnMessage);
                }
                /**
                 * Searches a course for a student by last name
                 */
                if(message.getObject().getClass().toString().contains("Course") && message.getMessage().contains("SearchName")){
                    Course course = (Course) message.getObject();
                    String searchName= message.getMessage().split(" ")[1];
                    ArrayList<Student> students= database.searchStudentsByLastName(searchName);
                    ServerMessage<ArrayList<Student>> returnMessage= new ServerMessage<>(students, "");
                    out.writeObject(returnMessage);
                }
                if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("GetCourseAssignments"))
                {
                	ArrayList<Assignment> list = database.getCourseAssignments((Course) message.getObject());
					ServerMessage<ArrayList<Assignment>> returnmessage = new ServerMessage<ArrayList<Assignment>>(list, "");
					out.writeObject(returnmessage);
                }
                /**
				 * Activates an Assignment
				 */
				if(message.getObject().getClass().toString().contains("Assignment") && message.getMessage().equals("Activate"))
				{
					Assignment assignment = (Assignment) message.getObject();
					database.activateAssignment(assignment);
					out.writeObject(null);
				}
				/**
				 * Deactivates an Assignment
				 */
				if(message.getObject().getClass().toString().contains("Assignment") && message.getMessage().equals("Deactivate"))
				{
					Assignment assignment = (Assignment) message.getObject();
					database.deactivateAssignment(assignment);
					out.writeObject(null);
				}
				/**
				 * deletes an assignment
				 */
				if(message.getObject().getClass().toString().contains("Assignment") && message.getMessage().equals("Delete"))
				{
					Assignment assignment = (Assignment) message.getObject();
					database.deleteAssignment(assignment);
					out.writeObject(null);
				}
				/**
				 * adds an assignment
				 */
				if(message.getObject().getClass().toString().contains("Assignment") && message.getMessage().equals("Add"))
				{
					Assignment assignment = (Assignment) message.getObject();
					database.addAssignment(assignment);
					out.writeObject(null);
				}
				/**
				 * Gets all courses a student is taking.
				 */
				if(message.getObject().getClass().toString().contains("Student") && message.getMessage().equals("GetCourses"))
				{
					ArrayList<Course> list = database.getStudentsCourses((Student) message.getObject());
					ServerMessage<ArrayList<Course>> returnmessage = new ServerMessage<ArrayList<Course>>(list, "");
					out.writeObject(returnmessage);
					
				}
				/**
				 * Gets all active course assignments.
				 */
				if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("GetActiveCourseAssignments"))
                {
                	ArrayList<Assignment> list = database.getActiveCourseAssignments((Course) message.getObject());
					ServerMessage<ArrayList<Assignment>> returnmessage = new ServerMessage<ArrayList<Assignment>>(list, "");
					out.writeObject(returnmessage);
                }
				if(message.getObject().getClass().toString().contains("Grade") && message.getMessage().equals("Add"))
				{
					Grade grade = (Grade) message.getObject();
					database.addGrade(grade);
					out.writeObject(null);
				}
                /**
                 * Send an email to all students in a course
                 */
                if(message.getObject().getClass().toString().contains("CourseEmail") && message.getMessage().equals("AllStudents"))
                {
                    CourseEmail courseEmail= (CourseEmail) message.getObject();
                    ArrayList<Student> list = database.getCourseStudents(courseEmail.getCourse());
                    
                    ArrayList<String> recipients= new ArrayList<>();
                    
                    for(int i=0; i< list.size(); i++){
                        recipients.add(list.get(i).getEmail());
                    }
                    
                    Email email= courseEmail.getEmail();
                    email.setTo(recipients);
                    emailservice= new EmailHelper(email.getFrom(),email.getFromPassword());
                    emailservice.sendEmail(email);
                    out.writeObject(null);
                }
				/**
				 * Send an email to professor of course
				 */
				if(message.getObject().getClass().toString().contains("CourseEmail") && message.getMessage().equals("ToProfessor"))
				{
					CourseEmail courseEmail= (CourseEmail) message.getObject();
					Professor prof = database.getCoursesProf(courseEmail.getCourse());

					ArrayList<String> recipients= new ArrayList<>();

					recipients.add(prof.getEmail());

					Email email= courseEmail.getEmail();
					email.setTo(recipients);
					emailservice= new EmailHelper(email.getFrom(),email.getFromPassword());
					emailservice.sendEmail(email);
					out.writeObject(null);
				}
                /**
                 * Sends an arraylist of grades that correspond to sent student and profID
                 */
                if(message.getObject().getClass().toString().contains("Student") && message.getMessage().contains("PGetStudentGrades"))
                {
                	String[] split = message.getMessage().split(" ");
                	int profid = Integer.parseInt(split[1]);
                	ArrayList<Grade> list = database.getStudentGrades((Student) message.getObject(), profid);
					ServerMessage<ArrayList<Grade>> returnmessage = new ServerMessage<ArrayList<Grade>>(list, "");
					out.writeObject(returnmessage);
                }
               /**
                * changes a specific grade.
                */
                if(message.getObject().getClass().toString().contains("Grade") && message.getMessage().contains("ChangeGrade"))
                {
                	String[] split = message.getMessage().split(" ");
                	int newgrade = Integer.parseInt(split[1]);
                	Grade grade = (Grade) message.getObject();
                	database.updateGrade(grade.getId(), newgrade);
                	out.writeObject(null);
                }
                /**
                 * gets all of a students grades.
                 */
                if(message.getObject().getClass().toString().contains("Student") && message.getMessage().equals("GetGrades"))
                {
                	ArrayList<Grade> list = database.getStudentGrades((Student) message.getObject());
                	ServerMessage<ArrayList<Grade>> returnmessage = new ServerMessage<ArrayList<Grade>>(list, "");
					out.writeObject(returnmessage);
                }
                /**
                 * gets prof for a certain assignment
                 * BUGGY. Some reason the tostring for an assign object in all
                 * tested instances is an EMAIL?
                 */
                if(message.getMessage().equals("GetProf"))
                {
                	Assignment assign = (Assignment) message.getObject();
                	Professor prof = database.getProf(assign);
                	ServerMessage<Professor> returnmessage = new ServerMessage<Professor>(prof,"");
                	out.writeObject(returnmessage);
                }
                
                if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("Professor")){

                	Course course= (Course) message.getObject();
                	String profEmail= database.getCoursesProf(course).getEmail();
                	ServerMessage<?> returnMessage= new ServerMessage<>(null, profEmail);
                	out.writeObject(returnMessage);
				}
                if(message.getObject().getClass().toString().contains("Course") && message.getMessage().equals("ProfessorName")){
                	Course course= (Course) message.getObject();
                	String name = database.getCoursesProf(course).getFirstname() + " " + database.getCoursesProf(course).getLastname();
                	ServerMessage<?> returnMessage= new ServerMessage<>(null, name);
                	out.writeObject(returnMessage);
                	
                }
				/**
				 * For student uploading a submission
				 */
				if(message.getMessage().contains("Submission")&& message.getMessage().contains("Submissionstr-1splitter"))
				{
					byte[] input = (byte[]) message.getObject();
					filemanager.uploadSubmission(input,message.getMessage());
					out.writeObject(null);
				}
				/**
				 * adds submission to database
				 */
				if(message.getObject().getClass().toString().contains("Submission") && message.getMessage().equals("Add"))
				{
					Submission sub = (Submission) message.getObject();
					database.addSubmission(sub);
					out.writeObject(null);
				}
				if(message.getObject().getClass().toString().contains("Assignment") && message.getMessage().equals("GetSubmissions"))
				{
					Assignment assign = (Assignment) message.getObject();
					ArrayList<Submission> list = database.getSubmissions(assign);
					ServerMessage<ArrayList<Submission>> returnMessage= new ServerMessage<>(list, "");
					out.writeObject(returnMessage);
				}
				if(message.getObject().getClass().toString().contains("Submission") && message.getMessage().equals("GetStudentName"))
				{
					Submission sub = (Submission) message.getObject();
					int stuid = sub.getStudent_id();
					ServerMessage<?> returnmessage = new ServerMessage<>(null, database.getStudent(stuid).getFirstname() + " " + database.getStudent(stuid).getLastname());
					out.writeObject(returnmessage);
				}
                
			}
		} 
				
			
		
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
