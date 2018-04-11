package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import FrontEnd.StudentGUI;
import FrontEnd.components.PageNavigator;

/**
 * Class that contains methods and fields for all pages.
 */
public class Page extends javax.swing.JFrame{

    /**
     * boolean flag to keep track of user privileges
     */
    protected boolean isProfessor;

    /**
     * professor user that is accessing pages
     */
    protected ProfessorGUI professorGUI;

    /**
     * Student user that is accessing pages
     */
    protected StudentGUI studentGUI;

    /**
     * Ctor, used by the pages in the program to get access to PageNavigator and Client
     * @param user  Current user
     * @param isProf    boolean flag for if the user has prof or student privileges
     */
    public Page(PageNavigator user, boolean isProf){
        isProfessor= isProf;
        if(isProfessor) {
            professorGUI = (ProfessorGUI) user;
            studentGUI= null;
        }
        else{
            studentGUI= (StudentGUI) user;
            professorGUI= null;
        }

    }

    /**
     * Default Ctor
     */
    public Page(){
        isProfessor = false;
        professorGUI = null;
        studentGUI = null;
    }

    /**
     * Returns the current user page navigator
     * @return
     */
    public PageNavigator getNavigator() {
        if(isProfessor) {
            return professorGUI;
        }
        else{
            return studentGUI;
        }
    }

}
