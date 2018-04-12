package FrontEnd;

import FrontEnd.components.PageNavigator;
import FrontEnd.pages.StudentHome;
import SharedDataObjects.Student;
import SharedDataObjects.User;

/**
 *
 */
public class StudentGUI extends PageNavigator {

    /**
     * Current user
     */
    private Student student;

    /**
     * boolean flag to differentiate user
     */
    private boolean isProfessor;

    /**
     * Ctor called after the login window authenticates user
     * @param stud
     * @param client
     */
    public StudentGUI(User stud, Client client) {

        super(null, client);
        student = new Student(stud.getId(), stud.getFirstname(), stud.getLastname(), stud.getEmail(), stud.getLogininfo().getPassword());
        isProfessor = false;
        addPage(new StudentHome(this));
        showPage();
    }

    /**
     * Getter for student
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     * setter for student
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }
}
