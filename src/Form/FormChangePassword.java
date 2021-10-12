

package Form;
import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale.Category;
import javax.swing.table.DefaultTableModel;
import com.sun.glass.events.KeyEvent;
import java.awt.*; // awt.print is used for printing
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Lenovo
 */
public class FormChangePassword extends javax.swing.JFrame {

    private String SQL;
    private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    public FormChangePassword() {
        initComponents();
        conn = DbConnection.connect();
        Lock();
    }
    
    public void username(String User){
        LUsername.setText(User);
        displayid(User);
    }
    
    public void Lock(){
        TFNPassword.setEnabled(false);
        TFCNPassword.setEnabled(false);
        BAddUser.setEnabled(false);
    }
    
    public void displayid(String User){
        String sql= "select * from tuser where Username='"+User+"'";
            
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            
            String Id= rs.getString("Id");
            LID.setText(Id);
            
            }
        }catch(Exception e){
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        LId1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        BAddUser = new javax.swing.JButton();
        CBShow = new javax.swing.JCheckBox();
        TFNPassword = new javax.swing.JPasswordField();
        TFCNPassword = new javax.swing.JPasswordField();
        jLabel39 = new javax.swing.JLabel();
        TFCPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        LUsername = new javax.swing.JLabel();
        BValidate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Change Password");

        jPanel2.setBackground(new java.awt.Color(204, 0, 102));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        LId1.setBackground(new java.awt.Color(255, 51, 51));
        LId1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LId1.setForeground(new java.awt.Color(255, 255, 255));
        LId1.setText("BACK");
        LId1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LId1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LId1)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(LId1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Username");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("New Password");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Confirm New Password");

        BAddUser.setText("Change Password");
        BAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddUserActionPerformed(evt);
            }
        });

        CBShow.setText("Show Password");
        CBShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBShowActionPerformed(evt);
            }
        });

        TFNPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));

        TFCNPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("Current Password");

        TFCPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setText("ID");

        LID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LID.setText("ID");

        LUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LUsername.setText("Username");

        BValidate.setText("Validate");
        BValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BValidateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BValidate)
                    .addComponent(BAddUser)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFCPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(CBShow)
                            .addComponent(TFNPassword)
                            .addComponent(TFCNPassword)
                            .addComponent(LID)
                            .addComponent(LUsername))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LID))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(LUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFCPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BValidate)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFNPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFCNPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel38))
                .addGap(7, 7, 7)
                .addComponent(CBShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BAddUser)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LId1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LId1MouseClicked
        String Username= LUsername.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_LId1MouseClicked

    
    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void BAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddUserActionPerformed
            if(TFNPassword.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "New Password Must not be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            }else{
            if(TFNPassword.getText().equals(TFCNPassword.getText())){
                    if(TFNPassword.getText().equals(TFCPassword.getText())){
                            JOptionPane.showMessageDialog(null, "New Password must not be the same with the current password");
                    }else{
                        if (TFNPassword.getText().length() < 6){
                            JOptionPane.showMessageDialog(null, "Password must contain minimum 6 characters");
                        }else{
                        
                            try{
                                String sqlpass = "update tuser set Password='"+TFNPassword.getText()+"' where Id='"+LID.getText()+"'";

                                pst = conn.prepareStatement(sqlpass);
                                pst=conn.prepareStatement(sqlpass);
                                pst.executeUpdate();
                                pst.execute(); // execute the pst
                                JOptionPane.showMessageDialog(null, "Password changed");

                                String Username= LUsername.getText();
                                FormSiswa lf = new FormSiswa();
                                lf.setVisible(true);
                                this.setVisible(true);
                                lf.username(Username);
                                hide();
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, e);
                                System.out.println("e "+ e);
                            }
                        }
                    }
            }else{
              JOptionPane.showMessageDialog(null, "New Password and Confirm New Password don't match", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            }
    }//GEN-LAST:event_BAddUserActionPerformed

    private void CBShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBShowActionPerformed
        if(CBShow.isSelected()){
            TFCPassword.setEchoChar((char)0);
            TFNPassword.setEchoChar((char)0);
            TFCNPassword.setEchoChar((char)0);
        }else{
            TFCPassword.setEchoChar('*');
            TFNPassword.setEchoChar('*');
            TFCNPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_CBShowActionPerformed

    private void BValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BValidateActionPerformed
        String sql= "select * from tuser where Username=? and Password=?";
            
            try{
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1,LUsername.getText());
                pst.setString(2,TFCPassword.getText());
                rs=pst.executeQuery();
                if(rs.next()){
                    TFNPassword.setEnabled(true);
                    TFCNPassword.setEnabled(true);
                    TFCPassword.setEnabled(false);
                    BValidate.setEnabled(false);
                    BAddUser.setEnabled(true);
                }else{
                JOptionPane.showMessageDialog(null, "Incorect Password", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }catch(Exception e){
                
            }
        
    }//GEN-LAST:event_BValidateActionPerformed

    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FormChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChangePassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChangePassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAddUser;
    private javax.swing.JButton BValidate;
    private javax.swing.JCheckBox CBShow;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LId1;
    private javax.swing.JLabel LUsername;
    private javax.swing.JPasswordField TFCNPassword;
    private javax.swing.JPasswordField TFCPassword;
    private javax.swing.JPasswordField TFNPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
