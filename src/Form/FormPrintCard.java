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
import java.time.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

public class FormPrintCard extends javax.swing.JFrame {
    private String SQL;
    private String SQL2;
    //private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    Double Amount, LateFee, total;
    
    public FormPrintCard() {
        initComponents();
        CurrentDate();
        conn = DbConnection.connect();
        HideButton();
        autonumber();
        autoNumberPayment();
    }

    public void SetImage(String Images){
        ImageIcon icon = new ImageIcon(Images);
        Image image = icon.getImage().getScaledInstance(LImage.getWidth(), LImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(image);
        LImage.setIcon(scaleIcon);
    }
    public void SetCardFee(String Class, String Category){
         //Regular Category takes from tcategory
        if(Category.equals("Regular")){
            String sqld = "select * from tsettings";
            try{
            pst = conn.prepareStatement(sqld);
            rs=pst.executeQuery();
            
            if(rs.next()){
                String CardFee= rs.getString("Card");
                LCardFee.setText(CardFee);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
            
        }else{
            //Non regular Category takes from tcategory schoolfee    
            try{
                    String sql="select Card from tcategory where Status='"+Category+"'";
                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    if(rs.next()){
                        String CardFee=rs.getString("Card");
                        LCardFee.setText(CardFee);
                    }
                }catch(Exception e){
                    System.out.println("error: "+e);
                }
            }
     }
     
    public void SetFee(String Class,String Category){
        //Regular Category takes from tclass school fee
        if(Category.equals("Regular")){
            try{
                String sql="select SchoolFee from tclass where Id='"+Class+"'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                System.out.println("rs setFee: "+rs);
                if(rs.next()){
                    String SchoolFee=rs.getString("SchoolFee");
                    LSchoolFee.setText(SchoolFee);
                }
            }catch(Exception e){
                System.out.println("error: "+e);
            }
            // AdaSaudara Category deducts 5k from the schoolfee
        }else if(Category.equals("Ada Saudara")){
            try{
                String sql="select tclass.SchoolFee, tcategory.Deduction from tclass inner join tcategory where tclass.Id= '"+Class+"' and tcategory.Status='Ada Saudara'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                System.out.println("rs setFee: "+rs);
                if(rs.next()){
                    String SchoolFee=rs.getString("tclass.SchoolFee");
                    String Deduction=rs.getString("tcategory.Deduction");
                    Double SchoolFee2= (Double) Double.parseDouble(SchoolFee);
                    Double Deduction2= (Double) Double.parseDouble(Deduction);
                    SchoolFee2 = SchoolFee2-Deduction2;
                    LSchoolFee.setText(Double.toString(SchoolFee2));
                }
            }catch(Exception e){
                System.out.println("error ini: "+e);
            }
        }else{
            //Non regular Category takes from tcategory school fee    
            try{
                    String sql="select SchoolFee from tcategory where Status='"+Category+"'";
                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    if(rs.next()){
                        String SchoolFee=rs.getString("SchoolFee");
                        LSchoolFee.setText(SchoolFee);
                    }
                }catch(Exception e){
                    System.out.println("error: "+e);
                }
            }
    } 
    
    public void username(String User, String StartingDate, String Id,String Name, String Class, String Category, String Image){
        Luser.setText(User);
        LUser.setText(User);
        LName.setText(Name);
        LStudentID.setText(Id);
        LCategory.setText(Category);
        LSDate.setText(StartingDate);
        LClass.setText(Class);
        SetFee(Class, Category);
        SetCardFee(Class, Category);
        SetImage(Image);
    }
    
public void HideButton(){
    BPrintCard.setEnabled(false); 
    BPrint.setEnabled(false);
    BPay.setEnabled(false);
    BGenerateCard.setEnabled(false);
    BUpload.setEnabled(false);
    TFImage.setEnabled(false);
}    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        PClasses = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        PRegister = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LTime1 = new javax.swing.JLabel();
        LDate1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LCode = new javax.swing.JLabel();
        LCategory = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LUser = new javax.swing.JLabel();
        LSchoolFee = new javax.swing.JLabel();
        LStudentID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LCodeP = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        LID1 = new javax.swing.JLabel();
        LClass = new javax.swing.JLabel();
        LName = new javax.swing.JLabel();
        LCardFee = new javax.swing.JLabel();
        LGroup = new javax.swing.JLabel();
        BGenerateReceipt = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        LSDate = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        TFUsername = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        TFPassword = new javax.swing.JPasswordField();
        BValidate = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        BPay = new javax.swing.JButton();
        BPrint = new javax.swing.JButton();
        PPrint = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        PCard = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        LCStudentID = new javax.swing.JLabel();
        LCName = new javax.swing.JLabel();
        LCClass = new javax.swing.JLabel();
        LCStartingDate = new javax.swing.JLabel();
        LCSchoolFee = new javax.swing.JLabel();
        LImage = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        BUpload = new javax.swing.JButton();
        BPrintCard = new javax.swing.JButton();
        BGenerateCard = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        TFImage = new javax.swing.JTextField();
        print1 = new javax.swing.JPanel();
        PReceipt1 = new javax.swing.JPanel();
        ResStudentName1 = new javax.swing.JLabel();
        ResClassName1 = new javax.swing.JLabel();
        ResTotal1 = new javax.swing.JLabel();
        ResPaymentID1 = new javax.swing.JLabel();
        ResDate1 = new javax.swing.JLabel();
        ResUser1 = new javax.swing.JLabel();
        ResGroup1 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        LRUser1 = new javax.swing.JLabel();
        LRStudentName1 = new javax.swing.JLabel();
        LRClassID1 = new javax.swing.JLabel();
        LRGroup1 = new javax.swing.JLabel();
        LRCardFee = new javax.swing.JLabel();
        LRTotal1 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        ResStudentID1 = new javax.swing.JLabel();
        LRDate1 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        LRPaymentID1 = new javax.swing.JLabel();
        LRStudentID1 = new javax.swing.JLabel();
        Background2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel.setBackground(new java.awt.Color(51, 102, 255));
        MenuPanel.setPreferredSize(new java.awt.Dimension(216, 780));

        jPanel7.setBackground(new java.awt.Color(102, 204, 255));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Students");

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel36)
                .addGap(20, 20, 20)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Teachers");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Training_32px.png"))); // NOI18N

        javax.swing.GroupLayout PTeacherLayout = new javax.swing.GroupLayout(PTeacher);
        PTeacher.setLayout(PTeacherLayout);
        PTeacherLayout.setHorizontalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel38)
                .addGap(20, 20, 20)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PTeacherLayout.setVerticalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Classes");

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClassesLayout = new javax.swing.GroupLayout(PClasses);
        PClasses.setLayout(PClassesLayout);
        PClassesLayout.setHorizontalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel40)
                .addGap(20, 20, 20)
                .addComponent(jLabel39)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClassesLayout.setVerticalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PRegister.setBackground(new java.awt.Color(51, 102, 255));
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

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Register");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
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

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Log Out");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Report");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(51, 102, 255));

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

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel18.setText("Change Password");

        javax.swing.GroupLayout PCPassLayout = new javax.swing.GroupLayout(PCPass);
        PCPass.setLayout(PCPassLayout);
        PCPassLayout.setHorizontalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPassLayout.setVerticalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Welcome ,");

        jLabel24.setFont(new java.awt.Font("Poor Richard", 0, 22)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/ddtclogo2011.png"))); // NOI18N
        jLabel24.setBorder(null);

        Luser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Luser.setForeground(new java.awt.Color(255, 255, 255));
        Luser.setText("aaaaa");

        LTime1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime1.setForeground(new java.awt.Color(255, 255, 255));
        LTime1.setText("Time");

        LDate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate1.setForeground(new java.awt.Color(255, 255, 255));
        LDate1.setText("Date");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel24)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(LDate1)
                                .addGap(18, 18, 18)
                                .addComponent(LTime1)))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDate1)
                    .addComponent(LTime1))
                .addGap(10, 10, 10)
                .addComponent(jLabel24)
                .addGap(28, 28, 28)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 132, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Category");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("School Fee");

        LCode.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LCode.setText("Code");

        LCategory.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LCategory.setText("Category");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Card Fee");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        LUser.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LUser.setText("User");

        LSchoolFee.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LSchoolFee.setText("School Fee");

        LStudentID.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LStudentID.setText("ID");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("-");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Class");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Student's ID");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Group");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("User");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Payment ID");

        LCodeP.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LCodeP.setText("CodeP");

        LID.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LID.setText("ID");

        LID1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LID1.setText("-");

        LClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LClass.setText("Class");

        LName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LName.setText("Name");

        LCardFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCardFee.setText("Card Fee");

        LGroup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LGroup.setText("C-Fee");

        BGenerateReceipt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BGenerateReceipt.setText("Generate Receipt");
        BGenerateReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGenerateReceiptActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Starting Date");

        LSDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LSDate.setText("Starting Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BGenerateReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel15)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LSDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LSchoolFee)
                            .addComponent(LUser)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(LCodeP)
                                .addGap(3, 3, 3)
                                .addComponent(LID1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LID))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(LCode)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(LStudentID))
                            .addComponent(LCategory)
                            .addComponent(LClass)
                            .addComponent(LName)
                            .addComponent(LCardFee)
                            .addComponent(LGroup))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(LID)
                    .addComponent(LCodeP)
                    .addComponent(LID1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LStudentID)
                    .addComponent(LCode)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(LCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(LGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(LCardFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(LSchoolFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(LUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(LSDate))
                .addGap(51, 51, 51)
                .addComponent(BGenerateReceipt)
                .addGap(7, 7, 7))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PAYMENT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setText("Username");

        jLabel29.setText("Password");

        BValidate.setText("Unlock Generate Card Button");
        BValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BValidateActionPerformed(evt);
            }
        });

        jLabel31.setText("To reprint the card without making payment, enter admin's :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(15, 15, 15)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFUsername)
                            .addComponent(TFPassword)))
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BValidate)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TFUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(TFPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BValidate)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PRINT CARD");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18))
        );

        BPay.setText("Pay");
        BPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPayActionPerformed(evt);
            }
        });

        BPrint.setText("Print Receipt");
        BPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPrintActionPerformed(evt);
            }
        });

        PPrint.setBackground(new java.awt.Color(255, 255, 255));
        PPrint.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        PCard.setBackground(new java.awt.Color(255, 255, 255));
        PCard.setBorder(null);
        PCard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 102, 255));
        jLabel35.setText("Name");
        PCard.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        jLabel86.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(51, 102, 255));
        jLabel86.setText("Class");
        PCard.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel87.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(51, 102, 255));
        jLabel87.setText("User");
        PCard.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        jLabel88.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(51, 102, 255));
        jLabel88.setText("Student Id");
        PCard.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        jLabel89.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(51, 102, 255));
        jLabel89.setText("Starting Date");
        PCard.add(jLabel89, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        jLabel90.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(51, 102, 255));
        jLabel90.setText("School Fee");
        PCard.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

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

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout PPrintLayout = new javax.swing.GroupLayout(PPrint);
        PPrint.setLayout(PPrintLayout);
        PPrintLayout.setHorizontalGroup(
            PPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PPrintLayout.setVerticalGroup(
            PPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

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

        jPanel15.setMaximumSize(new java.awt.Dimension(24, 40));
        jPanel15.setOpaque(false);

        TFImage.setMaximumSize(new java.awt.Dimension(12, 28));
        TFImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TFImage, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TFImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BGenerateCard, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(BPrintCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BUpload)
                .addGap(37, 37, 37)
                .addComponent(BGenerateCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BPrintCard)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        print1.setBackground(new java.awt.Color(255, 255, 255));
        print1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        PReceipt1.setBackground(new java.awt.Color(51, 102, 255));
        PReceipt1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)));
        PReceipt1.setMinimumSize(new java.awt.Dimension(730, 280));
        PReceipt1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResStudentName1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResStudentName1.setText("Student Name");
        PReceipt1.add(ResStudentName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, -1));

        ResClassName1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResClassName1.setText("Class Name");
        PReceipt1.add(ResClassName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        ResTotal1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResTotal1.setText("Total");
        PReceipt1.add(ResTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        ResPaymentID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResPaymentID1.setText("Payment ID");
        PReceipt1.add(ResPaymentID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, -1, -1));

        ResDate1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResDate1.setText("Date");
        PReceipt1.add(ResDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, -1, -1));

        ResUser1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResUser1.setText("User");
        PReceipt1.add(ResUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        ResGroup1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResGroup1.setText("Group");
        PReceipt1.add(ResGroup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, -1, -1));

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Student Name");
        PReceipt1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setText("Class Name");
        PReceipt1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setText("Total");
        PReceipt1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setText("Payment ID");
        PReceipt1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setText("Date");
        PReceipt1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setText("User");
        PReceipt1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Group");
        PReceipt1.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel73.setText("Student Name");
        PReceipt1.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel76.setText("Group");
        PReceipt1.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel77.setText("Class ID");
        PReceipt1.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel78.setText("Card Fee");
        PReceipt1.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel79.setText("Total");
        PReceipt1.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, -1));

        LRUser1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRUser1.setText("User");
        PReceipt1.add(LRUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, -1, -1));

        LRStudentName1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRStudentName1.setText("Student Name");
        PReceipt1.add(LRStudentName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        LRClassID1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRClassID1.setText("Class ID");
        PReceipt1.add(LRClassID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, -1, -1));

        LRGroup1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRGroup1.setText("Group");
        PReceipt1.add(LRGroup1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        LRCardFee.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRCardFee.setText("Card Fee");
        PReceipt1.add(LRCardFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        LRTotal1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRTotal1.setText("Total");
        PReceipt1.add(LRTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, -1, -1));

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText("Student ID");
        PReceipt1.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        ResStudentID1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ResStudentID1.setText("Student ID");
        PReceipt1.add(ResStudentID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, -1, -1));

        LRDate1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRDate1.setText("Date");
        PReceipt1.add(LRDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, -1, -1));

        jLabel81.setFont(new java.awt.Font("sansserif", 0, 9)); // NOI18N
        jLabel81.setText("e-mail :ddtc_edu@yahoo.com. ");
        PReceipt1.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jLabel82.setText("Jl.BakarBatu no. 8 – 9 C.Telp : +62 771 23047.Tanjungpinang ( Kepri ) Indonesia 29112");
        PReceipt1.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel84.setText("Payment ID");
        PReceipt1.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel85.setText("Student ID");
        PReceipt1.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        LRPaymentID1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRPaymentID1.setText("Payment ID");
        PReceipt1.add(LRPaymentID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        LRStudentID1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        LRStudentID1.setText("Student ID");
        PReceipt1.add(LRStudentID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        Background2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/DD Watermark.png"))); // NOI18N
        Background2.setBorder(null);
        PReceipt1.add(Background2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout print1Layout = new javax.swing.GroupLayout(print1);
        print1.setLayout(print1Layout);
        print1Layout.setHorizontalGroup(
            print1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        print1Layout.setVerticalGroup(
            print1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(51, 102, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("REPRINT");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(PPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(print1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(BPay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BPrint))))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(print1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BPay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PPrint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addGap(20, 20, 20)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MenuPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BGenerateReceiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGenerateReceiptActionPerformed
    try{
            String PaymentID= LID.getText();
            String StudentID= LStudentID.getText();
            String StudentName= LName.getText();
            String ClassName= LClass.getText();
            String Group = LGroup.getText();
            String User   = LUser.getText();
            String CardFee = LCardFee.getText();
            String Date = LDate1.getText(); 
            
            // TODO add your handling code here:
            //student's
            LRPaymentID1.setText(PaymentID);
            LRStudentID1.setText(StudentID);
            LRStudentName1.setText(StudentName);
            LRClassID1.setText(ClassName);
            LRGroup1.setText(Group);
            LRCardFee.setText(CardFee);
            LRTotal1.setText(CardFee);
            LRDate1.setText(Date);
            LRUser1.setText(User);
                
                //receptionist
                ResPaymentID1.setText(PaymentID);
                ResStudentID1.setText(StudentID);
                ResStudentName1.setText(StudentName);
                ResClassName1.setText(ClassName);
                ResGroup1.setText(Group);
                ResDate1.setText(Date);
                ResTotal1.setText(CardFee);
                ResUser1.setText(User);
                
                BPay.setEnabled(true);
            
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    
    }//GEN-LAST:event_BGenerateReceiptActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String Username=LUser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        String Username=Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_jPanel7MouseClicked

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
        PRegister.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PRegisterMouseEntered

    private void PRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseExited
        PRegister.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PRegisterMouseExited

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

    private void BValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BValidateActionPerformed
        String sql= "select * from tuser where Username=? and Password=? and status='admin'";
        //F1= new String(TFUsername.getText());
        
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,TFUsername.getText());
            pst.setString(2,TFPassword.getText());
            rs=pst.executeQuery();
            
            if(rs.next()){
                BGenerateCard.setEnabled(true);
            }else {
                JOptionPane.showMessageDialog(null, "Username and Password are incorect");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_BValidateActionPerformed

    private void BPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPayActionPerformed
        try{
            String sql = "Insert into tschoolfee ( Id, StudentId, Name, Groups, ForClass, Amount, Description, UserLog, Total, Category, Card) values (?,?,?,?,?,?,?,?,?,?,?)";
            //Id can be emptied.
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql);
            pst.setString(1, LID.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, LStudentID.getText());
            pst.setString(3, LName.getText());
            pst.setString(4, LGroup.getText());
            pst.setString(5, (String) LClass.getText());
            pst.setString(6, LSchoolFee.getText());
            pst.setString(7, "School fee + Card Fee");
            pst.setString(8, LUser.getText());
            pst.setString(9, LCardFee.getText());
            pst.setString(10, LCategory.getText());
            pst.setString(11, LCardFee.getText());
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            BPrint.setEnabled(true);
            BGenerateCard.setEnabled(true);
            BPay.setEnabled(false);
            BUpload.setEnabled(true);
            TFImage.setEnabled(true);
            //ShowData();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BPayActionPerformed

    private void BPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPrintActionPerformed
       try{
        Toolkit receipt= PReceipt1.getToolkit();
        PrintJob printing = receipt.getPrintJob(this, null, null);
        Graphics g = printing.getGraphics();
        PReceipt1.printAll(g);
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }    // TODO add your handling code here:
    }//GEN-LAST:event_BPrintActionPerformed

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
    }//GEN-LAST:event_BUploadActionPerformed

    private void BPrintCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPrintCardActionPerformed
        try{
            Toolkit receipt= PCard.getToolkit();
            PrintJob printing = receipt.getPrintJob(this, null, null);
            Graphics g = printing.getGraphics();
            PCard.printAll(g);
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_BPrintCardActionPerformed

    private void BGenerateCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGenerateCardActionPerformed
        try{
            String PaymentID= LID.getText();
            String StudentID= LStudentID.getText();
            String StudentName= LName.getText();
            String ClassName= (String) LClass.getText();
            String SchoolFee = LSchoolFee.getText();
            String StartingDate   = LSDate.getText();
            //student's
            LCStudentID.setText(StudentID);
            LCName.setText(StudentName);
            LCClass.setText(ClassName);
            LCStartingDate.setText(StartingDate);
            LCSchoolFee.setText(SchoolFee);

            BPrintCard.setEnabled(true);
            BGenerateCard.setEnabled(false);

        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    }//GEN-LAST:event_BGenerateCardActionPerformed

    private void TFImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFImageActionPerformed
    
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
        int month1= month+1;
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
    //MenuDate.setText(year+"/"+(month+1)+"/"+day);
    
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
    
    //MenuTime.setText(hour+":"+minute+":"+second);
    LDate1.setText(year+"-"+(month+1)+"-"+day);
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
            java.util.logging.Logger.getLogger(FormPrintCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrintCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrintCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrintCard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrintCard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BGenerateCard;
    private javax.swing.JButton BGenerateReceipt;
    private javax.swing.JButton BPay;
    private javax.swing.JButton BPrint;
    private javax.swing.JButton BPrintCard;
    private javax.swing.JButton BUpload;
    private javax.swing.JButton BValidate;
    private javax.swing.JLabel Background;
    private javax.swing.JLabel Background2;
    private javax.swing.JLabel LCClass;
    private javax.swing.JLabel LCName;
    private javax.swing.JLabel LCSchoolFee;
    private javax.swing.JLabel LCStartingDate;
    private javax.swing.JLabel LCStudentID;
    private javax.swing.JLabel LCardFee;
    private javax.swing.JLabel LCategory;
    private javax.swing.JLabel LClass;
    private javax.swing.JLabel LCode;
    private javax.swing.JLabel LCodeP;
    private javax.swing.JLabel LDate1;
    private javax.swing.JLabel LGroup;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LID1;
    private javax.swing.JLabel LImage;
    private javax.swing.JLabel LName;
    private javax.swing.JLabel LRCardFee;
    private javax.swing.JLabel LRClassID1;
    private javax.swing.JLabel LRDate1;
    private javax.swing.JLabel LRGroup1;
    private javax.swing.JLabel LRPaymentID1;
    private javax.swing.JLabel LRStudentID1;
    private javax.swing.JLabel LRStudentName1;
    private javax.swing.JLabel LRTotal1;
    private javax.swing.JLabel LRUser1;
    private javax.swing.JLabel LSDate;
    private javax.swing.JLabel LSchoolFee;
    private javax.swing.JLabel LStudentID;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel LUser;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PCard;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PPrint;
    private javax.swing.JPanel PReceipt1;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JLabel ResClassName1;
    private javax.swing.JLabel ResDate1;
    private javax.swing.JLabel ResGroup1;
    private javax.swing.JLabel ResPaymentID1;
    private javax.swing.JLabel ResStudentID1;
    private javax.swing.JLabel ResStudentName1;
    private javax.swing.JLabel ResTotal1;
    private javax.swing.JLabel ResUser1;
    private javax.swing.JTextField TFImage;
    private javax.swing.JPasswordField TFPassword;
    private javax.swing.JTextField TFUsername;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel print1;
    // End of variables declaration//GEN-END:variables

    void pass(com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type String) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
