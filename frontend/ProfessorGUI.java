package FrontEnd;

import SharedDataObjects.Professor;
import SharedDataObjects.User;
import FrontEnd.components.PageNavigator;
import FrontEnd.pages.ProfHome;
/**
 * The Class that gets launched when a professor logs onto the system.
 */
public class ProfessorGUI extends PageNavigator {

    /**
     *  Current user
     */
    private Professor professor;

    /**
     * boolean flag to tell the program this user has professor priviliges
     */
    private boolean isProfessor;

    /**
     * Ctor that gets called after the login is authenticated
     * @param prof
     * @param client
     */
    public ProfessorGUI(User prof, Client client){
        super(null, client);
        professor = new Professor(prof.getId(),prof.getFirstname(),prof.getLastname(),prof.getEmail(),prof.getLogininfo().getPassword());
        isProfessor = true;
        addPage(new ProfHome(this));
        showPage();
    }

    /**
     * getter for professor
     * @return
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * setter for professor
     * @param professor
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

}
