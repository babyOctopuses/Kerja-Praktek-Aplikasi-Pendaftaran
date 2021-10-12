package Form;

import java.sql.*;// this and javax.swing are all you need to build db connection
import javax.swing.*;
import DatabaseConnection.DbConnection;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.time.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;

public class FormReceipt extends javax.swing.JFrame {
    private String SQL;
    private String SQL2;
    Connection conn= null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    Double Amount, LateFee, total;
    
    public FormReceipt() {
        initComponents();
        CurrentDate();
        conn = DbConnection.connect();
        HideButton();
        autonumber();
        autoNumberPayment();
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
            System.out.println("CATEGORY"+ Category);
            try{
                String sql="select tclass.SchoolFee, tcategory.Deduction from tclass inner join tcategory where tclass.Id= '"+Class+"' and tcategory.Status='Ada Saudara'";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                System.out.println("rs setFee: "+rs);
                if(rs.next()){
                    String SchoolFee=rs.getString("tclass.SchoolFee");
                    String Deduction=rs.getString("tcategory.Deduction");
                    System.out.println("schoolfee"+ SchoolFee);
                    Double SchoolFee2= (Double) Double.parseDouble(SchoolFee);
                    System.out.println("schoolfee2"+ SchoolFee2);
                    Double Deduction2= (Double) Double.parseDouble(Deduction);
                    SchoolFee2 = SchoolFee2-Deduction2;
                    System.out.println("schoolfee2"+ SchoolFee2);
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
     
    //to get the latest month and year of payment and also set late or not
    public void SetDate(String Id, String StartingDate){
    try{
            
            //to get dates from a studen id from tschoolfee and tsiswa into int
            String sql2="select tschoolfee.name, max(tschoolfee.id), tschoolfee.formonth, tschoolfee.foryear, tschoolfee.groups, DATE_FORMAT(tsiswa.StartingDate, \"%d\")\n" +
                        "from tschoolfee\n" +
                        "inner join tsiswa\n" +
                        "on tsiswa.id=tschoolfee.studentid && tschoolfee.studentid\n" +
                        "where tschoolfee.id =(\n" +
                        "select id from tschoolfee where id=(select max(id) from tschoolfee where studentid='"+Id+"' and not tschoolfee.groups='C-Fee'))";
                        
            System.out.println("sql2"+sql2);
            pst= conn.prepareStatement(sql2);
            rs= pst.executeQuery();
            System.out.println("rs:"+rs);
            
            while(rs.next()){
            
            //DECLARATION
            //students payment date is from startingdate day from tsiswa, formonth, foryear from tschoolfee
            String ForMonth = rs.getString("tschoolfee.formonth");
            String ForYear = rs.getString("tschoolfee.foryear");
            String StartingDateDay = rs.getString("DATE_FORMAT(tsiswa.StartingDate, \"%d\")");
            System.out.println("ForMonth: "+ ForMonth);
            System.out.println("ForYear"+ ForYear);
            System.out.println("StartingDateDay: "+ StartingDateDay);
            
            //parse string from above to int
            int StartingDateDayint = Integer.parseInt(StartingDateDay);//starting date day from tsiswa
            int ForMonthint = Integer.parseInt(ForMonth); // formonth from tschoolfee
            int ForYearint = Integer.parseInt(ForYear); // foryear from tschoolfee
            System.out.println("StartingDateDayint: "+ StartingDateDayint);
            System.out.println("ForMonthint"+ ForMonthint);
            System.out.println("ForYearint: "+ ForYearint);
            
            //date after 10days of startingdate, nextmonth is 
            int plusten=StartingDateDayint+11;//to get the starting date and check if it's 10 days alr
            int nextmonth=ForMonthint+1;
            int nextyear=ForYearint+1;
            System.out.println("plusten: "+ plusten);
            System.out.println("nextmonth"+ nextmonth);
            System.out.println("nextyear: "+ nextyear);
            
            //set today's date and selected date
            LocalDate today = LocalDate.now();
            System.out.println("today: "+ today);
            
            //userday is startingdate(plus ten days) and formonth+foryear of the latest payment
            //plusten=1;
            LocalDate userday = LocalDate.of(ForYearint, ForMonthint, plusten);
            System.out.println("userday: "+ userday);
            
            // to  calculate the difference of the date and month from starting date and current date            
            Period diff = Period.between(userday, today);
            System.out.println("userday "+userday+" today"+today+" diff: "+diff.getDays()+"days"+" "+diff.getMonths()+" Months");
            System.out.println("nextmonth "+ nextmonth);
            //!ASSIGN TO TEXT
            if(nextmonth>12){
                nextmonth=nextmonth-12;
                LUnpaid.setText(""+diff.getMonths());
                LNPDate.setText(nextyear+"-"+nextmonth+"-"+plusten);
                LMonth.setText(""+nextmonth);
                LYear.setText(""+nextyear);
                LForDate.setText(nextyear+"-"+nextmonth+"-"+StartingDateDayint);

            }else if(nextmonth<=12){
                LUnpaid.setText(""+diff.getMonths());
                LNPDate.setText(ForYearint+"-"+nextmonth+"-"+plusten);
                LMonth.setText(""+nextmonth);
                LYear.setText(""+ForYearint);
                LForDate.setText(ForYearint+"-"+nextmonth+"-"+StartingDateDayint);
            }
            
            // this part does not need the if conditions
            // get the month, year of current date and plusten for the date
            try{
                String NPDate = LNPDate.getText();
                String sql="Select DayName('"+NPDate+"'), month('"+today+"'), year('"+today+"')";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    String getDate=rs.getString("DayName('"+NPDate+"')");
                    String todaymonth=rs.getString("month('"+today+"')");
                    String todayyear=rs.getString("year('"+today+"')");
                    LDayName.setText(getDate);
                    LNPDateTM.setText(todayyear+"-"+todaymonth+"-"+plusten);
                    checkLate(todayyear, todaymonth,  plusten, today );
                } //closes if(rs.next)
            }catch(Exception e){
                    System.out.println("error: "+e);
            }     
            
            //to get the day name of this month's latest date. (Am trying the other way)It must be inside another try because of the LNPDateTM is only available inside the try
            try{
                String DateTM =LNPDateTM.getText();
                String dayname="Select DayName('"+DateTM+"')";
                pst=conn.prepareStatement(dayname);
                rs=pst.executeQuery();
                if(rs.next()){
                    String getTMName=rs.getString("DayName('"+DateTM+"')");
                    LDayNameTM.setText(getTMName);
                }
            }catch(Exception e){
                System.out.println("error: "+e);
            }
                        
            //add class time
            try{
                String ClassCode= LClass.getText();
                String classa="Select * from tclass where Id='"+ClassCode+"'";
                pst=conn.prepareStatement(classa);
                rs=pst.executeQuery();
                if(rs.next()){
                    //if you want to contune working on this code, erase from bekow here because this part is already commented down there
                    //also you deleted the parameter class and category so that you can call the function on the username() when passing
                    String Time=rs.getString("Time");
                    LCTime.setText(Time.substring(0, 3));                    
                }
            }catch(Exception e){
                    System.out.println("error: "+e);
            }
        }
            displayForMonthForYear(Id);
        }catch(Exception e){
        
        }
    } 

    public void SetLateFee(String Category){
        String Late = LLate.getText();   
        System.out.println("Late:"+Late);
        //Regular Category takes from tcategory
            if(LUnpaid.getText().equals("0")){    
                if(Late.equals("Late")){
                    if(Category.equals("Regular")){
                        //starts here    
                        String sqld = "select * from tsettings";
                            try{
                                pst = conn.prepareStatement(sqld);
                                rs=pst.executeQuery();
                                if(rs.next()){
                                    String latefee= rs.getString("LateFee");
                                    LLateFee.setText(latefee);
                                }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,e);
                            }
                    }else{
                        //Non regular Category takes from tcategory schoolfee    
                        try{
                                String sql="select LateFee from tcategory where Status='"+Category+"'";
                                pst=conn.prepareStatement(sql);
                                rs=pst.executeQuery();
                                if(rs.next()){
                                    String latefee=rs.getString("LateFee");
                                    LLateFee.setText(latefee);
                                }
                            }catch(Exception e){
                                System.out.println("error: "+e);
                            }
                        }
                    //ends here

                }else if(Late.equals("Not Late")){
                   LLateFee.setText("0");
                }        

            }else{ // this else is if unpaid months is more than one
                if(Category.equals("Regular")){
                        //starts here    
                        String sqld = "select * from tsettings";
                            try{
                                pst = conn.prepareStatement(sqld);
                                rs=pst.executeQuery();
                                System.out.println("rs setfee"+rs);
                                if(rs.next()){
                                    String LateFee= rs.getString("LateFee");
                                    LLateFee.setText(LateFee);
                            }
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,e);
                        }

                    }else{
                        //Non regular Category takes from tcategory schoolfee    
                        try{
                                String sql="select LateFee from tcategory where Status='"+Category+"'";
                                pst=conn.prepareStatement(sql);
                                rs=pst.executeQuery();
                                if(rs.next()){
                                    String LateFee=rs.getString("LateFee");
                                    LLateFee.setText(LateFee);
                                }
                            }catch(Exception e){
                                System.out.println("error: "+e);
                            }
                        }

            }      
                
     }
    
    public void displayForMonthForYear(String Id){
        //display the latest month and date of a student's schoolfee
            try{
                String sql="select formonth, foryear from tschoolfee where studentid="+Id+" && id=(select max(Id)"
                            + "from tschoolfee where studentid="+Id+" and not groups='C-fee')";
                pst=conn.prepareStatement(sql);
                rs=pst.executeQuery();
                if(rs.next()){
                    String formonth=rs.getString("formonth");
                    String foryear=rs.getString("foryear");
                    LForMonth.setText("Month: "+formonth+"   Year: "+ foryear);
                }
            }catch(Exception e){
                System.out.println("error: "+e);
            }
    }
    
    public void getStartingDateName(String StartingDate){
        try{
            String dayname="Select DayName('"+StartingDate+"')";
            pst=conn.prepareStatement(dayname);
            rs=pst.executeQuery();
            if(rs.next()){
                String getSDName=rs.getString("DayName('"+StartingDate+"')");
                LDayNameSD.setText(getSDName);
   }
        }catch(Exception e){
        }
    }
    
    // To Calculate the total of school fee and late fee
    public void Calculate(){
        Double SchoolFee= (Double) Double.parseDouble(LSchoolFee.getText());
        Double RegisFee= (Double) Double.parseDouble(LLateFee.getText());
        total=SchoolFee+RegisFee;
        LTotal.setText(Double.toString(total));
    }
    
    public void checkLate(String foryear, String todaymonth, int plusten, LocalDate today ){
              LocalDate userdayN = LocalDate.of(Integer.parseInt(foryear), Integer.parseInt(todaymonth), plusten);
              Period diffN = Period.between(userdayN, today);
              System.out.println("userdayN: "+userdayN+"Today is "+today+" Months "+diffN.getMonths()+" Days "+diffN.getDays());
              System.out.println("diff.getmonth: "+diffN.getDays());
              if(diffN.getDays()>=1){
                LLate.setText("Late");
                  
              }else if(diffN.getDays()<1){
                LLate.setText("Not Late");
              }
    }
    
    public void username(String User, String StartingDate, String Id,String Name, String Class, String Category){
        LUser.setText(User);
        Luser.setText(User);
        LName.setText(Name);
        LStudentID.setText(Id);
        LCategory.setText(Category);
        LSDate.setText(StartingDate);
        LClass.setText(Class);
        SetFee(Class, Category);
        SetDate(Id, StartingDate);
        getStartingDateName(StartingDate);
        String test=LCategory.getText();
        SetLateFee(Category);
        
        //to decide if there's late fee
//        if(LLate.getText().equals("Not Late")){
//            LLateFee.setText("0");
//        }else if(LLate.getText().equals("Late")){
//            SetLateFee(Class, Category);
//        }
        Calculate();
}

public void HideButton(){
    BPrint.setEnabled(false); 
    BPay.setEnabled(false);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LCode = new javax.swing.JLabel();
        LCategory = new javax.swing.JLabel();
        BGenerateReceipt = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        LUser = new javax.swing.JLabel();
        LTotal = new javax.swing.JLabel();
        LStudentID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LCodeP = new javax.swing.JLabel();
        LID = new javax.swing.JLabel();
        LID1 = new javax.swing.JLabel();
        LClass = new javax.swing.JLabel();
        LName = new javax.swing.JLabel();
        LSchoolFee = new javax.swing.JLabel();
        LLateFee = new javax.swing.JLabel();
        LMonth = new javax.swing.JLabel();
        LYear = new javax.swing.JLabel();
        LGroup = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        LForDate = new javax.swing.JLabel();
        LCTime = new javax.swing.JLabel();
        BClear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        LDate = new javax.swing.JLabel();
        LTime = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LDayNameSD = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        LForMonth = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        LDayName = new javax.swing.JLabel();
        LNPDate = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        LUnpaid = new javax.swing.JLabel();
        LSDate = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        LLate = new javax.swing.JLabel();
        LDayNameTM = new javax.swing.JLabel();
        LNPDateTM = new javax.swing.JLabel();
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
        LRPaymentID = new javax.swing.JLabel();
        LRStudentName = new javax.swing.JLabel();
        LRClassID = new javax.swing.JLabel();
        LRForMonthYear = new javax.swing.JLabel();
        LRGroup = new javax.swing.JLabel();
        LRLateFee = new javax.swing.JLabel();
        LRAmount = new javax.swing.JLabel();
        LRTotal = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        ResStudentID = new javax.swing.JLabel();
        LRDate = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        LRStudentID = new javax.swing.JLabel();
        Background1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BPay = new javax.swing.JButton();
        BPrint = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();

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
        Luser.setText("User");

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
                .addGap(0, 141, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)), "Payment Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("For Month");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Category");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Total");

        LCode.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LCode.setText("Code");

        LCategory.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LCategory.setText("Category");

        BGenerateReceipt.setBackground(new java.awt.Color(255, 255, 255));
        BGenerateReceipt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BGenerateReceipt.setText("Generate Receipt");
        BGenerateReceipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGenerateReceiptActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("For Year");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("School Fee");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Name");

        LUser.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LUser.setText("User");

        LTotal.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        LTotal.setText("Total");

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

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Late Fee");

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

        LSchoolFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LSchoolFee.setText("School Fee");

        LLateFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LLateFee.setText("LateFee");

        LMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LMonth.setText("Month");

        LYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LYear.setText("Year");

        LGroup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LGroup.setText("S-fee");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("For Date");

        LForDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LForDate.setText("Date");

        LCTime.setText("Time");

        BClear.setBackground(new java.awt.Color(255, 255, 255));
        BClear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BClear.setText("Clear");
        BClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BGenerateReceipt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15)))
                    .addComponent(jLabel9))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(LName)
                    .addComponent(LSchoolFee)
                    .addComponent(LGroup)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LCTime)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel10)
                        .addComponent(jLabel25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel13)))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LTotal)
                    .addComponent(LForDate)
                    .addComponent(LUser)
                    .addComponent(LMonth)
                    .addComponent(LYear)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LLateFee)
                        .addGap(18, 18, 18)
                        .addComponent(BClear)))
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
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
                            .addComponent(LClass)
                            .addComponent(LCTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(LCategory))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(LGroup)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(LLateFee)
                            .addComponent(BClear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(LForDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(LMonth))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(LYear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(LTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(LUser))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(LSchoolFee))
                .addGap(30, 30, 30)
                .addComponent(BGenerateReceipt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SCHOOL FEE PAYMENT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(780, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        BBack.setBackground(new java.awt.Color(255, 255, 255));
        BBack.setText("Back");
        BBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBackActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)), "Unpaid", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Today is");

        LDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LDate.setText("Date");

        LTime.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LTime.setText("Time");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Starting Date");

        LDayNameSD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LDayNameSD.setText("DayName");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Last Payment");

        LForMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LForMonth.setText("ForMonth");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Next Payment Before");

        LDayName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LDayName.setText("DayName");

        LNPDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LNPDate.setText("Date");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Unpaid");

        LUnpaid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LUnpaid.setText("Month(s)");

        LSDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LSDate.setText("Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LDayNameSD, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(LSDate)
                                .addContainerGap(100, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LDayName)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(LNPDate))
                                    .addComponent(LUnpaid)
                                    .addComponent(LForMonth)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(LDate)
                                        .addGap(23, 23, 23)
                                        .addComponent(LTime)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LDate)
                            .addComponent(LTime))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LDayNameSD)
                        .addComponent(LSDate))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(LForMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(LDayName)
                    .addComponent(LNPDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(LUnpaid))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)), "This Month", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(51, 102, 255))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Status");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Late Fee on");

        LLate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LLate.setText("Late or Not");

        LDayNameTM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LDayNameTM.setText("DayName");

        LNPDateTM.setText("Date");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel21))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(LDayNameTM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LNPDateTM))
                    .addComponent(LLate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(LDayNameTM)
                    .addComponent(LNPDateTM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(LLate))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)));

        PReceipt.setBackground(new java.awt.Color(255, 255, 255));
        PReceipt.setBorder(null);
        PReceipt.setMinimumSize(new java.awt.Dimension(730, 280));
        PReceipt.setPreferredSize(new java.awt.Dimension(734, 302));
        PReceipt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResStudentName.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResStudentName.setText("Student Name");
        PReceipt.add(ResStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        ResClassName.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResClassName.setText("Class Name");
        PReceipt.add(ResClassName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        ResTotal.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResTotal.setText("Total");
        PReceipt.add(ResTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, -1, -1));

        ResPaymentID.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResPaymentID.setText("Payment ID");
        PReceipt.add(ResPaymentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        ResDate.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResDate.setText("Date");
        PReceipt.add(ResDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        ResUser.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResUser.setText("User");
        PReceipt.add(ResUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        ResGroup.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResGroup.setText("Group");
        PReceipt.add(ResGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel43.setText("Student Name");
        PReceipt.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel46.setText("Class Name");
        PReceipt.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel48.setText("Total");
        PReceipt.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel49.setText("Payment ID");
        PReceipt.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel50.setText("Date");
        PReceipt.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel51.setText("User");
        PReceipt.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel52.setText("Group");
        PReceipt.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel53.setText("Payment ID");
        PReceipt.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel54.setText("Student Name");
        PReceipt.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel55.setText("For Month/Year");
        PReceipt.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel56.setText("Amount");
        PReceipt.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel57.setText("Group");
        PReceipt.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, -1, -1));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel60.setText("Class ID");
        PReceipt.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel61.setText("Late Fee");
        PReceipt.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel62.setText("Total");
        PReceipt.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));

        LRUser.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRUser.setText("User");
        PReceipt.add(LRUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 210, -1, -1));

        LRPaymentID.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRPaymentID.setText("Payment ID");
        PReceipt.add(LRPaymentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        LRStudentName.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRStudentName.setText("Student Name");
        PReceipt.add(LRStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        LRClassID.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRClassID.setText("Class ID");
        PReceipt.add(LRClassID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        LRForMonthYear.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRForMonthYear.setText("For Month/Year");
        PReceipt.add(LRForMonthYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        LRGroup.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRGroup.setText("Group");
        PReceipt.add(LRGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, -1));

        LRLateFee.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRLateFee.setText("Late Fee");
        PReceipt.add(LRLateFee, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        LRAmount.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRAmount.setText("Amount");
        PReceipt.add(LRAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        LRTotal.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRTotal.setText("Total");
        PReceipt.add(LRTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel58.setText("Student ID");
        PReceipt.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        ResStudentID.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        ResStudentID.setText("Student ID");
        PReceipt.add(ResStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        LRDate.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRDate.setText("Date");
        PReceipt.add(LRDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, -1, -1));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        jLabel63.setText("e-mail :ddtc_edu@yahoo.com. ");
        PReceipt.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, 10));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 6)); // NOI18N
        jLabel29.setText("Jl.BakarBatu no. 8 – 9 C.Telp : +62 771 23047.Tanjungpinang ( Kepri ) Indonesia 29112");
        PReceipt.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, -1, 10));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel59.setText("Student ID");
        PReceipt.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, -1, -1));

        LRStudentID.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        LRStudentID.setText("Student ID");
        PReceipt.add(LRStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        Background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture37/DD Watermark4.png"))); // NOI18N
        Background1.setBorder(null);
        Background1.setPreferredSize(new java.awt.Dimension(734, 303));
        PReceipt.add(Background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 570, 240));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(51, 102, 255)));

        BPay.setBackground(new java.awt.Color(255, 255, 255));
        BPay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BPay.setText("Pay");
        BPay.setOpaque(true);
        BPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPayActionPerformed(evt);
            }
        });

        BPrint.setBackground(new java.awt.Color(255, 255, 255));
        BPrint.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BPrint.setText("Print Receipt");
        BPrint.setOpaque(true);
        BPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BPay, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BPay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(51, 102, 255));
        jPanel11.setForeground(new java.awt.Color(255, 255, 255));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("SCHOOL FEE RECEIPT");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel31)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BBack)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(BBack))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(MenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1063, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(MenuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPayActionPerformed
        
            try{
            String sql = "Insert into tschoolfee ( Id, StudentId, Name, Date, Groups,ForMonth, ForYear, ForClass, Amount, Description, UserLog, LateFee, Total, Category, forDate, Card) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.setString(8, LClass.getText());
            pst.setString(9, LSchoolFee.getText());
            pst.setString(10, "school fee");
            pst.setString(11, LUser.getText());
            pst.setString(12, LLateFee.getText()); //use getText() to get the text from the TextField and pass it to the table
            pst.setString(13, LTotal.getText());
            pst.setString(14, LCategory.getText());
                System.out.println("After this");
            pst.setString(15, LForDate.getText());
                System.out.println("Does it work?");
            pst.setString(16, "0");    
            pst.execute(); // execute the pst
            JOptionPane.showMessageDialog(null, "Saved");
            BPrint.setEnabled(true);
            BPay.setEnabled(false);
            
            //ShowData();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println("e "+ e);
        }
    }//GEN-LAST:event_BPayActionPerformed

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
            String Class= (String) LClass.getText();
            String PaymentID= LID.getText();
            String StudentID= LStudentID.getText();
            String StudentName= LName.getText();
            String ClassName= LClass.getText();
            String Group = LGroup.getText();
            String Total = LTotal.getText();
            String Amount = LSchoolFee.getText();
            String Date   = LDate.getText();
            String LateFee= LLateFee.getText();
            String User   = LUser.getText();
            String ForMonth= LMonth.getText();
            String ForYear = LYear.getText();
            
            if(Class.isEmpty()){
                JOptionPane.showMessageDialog(null, "Class must not be empty");
            } else {
                // TODO add your handling code here:
                //student's
                LRPaymentID.setText(PaymentID);
                LRStudentID.setText(StudentID);
                LRStudentName.setText(StudentName);
                LRClassID.setText(ClassName);
                LRGroup.setText(Group);
                LRTotal.setText(Total);
                LRAmount.setText(Amount);
                LRDate.setText(Date);
                LRLateFee.setText(LateFee);
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
               
                BPay.setEnabled(true);
            }
        }catch(Exception e){
            System.err.format("cannot print %s%n", e.getMessage());
        }
    
    }//GEN-LAST:event_BGenerateReceiptActionPerformed

    private void BBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBackActionPerformed
        String Username=LUser.getText();
        FormSiswa lf = new FormSiswa();
        lf.setVisible(true);
        this.setVisible(true);
        lf.username(Username);
        hide();
    }//GEN-LAST:event_BBackActionPerformed

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

    private void BClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearActionPerformed
        LLateFee.setText("0");
    }//GEN-LAST:event_BClearActionPerformed
    
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
    LDate.setText(year+"-"+(month+1)+"-"+day);
    LTime.setText(hour+":"+minute+":"+second);
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
            java.util.logging.Logger.getLogger(FormReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormReceipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormReceipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBack;
    private javax.swing.JButton BClear;
    private javax.swing.JButton BGenerateReceipt;
    private javax.swing.JButton BPay;
    private javax.swing.JButton BPrint;
    private javax.swing.JLabel Background1;
    private javax.swing.JLabel LCTime;
    private javax.swing.JLabel LCategory;
    private javax.swing.JLabel LClass;
    private javax.swing.JLabel LCode;
    private javax.swing.JLabel LCodeP;
    private javax.swing.JLabel LDate;
    private javax.swing.JLabel LDate1;
    private javax.swing.JLabel LDayName;
    private javax.swing.JLabel LDayNameSD;
    private javax.swing.JLabel LDayNameTM;
    private javax.swing.JLabel LForDate;
    private javax.swing.JLabel LForMonth;
    private javax.swing.JLabel LGroup;
    private javax.swing.JLabel LID;
    private javax.swing.JLabel LID1;
    private javax.swing.JLabel LLate;
    private javax.swing.JLabel LLateFee;
    private javax.swing.JLabel LMonth;
    private javax.swing.JLabel LNPDate;
    private javax.swing.JLabel LNPDateTM;
    private javax.swing.JLabel LName;
    private javax.swing.JLabel LRAmount;
    private javax.swing.JLabel LRClassID;
    private javax.swing.JLabel LRDate;
    private javax.swing.JLabel LRForMonthYear;
    private javax.swing.JLabel LRGroup;
    private javax.swing.JLabel LRLateFee;
    private javax.swing.JLabel LRPaymentID;
    private javax.swing.JLabel LRStudentID;
    private javax.swing.JLabel LRStudentName;
    private javax.swing.JLabel LRTotal;
    private javax.swing.JLabel LRUser;
    private javax.swing.JLabel LSDate;
    private javax.swing.JLabel LSchoolFee;
    private javax.swing.JLabel LStudentID;
    private javax.swing.JLabel LTime;
    private javax.swing.JLabel LTime1;
    private javax.swing.JLabel LTotal;
    private javax.swing.JLabel LUnpaid;
    private javax.swing.JLabel LUser;
    private javax.swing.JLabel LYear;
    private javax.swing.JLabel Luser;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JPanel PCPass;
    private javax.swing.JPanel PClasses;
    private javax.swing.JPanel PLogOut;
    private javax.swing.JPanel PReceipt;
    private javax.swing.JPanel PRegister;
    private javax.swing.JPanel PReport;
    private javax.swing.JPanel PTeacher;
    private javax.swing.JLabel ResClassName;
    private javax.swing.JLabel ResDate;
    private javax.swing.JLabel ResGroup;
    private javax.swing.JLabel ResPaymentID;
    private javax.swing.JLabel ResStudentID;
    private javax.swing.JLabel ResStudentName;
    private javax.swing.JLabel ResTotal;
    private javax.swing.JLabel ResUser;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
