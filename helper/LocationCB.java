package helper;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.stream.Location;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Location ComboBox Class.
 *  Used for populating Location ComboBoxes.
 */
public class LocationCB {

    //Observable array list to hold All Locations.
    private static ObservableList<LocationCB> allLocations = FXCollections.observableArrayList();
    //Location name
    private String location;

    /**Location Constructor
     * @param type Location type.
     */
    public LocationCB(String type)
    {
        this.location = type;
    }

    /**SQL Location Method.
     * Adds Locations in database to array list.
     * @throws SQLException .
     */
    public static void sqlLocation() throws SQLException {
        String sql = "SELECT DISTINCT LOCATION FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            String locName = rs.getString("Location");

            allLocations.add(new LocationCB(locName));
        }
    }

    /**Get All Locations Method.
     * Returns all Locations in the database.
     * @return allLocations.
     */
    public static ObservableList<LocationCB> getAllLocations() { return allLocations;}

    /**Set All Locations Method.
     * Sets all Locations into allLocations.
     * @param allLocations .
     */
    public static void setAllLocations(ObservableList<LocationCB> allLocations) { LocationCB.allLocations = allLocations;}

    /**Get Location Method.
     * Returns the location.
     * @return location.
     */
    public String getLocation() { return location; }

    /**Set Location Method.
     * Sets location.
     * @param location .
     */
    public void setLocation(String location) { this.location = location; }

    /** String toString Method.
     *  Converts Hex to String to show on the ComboBox
     * @return (string)
     */
    @Override
    public String toString()
    {
        return (location);
    }

    static
    {
        try {
            sqlLocation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
