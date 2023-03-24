package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Users ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class UsersCB {
    //Observable Array List of All Users
    private static ObservableList<UsersCB> allUsers = FXCollections.observableArrayList();
    //User ID
    private int id;
    //User Name
    private String name;

    /**Users Constructor
     * @param id UsersCB id.
     * @param name UsersCB name.
     */
    public UsersCB(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**sqlUsers Method.
     * @throws SQLException .
     */
    public static void sqlUsers() throws SQLException {
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");

            allUsers.add(new UsersCB(userID, userName));
        }
    }

    //Automattical runs sqlUsers
    static
    {
        try {
            sqlUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Get All Users Method.
     * Returns all Users in the Observable Array List.
     * @return all Users.
     */
    public static ObservableList<UsersCB> getAllUsers(){return allUsers;}

    /**Get ID Method.
     * Returns ID of the User.
     * @return id.
     */
    public int getId(){return id;}

    /**Set ID Method.
     * Sets the ID of User.
     * @param id UsersCB id.
     */
    public void setId(int id){ this.id = id;}

    /**Get Name Method.
     * Returns the name of the User.
     * @return name.
     */
    public String getName(){return name;}

    /**Set Name Method.
     * Sets the name value.
     * @param name UsersCB name.
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
