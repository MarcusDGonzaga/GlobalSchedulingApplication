package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** First Level Division ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class FirstLevelDivCB {
    //First Level Division ID
    private int divisionID;
    //First Level Division Name
    private String divisionName;

    //Observable array list for keeping UK First Level Divisions
    private static ObservableList<FirstLevelDivCB> allUKDivisions = FXCollections.observableArrayList();
    //Observable array list for keeping Canada First Level Divisions
    private static ObservableList<FirstLevelDivCB> allCanadaDivisions = FXCollections.observableArrayList();
    //Observable array list for keeping USA First Level Divisions
    private static ObservableList<FirstLevelDivCB> allUSADivisions = FXCollections.observableArrayList();

    /** First Level Division ComboBox Constructor
     * @param divisionID FirstLevelDivCB Division ID.
     * @param divisionName FirstLevelDivCB Division Name.
     */
    public FirstLevelDivCB(int divisionID, String divisionName)
    {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
    }

    /**Get Division ID Method.
     * Returns Division ID.
     * @return divisionID.
     */
    public int getDivisionID()
    {
        return divisionID;
    }

    /**Get Division Name Method.
     * Returns Division Name.
     * @return divisionName.
     */
    public String getDivisionName(){ return divisionName;}

    /**Set UK Division Method.
     * Sets UK Divisions for ComboBoxes.
     */
    public static void setAllUKDivisions()
    {
        allUKDivisions.add(new FirstLevelDivCB(101, "England"));
        allUKDivisions.add(new FirstLevelDivCB(102, "Wales"));
        allUKDivisions.add(new FirstLevelDivCB(103, "Scotland"));
        allUKDivisions.add(new FirstLevelDivCB(104, "Northern Ireland"));
    }

    /**Set Canada Division Method.
     * Sets Canada Divisions for ComboBoxes.
     */
    public static void setAllCanadaDivisions()
    {
        allCanadaDivisions.add(new FirstLevelDivCB(60, "Northwest Territories"));
        allCanadaDivisions.add(new FirstLevelDivCB(61, "Alberta"));
        allCanadaDivisions.add(new FirstLevelDivCB(62, "British Columbia"));
        allCanadaDivisions.add(new FirstLevelDivCB(63, "Manitoba"));
        allCanadaDivisions.add(new FirstLevelDivCB(64, "New Brunswick"));
        allCanadaDivisions.add(new FirstLevelDivCB(65, "Nova Scotia"));
        allCanadaDivisions.add(new FirstLevelDivCB(66, "Prince Edward Island"));
        allCanadaDivisions.add(new FirstLevelDivCB(67, "Ontario"));
        allCanadaDivisions.add(new FirstLevelDivCB(68, "Quebec"));
        allCanadaDivisions.add(new FirstLevelDivCB(69, "Saskatchewan"));
        allCanadaDivisions.add(new FirstLevelDivCB(70, "Nunavut"));
        allCanadaDivisions.add(new FirstLevelDivCB(71, "Yukon"));
        allCanadaDivisions.add(new FirstLevelDivCB(72, "Newfoundland and Labrador"));
    }

    /**Set USA Division Method.
     * Sets USA Divisions for ComboBoxes.
     */
    public static void setAllUSADivisions()
    {

        allUSADivisions.add(new FirstLevelDivCB(1, "Alabama"));
        allUSADivisions.add(new FirstLevelDivCB(2, "Arizona"));
        allUSADivisions.add(new FirstLevelDivCB(3, "Arkansas"));
        allUSADivisions.add(new FirstLevelDivCB(4, "California"));
        allUSADivisions.add(new FirstLevelDivCB(5, "Colorado"));
        allUSADivisions.add(new FirstLevelDivCB(6, "Connecticut"));
        allUSADivisions.add(new FirstLevelDivCB(7, "Delaware"));
        allUSADivisions.add(new FirstLevelDivCB(8, "District of Columbia"));
        allUSADivisions.add(new FirstLevelDivCB(9, "Florida"));
        allUSADivisions.add(new FirstLevelDivCB(10, "Georgia"));
        allUSADivisions.add(new FirstLevelDivCB(11, "Idaho"));
        allUSADivisions.add(new FirstLevelDivCB(12, "Illinois"));
        allUSADivisions.add(new FirstLevelDivCB(13, "Indiana"));
        allUSADivisions.add(new FirstLevelDivCB(14, "Iowa"));
        allUSADivisions.add(new FirstLevelDivCB(15, "Kansas"));
        allUSADivisions.add(new FirstLevelDivCB(16, "Kentucky"));
        allUSADivisions.add(new FirstLevelDivCB(17, "Louisiana"));
        allUSADivisions.add(new FirstLevelDivCB(18, "Maine"));
        allUSADivisions.add(new FirstLevelDivCB(19, "Maryland"));
        allUSADivisions.add(new FirstLevelDivCB(20, "Massachusetts"));
        allUSADivisions.add(new FirstLevelDivCB(21, "Michigan"));
        allUSADivisions.add(new FirstLevelDivCB(22, "Minnesota"));
        allUSADivisions.add(new FirstLevelDivCB(23, "Mississippi"));
        allUSADivisions.add(new FirstLevelDivCB(24, "Missouri"));
        allUSADivisions.add(new FirstLevelDivCB(25, "Montana"));
        allUSADivisions.add(new FirstLevelDivCB(26, "Nebraska"));
        allUSADivisions.add(new FirstLevelDivCB(27, "Nevada"));
        allUSADivisions.add(new FirstLevelDivCB(28, "New Hampshire"));
        allUSADivisions.add(new FirstLevelDivCB(29, "New Jersey"));
        allUSADivisions.add(new FirstLevelDivCB(30, "New Mexico"));
        allUSADivisions.add(new FirstLevelDivCB(31, "New York"));
        allUSADivisions.add(new FirstLevelDivCB(32, "North Carolina"));
        allUSADivisions.add(new FirstLevelDivCB(33, "North Dakota"));
        allUSADivisions.add(new FirstLevelDivCB(34, "Ohio"));
        allUSADivisions.add(new FirstLevelDivCB(35, "Oklahoma"));
        allUSADivisions.add(new FirstLevelDivCB(36, "Oregon"));
        allUSADivisions.add(new FirstLevelDivCB(37, "Pennsylvania"));
        allUSADivisions.add(new FirstLevelDivCB(38, "Rhode Island"));
        allUSADivisions.add(new FirstLevelDivCB(39, "South Carolina"));
        allUSADivisions.add(new FirstLevelDivCB(40, "South Dakota"));
        allUSADivisions.add(new FirstLevelDivCB(41, "Tennessee"));
        allUSADivisions.add(new FirstLevelDivCB(42, "Texas"));
        allUSADivisions.add(new FirstLevelDivCB(43, "Utah"));
        allUSADivisions.add(new FirstLevelDivCB(44, "Vermont"));
        allUSADivisions.add(new FirstLevelDivCB(45, "Virginia"));
        allUSADivisions.add(new FirstLevelDivCB(46, "Washington"));
        allUSADivisions.add(new FirstLevelDivCB(47, "West Virginia"));
        allUSADivisions.add(new FirstLevelDivCB(48, "Wisconsin"));
        allUSADivisions.add(new FirstLevelDivCB(49, "Wyoming"));
        allUSADivisions.add(new FirstLevelDivCB(52, "Hawaii"));
        allUSADivisions.add(new FirstLevelDivCB(54, "Alaska"));
    }

    /**Get UK Divisions Method.
     * Returns all UK divisions.
     * @return allUKDivisions.
     */
    public static ObservableList<FirstLevelDivCB> getAllUKDivisions()
    {
        return allUKDivisions;
    }

    /**Get Canada Divisions Method.
     * Returns all Canada divisions.
     * @return allCanadaDivisions.
     */
    public static ObservableList<FirstLevelDivCB> getAllCanadaDivisions()
    {
        return  allCanadaDivisions;
    }

    /**Get USA Divisions Method.
     * Returns all USA divisions.
     * @return allUSADivisions.
     */
    public static ObservableList<FirstLevelDivCB> getAllUSADivisions()
    {
        return allUSADivisions;
    }

    //automatically sets all divisions for combo box
    static
    {
        setAllCanadaDivisions();
        setAllUKDivisions();
        setAllUSADivisions();
    }

    /** String toString Method.
     *  Converts Hex to String to show on the ComboBox
     * @return (string)
     */
    @Override
    public String toString()
    {
        return (divisionName);
    }
}
