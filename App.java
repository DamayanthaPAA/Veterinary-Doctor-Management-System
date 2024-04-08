import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code App} class represents a JavaFX application for managing veterinary
 * doctor details.
 * It provides a graphical user interface (GUI) for adding, editing, saving, and
 * deleting records of veterinary doctors.
 * Additionally, it allows users to upload images for each doctor and displays
 * doctor schedules using checkboxes for each day of the week.
 * <p>
 * The application implements the {@link CommonFunction} interface to provide
 * common functionality
 * for adding, editing, saving, and deleting records, as well as input
 * validation.
 * </p>
 * <p>
 * This class extends {@link Application} from JavaFX, which is the base class
 * for JavaFX applications.
 * </p>
 * <p>
 * The graphical user interface layout is constructed using a {@link GridPane},
 * and various components such as labels,
 * text fields, checkboxes, buttons, and event handlers are defined within the
 * {@link #start(Stage)} method.
 * </p>
 * <p>
 * The application also includes utility methods for uploading and saving
 * images, as well as populating GUI fields with data
 * from {@link DataDoctor} objects.
 * </p>
 * <p>
 * The main method launches the JavaFX application.
 * </p>
 *
 * @author Anura Damayantha
 * @version 1.0
 * @since 2024-04-08
 */

public class App extends Application implements CommonFunction {

    // Create an instance of CommonMethods
    CommonMethods commonMethods = CommonMethods.getInstance();

    private TextField doctorNumberField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField telephoneField = new TextField();
    private TextField addressField = new TextField();
    private TextField imageField = new TextField();
    private CheckBox dayLabelMonday;
    private CheckBox dayLabelTuesday;
    private CheckBox dayLabelWednesday;
    private CheckBox dayLabelThursday;
    private CheckBox dayLabelFriday;
    private CheckBox dayLabelSaturday;
    private CheckBox dayLabelSunday;
    private ImageView imageView = new ImageView(); // Add an ImageView to display the uploaded image
    private File imageFile;

    /**
     * The start method is overridden from the Application class.
     * It initializes the graphical user interface (GUI) layout and components
     * for managing veterinary doctor details.
     *
     * @param primaryStage the primary stage for the JavaFX application
     */

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Veterinary Doctor Details");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label doctorNumberLabel = new Label("Doctor Number:");
        GridPane.setConstraints(doctorNumberLabel, 0, 0);

        GridPane.setConstraints(doctorNumberField, 1, 0);

        Label firstNameLabel = new Label("First Name:");
        GridPane.setConstraints(firstNameLabel, 0, 1);

        GridPane.setConstraints(firstNameField, 1, 1);

        Label lastNameLabel = new Label("Last Name:");
        GridPane.setConstraints(lastNameLabel, 0, 2);

        GridPane.setConstraints(lastNameField, 1, 2);

        Label telephoneLabel = new Label("Telephone Number:");
        GridPane.setConstraints(telephoneLabel, 0, 3);

        GridPane.setConstraints(telephoneField, 1, 3);

        Label addressLabel = new Label("Address:");
        GridPane.setConstraints(addressLabel, 0, 4);

        GridPane.setConstraints(addressField, 1, 4);

        Label imageLabel = new Label("Image:");
        GridPane.setConstraints(imageLabel, 0, 5);

        // GridPane.setConstraints(imageField, 1, 5);

        Button uploadButton = new Button("Upload Image");

        // GridPane.setConstraints(uploadButton, 1, 5);

        uploadButton.setOnAction(e -> uploadImage());

        HBox imageBox = new HBox(10); // Set spacing between image view and upload button
        imageView.setFitWidth(200); // Set preferred width
        imageView.setFitHeight(250); // Set preferred height

        imageBox.getChildren().addAll(imageView, uploadButton);

        GridPane.setConstraints(imageBox, 1, 5); // Adjust the row according to your layout

        Label scheduleLabel = new Label("Schedule:");
        GridPane.setConstraints(scheduleLabel, 0, 6);

        // Create checkboxes for each day of the week

        // for (int i = 0; i < daysOfWeek.length; i++) {
        // dayLabel = new CheckBox(daysOfWeek[i]);
        // GridPane.setConstraints(dayLabel, 1, 6 + i);
        // grid.getChildren().add(dayLabel);
        // }

        dayLabelMonday = new CheckBox("Monday");
        // GridPane.setConstraints(dayLabelMonday, 1, 7 + 0);
        // grid.getChildren().add(dayLabelMonday);

        dayLabelTuesday = new CheckBox("Tuesday");
        // GridPane.setConstraints(dayLabelTuesday, 1, 7 + 1);
        // grid.getChildren().add(dayLabelTuesday);

        dayLabelWednesday = new CheckBox("Wednesday");
        // GridPane.setConstraints(dayLabelWednesday, 1, 7 + 2);
        // grid.getChildren().add(dayLabelWednesday);

        dayLabelThursday = new CheckBox("Thursday");
        // GridPane.setConstraints(dayLabelThursday, 1, 7 + 3);
        // grid.getChildren().add(dayLabelThursday);

        dayLabelFriday = new CheckBox("Friday");
        // GridPane.setConstraints(dayLabelFriday, 1, 7 + 4);
        // grid.getChildren().add(dayLabelFriday);
        dayLabelSaturday = new CheckBox("Saturday");
        dayLabelSaturday.setStyle("-fx-background-color: lightblue;"); // Set background color using CSS
        // GridPane.setConstraints(dayLabelSaturday, 1, 7 + 5);
        // grid.getChildren().add(dayLabelSaturday);
        dayLabelSunday = new CheckBox("Sunday");
        dayLabelSunday.setStyle("-fx-background-color: lightgreen;"); // Set background color using CSS

        // GridPane.setConstraints(dayLabelSunday, 1, 7 + 6);
        // grid.getChildren().add(dayLabelSunday);

        HBox checkBox = new HBox(10); // Set spacing between buttons
        checkBox.getChildren().addAll(dayLabelMonday, dayLabelTuesday, dayLabelWednesday, dayLabelThursday,
                dayLabelFriday);
        GridPane.setConstraints(checkBox, 1, 7);
        HBox checkBoxWeekEnd = new HBox(10); // Set spacing between buttons
        checkBoxWeekEnd.getChildren().addAll(dayLabelSaturday, dayLabelSunday);
        GridPane.setConstraints(checkBoxWeekEnd, 1, 8);

        Button saveButton = new Button("Save");
        Button addButton = new Button("Add New");
        Button clearButton = new Button("Clear");
        Button editButton = new Button("Edit");
        Button printButton = new Button("Print");
        Button deleteButton = new Button("Delete");

        setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                deleteButton, "1,1,0,0,0,0");

        addButton.setOnAction(e -> {
            addNew();
            doctorNumberField.setDisable(false);
            setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                    deleteButton, "0,1,1,0,0,0");
        });

        clearButton.setOnAction(e -> {
            clear();
            doctorNumberField.setDisable(false);
            setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                    deleteButton, "1,1,0,0,0,0");
        });

        editButton.setOnAction(e ->

        {
            edit();
            doctorNumberField.setDisable(true);
            setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                    deleteButton, "1,1,1,0,0,0");
        });

        printButton.setOnAction(e -> print());

        deleteButton.setOnAction(e -> delete());

        // GridPane.setConstraints(addButton, 0, 14);
        // GridPane.setConstraints(clearButton, 1, 14);
        // GridPane.setConstraints(saveButton, 2, 14);
        // GridPane.setConstraints(editButton, 3, 14);
        // GridPane.setConstraints(printButton, 4, 14);
        // GridPane.setConstraints(deleteButton, 5, 14);

        doctorNumberField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                // Handle the ENTER key press event
                // For example, call a method to handle saving or validating the input
                String doctoStrictMath = "";
                doctoStrictMath = doctorNumberField.getText();
                // commonMethods.showAlert("STYLESHEET_MODENA", doctorNumberField.getText());
                DataReadWriteDoctorDetail doctorDetail = new DataReadWriteDoctorDetail();

                clear();
                doctorNumberField.setText(doctoStrictMath);
                if (doctoStrictMath.length() != 0) {
                    // If the length of doctoStrictMath is not 0
                    List<DataDoctor> objectDataDoctor = new ArrayList<>();
                    // Create a new list to store DataDoctor objects

                    // Call the searchRecord method to retrieve records based on doctoStrictMath
                    // and add them to objectDataDoctor
                    objectDataDoctor.addAll(doctorDetail.searchRecord(doctoStrictMath));
                    if (!objectDataDoctor.isEmpty()) {
                        for (DataDoctor dataDoctor : objectDataDoctor) {
                            // Print the assigned values

                            // System.out.println( "dataDoctor.getId()" + dataDoctor.getId());

                            if (setValues(dataDoctor)) {
                                setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                                        deleteButton, "0,1,0,1,1,1");
                            } else {
                                firstNameField.requestFocus();
                            }

                        }
                    }

                }

            }
        });

        doctorNumberField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Check if the field lost focus
                // Call a method to handle actions when the field loses focus
                handleDoctorNumberFieldFocusLost();
            }
        });

        saveButton.setOnAction(e -> {
            boolean saveValidating = saveValidate();
            if (!saveValidating) {
                return;
            }
            if (save()) {

                commonMethods.showAlert("Success", "Information saved successfully", 3);
                {

                    setButtonPanel(addButton, clearButton, saveButton, editButton, printButton,
                            deleteButton, "1,1,0,0,0,0");
                }
            } else {
                commonMethods.showAlert("Success", "Information failed to save successfully", 1);

            }

            // saveDoctorDetails(doctorNumberField.getText(), firstNameField.getText(),
            // lastNameField.getText(),
            // telephoneField.getText(), addressField.getText(), imageField.getText());
        });

        HBox buttonBox = new HBox(10); // Set spacing between buttons
        buttonBox.getChildren().addAll(addButton, clearButton, saveButton, editButton, printButton, deleteButton);
        // VBox vbox = new VBox(10); // Set spacing between components
        // GridPane.setConstraints(saveButton, 6, 14);
        GridPane.setConstraints(buttonBox, 1, 9);

        Label scheduleLabelDrListLabel = new Label("Doctor List:");
        GridPane.setConstraints(scheduleLabelDrListLabel, 3, 4);

        Button SearchButton = new Button("Search");
        GridPane.setConstraints(SearchButton, 3, 0);

        @SuppressWarnings("rawtypes")
        TableView listDoctor = showDoctorList();
        GridPane.setConstraints(listDoctor, 3, 5);

        SearchButton.setOnAction(e -> {
            searchButton(listDoctor, doctorNumberField.getText());

        });

        doctorNumberField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            // Get the character typed by the user
            char inputChar = event.getCharacter().charAt(0);

            // Check if the character is a digit or backspace
            if (!Character.isDigit(inputChar) && inputChar != '\b') {
                // Consume the event to prevent the character from being inputted
                event.consume();
            }
        });

        telephoneField.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            // Get the character typed by the user
            char inputChar = event.getCharacter().charAt(0);

            // Check if the character is a digit or backspace
            if (!Character.isDigit(inputChar) && inputChar != '\b') {
                // Consume the event to prevent the character from being inputted
                event.consume();
            }
        });

        grid.getChildren().addAll(doctorNumberLabel, doctorNumberField, firstNameLabel, firstNameField,
                lastNameLabel, lastNameField, telephoneLabel, telephoneField, addressLabel, addressField,
                imageLabel, imageBox, checkBox, checkBoxWeekEnd, buttonBox, SearchButton, scheduleLabelDrListLabel,
                listDoctor);
        // imageFieldscheduleLabel
        // grid.getChildren().addAll(doctorNumberLabel, doctorNumberField,
        // firstNameLabel, firstNameField,
        // lastNameLabel, lastNameField, telephoneLabel, telephoneField, addressLabel,
        // addressField,
        // imageLabel, imageField, scheduleLabel, saveButton,addButton, clearButton,
        // editButton, printButton, deleteButton);

        Scene scene = new Scene(grid, 400, 500);
        // Scene scene = new Scene(vbox, 400, 500);
        primaryStage.setScene(scene);
        // Maximize the stage
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    /**
     * Method to handle uploading of images.
     * Opens a file chooser dialog to select an image file,
     * then displays the selected image in the ImageView.
     */
    private void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                Image image = new Image(new FileInputStream(file));
                imageView.setImage(image);
                this.imageFile = file;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to populate GUI fields with data from a DataDoctor object.
     * Sets the values of text fields, checkboxes, and the image view based on the
     * data retrieved.
     *
     * @param dataDoctor the DataDoctor object containing doctor details
     * @return true if values are successfully set, false otherwise
     */
    private boolean setValues(DataDoctor dataDoctor) {
        try {
            clear();

            doctorNumberField.setUserData(dataDoctor.getId());

            doctorNumberField.setText(dataDoctor.getDoctorNumber());
            firstNameField.setText(dataDoctor.getFirstName());
            lastNameField.setText(dataDoctor.getLastName());
            telephoneField.setText(dataDoctor.getTelephone());
            addressField.setText(dataDoctor.getAddress());
            imageField.setText(dataDoctor.getImage());

            String folderPath = "images/".concat(doctorNumberField.getText().concat(".jpg"));
            loadImage(folderPath, this.imageView);

            if (dataDoctor.getDayLabelMonday() == 1) {
                dayLabelMonday.setSelected(true); // Checked
            } else {
                dayLabelMonday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelTuesday() == 1) {
                dayLabelTuesday.setSelected(true); // Checked
            } else {
                dayLabelTuesday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelWednesday() == 1) {
                dayLabelWednesday.setSelected(true); // Checked
            } else {
                dayLabelWednesday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelThursday() == 1) {
                dayLabelThursday.setSelected(true); // Checked
            } else {
                dayLabelThursday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelFriday() == 1) {
                dayLabelFriday.setSelected(true); // Checked
            } else {
                dayLabelFriday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelSaturday() == 1) {
                dayLabelSaturday.setSelected(true); // Checked
            } else {
                dayLabelSaturday.setSelected(false); // Unchecked
            }

            if (dataDoctor.getDayLabelSunday() == 1) {
                dayLabelSunday.setSelected(true); // Checked
            } else {
                dayLabelSunday.setSelected(false); // Unchecked
            }
            return true;
        } catch (Exception e) {

            System.out.println("Error " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to enable or disable buttons based on the input controlMode string.
     * Used to control the state of add, clear, save, edit, print, and delete
     * buttons.
     *
     * @param addButton    the add button
     * @param clearButton  the clear button
     * @param saveButton   the save button
     * @param editButton   the edit button
     * @param printButton  the print button
     * @param deleteButton the delete button
     * @param controlMode  a string representing the control mode for buttons
     */
    private void setButtonPanel(Button addButton, Button clearButton, Button saveButton, Button editButton,
            Button printButton, Button deleteButton, String controlMode) {

        try {
            // Split the inputString using comma as the delimiter
            String[] parts = controlMode.split(",");

            // Assign each part to different variables
            int var1 = Integer.parseInt(parts[0]);
            int var2 = Integer.parseInt(parts[1]);
            int var3 = Integer.parseInt(parts[2]);
            int var4 = Integer.parseInt(parts[3]);
            int var5 = Integer.parseInt(parts[4]);
            int var6 = Integer.parseInt(parts[5]);

            addButton.setDisable(false);
            clearButton.setDisable(false);
            saveButton.setDisable(false);
            editButton.setDisable(false);
            printButton.setDisable(false);
            deleteButton.setDisable(false);

            addButton.setDisable(var1 == 0);
            clearButton.setDisable(var2 == 0);
            saveButton.setDisable(var3 == 0);
            editButton.setDisable(var4 == 0);
            printButton.setDisable(var5 == 0);
            deleteButton.setDisable(var6 == 0);

        } catch (Exception e) {

        }

    }

    /**
     * Saves the details of the veterinary doctor to a data storage system and
     * uploads the associated image file.
     * The method retrieves input from various GUI components such as text fields
     * and checkboxes,
     * creates a DataReadWriteDoctorDetail object to manage data storage operations,
     * sets the values of the DataReadWriteDoctorDetail object with the retrieved
     * input,
     * and invokes the saveDoctorDetails method to persist the doctor details.
     * Additionally, it saves the associated image file to the file system using the
     * saveImage method.
     *
     * @return true if the doctor details and image are successfully saved, false
     *         otherwise
     */
    private boolean saveDoctorDetails() {
        try {
            DataReadWriteDoctorDetail doctorDetail = new DataReadWriteDoctorDetail();
            // doctorDetail.setId(Integer.parseInt(doctorNumberField.getUserData());

            if (doctorNumberField.getUserData() == null) {
                doctorDetail.setId(0);
            } else {
                doctorDetail.setId((Integer) doctorNumberField.getUserData());
            }

            doctorDetail.setDoctorNumber(doctorNumberField.getText());
            doctorDetail.setFirstName(firstNameField.getText());
            doctorDetail.setLastName(lastNameField.getText());
            doctorDetail.setTelephone(telephoneField.getText());
            doctorDetail.setAddress(addressField.getText());
            doctorDetail.setImage(imageField.getText());

            String schedule = getSchedule();
            doctorDetail.setSchedule(schedule);

            doctorDetail.saveDoctorDetails(1, "");

            saveImage(imageFile, doctorNumberField.getText().concat(".jpg"));
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    /**
     * Constructs and returns a schedule string based on the selected checkboxes for
     * each day of the week.
     * Each checkbox representing a day is checked, and if selected, '1' is appended
     * to the schedule string; otherwise, '0' is appended.
     *
     * @return the constructed schedule string representing the selected days of the
     *         week (e.g., "1010111")
     */
    private String getSchedule() {
        String schedule = "";
        if (dayLabelMonday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelTuesday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelWednesday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelThursday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelFriday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelSaturday.isSelected()) {
            schedule = schedule.concat("1").concat(",");
        } else {
            schedule = schedule.concat("0").concat(",");
        }

        if (dayLabelSunday.isSelected()) {
            schedule = schedule.concat("1");
        } else {
            schedule = schedule.concat("0");
        }

        return schedule;
    }

    /**
     * The main method serves as the entry point for launching the JavaFX
     * application.
     * It invokes the launch method, initiating the JavaFX runtime and starting the
     * application.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Handles the event when the focus is lost from the doctorNumberField.
     * This method should be implemented to define actions to be performed when the
     * doctorNumberField loses focus.
     * For example, it can be used to perform validation or save the entered data.
     */
    private void handleDoctorNumberFieldFocusLost() {
        // Implement the actions to be performed when doctorNumberField loses focus
        // For example, perform validation or save the entered data
    }

    /**
     * Adds a new entry for a veterinary doctor.
     * This method clears the input fields and sets focus to the doctorNumberField.
     *
     * @return true if adding a new entry is successful, false otherwise
     */
    @Override
    public boolean addNew() {
        try {
            clear();
            commonMethods.setFocusTextfield(doctorNumberField);

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Clears all input fields and resets checkboxes and image display.
     * This method resets all text fields, checkboxes, and the image display to
     * their initial states.
     *
     * @return true if clearing the fields is successful, false otherwise
     */
    @Override
    public boolean clear() {
        try {
            doctorNumberField.setUserData(null);
            doctorNumberField.clear();
            firstNameField.clear();
            lastNameField.clear();
            telephoneField.clear();
            addressField.clear();
            // Clear checkboxes
            dayLabelMonday.setSelected(false);
            dayLabelTuesday.setSelected(false);
            dayLabelWednesday.setSelected(false);
            dayLabelThursday.setSelected(false);
            dayLabelFriday.setSelected(false);
            dayLabelSaturday.setSelected(false);
            dayLabelSunday.setSelected(false);
            // Clear image
            imageView.setImage(null);
            this.imageFile = null;
            commonMethods.setFocusTextfield(doctorNumberField);
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Saves the details of the veterinary doctor.
     * This method attempts to save the details of the veterinary doctor by calling
     * the saveDoctorDetails() method.
     *
     * @return true if saving the details is successful, false otherwise
     */
    @Override
    public boolean save() {
        try {

            return saveDoctorDetails();
            // return true;
        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public boolean edit() {

        // throw new UnsupportedOperationException("Unimplemented method 'edit'");

        return true;
    }

    @Override
    public boolean print() {

        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }

    @Override
    public boolean delete() {

        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    /**
     * Validates the data before saving.
     * This method checks if the required text fields (doctor number, first name,
     * last name, and telephone) are empty.
     * If any of these fields are empty, it displays an alert message and sets focus
     * to the empty field.
     *
     * @return true if the data is valid and ready for saving, false otherwise
     */
    @Override
    public boolean saveValidate() {
        try {

            // Call the isEmptyTextField method and store the result doctorNumberField
            boolean isEmpty = commonMethods.isEmptyTextField(this.doctorNumberField);

            // Display the result
            if (isEmpty) {

                commonMethods.showAlert("Doctor Number Field", "Requres Doctor Number Field", 1);
                // Set focus to the TextField
                // this.doctorNumberField.requestFocus();
                commonMethods.setFocusTextfield(this.doctorNumberField);
                return false;
                // System.out.println("TextField is empty");
                // } else {
                // System.out.println("TextField is not empty");
            }

            // Call the isEmptyTextField method and store the result firstNameField
            isEmpty = commonMethods.isEmptyTextField(this.firstNameField);

            // Display the result
            if (isEmpty) {
                commonMethods.showAlert("First Name Field", "Requres First Name Field", 1);
                commonMethods.setFocusTextfield(this.firstNameField);
                return false;

            }

            // Call the isEmptyTextField method and store the result lastNameField
            isEmpty = commonMethods.isEmptyTextField(this.lastNameField);

            // Display the result
            if (isEmpty) {
                commonMethods.showAlert("Last Name Field", "Requres Last Name Field", 1);
                commonMethods.setFocusTextfield(this.lastNameField);
                return false;

            }

            // Call the isEmptyTextField method and store the result telephoneField
            isEmpty = commonMethods.isEmptyTextField(this.telephoneField);

            // Display the result
            if (isEmpty) {
                commonMethods.showAlert("Tele phone Field", "Requres Tele phone Field", 1);
                commonMethods.setFocusTextfield(this.telephoneField);
                return false;

            }

            return true;
        } catch (Exception e) {

            return false;
        }

    }

    /**
     * Displays a TableView of doctors.
     * This method retrieves doctor data from the database, creates a TableView, and
     * populates it with the retrieved data.
     *
     * @return TableView containing the list of doctors, or null if an error occurs
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TableView showDoctorList() {
        try {

            DataReadWriteDoctorDetail dataReadWriteDoctorDetail = new DataReadWriteDoctorDetail();

            List<DataDoctor> dataDoctorList = dataReadWriteDoctorDetail.searchRecord("");

            ObservableList<DataDoctor> data = FXCollections.observableArrayList(dataDoctorList);

            TableView<DataDoctor> tableView = new TableView<>();

            TableColumn<DataDoctor, String> doctorNumberCol = new TableColumn<>("Doctor Number");
            doctorNumberCol.setCellValueFactory(cellData -> cellData.getValue().getDoctorNumberStringProperty());

            TableColumn<DataDoctor, String> firstNameCol = new TableColumn<>("First Name");
            firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstNameStringProperty());

            TableColumn<DataDoctor, String> lastNameCol = new TableColumn<>("Last Name");
            lastNameCol.setCellValueFactory(cellData -> cellData.getValue().getLastNameStringProperty());

            tableView.getColumns().addAll(doctorNumberCol, firstNameCol, lastNameCol);

            int dataCount = data.size();
            DataDoctor[] doctors = new DataDoctor[dataCount];

            int i = 0;
            for (DataDoctor dataDoctor : data) {
                doctors[i] = new DataDoctor(dataDoctor.getDoctorNumber(), dataDoctor.getFirstName(),
                        dataDoctor.getLastName());
                i++;
            }

            ObservableList<DataDoctor> datax = FXCollections.observableArrayList(doctors);

            tableView.setItems(datax);
            return tableView;
        } catch (Exception e) {
            // Handle any exceptions or log them for debugging
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves search results for doctors based on the provided search string.
     * This method searches for doctors whose doctor number matches the search
     * string and returns a list of matching doctors.
     * It returns a maximum of two search results.
     *
     * @param searchString The search string to match against doctor numbers
     * @return An ObservableList containing the search results, or an empty list if
     *         no matches are found
     */
    public ObservableList<DataDoctor> getSearchResults(String serarchString) {

        DataReadWriteDoctorDetail dataReadWriteDoctorDetail = new DataReadWriteDoctorDetail();

        List<DataDoctor> dataDoctorList = dataReadWriteDoctorDetail.searchRecord("");

        ObservableList<DataDoctor> data = FXCollections.observableArrayList(dataDoctorList);
        int dataCount = 2;// data.size();
        DataDoctor[] doctors = new DataDoctor[dataCount];

        int i = 0;
        for (DataDoctor dataDoctor : data) {

            if (dataDoctor.getDoctorNumber().toString().toLowerCase()
                    .contains(serarchString.toString().toLowerCase())) {
                doctors[i] = new DataDoctor(dataDoctor.getDoctorNumber(), dataDoctor.getFirstName(),
                        dataDoctor.getLastName());
                i++;
                if (i == 2) {
                    break;
                }
            }

        }

        ObservableList<DataDoctor> datax = FXCollections.observableArrayList(doctors);

        return datax;

    }

    /**
     * Searches for doctors based on the provided search string and updates the
     * TableView with the search results.
     *
     * @param tableView    The TableView to update with the search results
     * @param searchString The search string to match against doctor numbers
     * @return true if the search operation is successful and the TableView is
     *         updated, otherwise false
     */
    public boolean searchButton(TableView<DataDoctor> tableView, String serarchString) {
        try {
            if (serarchString != null) {

                tableView.setItems(getSearchResults(serarchString));
                tableView.refresh();
            }

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    /**
     * Saves the provided image file to a specific folder with the specified file
     * name.
     *
     * @param imageFile The image file to be saved
     * @param fileName  The name to be used for the saved image file
     * @return true if the image is saved successfully, otherwise false
     */
    public static boolean saveImage(File imageFile, String fileName) {
        try {
            // Specify the folder path where you want to save the image
            String folderPath = "images/";

            // Create the folder if it doesn't exist
            Path path = Paths.get(folderPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Get the file name from the original image file
            // String fileName = imageFile.getName();

            // Construct the destination file path
            String destinationPath = folderPath + fileName;

            // Read the content of the original image file
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Write the content to the destination file
            FileOutputStream outputStream = new FileOutputStream(destinationPath);
            outputStream.write(imageData);
            outputStream.close();

            return true; // Image saved successfully
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Error occurred while saving the image
        }
    }

    /**
     * Loads an image from the specified file path and sets it to the provided
     * ImageView.
     *
     * @param imagePath The file path of the image to be loaded
     * @param imageView The ImageView to which the loaded image will be set
     */
    public static void loadImage(String imagePath, ImageView imageView) {
        try {
            // Create an image object from the file path
            Image image = new Image(new FileInputStream(imagePath));

            // Set the image to the ImageView
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Handle file not found exception
        }
    }

}
