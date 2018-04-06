package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
	/**
	 * Class that contains methods and fields for all pages.
	 *
	 */
public class Page extends javax.swing.JFrame{
   protected boolean isProfesor;
   //itemList?
    //itemDisplay?
    protected ProfessorGUI professor;
    //StudentGui
    public Page(ProfessorGUI prof){
        professor = prof;
        isProfesor = true;
    }

    public Page(){
        //default ctor for inheritance for now
    }

    public ProfessorGUI getProfessor() {
        return professor;
    }
}
