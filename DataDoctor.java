import javafx.beans.property.SimpleStringProperty;

/**
 * The DataDoctor class represents a doctor's information including their ID,
 * name, contact details, address, image, and schedule.
 * It also provides properties for the doctor's name to be used in JavaFX UI
 * components.
 * @author Anura Damayantha
 * @version 1.0
 * @since 2024-04-08
 */
public class DataDoctor {

    private int Id;
    private String doctorNumber;
    private String firstName;
    private String lastName;
    private String telephone;
    private String address;
    private String image;
    private String schedule;
    private int dayLabelMonday;
    private int dayLabelTuesday;
    private int dayLabelWednesday;
    private int dayLabelThursday;
    private int dayLabelFriday;
    private int dayLabelSaturday;
    private int dayLabelSunday;

    private SimpleStringProperty doctorNumberStringProperty;
    private SimpleStringProperty firstNameStringProperty;
    private SimpleStringProperty lastNameStringProperty;

    /**
     * Default constructor.
     */
    public DataDoctor() {
    }

    /**
     * Parameterized constructor to initialize the doctor's name properties.
     *
     * @param doctorNumber The doctor's number.
     * @param firstName    The doctor's first name.
     * @param lastName     The doctor's last name.
     */
    public DataDoctor(String doctorNumber, String firstNameString, String lastNameString) {

        this.doctorNumberStringProperty = new SimpleStringProperty();
        this.firstNameStringProperty = new SimpleStringProperty();
        this.lastNameStringProperty = new SimpleStringProperty();

        this.doctorNumberStringProperty.setValue(doctorNumber);
        this.firstNameStringProperty.setValue(firstNameString);
        this.lastNameStringProperty.setValue(lastNameString);

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getDayLabelMonday() {
        return dayLabelMonday;
    }

    public void setDayLabelMonday(int dayLabelMonday) {
        this.dayLabelMonday = dayLabelMonday;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorNumber() {
        return this.doctorNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }

    public int getDayLabelTuesday() {
        return dayLabelTuesday;
    }

    public void setDayLabelTuesday(int dayLabelTuesday) {
        this.dayLabelTuesday = dayLabelTuesday;
    }

    public int getDayLabelWednesday() {
        return dayLabelWednesday;
    }

    public void setDayLabelWednesday(int dayLabelWednesday) {
        this.dayLabelWednesday = dayLabelWednesday;
    }

    public int getDayLabelThursday() {
        return dayLabelThursday;
    }

    public void setDayLabelThursday(int dayLabelThursday) {
        this.dayLabelThursday = dayLabelThursday;
    }

    public int getDayLabelFriday() {
        return dayLabelFriday;
    }

    public void setDayLabelFriday(int dayLabelFriday) {
        this.dayLabelFriday = dayLabelFriday;
    }

    public int getDayLabelSaturday() {
        return dayLabelSaturday;
    }

    public void setDayLabelSaturday(int dayLabelSaturday) {
        this.dayLabelSaturday = dayLabelSaturday;
    }

    public int getDayLabelSunday() {
        return dayLabelSunday;
    }

    public void setDayLabelSunday(int dayLabelSunday) {
        this.dayLabelSunday = dayLabelSunday;
    }

    public SimpleStringProperty getDoctorNumberStringProperty() {
        return doctorNumberStringProperty;
    }

    public void setDoctorNumberStringProperty(SimpleStringProperty doctorNumberStringProperty) {
        this.doctorNumberStringProperty = doctorNumberStringProperty;
    }

    public SimpleStringProperty getFirstNameStringProperty() {
        return firstNameStringProperty;
    }

    public void setFirstNameStringProperty(SimpleStringProperty firstNameStringProperty) {
        this.firstNameStringProperty = firstNameStringProperty;
    }

    public SimpleStringProperty getLastNameStringProperty() {
        return lastNameStringProperty;
    }

    public void setLastNameStringProperty(SimpleStringProperty lastNameStringProperty) {
        this.lastNameStringProperty = lastNameStringProperty;
    }
}
