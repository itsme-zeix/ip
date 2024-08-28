package PurrfessorDipsy.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Task.Task;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

/**
 * Represents a command to mark a task as done or undone in the task list.
 * The command follows the format "mark <task_number>" or "unmark <task_number>".
 */
public class MarkCommand extends Command {

    /** Regular expression pattern to parse the mark or unmark command input. */
    private static final Pattern MARK_PATTERN = Pattern.compile("(mark|unmark) (\\d+)");

    /**
     * Constructs a MarkCommand with the specified user input, task list, and UI handler.
     *
     * @param userInput The user input that triggered this command.
     * @param tasks The task list associated with this command.
     * @param ui The UI handler for interacting with the user.
     */
    public MarkCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the MarkCommand by parsing the user input and marking the specified task
     * as done or undone in the task list.
     * If the input is invalid or the task number is out of bounds, an exception is thrown.
     *
     * @throws InvalidCommandException If the command format is invalid or the task number is out of bounds.
     */
    @Override
    public void execute() throws InvalidCommandException {
        Matcher markMatcher = MARK_PATTERN.matcher(super.userInput);
        if (markMatcher.matches()) {
            String action = markMatcher.group(1);
            int userGivenIndex = Integer.parseInt(markMatcher.group(2));
            int index = userGivenIndex - 1; // Since tasks are 0-indexed.
            if (index >= 0 && index < tasks.getSize()) {
                if (action.equals("mark")) {
                    markTaskAsDone(index);
                } else {
                    markTaskAsUndone(index);
                }
            } else {
                throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_INDEX);
            }
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_MARK_COMMAND);
        }
    }

    /**
     * Marks the task at the specified index as done, prints a confirmation message to the user,
     * and saves the task list to the local disk.
     *
     * @param index The index of the task to mark as done.
     */
    private void markTaskAsDone(int index) {
        Task task = tasks.getTask(index);
        task.markAsDone();
        ui.printMarkTaskDoneMessage(task);
        saveTasksToLocalDisk();
    }

    /**
     * Marks the task at the specified index as undone, prints a confirmation message to the user,
     * and saves the task list to the local disk.
     *
     * @param index The index of the task to mark as undone.
     */
    private void markTaskAsUndone(int index) {
        Task task = tasks.getTask(index);
        task.markAsUndone();
        ui.printMarkTaskUndoneMessage(task);
        saveTasksToLocalDisk();
    }
}
