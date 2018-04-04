package frontend;

import frontend.components.PageNavigator;
import frontend.pages.ProfHome;

public class ProfessorGUI extends PageNavigator {
    //private Client client;
    //private Professor professor;
    private boolean isProfessor;

    public ProfessorGUI(){
        super();
        addPage(new ProfHome(this));
        showPage("ProfHome");
    }

    public void testPrint(){
        System.out.println("Printing from profGUI");
    }
}
