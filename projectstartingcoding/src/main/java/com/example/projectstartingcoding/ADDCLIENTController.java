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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ADDCLIENTController implements Initializable {
    @FXML
    private String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @FXML
    private TextField BD_FIELD;

    @FXML
    private Button CANCLEADDING_BUTTON;

    @FXML
    private TextField CITY_FIELD;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private TextField EMAIL_FIELD;

    @FXML
    private TextField NAME_FIELD;

    @FXML
    private TextField PHONE_FIELD;

    @FXML
    private Button SAVENEWCLIENT_BUTTON;

    @FXML
    private ComboBox SEX_BOX;
    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private TextField STREETNUMBER_FIELD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> listSEX= FXCollections.observableArrayList("M","F");
        SEX_BOX.setItems(listSEX);


    }



    @FXML
    void addnewclient(ActionEvent event) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(DB_URL, "projectproject", "12345");
            String qry="INSERT INTO lawpro.Client(\"Client_id\" , \"Name\"  , \"Street_number\" , \"City\" , \"Phone_number\" , \"Email\" , \"Sex\" ,\"Lawyer_id\" ,\"Bdate\" )" +
                    " values ("+this.CLIENTID_FIELD.getText()+",'"+this.NAME_FIELD.getText()+"',"+this.STREETNUMBER_FIELD.getText()+",'"+this.CITY_FIELD.getText()+"','"+this.PHONE_FIELD.getText()+"','"+this.EMAIL_FIELD.getText()+"','"+this.SEX_BOX.getSelectionModel().getSelectedItem().toString()+"'," +this.LAWYERID_FIELD.getText()+" ,'"+this.BD_FIELD.getText()+"')";

            Statement ST=con.createStatement();
            ST.executeUpdate(qry);
            Alert A=new Alert(Alert.AlertType.CONFIRMATION,"ADD NEW CLIENT SUCCESSFUL", ButtonType.OK);
            A.show();
            con.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @FXML
        void cancleaddnewclient (ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CLIENT.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(dashboardScene);
            window.show();


        }


    }

