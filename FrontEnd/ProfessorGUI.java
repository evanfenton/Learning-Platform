package FrontEnd;

import SharedDataObjects.*;

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
        
        ServerMessage loginReturn = client.communicate(new ServerMessage(null,
                                                       "login "+userID+" "+ password+ " "+ isProfessor));
        professor= (Professor)loginReturn.getObject();
        
        if(professor == null){
            return false;
        }
        else{
            return true;
        }
    }
    
}
