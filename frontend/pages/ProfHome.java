package frontend.pages;

import frontend.ProfessorGUI;

import javax.swing.*;

import SharedDataObjects.Course;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
   *
   * @author Evan Mcphee
   */
  public class ProfHome extends Page {

      /**
       * Creates new form ProfHome
       */
      public ProfHome(ProfessorGUI prof) {
          super(prof);
          initComponents();
          userLabel.setText("User: " + prof.getProfessor().getFirstname() + "   " + prof.getProfessor().getLastname());
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
              java.util.logging.Logger.getLogger(ProfHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (InstantiationException ex) {
              java.util.logging.Logger.getLogger(ProfHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (IllegalAccessException ex) {
              java.util.logging.Logger.getLogger(ProfHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          } catch (UnsupportedLookAndFeelException ex) {
              java.util.logging.Logger.getLogger(ProfHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
          }
          //</editor-fold>


          /**
           * Logout button event handler, just terminates the program when pressed
           * we should probably make it have an are you sure popup just in case
           */
          logoutB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  System.exit(0);
              }
          });

          /** --COMEPLETE?--
           * This needs to get whatever course is selected in courseList and create the ProfCourseHome with that info
           * or create a popup if nothing is selected
           */
          viewCourseB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  //need to make course home get whatever course its displaying
            	  if(courseList.getSelectedValue() != null)
            	  {
            		  Course selectedcourse = courseList.getSelectedValue();
                	  professor.addPage(new ProfCourseHome(ProfHome.super.getProfessor(), selectedcourse));
                	  professor.showPage();
                      setVisible(false);
            	  }
            	  else
            	  {
            		  JOptionPane.showMessageDialog(new JPanel(), "No Course Selected");
            	  }
            	
              }
          });

          /**
           * --INCOMPLETE--
           * Sends something to server to set the course to active and then reloads the courseList from server so its updated
           *
           * To get to profGUI use ProfHome.super.getProfessor
           */
          activateCourseB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
              }
          });

          /**
           * --INCOMPLETE--
           * Sends something to server to set the course to inactive and then reloads the courseList from server so its updated
           *
           * To get to profGUI use ProfHome.super.getProfessor
           */
          deActivateCourseB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

              }
          });

          /**
           * --INCOMPLETE--
           * Opens up a new frame of AddCourse that handles the rest of the event
           */
          addCourseB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  ProfHome.super.professor.addPage(new AddCourse(ProfHome.super.professor));
                  ProfHome.super.professor.showPage();
                  setVisible(false);
              }
          });

          /**
           * --INCOMPLETE--
           * Sends something to server to delete the course and then updates courseList
           */
          removeCourseB.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {

              }
          });


      }

      /**
       * This method is called from within the constructor to initialize the form.
       * WARNING: Do NOT modify this code. The content of this method is always
       * regenerated by the Form Editor.
       */
      @SuppressWarnings("unchecked")
      // <editor-fold defaultstate="collapsed" desc="Generated Code">
      private void initComponents() {

          jPanel2 = new JPanel();
          deActivateCourseB = new JButton();
          logoutB = new JButton();
          viewCourseB = new JButton();
          activateCourseB = new JButton();
          jLabel2 = new JLabel();
          jScrollPane1 = new JScrollPane();
          addCourseB = new JButton();
          removeCourseB = new JButton();
          jPanel1 = new JPanel();
          userLabel = new JLabel();
          jLabel3 = new JLabel();

          setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          setTitle("HomePage");
          setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
          setName("HomePage"); // NOI18N

          jPanel2.setBackground(java.awt.Color.orange);

          deActivateCourseB.setText("Deactivate Course");

          logoutB.setText("Logout");

          viewCourseB.setText("View Course");

          activateCourseB.setText("Activate Course");

          jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
          jLabel2.setText("Your Courses:");

          courseList.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
          
          
          jScrollPane1.setViewportView(courseList);

          addCourseB.setText("Add Course");

          removeCourseB.setText("Remove Course");

          GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
          jPanel2.setLayout(jPanel2Layout);
          jPanel2Layout.setHorizontalGroup(
                  jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                  .addContainerGap()
                                  .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                          .addComponent(deActivateCourseB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(activateCourseB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(viewCourseB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(removeCourseB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                          .addComponent(addCourseB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                  .addGap(18, 18, 18)
                                  .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 817, GroupLayout.PREFERRED_SIZE)
                                  .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                          .addGroup(jPanel2Layout.createSequentialGroup()
                                  .addGap(461, 461, 461)
                                  .addComponent(jLabel2)
                                  .addGap(0, 0, Short.MAX_VALUE))
                          .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                  .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                  .addComponent(logoutB)
                                  .addContainerGap())
          );
          jPanel2Layout.setVerticalGroup(
                  jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGroup(jPanel2Layout.createSequentialGroup()
                                  .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                          .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addGap(172, 172, 172)
                                                  .addComponent(viewCourseB)
                                                  .addGap(53, 53, 53)
                                                  .addComponent(activateCourseB)
                                                  .addGap(18, 18, 18)
                                                  .addComponent(deActivateCourseB)
                                                  .addGap(68, 68, 68)
                                                  .addComponent(addCourseB)
                                                  .addGap(18, 18, 18)
                                                  .addComponent(removeCourseB)
                                                  .addGap(0, 12, Short.MAX_VALUE))
                                          .addGroup(jPanel2Layout.createSequentialGroup()
                                                  .addGap(62, 62, 62)
                                                  .addComponent(jLabel2)
                                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                  .addComponent(jScrollPane1)))
                                  .addGap(68, 68, 68)
                                  .addComponent(logoutB)
                                  .addContainerGap())
          );

          jPanel1.setBackground(java.awt.Color.pink);

          userLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
          userLabel.setText("User:");

          jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
          jLabel3.setText("Home Page");

          GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
          jPanel1.setLayout(jPanel1Layout);
          jPanel1Layout.setHorizontalGroup(
                  jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGroup(jPanel1Layout.createSequentialGroup()
                                  .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                          .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addGap(453, 453, 453)
                                                  .addComponent(jLabel3))
                                          .addGroup(jPanel1Layout.createSequentialGroup()
                                                  .addGap(343, 343, 343)
                                                  .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)))
                                  .addContainerGap(383, Short.MAX_VALUE))
          );
          jPanel1Layout.setVerticalGroup(
                  jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                          .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                  .addGap(27, 27, 27)
                                  .addComponent(jLabel3)
                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                  .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                  .addGap(25, 25, 25))
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
                          .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                  .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                  .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                  .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          );

          pack();
      }// </editor-fold>




      // Variables declaration - do not modify
      private JButton activateCourseB;
      private JButton addCourseB;
      private DefaultListModel<Course> listmodel = new DefaultListModel<>();
      private JList<Course> courseList = new JList<>(listmodel);
      private JButton deActivateCourseB;
      private JLabel jLabel2;
      private JLabel jLabel3;
      private JPanel jPanel1;
      private JPanel jPanel2;
      private JScrollPane jScrollPane1;
      private JScrollPane jScrollPane2;
      private JButton logoutB;
      private JButton removeCourseB;
      private JLabel userLabel;
      private JButton viewCourseB;
      // End of variables declaration

  }
