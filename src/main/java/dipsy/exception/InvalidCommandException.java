package dipsy.exception;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 * The exception provides detailed error messages based on the type of invalid command.
 */
public class InvalidCommandException extends Exception {

    /** Enum representing different types of invalid commands. */
    public enum ErrorType {
        INVALID_TODO,
        INVALID_DEADLINE,
        INVALID_EVENT,
        INVALID_MARK_COMMAND,
        INVALID_MARK_INDEX,
        INVALID_DELETE_COMMAND,
        INVALID_DELETE_INDEX,
        INVALID_FIND_COMMAND,
        INVALID_LIST_COMMAND,
    }

    /** The specific type of invalid command that caused the exception. */
    private final ErrorType errorType;

    /**
     * Constructs an InvalidCommandException with the specified error type.
     *
     * @param errorType The specific type of invalid command that caused the exception.
     */
    public InvalidCommandException(ErrorType errorType) {
        super();

        assert errorType != null : "ErrorType should not be null";

        this.errorType = errorType;
    }

    /**
     * Returns a detailed error message based on the specific error type.
     *
     * @return The detailed error message associated with the error type.
     */
    @Override
    public String getMessage() {
        switch (errorType) {
        case INVALID_TODO:
            return "'todo' command requires a description.\nUsage: todo <description>";
        case INVALID_DEADLINE:
            return "'deadline' command requires a 'by' date.\nUsage: deadline <description> /by <day/date/time>";
        case INVALID_EVENT:
            return "'event' command requires a description, a '/from' time, and a '/to' time.\n"
                    + "Usage: event <description> /from <day/date/time> /to <day/date/time>";
        case INVALID_MARK_COMMAND:
            return "'mark' or 'unmark' command requires a valid index.\nUsage: mark <index> or unmark <index>";
        case INVALID_MARK_INDEX:
            return "index given in 'mark/unmark <index>' lies outside of the table's valid range.";
        case INVALID_DELETE_COMMAND:
            return "'delete' command requires a valid index.\nUsage: delete <index>";
        case INVALID_DELETE_INDEX:
            return "index given in 'delete <index>' lies outside of the table's valid range.";
        case INVALID_FIND_COMMAND:
            return "'find' command requires a keyword to search for.\nUsage: find <keyword>";
        case INVALID_LIST_COMMAND:
            return "'list' command usage: 'list' or 'list yyyy-mm-dd' for filtering by date.";
        default:
            assert false : "Unhandled ErrorType: " + errorType;
            return "Unknown invalid command. Please try again.";
        }
    }
}
