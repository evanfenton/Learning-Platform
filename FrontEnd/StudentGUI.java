package FrontEnd;

import FrontEnd.components.PageNavigator;

import FrontEnd.pages.StudentHome;
import SharedDataObjects.Professor;
import SharedDataObjects.Student;
import SharedDataObjects.User;
/**
 * The Class that gets launched when a professor logs onto the system.
 *
 */
public class StudentGUI extends PageNavigator {
        private Client client;
        private Student student;
        private boolean isProfessor;

        public StudentGUI(User stu, Client client){
            super();
            this.setClient(client);
            student = new Student(stu.getId(),stu.getFirstname(),stu.getLastname(),stu.getEmail(),stu.getType());
            isProfessor = false;
            addPage(new StudentHome(this));
            showPage();
        }


        public Student getStudent() {
            return student;
        }


        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }


}
