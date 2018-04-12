package FrontEnd.pages;

import SharedDataObjects.Assignment;
import SharedDataObjects.Course;
import SharedDataObjects.Grade;
import FrontEnd.ProfessorGUI;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;
import SharedDataObjects.StudentEnrollment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Evan Mcphee
 */
public class UploadAssignment extends Page {

    /**
     * Creates new frame UploadAssignment
     */
    public UploadAssignment(ProfessorGUI prof, Course course) {
        super(prof, true);
        initComponents();
        userLabel.setText("User:  " + prof.getProfessor().getFirstname() + " " + prof.getProfessor().getLastname());
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UploadAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UploadAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UploadAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UploadAssignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /**
         * Cancel button Closes the frame and reopens the ProfCourseAssignments frame
         */
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new ProfCourseAssignments(professorGUI,course));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * Creates a new assignment and sends the selected file to the server and an object of type assignment with the due date stored with it
         * also sets the grade for all students in that course to 0 for the current assignment effectively creating a grade object for it.
         */
        createB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkDate()){
                    File filetosend = fileChooser.getSelectedFile();

                    if(filetosend != null) {
                        String fileinfo = filetosend.getName();
                        //Start of code to send file, provided by ENSF409 instructor
                        long length = filetosend.length();
                        byte[] content = new byte[(int) length]; // Converting Long to Int
                        try {
                            FileInputStream fis = new FileInputStream(filetosend);
                            BufferedInputStream bos = new BufferedInputStream(fis);
                            bos.read(content, 0, (int) length);
                            @SuppressWarnings("unchecked")
                            ServerMessage message = new ServerMessage(content, "FileUploadstr-1splitter".concat(fileinfo));
                            getNavigator().getClient().communicate(message);

                        } catch (FileNotFoundException g) {
                            g.printStackTrace();
                        } catch (IOException f) {
                            f.printStackTrace();
                        }
                        String[] filesplit = fileinfo.split("\\.(?=[^\\.]+$)");
                        Random rand = new Random();
                        Assignment assignment = new Assignment(rand.nextInt(99999999) + 1, course.getId(), filesplit[0], "." + filesplit[1], dueDateInput.getText());
                        ServerMessage<Assignment> message = new ServerMessage<Assignment>(assignment, "Add");
                        professorGUI.getClient().communicate(message);
                        ServerMessage<Student> mes = new ServerMessage<Student>(new Student(), "GetAllStudents");
                        ServerMessage<?> returned = professorGUI.getClient().communicate(mes);
                        @SuppressWarnings("unchecked")
                        ArrayList<Student> students = (ArrayList<Student>) returned.getObject();
                        for (int i = 0; i < students.size(); i++) {
                            StudentEnrollment enrollment = new StudentEnrollment(1, students.get(i).getId(), course.getId(), true);
                            ServerMessage<StudentEnrollment> message2 = new ServerMessage<StudentEnrollment>(enrollment, "CheckEnroll");
                            ServerMessage<?> response = professorGUI.getClient().communicate(message2);
                            enrollment = (StudentEnrollment) response.getObject();
                            if (response.getObject() != null) {
                                Grade grade = new Grade(rand.nextInt(99999999) + 1, 0, students.get(i).getId(), assignment.getId(), course.getId(), assignment.getTitle());
                                ServerMessage<Grade> message3 = new ServerMessage<Grade>(grade, "Add");
                                professorGUI.getClient().communicate(message3);
                            }
                        }
                        professorGUI.addPage(new ProfCourseAssignments(professorGUI, course));
                        professorGUI.showPage();
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JPanel(), "No file selected");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new JPanel(), "The due date must be valid and of the format:\n"+
                            "MM/DD/YYYY");
                    dueDateInput.setText("");
                }
            }
        });

    }

    /**
     * check that the given date is valid
     */
    private boolean checkDate(){
        String date= dueDateInput.getText();

        if(date == null){ return false; }

        String [] dateFields= date.split("/");

        if(dateFields[0].compareTo("12")>0 || dateFields[0].compareTo("1")<0){ return false; }

        else if(dateFields[1].compareTo("31")>0 || dateFields[1].compareTo("1")<0){ return false; }

        else if(dateFields[2].compareTo("2018") != 0){ return false; }

        else { return true; }
    }

    /**
     * Constructs the elements of the frame
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new JPanel();
        userLabel = new JLabel();
        courseNameHeader = new JLabel();
        jPanel2 = new JPanel();
        jLabel4 = new JLabel();
        fileChooser = new JFileChooser();
        createB = new JButton();
        cancelB = new JButton();
        jLabel1 = new JLabel();
        dueDateInput = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.pink);

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userLabel.setText("User:");

        courseNameHeader.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        courseNameHeader.setText("CourseName Assignments");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(268, 268, 268)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseNameHeader))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(courseNameHeader)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(java.awt.Color.orange);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        createB.setText("Create");

        cancelB.setText("Cancel");

        jLabel1.setText("Due Date:");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(jLabel4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(dueDateInput, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(66, 66, 66)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(createB)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelB)))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addComponent(jLabel4))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(111, 111, 111)
                                                                .addComponent(jLabel1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(dueDateInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(48, 48, 48)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(createB)
                                                                        .addComponent(cancelB))
                                                                .addGap(10, 10, 10))
                                                        .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>


    // Variables declaration - do not modify
    private JTextField dueDateInput;
    private JFileChooser fileChooser;
    private JButton createB;
    private JButton cancelB;
    private JLabel jLabel1;
    private JLabel courseNameHeader;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel userLabel;
    // End of variables declaration
}

