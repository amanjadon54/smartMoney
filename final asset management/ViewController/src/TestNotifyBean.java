import com.sun.mail.smtp.SMTPTransport;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.jbo.VariableValueManager;
import oracle.jbo.ViewCriteria;

import oracle.jbo.ViewObject;

import oracle.project.AssetManagement.uiview.AssetManagementAM1Impl;
import oracle.project.AssetManagement.uiview.MonthlyAnalticsBookOVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsNewVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyVOImpl;
import oracle.project.AssetManagement.uiview.UserUpdatableVOImpl;
import oracle.project.AssetManagement.uiview.ViewObjVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsBookLVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsBookOVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsDebtVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsLentVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyChartVVOImpl;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class TestNotifyBean {
  
    private RichInputText user;
    private RichInputText userId;
    private RichInputDate date_1;
    private RichInputDate date_2;
    public static String userId_new="test";
    public static Date currDate;
    private RichSelectOneChoice selectedMonth;
  FacesContext context_new = FacesContext.getCurrentInstance();
  BindingContext bindingContext_new = BindingContext.getCurrent();
  DCDataControl dc_new =
      bindingContext_new.findDataControl("AssetManagementAM1DataControl"); // Name of application module in datacontrolBinding.cpx
  AssetManagementAM1Impl am5_new =
      (AssetManagementAM1Impl)dc_new.getDataProvider();
    private RichInputText label1;

    public TestNotifyBean() {
    }

    public void setUser(RichInputText user) {
        this.user = user;
    }

    public RichInputText getUser() {
        return user;
    }

    public void populate(ActionEvent actionEvent) throws Exception {
        // Add event code here...
        
       
        MonthlyVOImpl monthlyVo =
            (MonthlyVOImpl)am5_new.findViewObject("Monthly1");
        
        System.out.println("this ran");
        //ViewCriteria vc = UseVO.getViewCriteria("UserUpdatableVOCriteria");
        
        String user_new = getUser().getValue().toString();
      System.out.println("this also ran"+user_new);
      ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyVOCriteria");
      monthlyVo.setNamedWhereClauseParam("m_user", user_new);
      
//      final Calendar calendar = new Calendar();
//      calendar.setTime(date);
//      
      
      //SimpleDate simpleDate = new SimpleDate();
    
              Date dateFirst = getFirstDay(new Date());
              Date dateLast = getLastDay(new Date());
              System.out.println("First Date: " + dateFirst);
              System.out.println("Last Date: " + dateLast);
              
//              
      Calendar calendar = new GregorianCalendar();
             // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss.ff");
//              Date dateFirst = sdf.parse(firstDate);
//              Date dateLast=sdf.parse(lastDate);
      
      
      oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
      oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);      
      
      System.out.println("date now is");
      System.out.println(timestampObj1);
      System.out.println(timestampObj2);
           monthlyVo.setNamedWhereClauseParam("m_date1",timestampObj1);
           monthlyVo.setNamedWhereClauseParam("m_date2",timestampObj2);
      monthlyVo.applyViewCriteria(vc_new, true);
      monthlyVo.getQuery();
      // System.out.println(UseVO.getWhereClauseParams());
      System.out.println("hurrrrrrrrrrrrrrrrrrrra");
      monthlyVo.executeQuery();
      System.out.println("executing yuyuyu");
      
      
     
      
      
     MonthlyAnalyticsVVOImpl monthlyAnalytics =
          (MonthlyAnalyticsVVOImpl)am5_new.getMonthlyAnalyticsV1();
      
      System.out.println("this ran");
      //ViewCriteria vc = UseVO.getViewCriteria("UseUpdatableVOCriteria");
    
      
      System.out.println(user_new);
      monthlyAnalytics.setNamedWhereClauseParam("p_user", user_new);
      vc_new = monthlyAnalytics.getViewCriteria("MonthlyAnalyticsVVOCriteria");
      monthlyAnalytics.applyViewCriteria(vc_new);
      
      System.out.println(monthlyAnalytics.getQuery());
      monthlyAnalytics.executeQuery();
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      //Date date=new Date();
     // Date date=new Date();
     // DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss.ff");
    //
      /* imp date thing used earlier
    java.util.Date dateObj=new Date();
      //Date today=new Date();
      long ltime=dateObj.getTime()+8*24*60*60*1000;
      Date today8=new Date(ltime);
    
    oracle.jbo.domain.Timestamp timestampObj = new oracle.jbo.domain.Timestamp(today8);
      //String date="2017-04-19";
      System.out.println(timestampObj);
           vo_new.setNamedWhereClauseParam("p_date",timestampObj);
      vo_new.applyViewCriteria(vc, true);
      vo_new.getQuery();
     // System.out.println(UseVO.getWhereClauseParams());
      System.out.println("hurrrrrrrrrrrrrrrrrrrra");
      vo_new.executeQuery();
      System.out.println("executing yuyuyu");
        //return true;
  */
        
      YearlyAnalyticsBookOVVOImpl yearBookOwe =
           (YearlyAnalyticsBookOVVOImpl)am5_new.getYearlyAnalyticsBookOV1();
       System.out.println(user_new);
       yearBookOwe.setNamedWhereClauseParam("p_user", user_new);
       vc_new = yearBookOwe.getViewCriteria("YearlyAnalyticsBookOVVOCriteria");
       yearBookOwe.applyViewCriteria(vc_new);
       //System.out.println(bookOwe.getQuery());
       yearBookOwe.executeQuery();
       
       
       
      YearlyAnalyticsBookLVVOImpl yearBookLent =
           (YearlyAnalyticsBookLVVOImpl)am5_new.getYearlyAnalyticsBookLV1();
       System.out.println(user_new);
       yearBookLent.setNamedWhereClauseParam("p_user", user_new);
       vc_new = yearBookLent.getViewCriteria("YearlyAnalyticsBookLVVOCriteria");
       yearBookLent.applyViewCriteria(vc_new);
       //System.out.println(bookOwe.getQuery());
       yearBookLent.executeQuery();
       
       
       
      YearlyAnalyticsDebtVVOImpl yearDebt =
           (YearlyAnalyticsDebtVVOImpl)am5_new.getYearlyAnalyticsDebtV1();
       System.out.println(user_new);
       yearDebt.setNamedWhereClauseParam("p_user", user_new);
       vc_new = yearDebt.getViewCriteria("YearlyAnalyticsDebtVVOCriteria");
       yearDebt.applyViewCriteria(vc_new);
       //System.out.println(bookOwe.getQuery());
       yearDebt.executeQuery();
       
       
       
      YearlyAnalyticsLentVVOImpl yearLent =
           (YearlyAnalyticsLentVVOImpl)am5_new.getYearlyAnalyticsLentV1();
       System.out.println(user_new);
       yearLent.setNamedWhereClauseParam("p_user", user_new);
       vc_new = yearLent.getViewCriteria("YearlyAnalyticsLentVVOCriteria");
       yearLent.applyViewCriteria(vc_new);
       //System.out.println(bookOwe.getQuery());
       yearLent.executeQuery();
       
       
       
      YearlyAnalyticsVVOImpl yearly =
                 (YearlyAnalyticsVVOImpl)am5_new.getYearlyAnalyticsV1();
             System.out.println(user_new);
             yearly.setNamedWhereClauseParam("m_user", user_new);
             vc_new = yearly.getViewCriteria("YearlyAnalyticsVVOCriteria");
             yearly.applyViewCriteria(vc_new);
             //System.out.println(bookOwe.getQuery());
             yearly.executeQuery();
       
       
       
       
       
       
       
       
        
        
        
        
        
    }
    
    
    
  public Date getFirstDay(Date d) throws Exception
      {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(d);
          calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
          Date dddd = calendar.getTime();
          //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss.ff");
          return dddd;
      }
   
      public Date getLastDay(Date d) throws Exception
      {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(d);
          calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
          Date dddd = calendar.getTime();
          //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd hh24:mi:ss.ff");
          return dddd;
      }


    public void UserId(FacesContext facesContext, UIComponent uIComponent,
                       Object object) {
        // Add event code here...
    }

    public void setUserId(RichInputText userId) {
        this.userId = userId;
    }

    public RichInputText getUserId() {
        return userId;
    }

    public void setDate_1(RichInputDate date_1) {
        this.date_1 = date_1;
    }

    public RichInputDate getDate_1() {
        return date_1;
    }

    public void setDate_2(RichInputDate date_2) {
        this.date_2 = date_2;
    }

    public RichInputDate getDate_2() {
        return date_2;
    }
    
    
    
    

    public void selection2(ActionEvent actionEvent) throws Exception {
        
        System.out.println(userId_new);
      System.out.println(currDate);
      //System.out.println(getDate_1());
        MonthlyAnalyticsNewVVOImpl monthlyVo =(MonthlyAnalyticsNewVVOImpl)am5_new.findViewObject("MonthlyAnalyticsNewV1");
        System.out.println("this also ran"+userId_new);
      //monthlyVo.setNamedWhereClauseParam("p_day",4);
        
        
        ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyAnalyticsNewVVOCriteria");
        monthlyVo.setNamedWhereClauseParam("m_user",userId_new);
     
              Date dateFirst = getFirstDay(new Date());
              Date dateLast = getLastDay(new Date());
              System.out.println(dateFirst);
              System.out.println(dateLast);
        Calendar calendar = new GregorianCalendar();
        oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
        oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);
           monthlyVo.setNamedWhereClauseParam("m_date1",timestampObj1);
           monthlyVo.setNamedWhereClauseParam("m_date2",timestampObj2);
        
        monthlyVo.applyViewCriteria(vc_new, true);
        monthlyVo.getQuery();
        // System.out.println(UseVO.getWhereClauseParams());
        System.out.println("hurrrrrrrrrrrrrrrrrrrra");
        monthlyVo.executeQuery();
        System.out.println("executing yuyuyu");
    }

    public void take(ActionEvent actionEvent) {
        // Add event code here...
        userId_new  = getUserId().getValue().toString();
//        currDate=
        
        System.out.println(userId_new);
        System.out.println(getDate_1().getValue());
    }

    public void setSelectedMonth(RichSelectOneChoice selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public RichSelectOneChoice getSelectedMonth() {
        return selectedMonth;
    }

    public void selectOneChoice(ActionEvent actionEvent) throws Exception {
        // Add event code here...
        
        System.out.println("Seleced ran");
        
        //System.out.println(label1.getValue().toString());
        
        System.out.println(selectedMonth.getValue().toString());
        //month++;
        int month = Integer.parseInt(selectedMonth.getValue().toString());
        month++;
        System.out.println(userId_new);
        //System.out.println(currDate);
        //System.out.println(getDate_1());
        MonthlyAnalyticsNewVVOImpl monthlyVo =(MonthlyAnalyticsNewVVOImpl)am5_new.findViewObject("MonthlyAnalyticsNewV1");
        System.out.println("this also ran"+userId_new);
        //monthlyVo.setNamedWhereClauseParam("p_day",4);
        
        String sDate1= "01/"+month+"/2017";
        
        
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
       
        ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyAnalyticsNewVVOCriteria");
        monthlyVo.setNamedWhereClauseParam("m_user",userId_new);
        
              Date dateFirst = getFirstDay(date1);
              Date dateLast = getLastDay(date1);
              System.out.println(dateFirst);
              System.out.println(dateLast);
        Calendar calendar = new GregorianCalendar();
        
        
        oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
        oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);
                   monthlyVo.setNamedWhereClauseParam("m_date1",timestampObj1);
                   monthlyVo.setNamedWhereClauseParam("m_date2",timestampObj2);
        
        monthlyVo.applyViewCriteria(vc_new, true);
        monthlyVo.getQuery();
        // System.out.println(UseVO.getWhereClauseParams());
        System.out.println("hurrrrrrrrrrrrrrrrrrrra");
        monthlyVo.executeQuery();
        System.out.println("executing yuyuyu");
        
        
        
        
        
      YearlyChartVVOImpl yearlyVo =(YearlyChartVVOImpl)am5_new.findViewObject("YearlyChartV1");
      System.out.println("this also ran"+userId_new);
      //monthlyVo.setNamedWhereClauseParam("p_day",4);
      
      //String sDate1= "01/"+month+"/2017";
      
      
      //Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
      
      //ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyAnalyticsNewVVOCriteria");
      yearlyVo.setNamedWhereClauseParam("p_user",userId_new);
      yearlyVo.getQuery();
      // System.out.println(UseVO.getWhereClauseParams());
      System.out.println("hurrrrrrrrrrrrrrrrrrrra");
      yearlyVo.executeQuery();
        
        
        
    }

    public void setLabel1(RichInputText label1) {
        this.label1 = label1;
    }

    public RichInputText getLabel1() {
        return label1;
    }

    public void sendEmail(ActionEvent actionEvent) throws Exception {
        // Add event code here...
        
        
     

    }
}
