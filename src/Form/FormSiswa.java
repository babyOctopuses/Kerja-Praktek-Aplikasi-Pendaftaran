package Form;

import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

public class FormSiswa extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private DefaultTableModel DftTblModelSfee;
    private String SQL;
    private String SQL2;
    //private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    
    public FormSiswa() {
        initComponents();
        hidePanel();
        ShowData();
        ShowDataSchoolFee();
        conn= DbConnection.connect();
        CurrentDate();
//setUndecorated(true);
        
    }

    public void hidePanel(){
        PSiswa.setVisible(true); //setVisible to set the STUDENTS PANEL invisible at the beginning
        PMenuSfee.setVisible(true); //setVisible to set the SCHOOLFEE PANEL invisible at the beginning
        PQuit.setVisible(true); //setVisible to set QUIT the table invisible at the beginning
        if(TFSearch.getText().equals("")){
        PSiswa.hide();
        PSfee.hide();
        PMenuSfee.setVisible(true);
        TSfee.setVisible(true);
        PQuit.hide();
        PTotal.hide();  
        }else{
            
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
    
    LDate1.setText(year+"-"+(month+1)+"-"+day);
    LTime2.setText(hour+":"+minute+":"+second);
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
    
    public void CheckUserStatus(String User){
        try{
            String sql= "select status from tuser where username='"+User+"'";
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Status= rs.getString("status"); // insert count(Id) to get the total of the value
                if(Status.equals("Admin")){
                
                }else{
                //JOptionPane.showMessageDialog(null, User);
            }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void pass(String User, String Class){
        Luser.setText(User);
        TFSearch.setText(Class);
    }
    public void username(String User){
        Luser.setText(User);
        CheckUserStatus(User);
        
    }
    
    public void showPanel(){
        PSfee.setVisible(true);
        PTotal.setVisible(true);
    }
    
    public void Count() {
        try{
            String sql= "select count(Id) from tsiswa where class=? and Status='Active'" ;
            pst= conn.prepareStatement(sql);
            pst.setString(1, TFSearch.getText());
            rs= pst.executeQuery();
            if(rs.next()){
                String add1= rs.getString("count(Id)"); // insert count(Id) to get the total of the value
                System.out.println(add1);
                
                LTotalStudents2.setText(add1);
            
            }
        }catch(Exception e){}
                
    }
    
    public void ShowData(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Name");
            DftTblModelSiswa.addColumn("Gender");
            DftTblModelSiswa.addColumn("Phone");
            DftTblModelSiswa.addColumn("Address");
            DftTblModelSiswa.addColumn("Starting Date");
            DftTblModelSiswa.addColumn("Birthplace");
            DftTblModelSiswa.addColumn("Date Of Birth");
            DftTblModelSiswa.addColumn("Religion");
            DftTblModelSiswa.addColumn("Occupation");
            DftTblModelSiswa.addColumn("Note");
            DftTblModelSiswa.addColumn("Category");
            DftTblModelSiswa.addColumn("Image");
            // to fill an empty table on the page with the table from the DftTblModel
            Tsiswa.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       //???
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tsiswa";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Name"),
                           res.getString("Gender"),
                           res.getString("Phone"),
                           res.getString("Address"),
                           res.getString("StartingDate"),
                           res.getString("Birthplace"),
                           res.getString("Birthday"),
                           res.getString("Religion"),
                           res.getString("Occupation"),
                           res.getString("Note"),
                           res.getString("Category"),
                           res.getString("Image")
                       });
                       }
                      
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    }
    
    public void ShowDataSchoolFee(){
            DftTblModelSfee= new DefaultTableModel();
            DftTblModelSfee.addColumn("Id");
            DftTblModelSfee.addColumn("StudentId");
            DftTblModelSfee.addColumn("Name");
            DftTblModelSfee.addColumn("Date");
            DftTblModelSfee.addColumn("Groups");
            DftTblModelSfee.addColumn("ForMonth");
            DftTblModelSfee.addColumn("ForYear");
            DftTblModelSfee.addColumn("ForClass");
            DftTblModelSfee.addColumn("Amount");
            DftTblModelSfee.addColumn("Description");
            DftTblModelSfee.addColumn("Userlog");
            DftTblModelSfee.addColumn("LateFee");
            DftTblModelSfee.addColumn("Total");
            DftTblModelSfee.addColumn("Category");
            TSfee.setModel(DftTblModelSfee);
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tschoolfee order by date asc";
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSfee.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("StudentId"),    
                           res.getString("Name"),
                           res.getString("Date"),
                           res.getString("Groups"),
                           res.getString("ForMonth"),
                           res.getString("ForYear"),
                           res.getString("ForClass"),
                           res.getString("Amount"),
                           res.getString("Description"),
                           res.getString("Userlog"),
                           res.getString("LateFee"),
                           res.getString("Total"),
                           res.getString("Category"),
                       });
                       }
            TSfee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
            }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        MenuPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PClasses = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        PRegister = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LDate1 = new javax.swing.JLabel();
        LTime2 = new javax.swing.JLabel();
        StudentSPanel = new javax.swing.JScrollPane();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        PMenuSiswa = new javax.swing.JPanel();
        TFSearch = new javax.swing.JTextField();
        PMove = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PLevelUp = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        CBSearch = new javax.swing.JComboBox<>();
        PSiswa = new javax.swing.JScrollPane();
        Tsiswa = new javax.swing.JTable();
        PSfee = new javax.swing.JScrollPane();
        TSfee = new javax.swing.JTable();
        PQuit = new javax.swing.JPanel();
        LQuit = new javax.swing.JLabel();
        PTotal = new javax.swing.JPanel();
        LTotalStudents = new javax.swing.JLabel();
        LTotalStudents2 = new javax.swing.JLabel();
        PMenuSfee = new javax.swing.JPanel();
        PSchoolFee = new javax.swing.JPanel();
        LSchoolFee = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TFSearchfee = new javax.swing.JTextField();
        CBSearch1 = new javax.swing.JComboBox<>();
        PPrintCard = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MenuPanel.setBackground(new java.awt.Color(51, 102, 255));
        MenuPanel.setPreferredSize(new java.awt.Dimension(216, 587));

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Students");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Teachers");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Training_32px.png"))); // NOI18N

        javax.swing.GroupLayout PTeacherLayout = new javax.swing.GroupLayout(PTeacher);
        PTeacher.setLayout(PTeacherLayout);
        PTeacherLayout.setHorizontalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PTeacherLayout.setVerticalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Classes");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClassesLayout = new javax.swing.GroupLayout(PClasses);
        PClasses.setLayout(PClassesLayout);
        PClassesLayout.setHorizontalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClassesLayout.setVerticalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Register");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PRegisterLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addContainerGap())
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Log Out");

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Report");

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

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

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel15.setText("Change Password");

        javax.swing.GroupLayout PCPassLayout = new javax.swing.GroupLayout(PCPass);
        PCPass.setLayout(PCPassLayout);
        PCPassLayout.setHorizontalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPassLayout.setVerticalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome ,");

        jLabel7.setFont(new java.awt.Font("Poor Richard", 0, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/ddtclogo2011.png"))); // NOI18N
        jLabel7.setBorder(null);

        Luser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Luser.setForeground(new java.awt.Color(255, 255, 255));
        Luser.setText("User");

        LDate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate1.setForeground(new java.awt.Color(255, 255, 255));
        LDate1.setText("Date");

        LTime2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime2.setForeground(new java.awt.Color(255, 255, 255));
        LTime2.setText("Time");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LDate1)
                                .addGap(18, 18, 18)
                                .addComponent(LTime2)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDate1)
                    .addComponent(LTime2))
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE)
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 141, Short.MAX_VALUE))
        );

        StudentSPanel.setBackground(new java.awt.Color(255, 255, 255));
        StudentSPanel.setBorder(null);
        StudentSPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        StudentSPanel.setAutoscrolls(true);
        StudentSPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        StudentSPanel.setInheritsPopupMenu(true);
        StudentSPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkGradientFocus(600);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));

        PMenuSiswa.setBackground(new java.awt.Color(51, 102, 255));

        TFSearch.setBackground(new java.awt.Color(0, 51, 153));
        TFSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearch.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFSearch.setText("Search Class or Name");
        TFSearch.setBorder(null);
        TFSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFSearchFocusLost(evt);
            }
        });
        TFSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSearchActionPerformed(evt);
            }
        });
        TFSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchKeyReleased(evt);
            }
        });

        PMove.setBackground(new java.awt.Color(51, 102, 255));
        PMove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PMoveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PMoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PMoveMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Move Class");

        javax.swing.GroupLayout PMoveLayout = new javax.swing.GroupLayout(PMove);
        PMove.setLayout(PMoveLayout);
        PMoveLayout.setHorizontalGroup(
            PMoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMoveLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        PMoveLayout.setVerticalGroup(
            PMoveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMoveLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(21, 21, 21))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        PLevelUp.setBackground(new java.awt.Color(51, 102, 255));
        PLevelUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PLevelUpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PLevelUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PLevelUpMouseExited(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Level Up");

        javax.swing.GroupLayout PLevelUpLayout = new javax.swing.GroupLayout(PLevelUp);
        PLevelUp.setLayout(PLevelUpLayout);
        PLevelUpLayout.setHorizontalGroup(
            PLevelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLevelUpLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel17)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        PLevelUpLayout.setVerticalGroup(
            PLevelUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLevelUpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(21, 21, 21))
        );

        CBSearch.setBackground(new java.awt.Color(204, 153, 255));
        CBSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Class", "Name", "Id", "Birthday", "Address" }));
        CBSearch.setBorder(null);

        javax.swing.GroupLayout PMenuSiswaLayout = new javax.swing.GroupLayout(PMenuSiswa);
        PMenuSiswa.setLayout(PMenuSiswaLayout);
        PMenuSiswaLayout.setHorizontalGroup(
            PMenuSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswaLayout.createSequentialGroup()
                .addComponent(PMove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PLevelUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        PMenuSiswaLayout.setVerticalGroup(
            PMenuSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswaLayout.createSequentialGroup()
                .addGroup(PMenuSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PMove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PLevelUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PMenuSiswaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PMenuSiswaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PSiswa.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        PSiswa.setPreferredSize(new java.awt.Dimension(350, 421));
        PSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSiswaMouseClicked(evt);
            }
        });
        PSiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PSiswaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PSiswaKeyReleased(evt);
            }
        });

        Tsiswa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Tsiswa.setForeground(new java.awt.Color(0, 51, 51));
        Tsiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tsiswa.setAutoscrolls(false);
        Tsiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tsiswa.setGridColor(new java.awt.Color(153, 153, 153));
        Tsiswa.setRowHeight(30);
        Tsiswa.setRowMargin(5);
        Tsiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TsiswaMouseClicked(evt);
            }
        });
        Tsiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsiswaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TsiswaKeyReleased(evt);
            }
        });
        PSiswa.setViewportView(Tsiswa);

        TSfee.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        TSfee.setForeground(new java.awt.Color(0, 51, 51));
        TSfee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TSfee.setGridColor(new java.awt.Color(153, 153, 153));
        TSfee.setRowHeight(30);
        TSfee.setRowMargin(5);
        TSfee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSfeeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TSfeeKeyReleased(evt);
            }
        });
        PSfee.setViewportView(TSfee);

        PQuit.setBackground(new java.awt.Color(51, 102, 255));
        PQuit.setForeground(new java.awt.Color(51, 153, 255));
        PQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PQuitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PQuitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PQuitMouseExited(evt);
            }
        });

        LQuit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LQuit.setForeground(new java.awt.Color(255, 255, 255));
        LQuit.setText("Quit");
        LQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LQuitMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PQuitLayout = new javax.swing.GroupLayout(PQuit);
        PQuit.setLayout(PQuitLayout);
        PQuitLayout.setHorizontalGroup(
            PQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PQuitLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(LQuit)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        PQuitLayout.setVerticalGroup(
            PQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LQuit, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        PTotal.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PTotalLayout = new javax.swing.GroupLayout(PTotal);
        PTotal.setLayout(PTotalLayout);
        PTotalLayout.setHorizontalGroup(
            PTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );
        PTotalLayout.setVerticalGroup(
            PTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        LTotalStudents.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTotalStudents.setForeground(new java.awt.Color(0, 51, 51));
        LTotalStudents.setText("Total Students :");

        LTotalStudents2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTotalStudents2.setForeground(new java.awt.Color(0, 51, 51));
        LTotalStudents2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LTotalStudents2.setText("-");

        PMenuSfee.setBackground(new java.awt.Color(51, 102, 255));

        PSchoolFee.setBackground(new java.awt.Color(51, 102, 255));
        PSchoolFee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSchoolFeeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PSchoolFeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PSchoolFeeMouseExited(evt);
            }
        });

        LSchoolFee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee.setText("School fee");
        LSchoolFee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFeeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PSchoolFeeLayout = new javax.swing.GroupLayout(PSchoolFee);
        PSchoolFee.setLayout(PSchoolFeeLayout);
        PSchoolFeeLayout.setHorizontalGroup(
            PSchoolFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PSchoolFeeLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(LSchoolFee)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        PSchoolFeeLayout.setVerticalGroup(
            PSchoolFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PSchoolFeeLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(LSchoolFee)
                .addGap(20, 20, 20))
        );

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        TFSearchfee.setBackground(new java.awt.Color(0, 51, 153));
        TFSearchfee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearchfee.setForeground(new java.awt.Color(255, 255, 255));
        TFSearchfee.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFSearchfee.setBorder(null);
        TFSearchfee.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFSearchfeeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFSearchfeeFocusLost(evt);
            }
        });
        TFSearchfee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSearchfeeActionPerformed(evt);
            }
        });
        TFSearchfee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchfeeKeyReleased(evt);
            }
        });

        CBSearch1.setBackground(new java.awt.Color(204, 153, 255));
        CBSearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "StudentId", "Date" }));
        CBSearch1.setBorder(null);

        PPrintCard.setBackground(new java.awt.Color(51, 102, 255));
        PPrintCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintCardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintCardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintCardMouseExited(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Print Card");

        javax.swing.GroupLayout PPrintCardLayout = new javax.swing.GroupLayout(PPrintCard);
        PPrintCard.setLayout(PPrintCardLayout);
        PPrintCardLayout.setHorizontalGroup(
            PPrintCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintCardLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel18)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        PPrintCardLayout.setVerticalGroup(
            PPrintCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PPrintCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout PMenuSfeeLayout = new javax.swing.GroupLayout(PMenuSfee);
        PMenuSfee.setLayout(PMenuSfeeLayout);
        PMenuSfeeLayout.setHorizontalGroup(
            PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSfeeLayout.createSequentialGroup()
                .addComponent(PSchoolFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PPrintCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TFSearchfee, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        PMenuSfeeLayout.setVerticalGroup(
            PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSfeeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSearchfee, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSfeeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PSchoolFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PPrintCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 9)); // NOI18N
        jLabel3.setText("Search by class");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(87, 87, 87)
                                .addComponent(PTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(LTotalStudents)
                                .addGap(18, 18, 18)
                                .addComponent(LTotalStudents2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(853, 853, 853)
                        .addComponent(PQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(PSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(PSfee, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 35, Short.MAX_VALUE))
            .addComponent(PMenuSiswa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PMenuSfee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(PMenuSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LTotalStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LTotalStudents2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(PMenuSfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PSfee, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(PQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 68, Short.MAX_VALUE))
        );

        StudentSPanel.setViewportView(kGradientPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(StudentSPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1047, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StudentSPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                    .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1285, 724));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void PTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseEntered
        PTeacher.setBackground(new java.awt.Color(0, 51, 153));
// TODO add your handling code here:
    }//GEN-LAST:event_PTeacherMouseEntered

    private void PTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseExited
        PTeacher.setBackground(new java.awt.Color(51,102,255));        
// TODO add your handling code here:
    }//GEN-LAST:event_PTeacherMouseExited

    private void PClassesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseEntered
        PClasses.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseEntered

    private void PRegisterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseEntered
        PRegister.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PRegisterMouseEntered

    private void PLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseEntered
        PLogOut.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PLogOutMouseEntered

    private void PClassesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseExited
        PClasses.setBackground(new java.awt.Color(51,102,255));  
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseExited

    private void PRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseExited
        PRegister.setBackground(new java.awt.Color(51,102,255));  
        // TODO add your handling code here:
    }//GEN-LAST:event_PRegisterMouseExited

    private void PLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseExited
        PLogOut.setBackground(new java.awt.Color(51,102,255));  
        // TODO add your handling code here:
    }//GEN-LAST:event_PLogOutMouseExited

    private void PTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseClicked
        String Username=Luser.getText();
        FormTeacher lf = new FormTeacher();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PTeacherMouseClicked

    private void PClassesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseClicked
        String Username=Luser.getText();
        FormClass lf = new FormClass();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PClassesMouseClicked

    private void PRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PRegisterMouseClicked
        String Username=Luser.getText();
        FormRegister lf = new FormRegister();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PRegisterMouseClicked

    private void PLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLogOutMouseClicked
        new FormLogin().setVisible(true);
        hide();
    }//GEN-LAST:event_PLogOutMouseClicked

    private void PQuitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitMouseExited
       // LQuit.setBackground(new java.awt.Color(255, 255, 255));
       PQuit.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PQuitMouseExited

    private void PQuitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitMouseEntered
        PQuit.setBackground(new java.awt.Color(0, 51, 153));
        //LQuit.setForeground(new java.awt.Color(255, 255, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PQuitMouseEntered

    private void PQuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitMouseClicked
        
        int row= Tsiswa.getSelectedRow();
        String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
        try{
            String sql= "select * from tsiswa where Id='"+TableClick+"'";
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();

            String Id;
            String Name;
            String Class;
            String Username;
            if(rs.next()){
                Id = (String)(rs.getString("Id"));
                Name= (String)(rs.getString("Name"));
                Class = (String)(rs.getString("Class"));
                Username= Luser.getText();
                
                FormQuit lf = new FormQuit();
                lf.setVisible(true);
                this.setVisible(true);
                hide();        
                lf.pass(Username,Id,Name,Class);
                }}catch(Exception e){
                }
    }//GEN-LAST:event_PQuitMouseClicked

    private void LQuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LQuitMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LQuitMouseClicked

    private void PSchoolFeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseExited
        PSchoolFee.setBackground(new java.awt.Color(51, 102, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PSchoolFeeMouseExited

    private void PSchoolFeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseEntered
        PSchoolFee.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PSchoolFeeMouseEntered

    private void PSiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSiswaKeyReleased

    }//GEN-LAST:event_PSiswaKeyReleased

    private void PSiswaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PSiswaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PSiswaKeyPressed

    private void TsiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsiswaKeyReleased
        
        if (evt.getKeyCode()==KeyEvent.VK_DOWN ||evt.getKeyCode()==KeyEvent.VK_UP){
          
            DftTblModelSfee= new DefaultTableModel();
            DftTblModelSfee.addColumn("Id");
            DftTblModelSfee.addColumn("StudentId");
            DftTblModelSfee.addColumn("Name");
            DftTblModelSfee.addColumn("Date");
            DftTblModelSfee.addColumn("Groups");
            DftTblModelSfee.addColumn("ForMonth");
            DftTblModelSfee.addColumn("ForYear");
            DftTblModelSfee.addColumn("ForClass");
            DftTblModelSfee.addColumn("Amount");
            DftTblModelSfee.addColumn("Description");
            DftTblModelSfee.addColumn("Userlog");
            DftTblModelSfee.addColumn("LateFee");
            DftTblModelSfee.addColumn("Total");
            DftTblModelSfee.addColumn("Category");
            TSfee.setModel(DftTblModelSfee);
            
            TSfee.setModel(DftTblModelSfee);
            java.sql.Connection conn = new DbConnection().connect();
            try{
                //Display the schoolfee table based on what is selected from Tsiswa table       
                        
                int row= Tsiswa.getSelectedRow(); //make a variable to get row value
                String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                java.sql.Statement stmt = conn.createStatement();
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                       //System.out.println("TableClick:"+ TableClick);
                SQL ="select * from tschoolfee where StudentId='"+TableClick+"'";
                       
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSfee.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("StudentId"),    
                           res.getString("Name"),
                           res.getString("Date"),
                           res.getString("Groups"),
                           res.getString("ForMonth"),
                           res.getString("ForYear"),
                           res.getString("ForClass"),
                           res.getString("Amount"),
                           res.getString("Description"),
                           res.getString("Userlog"),
                           res.getString("LateFee"),
                           res.getString("Total"),
                           res.getString("Category"),
                       });
                       }
                       Count();
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }

        }else if (evt.getKeyCode()==KeyEvent.VK_ENTER){

        }
    }//GEN-LAST:event_TsiswaKeyReleased

    private void TsiswaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsiswaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_DOWN ||evt.getKeyCode()==KeyEvent.VK_UP){
            try{

                //hidePanel2();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

        }else if (evt.getKeyCode()==KeyEvent.VK_ENTER){
         //to make get the value of the table
         try{       
                int row= Tsiswa.getSelectedRow();
                String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();

                    //to pass variable to FormEdit 
                    String Name;
                    String Class;
                    String Gender;
                    String Phone;
                    String Address;
                    String StartingDate;
                    String Birthplace;
                    String Religion;
                    String Occupation;
                    String Note;
                    String Id;
                    String Birthday;
                    String Username;
                    String Image;
                    String Category;
                    
                    if(rs.next()){
                    
                    Username = (String)Luser.getText();
                    System.out.println(Username);
                    Name =  (String)(rs.getString("Name"));
                    Class = (String) rs.getString("Class");
                    Gender = (String) rs.getString("Gender");
                    Phone = (String) rs.getString("Phone");
                    Address = (String) rs.getString("Address");
                    StartingDate = (String) rs.getString("StartingDate");
                    Birthplace = (String) rs.getString("Birthplace");
                    Religion = (String) rs.getString("Religion");
                    Occupation = (String) rs.getString("Occupation");
                    Note = (String) rs.getString("Note");
                    Id= (String) rs.getString("Id");
                    Birthday= (String) rs.getString("Birthday");
                    Image = (String) rs.getString("Image");
                    Category=(String) rs.getString("Category");
                    
            FormEdit lf = new FormEdit();
            lf.setVisible(true);
            this.setVisible(true);
            hide();        
            lf.username(Username,Id ,Name,Class,Gender,Phone,Address, StartingDate,Birthplace,Religion,Occupation, Note, Birthday, Image, Category);
            }
            /*  when passing, remember to make a void Name(String Name){} in the passed form
                this is the reason you can pass the variable to more than one form
                when using new FormSiswa (String Name) directly, you won't be able to pass it to the third form
            */
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }   
                

            
        }
    }//GEN-LAST:event_TsiswaKeyPressed

    private void PMoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PMoveMouseExited
        PMove.setBackground(new java.awt.Color(51, 102, 255));

    }//GEN-LAST:event_PMoveMouseExited

    private void PMoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PMoveMouseEntered
        PMove.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PMoveMouseEntered

    private void TFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchKeyReleased
        ShowData();
        //hidePanel();
        Count();
        PTotal.setVisible(true);
        String Search = (String) CBSearch.getSelectedItem();
//        if(TFSearch.getText()==" "){
//            TFSearch.setText("Search");
//        }
        DftTblModelSiswa= new DefaultTableModel();
        DftTblModelSiswa.addColumn("Id");
        DftTblModelSiswa.addColumn("Class");
        DftTblModelSiswa.addColumn("Name");
        DftTblModelSiswa.addColumn("Gender");
        DftTblModelSiswa.addColumn("Phone");
        DftTblModelSiswa.addColumn("Adress");
        DftTblModelSiswa.addColumn("Place_Of_Birth");
        DftTblModelSiswa.addColumn("Date_Of_Birth");
        DftTblModelSiswa.addColumn("Religion");
        DftTblModelSiswa.addColumn("Occupation");
        DftTblModelSiswa.addColumn("Note");
        Tsiswa.setModel(DftTblModelSiswa);
        java.sql.Connection conn = new DbConnection().connect();
        try{
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tsiswa where "+Search+"='"+TFSearch.getText()+"' AND NOT (status='Prereg' OR status='Quit')";
            //String SQL2;
            //SQL2 = "select count(Id) from tsiswa where Class='"+TFSearch.getText()+"'";
            //java.sql.ResultSet res2 = stmt.executeQuery(SQL2);
          
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                //LTotalStudents2.setText(SQL2);
                DftTblModelSiswa.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("Class"),
                    res.getString("Name"),
                    res.getString("Gender"),
                    res.getString("Phone"),
                    res.getString("Address"),
                    res.getString("Birthplace"),
                    res.getString("Birthday"),
                    res.getString("Religion"),
                    res.getString("Occupation"),
                    res.getString("Note"),
                    
                }
                );
            
            
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_TFSearchKeyReleased

    private void TFSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearchActionPerformed

    private void TsiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TsiswaMouseClicked
        
    PSfee.setVisible(true);
    ShowDataSchoolFee();
    try{
        PSfee.setVisible(true);
        ShowDataSchoolFee();
        int row= Tsiswa.getSelectedRow();
        String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
            String sql= "select * from tsiswa where Id='"+TableClick+"'";
            System.out.println(sql);
            pst= conn.prepareStatement(sql);            
            rs= pst.executeQuery();
            
            if(rs.next()){
                String add1 = rs.getString("Name");
                System.out.println(add1);
                //V1.setText(add1);
                
            }
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
            
    // TODO add your handling code here:
    }//GEN-LAST:event_TsiswaMouseClicked

    private void PSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSiswaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PSiswaMouseClicked

    private void LSchoolFeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFeeMouseClicked
        
    }//GEN-LAST:event_LSchoolFeeMouseClicked

    private void PSchoolFeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseClicked
    try{
                //to make get the value of the table
                int row= Tsiswa.getSelectedRow();
                String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                if(rs.next()){
                
                String Name;
                String Class;
                String User;
                String Id;
                String Category;
                String StartingDate;
                String Status;
                User = new String(Luser.getText());
                Name =  (String)(rs.getString("Name"));
                Class = (String) rs.getString("Class");
                Category = (String) rs.getString("Category");
                Id=(String)rs.getString("Id");
                StartingDate= rs.getString("StartingDate");
                Status= (String) rs.getString("Status");
                if(Status.equals("QUIT")){
                    JOptionPane.showMessageDialog(null,"Stundent has QUIT","Info",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    FormReceipt lf= new FormReceipt();
                    lf.setVisible(true);
                    hide();
                    lf.username(User,StartingDate,Id,Name, Class, Category);
                }
                }
    }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        
    }//GEN-LAST:event_PSchoolFeeMouseClicked

    private void TFSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchFocusGained
        // TODO add your handling code here:
        if(TFSearch.getText().equals("Search Class or Name")){
        TFSearch.setText("");
        TFSearch.setForeground( new Color(255, 255, 255));
        }
    }//GEN-LAST:event_TFSearchFocusGained

    private void TFSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchFocusLost
        // TODO add your handling code here:
        if(TFSearch.getText().equals("")){
            TFSearch.setText("Search Class or Name");
            TFSearch.setForeground( new Color(153, 204, 255));
        }
    }//GEN-LAST:event_TFSearchFocusLost

    private void PLevelUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLevelUpMouseEntered
        PLevelUp.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PLevelUpMouseEntered

    private void PLevelUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLevelUpMouseExited
        PLevelUp.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PLevelUpMouseExited

    private void PLevelUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PLevelUpMouseClicked
        try{       
                if(TFSearch.getText().isEmpty() || TFSearch.getText().equals("Search Class or Name")){
                
                    JOptionPane.showMessageDialog(null, "Class Must Not Be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                String Search= TFSearch.getText();
                String sql= "select * from tclass where Id='"+Search+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                    String Name = null;
                    String Id = null;
                   
                    if(rs.next()){
                   
                    //Name = (String)rs.getString("Name");
                    Id= (String) rs.getString("Id");}
                    //String Class = TFSearch.getText();
                    String Username = Luser.getText();
                    FormLevelUp lf = new FormLevelUp();
                    lf.setVisible(true);
                    this.setVisible(true);
                    lf.LevelUp(Id, Username);
                    hide();
                }
        }catch(Exception e){
        }
    }//GEN-LAST:event_PLevelUpMouseClicked

    private void PMoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PMoveMouseClicked
        try{       
                if(TFSearch.getText().isEmpty() || TFSearch.getText().equals("Search Class or Name")){
                JOptionPane.showMessageDialog(null, "Class must not be empty");
            } else {
                int row= Tsiswa.getSelectedRow();
                String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                    String Name = null;
                    String Id = null;
                    String Username= null;
                    if(rs.next()){
                   
                    Name = (String)rs.getString("Name");
                    Id= (String) rs.getString("Id");}
                    Username=Luser.getText();
                    String Class = TFSearch.getText();
                    FormMove lf = new FormMove();
                    lf.setVisible(true);
                    this.setVisible(true);
                    lf.pass(Username, Id, Name, Class);
                    hide();
                }
        }catch(Exception e){
        }
    }//GEN-LAST:event_PMoveMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        String Username=Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_jPanel2MouseClicked

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

    private void TSfeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSfeeKeyReleased
        
    }//GEN-LAST:event_TSfeeKeyReleased

    private void TSfeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSfeeKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_DOWN ||evt.getKeyCode()==KeyEvent.VK_UP){
            try{
                //hidePanel2();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

        }else if (evt.getKeyCode()==KeyEvent.VK_ENTER){
         //to make get the value of the table
         try{       
                int row= TSfee.getSelectedRow();
                String TableClick= (TSfee.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tschoolfee where id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();

                    //to pass variable to FormReprint
                    String User;
                    String Id;
                    String StudentId;
                    String Name;
                    String Date;
                    String Groups;
                    String ForMonth;
                    String ForYear;
                    String ForClass;
                    String Amount;
                    String Description;
                    String Userlog;
                    String LateFee;
                    String Total;
                    String Category;
                    
                    if(rs.next()){
                    User = Luser.getText();
                    Id= (String)(rs.getString("Id"));
                    StudentId= (String)(rs.getString("StudentId"));;
                    Name= (String)(rs.getString("Name"));
                    Date= (String)(rs.getString("Date")); ;
                    Groups = (String)(rs.getString("Groups"));
                    ForMonth = (String)(rs.getString("Formonth"));
                    ForYear= (String)(rs.getString("ForYear"));
                    ForClass= (String)(rs.getString("ForClass"));
                    Amount= (String)(rs.getString("Amount"));;
                    Description= (String)(rs.getString("Description"));
                    Userlog= (String)(rs.getString("Userlog"));
                    LateFee= (String)(rs.getString("LateFee"));;
                    Total= (String)(rs.getString("Total"));;
                    Category= (String)(rs.getString("Category"));;
                        
            FormReprint lf = new FormReprint();
            lf.setVisible(true);
            this.setVisible(true);
            hide();        
            lf.username2(User, Id, StudentId,Name,Date,Groups,ForMonth,ForYear,ForClass,Amount,Description,Userlog,LateFee,Total,Category);
            }
            /*  when passing, remember to make a void Name(String Name){} in the passed form
                this is the reason you can pass the variable to more than one form
                when using new FormSiswa (String Name) directly, you won't be able to pass it to the third form
            */
        }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }   
         }
    }//GEN-LAST:event_TSfeeKeyPressed

    private void TFSearchfeeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchfeeFocusGained
        if(TFSearchfee.getText().equals("Search Class or Name")){
        TFSearchfee.setText("");
        TFSearchfee.setForeground( new Color(255, 255, 255));
        }
    }//GEN-LAST:event_TFSearchfeeFocusGained

    private void TFSearchfeeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchfeeFocusLost
        if(TFSearchfee.getText().equals("")){
            TFSearchfee.setText("Search Class or Name");
            TFSearchfee.setForeground( new Color(153, 204, 255));
        }
    }//GEN-LAST:event_TFSearchfeeFocusLost

    private void TFSearchfeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSearchfeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearchfeeActionPerformed

    private void TFSearchfeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchfeeKeyReleased
        ShowDataSchoolFee();
        String Search = (String) CBSearch1.getSelectedItem();
            DftTblModelSfee= new DefaultTableModel();
            DftTblModelSfee.addColumn("Id");
            DftTblModelSfee.addColumn("StudentId");
            DftTblModelSfee.addColumn("Name");
            DftTblModelSfee.addColumn("Date");
            DftTblModelSfee.addColumn("Groups");
            DftTblModelSfee.addColumn("ForMonth");
            DftTblModelSfee.addColumn("ForYear");
            DftTblModelSfee.addColumn("ForClass");
            DftTblModelSfee.addColumn("Amount");
            DftTblModelSfee.addColumn("Description");
            DftTblModelSfee.addColumn("Userlog");
            DftTblModelSfee.addColumn("LateFee");
            DftTblModelSfee.addColumn("Total");
            DftTblModelSfee.addColumn("Category");
            TSfee.setModel(DftTblModelSfee);
            
            java.sql.Connection conn = new DbConnection().connect();
        try{
            java.sql.Statement stmt = conn.createStatement();
            System.out.println("CBbox:"+CBSearch1.getSelectedItem());
            SQL ="select * from tschoolfee where "+Search+"='"+TFSearchfee.getText()+"'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                DftTblModelSfee.addRow(new Object[]{
                    res.getString("Id"),
                        res.getString("StudentId"),    
                        res.getString("Name"),
                        res.getString("Date"),
                        res.getString("Groups"),
                        res.getString("ForMonth"),
                        res.getString("ForYear"),
                        res.getString("ForClass"),
                        res.getString("Amount"),
                        res.getString("Description"),
                        res.getString("Userlog"),
                        res.getString("LateFee"),
                        res.getString("Total"),
                        res.getString("Category"),
                 }
                );
             }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TFSearchfeeKeyReleased

    private void PCPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseEntered
        PCPass.setBackground(new java.awt.Color(1,51,153));
    }//GEN-LAST:event_PCPassMouseEntered

    private void PCPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseExited
        PCPass.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PCPassMouseExited

    private void PCPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPassMouseClicked
        String Username=Luser.getText();
        FormChangePassword lf = new FormChangePassword();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PCPassMouseClicked

    private void PPrintCardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintCardMouseEntered
       PPrintCard.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintCardMouseEntered

    private void PPrintCardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintCardMouseExited
       PPrintCard.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PPrintCardMouseExited

    private void PPrintCardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintCardMouseClicked
       try{
                //to make get the value of the table
                int row= Tsiswa.getSelectedRow();
                String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                if(rs.next()){
                
                String Name;
                String Class;
                String User;
                String Id;
                String Category;
                String StartingDate;
                String Image;
                User = new String(Luser.getText());
                Name =  (String)(rs.getString("Name"));
                Class = (String) rs.getString("Class");
                Category = (String) rs.getString("Category");
                Id=(String)rs.getString("Id");
                StartingDate= rs.getString("StartingDate");
                Image= rs.getString("Image");
                if(Class.equals("QUIT")){
                    JOptionPane.showMessageDialog(null,"Stundent has QUIT","Info",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    FormPrintCard lf= new FormPrintCard();
                    lf.setVisible(true);
                    hide();
                    lf.username(User,StartingDate,Id,Name, Class, Category, Image);
                }
                }
    }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_PPrintCardMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBSearch;
    private javax.swing.JComboBox<String> CBSearch1;
    private javax.swing.JLabel LDate1;
    private javax.swing.JLabel LQuit;
    private javax.swing.JLabel LSchoolFee;
    private javax.swing.JLabel LTime2;
    private javax.swing.JLabel LTotalStudents;
    private javax.swing.JLabel LTotalStudents2;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLevelUp;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenuSfee;
    private javax.swing.JPanel PMenuSiswa;
    private javax.swing.JPanel PMove;
    private javax.swing.JPanel PPrintCard;
    private javax.swing.JPanel PQuit;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PSchoolFee;
    private javax.swing.JScrollPane PSfee;
    private javax.swing.JScrollPane PSiswa;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JPanel PTotal;
    private javax.swing.JScrollPane StudentSPanel;
    private javax.swing.JTextField TFSearch;
    private javax.swing.JTextField TFSearchfee;
    private javax.swing.JTable TSfee;
    private javax.swing.JTable Tsiswa;
    private javax.swing.JFrame jFrame1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
