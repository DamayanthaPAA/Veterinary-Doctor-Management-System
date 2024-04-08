import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

/**
 * The CommonMethods class provides utility methods for displaying messages,
 * handling text fields, and showing alert dialogs.
 * It follows the singleton design pattern to ensure only one instance is
 * created throughout the application.
 * @author Anura Damayantha
 * @version 1.0
 * @since 2024-04-08
 */
public class CommonMethods {
    // Private static instance variable
    private static CommonMethods instance;

    // Private constructor to prevent instantiation from outside
    private CommonMethods() {
        // Constructor implementation
    }

    /**
     * Retrieves the instance of the CommonMethods class.
     * If an instance doesn't exist, it creates a new one.
     *
     * @return The CommonMethods instance.
     */
    public static synchronized CommonMethods getInstance() {
        // Lazy initialization: create the instance only when needed
        if (instance == null) {
            instance = new CommonMethods();
        }
        return instance;
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Checks if a TextField is empty.
     *
     * @param textField The TextField to be checked.
     * @return True if the TextField is empty, false otherwise.
     */
    public boolean isEmptyTextField(TextField textField) {
        String fieldValue = textField.getText(); // Get the text value from the TextField
        return fieldValue.isEmpty();
    }

    /**
     * Shows an alert dialog with the specified title and message.
     *
     * @param title   The title of the alert dialog.
     * @param message The message to be displayed.
     */
    public void showAlert(String title, String message) {
        // Create an Alert dialog
        Alert alert = new Alert(AlertType.INFORMATION);

        // Set the title and message
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);

        // Display the alert dialog
        alert.showAndWait();
    }

    /**
     * Shows an alert dialog with the specified title, message, and type.
     *
     * @param title     The title of the alert dialog.
     * @param message   The message to be displayed.
     * @param alertType The type of the alert dialog (0: INFORMATION, 1: WARNING, 2:
     *                  CONFIRMATION, 3: DEFAULT).
     */
    public void showAlert(String title, String message, int alertType) {
        // Create an Alert dialog
        Alert alert = new Alert(AlertType.INFORMATION);
        switch (alertType) {
            case 0:
                alert = new Alert(AlertType.INFORMATION);
                break;

            case 1:
                alert = new Alert(AlertType.WARNING);
                break;

            case 2:
                alert = new Alert(AlertType.CONFIRMATION);
                break;

            case 3:
                alert = new Alert(AlertType.INFORMATION);
                break;

            default:
                alert = new Alert(AlertType.INFORMATION);
                break;
        }

        // Set the title and message
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);

        // Display the alert dialog
        alert.showAndWait();
    }

    /**
     * Sets focus to the specified TextField.
     *
     * @param objectTextField The TextField to set focus to.
     */
    public void setFocusTextfield(TextField objectTextField) {

        try {
            // Set focus to the TextField
            objectTextField.requestFocus();
        } catch (Exception e) {

        }

    }

}
