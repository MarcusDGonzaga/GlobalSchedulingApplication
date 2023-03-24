package helper;
/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Country ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class CountryCB {
    // Country ID
    private int countryID;
    //Country Name
    private String countryName;
    //Observable Array List for keeping all Countries
    private static ObservableList<CountryCB> allCountries = FXCollections.observableArrayList();

    /**Country Constructor.
     * @param countryID ContactsCB contact ID.
     * @param countryName ContactsCB contact name.
     */
    public CountryCB(int countryID, String countryName)
    {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**Set All Countries Method.
     * No Parameters.
     */
    public static void setAllCountries()
    {
        allCountries.add(new CountryCB(1, "U.S"));
        allCountries.add(new CountryCB(2, "UK"));
        allCountries.add(new CountryCB(3,"Canada"));
    }

    /**Get All Countries Method.
     * Returns all countries in the observable array list.
     * @return allCountries.
     */
    public static ObservableList<CountryCB> getAllCountries()
    {
        return allCountries;
    }

    /** String toString Method.
     * converts Hex to String to show on the ComboBox.
     * @return countryName.
     */
    @Override
    public String toString()
    {
        return (countryName);
    }

    /**Get Country ID Method.
     * Returns Country ID.
     * @return countryID.
     */
    public int getCountryID()
    {
        return countryID;
    }

    /**Set Country ID Method.
     * Sets countryID value.
     * @param countryID
     */
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }

    /**Get Country Name Method.
     * Returns name of the Country.
     * @return countryName.
     */
    public String getCountryName()
    {
        return countryName;
    }

    /**Set Country Name Method.
     * Sets countryName Value.
     * @param countryName
     */
    public void setCountryName( String countryName)
    {
        this.countryName = countryName;
    }

    // Automatically runs setAllCountries.
    static
    {
        setAllCountries();
    }
}
