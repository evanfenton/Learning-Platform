package FrontEnd.pages;

import SharedDataObjects.Course;
import SharedDataObjects.Professor;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;
import FrontEnd.ProfessorGUI;
import SharedDataObjects.Student;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Evan Mcphee
 */
public class ProfCourseHome extends Page {

    /**
     * Creates new frame ProfCourseHome
     */
    public ProfCourseHome(ProfessorGUI prof, Course course) {
        super(prof, true);
        initComponents();
        refreshStudentList();
        courseNameHeader.setText(course.getName() + " " + course.getId());
        userLabel.setText("User: " + prof.getProfessor().getFirstname() + "  " + prof.getProfessor().getLastname());
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
            java.util.logging.Logger.getLogger(ProfCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        gradesB.setVisible(false);

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
         * Closes this frame and reopens the ProfHome page
         */
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new ProfHome(prof));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /** 
         * Reads what is in the searchParameter text field and what parameter is set in the searchDropDown
         * then searches the DB and fill the studentList with results
         */
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchstr = searchParameter.getText();
                if(searchDropDown.getSelectedItem().equals("Student ID")){
                	int searchint = Integer.parseInt(searchstr);
                    // search db for student id
                    ServerMessage<Course> message= new ServerMessage<>(course, "SearchID "+ searchint);
                    ServerMessage<?> returned= ProfCourseHome.super.getNavigator().getClient().communicate(message);
                    listmodel.clear();
                    listmodel.addElement((Student) returned.getObject());
                    
                } else {
                    //search db for student last name
                    String lastName = searchstr;
                    ServerMessage<Course> message= new ServerMessage<>(course, "SearchName "+ lastName);
                    ServerMessage<?> returned= ProfCourseHome.super.getNavigator().getClient().communicate(message);
                    ArrayList<?> students = (ArrayList<?>) returned.getObject();
                    listmodel.clear();
                    
                    for(int i = 0; i < students.size(); i++)
                    {
                    	listmodel.addElement((Student) students.get(i));
                    }
                }
            }

        });

        /**
         *  refreshes the studentList with all students in the course and clears searchParameter
         */
        clearB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchParameter.setText(null);
                refreshStudentList();
            }
        });

        /**
         * Open ProfCourseAssignment frame and close this frame
         */
        assignmentsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new ProfCourseAssignments(professorGUI,course));
                professorGUI.showPage();
                setVisible(false);
            }
        });
        
        /**
         * Open a new message frame and close this frame
         */
        messageB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage( new Messenger(professorGUI, course, true));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * Reads which student from the selection model was pressed on and opens a StudentInfo frame with that students information
         */
        studentsList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //get Student object from page and open StudentInfo with it
                Student student = studentsList.getSelectedValue();
                StudentInfo studentinfopage = new StudentInfo(professorGUI,course,student);
                if(professorGUI.getPageHolder().getClass() != studentinfopage.getClass()) {
                    professorGUI.addPage(new StudentInfo(professorGUI, course, student));
                    professorGUI.showPage();
                }
                setVisible(false);
            }
        });

        
    }

    /**
     * Creates all the elements of the frame
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        courseNameHeader = new javax.swing.JLabel();
        returnB = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel2 = new javax.swing.JLabel();
        searchParameter = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        searchDropDown = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        searchB = new javax.swing.JButton();
        clearB = new javax.swing.JButton();
        assignmentsB = new javax.swing.JButton();
        gradesB = new javax.swing.JButton();
        messageB = new javax.swing.JButton();
        logoutB = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.pink);

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userLabel.setText("User:");

        courseNameHeader.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        courseNameHeader.setText("CourseName Home Page");

        returnB.setText("Return to Home Page");


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(returnB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseNameHeader))
                                .addGap(350, 350, 350))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(30, Short.MAX_VALUE)
                                .addComponent(courseNameHeader)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(returnB, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );



        jPanel2.setBackground(java.awt.Color.orange);


        jScrollPane1.setViewportView(studentsList);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Students:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Search Students:");

        searchDropDown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Last Name"}));


        jLabel5.setText("Search By:");

        jLabel6.setText("Please Enter Search Parameter:");

        searchB.setText("Search");

        clearB.setText("Clear");

        assignmentsB.setText("Assignments");

        gradesB.setText("Grades");

        messageB.setText("Message Students");

        logoutB.setText("Logout");

        jLabel7.setText("Select a student from the list for more information");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(assignmentsB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(messageB)
                                                .addGap(40, 40, 40))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(gradesB)
                                                                .addGap(163, 163, 163)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(searchB)
                                                                .addGap(55, 55, 55)
                                                                .addComponent(clearB))
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel6)
                                                        .addComponent(searchParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutB)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(74, 74, 74)
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(searchDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchParameter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(searchB)
                                                        .addComponent(clearB)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(messageB)
                                        .addComponent(gradesB)
                                        .addComponent(assignmentsB)
                                        .addComponent(logoutB))
                                .addContainerGap())
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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    /**
     * refreshes the student list with current info from the database
     */
    private void refreshStudentList()
    {
  	  try
  	  {
	    	  listmodel.clear();
	    	  ServerMessage<Student> message = new ServerMessage<Student>(new Student(), "GetAllStudents");
	    	  ServerMessage<?> recieved = professorGUI.getClient().communicate(message);
	    	  ArrayList<?> list = (ArrayList<?>) recieved.getObject();
	    	  for(int i = 0; i < list.size(); i++)
	    	  {
	    		  listmodel.addElement((Student) list.get(i));
	    	  }
  	  }
  	  catch(NullPointerException k)
  	  {
  		  studentsList.clearSelection();
  	  }
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton assignmentsB;
    private javax.swing.JButton clearB;
    private javax.swing.JButton gradesB;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel courseNameHeader;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutB;
    private javax.swing.JButton messageB;
    private javax.swing.JButton returnB;
    private javax.swing.JButton searchB;
    private javax.swing.JComboBox<String> searchDropDown;
    private javax.swing.JTextField searchParameter;
    private DefaultListModel<Student> listmodel = new DefaultListModel<>();
    private JList<Student> studentsList = new JList<>(listmodel);

   
    private javax.swing.JLabel userLabel;
    // End of variables declaration
}
