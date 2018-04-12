package FrontEnd.pages;

import SharedDataObjects.Assignment;
import SharedDataObjects.Course;
import SharedDataObjects.ServerMessage;
import SharedDataObjects.Student;
import FrontEnd.ProfessorGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Evan Mcphee
 */
public class ProfCourseAssignments extends Page {

    /**
     * Creates new frame ProfCourseAssignments
     */
    public ProfCourseAssignments(ProfessorGUI prof, Course course) {
        super(prof, true);
        initComponents();
        header.setText(course.getName() + " " + course.getId() + " - Assignments");
        userLabel.setText("User: " + prof.getProfessor().getFirstname() + "  " + prof.getProfessor().getLastname());
        this.course = course;
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
            java.util.logging.Logger.getLogger(ProfCourseAssignments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfCourseAssignments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfCourseAssignments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfCourseAssignments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
         * Closes this frame and opens the dropbox frame for the currently selected assignment
         */
        dropboxB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!assignmentList.isSelectionEmpty()) {
                    professorGUI.addPage(new ProfAssignmentDropBox(prof, course, assignmentList.getSelectedValue()));
                    professorGUI.showPage();
                    setVisible(false);
                } else{
                    JOptionPane.showMessageDialog(new JFrame(), "Please Select an assignment");
                }
            }
        });

        /**
         * Closes this frame and reopens the ProfCourseHome page
         */
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new ProfCourseHome(prof,course));
                professorGUI.showPage();
                setVisible(false);
            }
        });

        /**
         * Sets the Assignment to Active and updates list of Assignments
         */
        activateAssignmentB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ServerMessage<Assignment> message = new ServerMessage<Assignment>(assignmentList.getSelectedValue(), "Activate");
            	professorGUI.getClient().communicate(message);
            	refreshAssignmentList();
            }
        });

        /**
         * Sets the Assignment to Not Active and updates list of Assignments
         */
        deActivateAssignmentB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ServerMessage<Assignment> message = new ServerMessage<Assignment>(assignmentList.getSelectedValue(), "Deactivate");
            	professorGUI.getClient().communicate(message);
            	refreshAssignmentList();
            }
        });

        /**
         * Fill the corresponding textfield with info from the object selected in the list
         */
        assignmentList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try
                {
	            	Assignment assign = assignmentList.getSelectedValue();
	            	assignName.setText(assign.getTitle());
	                assignCloseDate.setText(assign.getDue_date());
	                if(assign.isActive())
	                	assignStatus.setText("Active");
	                else
	                	assignStatus.setText("Inactive");
                }
                catch(NullPointerException z)
                {
                	assignmentList.clearSelection();
                }
            }
        });

        /**
         * Remove Assignment from DB and refresh assignmentList
         */
        removeAssignB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ServerMessage<Assignment> message = new ServerMessage<Assignment>(assignmentList.getSelectedValue(), "Delete");
            	professorGUI.getClient().communicate(message);
            	refreshAssignmentList();
            }
        });

        /**
         * Opens the upload assignment page and closes this page
         */
        uploadAssignB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                professorGUI.addPage(new UploadAssignment(prof,course));
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
        activateAssignmentB = new JButton();
        deActivateAssignmentB = new JButton();
        removeAssignB = new JButton();
        dropboxB = new JButton();
        assignName = new JTextField();
  //      assignUpDate = new javax.swing.JTextField();
        assignCloseDate = new JTextField();
        assignStatus = new JTextField();
        uploadAssignB = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.pink);

        userLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        userLabel.setText("User:");

        header.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        header.setText("CourseName Assignments");

        returnB.setText("Return to Course Home Page");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(returnB)
                                .addGap(220, 220, 220)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(header))
                                .addContainerGap(394, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(36, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(returnB)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(header)
                                                .addGap(18, 18, 18)
                                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(java.awt.Color.orange);

      
        jScrollPane1.setViewportView(assignmentList);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Assignments");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        logoutB.setText("Logout");

        jLabel7.setText("Select an assignment from the list for more information");

        jLabel5.setText("Assignment:");

        //jLabel6.setText("Date Uploaded:");

        jLabel8.setText("Drop Box Closed:");

        jLabel9.setText("Active:");

        activateAssignmentB.setText("Activate Assignment");

        deActivateAssignmentB.setText("DeActivate Assignment");

        removeAssignB.setText("Remove Assignment");

        dropboxB.setText("Assignment DropBox");

        assignName.setEditable(false);

       // assignUpDate.setEditable(false);

        assignCloseDate.setEditable(false);

        assignStatus.setEditable(false);

        uploadAssignB.setText("Upload New Assignment");

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(96, 96, 96)
                                                .addComponent(jLabel7))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(activateAssignmentB, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel5)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jLabel8)
                                                                .addComponent(jLabel9))
                                                        .addComponent(deActivateAssignmentB, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(95, 95, 95)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
          //                                                      .addComponent(assignUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(assignName, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(assignCloseDate, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(assignStatus, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(75, 75, 75)
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(removeAssignB, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dropboxB, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(115, 115, 115)
                                                .addComponent(jLabel4)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(logoutB)
                                                .addContainerGap())
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(uploadAssignB))
                                                .addGap(48, 48, 48))))
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
                                                .addComponent(uploadAssignB)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutB)
                                                .addContainerGap())
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel7)
                                                                .addGap(78, 78, 78))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(assignName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel5))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                                                .addComponent(jLabel4)
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
           //                                             .addComponent(assignUpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6))
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(assignCloseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8))
                                                .addGap(31, 31, 31)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(assignStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(activateAssignmentB)
                                                        .addComponent(dropboxB))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(deActivateAssignmentB)
                                                        .addComponent(removeAssignB))
                                                .addGap(213, 213, 213))))
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
     * Refills the assignment list with current information from the database
     */
    private void refreshAssignmentList()
    {
    	try
    	{
    		listmodel.clear();
    		ServerMessage<Course> message = new ServerMessage<Course>(course, "GetCourseAssignments");
    		ServerMessage<?> recieved = professorGUI.getClient().communicate(message);
    		ArrayList<?> list = (ArrayList<?>) recieved.getObject();
    		for(int i = 0; i < list.size(); i++)
	    	  {
	    		  listmodel.addElement((Assignment) list.get(i));
	    	  }
    	}
    	catch(NullPointerException k)
    	{
    		assignmentList.clearSelection();
    	}
    }

    // Variables declaration - do not modify
    private JButton activateAssignmentB;
    private JTextField assignCloseDate;
    private JTextField assignName;
    private JTextField assignStatus;
    //private javax.swing.JTextField assignUpDate;
    private DefaultListModel<Assignment>listmodel = new DefaultListModel<Assignment>();
    private JList<Assignment> assignmentList = new JList<>(listmodel);
    private JButton deActivateAssignmentB;
    private JButton dropboxB;
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
    private JButton logoutB;
    private JButton removeAssignB;
    private JButton returnB;
    private JButton uploadAssignB;
    private JLabel userLabel;
    private Course course;
    // End of variables declaration
}
