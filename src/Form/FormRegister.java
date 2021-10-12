package Form;

import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import java.awt.Component;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;



public class FormRegister extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private DefaultTableModel DftTblModelSfee;
    private String SQL;
    private String F1 = null;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    ButtonGroup buttonGroup;
    
    public FormRegister() {
        initComponents();
        ShowData();
        conn = DbConnection.connect(); // since you declare to public form edit,make sure to declare it in the next one too or else, conn will be null
        CurrentDate();        
        autonumber();
        RBMale.setActionCommand("Male");
        RBFemale.setActionCommand("Female");
        buttonGroup=new ButtonGroup();
        buttonGroup.add(RBMale);
        buttonGroup.add(RBFemale);
        autonumber();
        FillCombo();      
    }
    //I changed from pass to username, let's see if it affects other forms
    public void username(String User){
    Luser.setText(User);
    }
    
    public void FillCombo(){
         try{
            String sql = "select Status from tcategory";
            pst= conn.prepareStatement(sql); // pst and rs are must-have commnands to load data into jtable
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Status   = rs.getString("Status");
                CBCategory.addItem(Status); //add the name from the string name to the combobox
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
        
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
            LID.setText(AN);
            }
        }catch(Exception e){
        
        }
    }
    
    public void FillComboSearch(){
         try{
            String sql = "Select * from INFORMATION_SCHEMA.columns WHERE TABLE_NAME='tregister';";
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
     public void ShowData(){
            DftTblModelSiswa= new DefaultTableModel();
            //adding the names of the columns
            DftTblModelSiswa.addColumn("Id");
            DftTblModelSiswa.addColumn("Name");
            DftTblModelSiswa.addColumn("Gender");
            DftTblModelSiswa.addColumn("Phone");
            DftTblModelSiswa.addColumn("Address");
            DftTblModelSiswa.addColumn("Birthplace");
            DftTblModelSiswa.addColumn("Date Of Birth");
            DftTblModelSiswa.addColumn("Religion");
            DftTblModelSiswa.addColumn("Occupation");
            DftTblModelSiswa.addColumn("Note");
            DftTblModelSiswa.addColumn("Category");
            // to fill an empty table on the page with the table from the DftTblModel
            Tsiswa.setModel(DftTblModelSiswa);
            // to connect to the db in DatabaseConnection so that we don't need to type the whole code
            java.sql.Connection conn = new DbConnection().connect();
            try{
                       java.sql.Statement stmt = conn.createStatement();
                       SQL ="select * from tsiswa where status='prereg'";
                       java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
                           res.getString("Id"),
                           res.getString("Name"),
                           res.getString("Gender"),
                           res.getString("Phone"),
                           res.getString("Address"),
                           res.getString("Birthplace"),
                           res.getString("Birthday"),
                           res.getString("Religion"),
                           res.getString("Occupation"),
                           res.getString("Note"),
                           res.getString("Category"),
                        });
                       }
                Tsiswa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
             }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
             }
            //resizeColumnWidth(Tsiswa);
            //Tsiswa.revalidate();
    }
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        Gender = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        TFBirthday = new com.toedter.calendar.JDateChooser();
        RBMale = new javax.swing.JRadioButton();
        RBFemale = new javax.swing.JRadioButton();
        LID = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        TFPhone = new javax.swing.JTextField();
        TFBirthPlace = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TANote = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        BRegister = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        LStatus = new javax.swing.JLabel();
        LCode = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        CBCategory = new javax.swing.JComboBox<>();
        CBReligion = new javax.swing.JComboBox<>();
        CBOccupation = new javax.swing.JComboBox<>();
        LPhone = new javax.swing.JLabel();
        LBirthPlace = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TFAddress = new javax.swing.JTextArea();
        BEnroll = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        PMenuSiswa2 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        TFSearch = new javax.swing.JTextField();
        CBSearch = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        Tsiswa = new javax.swing.JTable();
        BDelete = new javax.swing.JButton();
        JMenu = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
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
        jLabel24 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        PLogOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PCPass2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LDate1 = new javax.swing.JLabel();
        LTime2 = new javax.swing.JLabel();

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

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1280, 720));

        kGradientPanel1.setBackground(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkEndColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setkGradientFocus(600);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        LID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LID.setText("ID");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Name");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("ID");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Gender");

        TFName.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TFName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNameActionPerformed(evt);
            }
        });

        TFPhone.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
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

        TFBirthPlace.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TFBirthPlace.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFBirthPlace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFBirthPlaceActionPerformed(evt);
            }
        });
        TFBirthPlace.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFBirthPlaceKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TFBirthPlaceKeyTyped(evt);
            }
        });

        TANote.setColumns(20);
        TANote.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TANote.setForeground(new java.awt.Color(153, 153, 153));
        TANote.setRows(5);
        jScrollPane1.setViewportView(TANote);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Birthday");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Religion");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Occupation");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Note");

        BRegister.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BRegister.setText("Register");
        BRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRegisterActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Category");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Status");

        LStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LStatus.setText("PREREG");

        LCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LCode.setText("Code");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("Phone");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Address");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Birth Place");

        CBCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular" }));

        CBReligion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBReligion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buddha", "Islam", "Kristen", "Katolik", "Hindu", "Konghucu" }));

        CBOccupation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBOccupation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Karyawan swasta", "Pengusaha Jasa", "Pegawai negri", "Pedagang", "Lainnya" }));
        CBOccupation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBOccupationActionPerformed(evt);
            }
        });

        LPhone.setFont(new java.awt.Font("sansserif", 0, 9)); // NOI18N
        LPhone.setText("Please only enter numbers");

        LBirthPlace.setFont(new java.awt.Font("sansserif", 0, 9)); // NOI18N
        LBirthPlace.setText("Please only enter letters");

        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        TFAddress.setColumns(20);
        TFAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFAddress.setRows(5);
        TFAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 102, 255)));
        jScrollPane5.setViewportView(TFAddress);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(CBCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel36))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BRegister))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TFBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LCode)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LID))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(RBMale)
                                        .addGap(38, 38, 38)
                                        .addComponent(RBFemale))
                                    .addComponent(TFBirthPlace)
                                    .addComponent(TFPhone, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(LPhone)
                                    .addComponent(LBirthPlace)
                                    .addComponent(TFName, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel32)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CBReligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CBOccupation, 0, 293, Short.MAX_VALUE))))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LCode)
                    .addComponent(LID)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(RBMale, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RBFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TFBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addComponent(LPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBirthPlace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(CBReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(CBOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(CBCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BRegister)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LStatus)
                        .addComponent(jLabel36)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        TFBirthday.getAccessibleContext().setAccessibleDescription("");

        BEnroll.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BEnroll.setText("Enroll");
        BEnroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEnrollActionPerformed(evt);
            }
        });

        PMenuSiswa2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Search_32px.png"))); // NOI18N

        TFSearch.setBackground(new java.awt.Color(0, 51, 153));
        TFSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFSearch.setForeground(new java.awt.Color(255, 255, 255));
        TFSearch.setBorder(null);
        TFSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TFSearchKeyReleased(evt);
            }
        });

        CBSearch.setBackground(new java.awt.Color(255, 255, 255));
        CBSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Id", "Name", "Address", "Birthday" }));

        javax.swing.GroupLayout PMenuSiswa2Layout = new javax.swing.GroupLayout(PMenuSiswa2);
        PMenuSiswa2.setLayout(PMenuSiswa2Layout);
        PMenuSiswa2Layout.setHorizontalGroup(
            PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSiswa2Layout.createSequentialGroup()
                .addContainerGap(579, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        PMenuSiswa2Layout.setVerticalGroup(
            PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PMenuSiswa2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PMenuSiswa2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jScrollPane4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane4KeyReleased(evt);
            }
        });

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
        Tsiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsiswaKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(Tsiswa);

        BDelete.setText("Delete");
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(BDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BEnroll))))
                    .addComponent(PMenuSiswa2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PMenuSiswa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BEnroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(kGradientPanel1);

        JMenu.setBackground(new java.awt.Color(51, 102, 255));
        JMenu.setPreferredSize(new java.awt.Dimension(216, 685));
        JMenu.setRequestFocusEnabled(false);

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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Register");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel24)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));

        PCPass2.setBackground(new java.awt.Color(51, 102, 255));
        PCPass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PCPass2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PCPass2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PCPass2MouseExited(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel18.setText("Change Password");

        javax.swing.GroupLayout PCPass2Layout = new javax.swing.GroupLayout(PCPass2);
        PCPass2.setLayout(PCPass2Layout);
        PCPass2Layout.setHorizontalGroup(
            PCPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPass2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPass2Layout.setVerticalGroup(
            PCPass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPass2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
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
        Luser.setText("aaaaa");

        LDate1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate1.setForeground(new java.awt.Color(255, 255, 255));
        LDate1.setText("Date");

        LTime2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LTime2.setForeground(new java.awt.Color(255, 255, 255));
        LTime2.setText("Time");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(LDate1)
                                .addGap(18, 18, 18)
                                .addComponent(LTime2)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LDate1)
                    .addComponent(LTime2))
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout JMenuLayout = new javax.swing.GroupLayout(JMenu);
        JMenu.setLayout(JMenuLayout);
        JMenuLayout.setHorizontalGroup(
            JMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PStudents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE)
        );
        JMenuLayout.setVerticalGroup(
            JMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BEnrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEnrollActionPerformed
        int row= Tsiswa.getSelectedRow();
        String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
        try{
            String sql= "select * from tsiswa where Id='"+TableClick+"'";
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();

            String Id;
            String Name;
            String StartingDate;
            String Category;
            String Code;
            String User;
            if(rs.next()){
                Id = (String)(rs.getString("Id"));
                Name= (String)(rs.getString("Name"));
                StartingDate = (String)(rs.getString("StartingDate"));
                Category= (String)(rs.getString("Category"));
                Code= LCode.getText();
                User = Luser.getText();
                FormEnrollPay lf = new FormEnrollPay();
                lf.setVisible(true);
                this.setVisible(true);
                hide();
                lf.pass(User, Id, Name, Category, Code);
            }}catch(Exception e){

            }
    }//GEN-LAST:event_BEnrollActionPerformed

    private void BRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRegisterActionPerformed
        String Name= TFName.getText();
        String Birthday= ((JTextField)TFBirthday.getDateEditor().getUiComponent()).getText();
        String Phone = TFPhone.getText();
        String Address = TFAddress.getText();
        String BirthPlace= TFBirthPlace.getText();
        
        if(Name.isEmpty() || Birthday.isEmpty() || Phone.isEmpty() || Address.isEmpty() || BirthPlace.isEmpty()){
            JOptionPane.showMessageDialog(null, "Field must not be Empty");
        } else {
            if(Phone.matches("[0-9]*") && Phone.matches("\\S*")){
               
            }else{
                JOptionPane.showMessageDialog(null, "Phone Must only contain numbers");
            }
        }
        
            try{
                String sql = "Insert into tsiswa ( Name, Id, Address,StartingDate, Birthplace, Birthday, Occupation, Phone, Religion, Gender, Status, Note, Category) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //Id can be emptied.
                //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
                pst = conn.prepareStatement(sql);
                pst.setString(1, TFName.getText()); //use getText() to get the text from the TextField and pass it to the table
                pst.setString(2, LID.getText());
                pst.setString(3, TFAddress.getText());
                pst.setString(4, LDate1.getText());
                pst.setString(5, TFBirthPlace.getText());
                pst.setString(6, ((JTextField)TFBirthday.getDateEditor().getUiComponent()).getText());
                pst.setString(7, CBOccupation.getSelectedItem().toString());
                pst.setString(8, TFPhone.getText());
                pst.setString(9, CBReligion.getSelectedItem().toString());
                pst.setString(10, buttonGroup.getSelection().getActionCommand().toString());
                pst.setString(11, LStatus.getText());
                pst.setString(12, TANote.getText()); //use getText() to get the text from the TextField and pass it to the table
                pst.setString(13, (String) CBCategory.getSelectedItem());
                pst.execute(); // execute the pst
                JOptionPane.showMessageDialog(null, "Saved");
                ShowData();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println("e "+ e);
            }
        
        
    }//GEN-LAST:event_BRegisterActionPerformed

    private void TFPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFPhoneActionPerformed

    private void TFNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFNameActionPerformed

    private void RBFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBFemaleActionPerformed

    private void RBMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RBMaleActionPerformed

    private void TFSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFSearchKeyReleased
        ShowData();
        DftTblModelSiswa= new DefaultTableModel();
        DftTblModelSiswa.addColumn("Id");
        DftTblModelSiswa.addColumn("Name");
        DftTblModelSiswa.addColumn("Gender");
        DftTblModelSiswa.addColumn("Phone");
        DftTblModelSiswa.addColumn("Address");
        DftTblModelSiswa.addColumn("Birthplace");
        DftTblModelSiswa.addColumn("Date Of Birth");
        DftTblModelSiswa.addColumn("Religion");
        DftTblModelSiswa.addColumn("Occupation");
        DftTblModelSiswa.addColumn("Note");
        DftTblModelSiswa.addColumn("Category");
        Tsiswa.setModel(DftTblModelSiswa);

        java.sql.Connection conn = new DbConnection().connect();
        try{
            //CBSearch.getSelectedItem();
            String Search = (String) CBSearch.getSelectedItem();
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tsiswa where STATUS='PREREG' and "+Search+" Like '%"+TFSearch.getText()+"%'";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while(res.next()){
                //LTotalStudents2.setText(SQL2);
                DftTblModelSiswa.addRow(new Object[]{
                    res.getString("Id"),
                    res.getString("Name"),
                    res.getString("Gender"),
                    res.getString("Phone"),
                    res.getString("Address"),
                    res.getString("Birthplace"),
                    res.getString("Birthday"),
                    res.getString("Religion"),
                    res.getString("Occupation"),
                    res.getString("Note"),
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

    private void PStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseEntered
        PStudents.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PStudentsMouseEntered

    private void PStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseExited
        PStudents.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PStudentsMouseExited

    private void CBOccupationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOccupationActionPerformed
        
    }//GEN-LAST:event_CBOccupationActionPerformed

    private void PCPass2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPass2MouseClicked
        String Username=Luser.getText();
        FormChangePassword lf = new FormChangePassword();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PCPass2MouseClicked

    private void PCPass2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPass2MouseEntered
        PCPass2.setBackground(new java.awt.Color(1,51,153));
    }//GEN-LAST:event_PCPass2MouseEntered

    private void PCPass2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PCPass2MouseExited
        PCPass2.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PCPass2MouseExited

    private void TFPhoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFPhoneKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }else{
            
        }
    }//GEN-LAST:event_TFPhoneKeyTyped

    private void jScrollPane4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane4KeyReleased
        
    }//GEN-LAST:event_jScrollPane4KeyReleased

    private void TsiswaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsiswaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
         
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
                    StartingDate = "";
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

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        int row= Tsiswa.getSelectedRow();
        String TableClick= (Tsiswa.getModel().getValueAt(row, 0).toString());
        try{
            int Input = JOptionPane.showConfirmDialog(null, "Remove this student?", "Confirm Dismiss", JOptionPane.YES_NO_OPTION);
               if(Input==JOptionPane.YES_OPTION){
                    String sql = "Delete from tsiswa where Id='"+TableClick+"'";

                    pst=conn.prepareStatement(sql);
                    pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work

                    JOptionPane.showMessageDialog(null, "Deleted");
                    ShowData();
                }else{
               
               }
            //
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void TFBirthPlaceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFBirthPlaceKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
        }else{
            JOptionPane.showMessageDialog(null, "Letters only","Warning", JOptionPane.WARNING_MESSAGE);
            evt.consume();
        }
    }//GEN-LAST:event_TFBirthPlaceKeyTyped

    private void TFBirthPlaceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFBirthPlaceKeyPressed

    }//GEN-LAST:event_TFBirthPlaceKeyPressed

    private void TFBirthPlaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFBirthPlaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFBirthPlaceActionPerformed

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
                new FormRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEnroll;
    private javax.swing.JButton BRegister;
    private javax.swing.JComboBox<String> CBCategory;
    private javax.swing.JComboBox<String> CBOccupation;
    private javax.swing.JComboBox<String> CBReligion;
    private javax.swing.JComboBox<String> CBSearch;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JPanel JMenu;
    private javax.swing.JLabel LBirthPlace;
    private javax.swing.JLabel LCode;
    private javax.swing.JLabel LDate1;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LPhone;
    private javax.swing.JLabel LStatus;
    private javax.swing.JLabel LTime2;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel PCPass2;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenuSiswa2;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JRadioButton RBFemale;
    private javax.swing.JRadioButton RBMale;
    private javax.swing.JTextArea TANote;
    private javax.swing.JTextArea TFAddress;
    private javax.swing.JTextField TFBirthPlace;
    private com.toedter.calendar.JDateChooser TFBirthday;
    private javax.swing.JTextField TFName;
    private javax.swing.JTextField TFPhone;
    private javax.swing.JTextField TFSearch;
    private javax.swing.JTable Tsiswa;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
