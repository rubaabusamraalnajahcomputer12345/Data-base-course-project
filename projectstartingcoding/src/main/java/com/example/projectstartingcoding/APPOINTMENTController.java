package com.example.projectstartingcoding;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class APPOINTMENTController implements Initializable {
    @FXML
    public Button PREVIOUS_BUTTONa;



    @FXML
    private Button ADDAPPBUTTON;

    @FXML
    private TextField APPOINTMENTID_FIELD;

    @FXML
    private TableView<APPOINTMENT> APPONTMENTTABLE;

    @FXML
    private TextField CITY_FIELD;

    @FXML
    private TextField CLIENTID_FIELD;

    @FXML
    private TextField COURT_FIELD;

    @FXML
    private TextField DATE_FIELD;

    @FXML
    private Button DELETEAPPBUTTON;

    @FXML
    private TextField ENDTIME_FIELD;

    @FXML
    private TextField LAWYERID_FIELD;


    @FXML
    private Button SEARCHAPPBUTTON;

    @FXML
    private TextField SEARCH_FIELD;

    @FXML
    private TextField STARTTIME_FIELD;

    @FXML
    private TextField STREETNUM_FIELD;

    @FXML
    private Button UPAPPBUTTON;

    @FXML
    private TableColumn<APPOINTMENT, Integer> appid_col;

    @FXML
    private TableColumn<APPOINTMENT,String > city_col;

    @FXML
    private TableColumn<APPOINTMENT, Integer> clientid_col;

    @FXML
    private TableColumn<APPOINTMENT, String> courtname_col;

    @FXML
    private TableColumn<APPOINTMENT, String> date_col;

    @FXML
    private TableColumn<APPOINTMENT, String> endtime_col;

    @FXML
    private TableColumn<APPOINTMENT, Integer> lawyerid_col;

    @FXML
    private TableColumn<APPOINTMENT, String> starttime_col;

    @FXML
    private TableColumn<APPOINTMENT, Integer> streetnum_col;



    int index = -1;
    ObservableList<APPOINTMENT> listM;
    ObservableList<APPOINTMENT> datalist;


    @FXML
    Connection conn = null;
    @FXML
    ResultSet res = null;
    @FXML
    PreparedStatement PS = null;



    //THE DATA APPEAR INTABLEVIEW

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clientid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        courtname_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("court_name"));
        date_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("date"));

        appid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("appointment_id"));//SET THE DATA IN A TABLE VIEW

        starttime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("start_time"));
        endtime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("end_time"));

        lawyerid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("lawyer_id"));
        streetnum_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("street_number"));
        city_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("city"));


        try {
            listM = MYSQLCONNAPPOINTMENT.getDatausers();
            //System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        APPONTMENTTABLE.setItems(listM);
    }

///DELETE DATA FROM TABLE

    @FXML
    void daleteappointment(ActionEvent event) {
        APPOINTMENT delete = APPONTMENTTABLE.getSelectionModel().getSelectedItem();
        conn = MYSQLCONNAPPOINTMENT.ConnectDB();
        String query = "DELETE  FROM lawpro.Appointment WHERE \"Appointment_id\" =? ";
        try {
            PS = conn.prepareStatement(query);
            PS.setInt(1, delete.getAppointment_id());
            PS.execute();
            updatetablerefressh();
            searchappointment();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


///PUT THE DATA WHICH SELECT IN FIELDS
    @FXML
    void getSelected(MouseEvent event) {
        index = APPONTMENTTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        CLIENTID_FIELD.setText(clientid_col.getCellData(index).toString());
        APPOINTMENTID_FIELD.setText(appid_col.getCellData(index).toString());

        LAWYERID_FIELD.setText(lawyerid_col.getCellData(index).toString());

        CITY_FIELD.setText(city_col.getCellData(index).toString());
        COURT_FIELD.setText(courtname_col.getCellData(index).toString());
        DATE_FIELD.setText(date_col.getCellData(index).toString());

        STARTTIME_FIELD.setText(starttime_col.getCellData(index).toString());
        ENDTIME_FIELD.setText(endtime_col.getCellData(index).toString());
        STREETNUM_FIELD.setText(streetnum_col.getCellData(index).toString());
    }

    ///REFRESH THE TABLE
    @FXML
    public void updatetablerefressh() throws SQLException {

        clientid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("client_id"));//SET THE DATA IN A TABLE VIEW
        courtname_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("court_name"));
        date_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("date"));

        appid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("appointment_id"));//SET THE DATA IN A TABLE VIEW

        starttime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("start_time"));
        endtime_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("end_time"));

        lawyerid_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("lawyer_id"));
        streetnum_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, Integer>("street_number"));
        city_col.setCellValueFactory(new PropertyValueFactory<APPOINTMENT, String>("city"));
        listM = MYSQLCONNAPPOINTMENT.getDatausers();
        APPONTMENTTABLE.setItems(listM);


    }


///LOGIN TO ADD A NEW APPOINTMENT
    @FXML
    void logintoaddnewappointmentframe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADDAPPOINTMENT.fxml"));
        Parent root = loader.load();
        Scene dashboardScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(dashboardScene);
        window.setMaximized(true);
        window.show();
        window.centerOnScreen();


    }

    ///UPDATE A SELECTED DATA
    @FXML
    void updateappointment(ActionEvent event) {
        try {
            conn = MYSQLCONNLAWYER.ConnectDB();
            String VALUE1 = APPOINTMENTID_FIELD.getText();
            String VALUE2 = DATE_FIELD.getText();
            String VALUE3= LAWYERID_FIELD.getText();
            String VALUE4 = CLIENTID_FIELD.getText();
            String VALUE5= COURT_FIELD.getText();
            String VALUE6= STARTTIME_FIELD.getText();
            String VALUE7 = CITY_FIELD.getText();
            String VALUE8= ENDTIME_FIELD.getText();
            String VALUE9= STREETNUM_FIELD.getText();

            String sql = "UPDATE lawpro.Appointment set \"Appointment_id\"=" + VALUE1 + " ,\"Date\"='" + VALUE2 + "',\"Lawyer_id\"='" + VALUE3 + "',\"Client_id\"=" + VALUE4 + ",\"Court_name\"='" + VALUE5 + "',\"Start_time\"='" + VALUE6 +"', \"City\"='" + VALUE7 +"',\"End_time\"='" + VALUE8 +"' ,\"Street_number\"=" + VALUE9 +"   WHERE \"Appointment_id\"=" + VALUE1 + " " ;

            PS = conn.prepareStatement(sql);
            PS.execute();
            updatetablerefressh();
            searchappointment();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
void searchappointment() throws SQLException {

        datalist = MYSQLCONNAPPOINTMENT.getDatausers();
        APPONTMENTTABLE.setItems(datalist);

        FilteredList<APPOINTMENT> filteredata = new FilteredList<>(datalist, b -> true);
        SEARCH_FIELD.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredata.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (person.getCourt_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(person.getAppointment_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(person.getClient_id()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                if (String.valueOf(person.getStreet_number()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if(person.getStart_time().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if(person.getEnd_time().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if(person.getDate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }



             else
                    return false;


            });
        });
        SortedList<APPOINTMENT> sotedData = new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(APPONTMENTTABLE.comparatorProperty());
        APPONTMENTTABLE.setItems(sotedData);

    }















    @FXML
    protected void exita (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
        window.centerOnScreen();
    }


}
