package dipsy.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dipsy.exception.InvalidCommandException;
import dipsy.task.Task;
import dipsy.tasklist.TaskList;
import dipsy.ui.Ui;



/**
 * Represents a command to delete a task from the task list.
 * The command follows the format "delete &lt;task_number&gt;".
 */
public class DeleteCommand extends Command {

    /** Regular expression pattern to parse the delete command input. */
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d+)");

    /**
     * Constructs a DeleteCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public DeleteCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the DeleteCommand by parsing the user input and deleting the specified task from the task list.
     *
     * @return A message indicating that the task has been successfully deleted, including the details of the
     *      deleted task and the updated number of tasks remaining in the task list.
     * @throws InvalidCommandException If the command format is invalid or the task number is out of bounds.
     */
    @Override
    public String execute() throws InvalidCommandException {
        Matcher matcher = DELETE_PATTERN.matcher(userInput);

        if (!matcher.matches()) {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_DELETE_COMMAND);
        }

        int index = Integer.parseInt(matcher.group(1));
        Task deletedTask = tasks.deleteTask(index);

        return ui.getTaskDeletedMessage(deletedTask, tasks.getSize());
    }
}
