package helper;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**Month ComboBox Class.
 * Used for populating ComboBoxes.
 */
public class MonthCB {
    //Observable Array List to hold all Months
    private static ObservableList<MonthCB> allMonths = FXCollections.observableArrayList();
    //Month Name variable
    private String monthName;
    //Month Number variable
    private int monthNum;

    /** Month Constructor.
     * @param monthNum MonthCB month number.
     * @param monthName MonthCB month name.
     */
    public MonthCB(int monthNum, String monthName)
    {
        this.monthNum = monthNum;
        this.monthName = monthName;
    }

    /**Set All Months Method.
     * Sets Month values.
     */
    public static void setAllMonths()
    {
        allMonths.add(new MonthCB(1, "January"));
        allMonths.add(new MonthCB(2, "February"));
        allMonths.add(new MonthCB(3,"March"));
        allMonths.add(new MonthCB(4, "April"));
        allMonths.add(new MonthCB(5, "May"));
        allMonths.add(new MonthCB(6,"June"));
        allMonths.add(new MonthCB(7, "July"));
        allMonths.add(new MonthCB(8, "August"));
        allMonths.add(new MonthCB(9,"September"));
        allMonths.add(new MonthCB(10, "October"));
        allMonths.add(new MonthCB(11, "November"));
        allMonths.add(new MonthCB(12,"December"));
    }

    /**Get All Months Method.
     * Returns all of the months in Observable Array List.
     * @return getAllMonths.
     */
    public static ObservableList<MonthCB> getAllMonths() {
        return allMonths;
    }

    /**Set All Months Method.
     * Sets month values.
     * @param allMonths .
     */
    public static void setAllMonths(ObservableList<MonthCB> allMonths)
    {
        MonthCB.allMonths = allMonths;
    }

    /**Get Month Name Method.
     * Returns the month name.
     * @return monthName.
     */
    public String getMonthName()
    {
        return monthName;
    }

    /**Set Month Name Method.
     * Sets the month name value.
     * @param monthName .
     */
    public void setMonthName(String monthName)
    {
        this.monthName = monthName;
    }

    /**Get Month Num Method.
     * Returns the month number.
     * @return monthNum.
     */
    public int getMonthNum()
    {
        return monthNum;
    }

    /**Set Month Num Method.
     * Sets the month num value.
     * @param monthNum .
     */
    public void setMonthNum(int monthNum)
    {
        this.monthNum = monthNum;
    }

    //automatically runs setAllMonths;
    static
    {
        setAllMonths();
    }

    //converts Hex to String to show on the ComboBox
    @Override
    public String toString()
    {
        //return ( Integer.toString(countryID) + " " + countryName);
        return (Integer.toString(monthNum) + " | " + monthName);
    }
}
