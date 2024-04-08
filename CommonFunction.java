/**
 * This class represents a collection of common functionalities that can be implemented
 * by other classes. It serves as an interface for defining common operations such as
 * adding, clearing, saving, editing, printing, deleting, and validating data.
 *  @author Anura Damayantha
 * @version 1.0
 * @since 2024-04-08
 */
public interface CommonFunction {

    /**
     * Adds a new record.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean addNew();

    /**
     * Clears all fields, resetting them to their default values.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean clear();

    /**
     * Saves the current record.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean save();

    /**
     * Edits an existing record.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean edit();

    /**
     * Prints the current record.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean print();

    /**
     * Deletes the current record.
     *
     * @return True if the operation is successful, false otherwise.
     */
    boolean delete();

    /**
     * Validates data before saving.
     * This method should be called before attempting to save any data to ensure its integrity.
     *
     * @return True if the data is valid, false otherwise.
     */
    boolean saveValidate();
}
