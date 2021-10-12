package Form;

import DatabaseConnection.DbConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.SlidingCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

public class FormChart extends javax.swing.JFrame {
     private DefaultTableModel DftTblModel_Transaksi;
     private DefaultTableModel DftTblModel_Barang;
     private String SQL;
     Connection conn = null;
     PreparedStatement pst = null;
     ResultSet rs= null;
     
    public FormChart() {
       initComponents();
        TampilDataUnpaid();
        conn= DbConnection.connect();
        FillCombo();
        FillComboYear();
        StudentsTotal();
        NewStudentsTotal();
        QuitStudentsTotal();
        UnpaidStudentsTotal();
        CurrentDate();
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
    String monthString;
        monthString = new DateFormatSymbols().getMonths()[month];
    LMonth.setText(""+monthString);
    //MenuTime.setText(hour+":"+minute+":"+second); 
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
    
    LDate.setText(day+"/"+(month+1)+"/"+year+" \t  "+hour+":"+minute+":"+second);
    LDate2.setText(year+"-"+(month+1)+"-"+day);
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
    
    void username(String User){
        Luser.setText(User);
        CheckUserStatus(User);
    }
    
    public void StudentsTotal() {            
           String sql= "SELECT COUNT(Id) from tsiswa where status='Active'";
         try {
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            while(rs.next()){
            String Students=(String) rs.getString("count(Id)"); // insert count(Id) to get the total of the value
            LTotalStudents.setText(Students);
            }
         } catch (SQLException ex) {
             Logger.getLogger(FormChart.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    //to get the total of new students od current month
    public void NewStudentsTotal() {
         String sql= "SELECT COUNT(Id) FROM tsiswa WHERE MONTH(StartingDate) = MONTH(CURRENT_DATE())AND YEAR(StartingDate)= YEAR(CURRENT_DATE())";
         System.out.println("sql");
         try {
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            while(rs.next()){
            System.out.println("sql");
            String newStudents=(String) rs.getString("count(id)"); // insert count(Id) to get the total of the value
            System.out.println("newStudents");
            LNewStudents.setText(newStudents);
            }
         } catch (SQLException ex) {
             Logger.getLogger(FormChart.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    
    public void FillComboYear(){
    try{
            String sql = "Select Year(date) from tschoolfee group by Year(date);";
            pst= conn.prepareStatement(sql); 
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Year = rs.getString("Year(date)");
                CBYear.addItem(Year);
                
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void FillCombo(){
    try{
            String sql = "Select MOnth(date), Year(date) from tschoolfee group by MOnth(date), Year(date);";
            pst= conn.prepareStatement(sql); 
            rs= pst.executeQuery();
            
            while(rs.next()){
                String Month   = rs.getString("Month(date)");
                String Year = rs.getString("Year(date)");
                CBMonth.addItem(Year+"-"+Month);
                
            }
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    //to get the total of quit students of current month
    public void QuitStudentsTotal() {
         String sql= "SELECT COUNT(Id) FROM tsiswa WHERE MONTH(QuitDate)=MONTH(CURRENT_DATE()) AND YEAR(QuitDate)= YEAR(CURRENT_DATE())";
         try {
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            while(rs.next()){
            String quitStudents=(String) rs.getString("count(Id)"); // insert count(Id) to get the total of the value
            LQUIT.setText(quitStudents);
            }
         } catch (SQLException ex) {
             Logger.getLogger(FormChart.class.getName()).log(Level.SEVERE, null, ex);
         }    
    }
    
    public void UnpaidStudentsTotal() {
         String sql=    "select count(tschoolfee.id)\n" +
                        "from tschoolfee where id in (\n" +
                        "select tschoolfee.id\n" +
                        "from tschoolfee\n" +
                        "inner join tsiswa on tsiswa.id=tschoolfee.studentid\n" +
                        "where tschoolfee.id in (select max(id) from tschoolfee group by studentid)\n" +
                        "and tsiswa.status=\"active\"\n" +
                        "and not timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())='0'\n" +
                        "group by studentid)";
         try {
            pst= conn.prepareStatement(sql);
            rs= pst.executeQuery();
            while(rs.next()){
            String quitStudents=(String) rs.getString("Count(tschoolfee.id)"); // insert count(Id) to get the total of the value
            LTotalUnpaid.setText(quitStudents);
            }
         } catch (SQLException ex) {
             Logger.getLogger(FormChart.class.getName()).log(Level.SEVERE, null, ex);
         }    
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
        PGraph.setVisible(false);
        PGraph2.setVisible(false);
        //PSLaporan.setVisible(false);
    }
    
    public void TampilChart(){
    try{
            String query = "SELECT COUNT(Id),Month(StartingDate) FROM tsiswa WHERE NOT Class='Quit' GROUP BY Month(StartingDate)";
            System.out.println("1"+query);
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DbConnection.connect(),query);
            System.out.println("dataset "+ dataset);
            JFreeChart chart=ChartFactory.createLineChart("Students Total", "Month&Year", "Students", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer renderer = null;
            CategoryPlot plot = null;
            renderer = new BarRenderer();
            ChartFrame frame = new ChartFrame("Query Chart", chart);
            chart.setBackgroundPaint(new Color(222,63,173));
            chart.setBorderVisible(false);
            chart.setBorderPaint(new Color (0,63,173));
            ChartPanel cp = new ChartPanel(chart);
            pnl_chart.add(frame, BorderLayout.CENTER);
            pnl_chart.setFocusable(rootPaneCheckingEnabled);
            pnl_chart.validate();

            System.out.println("1"+frame);
            frame.setVisible(true);
            frame.setSize(1400,1650);
            }catch(Exception e){
        
            }
    } 
    
    public void TampilDataUnpaid(){
         DftTblModel_Barang= new DefaultTableModel();
         DftTblModel_Barang.addColumn("Id");
         DftTblModel_Barang.addColumn("Date");
         DftTblModel_Barang.addColumn("Active");
         DftTblModel_Barang.addColumn("Quit");
         DftTblModel_Barang.addColumn("New");
         DftTblModel_Barang.addColumn("Unpaid");
         
         TLaporan.setModel(DftTblModel_Barang);
         
         java.sql.Connection conn = new DbConnection().connect();
         try{
               java.sql.Statement stmt = conn.createStatement();
               SQL= "select * from tlaporan";
//               
               java.sql.ResultSet res = stmt.executeQuery(SQL);
               System.out.println("res"+res);
               while(res.next()){
                   DftTblModel_Barang.addRow(new Object[]{
                   res.getString("id"),
                   res.getString("date"),
                   res.getString("active"),
                   res.getString("quit"),
                   res.getString("new"),
                   res.getString("unpaid"),
                   
                });
               }
         }catch(SQLException e){
             
         }
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TLaporan1 = new javax.swing.JTable();
        JMenu = new javax.swing.JPanel();
        PStudents = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        PTeacher = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        PClasses = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        PRegister = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PLogOut = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        PReport = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PCPass = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Luser = new javax.swing.JLabel();
        LDate = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PMenuSfee = new javax.swing.JPanel();
        PSchoolFee = new javax.swing.JPanel();
        LSchoolFee = new javax.swing.JLabel();
        PUnpaidReport = new javax.swing.JPanel();
        LSchoolFee2 = new javax.swing.JLabel();
        PPrintNewStudentReport = new javax.swing.JPanel();
        LSchoolFee1 = new javax.swing.JLabel();
        PPrintClassesReport = new javax.swing.JPanel();
        LSchoolFee4 = new javax.swing.JLabel();
        PrintQuit = new javax.swing.JPanel();
        LSchoolFee11 = new javax.swing.JLabel();
        PPrintStudentReport = new javax.swing.JPanel();
        LSchoolFee12 = new javax.swing.JLabel();
        PNewTeacher = new javax.swing.JPanel();
        LSchoolFee9 = new javax.swing.JLabel();
        PQuitTeacher = new javax.swing.JPanel();
        LSchoolFee10 = new javax.swing.JLabel();
        PTeacherTotal = new javax.swing.JPanel();
        LSchoolFee21 = new javax.swing.JLabel();
        PMenuSfee1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        PGraph = new javax.swing.JPanel();
        PQuitStudentsGraph = new javax.swing.JPanel();
        LSchoolFee3 = new javax.swing.JLabel();
        PNewStudentsGraph = new javax.swing.JPanel();
        LSchoolFee5 = new javax.swing.JLabel();
        PQuitPie = new javax.swing.JPanel();
        LSchoolFee7 = new javax.swing.JLabel();
        PUnpaidStudentsGraph = new javax.swing.JPanel();
        LSchoolFee8 = new javax.swing.JLabel();
        PStudentsTot = new javax.swing.JPanel();
        LSchoolFee13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        pnl_chart = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LTotalStudents = new javax.swing.JLabel();
        LNewStudents = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LQUIT = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LTotalUnpaid = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LMonth = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        PGraph2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PSLaporan = new javax.swing.JScrollPane();
        TLaporan = new javax.swing.JTable();
        LDate2 = new javax.swing.JLabel();
        PMenuSfee2 = new javax.swing.JPanel();
        PPrintByMonth = new javax.swing.JPanel();
        LSchoolFee19 = new javax.swing.JLabel();
        PPrintByYear = new javax.swing.JPanel();
        LSchoolFee20 = new javax.swing.JLabel();
        CBReport = new javax.swing.JComboBox<>();
        CBMonth = new javax.swing.JComboBox<>();
        CBYear = new javax.swing.JComboBox<>();

        TLaporan1.setModel(new javax.swing.table.DefaultTableModel(
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
        TLaporan1.setAutoscrolls(false);
        TLaporan1.setPreferredSize(new java.awt.Dimension(300, 146));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JMenu.setBackground(new java.awt.Color(51, 102, 255));
        JMenu.setPreferredSize(new java.awt.Dimension(216, 681));

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

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Students");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Customer_32px.png"))); // NOI18N

        javax.swing.GroupLayout PStudentsLayout = new javax.swing.GroupLayout(PStudents);
        PStudents.setLayout(PStudentsLayout);
        PStudentsLayout.setHorizontalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStudentsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PStudentsLayout.setVerticalGroup(
            PStudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Teachers");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Training_32px.png"))); // NOI18N

        javax.swing.GroupLayout PTeacherLayout = new javax.swing.GroupLayout(PTeacher);
        PTeacher.setLayout(PTeacherLayout);
        PTeacherLayout.setHorizontalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12)
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PTeacherLayout.setVerticalGroup(
            PTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/Classroom_32px.png"))); // NOI18N

        javax.swing.GroupLayout PClassesLayout = new javax.swing.GroupLayout(PClasses);
        PClasses.setLayout(PClassesLayout);
        PClassesLayout.setHorizontalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PClassesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel14)
                .addGap(20, 20, 20)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PClassesLayout.setVerticalGroup(
            PClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Register");

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/edit.png"))); // NOI18N

        javax.swing.GroupLayout PRegisterLayout = new javax.swing.GroupLayout(PRegister);
        PRegister.setLayout(PRegisterLayout);
        PRegisterLayout.setHorizontalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegisterLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PRegisterLayout.setVerticalGroup(
            PRegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PRegisterLayout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Log Out");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/logout.png"))); // NOI18N

        javax.swing.GroupLayout PLogOutLayout = new javax.swing.GroupLayout(PLogOut);
        PLogOut.setLayout(PLogOutLayout);
        PLogOutLayout.setHorizontalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PLogOutLayout.setVerticalGroup(
            PLogOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            .addGroup(PLogOutLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PReport.setBackground(new java.awt.Color(102, 204, 255));
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

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/pie-chart.png"))); // NOI18N

        javax.swing.GroupLayout PReportLayout = new javax.swing.GroupLayout(PReport);
        PReport.setLayout(PReportLayout);
        PReportLayout.setHorizontalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PReportLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PReportLayout.setVerticalGroup(
            PReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PReportLayout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

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
        Luser.setText("aaaaa");

        LDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LDate.setForeground(new java.awt.Color(255, 255, 255));
        LDate.setText("Date");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Luser, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(LDate))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(LDate)
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

        javax.swing.GroupLayout JMenuLayout = new javax.swing.GroupLayout(JMenu);
        JMenu.setLayout(JMenuLayout);
        JMenuLayout.setHorizontalGroup(
            JMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PClasses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JMenuLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
        JMenuLayout.setVerticalGroup(
            JMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JMenuLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

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
        LSchoolFee.setText("Payment");
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
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PSchoolFeeLayout.setVerticalGroup(
            PSchoolFeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PSchoolFeeLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PUnpaidReport.setBackground(new java.awt.Color(51, 102, 255));
        PUnpaidReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PUnpaidReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PUnpaidReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PUnpaidReportMouseExited(evt);
            }
        });

        LSchoolFee2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee2.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee2.setText("Unpaid Students");
        LSchoolFee2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PUnpaidReportLayout = new javax.swing.GroupLayout(PUnpaidReport);
        PUnpaidReport.setLayout(PUnpaidReportLayout);
        PUnpaidReportLayout.setHorizontalGroup(
            PUnpaidReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PUnpaidReportLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee2)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PUnpaidReportLayout.setVerticalGroup(
            PUnpaidReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PUnpaidReportLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(LSchoolFee2)
                .addGap(20, 20, 20))
        );

        PPrintNewStudentReport.setBackground(new java.awt.Color(51, 102, 255));
        PPrintNewStudentReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintNewStudentReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintNewStudentReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintNewStudentReportMouseExited(evt);
            }
        });

        LSchoolFee1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee1.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee1.setText("New Students");
        LSchoolFee1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PPrintNewStudentReportLayout = new javax.swing.GroupLayout(PPrintNewStudentReport);
        PPrintNewStudentReport.setLayout(PPrintNewStudentReportLayout);
        PPrintNewStudentReportLayout.setHorizontalGroup(
            PPrintNewStudentReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintNewStudentReportLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee1)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PPrintNewStudentReportLayout.setVerticalGroup(
            PPrintNewStudentReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintNewStudentReportLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PPrintClassesReport.setBackground(new java.awt.Color(51, 102, 255));
        PPrintClassesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintClassesReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintClassesReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintClassesReportMouseExited(evt);
            }
        });

        LSchoolFee4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee4.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee4.setText("Classes");
        LSchoolFee4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PPrintClassesReportLayout = new javax.swing.GroupLayout(PPrintClassesReport);
        PPrintClassesReport.setLayout(PPrintClassesReportLayout);
        PPrintClassesReportLayout.setHorizontalGroup(
            PPrintClassesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintClassesReportLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee4)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PPrintClassesReportLayout.setVerticalGroup(
            PPrintClassesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintClassesReportLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PrintQuit.setBackground(new java.awt.Color(51, 102, 255));
        PrintQuit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintQuitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PrintQuitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PrintQuitMouseExited(evt);
            }
        });

        LSchoolFee11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee11.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee11.setText("Quit Students");
        LSchoolFee11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PrintQuitLayout = new javax.swing.GroupLayout(PrintQuit);
        PrintQuit.setLayout(PrintQuitLayout);
        PrintQuitLayout.setHorizontalGroup(
            PrintQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintQuitLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee11)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PrintQuitLayout.setVerticalGroup(
            PrintQuitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrintQuitLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PPrintStudentReport.setBackground(new java.awt.Color(51, 102, 255));
        PPrintStudentReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintStudentReportMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintStudentReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintStudentReportMouseExited(evt);
            }
        });

        LSchoolFee12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee12.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee12.setText("Student Total");
        LSchoolFee12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PPrintStudentReportLayout = new javax.swing.GroupLayout(PPrintStudentReport);
        PPrintStudentReport.setLayout(PPrintStudentReportLayout);
        PPrintStudentReportLayout.setHorizontalGroup(
            PPrintStudentReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PPrintStudentReportLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(LSchoolFee12)
                .addGap(15, 15, 15))
        );
        PPrintStudentReportLayout.setVerticalGroup(
            PPrintStudentReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintStudentReportLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PNewTeacher.setBackground(new java.awt.Color(51, 102, 255));
        PNewTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PNewTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PNewTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PNewTeacherMouseExited(evt);
            }
        });

        LSchoolFee9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee9.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee9.setText("New Teacher");
        LSchoolFee9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PNewTeacherLayout = new javax.swing.GroupLayout(PNewTeacher);
        PNewTeacher.setLayout(PNewTeacherLayout);
        PNewTeacherLayout.setHorizontalGroup(
            PNewTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNewTeacherLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee9)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PNewTeacherLayout.setVerticalGroup(
            PNewTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNewTeacherLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PQuitTeacher.setBackground(new java.awt.Color(51, 102, 255));
        PQuitTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PQuitTeacherMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PQuitTeacherMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PQuitTeacherMouseExited(evt);
            }
        });

        LSchoolFee10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee10.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee10.setText("Quit Teacher");
        LSchoolFee10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PQuitTeacherLayout = new javax.swing.GroupLayout(PQuitTeacher);
        PQuitTeacher.setLayout(PQuitTeacherLayout);
        PQuitTeacherLayout.setHorizontalGroup(
            PQuitTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PQuitTeacherLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee10)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PQuitTeacherLayout.setVerticalGroup(
            PQuitTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PQuitTeacherLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee10)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        PTeacherTotal.setBackground(new java.awt.Color(51, 102, 255));
        PTeacherTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PTeacherTotalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PTeacherTotalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PTeacherTotalMouseExited(evt);
            }
        });

        LSchoolFee21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee21.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee21.setText("Teacher Total");
        LSchoolFee21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee21MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PTeacherTotalLayout = new javax.swing.GroupLayout(PTeacherTotal);
        PTeacherTotal.setLayout(PTeacherTotalLayout);
        PTeacherTotalLayout.setHorizontalGroup(
            PTeacherTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherTotalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(LSchoolFee21)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        PTeacherTotalLayout.setVerticalGroup(
            PTeacherTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTeacherTotalLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PMenuSfeeLayout = new javax.swing.GroupLayout(PMenuSfee);
        PMenuSfee.setLayout(PMenuSfeeLayout);
        PMenuSfeeLayout.setHorizontalGroup(
            PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSfeeLayout.createSequentialGroup()
                .addComponent(PSchoolFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PUnpaidReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PPrintNewStudentReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PPrintClassesReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PrintQuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PPrintStudentReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PNewTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PQuitTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PTeacherTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PMenuSfeeLayout.setVerticalGroup(
            PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PPrintNewStudentReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PPrintClassesReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PrintQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PNewTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PMenuSfeeLayout.createSequentialGroup()
                .addGroup(PMenuSfeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PUnpaidReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PSchoolFee, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(PQuitTeacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PPrintStudentReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PTeacherTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PMenuSfee1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Print Report");

        jPanel7.setBackground(new java.awt.Color(51, 102, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 313, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PMenuSfee1Layout = new javax.swing.GroupLayout(PMenuSfee1);
        PMenuSfee1.setLayout(PMenuSfee1Layout);
        PMenuSfee1Layout.setHorizontalGroup(
            PMenuSfee1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMenuSfee1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        PMenuSfee1Layout.setVerticalGroup(
            PMenuSfee1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PMenuSfee1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        PGraph.setBackground(new java.awt.Color(51, 102, 255));

        PQuitStudentsGraph.setBackground(new java.awt.Color(51, 102, 255));
        PQuitStudentsGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PQuitStudentsGraphMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PQuitStudentsGraphMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PQuitStudentsGraphMouseExited(evt);
            }
        });

        LSchoolFee3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee3.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee3.setText("Quit Students Graph");
        LSchoolFee3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PQuitStudentsGraphLayout = new javax.swing.GroupLayout(PQuitStudentsGraph);
        PQuitStudentsGraph.setLayout(PQuitStudentsGraphLayout);
        PQuitStudentsGraphLayout.setHorizontalGroup(
            PQuitStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PQuitStudentsGraphLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee3)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PQuitStudentsGraphLayout.setVerticalGroup(
            PQuitStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PQuitStudentsGraphLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LSchoolFee3)
                .addGap(20, 20, 20))
        );

        PNewStudentsGraph.setBackground(new java.awt.Color(51, 102, 255));
        PNewStudentsGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PNewStudentsGraphMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PNewStudentsGraphMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PNewStudentsGraphMouseExited(evt);
            }
        });

        LSchoolFee5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee5.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee5.setText("New Students Graph");
        LSchoolFee5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PNewStudentsGraphLayout = new javax.swing.GroupLayout(PNewStudentsGraph);
        PNewStudentsGraph.setLayout(PNewStudentsGraphLayout);
        PNewStudentsGraphLayout.setHorizontalGroup(
            PNewStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PNewStudentsGraphLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee5)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PNewStudentsGraphLayout.setVerticalGroup(
            PNewStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PNewStudentsGraphLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(LSchoolFee5)
                .addGap(20, 20, 20))
        );

        PQuitPie.setBackground(new java.awt.Color(51, 102, 255));
        PQuitPie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PQuitPieMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PQuitPieMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PQuitPieMouseExited(evt);
            }
        });

        LSchoolFee7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee7.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee7.setText("Quit Pie");
        LSchoolFee7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PQuitPieLayout = new javax.swing.GroupLayout(PQuitPie);
        PQuitPie.setLayout(PQuitPieLayout);
        PQuitPieLayout.setHorizontalGroup(
            PQuitPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PQuitPieLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee7)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PQuitPieLayout.setVerticalGroup(
            PQuitPieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PQuitPieLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(LSchoolFee7)
                .addGap(20, 20, 20))
        );

        PUnpaidStudentsGraph.setBackground(new java.awt.Color(51, 102, 255));
        PUnpaidStudentsGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PUnpaidStudentsGraphMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PUnpaidStudentsGraphMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PUnpaidStudentsGraphMouseExited(evt);
            }
        });

        LSchoolFee8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee8.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee8.setText("Unpaid Students Graph");
        LSchoolFee8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PUnpaidStudentsGraphLayout = new javax.swing.GroupLayout(PUnpaidStudentsGraph);
        PUnpaidStudentsGraph.setLayout(PUnpaidStudentsGraphLayout);
        PUnpaidStudentsGraphLayout.setHorizontalGroup(
            PUnpaidStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PUnpaidStudentsGraphLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee8)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PUnpaidStudentsGraphLayout.setVerticalGroup(
            PUnpaidStudentsGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PUnpaidStudentsGraphLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LSchoolFee8)
                .addGap(20, 20, 20))
        );

        PStudentsTot.setBackground(new java.awt.Color(51, 102, 255));
        PStudentsTot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PStudentsTotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PStudentsTotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PStudentsTotMouseExited(evt);
            }
        });

        LSchoolFee13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee13.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee13.setText("Students Total");
        LSchoolFee13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PStudentsTotLayout = new javax.swing.GroupLayout(PStudentsTot);
        PStudentsTot.setLayout(PStudentsTotLayout);
        PStudentsTotLayout.setHorizontalGroup(
            PStudentsTotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PStudentsTotLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee13)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PStudentsTotLayout.setVerticalGroup(
            PStudentsTotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PStudentsTotLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(LSchoolFee13)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout PGraphLayout = new javax.swing.GroupLayout(PGraph);
        PGraph.setLayout(PGraphLayout);
        PGraphLayout.setHorizontalGroup(
            PGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PGraphLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PNewStudentsGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PQuitStudentsGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PUnpaidStudentsGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PQuitPie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PStudentsTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PGraphLayout.setVerticalGroup(
            PGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PGraphLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(PNewStudentsGraph, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PQuitStudentsGraph, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PUnpaidStudentsGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PQuitPie, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PStudentsTot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        pnl_chart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Students Total");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("New Students");

        LTotalStudents.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTotalStudents.setText("Total");

        LNewStudents.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LNewStudents.setText("New");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("QUIT Students");

        LQUIT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LQUIT.setText("Quit");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Unpaid Total");

        LTotalUnpaid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTotalUnpaid.setText("Unpaid");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Month");

        LMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LMonth.setText("Month");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_chartLayout = new javax.swing.GroupLayout(pnl_chart);
        pnl_chart.setLayout(pnl_chartLayout);
        pnl_chartLayout.setHorizontalGroup(
            pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_chartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(61, 61, 61)
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(LMonth)
                    .addComponent(LTotalUnpaid)
                    .addComponent(LQUIT)
                    .addComponent(LNewStudents)
                    .addComponent(LTotalStudents))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        pnl_chartLayout.setVerticalGroup(
            pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_chartLayout.createSequentialGroup()
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(LMonth))
                .addGap(5, 5, 5)
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(LTotalStudents))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(LNewStudents))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(LQUIT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl_chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(LTotalUnpaid))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PGraph2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Display Graph");

        javax.swing.GroupLayout PGraph2Layout = new javax.swing.GroupLayout(PGraph2);
        PGraph2.setLayout(PGraph2Layout);
        PGraph2Layout.setHorizontalGroup(
            PGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PGraph2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PGraph2Layout.setVerticalGroup(
            PGraph2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PGraph2Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(10, 10, 10))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Current Month");

        TLaporan.setAutoCreateRowSorter(true);
        TLaporan.setModel(new javax.swing.table.DefaultTableModel(
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
        PSLaporan.setViewportView(TLaporan);

        LDate2.setText("Date");

        PMenuSfee2.setBackground(new java.awt.Color(51, 102, 255));

        PPrintByMonth.setBackground(new java.awt.Color(51, 102, 255));
        PPrintByMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintByMonthMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintByMonthMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintByMonthMouseExited(evt);
            }
        });

        LSchoolFee19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee19.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee19.setText("Print By Month");
        LSchoolFee19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee19MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PPrintByMonthLayout = new javax.swing.GroupLayout(PPrintByMonth);
        PPrintByMonth.setLayout(PPrintByMonthLayout);
        PPrintByMonthLayout.setHorizontalGroup(
            PPrintByMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintByMonthLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(LSchoolFee19)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PPrintByMonthLayout.setVerticalGroup(
            PPrintByMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintByMonthLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PPrintByYear.setBackground(new java.awt.Color(51, 102, 255));
        PPrintByYear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PPrintByYearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PPrintByYearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PPrintByYearMouseExited(evt);
            }
        });

        LSchoolFee20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LSchoolFee20.setForeground(new java.awt.Color(255, 255, 255));
        LSchoolFee20.setText("Print By Year");
        LSchoolFee20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LSchoolFee20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PPrintByYearLayout = new javax.swing.GroupLayout(PPrintByYear);
        PPrintByYear.setLayout(PPrintByYearLayout);
        PPrintByYearLayout.setHorizontalGroup(
            PPrintByYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintByYearLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(LSchoolFee20)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PPrintByYearLayout.setVerticalGroup(
            PPrintByYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPrintByYearLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LSchoolFee20)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CBReport.setBackground(new java.awt.Color(255, 255, 255));
        CBReport.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBReport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Report:", "Payment", "New Students", "Quit Students", " " }));
        CBReport.setBorder(null);

        CBMonth.setBackground(new java.awt.Color(255, 255, 255));
        CBMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month:" }));
        CBMonth.setBorder(null);

        CBYear.setBackground(new java.awt.Color(255, 255, 255));
        CBYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year:" }));
        CBYear.setBorder(null);
        CBYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PMenuSfee2Layout = new javax.swing.GroupLayout(PMenuSfee2);
        PMenuSfee2.setLayout(PMenuSfee2Layout);
        PMenuSfee2Layout.setHorizontalGroup(
            PMenuSfee2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMenuSfee2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(CBReport, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CBYear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PPrintByMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PPrintByYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 286, Short.MAX_VALUE))
        );
        PMenuSfee2Layout.setVerticalGroup(
            PMenuSfee2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PPrintByMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PPrintByYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PMenuSfee2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PMenuSfee2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBYear)
                    .addComponent(CBReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PMenuSfee1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PMenuSfee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel11)))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LDate2)
                    .addComponent(PSLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
            .addComponent(PGraph2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PMenuSfee2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PMenuSfee1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PMenuSfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PMenuSfee2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(LDate2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PSLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(PGraph2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(PGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1280, 720));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    }//GEN-LAST:event_PLogOutMouseExited

    private void PReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PReportMouseClicked

    private void PReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PReportMouseEntered

    private void PReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PReportMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PReportMouseExited

    private void PStudentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseEntered
        PStudents.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PStudentsMouseEntered

    private void PStudentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsMouseExited
       PStudents.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PStudentsMouseExited

    private void PSchoolFeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'School Fee' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportSchoolFee.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql = "Select * from tschoolfee where MOnth(date)=(select month(CURRENT_DATE())) and Year(date)=(select Year(CURRENT_DATE()));";
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PSchoolFeeMouseClicked

    private void PSchoolFeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseEntered
        PSchoolFee.setBackground(new java.awt.Color(0, 51, 153));
        // TODO add your handling code here:
    }//GEN-LAST:event_PSchoolFeeMouseEntered

    private void PSchoolFeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PSchoolFeeMouseExited
        PSchoolFee.setBackground(new java.awt.Color(51, 102, 255));
        // TODO add your handling code here:
    }//GEN-LAST:event_PSchoolFeeMouseExited

    private void LSchoolFee2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee2MouseClicked

    private void PUnpaidReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidReportMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Unpaid' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportUnpaid.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            String sql = "select tschoolfee.name as 'Student Name', tclass.AorB as 'A/B',tsiswa.class ,tclass.Classname as 'Class Name',tteacher.name as 'Teacher Name', tclass.time as 'Time', tschoolfee.fordate as 'Latest Date', CURRENT_DATE() ,timestampdiff(month, fordate, CURRENT_DATE())\n" +
                        "from tschoolfee\n" +
                        "inner join tsiswa on tsiswa.id =tschoolfee.studentid\n" +
                        "inner join tteacher\n" +
                        "inner join tclass on tclass.teacherid=tteacher.id and tsiswa.class=tclass.id\n" +
                        "where tschoolfee.id in (select max(tschoolfee.id) from tschoolfee where tschoolfee.id=(select max(tschoolfee.id)) group by studentid) and\n" +
                        "not timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())='0' and\n" +
                        "not tsiswa.status = \"quit\" \n" +
                        "group by studentid order by tsiswa.class";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PUnpaidReportMouseClicked

    private void PUnpaidReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidReportMouseEntered
        PUnpaidReport.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PUnpaidReportMouseEntered

    private void PUnpaidReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidReportMouseExited
       PUnpaidReport.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PUnpaidReportMouseExited

    private void LSchoolFee3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee3MouseClicked

    private void PQuitStudentsGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitStudentsGraphMouseClicked
        try{
            String query = "select DATE_FORMAT( QuitDate, \"%Y-%m\"),COUNT(ID) from tsiswa where status=\"QUIT\" group by DATE_FORMAT( quitDate, \"%Y-%m\");";
            
            /*
            dataset is a set of data to be displayed on the chart
            JDBCCategoryDataset is to load database dataset based on the SQL
            */
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DbConnection.connect(),query);
            int colCount = dataset.getColumnCount();    // to count the total of the column the data has
            /*
            use slidngcategoryset to adjust the label so that they don't overlap
            insert JDDBCdataset into slidingCategoryset so that the label can be adjusted
            */
            SlidingCategoryDataset slidingCategoryDataset= new SlidingCategoryDataset(dataset, 0, colCount);
            JFreeChart chart = ChartFactory.createLineChart("Quit Students", "Months", "Total Students",
            slidingCategoryDataset, PlotOrientation.VERTICAL,true, true, true);
            chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // to set the label of the month to 45 degree
            chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(4);
            BarRenderer renderer= null;
            CategoryPlot plot = chart.getCategoryPlot();;
            chart.getPlot().setBackgroundPaint( Color.WHITE );
            plot.setRangeGridlinePaint(Color.BLUE);
            renderer = new BarRenderer();
            ChartFrame frame= new ChartFrame("Query Chart", chart); // to display another frame
            frame.setVisible(true);
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);
        }catch(Exception e){
        }
    }//GEN-LAST:event_PQuitStudentsGraphMouseClicked

    private void PQuitStudentsGraphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitStudentsGraphMouseEntered
        PQuitStudentsGraph.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PQuitStudentsGraphMouseEntered

    private void PQuitStudentsGraphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitStudentsGraphMouseExited
        PQuitStudentsGraph.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PQuitStudentsGraphMouseExited

    private void LSchoolFee5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee5MouseClicked

    private void PNewStudentsGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewStudentsGraphMouseClicked
        try{
            String query = "select DATE_FORMAT( StartingDate, \"%Y-%m\"),COUNT(ID) from tsiswa where not DATE_FORMAT( StartingDate, \"%Y-%m\") is null group by DATE_FORMAT( StartingDate, \"%Y-%m\")";
            /*
            dataset is a set of data to be displayed on the chart
            JDBCCategoryDataset is to load database dataset based on the SQL
            */
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DbConnection.connect(),query);
            int colCount = dataset.getColumnCount();    // to count the total of the column the data has
            /*
            use slidngcategoryset to adjust the label so that they don't overlap
            insert JDDBCdataset into slidingCategoryset so that the label can be adjusted
            */
            SlidingCategoryDataset slidingCategoryDataset= new SlidingCategoryDataset(dataset, 0, colCount);
            JFreeChart chart = ChartFactory.createLineChart("New Students", "Months", "Total Students",
            slidingCategoryDataset, PlotOrientation.VERTICAL,true, true, true);
            chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // to set the label of the month to 45 degree
            chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(4);
            chart.getPlot().setBackgroundPaint( Color.WHITE );
            BarRenderer renderer= null;
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame= new ChartFrame("Query Chart", chart); // to display another frame
            frame.setVisible(true);
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);

        }catch(Exception e){
        }
    }//GEN-LAST:event_PNewStudentsGraphMouseClicked

    private void PNewStudentsGraphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewStudentsGraphMouseEntered
        PNewStudentsGraph.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PNewStudentsGraphMouseEntered

    private void PNewStudentsGraphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewStudentsGraphMouseExited
        PNewStudentsGraph.setBackground(new java.awt.Color( 51,102, 255));
    }//GEN-LAST:event_PNewStudentsGraphMouseExited

    private void LSchoolFee7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee7MouseClicked

    private void PQuitPieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitPieMouseClicked
        try{
            String query = "select reason, count(id) from tsiswa where status=\"QUIT\" and Month(quitdate)=Month(CURRENT_DATE()) and Year(quitdate)=Year(CURRENT_DATE()) group by reason";

            /*
            dataset is a set of data to be displayed on the chart
            JDBCCategoryDataset is to load database dataset based on the SQL
            */
            JDBCPieDataset dataset = new JDBCPieDataset(DbConnection.connect(),query);
            /*
            use slidngcategoryset to adjust the label so that they don't overlap
            insert JDDBCdataset into slidingCategoryset so that the label can be adjusted
            */
            JFreeChart chart = ChartFactory.createPieChart("Quit", dataset, true, true, true);
            //        chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // to set the label of the month to 45 degree
            //        chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(4);
            BarRenderer renderer= null;
            CategoryPlot plot = null;;
            chart.getPlot().setBackgroundPaint( Color.WHITE );
            renderer = new BarRenderer();
            PiePlot P = (PiePlot)chart.getPlot();
            //        P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Query Chart", chart); // to display another frame
            frame.setVisible(true);
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);
        }catch(Exception e){
        }


    }//GEN-LAST:event_PQuitPieMouseClicked

    private void PQuitPieMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitPieMouseEntered
        PQuitPie.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PQuitPieMouseEntered

    private void PQuitPieMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitPieMouseExited
        PQuitPie.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PQuitPieMouseExited

    private void LSchoolFee8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee8MouseClicked

    private void PUnpaidStudentsGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidStudentsGraphMouseClicked
        try{
            //String query = "select DATE_FORMAT( StartingDate, \"%Y-%m\"),COUNT(ID) from tsiswa group by DATE_FORMAT( StartingDate, \"%Y-%m\")";
            String Sql= "select concat(tschoolfee.name,\" ( id: \",tschoolfee.studentid,\")\"), timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())\n" +
                        "from tschoolfee inner join tsiswa\n" +
                        "on tsiswa.id =tschoolfee.studentid\n" +
                        "where tschoolfee.id in (select max(tschoolfee.id) from tschoolfee where tschoolfee.id=(select max(tschoolfee.id)) group by studentid) and\n" +
                        "not timestampdiff(month, ADDDATE(fordate, INTERVAL 11 DAY), CURRENT_DATE())='0' and\n" +
                        "tsiswa.status = \"active\" \n" +
                        "group by studentid;";
            /*
            dataset is a set of data to be displayed on the chart
            JDBCCategoryDataset is to load database dataset based on the SQL
            */
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DbConnection.connect(),Sql);
            int colCount = dataset.getColumnCount();    // to count the total of the column the data has
            /*
            use slidngcategoryset to adjust the label so that they don't overlap
            insert JDDBCdataset into slidingCategoryset so that the label can be adjusted
            */
            SlidingCategoryDataset slidingCategoryDataset= new SlidingCategoryDataset(dataset, 0, colCount);
            JFreeChart chart = ChartFactory.createBarChart("Unpaid Students", "Names", "Total Students",
            slidingCategoryDataset, PlotOrientation.VERTICAL,true, true, true);
            chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // to set the label of the month to 45 degree
            chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(1);
            BarRenderer renderer= null;
            CategoryPlot plot = chart.getCategoryPlot();;
            chart.getPlot().setBackgroundPaint( Color.WHITE );
            plot.setRangeGridlinePaint(Color.BLUE);
            renderer = new BarRenderer();
            ChartFrame frame= new ChartFrame("Query Chart", chart); // to display another frame
            frame.setVisible(true);
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);
        }catch(Exception e){
        }
        
        
    }//GEN-LAST:event_PUnpaidStudentsGraphMouseClicked

    private void PUnpaidStudentsGraphMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidStudentsGraphMouseEntered
        PUnpaidStudentsGraph.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PUnpaidStudentsGraphMouseEntered

    private void PUnpaidStudentsGraphMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PUnpaidStudentsGraphMouseExited
        PUnpaidStudentsGraph.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PUnpaidStudentsGraphMouseExited

    private void LSchoolFee9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee9MouseClicked

    private void PNewTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewTeacherMouseEntered
        PNewTeacher.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PNewTeacherMouseEntered

    private void PNewTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewTeacherMouseExited
        PNewTeacher.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PNewTeacherMouseExited

    private void PNewTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PNewTeacherMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'New Teacher' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/reportNewTeacher.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "select * from tteacher\n" +
                            "where\n" +
                            "status=\"Active\" and\n" +
                            "month(tteacher.startingdate)=month(Current_date()) and year(tteacher.startingdate)=year(Current_date())";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }                 
    }//GEN-LAST:event_PNewTeacherMouseClicked

    private void CBYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBYearActionPerformed

    private void LSchoolFee10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee10MouseClicked

    private void PQuitTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitTeacherMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Quit Teacher' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/reportQuitTeacher.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "SELECT\n" +
                            "     tteacher.`Id` AS Id,\n" +
                            "     tteacher.`Name` AS Name,\n" +
                            "     tteacher.`Gender` AS Gender,\n" +
                            "     tteacher.`Address` AS Address,\n" +
                            "     tteacher.`Phone` AS Phone,\n" +
                            "     tteacher.`Birthday` AS Birthday,\n" +
                            "     tteacher.`Birthplace` AS Birthplace,\n" +
                            "     tteacher.`Religion` AS Religion,\n" +
                            "     tteacher.`StartingDate` AS StartingDate,\n" +
                            "     tteacher.`QuitDate` AS QuitDate,\n" +
                            "     tteacher.`Status` AS Status,\n" +
                            "     tteacher.`Category` AS Category\n" +
                            "FROM\n" +
                            "     `tteacher` tteacher\n" +
                            "where\n" +
                            "tteacher.`Status`=\"Quit\" and\n" +
                            "month(tteacher.`QuitDate`)=month(Current_date()) and year(tteacher.`QuitDate`)=year(Current_date())";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PQuitTeacherMouseClicked

    private void PQuitTeacherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitTeacherMouseEntered
        PQuitTeacher.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PQuitTeacherMouseEntered

    private void PQuitTeacherMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PQuitTeacherMouseExited
        PQuitTeacher.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PQuitTeacherMouseExited

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

    private void LSchoolFee1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee1MouseClicked

    private void PPrintNewStudentReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintNewStudentReportMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'New Students' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportNew.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "select tsiswa.id as \"Student Id\", tsiswa.name, tsiswa.class, tclass.classname, tsiswa.StartingDate, tteacher.name as \"Teacher Name\", tclass.time\n" +
                            "from tsiswa\n" +
                            "inner join tclass on tclass.id=tsiswa.class\n" +
                            "inner join tteacher on tteacher.id=tclass.teacherid\n" +
                            "where month(tsiswa.StartingDate)=month(Current_date()) and\n" +
                            "year(tsiswa.StartingDate)=year(Current_date()) and\n" +
                            "not tsiswa.class='dis';";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PPrintNewStudentReportMouseClicked

    private void PPrintNewStudentReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintNewStudentReportMouseEntered
        PPrintNewStudentReport.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintNewStudentReportMouseEntered

    private void PPrintNewStudentReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintNewStudentReportMouseExited
        PPrintNewStudentReport.setBackground(new java.awt.Color(51,102,255));
    }//GEN-LAST:event_PPrintNewStudentReportMouseExited

    private void LSchoolFee4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee4MouseClicked

    private void PPrintClassesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintClassesReportMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Classes' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportClass.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "select tclass.id, tclass.classname, tclass.aorb, tclass.teacherid, tteacher.name,  tclass.time, count(tsiswa.id)\n" +
                            "from tclass\n" +
                            "inner join tsiswa on tsiswa.class=tclass.id\n" +
                            "inner join tteacher on tclass.teacherid = tteacher.id\n" +
                            "where not tclass.id='dis'\n" +
                            "group by tclass.id";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PPrintClassesReportMouseClicked

    private void PPrintClassesReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintClassesReportMouseEntered
        PPrintClassesReport.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintClassesReportMouseEntered

    private void PPrintClassesReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintClassesReportMouseExited
        PPrintClassesReport.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PPrintClassesReportMouseExited

    private void LSchoolFee11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee11MouseClicked

    private void PrintQuitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintQuitMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Quit Students' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportQuit.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql = "select tsiswa.ID, tsiswa.name, tsiswa.class AS 'Class Id', tclass.classname,tclass.time, tteacher.name as 'Teacher', tsiswa.quitdate, tsiswa.reason\n" +
                            "from tsiswa\n" +
                            "inner join tteacher \n" +
                            "inner join tclass on tclass.teacherid = tteacher.id\n" +
                            "where tsiswa.status=\"QUIT\" and\n" +
                            "month(tsiswa.quitdate)=month(Current_date()) and year(tsiswa.quitdate)=year(Current_date())\n" +
                            "group by tsiswa.id;";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PrintQuitMouseClicked

    private void PrintQuitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintQuitMouseEntered
        PrintQuit.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PrintQuitMouseEntered

    private void PrintQuitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintQuitMouseExited
        PrintQuit.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PrintQuitMouseExited

    private void LSchoolFee12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee12MouseClicked

    private void PPrintStudentReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintStudentReportMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Students Total' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportStudent.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "SELECT\n" +
                            "     tsiswa.`Id`,\n" +
                            "     tsiswa.`Name`,\n" +
                            "     tclass.`ClassName`,\n" +
                            "     tclass.`Time`,\n" +
                            "     tteacher.`Name` AS tteacher_Name,\n" +
                            "     tclass.`AorB` AS tclass_AorB,\n" +
                            "     tclass.`Id` AS tclass_Id\n" +
                            "FROM\n" +
                            "     `tteacher` tteacher INNER JOIN `tclass` tclass ON tteacher.`Id` = tclass.`TeacherID`\n" +
                            "     INNER JOIN `tsiswa` tsiswa ON tclass.`Id` = tsiswa.`Class`\n" +
                            "WHERE\n" +
                            "     tsiswa.status = 'Active'";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PPrintStudentReportMouseClicked

    private void PPrintStudentReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintStudentReportMouseEntered
        PPrintStudentReport.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintStudentReportMouseEntered

    private void PPrintStudentReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintStudentReportMouseExited
        PPrintStudentReport.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PPrintStudentReportMouseExited

    private void LSchoolFeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFeeMouseClicked

    }//GEN-LAST:event_LSchoolFeeMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String date = LDate2.getText();
        String Total= (String) LTotalStudents.getText();
        String New= (String) LNewStudents.getText();
        String Quit= (String) LQUIT.getText();
        String Unpaid= LTotalUnpaid.getText();
                
        
        try{
            String sql = "Insert into tlaporan ( date, Active, new, quit, unpaid) values (?,?,?,?,?)";
            //Id can be emptied.
            //Even though Id is the primary key of the table, the program automattically create an numeric id e.g '43' for the Id;
            pst = conn.prepareStatement(sql);
            
            
            pst.setString(1, date); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(2, Total);
            pst.setString(3, New);
            pst.setString(4, Quit);
            pst.setString(5, Unpaid);
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            TampilDataUnpaid();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void PStudentsTotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsTotMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PStudentsTotMouseExited

    private void PStudentsTotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsTotMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_PStudentsTotMouseEntered

    private void PStudentsTotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PStudentsTotMouseClicked
        try{
            String query = "SELECT Date, Active from tlaporan";
            /*
            dataset is a set of data to be displayed on the chart
            JDBCCategoryDataset is to load database dataset based on the SQL
            */
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(DbConnection.connect(),query);
            int colCount = dataset.getColumnCount();    // to count the total of the column the data has
            /*
            use slidngcategoryset to adjust the label so that they don't overlap
            insert JDDBCdataset into slidingCategoryset so that the label can be adjusted
            */
            SlidingCategoryDataset slidingCategoryDataset= new SlidingCategoryDataset(dataset, 0, colCount);
            JFreeChart chart = ChartFactory.createLineChart("Students Total", "Months", "Total Students",
                slidingCategoryDataset, PlotOrientation.VERTICAL,true, true, true);
            chart.getCategoryPlot().getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.DOWN_45); // to set the label of the month to 45 degree
            chart.getCategoryPlot().getDomainAxis().setMaximumCategoryLabelLines(4);
            chart.getPlot().setBackgroundPaint( Color.WHITE );
            BarRenderer renderer= null;
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setRangeGridlinePaint(Color.BLUE);
            ChartFrame frame= new ChartFrame("Query Chart", chart); // to display another frame
            frame.setVisible(true);
            frame.setSize(1000, 650);
            frame.setLocationRelativeTo(null);

        }catch(Exception e){
        }
    }//GEN-LAST:event_PStudentsTotMouseClicked

    private void LSchoolFee13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee13MouseClicked

    private void LSchoolFee19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee19MouseClicked

    private void PPrintByMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByMonthMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        String Report = (String) CBReport.getSelectedItem();
        String Month = (String) CBMonth.getSelectedItem();
        String Year = (String) CBYear.getSelectedItem();
        
        if(Report.equals("Report:") || Month.equals("Month:")){
        JOptionPane.showMessageDialog(null, "Please select which report and which month to print");
        }else{
        
            JOptionPane.showMessageDialog(null, "You have clicked 'Print By Month' Button\nIt might take a while for the report to load");
        if(Report.equals("Payment")){
            try{
               File file = new File("src/Report/ReportSchoolFee.jrxml");
               JasperDesign jasperDesign = JRXmlLoader.load(file);
               String sql = "select * from tschoolfee where Month(date)=Month('"+Month+"-01') and Year(date)=Year('"+Month+"-01')";
               JRDesignQuery newQuery = new JRDesignQuery();
               newQuery.setText(sql);
               jasperDesign.setQuery (newQuery);
               JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
               JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
               System.out.println("jasperPrint"+jasperPrint);
               JasperViewer.viewReport (jasperPrint, false);
               System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(Report.equals("New Students")){
            try{
                File file = new File("src/Report/ReportNew.jrxml");
                JasperDesign jasperDesign = JRXmlLoader.load(file);
                String sql =    "select tsiswa.id as \"Student Id\", tsiswa.name, tsiswa.class, tclass.classname, tsiswa.StartingDate, tteacher.name as \"Teacher Name\", tclass.time\n" +
                                "from tsiswa\n" +
                                "inner join tclass on tclass.id=tsiswa.class\n" +
                                "inner join tteacher on tteacher.id=tclass.teacherid\n" +
                                "where month(tsiswa.StartingDate)=Month('"+Month+"-01') and\n" +
                                "year(tsiswa.StartingDate)=year('"+Month+"-01') and\n" +
                                "not tsiswa.class='dis';";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jasperDesign.setQuery (newQuery);
                JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                System.out.println("jasperPrint"+jasperPrint);
                JasperViewer.viewReport (jasperPrint, false);
                System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }else if(Report.equals("Quit Students")){
            
            try{
                File file = new File("src/Report/ReportQuit.jrxml");
                System.out.println("file"+file);
                JasperDesign jasperDesign = JRXmlLoader.load(file);
                System.out.println("jasperDesign"+jasperDesign);
                String sql =    "select tsiswa.ID, tsiswa.name, tsiswa.class AS 'Class Id', tclass.classname,tclass.time, tteacher.name as 'Teacher', tsiswa.quitdate, tsiswa.reason\n" +
                                "from tsiswa\n" +
                                "inner join tteacher \n" +
                                "inner join tclass on tclass.teacherid = tteacher.id\n" +
                                "where tsiswa.status=\"QUIT\" and\n" +
                                "month(tsiswa.quitdate)=Month('"+Month+"-01') and year(tsiswa.quitdate)=Year('"+Month+"-01')\n" +
                                "group by DATE_FORMAt( tsiswa.quitDate, \"%Y-%m\");";

                JRDesignQuery newQuery = new JRDesignQuery();
                System.out.println("newQ"+newQuery);
                newQuery.setText(sql);
                jasperDesign.setQuery (newQuery);
                JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
                System.out.println("jasperReport"+jasperReport);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                System.out.println("jasperPrint"+jasperPrint);
                JasperViewer.viewReport (jasperPrint, false);
                System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }else{
            JOptionPane.showMessageDialog(null, "school fee, unpaid, or new students or It's not working");
        } 
        }       
    }//GEN-LAST:event_PPrintByMonthMouseClicked

    private void PPrintByMonthMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByMonthMouseEntered
        PPrintByMonth.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintByMonthMouseEntered

    private void PPrintByMonthMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByMonthMouseExited
        PPrintByMonth.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PPrintByMonthMouseExited

    private void LSchoolFee20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee20MouseClicked

    private void PPrintByYearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByYearMouseClicked
        String Report = (String) CBReport.getSelectedItem();
        String Month = (String) CBMonth.getSelectedItem();
        String Year = (String) CBYear.getSelectedItem();
        
        if(Report.equals("Report:") || Year.equals("Year:")){
            JOptionPane.showMessageDialog(null, "Please select which report and which year to print");
        }else{
        JOptionPane.showMessageDialog(null, "You have clicked 'Print By Year' Button\nIt might take a while for the report to load");
        if(Report.equals("School Fee")){
            try{
               File file = new File("src/Report/ReportSchoolFee.jrxml");
               JasperDesign jasperDesign = JRXmlLoader.load(file);
               String sql = "select * from tschoolfee where Year(date)='"+Year+"'";
               JRDesignQuery newQuery = new JRDesignQuery();
               newQuery.setText(sql);
               jasperDesign.setQuery (newQuery);
               JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
               JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
               System.out.println("jasperPrint"+jasperPrint);
               JasperViewer.viewReport (jasperPrint, false);
               System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(Report.equals("New Students")){
            try{
                File file = new File("src/Report/ReportNew.jrxml");
                JasperDesign jasperDesign = JRXmlLoader.load(file);
                String sql =    "select tsiswa.id as \"Student Id\", tsiswa.name, tsiswa.class, tclass.classname, tsiswa.StartingDate, tteacher.name as \"Teacher Name\", tclass.time\n" +
                                "from tsiswa\n" +
                                "inner join tclass on tclass.id=tsiswa.class\n" +
                                "inner join tteacher on tteacher.id=tclass.teacherid\n" +
                                "where \n" +
                                "year(tsiswa.StartingDate)='"+Year+"' and\n" +
                                "not tsiswa.class='dis';";
                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(sql);
                jasperDesign.setQuery (newQuery);
                JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                System.out.println("jasperPrint"+jasperPrint);
                JasperViewer.viewReport (jasperPrint, false);
                System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }else if(Report.equals("Quit Students")){
            
            try{
                File file = new File("src/Report/ReportQuit.jrxml");
                System.out.println("file"+file);
                JasperDesign jasperDesign = JRXmlLoader.load(file);
                System.out.println("jasperDesign"+jasperDesign);
                String sql =    "select tsiswa.ID, tsiswa.name, tsiswa.class AS 'Class Id', tclass.classname,tclass.time, tteacher.name as 'Teacher', tsiswa.quitdate, tsiswa.reason\n" +
                                "from tsiswa\n" +
                                "inner join tteacher \n" +
                                "inner join tclass on tclass.teacherid = tteacher.id\n" +
                                "where tsiswa.status=\"QUIT\" and\n" +
                                "year(tsiswa.quitdate)='"+Year+"'\n" +
                                "group by tsiswa.id";

                JRDesignQuery newQuery = new JRDesignQuery();
                System.out.println("newQ"+newQuery);
                newQuery.setText(sql);
                jasperDesign.setQuery (newQuery);
                JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
                System.out.println("jasperReport"+jasperReport);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
                System.out.println("jasperPrint"+jasperPrint);
                JasperViewer.viewReport (jasperPrint, false);
                System.out.println("file"+file);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        
        }else{
            
        }}   
        /*
        try{
            JOptionPane.showMessageDialog(null, "You have clicked 'Print By Year' Button\nIt might take a while for the report to load");
            File file = new File("src/Report/ReportSchoolFee.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            String sql = "select * from tschoolfee where Year(date)="+CBYear.getSelectedItem()+"";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperC
        ompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }*/
    }//GEN-LAST:event_PPrintByYearMouseClicked

    private void PPrintByYearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByYearMouseEntered
       PPrintByYear.setBackground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_PPrintByYearMouseEntered

    private void PPrintByYearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PPrintByYearMouseExited
        PPrintByYear.setBackground(new java.awt.Color(51, 102, 255));
    }//GEN-LAST:event_PPrintByYearMouseExited

    private void LSchoolFee21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LSchoolFee21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_LSchoolFee21MouseClicked

    private void PTeacherTotalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherTotalMouseClicked
        java.sql.Connection conn = new DbConnection().connect();
        JOptionPane.showMessageDialog(null, "You have clicked 'Teachers Total' Button\nIt might take a while for the report to load");
        try{
            File file = new File("src/Report/ReportTeachers.jrxml");
            System.out.println("file"+file);
            JasperDesign jasperDesign = JRXmlLoader.load(file);
            System.out.println("jasperDesign"+jasperDesign);
            String sql =    "select tteacher.id, tteacher.name, count(tteacher.id)\n" +
                            "from tteacher\n" +
                            "inner join tclass on tclass.teacherid = tteacher.id\n" +
                            "where\n" +
                            "tteacher.status=\"Active\"\n" +
                            "group by tteacher.id";
            
            JRDesignQuery newQuery = new JRDesignQuery();
            System.out.println("newQ"+newQuery);
            newQuery.setText(sql);
            jasperDesign.setQuery (newQuery);
            JasperReport jasperReport  = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("jasperReport"+jasperReport);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
            System.out.println("jasperPrint"+jasperPrint);
            JasperViewer.viewReport (jasperPrint, false);
            System.out.println("file"+file);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_PTeacherTotalMouseClicked

    private void PTeacherTotalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherTotalMouseEntered
        PTeacherTotal.setBackground(new java.awt.Color(0, 51, 153));// TODO add your handling code here:
    }//GEN-LAST:event_PTeacherTotalMouseEntered

    private void PTeacherTotalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PTeacherTotalMouseExited
        PTeacherTotal.setBackground(new java.awt.Color(51, 102, 255));// TODO add your handling code here:
    }//GEN-LAST:event_PTeacherTotalMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBMonth;
    private javax.swing.JComboBox<String> CBReport;
    private javax.swing.JComboBox<String> CBYear;
    private javax.swing.JPanel JMenu;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LDate2;
    private javax.swing.JLabel LMonth;
    private javax.swing.JLabel LNewStudents;
    private javax.swing.JLabel LQUIT;
    private javax.swing.JLabel LSchoolFee;
    private javax.swing.JLabel LSchoolFee1;
    private javax.swing.JLabel LSchoolFee10;
    private javax.swing.JLabel LSchoolFee11;
    private javax.swing.JLabel LSchoolFee12;
    private javax.swing.JLabel LSchoolFee13;
    private javax.swing.JLabel LSchoolFee19;
    private javax.swing.JLabel LSchoolFee2;
    private javax.swing.JLabel LSchoolFee20;
    private javax.swing.JLabel LSchoolFee21;
    private javax.swing.JLabel LSchoolFee3;
    private javax.swing.JLabel LSchoolFee4;
    private javax.swing.JLabel LSchoolFee5;
    private javax.swing.JLabel LSchoolFee7;
    private javax.swing.JLabel LSchoolFee8;
    private javax.swing.JLabel LSchoolFee9;
    private javax.swing.JLabel LTotalStudents;
    private javax.swing.JLabel LTotalUnpaid;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PGraph;
    private javax.swing.JPanel PGraph2;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PMenuSfee;
    private javax.swing.JPanel PMenuSfee1;
    private javax.swing.JPanel PMenuSfee2;
    private javax.swing.JPanel PNewStudentsGraph;
    private javax.swing.JPanel PNewTeacher;
    private javax.swing.JPanel PPrintByMonth;
    private javax.swing.JPanel PPrintByYear;
    private javax.swing.JPanel PPrintClassesReport;
    private javax.swing.JPanel PPrintNewStudentReport;
    private javax.swing.JPanel PPrintStudentReport;
    private javax.swing.JPanel PQuitPie;
    private javax.swing.JPanel PQuitStudentsGraph;
    private javax.swing.JPanel PQuitTeacher;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JScrollPane PSLaporan;
    private javax.swing.JPanel PSchoolFee;
    private javax.swing.JPanel PStudents;
    private javax.swing.JPanel PStudentsTot;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JPanel PTeacherTotal;
    private javax.swing.JPanel PUnpaidReport;
    private javax.swing.JPanel PUnpaidStudentsGraph;
    private javax.swing.JPanel PrintQuit;
    private javax.swing.JTable TLaporan;
    private javax.swing.JTable TLaporan1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel pnl_chart;
    // End of variables declaration//GEN-END:variables
}
