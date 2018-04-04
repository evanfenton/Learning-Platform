package FrontEnd;

import SharedDataObjects.*;

public class StudentGUI{

    private Client client;
    private Student student;
    private boolean isProfessor;
    
    ProfessorGUI(){
        client= new Client();
        student= null;
        isProfessor= false;
    }
    
    public boolean login(String userID, String password){
        
        ServerMessage loginReturn = client.communicate(new ServerMessage(null,
                                                       "login "+userID+" "+ password+ " "+ isProfessor));
        student= (Student)loginReturn.getObject();
        if(student == null){
            return false;
        }
        else{
            return true;
        }
    }
    
}
