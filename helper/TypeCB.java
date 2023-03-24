package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Type ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class TypeCB {
    //Observable Array List for holding all Types.
    private static ObservableList<TypeCB> allTypes = FXCollections.observableArrayList();
    //Type name
    private String type;

    /**Type Constructor.
     * @param type TypeCB Type name.
     */
    public TypeCB(String type)
    {
        this.type = type;
    }

    /**SQL Types Method.
     * @throws SQLException .
     */
    public static void sqlTypes() throws SQLException {
        String sql = "SELECT DISTINCT TYPE FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            String typeName = rs.getString("Type");

            allTypes.add(new TypeCB(typeName));
        }
    }

    //automatically runs sqlTypes.
    static
    {
        try {
            sqlTypes();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**Get All Types Method.
     * Returns all Types.
     * @return allTypes.
     */
    public static ObservableList<TypeCB> getAllTypes(){return allTypes;}

    /** Get Name Method.
     * Returns name of Type.
     * @return type.
     */
    public String getName(){return type;}

    /**Set Name Method.
     * Sets the name value of Type.
     * @param type TypeCB type.
     */
    public void setName(String type){ this.type = type;}

    /** String toString Method.
     *  Converts Hex to String to show on the ComboBox
     * @return (string)
     */
    @Override
    public String toString()
    {
        return (type);
    }
}
