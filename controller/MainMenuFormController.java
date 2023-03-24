package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Appointments;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**Main Menu Controller Class.
 * Main Menu Window that pops up from the Log In Window.
 * Allows User to choose between Customers, Appointments, Reporting.
 */
public class MainMenuFormController implements Initializable {

    Stage stage;
    Parent scene;

    /**Customer Button Method.
     * Directs user to Customer View which includes a TableView.
     */
    @FXML
    public void onActionCustomersView (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerViewForm.FXML"));
        stage.setScene(new Scene(scene));
    }

    /**Appointments Button Method.
     * Directs user to Appointments View which includes a TableView.
     */
    @FXML
    public void onActionAppointmentsView (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AppointmentViewForm.FXML"));
        stage.setScene(new Scene(scene));
    }

    /**Reporting Button Method.
     * Directs user to Reporting View which includes a TableView.
     */
    @FXML
    public void onActionReportingsView( ActionEvent event)  throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ReportingForm.FXML"));
        stage.setScene(new Scene(scene));
    }

    /**Exit Button Method.
     * Exits the program when selected.
     */
    @FXML
    public void onActionExit (ActionEvent event) throws Exception
    {
        System.exit(0);
    }

    public void initialize (URL url, ResourceBundle rb) {}
}
