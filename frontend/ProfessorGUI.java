package FrontEnd;

import SharedDataObjects.Professor;
import SharedDataObjects.User;
import FrontEnd.components.PageNavigator;
import FrontEnd.pages.ProfHome;
/**
 * The Class that gets launched when a professor logs onto the system.
 *
 */
public class ProfessorGUI extends PageNavigator {
    private Client client;
    private Professor professor;
    private boolean isProfessor;

    public ProfessorGUI(User prof, Client client){
        super();
        this.setClient(client);
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
