package PurrfessorDipsy.Command;

import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Task.Task;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code FindCommand} class handles the execution of the 'find' command,
 * which searches for tasks whose descriptions contain a specified keyword.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {

    /** The regex pattern used to match the 'find' command syntax and extract the keyword. */
    private static final Pattern FIND_PATTERN = Pattern.compile("^find (.+)");

    /**
     * Constructs a {@code FindCommand} to search for tasks with a specific keyword in their description.
     *
     * @param userInput The user's input containing the 'find' command and the keyword.
     * @param tasks     The task list where tasks will be searched.
     * @param ui        The user interface to display messages and results.
     */
    public FindCommand(String userInput, TaskList tasks, Ui ui) {
        super(userInput, tasks, ui);
    }

    /**
     * Executes the 'find' command. It searches the task list for tasks whose descriptions
     * contain the specified keyword. If the keyword is missing or invalid, an exception is thrown.
     *
     * @throws InvalidCommandException if the command does not follow the expected syntax
     *                                 or if the keyword is missing.
     */
    @Override
    public void execute() throws InvalidCommandException {
        Matcher matcher = FIND_PATTERN.matcher(userInput);
        String keyword = matcher.group(1);

        if (matcher.matches()) {
            ArrayList<Task> tasksMatchingKeyword = tasks.getTasksByKeyword(keyword);
            ui.printTasksMatchingKeyword(tasksMatchingKeyword);
        } else {
            throw new InvalidCommandException(InvalidCommandException.ErrorType.INVALID_FIND_COMMAND);
        }
    }
}
