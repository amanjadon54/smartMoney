import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;


import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.*;
import oracle.jbo.domain.Number;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;

import oracle.adf.model.binding.DCDataControl;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaManager;
import oracle.jbo.server.ViewObjectImpl;

import oracle.project.AssetManagement.uiview.AssetManagementAM1Impl;
import oracle.project.AssetManagement.uiview.MonthlyAnalticsBookOVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsBookLVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsDebtVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsNewVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyAnalyticsVVOImpl;
import oracle.project.AssetManagement.uiview.MonthlyVOImpl;
import oracle.project.AssetManagement.uiview.UserUpdatableVOImpl;
import oracle.project.AssetManagement.uiview.UserUpdatableVORowImpl;
import oracle.project.AssetManagement.uiview.ViewObjVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsBookLVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsBookOVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsDebtVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsLentVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyAnalyticsVVOImpl;
import oracle.project.AssetManagement.uiview.YearlyChartVVOImpl;

public class LoginPageClass {
    private RichInputText userId;
    private RichInputText password;
    private RichOutputLabel passOutputLabel;
    private HtmlCommandLink forgotPasswordLink;
    public int controlFlag = 0;
    public Object question;
    public Object answer;
    public String user;
    private RichInputText securityQuestion;
    private RichInputText securityAnswer;
    private RichInputText newPassword;
    private RichInputText confirmPassword;
    private RichOutputText answerOutputText;
    private RichOutputText confirmPasswordOutputText;
    public int forgot_flag = 0;
    private RichInputText userIdNew;
    private RichSelectOneChoice selectedMonth;
    private boolean checkDashboardProfileValueChange=false;


    public LoginPageClass() {
    }

    public void setUserId(RichInputText userId) {
        this.userId = userId;
    }

    public RichInputText getUserId() {
        return userId;
    }

    public void setPassword(RichInputText password) {
        this.password = password;
    }

    public RichInputText getPassword() {
        return password;
    }

    public void setPassOutputLabel(RichOutputLabel passOutputLabel) {
        this.passOutputLabel = passOutputLabel;
    }

    public RichOutputLabel getPassOutputLabel() {
        return passOutputLabel;
    }

    public void setForgotPasswordLink(HtmlCommandLink forgotPasswordLink) {
        this.forgotPasswordLink = forgotPasswordLink;
    }

    public HtmlCommandLink getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    public void setSecurityQuestion(RichInputText securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public RichInputText getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityAnswer(RichInputText securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public RichInputText getSecurityAnswer() {
        return securityAnswer;
    }

    public void setNewPassword(RichInputText newPassword) {
        this.newPassword = newPassword;
    }

    public RichInputText getNewPassword() {
        return newPassword;
    }

    public void setConfirmPassword(RichInputText confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public RichInputText getConfirmPassword() {
        return confirmPassword;
    }

    public void validateForgotPasswordData() {
        // Add event code here..
    }

    public void setAnswerOutputText(RichOutputText answerOutputText) {
        this.answerOutputText = answerOutputText;
    }

    public RichOutputText getAnswerOutputText() {
        return answerOutputText;
    }

    public void setConfirmPasswordOutputText(RichOutputText confirmPasswordOutputText) {
        this.confirmPasswordOutputText = confirmPasswordOutputText;
    }

    public RichOutputText getConfirmPasswordOutputText() {
        return confirmPasswordOutputText;
    }

    public String toResetPassword() {
        if (forgot_flag == 1) {
            return "resetPassword";
        } else {
            return "";
        }
    }

    public void setUserIdNew(RichInputText userIdNew) {
        this.userIdNew = userIdNew;
    }

    public RichInputText getUserIdNew() {
        return userIdNew;
    }


    public boolean validate(ActionEvent actionEvent) throws Exception {
        Row userRow = null;
        String amDef = "oracle.project.AssetManagement.uiview.AssetManagementAM1";
        String config = "AssetManagementAM1Local";
        ApplicationModule am =Configuration.createRootApplicationModule(amDef, config);
        ViewObject vo = am.findViewObject("UserObject1");
        vo.executeQuery();
        
        //Doing the userid and password validation
        user = userId.getValue().toString();
        String pass = password.getValue().toString();
        int flag = 0;
        while (vo.hasNext()) {
            userRow = vo.next();
            if (user.equals(userRow.getAttribute("Userid"))) {
                flag = 1;
                question = userRow.getAttribute("Question");
                answer = userRow.getAttribute("Answer");
                if (pass.equals(userRow.getAttribute("Password"))) {
                    flag = 2;
                }
                break;
            }
        }
        Configuration.releaseRootApplicationModule(am, true);
        if (flag == 0) {
            passOutputLabel.setValue(user + "is not a registered user");
            passOutputLabel.setVisible(true);
        } else if (flag == 1) {
            passOutputLabel.setValue("wrong userid/password combination");
            passOutputLabel.setVisible(true);
            forgotPasswordLink.setDisabled(false);
            controlToDashboard();
        } else {
            String user = userId.getValue().toString();
            controlFlag = 1;
            
            // applying the view criteria to updatable 2 on the click of login
            
            FacesContext context = FacesContext.getCurrentInstance();
            BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc =
                bindingContext.findDataControl("AssetManagementAM1DataControl"); // Name of application module in datacontrolBinding.cpx
            AssetManagementAM1Impl am3 =
                (AssetManagementAM1Impl)dc.getDataProvider();
            UserUpdatableVOImpl UseVO =
                (UserUpdatableVOImpl)am3.findViewObject("UserUpdatable2");
            ViewCriteria vc = UseVO.getViewCriteria("UserUpdatableVOCriteria");
            UseVO.setNamedWhereClauseParam("p_Id", user);
            UseVO.applyViewCriteria(vc, true);
            UseVO.getQuery();
            UseVO.executeQuery();
            
            //done applying the criteria
            
            
            // for the notification display , below code
            
            ViewObjVOImpl vo_new =(ViewObjVOImpl)am3.findViewObject("ViewObj1");
            String user_new = getUserId().getValue().toString();
            ViewCriteria vc1 = vo_new.getViewCriteria("ViewObjVOCriteria");
            vo_new.setNamedWhereClauseParam("p_user", user_new);
            java.util.Date dateObj=new java.util.Date();
            long ltime=dateObj.getTime()+8*24*60*60*1000;
            java.util.Date today8=new java.util.Date(ltime);
          oracle.jbo.domain.Timestamp timestampObj = new oracle.jbo.domain.Timestamp(today8);
            vo_new.setNamedWhereClauseParam("p_date",timestampObj);
            vo_new.applyViewCriteria(vc1, true);
            vo_new.getQuery();
            vo_new.executeQuery();
            
            // notification crieteria ends here
            
           
            //monthly vo data sytarts here
          
          MonthlyVOImpl monthlyVo =(MonthlyVOImpl)am3.findViewObject("Monthly1");
          ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyVOCriteria");
          monthlyVo.setNamedWhereClauseParam("m_user", user);
//          java.util.Date dateFirst = getFirstDay(new java.util.Date());
//          java.util.Date dateLast = getLastDay(new java.util.Date());
//          Calendar calendar = new GregorianCalendar();
//          oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
//          oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);
//          monthlyVo.setNamedWhereClauseParam("m_date1",timestampObj1);
//          monthlyVo.setNamedWhereClauseParam("m_date2",timestampObj2);
          monthlyVo.applyViewCriteria(vc_new, true);
          monthlyVo.getQuery();
          monthlyVo.executeQuery();
  
          // monthly Vo ends here
            
          // monthly sum
            
          MonthlyAnalyticsVVOImpl monthlyAnalytics =
               (MonthlyAnalyticsVVOImpl)am3.getMonthlyAnalyticsV1();
           System.out.println("this ran");
           System.out.println(user_new);
           monthlyAnalytics.setNamedWhereClauseParam("p_user", user_new);
           vc_new = monthlyAnalytics.getViewCriteria("MonthlyAnalyticsVVOCriteria");
           monthlyAnalytics.applyViewCriteria(vc_new);
           System.out.println(monthlyAnalytics.getQuery());
           monthlyAnalytics.executeQuery();
            
            //ends finding the sum in here.
            
            
            // monthly debt acc to sysdate
            
            MonthlyAnalyticsDebtVVOImpl monDebt =
                 (MonthlyAnalyticsDebtVVOImpl)am3.getMonthlyAnalyticsDebtV1();
             System.out.println(user_new);
             monDebt.setNamedWhereClauseParam("p_user", user_new);
             vc_new = monDebt.getViewCriteria("MonthlyAnalyticsDebtVVOCriteria");
             monDebt.applyViewCriteria(vc_new);
             System.out.println(monDebt.getQuery());
             monDebt.executeQuery();
            
            //for monthly books
            
            MonthlyAnalyticsBookLVVOImpl bookDebt =
                 (MonthlyAnalyticsBookLVVOImpl)am3.getMonthlyAnalyticsBookLV1();
             System.out.println(user_new);
             bookDebt.setNamedWhereClauseParam("p_user", user_new);
             vc_new = bookDebt.getViewCriteria("MonthlyAnalyticsBookLVVOCriteria");
             bookDebt.applyViewCriteria(vc_new);
             System.out.println(bookDebt.getQuery());
             bookDebt.executeQuery();
            
            //end of monthly lent books
            
            //start of monthly owe books
            
          MonthlyAnalticsBookOVVOImpl bookOwe =
               (MonthlyAnalticsBookOVVOImpl)am3.getMonthlyAnalticsBookOV1();
           System.out.println(user_new);
           bookOwe.setNamedWhereClauseParam("p_user", user_new);
           vc_new = bookOwe.getViewCriteria("MonthlyAnalticsBookOVVOCriteria");
           bookOwe.applyViewCriteria(vc_new);
           System.out.println(bookOwe.getQuery());
           bookOwe.executeQuery();
            
            //end of monthly owe books
            
            
            
            YearlyAnalyticsBookOVVOImpl yearBookOwe =
                 (YearlyAnalyticsBookOVVOImpl)am3.getYearlyAnalyticsBookOV1();
             System.out.println(user_new);
             yearBookOwe.setNamedWhereClauseParam("p_user", user_new);
             vc_new = yearBookOwe.getViewCriteria("YearlyAnalyticsBookOVVOCriteria");
             yearBookOwe.applyViewCriteria(vc_new);
             //System.out.println(bookOwe.getQuery());
             yearBookOwe.executeQuery();
             
             
             
            YearlyAnalyticsBookLVVOImpl yearBookLent =
                 (YearlyAnalyticsBookLVVOImpl)am3.getYearlyAnalyticsBookLV1();
             System.out.println(user_new);
             yearBookLent.setNamedWhereClauseParam("p_user", user_new);
             vc_new = yearBookLent.getViewCriteria("YearlyAnalyticsBookLVVOCriteria");
             yearBookLent.applyViewCriteria(vc_new);
             //System.out.println(bookOwe.getQuery());
             yearBookLent.executeQuery();
             
             
             
            YearlyAnalyticsDebtVVOImpl yearDebt =
                 (YearlyAnalyticsDebtVVOImpl)am3.getYearlyAnalyticsDebtV1();
             System.out.println(user_new);
             yearDebt.setNamedWhereClauseParam("p_user", user_new);
             vc_new = yearDebt.getViewCriteria("YearlyAnalyticsDebtVVOCriteria");
             yearDebt.applyViewCriteria(vc_new);
             //System.out.println(bookOwe.getQuery());
             yearDebt.executeQuery();
             
             
             
            YearlyAnalyticsLentVVOImpl yearLent =
                 (YearlyAnalyticsLentVVOImpl)am3.getYearlyAnalyticsLentV1();
             System.out.println(user_new);
             yearLent.setNamedWhereClauseParam("p_user", user_new);
             vc_new = yearLent.getViewCriteria("YearlyAnalyticsLentVVOCriteria");
             yearLent.applyViewCriteria(vc_new);
             //System.out.println(bookOwe.getQuery());
             yearLent.executeQuery();
             
             
             
            YearlyAnalyticsVVOImpl yearly =
                       (YearlyAnalyticsVVOImpl)am3.getYearlyAnalyticsV1();
                   System.out.println(user_new);
                   yearly.setNamedWhereClauseParam("m_user", user_new);
                   vc_new = yearly.getViewCriteria("YearlyAnalyticsVVOCriteria");
                   yearly.applyViewCriteria(vc_new);
                   //System.out.println(bookOwe.getQuery());
                   yearly.executeQuery();
            
            //monthly select one choice default
            
            int month=4;
                MonthlyAnalyticsNewVVOImpl monthlyVoNew =(MonthlyAnalyticsNewVVOImpl)am3.findViewObject("MonthlyAnalyticsNewV1");
                String sDate1= "01/"+month+"/2017";
                  java.util.Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
                  vc_new = monthlyVoNew.getViewCriteria("MonthlyAnalyticsNewVVOCriteria");
                  monthlyVoNew.setNamedWhereClauseParam("m_user",user_new);
                  java.util.Date dateFirst = getFirstDay(date1);
                  java.util.Date dateLast = getLastDay(date1);
                  Calendar calendar = new GregorianCalendar();
                  oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
                  oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);
                  monthlyVoNew.setNamedWhereClauseParam("m_date1",timestampObj1);
                  monthlyVoNew.setNamedWhereClauseParam("m_date2",timestampObj2);
                  monthlyVoNew.applyViewCriteria(vc_new, true);
                  monthlyVoNew.getQuery();
                  monthlyVoNew.executeQuery();
                  System.out.println("executingdsandband yuyuyu");
            
            //end of monthly
            
            //chart
            YearlyChartVVOImpl yearlyVo =(YearlyChartVVOImpl)am3.findViewObject("YearlyChartV1");
                  System.out.println("this also ran"+user_new);
                  yearlyVo.setNamedWhereClauseParam("p_user",user_new);
                  yearlyVo.getQuery();
                  System.out.println("yes");
                  yearlyVo.executeQuery();
            
            //end of chart
            
            
            
            
            return true;
        }
        return false;
    }

    public Object controlFlow() {
        // Add event code here...
        if (controlFlag == 1) {
            return "successful_login";
        }
        return " ";
    }

    public void controlToDashboard() {
        securityQuestion.setValue(question);
    }


    public void validateForgotPassword(ActionEvent actionEvent) {
        // Add event code here..
        String amDef =
            "oracle.project.AssetManagement.uiview.AssetManagementAM1";
        String config = "AssetManagementAM1Local";
        System.out.println("forgot password called");
        String user =
            (String)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userName");
        System.out.println(user);
        ApplicationModule am =
            Configuration.createRootApplicationModule(amDef, config);
        ViewObject vo = am.findViewObject("UserObject1");
        vo.executeQuery();
        while (vo.hasNext()) {
            Row userRow = vo.next();
            if (user.equals(userRow.getAttribute("Userid"))) {
                answer = userRow.getAttribute("Answer");
            }
        }
        Configuration.releaseRootApplicationModule(am, true);

        if (!securityAnswer.getValue().toString().equals(answer)) {
            answerOutputText.setValue("wrong answer please try again!");
            answerOutputText.setVisible(true);
        } else {
            securityAnswer.setDisabled(true);
            answerOutputText.setVisible(false);
            if (!newPassword.getValue().toString().equals(confirmPassword.getValue().toString())) {
                confirmPasswordOutputText.setValue("password don't match, please retry");
                confirmPasswordOutputText.setVisible(true);
            } else {
                confirmPasswordOutputText.setVisible(false);
                forgot_flag = 1;
                System.out.println(user);
                System.out.println(answer);
                FacesContext context = FacesContext.getCurrentInstance();
                BindingContext bindingContext = BindingContext.getCurrent();
                DCDataControl dc =
                    bindingContext.findDataControl("AssetManagementAM1DataControl"); // Name of application module in datacontrolBinding.cpx
                AssetManagementAM1Impl am4 =
                    (AssetManagementAM1Impl)dc.getDataProvider();
                UserUpdatableVOImpl userVO =
                    (UserUpdatableVOImpl)am4.findViewObject("UserUpdatable2");
                userVO.executeQuery();
                while (userVO.hasNext()) {
                    UserUpdatableVORowImpl userRow =(UserUpdatableVORowImpl) userVO.next();
                    if (user.equals(userRow.getAttribute("Userid"))) {
                        userRow.setPassword(newPassword.getValue().toString());
                      dc.commitTransaction();
                        break;
                    }
                }
            }
        }
    }

 
 
  public void selectOneChoice(ActionEvent actionEvent) throws Exception {
      // Add event code here...
      String user =(String)AdfFacesContext.getCurrentInstance().getPageFlowScope().get("userName");
      //String user1 = userId.getValue().toString();
      System.out.println("Seleced ran"+user);
      System.out.println(selectedMonth.getValue().toString());
      int month = Integer.parseInt(selectedMonth.getValue().toString());
      month++;
      System.out.println(user);
      
      
    FacesContext context = FacesContext.getCurrentInstance();
    BindingContext bindingContext = BindingContext.getCurrent();
    DCDataControl dc = bindingContext.findDataControl("AssetManagementAM1DataControl"); // Name of application module in datacontrolBinding.cpx
    AssetManagementAM1Impl am3 =(AssetManagementAM1Impl)dc.getDataProvider();
    
      MonthlyAnalyticsNewVVOImpl monthlyVo =(MonthlyAnalyticsNewVVOImpl)am3.findViewObject("MonthlyAnalyticsNewV1");
      System.out.println("this also ran"+user);
    String sDate1= "01/"+month+"/2017";
    java.util.Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
     
      ViewCriteria vc_new = monthlyVo.getViewCriteria("MonthlyAnalyticsNewVVOCriteria");
      monthlyVo.setNamedWhereClauseParam("m_user",user);
      
    java.util.Date dateFirst = getFirstDay(date1);
    java.util.Date dateLast = getLastDay(date1);
            System.out.println(dateFirst);
            System.out.println(dateLast);
      Calendar calendar = new GregorianCalendar();
      oracle.jbo.domain.Timestamp timestampObj1 = new oracle.jbo.domain.Timestamp(dateFirst);
      oracle.jbo.domain.Timestamp timestampObj2 = new oracle.jbo.domain.Timestamp(dateLast);
                 monthlyVo.setNamedWhereClauseParam("m_date1",timestampObj1);
                 monthlyVo.setNamedWhereClauseParam("m_date2",timestampObj2);
      
      monthlyVo.applyViewCriteria(vc_new, true);
      monthlyVo.getQuery();
      monthlyVo.executeQuery();
      
      
      
      
  }
  
  public java.util.Date getFirstDay(java.util.Date d) throws Exception
      {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(d);
          calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
          java.util.Date dddd = calendar.getTime();
          return dddd;
      }
   
      public java.util.Date getLastDay(java.util.Date d) throws Exception
      {
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(d);
          calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
          java.util.Date dddd = calendar.getTime();
          return dddd;
      }


    public void setSelectedMonth(RichSelectOneChoice selectedMonth) {
        this.selectedMonth = selectedMonth;
    }

    public RichSelectOneChoice getSelectedMonth() {
        return selectedMonth;
    }

    public void createUserPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operation=bindings.getOperationBinding("CreateInsert");
        operation.execute();
    }


    public void createUserDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        String result= dialogEvent.getOutcome().toString();
        if ("ok".equalsIgnoreCase(dialogEvent.getOutcome().toString())) { 
               // do Ok things
            
               BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
               OperationBinding operation=bindings.getOperationBinding("Commit");
               operation.execute();
            
    }
        else if ("cancel".equalsIgnoreCase(dialogEvent.getOutcome().toString())) { 
               // do Ok things
               BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
               OperationBinding operation=bindings.getOperationBinding("Rollback");
               operation.execute();
            
    }
}

    public void dashboardSaveProfilePopupFetchListener(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        System.out.println("here we are");
        if(checkDashboardProfileValueChange==true)
        {
        UIComponent popup=popupFetchEvent.getComponent();
              BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
              OperationBinding operation=bindings.getOperationBinding("Commit");
              operation.execute();
//              popup.setRendered(true);
              FacesMessage msg = new FacesMessage("The data has been saved successfully");
              checkDashboardProfileValueChange=false;
              FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        else
        {
              FacesMessage msg = new FacesMessage("You have not changed anything!");
                      FacesContext.getCurrentInstance().addMessage(null, msg);
//              UIComponent popup=popupFetchEvent.getComponent();
//            popup.setRendered(false);
            }
        
        
    }

    public void valueChangeListenerDashboardProfiles(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        checkDashboardProfileValueChange=true;
        
    }

   
}
