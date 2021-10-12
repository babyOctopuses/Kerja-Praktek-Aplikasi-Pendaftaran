package Form;

import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.Period;

public class FormSettings extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private String SQL;
    private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    ButtonGroup buttonGroup;
    
    public FormSettings() {
        initComponents();
        ShowDefault();
        ShowStatus();
        ShowClassName();
        conn = DbConnection.connect(); 
        CurrentDate();
        }
    
    void username(String User){
        Luser.setText(User);
    }
    
    public void pass(String User){
    Luser.setText(User);
    }
    
    public void ShowDefault(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("LateFee");
            DftTblModelSiswa.addColumn("Report Book");
            DftTblModelSiswa.addColumn("Registration");
            TDefault.setModel(DftTblModelSiswa);
            
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tsettings";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("LateFee"),
                           res.getString("ReportBook"),
                           res.getString("Registration"),
                       });
                       }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    }
    public void ShowClassName(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("ClassNames");
            TClassName.setModel(DftTblModelSiswa);
            
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tclassName";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("ClassNames"),
                        });
                       }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    
    }
    
    public void ShowStatus(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Status");
            DftTblModelSiswa.addColumn("School Fee");
            DftTblModelSiswa.addColumn("Registration");
            DftTblModelSiswa.addColumn("Deduction");
            DftTblModelSiswa.addColumn("Late");
            DftTblModelSiswa.addColumn("Book Fee");
            DftTblModelSiswa.addColumn("Report Book");
            
            TStatus.setModel(DftTblModelSiswa);
            
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tcategory";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Status"),
                           res.getString("SchoolFee"),
                           res.getString("Registration"),
                           res.getString("Deduction"),
                           res.getString("LateFee"),
                           res.getString("BookFee"),
                           res.getString("ReportBook"),
                       });
                       }
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
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
    
    LDate.setText(year+"-"+(month+1)+"-"+day);
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        Gender = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        PStudents = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PClasses1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        PRegister = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LTime1 = new javax.swing.JLabel();
        LDate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        PMenuSiswa5 = new javax.swing.JPanel();
        SchoolFee3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TDefault = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        TFDLateFee = new javax.swing.JTextField();
        TFDReportBook = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        LTeacher1 = new javax.swing.JLabel();
        TFDRegistration = new javax.swing.JTextField();
        BChangeD = new javax.swing.JButton();
        TFDID = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        TFStatus = new javax.swing.JTextField();
        TFDeduction = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        SchoolFee = new javax.swing.JLabel();
        TFRegistration = new javax.swing.JTextField();
        TFSchoolFee = new javax.swing.JTextField();
        TFLateFee = new javax.swing.JTextField();
        TFBookFee = new javax.swing.JTextField();
        TFReportBook = new javax.swing.JTextField();
        SchoolFee1 = new javax.swing.JLabel();
        SchoolFee4 = new javax.swing.JLabel();
        SchoolFee5 = new javax.swing.JLabel();
        BChangeS = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        TFID = new javax.swing.JTextField();
        BDelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TStatus = new javax.swing.JTable();
        PMenuSiswa4 = new javax.swing.JPanel();
        SchoolFee2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PMenuSiswa6 = new javax.swing.JPanel();
        SchoolFee6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        TFClassName = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        BChangeD1 = new javax.swing.JButton();
        TFClassNameID = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        BDelete1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TClassName = new javax.swing.JTable();

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
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(51, 102, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(216, 732));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Students");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout PStudentsLayout = new javax.swing.GroupLayout(PStudents);
        PStudents.setLayout(PStudentsLayout);
        PStudentsLayout.setHorizontalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStudentsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        PStudentsLayout.setVerticalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        PClasses1.setBackground(new java.awt.Color(102, 204, 255));
        PClasses1.addMouseListener(new java.awt.event.MouseAdapter() {
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

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Classes");

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClasses1Layout = new javax.swing.GroupLayout(PClasses1);
        PClasses1.setLayout(PClasses1Layout);
        PClasses1Layout.setHorizontalGroup(
            PClasses1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClasses1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel17)
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClasses1Layout.setVerticalGroup(
            PClasses1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(51, 102, 255));

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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome ,");

        jLabel20.setFont(new java.awt.Font("Poor Richard", 0, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/ddtclogo2011.png"))); // NOI18N
        jLabel20.setBorder(null);

        Luser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Luser.setForeground(new java.awt.Color(255, 255, 255));
        Luser.setText("aaaaa");

        LTime1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime1.setForeground(new java.awt.Color(255, 255, 255));
        LTime1.setText("Time");

        LDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate.setForeground(new java.awt.Color(255, 255, 255));
        LDate.setText("Date");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel20)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(LDate)
                                .addGap(18, 18, 18)
                                .addComponent(LTime1)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDate)
                    .addComponent(LTime1))
                .addGap(10, 10, 10)
                .addComponent(jLabel20)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PClasses1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PRegister, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
        );

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        PMenuSiswa5.setBackground(new java.awt.Color(51, 102, 255));

        SchoolFee3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SchoolFee3.setForeground(new java.awt.Color(255, 255, 255));
        SchoolFee3.setText("Default Fee Settings");

        javax.swing.GroupLayout PMenuSiswa5Layout = new javax.swing.GroupLayout(PMenuSiswa5);
        PMenuSiswa5.setLayout(PMenuSiswa5Layout);
        PMenuSiswa5Layout.setHorizontalGroup(
            PMenuSiswa5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswa5Layout.createSequentialGroup()
                .addGap(443, 443, 443)
                .addComponent(SchoolFee3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PMenuSiswa5Layout.setVerticalGroup(
            PMenuSiswa5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(SchoolFee3)
                .addGap(17, 17, 17))
        );

        TDefault.setModel(new javax.swing.table.DefaultTableModel(
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
        TDefault.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TDefaultMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TDefault);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Report Book");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("ID");

        TFDLateFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFDLateFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFDLateFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDLateFeeActionPerformed(evt);
            }
        });

        TFDReportBook.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFDReportBook.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFDReportBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDReportBookActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Late Fee");

        LTeacher1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTeacher1.setText("Registration");

        TFDRegistration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFDRegistration.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFDRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDRegistrationActionPerformed(evt);
            }
        });

        BChangeD.setText("Change");
        BChangeD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BChangeDActionPerformed(evt);
            }
        });

        TFDID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFDID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFDID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BChangeD)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LTeacher1)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFDLateFee, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFDReportBook, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFDRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFDID, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(TFDID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(TFDLateFee, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TFDReportBook, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LTeacher1)
                    .addComponent(TFDRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BChangeD)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addComponent(PMenuSiswa5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PMenuSiswa5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Status");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("ID");

        TFStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFStatus.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFStatusActionPerformed(evt);
            }
        });

        TFDeduction.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFDeduction.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFDeduction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFDeductionActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("School Fee");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Registration");

        SchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SchoolFee.setText("Deduction");

        TFRegistration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFRegistration.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFRegistration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFRegistrationActionPerformed(evt);
            }
        });

        TFSchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSchoolFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFSchoolFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSchoolFeeActionPerformed(evt);
            }
        });

        TFLateFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFLateFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFLateFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFLateFeeActionPerformed(evt);
            }
        });

        TFBookFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFBookFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFBookFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBookFeeActionPerformed(evt);
            }
        });

        TFReportBook.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFReportBook.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFReportBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFReportBookActionPerformed(evt);
            }
        });

        SchoolFee1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SchoolFee1.setText("Late Fee");

        SchoolFee4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SchoolFee4.setText("Book Fee");

        SchoolFee5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SchoolFee5.setText("Report Book");

        BChangeS.setText("Change");
        BChangeS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BChangeSActionPerformed(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TFID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFIDActionPerformed(evt);
            }
        });

        BDelete.setText("Delete");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SchoolFee5)
                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel21)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(SchoolFee)
                    .addComponent(SchoolFee1)
                    .addComponent(SchoolFee4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BChangeS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BDelete))
                    .addComponent(TFStatus, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFSchoolFee)
                    .addComponent(TFRegistration, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFDeduction, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFLateFee)
                    .addComponent(TFBookFee, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFReportBook, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFID, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(TFID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TFStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSchoolFee, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFRegistration, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFDeduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SchoolFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFLateFee, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SchoolFee1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBookFee, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SchoolFee4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFReportBook, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SchoolFee5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(BChangeS)
                    .addComponent(BDelete))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        TStatus.setModel(new javax.swing.table.DefaultTableModel(
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
        TStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TStatusMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TStatusMouseEntered(evt);
            }
        });
        TStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TStatusKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TStatusKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TStatus);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        PMenuSiswa4.setBackground(new java.awt.Color(51, 102, 255));

        SchoolFee2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SchoolFee2.setForeground(new java.awt.Color(255, 255, 255));
        SchoolFee2.setText("Status Fee Settings");

        javax.swing.GroupLayout PMenuSiswa4Layout = new javax.swing.GroupLayout(PMenuSiswa4);
        PMenuSiswa4.setLayout(PMenuSiswa4Layout);
        PMenuSiswa4Layout.setHorizontalGroup(
            PMenuSiswa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswa4Layout.createSequentialGroup()
                .addGap(443, 443, 443)
                .addComponent(SchoolFee2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PMenuSiswa4Layout.setVerticalGroup(
            PMenuSiswa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(SchoolFee2)
                .addGap(17, 17, 17))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        PMenuSiswa6.setBackground(new java.awt.Color(51, 102, 255));

        SchoolFee6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SchoolFee6.setForeground(new java.awt.Color(255, 255, 255));
        SchoolFee6.setText("Class Names");

        javax.swing.GroupLayout PMenuSiswa6Layout = new javax.swing.GroupLayout(PMenuSiswa6);
        PMenuSiswa6.setLayout(PMenuSiswa6Layout);
        PMenuSiswa6Layout.setHorizontalGroup(
            PMenuSiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SchoolFee6)
                .addGap(428, 428, 428))
        );
        PMenuSiswa6Layout.setVerticalGroup(
            PMenuSiswa6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa6Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(SchoolFee6)
                .addGap(17, 17, 17))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("ID");

        TFClassName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFClassName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFClassName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFClassNameActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Class Name");

        BChangeD1.setText("Change");
        BChangeD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BChangeD1ActionPerformed(evt);
            }
        });

        TFClassNameID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFClassNameID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFClassNameID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFClassNameIDActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BDelete1.setText("Delete");
        BDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDelete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BChangeD1)
                        .addGap(12, 12, 12)
                        .addComponent(BDelete1))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFClassNameID, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(TFClassNameID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(TFClassName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BChangeD1)
                    .addComponent(jButton2)
                    .addComponent(BDelete1))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        TClassName.setModel(new javax.swing.table.DefaultTableModel(
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
        TClassName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TClassNameMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(TClassName);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(PMenuSiswa6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PMenuSiswa6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PMenuSiswa4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PMenuSiswa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TFStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFStatusActionPerformed

    private void TFDeductionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDeductionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDeductionActionPerformed

    private void PStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseClicked
        String Username=Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PStudentsMouseClicked

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

    private void PStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseEntered
        PStudents.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PStudentsMouseEntered

    private void PStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseExited
        PStudents.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PStudentsMouseExited

    private void TStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TStatusKeyReleased
    
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){
        try{
            int row = TStatus.getSelectedRow(); // to get the selected row
            String TableClick= (TStatus.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tcategory where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            
            if(rs.next()){
               String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Status = rs.getString("Status");
                TFStatus.setText(Status);
                String SchoolFee = rs.getString("SchoolFee");
                TFSchoolFee.setText(SchoolFee);
                String Registration = rs.getString("Registration");
                TFRegistration.setText(Registration);
                String Deduction = rs.getString("Deduction");
                TFDeduction.setText(Deduction);
                String LateFee = rs.getString("LateFee");
                TFLateFee.setText(LateFee);
                String BookFee = rs.getString("BookFee");
                TFBookFee.setText(BookFee);
                String ReportBook = rs.getString("ReportBook");
                TFReportBook.setText(ReportBook);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    }//GEN-LAST:event_TStatusKeyReleased

    private void TStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TStatusKeyPressed
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){

        }else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {

//            int row= TClass.getSelectedRow();
//            String TableClick= (TClass.getModel().getValueAt(row, 0).toString());
//            try{
//                String sql= "select * from tclass where Id='"+TableClick+"'";
//                pst= conn.prepareStatement(sql);
//                rs= pst.executeQuery();
//
//                String Class;
//                if(rs.next()){
//                    String User= Luser.getText();
//                    Class = (String)(rs.getString("Id"));
//                    FormSiswa lf = new FormSiswa();
//                    lf.setVisible(true);
//                    this.setVisible(true);
//                    hide();
//                    lf.pass(User, Class);
//                }}catch(Exception e){
//
//                }
//            }
        }
    }//GEN-LAST:event_TStatusKeyPressed
    
    private void TStatusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TStatusMouseEntered

    }//GEN-LAST:event_TStatusMouseEntered

    private void TStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TStatusMouseClicked
        try{
            int row = TStatus.getSelectedRow(); // to get the selected row
            String TableClick= (TStatus.getModel().getValueAt(row,0).toString()); 
            String sql = "select * from tcategory where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Status = rs.getString("Status");
                TFStatus.setText(Status);
                String SchoolFee = rs.getString("SchoolFee");
                TFSchoolFee.setText(SchoolFee);
                String Registration = rs.getString("Registration");
                TFRegistration.setText(Registration);
                String Deduction = rs.getString("Deduction");
                TFDeduction.setText(Deduction);
                String LateFee = rs.getString("LateFee");
                TFLateFee.setText(LateFee);
                String BookFee = rs.getString("BookFee");
                TFBookFee.setText(BookFee);
                String ReportBook = rs.getString("ReportBook");
                TFReportBook.setText(ReportBook);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TStatusMouseClicked

    private void TFRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFRegistrationActionPerformed

    private void TFSchoolFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSchoolFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSchoolFeeActionPerformed

    private void TFLateFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFLateFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFLateFeeActionPerformed

    private void TFBookFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBookFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBookFeeActionPerformed

    private void TFReportBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFReportBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFReportBookActionPerformed

    private void TFDLateFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDLateFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDLateFeeActionPerformed

    private void TFDReportBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDReportBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDReportBookActionPerformed

    private void TFDRegistrationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDRegistrationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDRegistrationActionPerformed

    private void TDefaultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TDefaultMouseClicked
        try{
            int row = TDefault.getSelectedRow(); // to get the selected row
            String TableClick= (TDefault.getModel().getValueAt(row,0).toString());
            String sql = "select * from tsettings where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFDID.setText(Id); // set the textfield of Id with the value from add2
                String LateFee = rs.getString("LateFee");
                TFDLateFee.setText(LateFee);
                String ReportBook = rs.getString("ReportBook");
                TFDReportBook.setText(ReportBook);
                String Registration = rs.getString("Registration");
                TFDRegistration.setText(Registration);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TDefaultMouseClicked

    private void BChangeDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BChangeDActionPerformed
    String ID = TFDID.getText();
    String LateFee= TFLateFee.getText();
    String Registration = TFRegistration.getText();
    String ReportBook = TFReportBook.getText();
    
        if(ID.isEmpty() || Registration.isEmpty() || LateFee.isEmpty() ||  ReportBook.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fields must not be empty!");
    }else{
        try{
           
            String sql = "update tsettings set LateFee='"+LateFee+"', ReportBook='"+ReportBook+"', Registration='"+Registration+"' where Id='"+ID+"'";

            pst=conn.prepareStatement(sql);
            pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

            JOptionPane.showMessageDialog(null, "Data Changed");
            ShowDefault();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }
    }//GEN-LAST:event_BChangeDActionPerformed

    private void BChangeSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BChangeSActionPerformed
            String ID = TFDID.getText();
            String Status= TFStatus.getText();
            String SchoolFee= TFSchoolFee.getText();
            String Registration = TFRegistration.getText();
            String Deduction = TFDeduction.getText();
            String LateFee = TFLateFee.getText();
            String BookFee = TFBookFee.getText();
            String ReportBook = TFReportBook.getText();
        
            if(ID.isEmpty() || Status.isEmpty() || SchoolFee.isEmpty() || Registration.isEmpty() || Deduction.isEmpty() || LateFee.isEmpty() || BookFee.isEmpty() || ReportBook.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fields must not be empty!");
            }else{
                try{


                    String sql = "update tcategory set Status='"+Status+"', SchoolFee='"+SchoolFee+"',Registration='"+Registration+"' , Deduction='"+Deduction+"', LateFee='"+LateFee+"', BookFee='"+BookFee+"', ReportBook='"+ReportBook+"' where Id='"+ID+"'";

                    pst=conn.prepareStatement(sql);
                    pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

                    JOptionPane.showMessageDialog(null, "Data Changed");
                    ShowStatus();

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                    System.out.println("e "+ e);
        }
        }
    }//GEN-LAST:event_BChangeSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            String ID = TFDID.getText();
            String Status= TFStatus.getText();
            String SchoolFee= TFSchoolFee.getText();
            String Registration = TFRegistration.getText();
            String Deduction = TFDeduction.getText();
            String LateFee = TFLateFee.getText();
            String BookFee = TFBookFee.getText();
            String ReportBook = TFReportBook.getText();
        
        if(ID.isEmpty() || Status.isEmpty() || SchoolFee.isEmpty() || Registration.isEmpty() || Deduction.isEmpty() || LateFee.isEmpty() || BookFee.isEmpty() || ReportBook.isEmpty()){
                JOptionPane.showMessageDialog(null, "Fields must not be empty!");
       }else{
        try{
           String sql = "Insert into tcategory (Id, Status, SchoolFee, Registration , Deduction, LateFee, BookFee) values (?,?,?,?,?,?,?)";
           //Id can be emptied. 
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql); 
            pst.setString(1, TFID.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, TFStatus.getText());
            pst.setString(3, TFSchoolFee.getText());
            pst.setString(4, TFRegistration.getText());
            pst.setString(5, TFDeduction.getText());
            pst.setString(6, TFLateFee.getText());
            pst.setString(7, TFBookFee.getText());
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            ShowStatus();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFIDActionPerformed

    private void TFDIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFDIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFDIDActionPerformed

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        try{
            String ID= TFID.getText();
            String sql = "Delete from tcategory where Id='"+ID+"'";

            pst=conn.prepareStatement(sql);
            pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

            JOptionPane.showMessageDialog(null, "Deleted");
            ShowStatus();
            //
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void PClassesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseExited

    private void PClassesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PClassesMouseEntered

    private void PClassesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseClicked
        String Username=Luser.getText();
        FormClass lf = new FormClass();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PClassesMouseClicked

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

    private void TFClassNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFClassNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFClassNameActionPerformed

    private void BChangeD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BChangeD1ActionPerformed
        try{
            String ID = TFClassNameID.getText();
            String ClassName = TFClassName.getText();
            String sql = "update tclassname set ClassNames='"+ClassName+"' where Id='"+ID+"'";

            pst=conn.prepareStatement(sql);
            pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

            JOptionPane.showMessageDialog(null, "Data Changed");
            ShowClassName();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BChangeD1ActionPerformed

    private void TFClassNameIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFClassNameIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFClassNameIDActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       try{
           String sql = "Insert into tclassname (Id, Classnames) values (?,?)";
           //Id can be emptied. 
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql); 
            pst.setString(1, TFClassNameID.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, TFClassName.getText());
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Class Added");
            ShowClassName();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDelete1ActionPerformed
        try{
            String ID= TFClassNameID.getText();
            String sql = "Delete from tclassname where Id='"+ID+"'";

            pst=conn.prepareStatement(sql);
            pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

            JOptionPane.showMessageDialog(null, "Deleted");
            ShowClassName();
            //
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BDelete1ActionPerformed

    private void TClassNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TClassNameMouseClicked
        try{
            int row = TClassName.getSelectedRow(); // to get the selected row
            String TableClick= (TClassName.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tclassname where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFClassNameID.setText(Id); // set the textfield of Id with the value from add2
                String ClassName = rs.getString("ClassNames");
                TFClassName.setText(ClassName);
            }
            }catch(Exception e){
            
            }
    }//GEN-LAST:event_TClassNameMouseClicked

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
                new FormSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BChangeD;
    private javax.swing.JButton BChangeD1;
    private javax.swing.JButton BChangeS;
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BDelete1;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LTeacher1;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses1;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenuSiswa4;
    private javax.swing.JPanel PMenuSiswa5;
    private javax.swing.JPanel PMenuSiswa6;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JLabel SchoolFee;
    private javax.swing.JLabel SchoolFee1;
    private javax.swing.JLabel SchoolFee2;
    private javax.swing.JLabel SchoolFee3;
    private javax.swing.JLabel SchoolFee4;
    private javax.swing.JLabel SchoolFee5;
    private javax.swing.JLabel SchoolFee6;
    private javax.swing.JTable TClassName;
    private javax.swing.JTable TDefault;
    private javax.swing.JTextField TFBookFee;
    private javax.swing.JTextField TFClassName;
    private javax.swing.JTextField TFClassNameID;
    private javax.swing.JTextField TFDID;
    private javax.swing.JTextField TFDLateFee;
    private javax.swing.JTextField TFDRegistration;
    private javax.swing.JTextField TFDReportBook;
    private javax.swing.JTextField TFDeduction;
    private javax.swing.JTextField TFID;
    private javax.swing.JTextField TFLateFee;
    private javax.swing.JTextField TFRegistration;
    private javax.swing.JTextField TFReportBook;
    private javax.swing.JTextField TFSchoolFee;
    private javax.swing.JTextField TFStatus;
    private javax.swing.JTable TStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
