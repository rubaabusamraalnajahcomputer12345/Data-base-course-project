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

public class LAWYERCONTROLLER implements Initializable {
    int index=-1;
   @FXML
   public Button PREVIOUS_BUTTON;
   @FXML
   public Button ADDLAWBUTTON;

   // @FXML
   // private TextField ADDRESS_FIELD;

    @FXML
    private TextField BD_FIELD;

    @FXML
    private TextField CITY_FIELD;

    @FXML
    private TextField EMAIL_FIELD;

    @FXML
    private TextField FNAME_FIELD;

    @FXML
    private TextField LAWYERID_FIELD;


    @FXML
    private TextField LNAME_FIELD;

    @FXML
    private TextField PHONE_FIELD;


    @FXML
    private ComboBox SEX_BOX;

    @FXML
    private TextField STREET_FIELD;

    @FXML
    private TextField YEAR_FIELD;
    @FXML
    private Button DELETELAWBUTTON;
    @FXML
    private Button UPDATELAWYER_BUTTON;
    @FXML
    private TextField SEARCH_FIELD;

   //@FXML
  //  private TableColumn<LAWYER,String > address_col;

    @FXML
    private TableColumn<LAWYER, String> bd_col;

    @FXML
    private TableColumn<LAWYER, String> city_col;

    @FXML
    private TableColumn<LAWYER, String> email_col;

    @FXML
    private TableColumn<LAWYER, String> fname_col;

    @FXML
    private TableColumn<LAWYER, Integer> lawyerid_col;

    @FXML
    private TableColumn<LAWYER, String> lname_col;

    @FXML
    private TableColumn<LAWYER,String > phone_col;

    @FXML
    private TableColumn<LAWYER, String> sex_col;

    @FXML
    private TableColumn<LAWYER, Integer> street_col;

    @FXML
    private TableColumn<LAWYER, Integer> year_col;
    @FXML
    private TableView<LAWYER> LAWYERTABLE;
    ObservableList<LAWYER> listM;
    ObservableList<LAWYER> datalist;


@FXML
    Connection conn=null;
@FXML
    ResultSet res=null;
@FXML
    PreparedStatement PS=null;



    @FXML

    protected void exit (ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DASHBOARD FOR ADMIN.fxml"));
        Parent root=loader.load();
        Scene dashboardScene=new Scene(root);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    @FXML
    protected void addnewlawyer(ActionEvent event) throws IOException{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("ADDLAWYER.fxml"));
    Parent root=loader.load();
    Scene dashboardScene=new Scene(root);
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    window.setScene(dashboardScene);
    window.show();}




    @Override
    public void initialize(URL url, ResourceBundle rb){
        lawyerid_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("lawyer_id"));
        fname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("fname"));

        lname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("lname"));
        city_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("city"));

      //  address_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("address"));
        phone_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("phonenumber"));

        email_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("email"));
        year_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("numberofyear"));
        sex_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("sex"));
        street_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("streetnumber"));

        bd_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("BD"));
        ObservableList<String> listSEX= FXCollections.observableArrayList("M","F");
        SEX_BOX.setItems(listSEX);



        try {
            listM = MYSQLCONNLAWYER.getDatausers();
            System.out.println("Number of records retrieved rubaahmad: " + listM.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LAWYERTABLE.setItems(listM);
    }

    @FXML
    void deletelawyer(ActionEvent event) {
        LAWYER delete=LAWYERTABLE.getSelectionModel().getSelectedItem();
            conn=MYSQLCONNLAWYER.ConnectDB();
            String query="DELETE  FROM lawpro.Lawyer WHERE \"Lawyer_id\" =? ";
            try {
                PS=conn.prepareStatement(query);
                PS.setInt(1, delete.getLawyer_id());
                PS.execute();
                updatetablerefressh();
               searchlawyer();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    @FXML
    void updatelawyer(ActionEvent event) {
            try {
                conn=MYSQLCONNLAWYER.ConnectDB();
                String VALUE1=LAWYERID_FIELD.getText();
                String VALUE2=FNAME_FIELD.getText();
                String VALUE3=LNAME_FIELD.getText();
                String VALUE4=CITY_FIELD.getText();
                String VALUE5=PHONE_FIELD.getText();
                String VALUE6=EMAIL_FIELD.getText();
                String VALUE7=YEAR_FIELD.getText();
                String VALUE8=SEX_BOX.getSelectionModel().getSelectedItem().toString();
                String VALUE9=BD_FIELD.getText();
           //    String VALUE10=ADDRESS_FIELD.getText();
                String VALUE11=STREET_FIELD.getText();

                String sql="UPDATE lawpro.Lawyer set \"Lawyer_id\"="+VALUE1+" ,\"FName\"='"+VALUE2+"', \"LName\"='"+VALUE3+" ',\"City\"='"+VALUE4+"',\"Phone_number\"='"+VALUE5+"',\"Email\"='"+VALUE6+"',\"Number_of_years_Professional_experience\"="+VALUE7+",\"Sex\"='"+VALUE8+"' ,\"Bdate\"='"+VALUE9+"'" +
                        ",\"Street_number\"="+VALUE11+" WHERE \"Lawyer_id\"="+VALUE1+" ";
                PS=conn.prepareStatement(sql) ;
                PS.execute();
                updatetablerefressh();
                searchlawyer();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



    @FXML
    public void updatetablerefressh() throws SQLException {

        lawyerid_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("lawyer_id"));
        fname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("fname"));

        lname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("lname"));
        city_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("city"));

       // address_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("address"));
        phone_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("phonenumber"));

        email_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("email"));
        year_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("numberofyear"));
        sex_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("sex"));
        street_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("streetnumber"));

        bd_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("BD"));
        listM = MYSQLCONNLAWYER.getDatausers();
        LAWYERTABLE.setItems(listM);


    }




    @FXML

    void searchlawyer() throws SQLException {


        lawyerid_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("lawyer_id"));
        fname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("fname"));

        lname_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("lname"));
        city_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("city"));

       // address_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("address"));
        phone_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("phonenumber"));

        email_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("email"));
        year_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("numberofyear"));
        sex_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("sex"));
        street_col.setCellValueFactory(new PropertyValueFactory<LAWYER, Integer>("streetnumber"));

        bd_col.setCellValueFactory(new PropertyValueFactory<LAWYER, String>("BD"));

        datalist=MYSQLCONNLAWYER.getDatausers();
        LAWYERTABLE.setItems(datalist);

        FilteredList<LAWYER> filteredata=new FilteredList<>(datalist, b->true);
        SEARCH_FIELD.textProperty().addListener( (observable,oldValue,newValue)->{filteredata.setPredicate(person -> {
            if(newValue==null || newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter=newValue.toLowerCase();
             if (String.valueOf(person.getLawyer_id()).indexOf(lowerCaseFilter) !=-1) {
                return true;
            }
             else if(person.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;
             }
             else if(person.getFname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;
             }

             else if(person.getLname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;
             }

             else if(person.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                 return true;
             }


             else
                return false;


        });
        });
        SortedList<LAWYER> sotedData=new SortedList<>(filteredata);
        sotedData.comparatorProperty().bind(LAWYERTABLE.comparatorProperty());
        LAWYERTABLE.setItems(sotedData);

    }

    @FXML
    void getSelected(MouseEvent event) {
        index = LAWYERTABLE.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        LAWYERID_FIELD.setText(lawyerid_col.getCellData(index).toString());
        LNAME_FIELD.setText(lname_col.getCellData(index).toString());
        FNAME_FIELD.setText(fname_col.getCellData(index).toString());
        CITY_FIELD.setText(city_col.getCellData(index).toString());
      //  ADDRESS_FIELD.setText(address_col.getCellData(index).toString());
        BD_FIELD.setText(bd_col.getCellData(index).toString());
        STREET_FIELD.setText(street_col.getCellData(index).toString());
        ObservableList<String> selectdata= FXCollections.observableArrayList("F","M");
        SEX_BOX.setItems(selectdata);
       // SEX_BOX.setText(sex_col.getCellData(index).toString());
        YEAR_FIELD.setText(year_col.getCellData(index).toString());
        PHONE_FIELD.setText(phone_col.getCellData(index).toString());
        EMAIL_FIELD.setText(email_col.getCellData(index).toString());
    }

    }

































