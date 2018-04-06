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
				if(message.getObject().getClass().toString().contains("Student") && message.getMessage().equals("GetAllStudents"))
				{
					ArrayList<Student> list = database.getAllStudents();
					ServerMessage<ArrayList<Student>> returnmessage = new ServerMessage<ArrayList<Student>>(list, "");
					out.writeObject(returnmessage);
				}
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("CheckEnroll"))
				{
					StudentEnrollment enrolled = database.isEnrolled((StudentEnrollment) message.getObject());
					ServerMessage<StudentEnrollment> returnmessage = new ServerMessage<StudentEnrollment>(enrolled, "");
					out.writeObject(returnmessage);
				}
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("Enroll"))
				{
					database.enroll((StudentEnrollment) message.getObject());
					out.writeObject(null);
				}
				if(message.getObject().getClass().toString().contains("StudentEnrollment") && message.getMessage().equals("Unenroll"))
				{
					database.unenroll((StudentEnrollment) message.getObject());
					out.writeObject(null);
				}
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
				
			}
		} 
				
			
		
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		}
		
	}

}
