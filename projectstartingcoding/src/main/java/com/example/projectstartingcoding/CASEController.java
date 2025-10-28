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

public class CASEController implements Initializable {

    @FXML
    public Button PREVIOUS_BUTTONc;
    @FXML
    private Button ADDCASEBUTTON;

    @FXML
    private TextField CASEID_FIELD;

    @FXML
    private ComboBox CASESTATUS_BOX;

    @FXML
    private ComboBox CASETYPE_BOX;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private TextField DATE_FIELD;

    @FXML
    private Button DELETECASEBUTTON;

    @FXML
    private TextField LAWYERID_FIELD;

    @FXML
    private Button SEARCHCASEBUTTON;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private Button UPCASEBUTTON;

    @FXML
    private TableView<CASE> CASETABLE;

    @FXML
    private TableColumn<CASE, Integer> caseid_col;

    @FXML
    private TableColumn<CASE, String> casestatus_col;

    @FXML
    private TableColumn<CASE, String> casetype_col;

    @FXML
    private TableColumn<CASE, Integer> clientid_col;

    @FXML
    private TableColumn<CASE, String> date_col;

    @FXML
    private TableColumn<CASE, Integer> lid_col;

    int index = -1;
    ObservableList<CASE> listM;
    ObservableList<CASE> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;



    //THE DATA APPEAR INTABLEVIEW

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        casestatus_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_status"));
        date_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("date"));

        lid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        casetype_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_type"));

        caseid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("caseid"));

        try {
            listM = MYSQLCONNCASE.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CASETABLE.setItems(listM);
    }

//THE DATA APPEAR IN TEXTSFIELD WHEN I SELECT IT

    @FXML
    void getSelected(MouseEvent event) {
        index = CASETABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        CLIENTID_FIELD.setText(clientid_col.getCellData(index).toString());
        CASEID_FIELD.setText(caseid_col.getCellData(index).toString());

        LAWYERID_FIELD.setText(lid_col.getCellData(index).toString());
        ObservableList<String> selectdata= FXCollections.observableArrayList( "Civil","Administrative","Criminal");
        CASETYPE_BOX.setItems(selectdata);
        ObservableList<String> selectdataA= FXCollections.observableArrayList("Pending","In Progress","Completed");
        CASESTATUS_BOX.setItems(selectdataA);



        DATE_FIELD.setText(date_col.getCellData(index).toString());
    }

    ///REFRESH DATA OF CASE

    @FXML
    public void updatetablerefressh() throws SQLException {
        clientid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        casestatus_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_status"));
        date_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("date"));

        lid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        casetype_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_type"));

        caseid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("caseid"));
        listM = MYSQLCONNCASE.getDatausers();
        CASETABLE.setItems(listM);


    }
    
//DELETE DATA OF CASE
    @FXML
    void deletecase(ActionEvent event) {
        CASE delete = CASETABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNCASE.ConnectDB();
        String query = "DELETE  FROM lawpro.Case WHERE \"Case_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getCaseid());
            PS.execute();
           updatetablerefressh();
           searchcase();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


//ADDING A NEW CASE
    @FXML
    void logintoaddnewcaseframe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADDCASE.fxml"));
        Parent root = loader.load();
        Scene dashboardScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();

    }


///UPDATE DATA OF CASE
    @FXML
    void updatecase(ActionEvent event) {

        try {
            conn = MYSQLCONNLAWYER.ConnectDB();
            String VALUE1 = CASEID_FIELD.getText();
            String VALUE2 = DATE_FIELD.getText();
            String VALUE3= LAWYERID_FIELD.getText();
            String VALUE4 = CLIENTID_FIELD.getText();

            ObservableList<String> selectdata= FXCollections.observableArrayList( "Civil","Administrative","Criminal");
            CASETYPE_BOX.setItems(selectdata);
            ObservableList<String> selectdataA= FXCollections.observableArrayList("Pending","In Progress","Completed");
            CASESTATUS_BOX.setItems(selectdataA);



            String VALUE5= CASESTATUS_BOX.getSelectionModel().getSelectedItem().toString();
            String VALUE6= CASETYPE_BOX.getSelectionModel().getSelectedItem().toString();

            String sql = "UPDATE lawpro.Case set \"Case_id\"=" + VALUE1 + " ,\"Important_date\"='" + VALUE2 + "',\"Lawyer_id\"='" + VALUE3 + "',\"Client_id\"='" + VALUE4 + "',\"case_status\"='" + VALUE5 + "',\"case_type\"='" + VALUE6 +  "' WHERE \"Case_id\"=" + VALUE1 + " " ;

            PS = conn.prepareStatement(sql);
            PS.execute();
            updatetablerefressh();
            searchcase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
@FXML
    void searchcase() throws SQLException{
        clientid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        casestatus_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_status"));
        date_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("date"));

        lid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("lawyer_id"));//SET THE DATA IN A TABLE VIEW

        casetype_col.setCellValueFactory(new PropertyValueFactory<CASE, String>("case_type"));

        caseid_col.setCellValueFactory(new PropertyValueFactory<CASE, Integer>("caseid"));

        datalist=MYSQLCONNCASE.getDatausers();
      CASETABLE.setItems(datalist);

        FilteredList<CASE> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
            if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            }
            else if (person.getCase_status().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }
            else if (person.getCase_type().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }
            else if (person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            }

            if (String.valueOf(person.getCaseid()).indexOf(lowerCaseFilter) !=-1) {
                return true;}
            if (String.valueOf(person.getClient_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;}

            else
                return false;


        });
        });
        SortedList<CASE> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(CASETABLE.comparatorProperty());
        CASETABLE.setItems(sotedData);


    }


    @FXML
    protected void exitc (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }
}
