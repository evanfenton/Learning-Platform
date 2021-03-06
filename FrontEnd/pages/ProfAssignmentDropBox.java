package FrontEnd.pages;

import FrontEnd.ProfessorGUI;
import SharedDataObjects.Assignment;
import SharedDataObjects.Course;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;
import SharedDataObjects.Submission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Evan Mcphee
 */
public class ProfAssignmentDropBox extends Page{
    /**
     * Current assignment directory the user is in
     */
	Assignment assign;
    /**
     * Creates new form ProfAssignmentsDropBox
     */
    public ProfAssignmentDropBox(ProfessorGUI prof, Course course, Assignment assignment) {
        super(prof,true);
        initComponents();
        assign = assignment;
        refreshSubmissionList();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProfAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfAssignmentDropBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        courseHeader.setText(course.getName() + " " + course.getId());
        assignmentHeader.setText(assignment.getTitle());
        userHeader.setText("User:  " + prof.getProfessor().getFirstname() + " " + prof.getProfessor().getLastname());

        /**
         * Logout button event handler, just terminates the program when pressed
         */
        logoutB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Closes this frame and reopens the ProfCourseHome page
         */
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new ProfCourseAssignments(prof,course));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * Updates the grade for the selected submission
         */
        updateGradeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String grade= gradeField.getText();

                if(checkGrade(grade)) {
                    ServerMessage<Submission> message = new ServerMessage<Submission>(subList.getSelectedValue(), "UpdateSubmissionGrade " + grade);
                    professorGUI.getClient().communicate(message);
                }
                else{
                    JOptionPane.showMessageDialog(new JPanel(), "Invalid grade input");
                    gradeField.setText("");
                }
            }
        });

        /**
         * Fills the text areas with the selected submissions information from the submissions list
         */
        subList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try
                {
	            	Submission submission = subList.getSelectedValue();
	            	ServerMessage<Submission> message = new ServerMessage<Submission>(submission, "GetStudentName");
	            	ServerMessage<?> response = professorGUI.getClient().communicate(message);
	            	stuNameInfo.setText(response.getMessage());
	            	stuIDInfo.setText("" + submission.getStudent_id());
	            	gradeField.setText("" + submission.getGrade());
                }
                catch(NullPointerException z)
                {
                	subList.clearSelection();
                }
            }
        });

        /**
         * Downloads the currently selected submission from server
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
                            // if the user accidentally clicks a file, then select the parent directory.
                            if (!f.isDirectory()) {
                                f = f.getParentFile();
                            }
                        }
                        String path = f.getAbsolutePath();
                        ServerMessage<Submission> message = new ServerMessage<Submission>(subList.getSelectedValue(),"DownloadSubmission");
                        ServerMessage<?> returnedmessage = getNavigator().getClient().communicate(message);
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
                professorGUI.addPage(new ProfCourseHome(prof,course));
                professorGUI.showPage();
                setVisible(false);
                }

        });
    }
    
    
    

    /**
     * creates all elements of the frame
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
    	fileChooser = new JFileChooser();
        jPanel6 = new javax.swing.JPanel();
        returnB = new javax.swing.JButton();
        userHeader = new javax.swing.JLabel();
        assignmentHeader = new javax.swing.JLabel();
        courseHeader = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        downloadB = new javax.swing.JButton();
        logoutB = new javax.swing.JButton();
        stuNameInfo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        stuIDInfo = new javax.swing.JTextField();
        gradeField = new javax.swing.JTextField();
        updateGradeB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel6.setBackground(java.awt.Color.pink);

        returnB.setText("Return to Course Home");

        userHeader.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userHeader.setText("User:");

        assignmentHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        assignmentHeader.setText("Assignment Name");

        courseHeader.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        courseHeader.setText("Course Name");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(returnB)
                                .addGap(147, 147, 147)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(courseHeader)
                                                .addGap(18, 18, 18)
                                                .addComponent(assignmentHeader))
                                        .addComponent(userHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseHeader)
                                        .addComponent(assignmentHeader))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(returnB)
                                        .addComponent(userHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanel7.setBackground(java.awt.Color.orange);

        
        jScrollPane1.setViewportView(subList);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel9.setText("Submissions:");

        jLabel1.setText("Select a submission for more details");

        jLabel10.setText("Submitted by: ");

        jLabel11.setText("Grade:");

        downloadB.setText("Download Submission");

        logoutB.setText("Logout");

        stuNameInfo.setEditable(false);

        jLabel13.setText("Student ID:");

        stuIDInfo.setEditable(false);

        updateGradeB.setText("Update Grade");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                .addGap(50, 50, 50)
                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel13)
                                                                                        .addComponent(jLabel10))
                                                                                .addGap(53, 53, 53)
                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                .addComponent(stuNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap(20, Short.MAX_VALUE))
                                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                        .addComponent(downloadB)
                                                                                                        .addComponent(stuIDInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 20, Short.MAX_VALUE))))
                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                .addGap(12, 270, Short.MAX_VALUE)
                                                                                                .addComponent(logoutB))
                                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                                                                .addComponent(jLabel11)
                                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(gradeField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                        .addComponent(updateGradeB))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                                .addContainerGap())))
                                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                                .addGap(88, 88, 88)
                                                                .addComponent(jLabel1)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(20, 20, 20))))
        );
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(52, 52, 52)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel10)
                                                        .addComponent(stuNameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(stuIDInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel13))
                                                .addGap(18, 18, 18)
                                                .addComponent(downloadB)
                                                .addGap(80, 80, 80)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(gradeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(updateGradeB)
                                                .addGap(136, 136, 136)
                                                .addComponent(logoutB)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    /**
     * Refills the submissions list with current information from the database
     */
    private void refreshSubmissionList()
    {
  	  try
  	  {
	    	  listmodel.clear();
	    	  ServerMessage<Assignment> message = new ServerMessage<Assignment>(assign, "GetSubmissions");
	    	  ServerMessage<?> recieved = professorGUI.getClient().communicate(message);
	    	  ArrayList<?> list = (ArrayList<?>) recieved.getObject();
	    	  for(int i = 0; i < list.size(); i++)
	    	  {
	    		  listmodel.addElement((Submission) list.get(i));
	    	  }
  	  }
  	  catch(NullPointerException k)
  	  {
  		  subList.clearSelection();
  	  }
    }

    /**
     * check that the given grade is valid
     */
    private boolean checkGrade(String grade){

        if(grade == null || grade.length()>3){ return false; }

        if(grade.length()==3 && (grade.charAt(0)!= '1' || grade.charAt(1)!='0' || grade.charAt(2)!= '0')){ return false; }

        for(int i=0; i< grade.length(); i++){
            if(grade.charAt(i)>'9' || grade.charAt(i)<'0'){ return false; }
        }

        return true;
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel assignmentHeader;
    private javax.swing.JLabel courseHeader;
    private javax.swing.JButton downloadB;
    private javax.swing.JTextField gradeField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutB;
    private javax.swing.JButton returnB;
    private javax.swing.JTextField stuIDInfo;
    private javax.swing.JTextField stuNameInfo;
    private DefaultListModel<Submission> listmodel = new DefaultListModel<>();
    private JList<Submission> subList = new JList<>(listmodel);
    private JFileChooser fileChooser;
    private javax.swing.JButton updateGradeB;
    private javax.swing.JLabel userHeader;
    // End of variables declaration
}
