package FrontEnd.pages;

import SharedDataObjects.Course;
import SharedDataObjects.Grade;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;
import SharedDataObjects.StudentEnrollment;
import FrontEnd.ProfessorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Evan Mcphee
 */
public class StudentInfo extends Page {
    /**
     * Selected student from the previous frame
     */
	private Student student;

    /**
     * Creates new form StudentInfo
     */
    public StudentInfo(ProfessorGUI prof, Course course, Student student) {
        super(prof, true);
        this.student = student;
        initComponents();
        refreshGradeList();
        int sum = 0;
        int average;
        int count = 0;;
        for(int i = 0; i < listmodel.size(); i++)
        {
        	Grade grade = listmodel.getElementAt(i);
        	sum += grade.getGrade();
        	count++;
        }
        try
        {
        	average = sum/count;
        }
        catch(ArithmeticException e)
        {
        	average = 0;
        }
        userLabel.setText("User: " + prof.getProfessor().getFirstname() + "  " + prof.getProfessor().getLastname());
        header.setText(student.getFirstname() + " " + student.getLastname() + " " + student.getId());
        fName.setText(student.getFirstname());
        lName.setText(student.getLastname());
        stuID.setText(Integer.toString(student.getId()));
        cAVG.setText((""+average));
        StudentEnrollment enrollment = new StudentEnrollment(1,student.getId(), course.getId(),true);
        ServerMessage<StudentEnrollment> message = new ServerMessage<StudentEnrollment>(enrollment, "CheckEnroll");
        ServerMessage<?> enrolled = professorGUI.getClient().communicate(message);
        
 
        if(enrolled.getObject() != null)
        	courseStatus.setText("Enrolled");
        else
        	courseStatus.setText("Not Enrolled");
         

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
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

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
                professorGUI.addPage(new ProfCourseHome(professorGUI,course));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * sets the students status in the course "course" to enrolled and updates the text field with the new info
         */
        enrollB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!courseStatus.getText().equals("Enrolled"))
                {
                	Random rand = new Random();
                	StudentEnrollment enrollment = new StudentEnrollment(rand.nextInt(99999999)+1, 
                									student.getId(),
                									course.getId(), true);
                	ServerMessage<StudentEnrollment> message = new ServerMessage<StudentEnrollment> (enrollment, "Enroll");
                	professorGUI.getClient().communicate(message);
                	courseStatus.setText("Enrolled");
                }
                
            }
        });

        /**
         * sets the students status in the course "course" to not enrolled and updates the text field with the new info
         */
        unEnrollB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(courseStatus.getText().equals("Enrolled"))
                {
                	Random rand = new Random();
                	StudentEnrollment enrollment = new StudentEnrollment(rand.nextInt(99999999)+1, 
                									student.getId(),
                									course.getId(), false);
                	ServerMessage<StudentEnrollment> message = new ServerMessage<StudentEnrollment> (enrollment, "Unenroll");
                	professorGUI.getClient().communicate(message);
                	courseStatus.setText("Not Enrolled");
                }
            }
        });

        /**
         * list selection listener for the page.
         */
        gradesList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //get Student object from page and open StudentInfo with it
                Grade grade = gradesList.getSelectedValue();
                selGrade.setText("" +grade.getGrade());
              
            }
        });

        /**
         * Changes the grade for selected value
         */
        changeGradeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerMessage<Grade> message = new ServerMessage<Grade>(gradesList.getSelectedValue(), "ChangeGrade " + selGrade.getText());
                professorGUI.getClient().communicate(message);
            }
        });
    }
    
    

    /**
     * Creates all the elements of the GUI
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        logoutB = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        selGrade = new javax.swing.JTextField();
        fName = new javax.swing.JTextField();
        lName = new javax.swing.JTextField();
        stuID = new javax.swing.JTextField();
        cAVG = new javax.swing.JTextField();
        changeGradeB = new javax.swing.JButton();
        unEnrollB = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        courseStatus = new javax.swing.JTextField();
        enrollB = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        returnB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(java.awt.Color.orange);

        logoutB.setText("Logout");

        jLabel2.setText("First Name:");

        jLabel4.setText("Last Name:");

        jLabel5.setText("Student ID:");

        jLabel6.setText("Current AVG:");

        jScrollPane1.setViewportView(gradesList);

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setText("Grades:");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Select a grade to edit it:");

        jLabel9.setText("Grade:");

        fName.setEditable(false);

        lName.setEditable(false);

        stuID.setEditable(false);

        cAVG.setEditable(false);

        changeGradeB.setText("Change Grade");

        unEnrollB.setText("UnEnroll");

        jLabel11.setText("Course Status:");

        courseStatus.setEditable(false);

        enrollB.setText("Enroll");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutB))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel2)
                                                                                        .addComponent(jLabel4)
                                                                                        .addComponent(jLabel5)
                                                                                        .addComponent(jLabel6)
                                                                                        .addComponent(jLabel11))
                                                                                .addGap(36, 36, 36)
                                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(cAVG, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                                .addComponent(fName)
                                                                                                .addComponent(lName, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                                                                                .addComponent(stuID, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                                .addComponent(courseStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(49, 49, 49)
                                                                                                .addComponent(enrollB)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(unEnrollB)))
                                                                                .addGap(208, 208, 208))
                                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel8)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel9)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(selGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(changeGradeB)))))
                                                .addGap(0, 13, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(stuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(cAVG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(courseStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(enrollB)
                                                        .addComponent(unEnrollB))
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                                .addComponent(logoutB)
                                                .addContainerGap())
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel9)
                                                        .addComponent(selGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(changeGradeB))
                                                .addGap(268, 268, 268))))
        );

        jPanel1.setBackground(java.awt.Color.pink);

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userLabel.setText("User:");

        header.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        header.setText("StudentName's Information");

        returnB.setText("Return to Course Page");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(343, 343, 343)
                                .addComponent(header)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(returnB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(325, 325, 325))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(36, Short.MAX_VALUE)
                                .addComponent(header)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(returnB))
                                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>


    /**
     * refreshes the grade list with current information from the server
     */
    private void refreshGradeList()
    {
  	  try
  	  {
	    	  listmodel.clear();
	    	  ServerMessage<Student> message = new ServerMessage<Student>(student, "PGetStudentGrades " + professorGUI.getProfessor().getId());
	    	  ServerMessage<?> recieved = professorGUI.getClient().communicate(message);
	    	  ArrayList<?> list = (ArrayList<?>) recieved.getObject();
	    	  for(int i = 0; i < list.size(); i++)
	    	  {
	    		  listmodel.addElement((Grade) list.get(i));
	    	  }
  	  }
  	  catch(NullPointerException k)
  	  {
  		  gradesList.clearSelection();
  	  }
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField cAVG;
    private javax.swing.JButton changeGradeB;
    private javax.swing.JTextField courseStatus;
    private javax.swing.JButton enrollB;
    private javax.swing.JTextField fName;
    private DefaultListModel<Grade> listmodel = new DefaultListModel<>();
    private JList<Grade> gradesList = new JList<>(listmodel);
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lName;
    private javax.swing.JButton logoutB;
    private javax.swing.JButton returnB;
    private javax.swing.JTextField selGrade;
    private javax.swing.JTextField stuID;
    private javax.swing.JButton unEnrollB;
    private javax.swing.JLabel userLabel;
    // End of variables declaration
}
