package frontend;

import SharedDataObjects.Professor;
import SharedDataObjects.User;
import frontend.components.PageNavigator;
import frontend.pages.ProfHome;

public class ProfessorGUI extends PageNavigator {
    private Client client;
    private Professor professor;
    private boolean isProfessor;

    public ProfessorGUI(User prof){
        super();
        professor = new Professor(prof.getId(),prof.getFirstname(),prof.getLastname(),prof.getEmail(),prof.getType());
        isProfessor = true;
        addPage(new ProfHome(this));
        showPage();
    }

    public void testPrint(){
        System.out.println("Printing from profGUI");
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
