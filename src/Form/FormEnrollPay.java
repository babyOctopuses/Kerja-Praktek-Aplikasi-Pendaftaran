package Form;

import java.sql.*;// this and javax.swing are all you need to build db connection
import javax.swing.*;
import DatabaseConnection.DbConnection;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.*;
import java.text.DateFormatSymbols;

public class FormEnrollPay extends javax.swing.JFrame {
    private String SQL;
    private String SQL2;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    Double Amount, LateFee, total;
    
    public FormEnrollPay() {
        initComponents();
        CurrentDate();
        conn = DbConnection.connect();
        HideButton();
        autonumber();
        autoNumberPayment();
        FillCombo();
        System.out.println(CheckDate("2020/04/30"));
    }

    public static boolean CheckDate(String InDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        
        try {
            dateFormat.parse(InDate.trim());
        }
        catch (ParseException e) {
          return false;
        }
        return true;
    }

    
     public void FillCombo(){
         try{
            String sql = "select ID from tclass where not id='Dis'";
            pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Id   = rs.getString("Id");
                System.out.println("Fill Combo Id:"+Id);
                CBClass.addItem(Id); //add the name from the string name to the combobox
            }
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //to use this method, remember that the combobox must have at least 1 default value in model or else it won't work
    public void setFee(){
        String Category= LCategory.getText();
        String Class = (String) CBClass.getSelectedItem();
        String tclass = "select * from tclass where Id=?" ;
        String tsettings = "select * from tsettings";
        String tcategory= "select * from tcategory where Status='"+Category+"'";
        
        if(Category.equals("Regular")){
            
            //set school and book fee 
            try{
                pst = conn.prepareStatement(tclass);
                pst.setString(1,Class);
                rs=pst.executeQuery();
                if(rs.next()){
                    String SchoolFee= rs.getString("Schoolfee");
                    String BookFee= rs.getString("Bookfee");
                    LSchoolFee.setText(SchoolFee);
                    LBookFee.setText(BookFee);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            // set report and registration fee
            try{
                pst = conn.prepareStatement(tsettings);
                rs=pst.executeQuery();

                if(rs.next()){
                    String ReportBook= rs.getString("ReportBook");
                    String Registration= rs.getString("Registration");
                    LRegisFee.setText(Registration);
                    LReportFee.setText(ReportBook);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            
        //for AdaSaudara category
        }else if(Category.equals("Ada Saudara")){
            // set report and registration fee
            try{
                pst = conn.prepareStatement(tcategory);
                rs=pst.executeQuery();

                if(rs.next()){
                    String ReportBook= rs.getString("ReportBook");
                    String Registration= rs.getString("Registration");
                    LRegisFee.setText(Registration);
                    LReportFee.setText(ReportBook);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            
            //set school and book fee 
            try{
                pst = conn.prepareStatement(tclass);
                pst.setString(1,Class);
                rs=pst.executeQuery();
                
                if(rs.next()){
                    String SchoolFee= rs.getString("SchoolFee");
                    String BookFee= rs.getString("BookFee");
                    LBookFee.setText(BookFee);                    
                    Double SchoolFee2= (Double) Double.parseDouble(SchoolFee);
                    System.out.println("SchoolFee="+SchoolFee2);
                    SchoolFee2 -= 5000;
                    LSchoolFee.setText(Double.toString(SchoolFee2));
                    
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }else{
            
            // set report and registration fee
            try{
                pst = conn.prepareStatement(tcategory);
                rs=pst.executeQuery();

                if(rs.next()){
                    String ReportBook= rs.getString("ReportBook");
                    String Registration= rs.getString("Registration");
                    LRegisFee.setText(Registration);
                    LReportFee.setText(ReportBook);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            
            //set school and book fee 
            try{
                pst = conn.prepareStatement(tcategory);
                rs=pst.executeQuery();
                if(rs.next()){
                    String SchoolFee= rs.getString("Schoolfee");
                    String BookFee= rs.getString("Bookfee");
                    LSchoolFee. setText(SchoolFee);
                    LBookFee.setText(BookFee);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        Calculate();
    }
    
    public void Calculate(){
        Double SchoolFee= (Double) Double.parseDouble(LSchoolFee.getText());
        Double RegisFee= (Double) Double.parseDouble(LRegisFee.getText());
        Double BookFee= (Double) Double.parseDouble(LBookFee.getText());
        Double ReportFee= (Double) Double.parseDouble(LReportFee.getText());
        total= SchoolFee + RegisFee + BookFee + ReportFee;
        System.out.println("3 "+total);
        LTotal.setText(Double.toString(total));
    }
     
    public void pass(String User, String Id, String Name, String Category, String Code ){
        
        LName.setText(Name);
        LStudentID.setText(Id);
        LCategory.setText(Category);
        LCode.setText(Code);
        LUser.setText(User);
        Luser.setText(User);
        String test=LCategory.getText();
               
}

public void HideButton(){
    BActivate.setEnabled(false); 
    BPrint.setEnabled(false); 
    BPay.setEnabled(false);
    BGenerateCard.setEnabled(false);
    BPrintCard.setEnabled(false);
    BUpload.setEnabled(false);
    BSavePhoto.setEnabled(false);
}    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        JMenu2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PStudents = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        PClasses = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        PRegister = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        PLogOut = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LTime1 = new javax.swing.JLabel();
        LDate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LCode = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        LCodeP = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LStudentID = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        LID1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LCategory = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CBClass = new javax.swing.JComboBox<>();
        LName = new javax.swing.JLabel();
        LMonth = new javax.swing.JLabel();
        LYear = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        LForDate = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        PReceipt = new javax.swing.JPanel();
        ResStudentName = new javax.swing.JLabel();
        ResClassName = new javax.swing.JLabel();
        ResTotal = new javax.swing.JLabel();
        ResPaymentID = new javax.swing.JLabel();
        ResDate = new javax.swing.JLabel();
        ResUser = new javax.swing.JLabel();
        ResGroup = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        LRUser = new javax.swing.JLabel();
        LRReportFee = new javax.swing.JLabel();
        LRStudentName = new javax.swing.JLabel();
        LRClassID = new javax.swing.JLabel();
        LRForMonthYear = new javax.swing.JLabel();
        LRGroup = new javax.swing.JLabel();
        LRBookFee = new javax.swing.JLabel();
        LRSchoolFee = new javax.swing.JLabel();
        LRTotal = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        ResStudentID = new javax.swing.JLabel();
        LRDate = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        LRRegisFee = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        LRPaymentID = new javax.swing.JLabel();
        LRStudentID = new javax.swing.JLabel();
        Background1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PCard = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        LCStudentID = new javax.swing.JLabel();
        LCName = new javax.swing.JLabel();
        LCClass = new javax.swing.JLabel();
        LCStartingDate = new javax.swing.JLabel();
        LCSchoolFee = new javax.swing.JLabel();
        LImage = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        BUpload = new javax.swing.JButton();
        BPrintCard = new javax.swing.JButton();
        BGenerateCard = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        TFImage = new javax.swing.JTextField();
        BSavePhoto = new javax.swing.JButton();
        BGenerateReceipt = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        LReportFee = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        LUser = new javax.swing.JLabel();
        LRegisFee = new javax.swing.JLabel();
        LBookFee = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        LTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        LGroup = new javax.swing.JLabel();
        LSchoolFee = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        BActivate = new javax.swing.JButton();
        BPrint = new javax.swing.JButton();
        BPay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JMenu2.setBackground(new java.awt.Color(51, 102, 255));
        JMenu2.setPreferredSize(new java.awt.Dimension(216, 755));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        PStudents.setBackground(new java.awt.Color(51, 102, 255));
        PStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PStudentsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PStudentsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PStudentsMouseExited(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Students");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout PStudentsLayout = new javax.swing.GroupLayout(PStudents);
        PStudents.setLayout(PStudentsLayout);
        PStudentsLayout.setHorizontalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStudentsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel18)
                .addGap(20, 20, 20)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PStudentsLayout.setVerticalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PTeacher.setBackground(new java.awt.Color(51, 102, 255));
        PTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PTeacherMouseExited(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Teachers");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Training_32px.png"))); // NOI18N

        javax.swing.GroupLayout PTeacherLayout = new javax.swing.GroupLayout(PTeacher);
        PTeacher.setLayout(PTeacherLayout);
        PTeacherLayout.setHorizontalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel20)
                .addGap(20, 20, 20)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PTeacherLayout.setVerticalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PClasses.setBackground(new java.awt.Color(51, 102, 255));
        PClasses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PClassesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PClassesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PClassesMouseExited(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Classes");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClassesLayout = new javax.swing.GroupLayout(PClasses);
        PClasses.setLayout(PClassesLayout);
        PClassesLayout.setHorizontalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel22)
                .addGap(20, 20, 20)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClassesLayout.setVerticalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PRegister.setBackground(new java.awt.Color(102, 204, 255));
        PRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PRegisterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PRegisterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PRegisterMouseExited(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Register");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PReport.setBackground(new java.awt.Color(51, 102, 255));
        PReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PReportMouseExited(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Report");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PLogOut.setBackground(new java.awt.Color(51, 102, 255));
        PLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PLogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PLogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PLogOutMouseExited(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Log Out");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBackground(new java.awt.Color(51, 102, 255));

        PCPass.setBackground(new java.awt.Color(51, 102, 255));
        PCPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PCPassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PCPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PCPassMouseExited(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel32.setText("Change Password");

        javax.swing.GroupLayout PCPassLayout = new javax.swing.GroupLayout(PCPass);
        PCPass.setLayout(PCPassLayout);
        PCPassLayout.setHorizontalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPassLayout.setVerticalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome ,");

        jLabel33.setFont(new java.awt.Font("Poor Richard", 0, 22)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/ddtclogo2011.png"))); // NOI18N
        jLabel33.setBorder(null);

        Luser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Luser.setForeground(new java.awt.Color(255, 255, 255));
        Luser.setText("aaaaa");

        LTime1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime1.setForeground(new java.awt.Color(255, 255, 255));
        LTime1.setText("Time");

        LDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate.setForeground(new java.awt.Color(255, 255, 255));
        LDate.setText("Date");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel33)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(LDate)
                                .addGap(18, 18, 18)
                                .addComponent(LTime1)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDate)
                    .addComponent(LTime1))
                .addGap(10, 10, 10)
                .addComponent(jLabel33)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout JMenu2Layout = new javax.swing.GroupLayout(JMenu2);
        JMenu2.setLayout(JMenu2Layout);
        JMenu2Layout.setHorizontalGroup(
            JMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PStudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JMenu2Layout.setVerticalGroup(
            JMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMenu2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("For Month");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Class");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        LCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCode.setText("Code");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Category");

        LCodeP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCodeP.setText("CodeP");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Student's ID");

        LStudentID.setText("ID");

        LID.setText("ID");

        LID1.setText("-");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("For Year");

        jLabel5.setText("-");

        LCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCategory.setText("Category");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Payment ID");

        CBClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih:" }));
        CBClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBClassActionPerformed(evt);
            }
        });

        LName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LName.setText("Name");

        LMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LMonth.setText("Month");

        LYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LYear.setText("Year");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("For Date");

        LForDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LForDate.setText("For Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(6, 6, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LStudentID))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LCodeP)
                                .addGap(3, 3, 3)
                                .addComponent(LID1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LID))
                            .addComponent(LCategory)
                            .addComponent(LForDate)
                            .addComponent(LMonth)
                            .addComponent(LYear)
                            .addComponent(LName)
                            .addComponent(CBClass, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(LID)
                    .addComponent(LCodeP)
                    .addComponent(LID1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LStudentID)
                    .addComponent(LCode)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CBClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(LCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(LForDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(LYear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PRINT CARD");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        PReceipt.setBackground(new java.awt.Color(51, 102, 255));
        PReceipt.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)));
        PReceipt.setMinimumSize(new java.awt.Dimension(730, 280));
        PReceipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResStudentName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResStudentName.setText("Student Name");
        PReceipt.add(ResStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        ResClassName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResClassName.setText("Class Name");
        PReceipt.add(ResClassName, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        ResTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResTotal.setText("Total");
        PReceipt.add(ResTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        ResPaymentID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResPaymentID.setText("Payment ID");
        PReceipt.add(ResPaymentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        ResDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResDate.setText("Date");
        PReceipt.add(ResDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        ResUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResUser.setText("User");
        PReceipt.add(ResUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        ResGroup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResGroup.setText("Group");
        PReceipt.add(ResGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Student Name");
        PReceipt.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Class Name");
        PReceipt.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Total");
        PReceipt.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Payment ID");
        PReceipt.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Date");
        PReceipt.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("User");
        PReceipt.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Group");
        PReceipt.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel53.setText("Report Fee");
        PReceipt.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel54.setText("Student Name");
        PReceipt.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel55.setText("For Month/Year");
        PReceipt.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel56.setText("School Fee");
        PReceipt.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel57.setText("Group");
        PReceipt.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel60.setText("Class ID");
        PReceipt.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel61.setText("Book Fee");
        PReceipt.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel62.setText("Total");
        PReceipt.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        LRUser.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRUser.setText("User");
        PReceipt.add(LRUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, -1, -1));

        LRReportFee.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRReportFee.setText("Report Fee");
        PReceipt.add(LRReportFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, -1, -1));

        LRStudentName.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRStudentName.setText("Student Name");
        PReceipt.add(LRStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        LRClassID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRClassID.setText("Class ID");
        PReceipt.add(LRClassID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        LRForMonthYear.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRForMonthYear.setText("For Month/Year");
        PReceipt.add(LRForMonthYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        LRGroup.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRGroup.setText("Group");
        PReceipt.add(LRGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        LRBookFee.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRBookFee.setText("Book Fee");
        PReceipt.add(LRBookFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        LRSchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRSchoolFee.setText("School Fee");
        PReceipt.add(LRSchoolFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        LRTotal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRTotal.setText("Total");
        PReceipt.add(LRTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("Student ID");
        PReceipt.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        ResStudentID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResStudentID.setText("Student ID");
        PReceipt.add(ResStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        LRDate.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRDate.setText("Date");
        PReceipt.add(LRDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        jLabel63.setFont(new java.awt.Font("sansserif", 0, 9)); // NOI18N
        jLabel63.setText("e-mail :ddtc_edu@yahoo.com. ");
        PReceipt.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jLabel36.setText("Jl.BakarBatu no. 8 – 9 C.Telp : +62 771 23047.Tanjungpinang ( Kepri ) Indonesia 29112");
        PReceipt.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel59.setText("Regis Fee");
        PReceipt.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        LRRegisFee.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRRegisFee.setText("Regis Fee");
        PReceipt.add(LRRegisFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, -1));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel65.setText("Payment ID");
        PReceipt.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel66.setText("Student ID");
        PReceipt.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        LRPaymentID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRPaymentID.setText("Payment ID");
        PReceipt.add(LRPaymentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        LRStudentID.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRStudentID.setText("Student ID");
        PReceipt.add(LRStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        Background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/DD Watermark.png"))); // NOI18N
        Background1.setBorder(null);
        PReceipt.add(Background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        PCard.setBackground(new java.awt.Color(255, 255, 255));
        PCard.setBorder(null);
        PCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 102, 255));
        jLabel35.setText("Name");
        PCard.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 102, 255));
        jLabel37.setText("Class");
        PCard.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(51, 102, 255));
        jLabel38.setText("User");
        PCard.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel39.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 102, 255));
        jLabel39.setText("Student Id");
        PCard.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(51, 102, 255));
        jLabel40.setText("Starting Date");
        PCard.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 102, 255));
        jLabel41.setText("School Fee");
        PCard.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        LCStudentID.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LCStudentID.setForeground(new java.awt.Color(51, 102, 255));
        LCStudentID.setText("Student Id");
        PCard.add(LCStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        LCName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LCName.setForeground(new java.awt.Color(51, 102, 255));
        LCName.setText("Name");
        PCard.add(LCName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        LCClass.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LCClass.setForeground(new java.awt.Color(51, 102, 255));
        LCClass.setText("Class");
        PCard.add(LCClass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        LCStartingDate.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LCStartingDate.setForeground(new java.awt.Color(51, 102, 255));
        LCStartingDate.setText("Starting Date");
        PCard.add(LCStartingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        LCSchoolFee.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LCSchoolFee.setForeground(new java.awt.Color(51, 102, 255));
        LCSchoolFee.setText("School Fee");
        PCard.add(LCSchoolFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));
        PCard.add(LImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 110, 150));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/Payment.jpg"))); // NOI18N
        PCard.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 230));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        BUpload.setText("Upload Photo");
        BUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUploadActionPerformed(evt);
            }
        });

        BPrintCard.setText("Print Card");
        BPrintCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPrintCardActionPerformed(evt);
            }
        });

        BGenerateCard.setText("Generate Card");
        BGenerateCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGenerateCardActionPerformed(evt);
            }
        });

        jPanel11.setMaximumSize(new java.awt.Dimension(24, 40));
        jPanel11.setOpaque(false);

        TFImage.setMaximumSize(new java.awt.Dimension(12, 28));
        TFImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TFImage, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TFImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        BSavePhoto.setText("Save Photo");
        BSavePhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSavePhotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BGenerateCard, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(BPrintCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BSavePhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BUpload)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BSavePhoto)
                .addGap(66, 66, 66)
                .addComponent(BGenerateCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BPrintCard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BGenerateReceipt.setText("Generate Receipt");
        BGenerateReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGenerateReceiptActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        LReportFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LReportFee.setText("Report Fee");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Report Fee");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Book Fee");

        LUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LUser.setText("User");

        LRegisFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LRegisFee.setText("RegisFee");

        LBookFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LBookFee.setText("BookFee");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("School Fee");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Total");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Group");

        LTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTotal.setText("Total");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("User");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Regis Fee");

        LGroup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LGroup.setText("Enroll");

        LSchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LSchoolFee.setText("SchoolFee");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11))
                .addGap(65, 65, 65)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LSchoolFee)
                    .addComponent(LRegisFee)
                    .addComponent(LBookFee)
                    .addComponent(LReportFee)
                    .addComponent(LTotal)
                    .addComponent(LGroup)
                    .addComponent(LUser))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(LSchoolFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(LRegisFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBookFee)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(LReportFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(LTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(LGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(LUser))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(51, 102, 255));

        jLabel34.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("PAYMENT DATA");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel34)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(51, 102, 255));

        jLabel42.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("PRINT RECEIPT");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel42)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        BActivate.setBackground(new java.awt.Color(255, 255, 255));
        BActivate.setText("Activate");
        BActivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActivateActionPerformed(evt);
            }
        });

        BPrint.setBackground(new java.awt.Color(255, 255, 255));
        BPrint.setText("Print Receipt");
        BPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPrintActionPerformed(evt);
            }
        });

        BPay.setBackground(new java.awt.Color(255, 255, 255));
        BPay.setText("Pay");
        BPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BActivate, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(BActivate)
                .addGap(1, 1, 1)
                .addComponent(BPay)
                .addGap(1, 1, 1)
                .addComponent(BPrint)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BGenerateReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BGenerateReceipt)))
                .addGap(26, 26, 26)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jScrollPane2.setViewportView(jPanel3);

        jLayeredPane1.setLayer(JMenu2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(JMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
            .addComponent(JMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPrintActionPerformed
        try{
        Toolkit receipt= PReceipt.getToolkit();
        PrintJob printing = receipt.getPrintJob(this, null, null);
        Graphics g = printing.getGraphics();
        PReceipt.printAll(g);
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BPrintActionPerformed

    private void BGenerateReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGenerateReceiptActionPerformed
        try{
            String PaymentID= LID.getText();
            String StudentID= LStudentID.getText();
            String StudentName= LName.getText();
            String ClassName= (String) CBClass.getSelectedItem();
            String Group = LGroup.getText();
            String Total = LTotal.getText();
            String Amount = LSchoolFee.getText();
            String Date   = LDate.getText();
            String User   = LUser.getText();
            String ForMonth= LMonth.getText();
            String ForYear = LYear.getText();
            String RegisFee= LRegisFee.getText();
            String ReportFee= LReportFee.getText();
            String BookFee= LBookFee.getText();
            
            if(ClassName.equals("Pilih:")){
                JOptionPane.showMessageDialog(null, "Select Class");
            } else {
                // TODO add your handling code here:
                //student's
                LRPaymentID.setText(PaymentID);
                LRStudentID.setText(StudentID);
                LRStudentName.setText(StudentName);
                LRClassID.setText(ClassName);
                LRGroup.setText(Group);
                LRTotal.setText(Total);
                LRSchoolFee.setText(Amount);
                LRRegisFee.setText(RegisFee);
                LRBookFee.setText(BookFee);
                LRReportFee.setText(ReportFee);
                LRDate.setText(Date);
                LRUser.setText(User);
                LRForMonthYear.setText(ForMonth+"/"+ForYear);
                
                //receptionist
                ResPaymentID.setText(PaymentID);
                ResStudentID.setText(StudentID);
                ResStudentName.setText(StudentName);
                ResClassName.setText(ClassName);
                ResGroup.setText(Group);
                ResTotal.setText(Total);
                ResDate.setText(Date);
                ResUser.setText(User);
                
                BActivate.setEnabled(true);
            }
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_BGenerateReceiptActionPerformed

    private void BActivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActivateActionPerformed
        try{
            String Name= LName.getText();
            String ID=LStudentID.getText();
            String Class= (String) CBClass.getSelectedItem();
            String SDate= LDate.getText();
            String sql = "update tsiswa set Class='"+Class+"', StartingDate='"+SDate+"', Status='ACTIVE' where Id='"+ID+"'";
       
           pst=conn.prepareStatement(sql);
           pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
           System.out.println("1 "+pst.executeUpdate());
           System.out.println("2 "+pst);
           JOptionPane.showMessageDialog(null, "Class and Starting Date Saved\n"+Name+" is now an ACTIVE student");
           BPay.setEnabled(true);
           BActivate.setEnabled(false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_BActivateActionPerformed

    private void BPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPayActionPerformed
        try{
           String sql = "Insert into tschoolfee ( Id, StudentId, Name, Date, Groups, ForMonth, ForYear, ForClass, Amount, Description, UserLog, LateFee, Total, Category, Card ,Fordate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            //Id can be emptied. 
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql); 
            pst.setString(1, LID.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, LStudentID.getText());
            pst.setString(3, LName.getText());
            pst.setString(4, LDate.getText());
            pst.setString(5, LGroup.getText());
            pst.setString(6, LMonth.getText());
            pst.setString(7, LYear.getText());
            pst.setString(8, (String) CBClass.getSelectedItem());
            pst.setString(9, LTotal.getText());
            pst.setString(10, "1 Month School fee");
            pst.setString(11, LUser.getText());
            pst.setString(12, "0"); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(13, LTotal.getText());
            pst.setString(14, LCategory.getText());
            pst.setString(15, "0");
            pst.setString(16, LDate.getText());
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            BPrint.setEnabled(true);
            BGenerateCard.setEnabled(true);
            BPay.setEnabled(false);
            BUpload.setEnabled(true);
            
            //ShowData();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
    }//GEN-LAST:event_BPayActionPerformed

    private void PStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseClicked
        String Username=Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PStudentsMouseClicked

    private void PStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseEntered
        PStudents.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PStudentsMouseEntered

    private void PStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseExited
        PStudents.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PStudentsMouseExited

    private void PTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseClicked
        String Username=Luser.getText();
        FormTeacher lf = new FormTeacher();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PTeacherMouseClicked

    private void PTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseEntered
        PTeacher.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PTeacherMouseEntered

    private void PTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseExited
        PTeacher.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PTeacherMouseExited

    private void PClassesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseClicked
        String Username=Luser.getText();
        FormClass lf = new FormClass();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PClassesMouseClicked

    private void PClassesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseEntered
        PClasses.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseEntered

    private void PClassesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseExited
        PClasses.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseExited

    private void PRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseClicked
        String Username=Luser.getText();
        FormRegister lf = new FormRegister();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PRegisterMouseClicked

    private void PRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseEntered

    }//GEN-LAST:event_PRegisterMouseEntered

    private void PRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseExited

    }//GEN-LAST:event_PRegisterMouseExited

    private void PReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseClicked
        String Username=Luser.getText();
        FormChart lf = new FormChart();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PReportMouseClicked

    private void PReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseEntered
        PReport.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PReportMouseEntered

    private void PReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseExited
        PReport.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PReportMouseExited

    private void PLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseClicked
        new FormLogin().setVisible(true);
        hide();
    }//GEN-LAST:event_PLogOutMouseClicked

    private void PLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseEntered
        PLogOut.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PLogOutMouseEntered

    private void PLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseExited
        PLogOut.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PLogOutMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String User= LUser.getText();
        //use OOP to pass variable
        FormRegister lf = new FormRegister();
        lf.setVisible(true);
        this.setVisible(true);
        hide();
        lf.username(User);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CBClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBClassActionPerformed
        setFee();
    }//GEN-LAST:event_CBClassActionPerformed

    private void PCPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseClicked
        String Username=Luser.getText();
        FormChangePassword lf = new FormChangePassword();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PCPassMouseClicked

    private void PCPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseEntered
        PCPass.setBackground(new java.awt.Color(1,51,153));
    }//GEN-LAST:event_PCPassMouseEntered

    private void PCPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseExited
        PCPass.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PCPassMouseExited

    private void BGenerateCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGenerateCardActionPerformed
        try{
            String PaymentID= LID.getText();
            String StudentID= LStudentID.getText();
            String StudentName= LName.getText();
            String ClassName= (String) CBClass.getSelectedItem();
            String SchoolFee = LSchoolFee.getText();
            String Date   = LDate.getText();
                //student's
                LCStudentID.setText(StudentID);
                LCName.setText(StudentName);
                LCClass.setText(ClassName);
                LCStartingDate.setText(Date);
                LCSchoolFee.setText(SchoolFee);
            
                BPrintCard.setEnabled(true);
                BGenerateCard.setEnabled(false);
            
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_BGenerateCardActionPerformed

    private void BPrintCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPrintCardActionPerformed
        try{
        Toolkit receipt= PReceipt.getToolkit();
        PrintJob printing = receipt.getPrintJob(this, null, null);
        Graphics g = printing.getGraphics();
        PCard.printAll(g);
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_BPrintCardActionPerformed

    private void BUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUploadActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog (null);
        File f = chooser.getSelectedFile();
        String filename= f.getAbsolutePath();
        TFImage.setText(filename);
        //Image getAbsolutePath= null;
        ImageIcon icon = new ImageIcon(filename);
        Image image = icon.getImage().getScaledInstance(LImage.getWidth(), LImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(image);
        LImage.setIcon(scaleIcon);
        BSavePhoto.setEnabled(true);
    }//GEN-LAST:event_BUploadActionPerformed

    private void TFImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFImageActionPerformed

    private void BSavePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSavePhotoActionPerformed
        try{
           
           // to get text as value and change the data in sql
           String value1= LStudentID.getText();
           String value13= TFImage.getText();
           value13= value13.replace("\\","\\\\");
           String sql = "update tsiswa set Image='"+value13+"' where Id= '"+value1+"'";
           pst=conn.prepareStatement(sql);
           pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
           JOptionPane.showMessageDialog(null, "Updated");
        
        }catch(Exception e){
        
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
           
       }
    }//GEN-LAST:event_BSavePhotoActionPerformed
    
    public void autonumber(){
        try{    
            String sql="select Id from tsiswa order by "+"Id desc";
            pst= conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String kode = rs.getString("Id");
            System.out.println("kode "+ kode);
            String AN= "" + (Integer.parseInt(kode)+1);
            String Nol ="";
            if(AN.length()==1){
                Nol = "000";
            }
            else if(AN.length()==2){
                Nol = "00";
            }
            else if(AN.length()==3){
                Nol = "0";
            }
            LCode.setText("S"+ Nol);
            LStudentID.setText(AN);
            }
        }catch(Exception e){
        
        }

    }
    
    public void CurrentDate(){
    //programming language #13 adding date and time but not only static time
    Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    //MenuDate.setText(year+"/"+(month+1)+"/"+day);
    
    int second = cal.get(Calendar.SECOND);
    int minute = cal.get(Calendar.MINUTE);
    int hour = cal.get(Calendar.HOUR);
    
    String monthString = new DateFormatSymbols().getMonths()[month];
  
    Thread clock = new Thread(){
    public void run(){
    for(;;){
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
    //MenuDate.setText(year+"/"+(month+1)+"/"+day);
    
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
    
    //MenuTime.setText(hour+":"+minute+":"+second);
    LMonth.setText(""+(month+1));
    LYear.setText(""+year);
    LDate.setText(year+"-"+(month+1)+"-"+day);
    LForDate.setText(year+"-"+(month+1)+"-"+day);
    LTime1.setText(hour+":"+minute+":"+second);
        try{
        sleep(1000);
        }catch(Exception e){        
        }
    }
    }
    
    //TFMonth.setText(month);
    };
    clock.start();
    
}
     public void autoNumberPayment(){
        try{
         String sql="select Id from tschoolfee order by "+"Id desc";
            pst= conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
            String kode = rs.getString("Id");
            System.out.println("kode "+ kode);
            String AN= "" + (Integer.parseInt(kode)+1);
            String Nol ="";
            if(AN.length()==1){
                Nol = "0000";
            }
            else if(AN.length()==2){
                Nol = "000";
            }
            else if(AN.length()==3){
                Nol = "00";
            }
            LCodeP.setText("P"+ Nol);
            LID.setText(AN);
            }
        }catch(Exception e){
            
        }
     }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormEnrollPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEnrollPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEnrollPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEnrollPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEnrollPay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActivate;
    private javax.swing.JButton BGenerateCard;
    private javax.swing.JButton BGenerateReceipt;
    private javax.swing.JButton BPay;
    private javax.swing.JButton BPrint;
    private javax.swing.JButton BPrintCard;
    private javax.swing.JButton BSavePhoto;
    private javax.swing.JButton BUpload;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Background1;
    private javax.swing.JComboBox<String> CBClass;
    private javax.swing.JPanel JMenu2;
    private javax.swing.JLabel LBookFee;
    private javax.swing.JLabel LCClass;
    private javax.swing.JLabel LCName;
    private javax.swing.JLabel LCSchoolFee;
    private javax.swing.JLabel LCStartingDate;
    private javax.swing.JLabel LCStudentID;
    private javax.swing.JLabel LCategory;
    private javax.swing.JLabel LCode;
    private javax.swing.JLabel LCodeP;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LForDate;
    private javax.swing.JLabel LGroup;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LID1;
    private javax.swing.JLabel LImage;
    private javax.swing.JLabel LMonth;
    private javax.swing.JLabel LName;
    private javax.swing.JLabel LRBookFee;
    private javax.swing.JLabel LRClassID;
    private javax.swing.JLabel LRDate;
    private javax.swing.JLabel LRForMonthYear;
    private javax.swing.JLabel LRGroup;
    private javax.swing.JLabel LRPaymentID;
    private javax.swing.JLabel LRRegisFee;
    private javax.swing.JLabel LRReportFee;
    private javax.swing.JLabel LRSchoolFee;
    private javax.swing.JLabel LRStudentID;
    private javax.swing.JLabel LRStudentName;
    private javax.swing.JLabel LRTotal;
    private javax.swing.JLabel LRUser;
    private javax.swing.JLabel LRegisFee;
    private javax.swing.JLabel LReportFee;
    private javax.swing.JLabel LSchoolFee;
    private javax.swing.JLabel LStudentID;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel LTotal;
    private javax.swing.JLabel LUser;
    private javax.swing.JLabel LYear;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PCard;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PReceipt;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JLabel ResClassName;
    private javax.swing.JLabel ResDate;
    private javax.swing.JLabel ResGroup;
    private javax.swing.JLabel ResPaymentID;
    private javax.swing.JLabel ResStudentID;
    private javax.swing.JLabel ResStudentName;
    private javax.swing.JLabel ResTotal;
    private javax.swing.JLabel ResUser;
    private javax.swing.JTextField TFImage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    void pass(com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type String) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
