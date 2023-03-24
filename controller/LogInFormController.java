package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import LambdaEx.inputErrorInterface;
import helper.Messages;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Appointments;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;

/**Log In Controller Class.
 * Initial window asking for Log In credentials.
 * English or French Text depending on user's computer language settings.
 * Checks for appointments in the next 15 minutes.
 * Writes into a .txt file on success or failure of Log In attempt.
 */
public class LogInFormController implements Initializable {

    Stage stage;
    Parent scene;

    private boolean languageEnglish= false;

    @FXML private TextField userIDInput;
    @FXML private TextField passwordInput;
    @FXML private Label zoneID;
    @FXML private Label logInLabel;
    @FXML private Label userIDLabel;
    @FXML private Label passwordLabel;
    @FXML private Label locationLabel;
    @FXML private Button enterButton;
    @FXML private Button exitButton;

    /**Language Display Method.
     * Displays English or French text depending on
     * default computer language settings.
     */
    public void languageDisplay()
    {
        if (Locale.getDefault().getLanguage().equals("fr")) {
            logInLabel.setText("Formulaire de Connexion");
            userIDLabel.setText("Identification de L'utilisateur:");
            passwordLabel.setText("Le Mot de Passe:");
            locationLabel.setText("Emplacement:");
            enterButton.setText("Entrer");
            exitButton.setText("Sortir");
            languageEnglish = false;
        }
        else
        {
            logInLabel.setText("Log-In Form");
            userIDLabel.setText("User ID:");
            passwordLabel.setText("Password:");
            locationLabel.setText("Location:");
            enterButton.setText("Enter");
            exitButton.setText("Exit");
            languageEnglish = true;
        }
    }

    /**Input Data Method.
     * Evaluates User Log In inputs.
     * Logs Log In attempts and Input Validation if wrong
     * User ID or Password.
     * Lambda Expression used pass through a String for output.
     */
    public void onActionEnterInputData (ActionEvent event) throws IOException
    {
        String userID = userIDInput.getText();
        String password = passwordInput.getText();
        // Setting up file to written to
        String activityFile = "login_activity.txt", logIn;
        FileWriter fwriter = new FileWriter(activityFile, true);
        PrintWriter outputFile = new PrintWriter(fwriter);

        //user and password input verified
        if (userID.equals("test") && password.equals("test"))
        {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenuForm.FXML"));
            stage.setScene(new Scene(scene));
            // getting current time and date when logging in
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            LocalDateTime startDateTime = LocalDateTime.of(currentDate, currentTime);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startLDT = dtf.format(startDateTime);
            //String writttin to .txt file recording log in attempt successful
            logIn = ("Log in by: " + userID + " | attempted at: " + startLDT + " | was successful! \n");
            outputFile.println(logIn);
            outputFile.close();
            //call function to check for appointments within the next 15 minutes
            try {
                appointmentCheck();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //Input Validation if either User ID or Password is incorrect
        else if (!userID.equals("sqlUser") || !password.equals("Passw0rd!"))
        {
            //Warning message in French based on computer language settings
           if(languageEnglish == false)
           {
               Messages.warningPopUp("S'il vous plaît retaper Identification de L'utilisateur et Le Mot de Passe ", "Mauvais Identification de L'utilisateur ou Le Mot de Passe!");
           }
           else
           {
               //String value returning Lambda Expression allows a message to be shown.
               inputErrorInterface error = e -> e;
               Messages.warningPopUp(error.inputWarning("Please reenter Username and Password"), "Incorrect Username or Password!");
           }
            //grabbing the time and date when attempting to log in
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            LocalDateTime startDateTime = LocalDateTime.of(currentDate, currentTime);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String startLDT = dtf.format(startDateTime);
            //String writttin to .txt file recording log in attempt NOT successful
            logIn = ("Log in by: " + userID + " | attempted at: " + startLDT + " | was NOT successful! \n");
            outputFile.println(logIn);
            outputFile.close();
        }
    }

    /**User Timezone Method.
     * Zone ID of user is automatically displayed.
     */
    public void userTimeZone ()
    {
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        zoneID.setText(localZoneId.toString());
    }

    /**Appointment Check Method.
     * Checks if any appointments within the next 15 minutes.
     */
    public void appointmentCheck() throws SQLException
    {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        Appointments.sql15MinAppointmentsCheck(currentTime, currentDate);
    }

    /**Exit Button Method.
     * Exits the program when selected.
     */
    public void onActionExitLogIn (ActionEvent event) throws Exception
    {
        System.exit(0);
    }

    /**Initialize Method.
     * Initially ran when program is executed.
     * Initializes language to be displayed.
     * Calls for method to diplay user Zone ID.
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        languageDisplay();
        userTimeZone();
    }
}
