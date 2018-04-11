package SharedDataObjects;

import java.io.Serializable;

/**
* Used to send an email to all students in a certain course
* @author Evan Fenton
* */
public class CourseEmail implements Serializable{

    /**
     * id number for serialization
     */
    private static final long serialVersionUID = 87945569L;

    /**
     * Course that the email is for
     */
    private Course course;

    /**
     * Email object
     */
    private Email email;

    /**
     * Ctor
     * @param c
     * @param e
     */
    public CourseEmail(Course c, Email e){
        course= c;
        email= e;
    }

    /**
     * returns the email object
     * @return
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the course object
     * @return
     */
    public Course getCourse() {
        return course;
    }
}