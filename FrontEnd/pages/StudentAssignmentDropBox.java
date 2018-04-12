package FrontEnd.pages;

import FrontEnd.StudentGUI;
import SharedDataObjects.Assignment;
import SharedDataObjects.Course;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Submission;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

/**
 *
 * @author Evan Mcphee
 */
public class StudentAssignmentDropBox extends Page {

    /**
     * Creates new frame StudentAssignmentDropBox
     */
    public StudentAssignmentDropBox(StudentGUI stu, Assignment assignment, Course course) {
        super(stu,false);
        initComponents();
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
            java.util.logging.Logger.getLogger(StudentAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        courseHeader.setText(course.getName() + " " + course.getId());
        assignmentHeader.setText(assignment.getTitle());
        closeDateInfo.setText(assignment.getDue_date());
        userHeader.setText(stu.getStudent().getFirstname() + " " + stu.getStudent().getLastname());

        /**
         * logout button event handler, just terminates the program
         */
        logoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Closes this frame and reopens the StudentHome page
         */
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentGUI.addPage(new StudentCourseHome(studentGUI,course));
                studentGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * uploads a submission to the server
         */
        uploadB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.getSelectedFile() != null) {
                    File filetosend = fileChooser.getSelectedFile();
                    String fileinfo = filetosend.getName();
                    //Start of code to send file, provided by ENSF409 instructor
                    long length = filetosend.length();
                    byte[] content = new byte[(int) length]; // Converting Long to Int


                    try {
                        FileInputStream fis = new FileInputStream(filetosend);
                        BufferedInputStream bos = new BufferedInputStream(fis);
                        bos.read(content, 0, (int) length);
                        ServerMessage message = new ServerMessage(content, "Submissionstr-1splitter".concat(fileinfo));
                        getNavigator().getClient().communicate(message);

                    } catch (FileNotFoundException g) {
                        g.printStackTrace();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                    String[] filesplit = fileinfo.split("\\.(?=[^\\.]+$)");
                    Random rand = new Random();
                    Submission sub = new Submission(rand.nextInt(99999999) + 1, assignment.getId(), stu.getStudent().getId(), "."+filesplit[1], filesplit[0], "timestamp");
                    ServerMessage<Submission> message = new ServerMessage<>(sub, "Add");
                    studentGUI.getClient().communicate(message);
                    studentGUI.addPage(new StudentCourseHome(stu,course));
                    studentGUI.showPage();
                    setVisible(false);
                }
            }
        });

        /**
         * Downloads the file to the directory chosen in the filechooser popup after the button press
         */
        downloadB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        int option = fileChooser.showDialog(null,
                                "Select Directory");
                        File f = null;
                        if (option == JFileChooser.APPROVE_OPTION) {
                            f = fileChooser.getSelectedFile();
                            // if the user accidently click a file, then select the parent directory.
                            if (!f.isDirectory()) {
                                f = f.getParentFile();
                            }
                        }
                        String path = f.getAbsolutePath();
                        ServerMessage message = new ServerMessage(assignment,"DownloadAssignment");
                        ServerMessage returnedmessage = getNavigator().getClient().communicate(message);
                        File newFile = new File(path.concat("//"+returnedmessage.getMessage()));
                        System.out.println(newFile.getAbsolutePath());
                    try{
                        if(!newFile.exists())
                            newFile.createNewFile();
                        FileOutputStream writer = new FileOutputStream(newFile);
                        BufferedOutputStream bos = new BufferedOutputStream(writer);
                        bos.write((byte []) returnedmessage.getObject());
                        bos.close();
                    } catch(IOException g){
                        g.printStackTrace();
                    }
                studentGUI.addPage(new StudentCourseHome(stu,course));
                studentGUI.showPage();
                setVisible(false);
                }

        });
    }

    /**
     * Creates all the elements of the GUI
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel6 = new JPanel();
        returnB = new JButton();
        userHeader = new JLabel();
        assignmentHeader = new JLabel();
        courseHeader = new JLabel();
        jPanel7 = new JPanel();
        logoutB = new JButton();
        jLabel1 = new JLabel();
        fileChooser = new JFileChooser();
        uploadB = new JButton();
        closeDateInfo = new JTextField();
        downloadB = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(java.awt.Color.pink);

        returnB.setText("Return to Course Home");

        userHeader.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userHeader.setText("User:");

        assignmentHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        assignmentHeader.setText("Assignment Name");

        courseHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        courseHeader.setText("Course Name");

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGap(191, 191, 191)
                                                .addComponent(courseHeader)
                                                .addGap(18, 18, 18)
                                                .addComponent(assignmentHeader))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(returnB)
                                                .addGap(113, 113, 113)
                                                .addComponent(userHeader, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseHeader)
                                        .addComponent(assignmentHeader))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(returnB)
                                        .addComponent(userHeader, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel7.setBackground(java.awt.Color.orange);

        logoutB.setText("Logout");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("DropBox Close Date:");

        uploadB.setText("Upload Assignment");

        closeDateInfo.setEditable(false);

        downloadB.setText("Download Assignment");

        GroupLayout jPanel7Layout = new GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutB))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(closeDateInfo, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(downloadB))
                                                .addGap(50, 50, 50)
                                                .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(uploadB)
                                                        .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(closeDateInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(downloadB)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(uploadB)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(logoutB)
                                .addGap(10, 10, 10))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>



    // Variables declaration - do not modify
    private JLabel assignmentHeader;
    private JTextField closeDateInfo;
    private JLabel courseHeader;
    private JButton downloadB;
    private JFileChooser fileChooser;
    private JLabel jLabel1;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JButton logoutB;
    private JButton returnB;
    private JButton uploadB;
    private JLabel userHeader;
    // End of variables declaration
}

