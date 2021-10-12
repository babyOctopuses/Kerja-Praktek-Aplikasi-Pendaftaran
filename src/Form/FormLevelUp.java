

package Form;
import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;

/**
 *
 * @author Julfendi 1217001
 */
public class FormLevelUp extends javax.swing.JFrame {

    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    public FormLevelUp() {
        initComponents();
        conn = DbConnection.connect();
        FillCombo();
    }
    
    void LevelUp(String Id, String Username){
        Luser.setText(Username);
        LClass.setText(Id);
        SetAB(Id);
    }

    public void SetAB(String Id){
        try{
            String sql = "select AorB from tclass where ID='"+Id+"'";
            pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
            rs= pst.executeQuery();
            
            if(rs.next()){
                String A= rs.getString("AorB");
                LAorB.setText(A);
            }
        }catch(Exception e){
                    
        }
    }
    
    public void FillCombo(){
         try{
            String sql = "select ID from tclass where not id='Dis'";
            pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Id   = rs.getString("Id");
                CBLevelUp.addItem(Id); //add the name from the string name to the combobox
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CBLevelUp = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BLevelUp = new javax.swing.JButton();
        LClass = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LId1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LAorB = new javax.swing.JLabel();
        BToB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Current Class");

        CBLevelUp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Level Up to");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("User");

        BLevelUp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BLevelUp.setText("Level Up");
        BLevelUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLevelUpActionPerformed(evt);
            }
        });

        LClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LClass.setText("Class");

        Luser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Luser.setText("User");

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LEVEL UP");

        jPanel1.setBackground(new java.awt.Color(204, 0, 51));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        LId1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        LId1.setForeground(new java.awt.Color(255, 255, 255));
        LId1.setText("BACK");
        LId1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LId1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LId1)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LId1)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(192, 192, 192))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel4.setText("-");

        LAorB.setText("AorB");

        BToB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BToB.setText("Change to B");
        BToB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BToBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(26, 26, 26)
                        .addComponent(LClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LAorB))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(BLevelUp))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(BToB, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(Luser)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(CBLevelUp, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(LClass)
                    .addComponent(jLabel4)
                    .addComponent(LAorB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBLevelUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Luser)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BLevelUp)
                    .addComponent(BToB))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BLevelUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLevelUpActionPerformed
        String Class= LClass.getText();
        String New=(String) CBLevelUp.getSelectedItem();        
        
        try{
            String Prechildren="Select * from tclass where id='"+Class+"'";
            pst= conn.prepareStatement(Prechildren);
            rs =pst.executeQuery();
            if(rs.next()){
            String ClassName=rs.getString("ClassName");
                if(ClassName.equals("Pre-Children")){
                int Input = JOptionPane.showConfirmDialog(null, "Level Up "+Class+" to "+New+"?", "Confirm Move", JOptionPane.YES_NO_OPTION);      
                if(Input==JOptionPane.YES_OPTION){
                    try{
                        String sql = "update tsiswa set Class='"+New+"' where Class='"+Class+"' ";
                        pst=conn.prepareStatement(sql);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Updated");

                        String Username= Luser.getText();
                        FormSiswa lf = new FormSiswa();
                        lf.setVisible(true);
                        this.setVisible(true);
                        lf.username(Username);
                        hide();

                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                        System.out.println("e "+ e);
                    }
                }else{

                }
            
            }else{
                if(LAorB.getText().equals("B")){
                    int Input = JOptionPane.showConfirmDialog(null, "Level Up "+Class+" to "+New+"?", "Confirm Move", JOptionPane.YES_NO_OPTION);      
                    if(Input==JOptionPane.YES_OPTION){
                        try{
                            String sql = "update tsiswa set Class='"+New+"'  where Class='"+Class+"' ";
                            pst=conn.prepareStatement(sql);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null, "Updated");

                            String Username= Luser.getText();
                            FormSiswa lf = new FormSiswa();
                            lf.setVisible(true);
                            this.setVisible(true);
                            lf.username(Username);
                            hide();

                        //UpdateTable(); 
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null, e);
                            System.out.println("e "+ e);
                        }
                    }else{
                        //No action
                    }
            }else{
                //if not b
                JOptionPane.showMessageDialog(null, "Class Must be on Level B to Level Up");    
                }
                }
            }
        }catch(Exception e){
                
        }
    }//GEN-LAST:event_BLevelUpActionPerformed

    private void LId1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LId1MouseClicked
        String Username= Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_LId1MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        String Username= Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
        hide();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void BToBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BToBActionPerformed
        String Class= LClass.getText();
        String AorB = LAorB.getText();
        String New=(String) CBLevelUp.getSelectedItem();        
        
        try{
            String Prechildren="Select * from tclass where id='"+Class+"'";
            pst= conn.prepareStatement(Prechildren);
            rs =pst.executeQuery();
            if(rs.next()){
                String ClassName=rs.getString("ClassName");
                if(ClassName.equals("Pre-Children")){
                        JOptionPane.showMessageDialog(null, "Pre-Children doesn't have Level B");
                }else{
                    if(LAorB.getText().equals("B")){
                        JOptionPane.showMessageDialog(null, "Class is already Level B");
                    }else{
                        int Input = JOptionPane.showConfirmDialog(null, "Change Level "+AorB+" to B?", "Confirm Move", JOptionPane.YES_NO_OPTION);
                        if(Input==JOptionPane.YES_OPTION){
                            try{
                                String sql = "update tclass set AorB='B'  where Id='"+Class+"' ";
                                pst=conn.prepareStatement(sql);
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Class "+Class+"is now level B");

                                String Username= Luser.getText();
                                FormSiswa lf = new FormSiswa();
                                lf.setVisible(true);
                                this.setVisible(true);
                                lf.username(Username);
                                hide();

                            //UpdateTable(); 
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, e);
                                System.out.println("e "+ e);
                            }
                        }else{

                            }
                    }
                }
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_BToBActionPerformed

    
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
            java.util.logging.Logger.getLogger(FormLevelUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLevelUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLevelUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLevelUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLevelUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BLevelUp;
    private javax.swing.JButton BToB;
    private javax.swing.JComboBox<String> CBLevelUp;
    private javax.swing.JLabel LAorB;
    private javax.swing.JLabel LClass;
    private javax.swing.JLabel LId1;
    private javax.swing.JLabel Luser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
