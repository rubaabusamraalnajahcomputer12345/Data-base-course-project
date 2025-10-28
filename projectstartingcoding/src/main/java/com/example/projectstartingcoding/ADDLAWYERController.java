package com.example.projectstartingcoding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ADDLAWYERController implements Initializable {
    @FXML
    public Button canclebutton;
    @FXML
    public Button savebutton;


    @FXML
    private String DB_URL="jdbc:postgresql://localhost:5432/postgres";
    @FXML
    private String DB_USERNAME="projectproject";
    @FXML
    private String DB_PASSWORD="12345";
    @FXML
    private TextField lawyeridfield;
    @FXML
    private TextField firstnamefield;
    @FXML
    private TextField lastnamefield;
    @FXML
    private TextField cityfield;
    @FXML
    private TextField streetnumberfield;
    @FXML
    private TextField numberofyearfield;
    @FXML
    private ComboBox SEX_BOX;
    @FXML
    private TextField birthdatefield;
    @FXML
    private TextField phonefield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField addressfield;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listSEX= FXCollections.observableArrayList("M","F");
        SEX_BOX.setItems(listSEX);


    }






    @FXML
    protected void cancleaddframe (ActionEvent event) throws IOException{

        FXMLLoader loader=new FXMLLoader(getClass().getResource("LAWYER.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }



    @FXML
    protected void savethenewlawyer(ActionEvent event){
        Connection con=null;
      //  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
con=DriverManager.getConnection(DB_URL,"projectproject","12345");

         //   Date birthdate = Date.valueOf(this.birthdatefield.getText());

String qry="INSERT INTO lawpro.lawyer(\"Lawyer_id\" , \"FName\" , \"LName\" , \"Street_number\" , \"City\" , \"Phone_number\" , \"Email\" , \"Number_of_years_Professional_experience\", \"Sex\" , \"Bdate\" )" +
        " values ("+this.lawyeridfield.getText()+",'"+this.firstnamefield.getText()+"','"+this.lastnamefield.getText()+"',"+this.streetnumberfield.getText()+",'"+this.cityfield.getText()+"','"+this.phonefield.getText()+"','"+this.emailfield.getText()+"',"+this.numberofyearfield.getText()+",'"+this.SEX_BOX.getSelectionModel().getSelectedItem().toString()+"','"+this.birthdatefield.getText()+"')";

Statement ST=con.createStatement();
ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADDING A NEW APPOINTMENT SUCCESSFULL", ButtonType.OK);
            A.show();
con.close();
            } catch (SQLException e) {
                e.printStackTrace();  // Handle the exception appropriately


        }



    }

}
