package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import FrontEnd.StudentGUI;

/**
	 * Class that contains methods and fields for all pages.
	 *
	 */
public class Page extends javax.swing.JFrame{
   protected boolean isProfesor;
    protected ProfessorGUI professor;
    protected StudentGUI student;

    public Page(ProfessorGUI prof){
        professor = prof;
        isProfesor = true;
        student = null;
    }

    public Page(StudentGUI stu){
        student = stu;
        professor = null;
        isProfesor = false;
    }

    public Page(){
        //default ctor for inheritance for now
    }

    public ProfessorGUI getProfessor() {
        return professor;
    }

    public StudentGUI getStudent() {
        return student;
    }
}
