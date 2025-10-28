package com.example.projectstartingcoding;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import  javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.*;
import javafx.stage.Stage;



public class LOGINController {
@FXML
public TextField USERFIELD;

@FXML
public PasswordField PASSFIELD;
@FXML
public Button LOGINBUTTON;
@FXML
public ToggleButton BOXBUTTON;
@FXML
private String DB_URL="jdbc:postgresql://localhost:5432/postgres";
@FXML
private String DB_USERNAME="projectproject";
@FXML
private String DB_PASSWORD="12345";
@FXML
private Label wronglogin;
@FXML
private Label showpassword;
@FXML
private TextField PASSWORD;


@FXML
void Showpassword(ActionEvent event){
 if(BOXBUTTON.isSelected()){
 showpassword.setVisible(true);
 showpassword.textProperty().bind(Bindings.concat(PASSFIELD.getText()));
BOXBUTTON.setText("HIDE");
 }
 else {
  showpassword.setVisible(false);
  BOXBUTTON.setText("SHOW PASSWORD");
 }
}

@FXML
protected void login(ActionEvent event)  throws IOException {

String usernameinput=USERFIELD.getText();
 String passwordinput=PASSFIELD.getText();
Boolean loginsuccessful= validateCredentials(usernameinput,passwordinput);
if(loginsuccessful){
 wronglogin.setText("LOGIN SUCCESSFUL");
 afterLoginSuccessfullawyer(event);
}
else {
 wronglogin.setText("LOGIN FAILED");

}

 if("ADMIN".equals(usernameinput)&&"12345678910".equals(passwordinput)) {
afterLoginSuccessful(event);///
}

};

@FXML
private void afterLoginSuccessful(ActionEvent event) throws IOException{

  FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
  Parent root=loader.load();
 Scene dashboardScene=new Scene(root);
 Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  window.setScene(dashboardScene);
  window.show();
  }

 @FXML
 private void afterLoginSuccessfullawyer(ActionEvent event) throws IOException{

  FXMLLoader loader=new FXMLLoader(getClass().getResource("LAWYERDATA.fxml"));
  Parent root=loader.load();
  Scene dashboardScene=new Scene(root);
  Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
  window.setScene(dashboardScene);
  window.show();
 }




 boolean validateCredentials(String username,String password){
 Connection con=null;
 try{
 //DriverManager.registerDriver(new org.postgresql.Driver());
  con=DriverManager.getConnection(DB_URL,"projectproject","12345");
     String sql = "SELECT * FROM lawpro.user WHERE \"User_id\"=? AND \"Password\"=?";
     PreparedStatement st=con.prepareStatement(sql);
  st.setString(1,username);
  st.setString(2,password);
  ResultSet res=st.executeQuery();
 return res.next();

 }
 catch (SQLException e){
  e.printStackTrace();
  return false;
 }


 finally{
  try{
   if (con!=null){
    con.close();
   }
  }
  catch(SQLException e){
   e.printStackTrace();
  }


 }



}

 }







