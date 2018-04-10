package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import SharedDataObjects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* Basic email page used for testing email functionality
* Should be switched out for a better version!
* Also, there doesn't need to be separate prof and student
* email gui's since there's just a subject and content
*
* @author Evan Fenton
* */
public class ProfEmail extends Page{

    //components
    private JTextField subject;
    private JTextArea content;
    private JButton send, cancel;
    private JPanel buttons, text;

    //constructor with action listeners
    public ProfEmail(ProfessorGUI profGUI, Course course){

        super(profGUI, true);
        setLayout(new GridLayout(3,1));
        setSize(200,200);

        initComponents();

        addComponents();

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Email email= new Email(profGUI.getProfessor().getEmail(),
                        profGUI.getProfessor().getLogininfo().getPassword(),
                        null, subject.getText(), content.getText());

                System.out.println(profGUI.getProfessor().getLogininfo().getPassword());

                CourseEmail courseEmail= new CourseEmail(course, email);

                ServerMessage<CourseEmail> emailMessage= new ServerMessage<>(courseEmail, "AllStudents");
                profGUI.getClient().communicate(emailMessage);

                setVisible(false);
                dispose();
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

    }

    //initialize components
    private void initComponents(){
        subject= new JTextField();

        content= new JTextArea();
        content.setWrapStyleWord(true);

        send= new JButton("Send");
        cancel= new JButton("Cancel");

        buttons= new JPanel();
        buttons.setLayout(new FlowLayout());

        text= new JPanel();
        text.setLayout(new GridLayout(1,2));
    }

    //add the components to their respective panels and
    //finally, the panels to the frame
    private void addComponents(){
        text.add(new JLabel("Subject"));
        text.add(subject);

        buttons.add(send);
        buttons.add(cancel);

        add(text);
        add(content);
        add(buttons);
    }

}