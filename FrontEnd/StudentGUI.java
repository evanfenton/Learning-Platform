
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
        
        student = client.verifyLogin(userID, password, isProfessor);
        
        if(student == null){
            return false;
        }
        else{
            return true;
        }
    }
    
}
