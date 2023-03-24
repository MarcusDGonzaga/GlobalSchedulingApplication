package main;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/** Main Class.
 *  Class initially for setting up the program and connecting to the database.
 */
public class Main extends Application {

    /** Start Method.
     * Loads Main window as well as setting up the Title and Scene Size.
     * @param stage .
     * @throws Exception .
     */
    @Override
    public void start (Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource( "/view/LogInForm.fxml"));
        stage.setTitle("Gonzaga Scheduling Desktop Application");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**Main Method.
     * Opening and closing the connection to the database.
     * @param args .
     */
    public static void main(String[] args)
    {

        JDBC.openConnection();

        //Test for French
        //Locale.setDefault(new Locale("fr"));
        //Test EST
        //Locale.setDefault(new Locale("EST"));

        launch (args);

        //DBCountries.checkDataConversion();
        JDBC.closeConnection();
    }
}
