package helper;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Reports Filtered Class.
 * Methods for populating Reporting Form.
 */
public class ReportsFiltered {
    //Observable Array List to hold each filtered list
    private static ObservableList<ReportsFiltered> filteredTypeMonth = FXCollections.observableArrayList();
    private static ObservableList<ReportsFiltered> filteredContact = FXCollections.observableArrayList();
    private static ObservableList<ReportsFiltered> filteredLocMonth = FXCollections.observableArrayList();

    /** SQL Filter on Type and Month.
     * Filters appointments based on Type and Month.
     * @param filterType sqlFilterTypeMonth Filter Type.
     * @param filterMonth sqlFilterTypeMonth Filter Month.
     * @return count.
     * @throws SQLException .
     */
    public static int sqlFilterTypeMonth(String filterType, int filterMonth) throws SQLException
    {
        String sql = "SELECT * FROM APPOINTMENTS WHERE TYPE =? AND MONTH(Start) = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, filterType);
        ps.setInt(2,filterMonth);
        ResultSet rs = ps.executeQuery(); //2-D List

        int count = 0;

        while (rs.next()) // next moves cursor through result set
        {
            String typeName = rs.getString("Type");
            String startTime = rs.getString("start");

            count ++;

        }
        return count;
    }

    /**SQL Filter on Location and Month.
     * Filters appointments based on Location and Month
     * @param filterLocation sqlFilterLocMonth Filter Location.
     * @param filterMonth sqlFilterLocMonth Filter Month.
     * @return count.
     * @throws SQLException .
     */
    public static int sqlFilterLocMonth(String filterLocation, int filterMonth) throws SQLException
    {
        String sql = "SELECT * FROM APPOINTMENTS WHERE LOCATION =? AND MONTH(Start) = ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, filterLocation);
        ps.setInt(2,filterMonth);
        ResultSet rs = ps.executeQuery(); //2-D List

        int count = 0;

        while (rs.next()) // next moves cursor through result set
        {
            String locationName = rs.getString("Type");
            String startTime = rs.getString("start");

            count ++;

        }

        return count;
    }
}
