package frontend.pages;

import frontend.ProfessorGUI;

public class Page extends javax.swing.JFrame{
   protected boolean isProfesor;
   //itemList?
    //itemDisplay?
    protected ProfessorGUI professor;
    //StudentGui
    public Page(ProfessorGUI prof){
        professor = prof;
        isProfesor = true;
        System.out.println("Page has ProfessorGUI in it, constructed");
    }

    public Page(){
        //default ctor for inheritance for now
    }

    public ProfessorGUI getProfessor() {
        return professor;
    }
}
