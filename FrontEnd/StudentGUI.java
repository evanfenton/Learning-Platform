package FrontEnd;

import FrontEnd.components.PageNavigator;
import FrontEnd.pages.StudentHome;
import SharedDataObjects.Student;
import SharedDataObjects.User;

public class StudentGUI extends PageNavigator {

    private Student student;
    private boolean isProfessor;

    public StudentGUI(User stud, Client client) {

        super(null, client);
        student = new Student(stud.getId(), stud.getFirstname(), stud.getLastname(), stud.getEmail(), stud.getLogininfo().getPassword());
        isProfessor = false;
        addPage(new StudentHome(this));
        showPage();
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
