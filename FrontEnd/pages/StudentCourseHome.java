package FrontEnd.pages;

import FrontEnd.StudentGUI;
import SharedDataObjects.Assignment;
import SharedDataObjects.Course;
import SharedDataObjects.Grade;
import SharedDataObjects.Professor;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Evan
 */
public class StudentCourseHome extends Page{
	Course course;
    /**
     * Creates new form StudentCourseHome
     */
    public StudentCourseHome(StudentGUI stu, Course course) {
        super(stu, false);
        this.course = course;
        initComponents();
        ServerMessage<Course> message = new ServerMessage<Course>(course, "ProfessorName");
        ServerMessage<?> response =  stu.getClient().communicate(message);
        profNamefield.setText(response.getMessage());
        refreshAssignmentList();
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
            java.util.logging.Logger.getLogger(StudentCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentCourseHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        userLabel.setText("User:  " + studentGUI.getStudent().getFirstname()  + " " + studentGUI.getStudent().getLastname());
        header.setText(course.getName() + " " + course.getId());

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
                studentGUI.addPage(new StudentHome(studentGUI));
                studentGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * Opens up messenger frame to email the prof of the course the student is currently viewing
         */
        profMessageB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentGUI.addPage(new Messenger(studentGUI, course, false));
                studentGUI.showPage();
                setVisible(false);
            }
        });
        
        assignmentList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try
                {
	            	Assignment assign = assignmentList.getSelectedValue();
	            	assignName.setText(assign.getTitle());
	                assignCloseDate.setText(assign.getDue_date());
	                ServerMessage<Student> message = new ServerMessage<Student>(studentGUI.getStudent(), "GetGrades");
	                ServerMessage<?> recieved = studentGUI.getClient().communicate(message);
	                ArrayList<?> grades =  (ArrayList<?>) recieved.getObject();
	                for(int i = 0; i < grades.size(); i++)
	                {
	                	Grade grade = (Grade) grades.get(i);
	                	if(grade.getAssign_id() == assign.getId())
	                	{
	                		assignGrade.setText("" +grade.getGrade());
	                	}
	                }
	                ServerMessage<Assignment> message2 = new ServerMessage<Assignment>(assign, "GetProf");
	                ServerMessage<?> recieved2 = studentGUI.getClient().communicate(message2);
	                Professor prof = (Professor) recieved2.getObject();
	                profNamefield.setText(prof.getFirstname() + " " + prof.getLastname());
                }
                catch(NullPointerException z)
                {
                	z.printStackTrace();
                	//assignmentList.clearSelection();
                }
            }
        });

        /**
         * opens up StudentAssignmentDropBox frame for the current course and closes this frame
         */
        dropboxB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Assignment assign= assignmentList.getSelectedValue();

                if(assign != null) {
                    studentGUI.addPage(new StudentAssignmentDropBox(stu, assign, course));
                    studentGUI.showPage();
                    setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(new JPanel(), "No Assignment Selected");
                }
            }
        });

    }

    /**
     * Creates all the elements of the GUI
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new JPanel();
        userLabel = new JLabel();
        header = new JLabel();
        returnB = new JButton();
        jPanel2 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jLabel2 = new JLabel();
        jLabel4 = new JLabel();
        logoutB = new JButton();
        jLabel7 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        dropboxB = new JButton();
        assignName = new JTextField();
        assignUpDate = new JTextField();
        assignCloseDate = new JTextField();
        jLabel1 = new JLabel();
        jLabel10 = new JLabel();
        assignStatus = new JTextField();
        assignGrade = new JTextField();
        jScrollPane2 = new JScrollPane();
        commentTextArea = new JTextArea();
        jLabel11 = new JLabel();
        profNamefield = new JTextField();
        profMessageB = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(Color.pink);

        userLabel.setFont(new Font("Tahoma", 0, 18)); // NOI18N
        userLabel.setText("User:");

        header.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        header.setText("CourseName");

        returnB.setText("Return to Home Page");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(returnB)
                                .addGap(265, 265, 265)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(header))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(36, Short.MAX_VALUE)
                                .addComponent(header)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(returnB)
                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(Color.orange);

        jScrollPane1.setViewportView(assignmentList);

        jLabel2.setFont(new Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Assignments");

        jLabel4.setFont(new Font("Dialog", 1, 18)); // NOI18N

        logoutB.setText("Logout");

        jLabel7.setText("Select an assignment from the list for more information");

        jLabel5.setText("Assignment:");

        jLabel8.setText("Drop Box Closed:");

        dropboxB.setText("Assignment DropBox");

        assignName.setEditable(false);

        assignCloseDate.setEditable(false);

        jLabel1.setText("Grade:");

        assignGrade.setEditable(false);

        jLabel11.setText("Professor:");

        profNamefield.setEditable(false);

        profMessageB.setText("Message Professor");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                .addGap(115, 115, 115)
                                                                .addComponent(jLabel4))
                                                        .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                                .addGap(105, 105, 105)
                                                                .addComponent(dropboxB, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap(45, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel7)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel8)
                                                                        .addComponent(jLabel1))
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(assignCloseDate, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(assignGrade, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(assignName, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(logoutB)
                                                .addContainerGap())
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE))
                                                .addGap(48, 48, 48))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel11)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(profNamefield, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(profMessageB, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 361, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(profNamefield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(profMessageB)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                                .addComponent(logoutB))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(94, 94, 94)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addGap(52, 52, 52)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(assignName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(assignCloseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(assignGrade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(51, 51, 51)
                                                .addComponent(dropboxB)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    /**
     * refreshes the assignment list with current information from the database
     */
    private void refreshAssignmentList()
    {
    	try
    	{
    		listmodel.clear();
    		ServerMessage<Course> message = new ServerMessage<Course>(course, "GetActiveCourseAssignments");
    		ServerMessage<?> recieved = studentGUI.getClient().communicate(message);
    		ArrayList<?> list = (ArrayList<?>) recieved.getObject();
    		for(int i = 0; i < list.size(); i++)
	    	  {
	    		  System.out.println(list.get(i));
    			  listmodel.addElement((Assignment) list.get(i));
	    	  }
    	}
    	catch(NullPointerException k)
    	{
    		assignmentList.clearSelection();
    	}
    }

    // Variables declaration - do not modify
    private JTextField assignCloseDate;
    private JTextField assignGrade;
    private JTextField assignName;
    private JTextField assignStatus;
    private JTextField assignUpDate;
    private DefaultListModel<Assignment> listmodel = new DefaultListModel<>();
    private JList<Assignment> assignmentList = new JList<>(listmodel);
    private JTextArea commentTextArea;
    private JButton dropboxB;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel2;
    private JLabel header;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JButton logoutB;
    private JButton profMessageB;
    private JTextField profNamefield;
    private JButton returnB;
    private JLabel userLabel;
    // End of variables declaration
}
