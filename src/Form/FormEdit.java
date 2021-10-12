package Form;

import java.sql.*;
import javax.swing.*;
import DatabaseConnection.DbConnection;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import static java.lang.Thread.sleep;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;



public class FormEdit extends javax.swing.JFrame {

    private DefaultTableModel DftTblModelSiswa;
    private DefaultTableModel DftTblModelSfee;
    private String SQL;
    private String gender;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
     
    public FormEdit() {
        initComponents();
        conn = DbConnection.connect(); // since you declare to public form edit,make sure to declare it in the next one too or else, conn will be null
        ShowData();
        ShowID();
        FillCombo();
        
    }
    void username(String User, String Id, String Name, String Class, String Gender, String Phone, String Address,String StartingDate, String Birthplace, String Religion, String Occupation, String Note, String Birthday, String Image, String Category){
        Luser.setText(User);
        TFImage.setText(Image);
        showPicture(Image);
        Luser.setText(User);
        LID.setText(Id);
        TFName.setText(Name);
        LClass.setText(Class);
        TFPhone.setText(Phone);
        TFAddress.setText(Address);
        TFBirthPlace.setText(Birthplace);
        CBReligion.setSelectedItem(Religion);
        CBOccupation.setSelectedItem(Occupation);
        TANote.setText(Note);
        CBCategory.setSelectedItem(Category);
        LStartingDate.setText(StartingDate);
        if(Gender.equals("Female")){
            RBFemale.setSelected(true);
        }else if(Gender.equals("Male")){
            RBMale.setSelected(true);
       }
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(Birthday);
            TFBirthday.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(FormEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conn = DbConnection.connect();
    }

    public void showPicture(String images){
        //pass the url to the function and then display the picture
        ImageIcon icon = new ImageIcon(images);
        Image img = icon.getImage(); 
        Image image = img.getScaledInstance(LImage.getWidth(), LImage.getHeight(), Image.SCALE_SMOOTH); //to set the scaling quality
        ImageIcon scaleIcon = new ImageIcon(image);// to scale
        LImage.setIcon(scaleIcon);
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
    
    public void ShowID(){
        try{
            String sql= "select * from tsiswa where Name=? ";
            pst= conn.prepareStatement(sql);
            pst.setString(1, TFName.getText());
         }catch(Exception e){
        ShowID();
        }
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
    
    public void ShowData(){
        DftTblModelSiswa = new DefaultTableModel();
        java.sql.Connection conn;
        conn = new DbConnection.connect() {
            @Override
            public Statement createStatement() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public CallableStatement prepareCall(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String nativeSQL(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setAutoCommit(boolean bln) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean getAutoCommit() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void commit() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void rollback() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void close() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isClosed() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public DatabaseMetaData getMetaData() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setReadOnly(boolean bln) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isReadOnly() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setCatalog(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getCatalog() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setTransactionIsolation(int i) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getTransactionIsolation() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public SQLWarning getWarnings() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void clearWarnings() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Statement createStatement(int i, int i1) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string, int i, int i1) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public CallableStatement prepareCall(String string, int i, int i1) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Map<String, Class<?>> getTypeMap() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setHoldability(int i) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getHoldability() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Savepoint setSavepoint() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Savepoint setSavepoint(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void rollback(Savepoint svpnt) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void releaseSavepoint(Savepoint svpnt) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Statement createStatement(int i, int i1, int i2) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string, int i, int i1, int i2) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public CallableStatement prepareCall(String string, int i, int i1, int i2) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string, int i) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string, int[] ints) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public PreparedStatement prepareStatement(String string, String[] strings) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Clob createClob() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Blob createBlob() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public NClob createNClob() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public SQLXML createSQLXML() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isValid(int i) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setClientInfo(String string, String string1) throws SQLClientInfoException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setClientInfo(Properties prprts) throws SQLClientInfoException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getClientInfo(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Properties getClientInfo() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Array createArrayOf(String string, Object[] os) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Struct createStruct(String string, Object[] os) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setSchema(String string) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String getSchema() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void abort(Executor exctr) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setNetworkTimeout(Executor exctr, int i) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getNetworkTimeout() throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <T> T unwrap(Class<T> type) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isWrapperFor(Class<?> type) throws SQLException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        try{
            java.sql.Statement stmt = conn.createStatement();
            SQL ="select * from tsiswa";
            java.sql.ResultSet res = stmt.executeQuery(SQL);
                       while(res.next()){
                           DftTblModelSiswa.addRow(new Object[]{
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
                       });
                       }
            LClass.setText("Class");

        }catch(Exception e){
        
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        Gender = new javax.swing.ButtonGroup();
        EditScrollPanel = new javax.swing.JScrollPane();
        EditPanel = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        LClass = new javax.swing.JLabel();
        TFName = new javax.swing.JTextField();
        TFBirthPlace = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        RBFemale = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        TFPhone = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        RBMale = new javax.swing.JRadioButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LStartingDate = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        CBCategory = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TFBirthday = new com.toedter.calendar.JDateChooser();
        CBReligion = new javax.swing.JComboBox<>();
        CBOccupation = new javax.swing.JComboBox<>();
        LPhone = new javax.swing.JLabel();
        LBirthPlace = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TFAddress = new javax.swing.JTextArea();
        BSave = new javax.swing.JButton();
        BBack = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TANote = new javax.swing.JTextArea();
        BImage = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        TFImage = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        LImage = new javax.swing.JLabel();
        MenuPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PClasses = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        PStock = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        PStock1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
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
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        EditScrollPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        EditPanel.setBackground(new java.awt.Color(255, 255, 255));
        EditPanel.setkEndColor(new java.awt.Color(255, 255, 255));
        EditPanel.setkGradientFocus(600);
        EditPanel.setkStartColor(new java.awt.Color(255, 255, 255));
        EditPanel.setPreferredSize(new java.awt.Dimension(752, 669));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Name");

        LClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LClass.setText("Class");

        TFName.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TFName.setForeground(new java.awt.Color(51, 51, 51));
        TFName.setText("Name");
        TFName.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 102, 255)));
        TFName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFNameActionPerformed(evt);
            }
        });

        TFBirthPlace.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TFBirthPlace.setForeground(new java.awt.Color(51, 51, 51));
        TFBirthPlace.setText("BirthPlace");
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

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Gender");

        RBFemale.setBackground(new java.awt.Color(255, 255, 255));
        Gender.add(RBFemale);
        RBFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RBFemale.setText("Female");
        RBFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBFemaleActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Starting Date");

        TFPhone.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        TFPhone.setForeground(new java.awt.Color(51, 51, 51));
        TFPhone.setText("Phone");
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

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Occupation");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Birthday");

        LID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LID.setText("jLabel20");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Address");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Class");

        RBMale.setBackground(new java.awt.Color(255, 255, 255));
        Gender.add(RBMale);
        RBMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        RBMale.setText("Male");
        RBMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBMaleActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("BirthPlace");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Phone");

        LStartingDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LStartingDate.setText("Starting Date");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Religion");

        CBCategory.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Regular" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Category");

        TFBirthday.setDateFormatString("yyyy-MM-dd");

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

        TFAddress.setColumns(20);
        TFAddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TFAddress.setRows(5);
        TFAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 102, 255)));
        jScrollPane2.setViewportView(TFAddress);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel25))
                            .addComponent(jLabel21))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LID)
                            .addComponent(LClass)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(RBMale, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(RBFemale))
                            .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LPhone)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel33)
                                .addComponent(jLabel36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TFBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(LStartingDate))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LBirthPlace)
                                .addComponent(TFBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(2, 2, 2))
                            .addComponent(CBReligion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBOccupation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CBCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LID)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(LClass))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(RBMale, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RBFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(LPhone)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LBirthPlace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TFBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(TFBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBReligion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CBCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(LStartingDate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BSave.setText("Save");
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });

        BBack.setText("Back To Student Form");
        BBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBackActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1036, 65));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Edit Students");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(491, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(394, 394, 394))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        TANote.setColumns(20);
        TANote.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TANote.setForeground(new java.awt.Color(153, 153, 153));
        TANote.setRows(5);
        TANote.setText("Note");
        jScrollPane1.setViewportView(TANote);

        BImage.setText("Change Picture");
        BImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BImageActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Note");

        TFImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFImageActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        LImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/Classroom_37px.png"))); // NOI18N
        LImage.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(51, 51, 255)));
        LImage.setPreferredSize(new java.awt.Dimension(113, 151));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(LImage, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LImage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TFImage)
                        .addComponent(BImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TFImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BImage)
                .addGap(30, 30, 30)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout EditPanelLayout = new javax.swing.GroupLayout(EditPanel);
        EditPanel.setLayout(EditPanelLayout);
        EditPanelLayout.setHorizontalGroup(
            EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditPanelLayout.createSequentialGroup()
                .addGroup(EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EditPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditPanelLayout.createSequentialGroup()
                        .addGroup(EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditPanelLayout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, EditPanelLayout.createSequentialGroup()
                                .addGap(407, 407, 407)
                                .addComponent(BSave, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)
                                .addComponent(BBack)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(481, 481, 481))
        );
        EditPanelLayout.setVerticalGroup(
            EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BSave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BBack, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        EditScrollPanel.setViewportView(EditPanel);

        MenuPanel1.setBackground(new java.awt.Color(51, 102, 255));
        MenuPanel1.setToolTipText("");
        MenuPanel1.setPreferredSize(new java.awt.Dimension(216, 726));

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Students");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Teachers");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Training_32px.png"))); // NOI18N

        javax.swing.GroupLayout PTeacherLayout = new javax.swing.GroupLayout(PTeacher);
        PTeacher.setLayout(PTeacherLayout);
        PTeacherLayout.setHorizontalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PTeacherLayout.setVerticalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Classes");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClassesLayout = new javax.swing.GroupLayout(PClasses);
        PClasses.setLayout(PClassesLayout);
        PClassesLayout.setHorizontalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClassesLayout.setVerticalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PStock.setBackground(new java.awt.Color(51, 102, 255));
        PStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PStockMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PStockMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PStockMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Register");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PStockLayout = new javax.swing.GroupLayout(PStock);
        PStock.setLayout(PStockLayout);
        PStockLayout.setHorizontalGroup(
            PStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStockLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PStockLayout.setVerticalGroup(
            PStockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PStockLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Log Out");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLogOutLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PStock1.setBackground(new java.awt.Color(51, 102, 255));
        PStock1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PStock1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PStock1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PStock1MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Report");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PStock1Layout = new javax.swing.GroupLayout(PStock1);
        PStock1.setLayout(PStock1Layout);
        PStock1Layout.setHorizontalGroup(
            PStock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStock1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PStock1Layout.setVerticalGroup(
            PStock1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PStock1Layout.createSequentialGroup()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/padlock.png"))); // NOI18N
        jLabel16.setText("Change Password");

        javax.swing.GroupLayout PCPassLayout = new javax.swing.GroupLayout(PCPass);
        PCPass.setLayout(PCPassLayout);
        PCPassLayout.setHorizontalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        PCPassLayout.setVerticalGroup(
            PCPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PCPassLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Welcome ,");

        jLabel13.setFont(new java.awt.Font("Poor Richard", 0, 22)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/ddtclogo2011.png"))); // NOI18N
        jLabel13.setBorder(null);

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
                            .addComponent(jLabel13)
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
                .addComponent(jLabel13)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout MenuPanel1Layout = new javax.swing.GroupLayout(MenuPanel1);
        MenuPanel1.setLayout(MenuPanel1Layout);
        MenuPanel1Layout.setHorizontalGroup(
            MenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PStock1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE)
        );
        MenuPanel1Layout.setVerticalGroup(
            MenuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PClasses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PStock1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 145, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MenuPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(EditScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MenuPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
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
        gender="Male";
    }//GEN-LAST:event_RBMaleActionPerformed

    private void RBFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBFemaleActionPerformed
        gender="Female";
    }//GEN-LAST:event_RBFemaleActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed

        try{
            // to get text as value and change the data in sql
           String value1= LID.getText();
           String value2= TFName.getText();
           //String value3= LClass.getText(); 
           String value5= TFAddress.getText();
           String value6= TFBirthPlace.getText();
           String value7= ((JTextField)TFBirthday.getDateEditor().getUiComponent()).getText();
           String value9= TANote.getText();
           String value10= CBOccupation.getSelectedItem().toString();
           String value11= TFPhone.getText();
           String value12= CBReligion.getSelectedItem().toString();
           String value13= TFImage.getText();
           value13= value13.replace("\\","\\\\");
           String value14= gender;
           System.out.println("value13"+value13);
           String sql = "update tsiswa set Name='"+value2+"', Gender='"+value14+"',Address='"+value5+"', Birthplace='"+value6+"', Birthday='"+value7+"', Occupation='"+value10+"', Phone='"+value11+"', Religion='"+value12+"', Note='"+value9+"', Image='"+value13+"' where Id= '"+value1+"'";
           
           /*
            must use where to specify which row/data you want to change
           */
           
           pst=conn.prepareStatement(sql);
           pst.executeUpdate(); // don't make a mistake by using pst.execute(), it won't work
           
           JOptionPane.showMessageDialog(null, "Updated");
           //UpdateTable(); 
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
           System.out.println("e "+ e);
       }
    }//GEN-LAST:event_BSaveActionPerformed

    private void BBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBackActionPerformed
        String User = (String)Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        //this.setVisible(false);
        lf.username(User);
//        new FormSiswa().setVisible(true);
        hide();
    }//GEN-LAST:event_BBackActionPerformed

    private void BImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BImageActionPerformed
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
    }//GEN-LAST:event_BImageActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        String Username=Luser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_jPanel3MouseClicked

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

    private void PStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStockMouseClicked
        String Username=Luser.getText();
        FormRegister lf = new FormRegister();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PStockMouseClicked

    private void PStockMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStockMouseEntered
        PStock.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PStockMouseEntered

    private void PStockMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStockMouseExited
        PStock.setBackground(new java.awt.Color(51,102,255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PStockMouseExited

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

    private void PStock1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStock1MouseClicked
        String Username=Luser.getText();
        FormChart lf = new FormChart();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_PStock1MouseClicked

    private void PStock1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStock1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PStock1MouseEntered

    private void PStock1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStock1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PStock1MouseExited

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

    private void CBOccupationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBOccupationActionPerformed

    }//GEN-LAST:event_CBOccupationActionPerformed

    private void TFImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFImageActionPerformed

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
                new FormEdit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBack;
    private javax.swing.JButton BImage;
    private javax.swing.JButton BSave;
    private javax.swing.JComboBox<String> CBCategory;
    private javax.swing.JComboBox<String> CBOccupation;
    private javax.swing.JComboBox<String> CBReligion;
    private keeptoo.KGradientPanel EditPanel;
    private javax.swing.JScrollPane EditScrollPanel;
    private javax.swing.ButtonGroup Gender;
    private javax.swing.JLabel LBirthPlace;
    private javax.swing.JLabel LClass;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LImage;
    private javax.swing.JLabel LPhone;
    private javax.swing.JLabel LStartingDate;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel MenuPanel1;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PStock;
    private javax.swing.JPanel PStock1;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JRadioButton RBFemale;
    private javax.swing.JRadioButton RBMale;
    private javax.swing.JTextArea TANote;
    private javax.swing.JTextArea TFAddress;
    private javax.swing.JTextField TFBirthPlace;
    private com.toedter.calendar.JDateChooser TFBirthday;
    private javax.swing.JTextField TFImage;
    private javax.swing.JTextField TFName;
    private javax.swing.JTextField TFPhone;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
