import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import java.io.FileWriter;
// import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The DataReadWriteDoctorDetail class handles reading and writing doctor
 * details to a file.
 * It extends the DataDoctor class to access and manipulate doctor data.
 */
public class DataReadWriteDoctorDetail extends DataDoctor {
    private PrintWriter file;
    private File newFile;

    /**
     * Default constructor.
     */
    public DataReadWriteDoctorDetail() {

    }

    /**
     * Saves the doctor details to the file.
     *
     * @param action          The action to perform (not used in this
     *                        implementation).
     * @param textValueString The string containing doctor details to be saved.
     * @return True if the doctor details are successfully saved, otherwise false.
     */
    public boolean saveDoctorDetails(int action, String textValieString) {
        try {

            textValieString = getDoctorNumber().concat(",").concat(getFirstName().concat(",").concat(getLastName())
                    .concat(",").concat(getTelephone()).concat(",").concat(getAddress()).concat(",").concat(getImage())
                    .concat(",").concat(getSchedule()));

            if (getId() != 0) {

                updateData(textValieString, getId());
                return true;
            }

            file = new PrintWriter(new FileOutputStream(new File("doctordetails.txt"), true));

            file.println(textValieString);
            file.close();
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    /**
     * Searches for doctor records in the file based on the given search criteria.
     *
     * @param strDoctorNumber The doctor number to search for.
     * @return A list of DataDoctor objects matching the search criteria.
     */
    public List<DataDoctor> searchRecord(String strDocNumbeString) {
        List<DataDoctor> objectDataDoctor = new ArrayList<>();

        try {

            newFile = new File("doctordetails.txt");

            if (!newFile.exists()) {
                return null;
            }

            Scanner newScanner = new Scanner(newFile);
            int i = 0;
            while (newScanner.hasNext()) {

                String doctorDataLine = newScanner.nextLine();
                // System.out.println(doctorDataLine);

                DataDoctor dataDoctor = new DataDoctor();
                if (strDocNumbeString.length() == 0) {

                    dataDoctor = getDoctorObj(i, doctorDataLine);
                } else {
                    // System.out.println("strDocNumbeString: " + strDocNumbeString);
                    // System.out.println("doctorDataLine: " + doctorDataLine);

                    dataDoctor = getDoctorObj(i, strDocNumbeString, doctorDataLine);

                }
                if (dataDoctor != null) {
                    objectDataDoctor.add(dataDoctor);
                }

                i++;
            }

            newScanner.close();

            return objectDataDoctor;
        } catch (Exception e) {

            return null;
        }
    }

    private DataDoctor getDoctorObj(int i, String docString) {

        DataDoctor newDoctor = new DataDoctor();

        // Split the string by comma
        String[] values = docString.split(",");

        // Assign values to variables
        newDoctor.setId(i);
        newDoctor.setDoctorNumber(values[0]);
        newDoctor.setFirstName(values[1]);
        newDoctor.setLastName(values[2]);
        newDoctor.setTelephone(values[3]);
        newDoctor.setAddress(values[4]);
        newDoctor.setImage(values[5]);

        newDoctor.setDayLabelMonday(Integer.parseInt(values[6]));
        newDoctor.setDayLabelTuesday(Integer.parseInt(values[7]));
        newDoctor.setDayLabelWednesday(Integer.parseInt(values[8]));
        newDoctor.setDayLabelThursday(Integer.parseInt(values[9]));
        newDoctor.setDayLabelFriday(Integer.parseInt(values[10]));
        newDoctor.setDayLabelSaturday(Integer.parseInt(values[11]));
        newDoctor.setDayLabelSunday(Integer.parseInt(values[12]));
        // System.out.println(newDoctor);
        return newDoctor;
    }

    /**
     * Creates a new DataDoctor object with values parsed from a string if the
     * doctor number matches the given criteria.
     *
     * @param i                  The index of the doctor record.
     * @param doctorNumberString The doctor number to match for creating the object.
     * @param docString          The string containing doctor details to parse.
     * @return A DataDoctor object initialized with values parsed from the string if
     *         the doctor number matches the criteria; otherwise, null.
     */
    private DataDoctor getDoctorObj(int i, String doctorNumbeString, String docString) {

        DataDoctor newDoctor = new DataDoctor();

        // Split the string by comma
        String[] values = docString.split(",");

        // Assign values to variables

        String fieldValue = "";
        fieldValue = values[0].trim();
        if (doctorNumbeString.equalsIgnoreCase(fieldValue)) {
            newDoctor.setId(i);
            newDoctor.setDoctorNumber(values[0]);
            newDoctor.setFirstName(values[1]);
            newDoctor.setLastName(values[2]);
            newDoctor.setTelephone(values[3]);
            newDoctor.setAddress(values[4]);
            newDoctor.setImage(values[5]);

            newDoctor.setDayLabelMonday(Integer.parseInt(values[6]));
            newDoctor.setDayLabelTuesday(Integer.parseInt(values[7]));
            newDoctor.setDayLabelWednesday(Integer.parseInt(values[8]));
            newDoctor.setDayLabelThursday(Integer.parseInt(values[9]));
            newDoctor.setDayLabelFriday(Integer.parseInt(values[10]));
            newDoctor.setDayLabelSaturday(Integer.parseInt(values[11]));
            newDoctor.setDayLabelSunday(Integer.parseInt(values[12]));
            // System.out.println(newDoctor);

        } else {
            newDoctor = null;
        }
        return newDoctor;
    }

    /**
     * Updates the data in the file at the specified line number.
     *
     * @param args               The updated data to be written to the file.
     * @param lineNumberToUpdate The line number in the file to update.
     */
    public static void updateData(String args, int lineNumberToUpdate) {
        // Specify the file path
        String filePath = "doctordetails.txt";

        // Read the file content into a list
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update the desired line
        // int lineNumberToUpdate = 1; // Specify the line number to update
        if (lineNumberToUpdate >= 0 && lineNumberToUpdate < lines.size()) {
            String updatedLine = args; // Provide the updated data
            lines.set(lineNumberToUpdate, updatedLine);
        } else {
            System.out.println("Invalid line number.");
            return;
        }

        // Write the modified content back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println("File updated successfully.");
    }

    /**
     * Retrieves doctor data as an ObservableList for use in JavaFX UI components.
     *
     * @return An ObservableList containing doctor data.
     */
    public ObservableList<DataDoctor> getDoctorDataObservableList() {
        // Your logic to retrieve doctor data dynamically goes here
        // For example, you can call a method that retrieves data from a database or a
        // file
        // In this example, let's create some sample data dynamically
        ObservableList<DataDoctor> data = FXCollections.observableArrayList();
        data.addAll(searchRecord(""));
        // Add your logic here to dynamically populate the data list

        return data;
    }
}