package FrontEnd;

import javax.swing.*;
import SharedDataObjects.LoginInfo;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author Evan Fenton
 */
public class LoginWindow extends JFrame{

	private static final long serialVersionUID = 1231231L;
	private JTextField userIDInput;
    private JPasswordField passwordInput;
    private JLabel idLabel, passwordLabel, messageLabel;
    private JButton loginButton, cancelButton;
    private JPanel field1, field2, buttons, message;
    private Client client;
    /**
     * Creates the login window with client connection.
     */
    public LoginWindow(){
        super();
        client = new Client("localhost", 9090);
        setLayout(new GridLayout(4,1));
        setTitle("User Login");
        setSize(430, 200);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initializeButtons();

        initializeLabels();

        initializeFields();

        initializePanels();

        fillPanels();

        add(message);
        add(field1);
        add(field2);
        add(buttons);

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void initializeLabels(){
        idLabel= new JLabel("User ID");
        passwordLabel= new JLabel("Password");
        messageLabel= new JLabel("Please login with a valid Gmail account");

        messageLabel.setFont(new Font(null, Font.BOLD, 14 ));

        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
        field1= new JPanel();
        field2= new JPanel();
        buttons= new JPanel();
        message= new JPanel();

        field1.setBackground(Color.orange);
        field2.setBackground(Color.orange);
        buttons.setBackground(Color.orange);
        message.setBackground(Color.pink);

        field1.setLayout(new GridLayout(1,2));
        field2.setLayout(new GridLayout(1,2));
        buttons.setLayout(new FlowLayout());

        field1.setBorder(BorderFactory.createMatteBorder(10,10,5,10, Color.orange));
        field2.setBorder(BorderFactory.createMatteBorder(7,10,8,10, Color.orange));
        message.setBorder(BorderFactory.createMatteBorder(0,0,5,0, UIManager.getColor("Panel.background")));
    }

    private void fillPanels(){
        field1.add(idLabel);
        field1.add(userIDInput);
        field2.add(passwordLabel);
        field2.add(passwordInput);

        buttons.add(loginButton);
        buttons.add(cancelButton);

        message.add(messageLabel);
    }

    // register the two button listeners
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
