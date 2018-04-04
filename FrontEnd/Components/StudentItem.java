package Components;

import SharedDataObjects.Student;
import javax.swing.*;

public class StudentItem extends Box{
    
    private Student student;
    private boolean enrolled;
    private JButton enrollButton;
    
    public StudentItem{
        
    }
    
    public void changeEnrollStatus(){
        enrolled= !enrolled;
    }
    
    public void changeBoxColor(){
        
    }
}
