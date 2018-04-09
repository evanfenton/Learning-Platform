package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import SharedDataObjects.Course;
import SharedDataObjects.Email;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* @author Evan Fenton
* */
public class ProfEmail extends Page{

    private JTextField subject;
    private JTextArea content;
    private JButton send, cancel;
    private JPanel buttons, text;

    public ProfEmail(ProfessorGUI profGUI, Course course){

        super(profGUI, true);
        setLayout(new GridLayout(3,1));
        setSize(200,200);

        initComponents();

        addComponents();

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ServerMessage<Course> getStudents= new ServerMessage<>(course, "Students");
                ArrayList<Student> students= (ArrayList<Student>) profGUI.getClient().communicate(getStudents).getObject();
                ArrayList<String> recipients= new ArrayList<>();

                for(int i=0; i< students.size(); i++){
                    recipients.add(students.get(i).getEmail());
                }

                Email email= new Email(profGUI.getProfessor().getEmail(), recipients, subject.getText(), content.getText());
                ServerMessage<Email> emailMessage= new ServerMessage<>(email, "Professor");
                profGUI.getClient().communicate(emailMessage);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

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