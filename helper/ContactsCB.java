package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Contacts ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class ContactsCB {
    // Observable Array List for keeping contacts
    private static ObservableList<ContactsCB> allContacts = FXCollections.observableArrayList();
    // Contact ID
    private int id;
    //Contact Name
    private String name;

    /**Contacts Constructor.
     * @param id ContactsCB contact ID.
     * @param name ContactsCB contact name.
     */
    public ContactsCB(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**SQL Contacts Method.
     * Add Contacts to allContacts
     */
    public static void sqlContacts() throws SQLException
    {
        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            //add Contact to Observable Array List
            allContacts.add(new ContactsCB(contactID, contactName));
        }
    }

    // Automatically runs sqlContacts.
    static
    {
        try {
            sqlContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Get All Contacts Method.
     * Returns all contacts in the array.
     * @return allContacts.
     */
    public static ObservableList<ContactsCB>getAllContacts(){return allContacts;}

    /**Get Id Method.
     * Returns ID of contact.
     * @return id.
     */
    public int getId(){return id;}

    /**Set If Method.
     * Sets id value.
     * @param id .
     */
    public void setId(int id){ this.id = id;}

    /**Get Name Method.
     * Returns Contact Name.
     * @return name.
     */
    public String getName(){return name;}

    /**Set Name Method.
     * Sets name value.
     * @param name .
     */
    public void setName(String name){ this.name = name;}

    /** String toString Method.
     *  Converts Hex to String to show on the ComboBox
     * @return (string)
     */
    @Override
    public String toString()
    {

        return (Integer.toString(id) + " | " + name);
    }
}
