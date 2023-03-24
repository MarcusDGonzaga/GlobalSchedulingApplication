package model;

/*
 * @author Marcus Syldon Antino Dirige Gonzaga
 */

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

/**Appointments Class
 * Records, Updates, and Populates Appointments.
 */
public class Appointments {
    //Observable Array List to hold all Appointments
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String startDateTime;
    private String endDateTime;
    private int customerID;
    private int userID;
    private int contactID;

    /**Appointments Constructor.
     * @param id Appointments Appointment ID.
     * @param title Appointments Appointment Title.
     * @param description Appointments Appointment Description.
     * @param location Appointments Appointment Location.
     * @param type Appointments Appointment Type.
     * @param startDateTime Appointments Appointment Start Date Time.
     * @param endDateTime Appointments Appointment End Date Time.
     * @param customerID Appointments Appointment Customer ID.
     * @param userID Appointments Appointment User ID.
     * @param contactID Appointments Appointment Contact ID.
     */
    public Appointments (int id, String title, String description, String location, String type, String startDateTime, String endDateTime, int customerID, int userID, int contactID)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;

    }

    /**SQL Appointments Method.
     * Adds Appointments to Observable Array List allAppointments.
     * @throws SQLException .
     */
    public static void sqlAppointments() throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDesc = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
            //ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            LocalDate sDate = startLDT.toLocalDate();
            LocalTime sTime = startLDT.toLocalTime();
            ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, ZoneId.of("Etc/UTC"));
            startLocalZDT = startLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate startZoneDate = startLocalZDT.toLocalDate();
            LocalTime startZoneTime = startLocalZDT.toLocalTime();
            LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
            String start = dtf.format(startDateTime);

            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString,dtf);
            LocalDate eDate = endLDT.toLocalDate();
            LocalTime eTime = endLDT.toLocalTime();
            ZonedDateTime endLocalZDT = ZonedDateTime.of(eDate, eTime, ZoneId.of("Etc/UTC"));
            endLocalZDT = endLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate endZoneDate = endLocalZDT.toLocalDate();
            LocalTime endZoneTime = endLocalZDT.toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);
            String end = dtf.format(endDateTime);


            int custID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            allAppointments.add(new Appointments (apptID, apptTitle,apptDesc, apptLocation, apptType, start, end, custID, userID, contactID));
        }
    }

    /** SQL Filter Month Appointments Method.
     * Filters Appointments by the month.
     * @throws SQLException .
     */
    public static void sqlFilterMonthAppointments() throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE MONTH(start) = MONTH (CURRENT_DATE()) AND YEAR(start) =YEAR (CURRENT_DATE())" ;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDesc = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
            //ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            LocalDate sDate = startLDT.toLocalDate();
            LocalTime sTime = startLDT.toLocalTime();
            ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, ZoneId.of("Etc/UTC"));
            startLocalZDT = startLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate startZoneDate = startLocalZDT.toLocalDate();
            LocalTime startZoneTime = startLocalZDT.toLocalTime();
            LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
            String start = dtf.format(startDateTime);

            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString,dtf);
            LocalDate eDate = endLDT.toLocalDate();
            LocalTime eTime = endLDT.toLocalTime();
            ZonedDateTime endLocalZDT = ZonedDateTime.of(eDate, eTime, ZoneId.of("Etc/UTC"));
            endLocalZDT = endLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate endZoneDate = endLocalZDT.toLocalDate();
            LocalTime endZoneTime = endLocalZDT.toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);
            String end = dtf.format(endDateTime);


            int custID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            allAppointments.add(new Appointments (apptID, apptTitle,apptDesc, apptLocation, apptType, start, end, custID, userID, contactID));
        }

    }

    /**SQL Filter Week Appointments Method.
     * Filters Appointments by the week.
     * @throws SQLException .
     */
    public static void sqlFilterWeekAppointments() throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE WEEK(start) = WEEK(CURRENT_DATE()) AND YEAR(start) =YEAR (CURRENT_DATE())" ;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDesc = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
            //ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            LocalDate sDate = startLDT.toLocalDate();
            LocalTime sTime = startLDT.toLocalTime();
            ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, ZoneId.of("Etc/UTC"));
            startLocalZDT = startLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate startZoneDate = startLocalZDT.toLocalDate();
            LocalTime startZoneTime = startLocalZDT.toLocalTime();
            LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
            String start = dtf.format(startDateTime);

            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString,dtf);
            LocalDate eDate = endLDT.toLocalDate();
            LocalTime eTime = endLDT.toLocalTime();
            ZonedDateTime endLocalZDT = ZonedDateTime.of(eDate, eTime, ZoneId.of("Etc/UTC"));
            endLocalZDT = endLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate endZoneDate = endLocalZDT.toLocalDate();
            LocalTime endZoneTime = endLocalZDT.toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);
            String end = dtf.format(endDateTime);


            int custID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            allAppointments.add(new Appointments (apptID, apptTitle,apptDesc, apptLocation, apptType, start, end, custID, userID, contactID));
        }

    }

    /**Insert Appointments Method.
     * Adds Appointment information to the database.
     * @param title Appointments Appointment Title.
     * @param description Appointments Appointment Description.
     * @param location Appointments Appointment Location.
     * @param type Appointments Appointment Type.
     * @param startDateTime Appointments Appointment Start Date Time.
     * @param endDateTime Appointments Appointment End Date Time.
     * @param customerID Appointments Appointment Customer ID.
     * @param userID Appointments Appointment User ID.
     * @param contactID Appointments Appointment Contact ID.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int insertAppointment(String title, String description, String location, String type, String startDateTime, String endDateTime, int customerID, int userID, int contactID) throws SQLException
    {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID ) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, startDateTime);
        ps.setString(6, endDateTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        ps.setInt(9,contactID);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Update SQL Appointment Method.
     * Updates Appointments in the database.
     * @param id Appointments Appointment ID.
     * @param title Appointments Appointment Title.
     * @param description Appointments Appointment Description.
     * @param location Appointments Appointment Location.
     * @param type Appointments Appointment Type.
     * @param startDateTime Appointments Appointment Start Date Time.
     * @param endDateTime Appointments Appointment End Date Time.
     * @param customerID Appointments Appointment Customer ID.
     * @param userID Appointments Appointment User ID.
     * @param contactID Appointments Appointment Contact ID.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int updateSQLAppointment(int id, String title, String description, String location, String type, String startDateTime, String endDateTime, int customerID, int userID, int contactID) throws SQLException
    {
        String sql = "UPDATE APPOINTMENTS SET Title =?, Description =?, Location= ?, Type =?, Start= ?, End= ?, Customer_ID= ?, User_ID= ?, Contact_ID= ?  WHERE Appointment_ID= ? ";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);

        ps.setString(1,title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setString(5, startDateTime);
        ps.setString(6, endDateTime);
        ps.setInt(7, customerID);
        ps.setInt(8, userID);
        ps.setInt(9,contactID);
        ps.setInt(10,id);


        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**SQL 15 Minute Appointment Check Method.
     * Checks whether or not there is an appointment within the next 15 minutes.
     * @param currentTime Appointments Current Time.
     * @param currentDate Appointments Current Date.
     * @throws SQLException .
     */
    public static void sql15MinAppointmentsCheck(LocalTime currentTime, LocalDate currentDate) throws SQLException {
        String sql = "SELECT *FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        boolean appointmentCheckTF = false;

            while (rs.next()) // next moves cursor through result set
            {
                int apptID = rs.getInt("Appointment_ID");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
                //ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

                String startString = rs.getString("Start");
                LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
                LocalDate sDate = startLDT.toLocalDate();
                LocalTime sTime = startLDT.toLocalTime();
                ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, ZoneId.of("Etc/UTC"));
                startLocalZDT = startLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
                LocalDate startZoneDate = startLocalZDT.toLocalDate();
                LocalTime startZoneTime = startLocalZDT.toLocalTime();

                long appointmentCheck = ChronoUnit.MINUTES.between(currentTime, startZoneTime);

                if (startZoneDate.equals(currentDate) && (appointmentCheck < 15 && appointmentCheck > 0))
                {
                    appointmentCheckTF = true;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Upcoming Appointments");
                    alert.setTitle("Upcoming appointment within 15 minutes!");
                    alert.setContentText("Appointment ID: " + apptID + " | Date: " + startZoneDate + " | Time: " + startZoneTime);
                    alert.showAndWait();
                }
            }
    }

    /**SQL Overlap Appointments Check Method.
     * Checks for any overlapping appointments.
     * @param inputStartLDT Appointments Input Start Local Date Time.
     * @param inputEndLDT Appointments Input End Local Date Time.
     * @return rowsAffected.
     * @throws SQLException
     */
    public static boolean sqlOverlapAppointmentsCheck(LocalDateTime inputStartLDT, LocalDateTime inputEndLDT) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        boolean overlapCheckTF = false;

        while (rs.next()) // next moves cursor through result set
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString, dtf);


            if(inputStartLDT.isAfter(startLDT) && inputStartLDT.isBefore(endLDT))
            {
                overlapCheckTF = true;
            }
            if(inputEndLDT.isAfter(startLDT) && inputEndLDT.isBefore(endLDT))
            {
                overlapCheckTF = true;
            }
            if(inputStartLDT.equals(startLDT) || inputEndLDT.equals(endLDT))
            {
                overlapCheckTF = true;
            }
        }

        return overlapCheckTF;
    }

    /**SQL Overlap Appointments Update Check Method.
     * Checks for any overlapping appointments.
     * @param iD Appointments iD.
     * @param inputStartLDT Appointments Input Start Local Date Time.
     * @param inputEndLDT Appointments Input End Local Date Time.
     * @return rowsAffected.
     * @throws SQLException
     */
    public static boolean sqlOverlapAppointmentsUpdateCheck(int iD, LocalDateTime inputStartLDT, LocalDateTime inputEndLDT) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(); //2-D List

        boolean overlapCheckTF = false;

        while (rs.next()) // next moves cursor through result set
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString, dtf);
            int apptID = rs.getInt("Appointment_ID");

            if (iD != apptID) {
                if (inputStartLDT.isAfter(startLDT) && inputStartLDT.isBefore(endLDT)) {
                    overlapCheckTF = true;
                }
                if (inputEndLDT.isAfter(startLDT) && inputEndLDT.isBefore(endLDT)) {
                    overlapCheckTF = true;
                }
                if (inputStartLDT.equals(startLDT) || inputEndLDT.equals(endLDT)) {
                    overlapCheckTF = true;
                }
            }
        }

        return overlapCheckTF;
    }

    /**SQL Filter Contacts Method.
     * Filters Appointments by Contact.
     * @param selectedContact Appoointments Selected Contact.
     * @throws SQLException .
     */
    public static void sqlFilterContacts(int selectedContact) throws SQLException
    {
        String sql = "SELECT * FROM APPOINTMENTS WHERE CONTACT_ID =?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedContact);
        ResultSet rs = ps.executeQuery(); //2-D List

        while (rs.next()) // next moves cursor through result set
        {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDesc = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("yyyy-MM-dd HH:mm:ss"));
            //ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());

            String startString = rs.getString("Start");
            LocalDateTime startLDT = LocalDateTime.parse(startString, dtf);
            LocalDate sDate = startLDT.toLocalDate();
            LocalTime sTime = startLDT.toLocalTime();
            ZonedDateTime startLocalZDT = ZonedDateTime.of(sDate, sTime, ZoneId.of("Etc/UTC"));
            startLocalZDT = startLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate startZoneDate = startLocalZDT.toLocalDate();
            LocalTime startZoneTime = startLocalZDT.toLocalTime();
            LocalDateTime startDateTime = LocalDateTime.of(startZoneDate, startZoneTime);
            String start = dtf.format(startDateTime);

            String endString = rs.getString("End");
            LocalDateTime endLDT = LocalDateTime.parse(endString,dtf);
            LocalDate eDate = endLDT.toLocalDate();
            LocalTime eTime = endLDT.toLocalTime();
            ZonedDateTime endLocalZDT = ZonedDateTime.of(eDate, eTime, ZoneId.of("Etc/UTC"));
            endLocalZDT = endLocalZDT.withZoneSameInstant(ZoneId.systemDefault());
            LocalDate endZoneDate = endLocalZDT.toLocalDate();
            LocalTime endZoneTime = endLocalZDT.toLocalTime();
            LocalDateTime endDateTime = LocalDateTime.of(endZoneDate, endZoneTime);
            String end = dtf.format(endDateTime);


            int custID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            allAppointments.add(new Appointments (apptID, apptTitle,apptDesc, apptLocation, apptType, start, end, custID, userID, contactID));

        }


    }

    /**Delete Appointment Method.
     * Deletes Appointments from TableView.
     * @param selectedAppointment Appointments Selected Appointment.
     * @return allAppointments.remove(selectedAppointment).
     */
    public static boolean deleteAppointment(Appointments selectedAppointment) {
        return allAppointments.remove(selectedAppointment);
    }

    /**Delete SQL Appointment Method.
     * Deletes Appointment from the Database.
     * @param idToDelete Appointments ID to Delete.
     * @return rowsAffected.
     * @throws SQLException .
     */
    public static int deleteSQLAppointment(int idToDelete) throws SQLException {


        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, idToDelete);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**Get ID Method.
     * Returns the ID of Appointment.
     * @return id.
     */
    public int getId()
    {
        return id;
    }

    /**Set ID Method.
     * Sets the Appointment ID value.
     * @param id Appointments ID.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**Get Title Method.
     * Returns the Appointment Title.
     * @return title.
     */
    public String getTitle()
    {
        return title;
    }

    /**Set Title Method.
     * Sets the Title value.
     * @param title Appointments Title.
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**Get Description Method.
     * Returns the Appointment Description.
     * @return description.
     */
    public String getDescription()
    {
        return description;
    }

    /**Set Description Method.
     * Sets Appointment description value.
     * @param description Appointments description.
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**Get Location Method.
     * Returns the Appointment location.
     * @return location.
     */
    public String getLocation()
    {
        return location;
    }

    /**Set Location Method.
     * Sets Appointment location method.
     * @param location Appointments Location.
     */
    public void setLocation(String location)
    {
        this.location = location;
    }

    /**Get Type Method.
     * Returns Appointment Type.
     * @return type.
     */
    public String getType()
    {
        return type;
    }

    /**Set Type Method.
     * Sets Appointment Type Method.
     * @param type Appointments Type.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**Get Start Date Time Method.
     * Returns the Start Date and Time Values as a string.
     * @return startDateTime.
     */
    public String getStartDateTime()
    {
        return startDateTime;
    }

    /**Set Start Date Time Method.
     * Sets the Appointment Start Date and Time Value.
     * @param startDateTime Appointments Start Date Time.
     */
    public void setStartDateTime(String startDateTime)
    {
        this.startDateTime = startDateTime;
    }

    /**Get End Date Time Method.
     * Returns the End Date and Time as a string.
     * @return endDateTime.
     */
    public String getEndDateTime()
    {
        return endDateTime;
    }

    /**Set End Date Time Method.
     * Sets the End Date and Time Value.
     * @param endDateTime Appointments End Date Time.
     */
    public void setEndDateTime(String endDateTime)
    {
        this.endDateTime = endDateTime;
    }

    /**Get Customer ID Method.
     * Returns the Appointment Customer ID.
     * @return customerID.
     */
    public int getCustomerID()
    {
        return customerID;
    }

    /**Set Customer ID Method.
     * Sets the Appointment Customer ID value.
     * @param customerID Appointments Customer ID.
     */
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }

    /**Get User ID Method.
     * Returns User ID of Appointment.
     * @return userID.
     */
    public int getUserID()
    {
        return userID;
    }

    /**Set User ID Method.
     * Sets Appointment User ID value.
     * @param userID Appointments User ID.
     */
    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    /**Get Contact ID Method.
     * Returns Appointment Contact ID.
     * @return contactID.
     */
    public int getContactID()
    {
        return contactID;
    }

    /**Set Contact ID Method.
     * Sets the value of Appointment Contact ID.
     * @param contactID Appointments Contact ID.
     */
    public void setContactID(int contactID)
    {
        this.contactID = contactID;
    }

    /**Get All Appointments Method.
     * Returns All Appointments from Observable Array List.
     * @return getAllAppointments.
     */
    public static ObservableList<Appointments> getAllAppointments(){ return allAppointments;}
}
