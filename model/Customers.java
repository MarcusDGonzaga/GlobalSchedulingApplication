package model;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Customers Class
 * Creates, Updates, and Deletes Customers from the database.
 */
public class Customers {
    //Observable Array List to hold All Customers.
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int firstLvlDiv;
    private String postalCode;

    /**Customers Constructor.
     * @param id Customers ID.
     * @param name Customers Name.
     * @param address Customers Address.
     * @param postalCode Customers Postal Code.
     * @param phoneNumber Customers Phone Number.
     * @param firstLvlDiv Customers First Level Division.
     */
    public Customers(int id, String name, String address, String postalCode, String phoneNumber, int firstLvlDiv)
    {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.firstLvlDiv = firstLvlDiv;
        this.postalCode = postalCode;
    }

    /**SQL Customers Method.
     * Loads Customers from the Database.
     * @throws SQLException .
     */
    public static void sqlCustomers() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int custID = rs.getInt("Customer_ID");
            String custName = rs.getString("Customer_Name");
            String custAddress = rs.getString("Address");
            String custPostal = rs.getString("Postal_Code");
            String custPhone = rs.getString("Phone");
            int custDivID = rs.getInt("Division_ID");

            allCustomers.add(new Customers (custID, custName, custAddress, custPostal, custPhone, custDivID));
        }

    }

    /**Insert Customer Method.
     * Creates a New Customer into the Database.
     * @param name Customers Name.
     * @param address Customers Address.
     * @param postalCode Customers Postal Code.
     * @param phoneNumber Customers Phone Number.
     * @param firstLvlDiv Customers First Level Division.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int insertCustomer(String name, String address, String postalCode, String phoneNumber, int firstLvlDiv) throws SQLException
    {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, firstLvlDiv);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Update Customer Method.
     * Updates Customer Information in the Database.
     * @param iD Customer ID.
     * @param name Customer Name.
     * @param address Customer Address.
     * @param postalCode Customer Postal Code.
     * @param phoneNumber Customer Phone Number.
     * @param firstLvlDiv Customer First Level Division.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int updateCustomer(int iD, String name, String address, String postalCode, String phoneNumber, int firstLvlDiv) throws SQLException
    {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?  ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, firstLvlDiv);
        ps.setInt(6, iD);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Delete Customer Method.
     * Deletes Customer from TableView.
     * @param selectedCustomer Customers selected Customer.
     * @return allCustomers.remove(selectedCustomer).
     */
    public static boolean deleteCustomer(Customers selectedCustomer)
    {
        return allCustomers.remove(selectedCustomer);
    }

    /**Delete SQL Customer.
     * Deletes Customer from Database.
     * @param idToDelete Customers ID to Delete.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int deleteSQLCustomer(int idToDelete) throws SQLException
    {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, idToDelete);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Delete SQL Customer Appointments.
     * Deletes Customers Appointments upon deletion of the Customer.
     * @param idToDelete Customers ID to Delete.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int deleteSQLCustomerAppointments(int idToDelete) throws SQLException
    {
        String sql = "DELETE FROM APPOINTMENTS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, idToDelete);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Get ID Method.
     * Returns the ID of the Customer.
     * @return id.
     */
    public int getId()
    {
        return id;
    }

    /**Set ID Method.
     * Sets ID value.
     * @param id Customers ID.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**Get Name Method.
     * Returns the name of Customer.
     * @return name.
     */
    public String getName()
    {
        return name;
    }

    /**Set Name Method.
     * Sets the Name value.
     * @param name Customers Name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**Get Phone Number Method.
     * Returns the Customer Phone Number.
     * @return phoneNumber.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**Sets Phone Number Method.
     * Sets Phone Number Value.
     * @param phoneNumber Customers Phone Number.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**Get Address Method.
     * Returns the Customer Address.
     * @return address.
     */
    public String getAddress()
    {
        return address;
    }

    /**Set Address Method.
     * Sets Address Value.
     * @param address Customers Address.
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**Get First Level Division Method.
     * Returns the first level division of the customer.
     * @return firstLvlDiv.
     */
    public int getFirstLvlDiv()
    {
        return firstLvlDiv;
    }

    /**Set First Level Division Method.
     * Sets the Customer First Level Division Method.
     * @param firstLvlDiv Customers First Level Division.
     */
    public void setFirstLvlDiv(int firstLvlDiv)
    {
        this.firstLvlDiv = firstLvlDiv;
    }

    /**Get Postal Code Method.
     * Returns  the postal code of the Customer.
     * @return postalCode.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**Set Postal Code Method.
     * Sets the Postal Code Value.
     * @param postalCode Customers Postal Code.
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    /**Get All Customers Method.
     * Returns all customers in the Observable Array List.
     * @return allCustomers.
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        return allCustomers;
    }
}
