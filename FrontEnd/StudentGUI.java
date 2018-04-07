package FrontEnd;

import FrontEnd.components.PageNavigator;
import FrontEnd.pages.StudHome;
import SharedDataObjects.Student;
import SharedDataObjects.User;

public class StudentGUI extends PageNavigator{

    private Student student;
    private boolean isProfessor;

    public StudentGUI(User stud, Client client){
        super(null, client);
        this.setClient(client);
        student = new Student(stud.getId(),stud.getFirstname(),stud.getLastname(),stud.getEmail(),stud.getType());
        isProfessor = false;
        addPage(new StudHome(this));
        showPage();
    }

    public void testPrint(){
        System.out.println("Printing from studGUI");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
