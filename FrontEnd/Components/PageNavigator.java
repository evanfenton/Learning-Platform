package Components;

import javax.swing.*;

public class PageNavigator{
    
    private JPanel pageHolder;
    private CardLayout cardLayout;
    
    public PageNavigator(){
        pageHolder= new JPanel();
        cardLayout= new CardLayout();
        
        pageHolder.setLayout(cardLayout);
    }
    
    public void showPage(String page){
        
    }
    
    public void addPage(JPanel page, String name){
        
    }
    
    public void removePage(String name){
        
    }
    
    public JPanel searchPage(String name){
        
    }
}
