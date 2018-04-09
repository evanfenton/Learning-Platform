package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import FrontEnd.StudentGUI;
import FrontEnd.components.PageNavigator;

/**
 * Class that contains methods and fields for all pages.
 */
public class Page extends javax.swing.JFrame{

    protected boolean isProfessor;
    protected ProfessorGUI professorGUI;
    protected StudentGUI studentGUI;

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

    public Page(){
        //default ctor for inheritance for now
    }

    public PageNavigator getNavigator() {
        if(isProfessor) {
            return professorGUI;
        }
        else{
            return studentGUI;
        }
    }

}
