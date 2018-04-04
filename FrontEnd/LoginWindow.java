import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame{

    private JTextField userIDInput;
    private JPasswordField passwordInput;
    private JLabel idLabel, passwordLabel;
    private JButton loginButton, cancelButton;
    private JPanel fields, buttons;

    public LoginWindow(){
        super();
        setLayout(new GridLayout(2,1));
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initializeButtons();

        initializeLabels();

        initializeFields();

        initializePanels();

        fillPanels();

        add(fields);
        add(buttons);

        setVisible(true);
    }

    private void initializeLabels(){
        idLabel= new JLabel("User ID");
        passwordLabel= new JLabel("Password");

        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initializeButtons(){
        loginButton= new JButton("Login");
        cancelButton= new JButton("Cancel");
    }

    private void initializeFields(){
        userIDInput= new JTextField();
        passwordInput= new JPasswordField();
    }

    private void initializePanels(){
        fields= new JPanel();
        buttons= new JPanel();

        fields.setLayout(new GridLayout(2,2));
        buttons.setLayout(new FlowLayout());
    }

    private void fillPanels(){
        fields.add(idLabel);
        fields.add(userIDInput);
        fields.add(passwordLabel);
        fields.add(passwordInput);

        buttons.add(loginButton);
        buttons.add(cancelButton);
    }

    private void registerListeners(ActionListener loginListener, ActionListener cancelListener){
        loginButton.addActionListener(loginListener);
        cancelButton.addActionListener(cancelListener);
    }

    private void login(){
        ProfessorGUI professorGUI= new ProfessorGUI();
        StudentGUI studentGUI= new StudentGUI();
        
        if(professorGUI.login(userIDInput.getText(), passwordInput.getText())){
            //start professor gui
            this.setVisible(false);
            this.dispose();
        }
        else if(studentGUI.login(userIDInput.getText(), passwordInput.getText())){
            //start student gui
            this.setVisible(false);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Incorrect user ID or password", "Login Error", JOptionPane.ERROR_MESSAGE);
            clearLoginWindow();
        }
    }
    
    private void clearLoginWindow(){
        userIDInput.setText("");
        passwordInput.setText("");
    }

    public static void main(String [] args){
        LoginWindow loginWindow= new LoginWindow();

        loginWindow.registerListeners(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LOGIN BUTTON
                loginWindow.login();
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CANCEL BUTTON
                System.exit(0);
            }
        });
    }
}
