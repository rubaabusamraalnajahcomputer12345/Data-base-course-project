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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CLIENTController implements Initializable {
    @FXML
    public Button PREVIOUS_BUTTONc;

    @FXML
    private Button ADDCLIENTBUTTON;

    @FXML
    private TextField BDATE_FIELD;

    @FXML
    private TextField CITY_FIELD;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private Button DELETECLIENTBUTTON;

    @FXML
    private TextField EMAIL_FIELD;

    @FXML
    private TextField NAME_FIELD;

    @FXML
    private TextField PHONENUMBER_FIELD;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private ComboBox SEX_BOX;

    @FXML
    private TextField STREETNUMBER_FIELD;
    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private Button UPCLIENTBUTTON;

    @FXML
    private TableView<CLIENT> CLIENTTABLE;
    @FXML
    private TableColumn<CLIENT, String> bd_col;

    @FXML
    private TableColumn<CLIENT, String> city_col;

    @FXML
    private TableColumn<CLIENT, Integer> clientid_col;

    @FXML
    private TableColumn<CLIENT, String> email_col;

    @FXML
    private TableColumn<CLIENT, String> name_col;

    @FXML
    private TableColumn<CLIENT, String> phone_col;

    @FXML
    private TableColumn<CLIENT, String> sex_col;

    @FXML
    private TableColumn<CLIENT, Integer> street_col;

    @FXML
    private TableColumn<CLIENT, Integer> lid_col;
    int index = -1;
    ObservableList<CLIENT> listM;
    ObservableList<CLIENT> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;

    @FXML
    protected void exitc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root = loader.load();
        Scene dashboardScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientid_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        name_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("name"));
        city_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("city"));


        phone_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("phonenumber"));
        email_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("email"));
        sex_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("sex"));

        street_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("streetnumber"));
        bd_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("BD"));
        lid_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("lawyer_id"));
        ObservableList<String> listSEX= FXCollections.observableArrayList("M","F");
        SEX_BOX.setItems(listSEX);


        try {
            listM = MYSQLCONNCLIENT.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CLIENTTABLE.setItems(listM);
    }


    @FXML
    void getSelected(MouseEvent event) {
        index = CLIENTTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        CLIENTID_FIELD.setText(clientid_col.getCellData(index).toString());
        NAME_FIELD.setText(name_col.getCellData(index).toString());

        CITY_FIELD.setText(city_col.getCellData(index).toString());

        BDATE_FIELD.setText(bd_col.getCellData(index).toString());
        STREETNUMBER_FIELD.setText(street_col.getCellData(index).toString());
        ObservableList<String> selectdata= FXCollections.observableArrayList("F","M");
        SEX_BOX.setItems(selectdata);
        PHONENUMBER_FIELD.setText(phone_col.getCellData(index).toString());
        EMAIL_FIELD.setText(email_col.getCellData(index).toString());
        LAWYERID_FIELD.setText(lid_col.getCellData(index).toString());
    }


    @FXML
    void deletclient(ActionEvent event) {

        CLIENT delete = CLIENTTABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNCLIENT.ConnectDB();
        String query = "DELETE  FROM lawpro.Client WHERE \"Client_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getClient_id());
            PS.execute();
            updatetablerefressh();
            searchclient();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void updateclient(ActionEvent event) {
        try {
            conn = MYSQLCONNLAWYER.ConnectDB();
            String VALUE1 = CLIENTID_FIELD.getText();
            String VALUE2 = NAME_FIELD.getText();
            String VALUE3 = CITY_FIELD.getText();
            String VALUE5 = PHONENUMBER_FIELD.getText();
            String VALUE6 = EMAIL_FIELD.getText();
            String VALUE8 = SEX_BOX.getSelectionModel().getSelectedItem().toString();
            String VALUE9 = BDATE_FIELD.getText();
            String VALUE11 = STREETNUMBER_FIELD.getText();
            String VALUE4=LAWYERID_FIELD.getText();

            String sql = "UPDATE lawpro.Client set \"Client_id\"=" + VALUE1 + " ,\"Name\"='" + VALUE2 + "',\"City\"='" + VALUE3+ "',\"Phone_number\"='" + VALUE5 + "',\"Email\"='" + VALUE6 + "',\"Sex\"='" + VALUE8 + "' ,\"Bdate\"='" + VALUE9 + "'" +
                    ",\"Street_number\"=" + VALUE11 + " , \"Lawyer_id\" = " + VALUE4+" WHERE \"Client_id\"=" + VALUE1 + " ";
            PS = conn.prepareStatement(sql);
            PS.execute();
            updatetablerefressh();
            searchclient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    @FXML
    public void updatetablerefressh() throws SQLException {
        clientid_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("client_id"));
        name_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("name"));
        city_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("city"));


        phone_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("phonenumber"));
        email_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("email"));
        sex_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("sex"));

        street_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("streetnumber"));
        bd_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("BD"));
        LAWYERID_FIELD.setText(lid_col.getCellData(index).toString());
        listM = MYSQLCONNCLIENT.getDatausers();
        CLIENTTABLE.setItems(listM);

    }



    @FXML
    void searchclient() throws SQLException{
        clientid_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        name_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("name"));
        city_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("city"));


        phone_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("phonenumber"));
        email_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("email"));
        sex_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("sex"));

        street_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("streetnumber"));
        bd_col.setCellValueFactory(new PropertyValueFactory<CLIENT, String>("BD"));
        lid_col.setCellValueFactory(new PropertyValueFactory<CLIENT, Integer>("lawyer_id"));

        datalist=MYSQLCONNCLIENT.getDatausers();
        CLIENTTABLE.setItems(datalist);

        FilteredList<CLIENT> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
            if (String.valueOf(person.getStreetnumber()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            }

            if(person.getCity().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getBD().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getEmail().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getSex().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getPhonenumber().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}
            if(person.getName().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                return true;}


            if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            if (String.valueOf(person.getClient_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}

            else
                return false;


        });
        });
        SortedList<CLIENT> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(CLIENTTABLE.comparatorProperty());
        CLIENTTABLE.setItems(sotedData);


    }



    @FXML
    void addnewclient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADDCLIENT.fxml"));
        Parent root = loader.load();
        Scene dashboardScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();



    }



}







