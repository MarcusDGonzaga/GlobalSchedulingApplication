package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import LambdaEx.apptConflictInterface;
import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**Appointment Add Controller Class.
 * Add Appointment Window that pops up from the Appointment View Window.
 * After Pressing the Add Button.
 * FUTURE ENHANCEMENT Better organization between ComboBoxes, Date Picker, and Text Fields.
 */
public class AppointmentAddFormController implements Initializable {

    Stage stage;
    Parent scene;
    //Adding and choosing Appointment information
    @FXML private TextField appointmentID;
    @FXML private TextField appointmentTitle;
    @FXML private TextField appointmentDescription;
    @FXML private TextField appointmentLocation;
    @FXML private ComboBox<TypeCB> apptTypeCB;
    @FXML private ComboBox<ContactsCB> appointmentContactCB;
    @FXML private ComboBox<CustomersCB> customerIDCB;
    @FXML private ComboBox<UsersCB> userIDCB;
    @FXML private DatePicker startDate;
    @FXML private ComboBox<LocalTime> startTime;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<LocalTime> endTime;

    /**Save Appointment Button Method.
     * Save Appointment information.
     * Input validation of overlapping appointments.
     * RUNTIME ERROR Dateformatter had wrong "yyyy-MM-dd HH:mm:ss".
     * I adjusted placement.
     * Lambda Expression used for quick customized message.
     */
    @FXML
    public void onActionSaveAppointment (ActionEvent event) throws IOException, SQLException {
        try {
            // Get information from user
            String apptTitle = appointmentTitle.getText();
            String appDesc = appointmentDescription.getText();
            String appLoc = appointmentLocation.getText();

            TypeCB selectType = apptTypeCB.getValue();
            String type = selectType.getName();

            CustomersCB selectCustomer = customerIDCB.getValue();
            int custID = selectCustomer.getId();

            UsersCB selectUser = userIDCB.getValue();
            int userID = selectUser.getId();

            ContactsCB selectContact = appointmentContactCB.getValue();
            int contactID = selectContact.getId();

            //Saving User Input of DATE and TIMES
            LocalDate sDate = startDate.getValue();
            LocalDate eDate = endDate.getValue();
            LocalTime sTime = startTime.getValue();
            LocalTime eTime = endTime.getValue();
            //Saving info at LOCAL DATE and TIME to UTC
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            ZoneId UTCZoneId = ZoneId.of("Etc/UTC");
            //Input Validation for EST Business Hours
            ZoneId ESTZoneId = ZoneId.of("US/Eastern");
            //convert to ZonedDateTime at LOCAL first then to UTC FOR SAVING into SQL
            ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, localZoneId);
            ZonedDateTime endLocalZDT = ZonedDateTime.of(eDate, eTime, localZoneId);

            // conversion of time for input validation
            startLocalZDT = startLocalZDT.withZoneSameInstant(ESTZoneId);
            endLocalZDT = endLocalZDT.withZoneSameInstant(ESTZoneId);
            LocalTime startCheck = startLocalZDT.toLocalTime();
            LocalTime endCheck = endLocalZDT.toLocalTime();
            LocalTime startBusinessEST = LocalTime.of(8,00);
            LocalTime endBusinessEST = LocalTime.of(22,0);

            startLocalZDT = startLocalZDT.withZoneSameInstant(UTCZoneId);
            endLocalZDT = endLocalZDT.withZoneSameInstant(UTCZoneId);
            //Break down ZDT
            LocalDate startZoneDate = startLocalZDT.toLocalDate();
            LocalTime startZoneTime = startLocalZDT.toLocalTime();
            LocalDate endZoneDate = endLocalZDT.toLocalDate();
            LocalTime endZoneTime = endLocalZDT.toLocalTime();
            //Create LDT from ZDT to save into SQL
            LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
            LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);

            //input validation start time is before end time
            if(endLocalZDT.isBefore(startLocalZDT))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Appointment Conflict");
                alert.setTitle("Choose another Date and Time");
                // No parameter Lambda Expression allows quick way to output a customized message.
                apptConflictInterface conflict = () -> alert.setContentText("End time and date cannot be before Start time and date!");
                conflict.displayMessage();

                alert.showAndWait();
            }
            else if (startCheck.isBefore(startBusinessEST) || endCheck.isAfter(endBusinessEST))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Appointment Conflict");
                alert.setTitle("Choose another Date and Time");
                // No parameter Lambda Expression allows quick way to output a customized message.
                apptConflictInterface conflict = () -> alert.setContentText("Appointment time must be during business hours!");
                conflict.displayMessage();

                alert.showAndWait();
            }
            // Overlap between appointments
            else if (Appointments.sqlOverlapAppointmentsCheck(startDateTime, endDateTime) == true)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Appointment Conflict");
                alert.setTitle("Choose another Date and Time");
                // No parameter Lambda Expression allows quick way to output a customized message.
                apptConflictInterface conflict = () -> alert.setContentText("Appointment time and date selected overlaps with another appointment!");
                conflict.displayMessage();

                alert.showAndWait();
            }
            else
            {
                //Convert LDT to STRING to save into SQL
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String startLDT = dtf.format(startDateTime);
                String endLDT = dtf.format(endDateTime);

                Appointments.insertAppointment(apptTitle, appDesc, appLoc, type, startLDT, endLDT, custID, userID, contactID);

                //takes user back to appointment view form
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/AppointmentViewForm.FXML"));
                stage.setScene(new Scene(scene));
            }

            /*
            // Check appointments are during business hours EST
            else if (startCheck.isBefore(startBusinessEST) || endCheck.isAfter(endBusinessEST))
            {
                startLocalZDT = startLocalZDT.withZoneSameInstant(UTCZoneId);
                endLocalZDT = endLocalZDT.withZoneSameInstant(UTCZoneId);
                //Break down ZDT
                LocalDate startZoneDate = startLocalZDT.toLocalDate();
                LocalTime startZoneTime = startLocalZDT.toLocalTime();
                LocalDate endZoneDate = endLocalZDT.toLocalDate();
                LocalTime endZoneTime = endLocalZDT.toLocalTime();
                //Create LDT from ZDT to save into SQL
                LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
                LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);

                //Check for overlapping appointments with Lambda Expression
                // No overlap between appointments
            if(Appointments.sqlOverlapAppointmentsCheck(startDateTime, endDateTime) == false)
                {
                    //Convert LDT to STRING to save into SQL
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String startLDT = dtf.format(startDateTime);
                    String endLDT = dtf.format(endDateTime);

                    Appointments.insertAppointment(apptTitle, appDesc, appLoc, type, startLDT, endLDT, custID, userID, contactID);

                    //takes user back to appointment view form
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/AppointmentViewForm.FXML"));
                    stage.setScene(new Scene(scene));
                }
                // Overlap between appointments
                else if (Appointments.sqlOverlapAppointmentsCheck(startDateTime, endDateTime) == true)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Appointment Conflict");
                    alert.setTitle("Choose another Date and Time");
                    // No parameter Lambda Expression allows quick way to output a customized message.
                    apptConflictInterface conflict = () -> alert.setContentText("Appointment time and date selected overlaps with another appointment!");
                    conflict.displayMessage();

                    alert.showAndWait();
                }
            }
            else
            {
                helper.Messages.warningPopUp("Please choose a time between 8am and 10pm EST!", "Input Error");
            }

             */
        }
        catch (NumberFormatException e)
            {
                helper.Messages.warningPopUp("Please fill in all the provided boxes!", "Input Error");
            }
    }

    /**Cancel Button Method.
     * Cancel button takes user back to the Appointment View Window.
     */
    @FXML
    public void onActionCancelToApptView (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentViewForm.FXML"));
        stage.setScene(new Scene(scene));
    }

    /**Initialize Method.
     * Initially ran when Appointment Add is called.
     * Initializes ComboBoxes and DatePicker.
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        //Appointment ID is automatically created in MySQL
        appointmentID.setDisable(true);

        ObservableList<ContactsCB> contacts = ContactsCB.getAllContacts();
        appointmentContactCB.setItems(contacts);

        ObservableList<UsersCB> users = UsersCB.getAllUsers();
        userIDCB.setItems(users);

        ObservableList<CustomersCB> customers = CustomersCB.getAllCustomers();
        customerIDCB.setItems(customers);

        ObservableList<TypeCB> types = TypeCB.getAllTypes();
        apptTypeCB.setItems(types);


        LocalTime start = LocalTime.of(1,0);
        LocalTime end = LocalTime.of(23,0);

        while(start.isBefore(end.plusSeconds(1))){
            startTime.getItems().add(start);
            endTime.getItems().add(start);
            start = start.plusMinutes(30);
        }

    }


}
