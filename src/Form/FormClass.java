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

public class FormClass extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private DefaultTableModel DftTblModelSfee;
    private String SQL;
    private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    ButtonGroup buttonGroup;
    
    public FormClass() {
        initComponents();
        ShowData();
        conn = DbConnection.connect(); 
        CurrentDate();
        FillCombo();
        FillComboSearch();
        FillComboClass();
    }
    
    void username(String User){
        Luser.setText(User);
        CheckUserStatus(User);
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
                    hidePanel();
                }
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void hidePanel(){
    PSettings.setVisible(false);
    }
    
    public void pass(String User){
    Luser.setText(User);
    }
    
    public void FillComboClass(){
        try{
                String sql = "Select * from tclassname";
                pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
                rs= pst.executeQuery();

                while(rs.next()){
                    String Id   = rs.getString("classnames");
                    CBClass.addItem(Id); //add the name from the string name to the combobox
                }

            }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            }
    }
    
    public void FillComboSearch(){
         try{
            String sql = "Select * from INFORMATION_SCHEMA.columns WHERE TABLE_NAME='tclass';";
            pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Id   = rs.getString("COLUMN_NAME");
                CBSearch.addItem(Id); //add the name from the string name to the combobox
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }

    public void FillCombo(){
         try{
            String sql = "select ID, Name from tteacher where not Status='QUIT'";
            pst= conn.prepareStatement(sql); 
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Id   = rs.getString("Id");
                String Name = rs.getString("Name");
                CBTeacher.addItem(Id); 
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //to for display Id Button
    public void Change(){
         try{
            String ID= (String)CBTeacher.getSelectedItem();
            String sql = "select Name from tteacher where Id='"+ID+"'";
            System.out.println("sql "+sql);
            pst= conn.prepareStatement(sql); 
            rs= pst.executeQuery();
            
            if(rs.next()){
                //String Name= (String)CBTeacher.getSelectedItem();
                String Name   = rs.getString("Name");
                LTeacher.setText(Name); //add the name from the string name to the combobox
            }            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void ShowData(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("ClassName");
            DftTblModelSiswa.addColumn("TeacherId");
            DftTblModelSiswa.addColumn("Time");
            DftTblModelSiswa.addColumn("SchoolFee");
            DftTblModelSiswa.addColumn("BookFee");
            
            // to fill an empty table on the page with the table from the DftTblModel
            TClass.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       //???
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tclass";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("ClassName"),
                           res.getString("TeacherID"),
                           res.getString("Time"),
                           res.getString("SchoolFee"),
                           res.getString("BookFee")
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
        PMenu = new javax.swing.JPanel();
        PStudents = new javax.swing.JPanel();
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
        PClassScroll = new javax.swing.JScrollPane();
        PClassMain = new javax.swing.JPanel();
        PUnpaid = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TUnpaid = new javax.swing.JTable();
        PMenuSiswa3 = new javax.swing.JPanel();
        SchoolFee2 = new javax.swing.JLabel();
        PClass2 = new javax.swing.JPanel();
        PScrollClass = new javax.swing.JScrollPane();
        TClass = new javax.swing.JTable();
        PEditClass = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        TFSchoolFee = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        SchoolFee = new javax.swing.JLabel();
        BAdd = new javax.swing.JButton();
        BSave = new javax.swing.JButton();
        BQuit = new javax.swing.JButton();
        CBTime = new javax.swing.JComboBox<>();
        CBTeacher = new javax.swing.JComboBox<>();
        LTeacher = new javax.swing.JLabel();
        TFID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        TFBookFee = new javax.swing.JTextField();
        CBClass = new javax.swing.JComboBox<>();
        PMenuSiswa4 = new javax.swing.JPanel();
        PSettings = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        CBSearch = new javax.swing.JComboBox<>();
        TFSearch = new javax.swing.JTextField();
        PAll = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

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

        PMenu.setBackground(new java.awt.Color(51, 102, 255));
        PMenu.setPreferredSize(new java.awt.Dimension(216, 732));

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        PClasses.setBackground(new java.awt.Color(102, 204, 255));
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

        javax.swing.GroupLayout PMenuLayout = new javax.swing.GroupLayout(PMenu);
        PMenu.setLayout(PMenuLayout);
        PMenuLayout.setHorizontalGroup(
            PMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PMenuLayout.setVerticalGroup(
            PMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
                .addGap(0, 152, Short.MAX_VALUE))
        );

        PClassScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        PClassScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        PUnpaid.setBackground(new java.awt.Color(255, 255, 255));

        TUnpaid.setModel(new javax.swing.table.DefaultTableModel(
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
        TUnpaid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TUnpaidMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TUnpaidMouseEntered(evt);
            }
        });
        TUnpaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUnpaidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TUnpaidKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(TUnpaid);

        PMenuSiswa3.setBackground(new java.awt.Color(51, 102, 255));

        SchoolFee2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        SchoolFee2.setForeground(new java.awt.Color(255, 255, 255));
        SchoolFee2.setText("Unpaid");

        javax.swing.GroupLayout PMenuSiswa3Layout = new javax.swing.GroupLayout(PMenuSiswa3);
        PMenuSiswa3.setLayout(PMenuSiswa3Layout);
        PMenuSiswa3Layout.setHorizontalGroup(
            PMenuSiswa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswa3Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(SchoolFee2)
                .addContainerGap(624, Short.MAX_VALUE))
        );
        PMenuSiswa3Layout.setVerticalGroup(
            PMenuSiswa3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswa3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(SchoolFee2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PUnpaidLayout = new javax.swing.GroupLayout(PUnpaid);
        PUnpaid.setLayout(PUnpaidLayout);
        PUnpaidLayout.setHorizontalGroup(
            PUnpaidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PUnpaidLayout.createSequentialGroup()
                .addGroup(PUnpaidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PMenuSiswa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PUnpaidLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PUnpaidLayout.setVerticalGroup(
            PUnpaidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PUnpaidLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PMenuSiswa3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        PClass2.setBackground(new java.awt.Color(255, 255, 255));

        TClass.setModel(new javax.swing.table.DefaultTableModel(
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
        TClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TClassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TClassMouseEntered(evt);
            }
        });
        TClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TClassKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TClassKeyReleased(evt);
            }
        });
        PScrollClass.setViewportView(TClass);

        PEditClass.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Class");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("ID:");

        TFSchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSchoolFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFSchoolFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSchoolFeeActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Teacher");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Time");

        SchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        SchoolFee.setText("School Fee");

        BAdd.setText("Add");
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        BSave.setText("Update");
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });

        BQuit.setText("Display Id");
        BQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BQuitActionPerformed(evt);
            }
        });

        CBTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "135 800", "135 930", "135 300", "135 430", "135 600", "135 730", "135 800", "135 930", "246 300", "246 430", "246 600", "246 730" }));

        CBTeacher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBTeacherItemStateChanged(evt);
            }
        });
        CBTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CBTeacherMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CBTeacherMouseExited(evt);
            }
        });
        CBTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBTeacherActionPerformed(evt);
            }
        });

        LTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTeacher.setText("Teacher");

        TFID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFIDActionPerformed(evt);
            }
        });

        jButton1.setText("Dismiss");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Book Fee");

        TFBookFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFBookFee.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFBookFee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBookFeeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PEditClassLayout = new javax.swing.GroupLayout(PEditClass);
        PEditClass.setLayout(PEditClassLayout);
        PEditClassLayout.setHorizontalGroup(
            PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PEditClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PEditClassLayout.createSequentialGroup()
                        .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PEditClassLayout.createSequentialGroup()
                                    .addGap(32, 32, 32)
                                    .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel25)
                                        .addComponent(jLabel21)))
                                .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel29)
                                .addComponent(SchoolFee)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFSchoolFee)
                            .addComponent(TFBookFee)
                            .addGroup(PEditClassLayout.createSequentialGroup()
                                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CBTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TFID, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PEditClassLayout.createSequentialGroup()
                                        .addComponent(CBTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                        .addComponent(LTeacher)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PEditClassLayout.createSequentialGroup()
                                .addComponent(CBClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(PEditClassLayout.createSequentialGroup()
                        .addComponent(BAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BQuit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PEditClassLayout.setVerticalGroup(
            PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PEditClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(TFID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(CBClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(LTeacher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(CBTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSchoolFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SchoolFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(TFBookFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(PEditClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAdd)
                    .addComponent(BSave)
                    .addComponent(BQuit)
                    .addComponent(jButton1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PClass2Layout = new javax.swing.GroupLayout(PClass2);
        PClass2.setLayout(PClass2Layout);
        PClass2Layout.setHorizontalGroup(
            PClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClass2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(PEditClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(PScrollClass)
                .addGap(184, 184, 184))
        );
        PClass2Layout.setVerticalGroup(
            PClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PClass2Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(PClass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PScrollClass, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PEditClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        PMenuSiswa4.setBackground(new java.awt.Color(51, 102, 255));

        PSettings.setBackground(new java.awt.Color(51, 102, 255));
        PSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PSettingsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PSettingsMouseExited(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/settings.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Change Default");

        javax.swing.GroupLayout PSettingsLayout = new javax.swing.GroupLayout(PSettings);
        PSettings.setLayout(PSettingsLayout);
        PSettingsLayout.setHorizontalGroup(
            PSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PSettingsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PSettingsLayout.setVerticalGroup(
            PSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PSettingsLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(PSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        CBSearch.setBackground(new java.awt.Color(255, 255, 255));

        TFSearch.setBackground(new java.awt.Color(0, 51, 153));
        TFSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearch.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 153)));
        TFSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        PAll.setBackground(new java.awt.Color(51, 102, 255));
        PAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PAllMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PAllMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PAllMouseExited(evt);
            }
        });
        PAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PAllKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Show All Class");

        javax.swing.GroupLayout PAllLayout = new javax.swing.GroupLayout(PAll);
        PAll.setLayout(PAllLayout);
        PAllLayout.setHorizontalGroup(
            PAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PAllLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(15, 15, 15))
        );
        PAllLayout.setVerticalGroup(
            PAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PAllLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout PMenuSiswa4Layout = new javax.swing.GroupLayout(PMenuSiswa4);
        PMenuSiswa4.setLayout(PMenuSiswa4Layout);
        PMenuSiswa4Layout.setHorizontalGroup(
            PMenuSiswa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa4Layout.createSequentialGroup()
                .addComponent(PSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(275, 275, 275)
                .addComponent(PAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(161, 161, 161))
        );
        PMenuSiswa4Layout.setVerticalGroup(
            PMenuSiswa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSiswa4Layout.createSequentialGroup()
                .addGroup(PMenuSiswa4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PSettings, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PClassMainLayout = new javax.swing.GroupLayout(PClassMain);
        PClassMain.setLayout(PClassMainLayout);
        PClassMainLayout.setHorizontalGroup(
            PClassMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PClassMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PUnpaid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PClass2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PMenuSiswa4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        PClassMainLayout.setVerticalGroup(
            PClassMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassMainLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PMenuSiswa4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PClass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PUnpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PClassScroll.setViewportView(PClassMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(PMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PClassScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PClassScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TClassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TClassMouseEntered
        
    }//GEN-LAST:event_TClassMouseEntered

    private void TClassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TClassKeyReleased
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){
            
            //to display the data of the table to the blanks beside 
            try{
            int row = TClass.getSelectedRow(); // to get the selected row
            String TableClick= (TClass.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tclass where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Name = rs.getString("ClassName");
                CBClass.setSelectedItem(Name);                
                String Teacher = rs.getString("TeacherId");
                CBTeacher.setSelectedItem(Teacher);
                String Time = rs.getString("Time");
                CBTime.setSelectedItem(Time);
                String SchoolFee = rs.getString("SchoolFee");
                TFSchoolFee.setText(SchoolFee);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        //to display the names of the unpaid on the table    
            DftTblModelSfee= new DefaultTableModel();
            DftTblModelSfee.addColumn("Id");
            DftTblModelSfee.addColumn("Name");
            DftTblModelSfee.addColumn("Last For Payment");
            DftTblModelSfee.addColumn("Unpaid months");
            
            TUnpaid.setModel(DftTblModelSfee);
            java.sql.Connection conn = new DbConnection().connect();
            try{
                //Display the schoolfee table based on what is selected from Tsiswa table       
                        
                int row= TClass.getSelectedRow(); //make a variable to get row value
                String TableClick= (TClass.getModel().getValueAt(row, 0).toString());
                String sql= "select * from tsiswa where Id='"+TableClick+"'";
                java.sql.Statement stmt = conn.createStatement();
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();
                       //System.out.println("TableClick:"+ TableClick);
                SQL =   "select tschoolfee.id, tschoolfee.name, tschoolfee.fordate ,timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())\n" +
                        "from tschoolfee\n" +
                        "inner join tsiswa on tsiswa.id=tschoolfee.studentid\n" +
                        "where tschoolfee.id in (select max(id) from tschoolfee group by studentid)\n" +
                        "and tsiswa.status=\"active\"\n" +
                        "and not timestampdiff(month,ADDDATE(fordate, INTERVAL 11 DAY) , CURRENT_DATE())='0'\n" +
                        "and tschoolfee.forclass='"+TableClick+"'\n" +
                        "group by studentid;";
                       
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSfee.addRow(new Object[]{
                           res.getString("tschoolfee.id"),
                           res.getString("tschoolfee.name"),
                           res.getString("tschoolfee.fordate"),
                           res.getString("timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())"),
                           });
                       }
                       
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }    
        }else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
        
        int row= TClass.getSelectedRow();
        String TableClick= (TClass.getModel().getValueAt(row, 0).toString());
        try{
            String sql= "select * from tsiswa where Id='"+TableClick+"'";
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();

            String Class;
            String User= Luser.getText();
            if(rs.next()){
                Class = (String)(rs.getString("Id"));
                FormSiswa lf = new FormSiswa();
                lf.setVisible(true);
                this.setVisible(true);
                hide();
                lf.pass(User, Class);
            }}catch(Exception e){

            }
        }
    }//GEN-LAST:event_TClassKeyReleased

    private void TClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TClassMouseClicked
        try{
            int row = TClass.getSelectedRow(); // to get the selected row
            String TableClick= (TClass.getModel().getValueAt(row,0).toString());
            // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tclass where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Name = rs.getString("ClassName");
                CBClass.setSelectedItem(Name);                
                String Teacher = rs.getString("TeacherId");
                //String substr=Teacher.substring(0, 5);
                CBTeacher.setSelectedItem(Teacher);
                String Time = rs.getString("Time");
                CBTime.setSelectedItem(Time);
                String SchoolFee = rs.getString("SchoolFee");
                TFSchoolFee.setText(SchoolFee);
                String BookFee = rs.getString("BookFee");
                TFBookFee.setText(BookFee);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TClassMouseClicked

    private void TClassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TClassKeyPressed
         if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){
            
        }else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
        
        int row= TClass.getSelectedRow();
        String TableClick= (TClass.getModel().getValueAt(row, 0).toString());
        try{
            String sql= "select * from tclass where Id='"+TableClick+"'";
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();

            String Class; 
            if(rs.next()){
                String User= Luser.getText();
                Class = (String)(rs.getString("Id"));
                FormSiswa lf = new FormSiswa();
                lf.setVisible(true);
                this.setVisible(true);
                hide();
                lf.pass(User, Class);
            }}catch(Exception e){

            }
        }
    }//GEN-LAST:event_TClassKeyPressed

    private void TUnpaidMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TUnpaidMouseClicked
        try{
            int row = TClass.getSelectedRow(); // to get the selected row
            String TableClick= (TClass.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tclass where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Name = rs.getString("ClassName");
                CBClass.setSelectedItem(Name);
                String Teacher = rs.getString("TeacherId");
                //String substr=Teacher.substring(0, 5);
                CBTeacher.setSelectedItem(Teacher);
                String Time = rs.getString("Time");
                CBTime.setSelectedItem(Time);
                String SchoolFee = rs.getString("SchoolFee");
                TFSchoolFee.setText(SchoolFee);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TUnpaidMouseClicked

    private void TUnpaidMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TUnpaidMouseEntered

    }//GEN-LAST:event_TUnpaidMouseEntered

    private void TUnpaidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUnpaidKeyPressed
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){

        }else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {

            int row= TClass.getSelectedRow();
            String TableClick= (TClass.getModel().getValueAt(row, 0).toString());
            try{
                String sql= "select * from tclass where Id='"+TableClick+"'";
                pst= conn.prepareStatement(sql);
                rs= pst.executeQuery();

                String Class;
                if(rs.next()){
                    String User= Luser.getText();
                    Class = (String)(rs.getString("Id"));
                    FormSiswa lf = new FormSiswa();
                    lf.setVisible(true);
                    this.setVisible(true);
                    hide();
                    lf.pass(User, Class);
                }}catch(Exception e){

                }
            }
    }//GEN-LAST:event_TUnpaidKeyPressed

    private void TUnpaidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUnpaidKeyReleased
//        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){
//            int row = TUnpaid.getSelectedRow(); // to get the selected row
//            String TableClick= (TUnpaid.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
//            try{
//                //(row, 0) 0 means the first column
//                String sql =    "select tschoolfee.studentid, tschoolfee.name\n" +
//                                "from tschoolfee\n" +
//                                "inner join tsiswa on tsiswa.id=tschoolfee.studentid\n" +
//                                "where tschoolfee.id in (select max(id) from tschoolfee group by studentid)\n" +
//                                "and tsiswa.status=\"active\"\n" +
//                                "and not timestampdiff(month, fordate, CURRENT_DATE())='0'\n" +
//                                "and tsiswa.id='"+TableClick+"'\n" +
//                                "group by studentid";
//                
//                pst = conn.prepareStatement(sql);
//                JOptionPane.showMessageDialog(null, pst);
//                rs= pst.executeQuery();
//                
//                if(rs.next()){
//                System.out.println("rs "+rs);
//                    
//                    String Id = rs.getString("tschoolfee.studentid"); // get the string of Id from the table
//                    JOptionPane.showMessageDialog(null, Id);
//                    LID.setText(Id); // set the textfield of Id with the value from add2
//                    String Name = rs.getString("tschoolfee.name");
//                    LName.setText(Name);
//                    }
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(null, e);
//            }
//            
//            // to display the latest formonth and foryear
//            try{
//                String sql="select formonth, foryear from tschoolfee where studentid="+TableClick+" && id=(select max(Id)"
//                            + "from tschoolfee where studentid="+TableClick+");";
//                pst=conn.prepareStatement(sql);
//                rs=pst.executeQuery();
//                if(rs.next()){
//                    String formonth=rs.getString("formonth");
//                    String foryear=rs.getString("foryear");
//                    LPayment.setText("Month: "+formonth+" Year: "+ foryear);
//                }
//            }catch(Exception e){
//                System.out.println("error: "+e);
//            }
//            
//            //to display the total of the unpaid month
//            try{
//            String sql2="select tschoolfee.formonth, tschoolfee.foryear, DATE_FORMAT(tsiswa.StartingDate, \"%d\")\n" +
//                        "from tschoolfee\n" +
//                        "inner join tsiswa\n" +
//                        "where tsiswa.id=tschoolfee.studentid && tschoolfee.studentid="+TableClick+" &&\n" +
//                        "tschoolfee.id=(select max(id) from tschoolfee where studentid ="+TableClick+");";
//            
//            pst= conn.prepareStatement(sql2);
//            rs= pst.executeQuery();
//            
//            while(rs.next()){
//            //students payment date is the formonnth and foryear + starting date
//            String Months = rs.getString("tschoolfee.formonth"); // rs will get the string(name) in the column name from the table
//            String Years = rs.getString("tschoolfee.foryear");
//            String Days = rs.getString("DATE_FORMAT(tsiswa.StartingDate, \"%d\")");
//                
//            //parse string from above to int
//            int dayint = Integer.parseInt(Days);//starting date day from tsiswa
//            int monthint = Integer.parseInt(Months); // formonth from tschoolfee
//            int yearint = Integer.parseInt(Years); // foryear from tschoolfee
//            int todaydate = LocalDate.now().getDayOfMonth();
//            
//            //set today's date and selected date
//            LocalDate today = LocalDate.now();
//            LocalDate userday = LocalDate.of(yearint, monthint, dayint); 
//            
//            
//            //to calculate the difference of the date and month from starting date and current date            
//            Period diff = Period.between(userday, today); 
//            
//            System.out.println("Last payment: bulan: "+monthint+"tahun: "+yearint);
//            System.out.println("\nDifference between "+ userday +" and "+ today +": " 
//            +diff.getDays()+ "Day(s) " + diff.getMonths() +" Month(s) "+ diff.getYears() +" Year(s) ");            
//            
//            LMonth.setText(""+ diff.getMonths());
//            }
//            }catch(Exception e){
//            }
//            
//        }else if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER) {
//
//            }
    }//GEN-LAST:event_TUnpaidKeyReleased

    private void TFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchKeyReleased
        ShowData();
        DftTblModelSiswa= new DefaultTableModel();
        //adding the names of the columns
        DftTblModelSiswa.addColumn("Id");
        DftTblModelSiswa.addColumn("ClassName");
        DftTblModelSiswa.addColumn("TeacherId");
        DftTblModelSiswa.addColumn("Time");
        DftTblModelSiswa.addColumn("SchoolFee");
        TClass.setModel(DftTblModelSiswa);

        java.sql.Connection conn = new DbConnection().connect();
        try{
            CBSearch.getSelectedItem();
            String Search = (String) CBSearch.getSelectedItem();
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tclass where "+Search+" Like '%"+TFSearch.getText()+"%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                //LTotalStudents2.setText(SQL2);
                DftTblModelSiswa.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("ClassName"),
                    res.getString("TeacherId"),
                    res.getString("Time"),
                    res.getString("SchoolFee"),
                }
            );

        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TFSearchKeyReleased

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

    private void PClassesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseClicked
        String Username=Luser.getText();
        FormClass lf = new FormClass();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PClassesMouseClicked

    private void PClassesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseEntered
        
    }//GEN-LAST:event_PClassesMouseEntered

    private void PClassesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PClassesMouseExited
       
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

    private void PSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSettingsMouseEntered
        PSettings.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PSettingsMouseEntered

    private void PSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSettingsMouseExited
        PSettings.setBackground(new java.awt.Color(51, 103, 255));
    }//GEN-LAST:event_PSettingsMouseExited

    private void PSettingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSettingsMouseClicked
        String Username=Luser.getText();
        FormSettings lf = new FormSettings();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PSettingsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String ID= TFID.getText();
        String Date = LDate.getText();
        
        if(ID.equals("Dis")){
            JOptionPane.showMessageDialog(null, "Dismissed Class can't be updated or deleted");
        }else{
                try{
                    String sql= "Select count(id) from tsiswa where class='"+ID+"' and not status='Quit'";
                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();

                    if(rs.next()){
                        String Total= rs.getString("count(Id)");
                        if(Total.equals("0")){
                            int Input = JOptionPane.showConfirmDialog(null, "Dismiss class "+ID+"?", "Confirm Dismiss", JOptionPane.YES_NO_OPTION);
                            if(Input==JOptionPane.YES_OPTION){

                                try{
                                    String Quit = "update tsiswa set Class='Dis', Status='QUIT', QuitDate='"+Date+"' ,Reason='Dismissed' where Class='"+ID+"' ";
                                    pst=conn.prepareStatement(Quit);
                                    pst.executeUpdate();
                                    JOptionPane.showMessageDialog(null, "Status changed to quit");
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, "Quit: "+e);
                                }

                                try{
                                    String delete = "Delete from tclass where Id='"+ID+"'";
                                    pst=conn.prepareStatement(delete);
                                    pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
                                    JOptionPane.showMessageDialog(null, "Class Dismissed");
                                    ShowData();
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, "Dismiss: "+e);
                                    System.out.println("e "+ e);
                                }
                            }else{

                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "There are still  "+Total+"  Student(s)");
                            int Input = JOptionPane.showConfirmDialog(null, "If you dismiss the class\n All the Students of the class "+ID+" will be set to Quit.\nProceed anyway?", "Confirm Dismiss", JOptionPane.YES_NO_OPTION);
                            if(Input==JOptionPane.YES_OPTION){

                            // to change status to quit    
                            try{
                                String Quit = "update tsiswa set Class='Dis', Status='QUIT', QuitDate='"+Date+"' ,Reason='Dismissed' where Class='"+ID+"' ";
                                pst=conn.prepareStatement(Quit);
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null, "Status changed to quit");
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Quit: "+e);
                            }

                            // to dismiss class
                            try{
                                String delete = "Delete from tclass where Id='"+ID+"'";
                                pst=conn.prepareStatement(delete);
                                pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
                                JOptionPane.showMessageDialog(null, "Class Dismissed");
                                ShowData();
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Dismiss: "+e);
                                System.out.println("e "+ e);
                            }
                        }else{

                        }
                        }
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Dari awal: "+e);
                }
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFIDActionPerformed

    private void CBTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBTeacherActionPerformed

    }//GEN-LAST:event_CBTeacherActionPerformed

    private void CBTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBTeacherMouseExited

    }//GEN-LAST:event_CBTeacherMouseExited

    private void CBTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CBTeacherMouseClicked

    }//GEN-LAST:event_CBTeacherMouseClicked

    private void CBTeacherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBTeacherItemStateChanged

    }//GEN-LAST:event_CBTeacherItemStateChanged

    private void BQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BQuitActionPerformed
        Change();
    }//GEN-LAST:event_BQuitActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed
        String ID= TFID.getText();
        String Name= (String) CBClass.getSelectedItem();
        String Teacher= (String) CBTeacher.getSelectedItem();
        //String teacher = (String) CBTeacher.getSelectedItem();
        String Time= (String) CBTime.getSelectedItem();
        String SchoolFee= TFSchoolFee.getText();
        String BookFee= TFBookFee.getText();
        if(ID.equals("Dis")){
            JOptionPane.showMessageDialog(null, "Dismissed Class can't be updated or deleted");
        }else{
            try{


                String sql = "update tclass set ClassName='"+Name+"', TeacherID='"+Teacher+"', Time='"+Time+"', SchoolFee='"+SchoolFee+"', BookFee='"+BookFee+"'  where Id='"+ID+"' ";

                pst=conn.prepareStatement(sql);
                pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

                JOptionPane.showMessageDialog(null, "Changed");
                ShowData();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println("e "+ e);
            }
        }
    }//GEN-LAST:event_BSaveActionPerformed

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
        String ID=TFID.getText();
        String ClassName= (String) CBClass.getSelectedItem();
        String TeacherId= (String) CBTeacher.getSelectedItem();
        String Time= (String) CBTime.getSelectedItem();
        String Schoolfee= TFSchoolFee.getText();
        String BookFee= TFBookFee.getText();
                
        if(ID.isEmpty() || ClassName.isEmpty() || TeacherId.isEmpty() || Time.isEmpty() || Schoolfee.isEmpty() || BookFee.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fields must not be Empty");
        }else{
        try{
            String sql = "Insert into tclass ( Id, ClassName, TeacherID, Time, SchoolFee, BookFee, AorB) values (?,?,?,?,?,?,?)";
            //Id can be emptied.
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, ID); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, ClassName);
            pst.setString(3, TeacherId);
            pst.setString(4, Time);
            pst.setString(5, Schoolfee);
            pst.setString(6, BookFee);
            pst.setString(7, "A");
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            ShowData();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
        }
    }//GEN-LAST:event_BAddActionPerformed

    private void TFSchoolFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSchoolFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSchoolFeeActionPerformed

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

    private void PAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PAllKeyPressed
        
    }//GEN-LAST:event_PAllKeyPressed

    private void PAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PAllMouseClicked
       ShowData();
    }//GEN-LAST:event_PAllMouseClicked

    private void PAllMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PAllMouseEntered
        PAll.setBackground(new java.awt.Color(1,51,153));
    }//GEN-LAST:event_PAllMouseEntered

    private void PAllMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PAllMouseExited
        PAll.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PAllMouseExited

    private void TFBookFeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBookFeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBookFeeActionPerformed

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
                new FormClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BQuit;
    private javax.swing.JButton BSave;
    private javax.swing.JComboBox<String> CBClass;
    private javax.swing.JComboBox<String> CBSearch;
    private javax.swing.JComboBox<String> CBTeacher;
    private javax.swing.JComboBox<String> CBTime;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LTeacher;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel PAll;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClass2;
    private javax.swing.JPanel PClassMain;
    private javax.swing.JScrollPane PClassScroll;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PEditClass;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenu;
    private javax.swing.JPanel PMenuSiswa3;
    private javax.swing.JPanel PMenuSiswa4;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JScrollPane PScrollClass;
    private javax.swing.JPanel PSettings;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JPanel PUnpaid;
    private javax.swing.JLabel SchoolFee;
    private javax.swing.JLabel SchoolFee2;
    private javax.swing.JTable TClass;
    private javax.swing.JTextField TFBookFee;
    private javax.swing.JTextField TFID;
    private javax.swing.JTextField TFSchoolFee;
    private javax.swing.JTextField TFSearch;
    private javax.swing.JTable TUnpaid;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
