package FrontEnd;

import javax.swing.*;

import SharedDataObjects.LoginInfo;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * @author Evan Fenton
 */
public class LoginWindow extends JFrame{

	private static final long serialVersionUID = 1231231L;
	private JTextField userIDInput;
    private JPasswordField passwordInput;
    private JLabel idLabel, passwordLabel;
    private JButton loginButton, cancelButton;
    private JPanel fields, buttons;
    private Client client;
    /**
     * Creates the login window with client connection.
     */
    public LoginWindow(){
        super();
        client = new Client("localhost", 9091);
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

        setLocationRelativeTo(null);

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
    /**
     * Creates a LoginInfo object based on the text are inputs.
     * Then puts that into a server message that tells the server to login.
     * Then receives the user back as either an actual object or null.
     * Opens the correct gui or tells the user the login info was incorrect.
     */
    private void login()
    {
        String password = String.valueOf(passwordInput.getPassword());
    	LoginInfo info = new LoginInfo(userIDInput.getText(), password);
    	ServerMessage<LoginInfo> message = new ServerMessage<LoginInfo>(info, "Login");
    	ServerMessage<?> recieve = client.communicate(message);
    	User user = (User) recieve.getObject();

    	if(user == null)
    	{
    		JOptionPane.showMessageDialog(this,"Incorrect Login Info");
    	}
    	else if(user.getType().equals("P"))
    	{
    		//Create the prof GUI
            ProfessorGUI profgui = new ProfessorGUI(user, client);
    		System.out.println("Prof GUI Created");
    		setVisible(false);
    	} else if(user.getType().equals("S")){
    	    StudentGUI stugui = new StudentGUI(user,client);
    	    setVisible(false);
        }
    	else
    	{
    	    //Create the student GUI
    	    StudentGUI studgui= new StudentGUI(user, client);
            System.out.println("Stud GUI Created");
            setVisible(false);
    	}
    }
    /**
     * Clears all text in the loginwindow
     */
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
