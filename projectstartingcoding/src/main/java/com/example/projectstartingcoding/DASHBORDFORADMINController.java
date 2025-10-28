package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.BorderSplitType;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.PositionTypeEnum;
import net.sf.jasperreports.engine.type.StretchTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


import static com.example.projectstartingcoding.MYSQLCONN.ConnectDB;

public class DASHBORDFORADMINController implements Initializable {
    @FXML
    public Button LAWYER_BUTTON;
    @FXML
    public Button CLIENT_BUTTON;
    @FXML
    public Button DOCUMENT_BUTTON;
    @FXML
    public Button CASES_BUTTON;

    @FXML
    public Button APPOINTMENT_BUTTON;
    @FXML
    public Button TASK_BUTTON;
    @FXML
    public Button BILLINGS_BUTTON;

    @FXML
    public Button LOGOUT_BUTTON;
    @FXML
    public Button DELETEUSER_BUTTON;
    @FXML
    public TextField SEARCH_FIELD;
@FXML
public Button ADDUSER_BUTTON;
    @FXML
    public Button ruba_button;

    @FXML
    private Button LAST_BUTTON;

    @FXML
    private TextField user_txt;
    @FXML
    private TextField pass_txt;
   // @FXML
   // public TextField PASSWORD_txt;

  //  @FXML
   // public TextField USERID_txt;
    @FXML
    int index=-1;

    @FXML
    private static String DB_URL="jdbc:postgresql://localhost:5432/postgres";
    @FXML
    private TableView<USER> USERTABLE;
    @FXML
    private TableColumn<USER, String> password_column;

    @FXML
    private TableColumn<USER, String> username_column;
    ObservableList<USER>listM;
    ObservableList<USER>datalist;

Connection con=null;
ResultSet res=null;
PreparedStatement PS=null;
@Override
    public void initialize(URL url, ResourceBundle rb){
    username_column.setCellValueFactory(new PropertyValueFactory<USER, String>("user_id"));
    password_column.setCellValueFactory(new PropertyValueFactory<USER, String>("password"));

    try {
        listM = MYSQLCONN.getDatausers();
        //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
    } catch (SQLException e) {
       e.printStackTrace();
    }
    USERTABLE.setItems(listM);
    }
@FXML
    protected void logintolawyerframe (ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("LAWYER.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
}
@FXML
    protected void logintoclientframe (ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("CLIENT.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

@FXML

protected void logintoappointmentframe (ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("APPOINTMENT.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
}

@FXML

protected void logintodocumentframe (ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("DOCUMENT.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
}
@FXML
    protected void logintobillingframe (ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("BILLING.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
@FXML

protected void logintotaskframe (ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("TASK.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
}
@FXML
protected void logintocaseframe (ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("CASE.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();
}


    @FXML
    protected void logoutframe (ActionEvent event) throws IOException{

        Alert A=new Alert(Alert.AlertType.CONFIRMATION);
        A.setTitle("LOGOUT");
        A.setHeaderText("You are about to logout!");
        A.setContentText("Do you want to logout?");
        if(A.showAndWait().get()==ButtonType.OK)
        {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();}
    }

@FXML
    protected void deleteuser(ActionEvent event) throws IOException{
    USER delete=USERTABLE.getSelectionModel().getSelectedItem();
    con=MYSQLCONN.ConnectDB();
    String query="DELETE  FROM lawpro.User WHERE \"User_id\" =? ";
try {
    PS=con.prepareStatement(query);
PS.setString(1, delete.getUser_id());
PS.execute();
    updatetablerefressh();
    searchuser();

} catch (Exception e) {
    throw new RuntimeException(e);
}
}
@FXML
void getSelected(MouseEvent event){
    index=USERTABLE.getSelectionModel().getSelectedIndex();
    if(index<=-1){
        return;
    }
    user_txt.setText(username_column.getCellData(index).toString());
    pass_txt.setText(password_column.getCellData(index).toString());

}

@FXML
public void updatetablerefressh() throws SQLException {
    username_column.setCellValueFactory(new PropertyValueFactory<USER, String>("user_id"));
    password_column.setCellValueFactory(new PropertyValueFactory<USER, String>("password"));
    listM = MYSQLCONN.getDatausers();
    USERTABLE.setItems(listM);

}

@FXML
    void edit(ActionEvent event){
try {
    con=MYSQLCONN.ConnectDB();
    String VALUE1=user_txt.getText();
    String VALUE2=pass_txt.getText();
    String sql="UPDATE lawpro.User set \"User_id\"='"+VALUE1+"' ,\"Password\"='"+VALUE2+"' WHERE \"User_id\"='" +VALUE1+"'";
    PS=con.prepareStatement(sql) ;
    PS.execute();
    updatetablerefressh();
    searchuser();
} catch (Exception e) {
    throw new RuntimeException(e);
}
}

@FXML

    void searchuser() throws SQLException {
    username_column.setCellValueFactory(new PropertyValueFactory<USER, String>("user_id"));
    password_column.setCellValueFactory(new PropertyValueFactory<USER, String>("password"));
datalist=MYSQLCONN.getDatausers();
USERTABLE.setItems(datalist);
   FilteredList<USER>filteredata=new FilteredList<>(datalist,b->true);
   SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
       if(newValue==null || newValue.isEmpty()){
           return true;
       }
       String lowerCaseFilter=newValue.toLowerCase();
       if(person.getUser_id().toLowerCase().indexOf(lowerCaseFilter) !=-1){
           return true;
       } else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) !=-1) {
           return true;
       }
       else
           return false;


   });
   });
    SortedList<USER>sotedData=new SortedList<>(filteredata);
    sotedData.comparatorProperty().bind(USERTABLE.comparatorProperty());
    USERTABLE.setItems(sotedData);

}

////MAKE A REPORT

    @FXML
    void makeareport(ActionEvent event) {
    FileInputStream input;
        OutputStream output;
      JasperReport jr;
        JasperPrint jp;
       JasperDesign jd;
       Connection con=null;
        try {
            con=DriverManager.getConnection(DB_URL,"projectproject","12345");
           input=new FileInputStream(new File("Flower_Landscape_Table_Based.jrxml"));
           jd= JRXmlLoader.load(input);
          jr= JasperCompileManager.compileReport(jd);
          jp= JasperFillManager.fillReport(jr,null, con);

         output=new FileOutputStream(new File("sec.pdf"));
            JasperExportManager.exportReportToPdfStream(jp,output);
           output.close();
            input.close();
            con.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }






    @FXML
    protected void logintoadduserframe (ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ADDUSER.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }







}


