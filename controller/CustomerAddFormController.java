package controller;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import helper.CountryCB;
import helper.FirstLevelDivCB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**Customer Add Controller Class.
 * Add Customer Window that pops up from the Customer View Window.
 * After Pressing the Add Button. First Level Division disabled initially.
 * FUTURE ENHANCEMENT Better organization between ComboBoxes and Text Fields.
 */
public class CustomerAddFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private TextField customerID;
    @FXML private TextField customerFN;
    @FXML private TextField customerLN;
    @FXML private TextField phoneNumber;
    @FXML private TextField houseAddress;
    @FXML private TextField postalCode;
    @FXML private ComboBox<CountryCB> countryCombo;
    @FXML private ComboBox<FirstLevelDivCB> firstLevelCombo;
    int chosenCountry;

    /**Save Customer Button Method.
     * Save Customer information.
     */
    @FXML
    public void onActionSaveCustInfo (ActionEvent event) throws IOException, SQLException
    {
        try
        {
            String firstName = customerFN.getText();
            String lastName = customerLN.getText();
            String phone = phoneNumber.getText();
            String streetAdd = houseAddress.getText();
            String zip = postalCode.getText();

            FirstLevelDivCB selectDiv = firstLevelCombo.getValue();
            firstLevelCombo.setValue(selectDiv);
            int division =  selectDiv.getDivisionID();

            String fullName = firstName + " " + lastName;
            Customers.insertCustomer(fullName, streetAdd, zip, phone, division);

            //takes user back to customer view form
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/CustomerViewForm.FXML"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NumberFormatException e)
        {
            helper.Messages.warningPopUp("Please fill in all the provided boxes!", "Input Error");
        }
    }

    /**Country ComboBox Method.
     * Enables First Level Division ComboBox after Country is selected.
     */
    @FXML
    public void selectCountryCB (ActionEvent event)
    {
        CountryCB selectCountry = countryCombo.getValue();
        countryCombo.setValue(selectCountry);
        chosenCountry = selectCountry.getCountryID();

        //Populates First Level Division depending on the country that is chosen
        if(chosenCountry == 1)
        {
            firstLevelCombo.setDisable(false);

            ObservableList<FirstLevelDivCB> usaDiv = FirstLevelDivCB.getAllUSADivisions();
            firstLevelCombo.setItems(usaDiv);
            firstLevelCombo.getSelectionModel().selectFirst();

        }
        else if (chosenCountry == 2)
        {
            firstLevelCombo.setDisable(false);

            ObservableList<FirstLevelDivCB> ukDiv = FirstLevelDivCB.getAllUKDivisions();
            firstLevelCombo.setItems(ukDiv);
            firstLevelCombo.getSelectionModel().selectFirst();
        }
        else if (chosenCountry == 3)
        {
            firstLevelCombo.setDisable(false);

            ObservableList<FirstLevelDivCB> canadaDiv = FirstLevelDivCB.getAllCanadaDivisions();
            firstLevelCombo.setItems(canadaDiv);
            firstLevelCombo.getSelectionModel().selectFirst();
        }
    }

    /**Cancel Button Method.
     * Cancel button takes user back to the Customer View Window.
     */
    @FXML
    public void onActionCancelToMainMenu (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerViewForm.FXML"));
        stage.setScene(new Scene(scene));

    }

    /**Initialize Method.
     * Initially ran when Customer Add is called.
     * Initializes ComboBoxes. Disables customer ID.
     * Disables First Level ComboBox.
     */
    public void initialize (URL url, ResourceBundle rb){
        //Disable customer ID for auto generating the ID
        customerID.setDisable(true);
        //Disabled until country is chosen
        firstLevelCombo.setDisable(true);
        //Populates Country ComboBox
        ObservableList<CountryCB> countries = CountryCB.getAllCountries();
        countryCombo.setItems(countries);

    }
}
