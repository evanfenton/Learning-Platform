
public class ProfessorGUI{

    private Client client;
    private Professor professor;
    private boolean isProfessor;
    
    ProfessorGUI(){
        client= new Client();
        professor= null;
        isProfessor= true;
    }
    
    public boolean login(String userID, String password){
        
        professor= client.verifyLogin(userID, password, isProfessor);
        
        if(professor == null){
            return false;
        }
        else{
            return true;
        }
    }
    
}
