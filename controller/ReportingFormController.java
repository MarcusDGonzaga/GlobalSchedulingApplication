package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**Reporting View Controller Class.
 * Reporting View Window that pops up from the Main Menu Window.
 * After Pressing the Reporting Button.
 */
public class ReportingFormController implements Initializable {

    Stage stage;
    Parent scene;
    //TableView of reporting of Appointments as requested
    @FXML TableView <Appointments> allReporting;
    @FXML private TableColumn<Appointments, Integer> reportingIDCol;
    @FXML private TableColumn<Appointments, String> reportingTitleCol;
    @FXML private TableColumn<Appointments, String> reportingDescCol;
    @FXML private TableColumn<Appointments, String> reportingLocationCol;
    @FXML private TableColumn<Appointments, String> reportingContactCol;
    @FXML private TableColumn<Appointments, String> reportingTypeCol;
    @FXML private TableColumn<Appointments, String> startDateTimeCol;
    @FXML private TableColumn<Appointments, String> endDateTimeCol;
    @FXML private TableColumn<Appointments, Integer> customerIDCol;

    //ToggleGroup of RadioButtons to choose for Reporting
    @FXML private ToggleGroup reportToggleGrp;
    @FXML private RadioButton apptTypeMonth;
    @FXML private RadioButton byContact;
    @FXML private RadioButton apptLocMonth;
    @FXML private ComboBox comboBox1;
    @FXML private ComboBox comboBox2;
    @FXML private TextField totalNumTxt;

    /**Type and Month RadioButton Method.
     * Sets ComboBoxes to request report by Type and Month.
     */
    @FXML
    public void onActionTypeMonth (ActionEvent event)
    {
        //Enables ComboBoxes
        //Disables TableView
        comboBox1.setDisable(false);
        totalNumTxt.setDisable(false);
        allReporting.setDisable(true);
        //Clears TableView from unneeded information
        allReporting.getItems().clear();
        //sets Type ComboBox
        comboBox1.setPromptText("Choose Type");
        ObservableList<TypeCB> type = TypeCB.getAllTypes();
        comboBox1.setItems(type);
        //sets Month ComboBox
        comboBox2.setPromptText("Choose Month");
        ObservableList<MonthCB> months = MonthCB.getAllMonths();
        comboBox2.setItems(months);
    }

    /**Contact RadioButton Method.
     * Filters Appointments by Contact.
     */
    @FXML
    public void onActionByContact (ActionEvent event)
    {
        //Disable first ComboBox1 and TextField
        //Enables TableView amd ComboBox2
        totalNumTxt.setDisable(true);
        allReporting.setDisable(false);
        comboBox1.setDisable(true);

        allReporting.getItems().clear();
        //sets ComboBox2 information with Contacts
        comboBox2.setPromptText("Select Contact");
        ObservableList<ContactsCB> contact = ContactsCB.getAllContacts();
        comboBox2.setItems(contact);
    }

    /**Location and Month RadioButton Method.
     * Sets ComboBoxes to request report by Location and Month.
     */
    @FXML
    public void onActionLocMonth (ActionEvent event)
    {
        //Enables ComboBoxes
        //Disables TableView
        comboBox1.setDisable(false);
        totalNumTxt.setDisable(false);
        allReporting.setDisable(true);

        allReporting.getItems().clear();
        //sets ComboBox1 with locations
        comboBox1.setPromptText("Choose Location");
        ObservableList<LocationCB> location = LocationCB.getAllLocations();
        comboBox1.setItems(location);
        //sets ComboBox2 with months
        comboBox2.setPromptText("Choose Month");
        ObservableList<MonthCB> months = MonthCB.getAllMonths();
        comboBox2.setItems(months);
    }

    /**Enter Button Method.
     * Create reports when pressed.
     * Based on the RadioButton chosen.
     */
    @FXML
    public void onActionEnter (ActionEvent event) throws IOException, SQLException
    {
        //Type Month RadioButton is selected
        if(apptTypeMonth.isSelected())
        {
            TypeCB selectType = (TypeCB) comboBox1.getValue();
            String typeName = selectType.getName();

            MonthCB selectMonth = (MonthCB) comboBox2.getValue();
            int monthNum = selectMonth.getMonthNum();
            //Displays total number of Appointments matching Type and Month values
            totalNumTxt.setText(Integer.toString(ReportsFiltered.sqlFilterTypeMonth(typeName,monthNum)));
        }
        //Contact RadioButton is selected
        else if(byContact.isSelected())
        {
            allReporting.getItems().clear();
            ContactsCB selectContact = (ContactsCB) comboBox2.getValue();
            int contactID = selectContact.getId();
            //Populated Table with Appointments associated with that Contact
            try {
                Appointments.sqlFilterContacts(contactID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // table view setup for all appointments
            allReporting.setItems(Appointments.getAllAppointments());
            reportingIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            reportingTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            reportingDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            reportingLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            reportingContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
            reportingTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
            endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
            customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        }
        //Location Month RadioButton is selected
        else if(apptLocMonth.isSelected())
        {
            LocationCB selectionLoc = (LocationCB) comboBox1.getValue();
            String locationName = selectionLoc.getLocation();

            MonthCB selectMonth = (MonthCB) comboBox2.getValue();
            int monthNum = selectMonth.getMonthNum();
            //Displays total number of Appointments matching Location and Month values
            totalNumTxt.setText(Integer.toString(ReportsFiltered.sqlFilterLocMonth(locationName,monthNum)));
        }
    }

    /**Back Button Method.
     * Directs User back to Main Menu View.
     */
    @FXML
    public void onActionBackToMainMenu (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.FXML"));
        stage.setScene(new Scene(scene));

       allReporting.getItems().clear();
    }

    /**Exit Button Method.
     * Exits the program when selected.
     */
    @FXML
    public void onActionExit (ActionEvent event) throws Exception
    {
        System.exit(0);
    }

    /**Initialize Method.
     * Initially ran when Reporting View is called.
     * Empty.
     */
    public void initialize (URL url, ResourceBundle rb)
    {

    }
}
