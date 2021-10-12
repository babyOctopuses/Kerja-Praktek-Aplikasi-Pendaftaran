package Form;

import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import java.awt.*; // awt.print is used for printing
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

public class FormTeacher extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private DefaultTableModel DftTblModelSfee;
    private String SQL;
    private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    ButtonGroup buttonGroup;
    
    public FormTeacher() {
        initComponents();
        ShowData();
        ShowDataQuit();
        ShowUser();
        conn = DbConnection.connect(); // since you declare to public form edit,make sure to declare it in the next one too or else, conn will be null
        CurrentDate();        
        RBMale.setActionCommand("Male");
        RBFemale.setActionCommand("Female");
        autonumber();
        buttonGroup=new ButtonGroup();
        buttonGroup.add(RBMale);
        buttonGroup.add(RBFemale);
              
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
        BAdd.setVisible(false);
        BSave.setVisible(false);
        BQuit.setVisible(false);
        PUserMenu.setVisible(false);
        PScrollUser.setVisible(false);
        PScrollTUser.setVisible(false);
    }
    
    public void pass(String User){
        Luser.setText(User);
    }
    
    public void hidePannel(){
        PEditTeacher.setVisible(false);
    }
    
    public void autonumber(){
        try{    
            String sql="select Id from tteacher order by "+"Id desc";
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
            TFID.setText(AN);
            }
        }catch(Exception e){
        
        }
    }
    
    public void ShowUser(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Username");
            DftTblModelSiswa.addColumn("Status");
            
            // to fill an empty table on the page with the table from the DftTblModel
            TUser.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       //???
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tuser";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Username"),
                           res.getString("Status"),
                       });
                       }
                
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    }
    
    public void ShowData(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Name");
            DftTblModelSiswa.addColumn("Gender");
            DftTblModelSiswa.addColumn("Address");
            DftTblModelSiswa.addColumn("Phone");
            DftTblModelSiswa.addColumn("Birthday");
            DftTblModelSiswa.addColumn("Birthplace");
            DftTblModelSiswa.addColumn("Religion");
            DftTblModelSiswa.addColumn("StartingDate");
            DftTblModelSiswa.addColumn("Category");
            DftTblModelSiswa.addColumn("Status");
            
            // to fill an empty table on the page with the table from the DftTblModel
            Tteacher.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       //???
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tteacher where Status='ACTIVE'";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Name"),
                           res.getString("Gender"),
                           res.getString("Address"),
                           res.getString("Phone"),
                           res.getString("Birthday"),
                           res.getString("Birthplace"),
                           res.getString("Religion"),
                           res.getString("StartingDate"),
                           res.getString("Category"),
                           res.getString("Status"),
                           
                       });
                       }
             Tteacher.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
    }
    
    public void ShowDataQuit(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Name");
            DftTblModelSiswa.addColumn("Gender");
            DftTblModelSiswa.addColumn("Address");
            DftTblModelSiswa.addColumn("Phone");
            DftTblModelSiswa.addColumn("Birthday");
            DftTblModelSiswa.addColumn("Birthplace");
            DftTblModelSiswa.addColumn("Religion");
            DftTblModelSiswa.addColumn("StartingDate");
            DftTblModelSiswa.addColumn("Category");
            DftTblModelSiswa.addColumn("Status");
            
            // to fill an empty table on the page with the table from the DftTblModel
            Tteacher1.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       //???
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tteacher where Status='Quit'";
                       //???
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Name"),
                           res.getString("Gender"),
                           res.getString("Address"),
                           res.getString("Phone"),
                           res.getString("Birthday"),
                           res.getString("Birthplace"),
                           res.getString("Religion"),
                           res.getString("StartingDate"),
                           res.getString("Category"),
                           res.getString("Status"),
                           
                       });
                       }
             Tteacher1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        PMenuSiswa2 = new javax.swing.JPanel();
        TFSearch = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        CBSearch = new javax.swing.JComboBox<>();
        PScrollTeacher = new javax.swing.JScrollPane();
        PEditTeacher = new javax.swing.JPanel();
        TFBirthday = new com.toedter.calendar.JDateChooser();
        RBMale = new javax.swing.JRadioButton();
        RBFemale = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        TFPhone = new javax.swing.JTextField();
        TFBirthPlace = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        CBCategory = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        LStatus = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TFSDate = new com.toedter.calendar.JDateChooser();
        BSave = new javax.swing.JButton();
        BQuit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Ltotal = new javax.swing.JLabel();
        BAdd = new javax.swing.JButton();
        CBReligion = new javax.swing.JComboBox<>();
        LCode = new javax.swing.JLabel();
        TFID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TFAddress = new javax.swing.JTextArea();
        PUserMenu = new javax.swing.JPanel();
        TFSearch1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        CBSearch1 = new javax.swing.JComboBox<>();
        PScrollTUser = new javax.swing.JScrollPane();
        TUser = new javax.swing.JTable();
        PScrollUser = new javax.swing.JScrollPane();
        PUser = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        TFUsername = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        BAddUser = new javax.swing.JButton();
        BDeleteUser = new javax.swing.JButton();
        CBStatus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        CBShow = new javax.swing.JCheckBox();
        TFPassword = new javax.swing.JPasswordField();
        TFCPassword = new javax.swing.JPasswordField();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tteacher1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tteacher = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        MenuPanel = new javax.swing.JPanel();
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
        jLabel15 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LTime1 = new javax.swing.JLabel();
        LDate = new javax.swing.JLabel();

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
        setPreferredSize(new java.awt.Dimension(1280, 720));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkGradientFocus(600);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));

        PMenuSiswa2.setBackground(new java.awt.Color(51, 102, 255));

        TFSearch.setBackground(new java.awt.Color(0, 51, 153));
        TFSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearch.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFSearch.setText("Search Teacher's Data");
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

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        CBSearch.setBackground(new java.awt.Color(204, 153, 255));
        CBSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Name", "Birthday", "Address" }));
        CBSearch.setBorder(null);

        javax.swing.GroupLayout PMenuSiswa2Layout = new javax.swing.GroupLayout(PMenuSiswa2);
        PMenuSiswa2.setLayout(PMenuSiswa2Layout);
        PMenuSiswa2Layout.setHorizontalGroup(
            PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa2Layout.createSequentialGroup()
                .addContainerGap(588, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        PMenuSiswa2Layout.setVerticalGroup(
            PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PMenuSiswa2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        PEditTeacher.setBackground(new java.awt.Color(255, 255, 255));

        TFBirthday.setDateFormatString("yyyy-MM-dd");

        RBMale.setBackground(new java.awt.Color(255, 255, 255));
        Gender.add(RBMale);
        RBMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RBMale.setText("Male");
        RBMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBMaleActionPerformed(evt);
            }
        });

        RBFemale.setBackground(new java.awt.Color(255, 255, 255));
        Gender.add(RBFemale);
        RBFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RBFemale.setText("Female");
        RBFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBFemaleActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Name");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("ID:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Gender");

        TFName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNameActionPerformed(evt);
            }
        });

        TFPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFPhoneActionPerformed(evt);
            }
        });
        TFPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFPhoneKeyTyped(evt);
            }
        });

        TFBirthPlace.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFBirthPlace.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFBirthPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBirthPlaceActionPerformed(evt);
            }
        });
        TFBirthPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFBirthPlaceKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Phone");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Address");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("BirthPlace");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Birthday");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Religion");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Category");

        CBCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FULL TIME", "PART TIME" }));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Status");

        LStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LStatus.setText("PREREG");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Starting Date");

        TFSDate.setDateFormatString("yyyy-MM-dd");

        BSave.setBackground(new java.awt.Color(255, 255, 255));
        BSave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BSave.setText("Save");
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });

        BQuit.setBackground(new java.awt.Color(255, 255, 255));
        BQuit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BQuit.setText("Quit");
        BQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BQuitActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Class Total");

        Ltotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Ltotal.setText("PREREG");

        BAdd.setBackground(new java.awt.Color(255, 255, 255));
        BAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BAdd.setText("Add");
        BAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        BAdd.setBorderPainted(false);
        BAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddActionPerformed(evt);
            }
        });

        CBReligion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buddha", "Islam", "Kristen", "Katolik", "Hindu", "Konghucu" }));
        CBReligion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBReligionActionPerformed(evt);
            }
        });

        LCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCode.setText("Code");

        TFID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFIDActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 102, 255)));

        TFAddress.setColumns(20);
        TFAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFAddress.setRows(5);
        jScrollPane1.setViewportView(TFAddress);

        javax.swing.GroupLayout PEditTeacherLayout = new javax.swing.GroupLayout(PEditTeacher);
        PEditTeacher.setLayout(PEditTeacherLayout);
        PEditTeacherLayout.setHorizontalGroup(
            PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PEditTeacherLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BQuit)
                .addGap(62, 62, 62))
            .addGroup(PEditTeacherLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PEditTeacherLayout.createSequentialGroup()
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel3)
                            .addComponent(jLabel21)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36))
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PEditTeacherLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PEditTeacherLayout.createSequentialGroup()
                                        .addComponent(LCode)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(TFID))
                                    .addComponent(Ltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PEditTeacherLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(LStatus))
                            .addGroup(PEditTeacherLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PEditTeacherLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(24, 24, 24)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBReligion, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PEditTeacherLayout.createSequentialGroup()
                                    .addComponent(RBMale)
                                    .addGap(27, 27, 27)
                                    .addComponent(RBFemale))
                                .addComponent(TFBirthPlace)
                                .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TFBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TFSDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                .addComponent(CBCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        PEditTeacherLayout.setVerticalGroup(
            PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PEditTeacherLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PEditTeacherLayout.createSequentialGroup()
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(LCode)
                            .addComponent(TFID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Ltotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(RBMale, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RBFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TFBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TFBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(CBReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CBCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(TFSDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(LStatus))
                .addGap(18, 18, 18)
                .addGroup(PEditTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BSave)
                    .addComponent(BQuit)
                    .addComponent(BAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        TFBirthday.getAccessibleContext().setAccessibleDescription("");

        PScrollTeacher.setViewportView(PEditTeacher);

        PUserMenu.setBackground(new java.awt.Color(51, 102, 255));

        TFSearch1.setBackground(new java.awt.Color(0, 51, 153));
        TFSearch1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearch1.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFSearch1.setBorder(null);
        TFSearch1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TFSearch1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                TFSearch1FocusLost(evt);
            }
        });
        TFSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFSearch1ActionPerformed(evt);
            }
        });
        TFSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearch1KeyReleased(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        CBSearch1.setBackground(new java.awt.Color(204, 153, 255));
        CBSearch1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username" }));
        CBSearch1.setBorder(null);

        javax.swing.GroupLayout PUserMenuLayout = new javax.swing.GroupLayout(PUserMenu);
        PUserMenu.setLayout(PUserMenuLayout);
        PUserMenuLayout.setHorizontalGroup(
            PUserMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PUserMenuLayout.createSequentialGroup()
                .addContainerGap(588, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        PUserMenuLayout.setVerticalGroup(
            PUserMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PUserMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PUserMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        TUser.setModel(new javax.swing.table.DefaultTableModel(
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
        PScrollTUser.setViewportView(TUser);

        PUser.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Username");

        TFUsername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFUsernameActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Password");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Confirm Password");

        BAddUser.setText("Add");
        BAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAddUserActionPerformed(evt);
            }
        });

        BDeleteUser.setText("Delete");
        BDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteUserActionPerformed(evt);
            }
        });

        CBStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "User", "Admin" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Status");

        CBShow.setText("Show Password");
        CBShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBShowActionPerformed(evt);
            }
        });

        TFPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));

        TFCPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));

        javax.swing.GroupLayout PUserLayout = new javax.swing.GroupLayout(PUser);
        PUser.setLayout(PUserLayout);
        PUserLayout.setHorizontalGroup(
            PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PUserLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CBShow)
                    .addComponent(TFPassword)
                    .addComponent(CBStatus, 0, 182, Short.MAX_VALUE)
                    .addComponent(TFUsername)
                    .addComponent(TFCPassword)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PUserLayout.createSequentialGroup()
                        .addComponent(BAddUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BDeleteUser)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        PUserLayout.setVerticalGroup(
            PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PUserLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(TFUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFCPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel38))
                .addGap(7, 7, 7)
                .addComponent(CBShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAddUser)
                    .addComponent(BDeleteUser))
                .addContainerGap())
        );

        PScrollUser.setViewportView(PUser);

        Tteacher1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Tteacher1.setModel(new javax.swing.table.DefaultTableModel(
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
        Tteacher1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tteacher1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Tteacher1MouseEntered(evt);
            }
        });
        Tteacher1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Tteacher1KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(Tteacher1);

        Tteacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Tteacher.setModel(new javax.swing.table.DefaultTableModel(
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
        Tteacher.setAutoscrolls(false);
        Tteacher.setRowMargin(5);
        Tteacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TteacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TteacherMouseEntered(evt);
            }
        });
        Tteacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TteacherKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(Tteacher);

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Quit Teachers");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Active Teachers");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PMenuSiswa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PUserMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(PScrollUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(PScrollTUser, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PScrollTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(PMenuSiswa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PScrollTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(PUserMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PScrollUser, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PScrollTUser, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(kGradientPanel1);

        MenuPanel.setBackground(new java.awt.Color(51, 102, 255));

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

        PTeacher.setBackground(new java.awt.Color(102, 204, 255));
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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(29, 29, 29)
                .addComponent(jLabel19)
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
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

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel18)
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel23.setText("Change Password");

        javax.swing.GroupLayout PCPassLayout = new javax.swing.GroupLayout(PCPass);
        PCPass.setLayout(PCPassLayout);
        PCPassLayout.setHorizontalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPassLayout.setVerticalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome ,");

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
                            .addComponent(jLabel24)
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
                .addComponent(jLabel24)
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
            .addComponent(PStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
            .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNameActionPerformed

    private void TFPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFPhoneActionPerformed

    private void TFBirthPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBirthPlaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBirthPlaceActionPerformed

    private void RBMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBMaleActionPerformed

    private void RBFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBFemaleActionPerformed

    private void BAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddActionPerformed
       try{
           String sql = "Insert into tteacher ( Name, Gender, Address, Phone, Birthday, Birthplace, Religion, StartingDate, Category, Status, Id) values (?,?,?,?,?,?,?,?,?,?,?)";
            //Id can be emptied. 
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql); 
            pst.setString(1, TFName.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, buttonGroup.getSelection().getActionCommand().toString());
            pst.setString(3, TFAddress.getText());
            pst.setString(4, TFPhone.getText());
            pst.setString(6, TFBirthPlace.getText());
            pst.setString(5, ((JTextField)TFBirthday.getDateEditor().getUiComponent()).getText());
           // pst.setString(7, TANote.getText());
            pst.setString(7, (String) CBReligion.getSelectedItem());
            pst.setString(8, ((JTextField)TFSDate.getDateEditor().getUiComponent()).getText());
            pst.setString(9, (String) CBCategory.getSelectedItem());
            pst.setString(10, "ACTIVE");
            pst.setString(11, TFID.getText());
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            ShowData();
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
    }//GEN-LAST:event_BAddActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed
           try{ 
           String ID= TFID.getText();
           String Name= TFName.getText();
           String Gender= buttonGroup.getSelection().getActionCommand().toString();
           String Birthplace= TFBirthPlace.getText();
           String Address= TFAddress.getText();
           String Birthday= ((JTextField)TFBirthday.getDateEditor().getUiComponent()).getText();
           String StartingDate= ((JTextField)TFSDate.getDateEditor().getUiComponent()).getText();
           String Phone= TFPhone.getText();
           String Religion= (String) CBReligion.getSelectedItem();
           String sql = "update tteacher set Name='"+Name+"', Gender='"+Gender+"', Address='"+Address+"', Birthplace='"+Birthplace+"', Birthday='"+Birthday+"', Phone='"+Phone+"', Religion='"+Religion+"', StartingDate='"+StartingDate+"'  where Id='"+ID+"' ";
           
           pst=conn.prepareStatement(sql);
           pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
           System.out.println("1 "+pst.executeUpdate());
           System.out.println("2 "+pst);
           JOptionPane.showMessageDialog(null, "Updated");
           ShowData();
           //UpdateTable(); 
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
    }//GEN-LAST:event_BSaveActionPerformed

    private void BQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BQuitActionPerformed
        try{String date = LDate.getText();
            String ID = TFID.getText(); 
            String sql = "update tteacher set Status='QUIT', quitdate='"+date+"' WHERE Id='"+ID+"'";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, ID+" has quit");
            ShowData();
            ShowDataQuit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_BQuitActionPerformed

    private void TteacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TteacherMouseEntered
        
    }//GEN-LAST:event_TteacherMouseEntered

    private void TteacherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TteacherKeyReleased
//        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN ||evt.getKeyCode()==java.awt.event.KeyEvent.VK_UP){
//            try{
//            int row = Tteacher.getSelectedRow(); // to get the selected row
//            String TableClick= (Tteacher.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
//            //(row, 0) 0 means the first column
//            String sql = "select * from tteacher where Id='"+TableClick+"' ";
//            pst = conn.prepareStatement(sql);
//            rs= pst.executeQuery();
//            if(rs.next()){
//                String Id = rs.getString("Id"); // get the string of Id from the table
//                LID.setText(Id); // set the textfield of Id with the value from add2
//                String Name = rs.getString("Name");
//                TFName.setText(Name);
//                String Gender = rs.getString("Gender");
//                if(Gender.equals("Female")){
//                    RBFemale.setSelected(true);
//                }else if(Gender.equals("Male")){
//                    RBMale.setSelected(true);
//                }
//                String Address = rs.getString("Address");
//                TFAddress.setText(Address);
//                String Phone = rs.getString("Phone");
//                TFPhone.setText(Phone);
//                String Birthday = rs.getString("Birthday");
//                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Birthday);
//                TFBirthday.setDate(date);
//                String Birthplace = rs.getString("Birthplace");
//                TFBirthPlace.setText(Birthplace);
//                String Religion = rs.getString("Religion");
//                TFReligion.setText(Religion);
//                String StartingDate = rs.getString("StartingDate");
//                java.util.Date Sdate = new SimpleDateFormat("yyyy-MM-dd").parse(StartingDate);
//                TFSDate.setDate(Sdate);
//                String Category = rs.getString("Category");
//                CBCategory.setSelectedItem(Category);
//                String Status = rs.getString("Status");
//                LStatus.setText(Status);
//
//                String total = "select count(Id) from tclass where TeacherId='"+TableClick+"' ";
//                pst = conn.prepareStatement(total);
//                rs= pst.executeQuery();
//                if(rs.next()){
//                    String Total = rs.getString("count(Id)");
//                    Ltotal.setText(Total);
//                }
//
//            }
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null, e);
//        }
//        }else {
//        
//        }
    }//GEN-LAST:event_TteacherKeyReleased

    private void TteacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TteacherMouseClicked
        try{
            int row = Tteacher.getSelectedRow(); // to get the selected row
            String TableClick= (Tteacher.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tteacher where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Name = rs.getString("Name");
                TFName.setText(Name);
                String Gender = rs.getString("Gender");
                if(Gender.equals("Female")){
                    RBFemale.setSelected(true);
                }else if(Gender.equals("Male")){
                    RBMale.setSelected(true);
                }
                String Address = rs.getString("Address");
                TFAddress.setText(Address);
                String Phone = rs.getString("Phone");
                TFPhone.setText(Phone);
                String Birthday = rs.getString("Birthday");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Birthday);
                TFBirthday.setDate(date);
                String Birthplace = rs.getString("Birthplace");
                TFBirthPlace.setText(Birthplace);
                String Religion = rs.getString("Religion");
                CBReligion.setSelectedItem(Religion);
                String StartingDate = rs.getString("StartingDate");
                java.util.Date Sdate = new SimpleDateFormat("yyyy-MM-dd").parse(StartingDate);
                TFSDate.setDate(Sdate);
                String Category = rs.getString("Category");
                CBCategory.setSelectedItem(Category);
                String Status = rs.getString("Status");
                LStatus.setText(Status);

                String total = "select count(Id) from tclass where TeacherId='"+TableClick+"' ";
                pst = conn.prepareStatement(total);
                rs= pst.executeQuery();
                if(rs.next()){
                    String Total = rs.getString("count(Id)");
                    Ltotal.setText(Total);
                }

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TteacherMouseClicked

    private void TFSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchFocusGained
        // TODO add your handling code here:
        if(TFSearch.getText().equals("Search Teacher's Data")){
            TFSearch.setText("");
            TFSearch.setForeground( new Color(255, 255, 255));
        }
    }//GEN-LAST:event_TFSearchFocusGained

    private void TFSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearchFocusLost
        // TODO add your handling code here:
        if(TFSearch.getText().equals("")){
            TFSearch.setText("Search Teacher's Data");
            TFSearch.setForeground( new Color(153, 204, 255));}
    }//GEN-LAST:event_TFSearchFocusLost

    private void TFSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearchActionPerformed

    private void TFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchKeyReleased
        ShowData();
        String Search = (String) CBSearch.getSelectedItem();
        String Searchbar = (String) TFSearch.getText();
        DftTblModelSiswa= new DefaultTableModel();
        DftTblModelSiswa.addColumn("Id");
        DftTblModelSiswa.addColumn("Name");
        DftTblModelSiswa.addColumn("Gender");
        DftTblModelSiswa.addColumn("Address");
        DftTblModelSiswa.addColumn("Phone");
        DftTblModelSiswa.addColumn("Birthday");
        DftTblModelSiswa.addColumn("Birthplace");
        DftTblModelSiswa.addColumn("Religion");
        DftTblModelSiswa.addColumn("Starting Date");
        DftTblModelSiswa.addColumn("Status");
        DftTblModelSiswa.addColumn("Category");
        Tteacher.setModel(DftTblModelSiswa);
        java.sql.Connection conn = new DbConnection().connect();
        try{
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tteacher where "+Search+"='"+Searchbar+"'";
           
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                DftTblModelSiswa.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("Name"),
                    res.getString("Gender"),
                    res.getString("Address"),
                    res.getString("Phone"),
                    res.getString("Birthday"),
                    res.getString("Birthplace"),
                    res.getString("Religion"),
                    res.getString("StartingDate"),
                    res.getString("Quitdate"),
                    res.getString("Category"),

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
        
    }//GEN-LAST:event_PTeacherMouseEntered

    private void PTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherMouseExited
        
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

    private void PStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseEntered
        PStudents.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PStudentsMouseEntered

    private void PStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseExited
       PStudents.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PStudentsMouseExited

    private void TFSearch1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearch1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearch1FocusGained

    private void TFSearch1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TFSearch1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearch1FocusLost

    private void TFSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFSearch1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFSearch1ActionPerformed

    private void TFSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearch1KeyReleased
        ShowData();
        String Search = (String) CBSearch1.getSelectedItem();
        String Searchbar = (String) TFSearch1.getText();
        DftTblModelSiswa= new DefaultTableModel();
        DftTblModelSiswa.addColumn("Id");
        DftTblModelSiswa.addColumn("UserName");
        DftTblModelSiswa.addColumn("Status");
       TUser.setModel(DftTblModelSiswa);
        java.sql.Connection conn = new DbConnection().connect();
        try{
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tuser where "+Search+"='"+Searchbar+"'";
           
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                DftTblModelSiswa.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("Username"),
                    res.getString("Status")
            }
            );

        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_TFSearch1KeyReleased

    private void TFUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFUsernameActionPerformed

    private void CBShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBShowActionPerformed
        if(CBShow.isSelected()){
            TFPassword.setEchoChar((char)0);
            TFCPassword.setEchoChar((char)0);
        }else{
            TFPassword.setEchoChar('*');
            TFCPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_CBShowActionPerformed

    private void BAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAddUserActionPerformed
        String Status = (String)CBStatus.getSelectedItem();
        String Username1 = TFUsername.getText();
        String Password = TFPassword.getText();
        String CPassword = TFCPassword.getText();
                
        if(Status.isEmpty() || Username1.isEmpty() || Password.isEmpty() || CPassword.isEmpty()){
        JOptionPane.showMessageDialog(null, "Fields must not be empty");
        }else{
            if(Password.equals(CPassword)){
               if (Password.length() < 6){
                   JOptionPane.showMessageDialog(null, "Password must contain minimum 6 characters");
               }else{ 
                try{
                    String sql = "Insert into tuser ( Username, Password, Status) values (?,?,?)";
                    //Id can be emptied. 
                    //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
                    pst = conn.prepareStatement(sql); 
                    pst.setString(1, TFUsername.getText()); //use getText() to get the text from the TextField and pass it to the table
                    pst.setString(2, TFPassword.getText());
                    pst.setString(3, (String) CBStatus.getSelectedItem());
                    pst.execute(); // execute the pst
                    JOptionPane.showMessageDialog(null, "Saved");
                    ShowUser();
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                   System.out.println("e "+ e);
               }
            }
           }else{
                JOptionPane.showMessageDialog(null, "Password and Confirm Password don't match"); 
           }
       }
    }//GEN-LAST:event_BAddUserActionPerformed

    private void BDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteUserActionPerformed
        String Username1 = TFUsername.getText();
        
        try{
            int row = TUser.getSelectedRow(); // to get the selected row
            String TableClick= (TUser.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            String sql = "Delete from tuser where id='"+TableClick+"'";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            ShowUser();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }//GEN-LAST:event_BDeleteUserActionPerformed

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

    private void TFPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFPhoneKeyTyped
        char enter = evt.getKeyChar();
        
        if(!(Character.isDigit(enter)) || (enter==KeyEvent.VK_BACK_SPACE || enter==KeyEvent.VK_DELETE)){
//            JOptionPane.showMessageDialog(null, "Numbers only","Warning", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }else{
            
        }
    }//GEN-LAST:event_TFPhoneKeyTyped

    private void TFBirthPlaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFBirthPlaceKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            
        }else{
            JOptionPane.showMessageDialog(null, "Letters only","Warning", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_TFBirthPlaceKeyTyped

    private void CBReligionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBReligionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBReligionActionPerformed

    private void Tteacher1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tteacher1MouseClicked
        try{
            int row = Tteacher1.getSelectedRow(); // to get the selected row
            String TableClick= (Tteacher1.getModel().getValueAt(row,0).toString()); // to give it the function when we click on it, it can select and convert to string
            //(row, 0) 0 means the first column
            String sql = "select * from tteacher where Id='"+TableClick+"' ";
            pst = conn.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                String Id = rs.getString("Id"); // get the string of Id from the table
                TFID.setText(Id); // set the textfield of Id with the value from add2
                String Name = rs.getString("Name");
                TFName.setText(Name);
                String Gender = rs.getString("Gender");
                if(Gender.equals("Female")){
                    RBFemale.setSelected(true);
                }else if(Gender.equals("Male")){
                    RBMale.setSelected(true);
                }
                String Address = rs.getString("Address");
                TFAddress.setText(Address);
                String Phone = rs.getString("Phone");
                TFPhone.setText(Phone);
                String Birthday = rs.getString("Birthday");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Birthday);
                TFBirthday.setDate(date);
                String Birthplace = rs.getString("Birthplace");
                TFBirthPlace.setText(Birthplace);
                String Religion = rs.getString("Religion");
                CBReligion.setSelectedItem(Religion);
                String StartingDate = rs.getString("StartingDate");
                java.util.Date Sdate = new SimpleDateFormat("yyyy-MM-dd").parse(StartingDate);
                TFSDate.setDate(Sdate);
                String Category = rs.getString("Category");
                CBCategory.setSelectedItem(Category);
                String Status = rs.getString("Status");
                LStatus.setText(Status);

//                String total = "select count(Id) from tclass where TeacherId='"+TableClick+"' ";
//                pst = conn.prepareStatement(total);
//                rs= pst.executeQuery();
//                if(rs.next()){
//                    String Total = rs.getString("count(Id)");
//                    Ltotal.setText(Total);
//                }

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_Tteacher1MouseClicked

    private void Tteacher1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tteacher1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Tteacher1MouseEntered

    private void Tteacher1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tteacher1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_Tteacher1KeyReleased

    private void TFIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFIDActionPerformed

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
                new FormTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAdd;
    private javax.swing.JButton BAddUser;
    private javax.swing.JButton BDeleteUser;
    private javax.swing.JButton BQuit;
    private javax.swing.JButton BSave;
    private javax.swing.JComboBox<String> CBCategory;
    private javax.swing.JComboBox<String> CBReligion;
    private javax.swing.JComboBox<String> CBSearch;
    private javax.swing.JComboBox<String> CBSearch1;
    private javax.swing.JCheckBox CBShow;
    private javax.swing.JComboBox<String> CBStatus;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JLabel LCode;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LStatus;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel Ltotal;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PEditTeacher;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenuSiswa2;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JScrollPane PScrollTUser;
    private javax.swing.JScrollPane PScrollTeacher;
    private javax.swing.JScrollPane PScrollUser;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JPanel PUser;
    private javax.swing.JPanel PUserMenu;
    private javax.swing.JRadioButton RBFemale;
    private javax.swing.JRadioButton RBMale;
    private javax.swing.JTextArea TFAddress;
    private javax.swing.JTextField TFBirthPlace;
    private com.toedter.calendar.JDateChooser TFBirthday;
    private javax.swing.JPasswordField TFCPassword;
    private javax.swing.JTextField TFID;
    private javax.swing.JTextField TFName;
    private javax.swing.JPasswordField TFPassword;
    private javax.swing.JTextField TFPhone;
    private com.toedter.calendar.JDateChooser TFSDate;
    private javax.swing.JTextField TFSearch;
    private javax.swing.JTextField TFSearch1;
    private javax.swing.JTextField TFUsername;
    private javax.swing.JTable TUser;
    private javax.swing.JTable Tteacher;
    private javax.swing.JTable Tteacher1;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
