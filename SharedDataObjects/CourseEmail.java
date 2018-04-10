package SharedDataObjects;

import java.io.Serializable;

/*
* Used to send an email to all students in a certain course
* @author Evan Fenton
* */
public class CourseEmail implements Serializable{

    //id number for serialization
    private static final long serialVersionUID = 87945569L;

    private Course course;
    private Email email;

    //constructor
    public CourseEmail(Course c, Email e){
        course= c;
        email= e;
    }

    //returns the email
    public Email getEmail() {
        return email;
    }

    //returns the course
    public Course getCourse() {
        return course;
    }
}