package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**Appointment View Controller Class.
 * Appointment View Window that pops up from the Main Menu Window.
 * After Pressing the Appointment Button. Includes Table View of all Appointments.
 */
public class AppointmentViewFormController implements Initializable {

    Stage stage;
    Parent scene;

    // Table View setup of Appointments
    @FXML private TableView<Appointments> allAppointments;
    @FXML private TableColumn<Appointments, Integer> appointmentIDCol;
    @FXML private TableColumn<Appointments, String> appointmentTitleCol;
    @FXML private TableColumn<Appointments, String> appointmentDescCol;
    @FXML private TableColumn<Appointments, String> appointmentLocationCol;
    @FXML private TableColumn<Appointments, String> appointmentContactCol;
    @FXML private TableColumn<Appointments, String> appointmentTypeCol;
    @FXML private TableColumn<Appointments, String> startDateTimeCol;
    @FXML private TableColumn<Appointments, String> endDateTimeCol;
    @FXML private TableColumn<Appointments, Integer> customerIDCol;
    @FXML private TableColumn<Appointments, Integer> userIDCol;

    // RadioButton ToggleGroup the filters between All, Month, and Week
    @FXML private ToggleGroup tableViewToggleGroup;
    @FXML private RadioButton allAppRBtn;
    @FXML private RadioButton monthAppRBtn;
    @FXML private RadioButton weekAppRBtn;

    private boolean selectAllApptView = true;
    private boolean selectMonthApptView = false;
    private boolean selectWeekApptView = false;

    /**All RadioButton Method.
     * Shows All Appointments when chosen.
     * Default view of appointments.
     */
    @FXML
    public void onActionAllRBtn (ActionEvent event) throws IOException
    {
        allAppointments.getItems().clear();
        //populates Table with all appointments
        try {
            Appointments.sqlAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Month RadioButton Method.
     * Shows Appointments within the current month when chosen.
     */
    @FXML
    public void onActionMonthRBtn (ActionEvent event) throws IOException
    {
        allAppointments.getItems().clear();
        //Populates table with appointments within the current month
        try {
            Appointments.sqlFilterMonthAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Week RadioButton Method.
     * Shows Appointments within the current week when chosen.
     */
    @FXML
    public void onActionWeekRBtn (ActionEvent event) throws IOException
    {
        allAppointments.getItems().clear();
        //populates table with appointments from the current week
        try {
            Appointments.sqlFilterWeekAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Add Button Method.
     * Takes User to Appointment Add Window.
     */
    @FXML
    public void onActionAddAppointment (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentAddForm.FXML"));
        stage.setScene(new Scene(scene));

        allAppointments.getItems().clear();
    }

    /**Update Button Method.
     * Takes User to Appointment Update Window.
     * Form is prepoluated.
     */
    @FXML
    public void onActionUpdateAppointment (ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource( "/view/AppointmentUpdateForm.FXML"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene (tableViewParent);

        //Sends populating information to the next window
        AppointmentUpdateFormController controller = loader.getController();
        controller.appointmentSelected(allAppointments.getSelectionModel().getSelectedItem());

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

        allAppointments.getItems().clear();
    }

    /**Delete Button Method.
     * Deletes selected appointed from both Table View and SQL doc.
     * Appointment ID and Type is displayed upon deletion.
     */
    @FXML
    public void onActionDeleteAppointment (ActionEvent event) throws IOException, SQLException
    {
        boolean confirmOk = false;
        //Confirmation Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Appointment Deletion");
        alert.setContentText("Ok to delete Appointment?");
        Optional<ButtonType> result = alert.showAndWait();

        //Customer Information will only be deleted if confirmed with the press of "OK"
        if(result.get() == ButtonType.OK)
        {
            confirmOk = true;

            int idDelete = allAppointments.getSelectionModel().getSelectedItem().getId();
            String typeDelete =allAppointments.getSelectionModel().getSelectedItem().getType();
            Appointments.deleteAppointment(allAppointments.getSelectionModel().getSelectedItem());
            Appointments.deleteSQLAppointment(idDelete);
            //Alerts user of Appointment ID and Type upon deletion
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Appointment Deletion");
            alert.setTitle("Information Box");
            alert.setContentText("Appointment: " + idDelete + "  " + "Type: " + typeDelete + "  has been deleted!");
            alert.showAndWait();
        }
    }
    /**Back Button Method.
     * Brings user back to Main Menu.
     */
    @FXML
    public void onActionBackToMainMenu (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.FXML"));
        stage.setScene(new Scene(scene));

        allAppointments.getItems().clear();
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
     * Initially ran when Appointment View is called.
     * Initializes TableView and populates it with appointments.
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        //Initializes toggle group with the default being all
        tableViewToggleGroup = new ToggleGroup();
        this.allAppRBtn.setToggleGroup(tableViewToggleGroup);
        this.monthAppRBtn.setToggleGroup(tableViewToggleGroup);
        this.weekAppRBtn.setToggleGroup((tableViewToggleGroup));

        //populate Appointments from SQL
        try {
            Appointments.sqlAppointments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // table view setup for all appointments and columns
        allAppointments.setItems(Appointments.getAllAppointments());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

    }

}
