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
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**Customer View Controller Class.
 * Customer View Window that pops up from the Main Menu Window.
 * After Pressing the Customer Button. Populated by all Customers.
 */
public class CustomerViewFormController implements Initializable {

    Stage stage;
    Parent scene;

    //TableView of Customers
    @FXML
    private TableView<Customers> allCustomers;
    @FXML
    private TableColumn<Customers, Integer> allCustIDCol;
    @FXML
    private TableColumn<Customers, String> allCustNameCol;
    @FXML
    private TableColumn<Customers, String> allCustPhoneCol;
    @FXML
    private TableColumn<Customers, String> allCustAddressCol;
    @FXML
    private TableColumn<Customers, String> allCustFLvlCol;
    @FXML
    private TableColumn<Customers, String> allCustPostalCol;

    /**Add Customer Button Method.
     * Directs user to Customer Add View.
     */
    @FXML
    public void onActionAddCustomer (ActionEvent event) throws IOException
    {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/CustomerAddForm.FXML"));
        stage.setScene(new Scene(scene));

        allCustomers.getItems().clear();
    }

    /**Update Customer Button Method.
     * Directs user to Customer Update View.
     * Information is populated with selected customer.
     */
    @FXML
    public void onActionUpdateCustomer (ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource( "/view/CustomerUpdateForm.FXML"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene (tableViewParent);
        // Transfers customer information to Customer Update Window
        CustomerUpdateFormController controller = loader.getController();
        controller.customerSelected(allCustomers.getSelectionModel().getSelectedItem());

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

        allCustomers.getItems().clear();
    }

    /**Delete Customer Button Method.
     * Deletes customer from MySQL.
     * Information is populated with selected customer.
     * Any appointments associated with that customer will also be deleted.
     */
    @FXML
    public void onActionDeleteCustomer (ActionEvent event) throws IOException, SQLException
    {
        boolean confirmOk = false;
        //Confirmation Alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Customer Deletion");
        alert.setContentText("Ok to delete Customer?");
        Optional<ButtonType> result = alert.showAndWait();

        //Customer Information will only be deleted if confirmed with the press of "OK"
        if(result.get() == ButtonType.OK)
        {
            confirmOk = true;

            int idDelete = allCustomers.getSelectionModel().getSelectedItem().getId();
            Customers.deleteCustomer(allCustomers.getSelectionModel().getSelectedItem());
            //Deletes customer appointments through Customer ID
            Customers.deleteSQLCustomerAppointments(idDelete);
            Customers.deleteSQLCustomer(idDelete);

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Customer Deletion");
            alert.setTitle("Information Box");
            alert.setContentText("Customer has been deleted!");
            alert.showAndWait();
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

        allCustomers.getItems().clear();
    }

    /**Exit Button Method.
     * Exits the program when selected.
     */
    @FXML
    public void onActionExit (ActionEvent event) throws Exception
    {
        allCustomers.getItems().clear();

        System.exit(0);
    }

    /**Initialize Method.
     * Initially ran when Customer View is called.
     * Initializes Table View and is populated with all Customers.
     */
    public void initialize (URL url, ResourceBundle rb)
    {
        //populate Customers from SQL
        try {
            Customers.sqlCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //table view setup for Customers
       allCustomers.setItems(Customers.getAllCustomers());
       allCustIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       allCustNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       allCustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
       allCustAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
       allCustFLvlCol.setCellValueFactory(new PropertyValueFactory<>("firstLvlDiv"));
       allCustPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
    }
}
