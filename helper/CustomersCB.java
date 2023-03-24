package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Customers ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class CustomersCB {
    // Observable Array List for keeping Customers
    private static ObservableList<CustomersCB> allCustomers = FXCollections.observableArrayList();
    //Customer ID
    private int id;
    //Customer Name
    private String name;

    /**Customer Constructor.
     * @param id CustomersCB Customer id.
     * @param name CustomersCB Customer name.
     */
    public CustomersCB ( int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /** SQL Customers Method.
     * Takes customer from MySQL and put into allCustomer.
     * @throws SQLException .
     */
    public static void sqlCustomersCB() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");

            allCustomers.add(new CustomersCB(customerID, customerName));
        }
    }

    //Automatically runs sqlCustomersCB.
    static
    {
        try {
            sqlCustomersCB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Get All Customers Method.
     * Returns all customers in the observable array
     * @return allCustomers.
     */
    public static ObservableList<CustomersCB> getAllCustomers(){return allCustomers;}

    /**Get ID Method.
     * Returns the Customer ID.
     * @return id.
     */
    public int getId(){return id;}

    /**Set ID Method.
     * Sets ID value
     * @param id Customer ID.
     */
    public void setId(int id){ this.id = id;}

    /**Get Customer Name Method.
     * Returns Customer Name.
     * @return name Customer Name.
     */
    public String getName(){return name;}

    /**Set Customer Name Method.
     * Sets Name value.
     * @param name Customer Name.
     */
    public void setName(String name){ this.name = name;}

    /** String toString Method.
     *  Converts Hex to String to show on the ComboBox
     * @return (string)
     */
    @Override
    public String toString()
    {

        return (Integer.toString(id) + " " + name);
    }
}
