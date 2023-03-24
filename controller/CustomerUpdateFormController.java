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

/**Customer Update Controller Class.
 * Update Customer Window that pops up from the Customer View Window.
 * After Pressing the Update Button. Information is prepopulated.
 */
public class CustomerUpdateFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML private TextField customerID;
    @FXML private TextField customerName;
    @FXML private TextField phoneNumber;
    @FXML private TextField houseAddress;
    @FXML private TextField postalCode;
    @FXML private ComboBox<CountryCB> countryCombo;
    @FXML private ComboBox<FirstLevelDivCB> firstLevelCombo;

    Customers selectedCustomer;
    private static int custUpdateIndex;
    int chosenCountry;

    /**Customer Selected Method.
     * Populates Selected Customer information from TableView.
     */
    public void customerSelected(Customers customers)
    {
        selectedCustomer = customers;

        custUpdateIndex = Customers.getAllCustomers().indexOf(selectedCustomer);
        customerID.setText(Integer.toString(selectedCustomer.getId()));
        customerName.setText(selectedCustomer.getName());
        phoneNumber.setText(selectedCustomer.getPhoneNumber());
        houseAddress.setText(selectedCustomer.getAddress());
        postalCode.setText(selectedCustomer.getPostalCode());

        //if else statement for setting first level div combo box
        //selectionmodel to flvDiv
        //if else statement for countries based on flvdiv
        int fLvlDiv = selectedCustomer.getFirstLvlDiv();

        if(fLvlDiv < 55)
        {
            ObservableList<CountryCB> countries = CountryCB.getAllCountries();
            countryCombo.setItems(countries);
            countryCombo.getSelectionModel().select(0);

            ObservableList<FirstLevelDivCB> usaDiv = FirstLevelDivCB.getAllUSADivisions();
            firstLevelCombo.setItems(usaDiv);
            firstLevelCombo.getSelectionModel().select(fLvlDiv - 1);
        }
        else if( fLvlDiv > 59 && fLvlDiv < 73)
        {
            ObservableList<CountryCB> countries = CountryCB.getAllCountries();
            countryCombo.setItems(countries);
            countryCombo.getSelectionModel().select(2);

            ObservableList<FirstLevelDivCB> canadaDiv = FirstLevelDivCB.getAllCanadaDivisions();
            firstLevelCombo.setItems(canadaDiv);
            firstLevelCombo.getSelectionModel().select(fLvlDiv - 60);
        }
       else if( fLvlDiv > 100)
       {
           ObservableList<CountryCB> countries = CountryCB.getAllCountries();
           countryCombo.setItems(countries);
           countryCombo.getSelectionModel().select(1);

            ObservableList<FirstLevelDivCB> ukDiv = FirstLevelDivCB.getAllUKDivisions();
            firstLevelCombo.setItems(ukDiv);
            firstLevelCombo.getSelectionModel().select(fLvlDiv - 101);
        }
    }

    /**Save Customer Button Method.
     * Updates Customer information to MySQL.
     */
    @FXML
    public void onActionSaveCustInfo (ActionEvent event) throws IOException
    {
        try {
            int iD = Integer.parseInt(customerID.getText());
            String custName = customerName.getText();
            String phoneNum = phoneNumber.getText();
            String houseAdd = houseAddress.getText();
            String postal = postalCode.getText();

            FirstLevelDivCB selectDiv = firstLevelCombo.getValue();
            firstLevelCombo.setValue(selectDiv);
            int division =  selectDiv.getDivisionID();

            Customers.updateCustomer(iD, custName, houseAdd, postal, phoneNum, division);
        }
        catch (NumberFormatException e)
        {
            helper.Messages.warningPopUp("Please fill in all the provided boxes!", "Input Error");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerViewForm.FXML"));
        stage.setScene(new Scene(scene));
    }

    /**Select Country Method.
     * Changes First Level Division ComboBox depending
     * on the chosen Country.
     */
    @FXML
    public void onActionSelectCountryCB (ActionEvent event)
    {
        CountryCB selectCountry = countryCombo.getValue();
        countryCombo.setValue(selectCountry);
        chosenCountry = selectCountry.getCountryID();
        // if else for populating First Level Division ComboBox
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
     * Brings user back to the Main Menu View when clicked.
     */
    @FXML
    public void onActionCancelToMainMenu (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerViewForm.FXML"));
        stage.setScene(new Scene(scene));

    }

    /**Initialize Method.
     * Initially ran when Customer Update is called.
     * Disables Customer ID. Taken care of by MySQL.
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        //Disable customer ID for auto generating the ID
        customerID.setDisable(true);

    }
}
