package PurrfessorDipsy;

import PurrfessorDipsy.Command.*;
import PurrfessorDipsy.Exception.InvalidCommandException;
import PurrfessorDipsy.Exception.InvalidDateException;
import PurrfessorDipsy.Exception.UnknownCommandException;
import PurrfessorDipsy.Parser.Parser;
import PurrfessorDipsy.Storage.Storage;
import PurrfessorDipsy.TaskList.TaskList;
import PurrfessorDipsy.Ui.Ui;

/**
 * The {@code PurrfessorDipsy} class is the main entry point for the program.
 * It initializes the user interface, task list, and runs the command loop to process user input.
 * This class manages the overall flow of the application, from startup to termination.
 */
public class PurrfessorDipsy {

    /** The user interface for interacting with the user. */
    private final Ui ui;

    /** The list of tasks managed by the application. */
    private final TaskList taskList;

    /**
     * Constructs a new {@code PurrfessorDipsy} object, initializing the user interface and task list.
     */
    public PurrfessorDipsy() {
        this.ui = new Ui();
        this.taskList = Storage.load();  // Load tasks from local disk when the application starts
    }

    /**
     * The main method that serves as the entry point for the application.
     * It creates an instance of {@code PurrfessorDipsy} and starts the program.
     *
     * @param launchArgs The command-line arguments passed to the program (unused).
     */
    public static void main(String[] launchArgs) {
        PurrfessorDipsy purrfessorDipsy = new PurrfessorDipsy();
        purrfessorDipsy.run();
    }

    /**
     * Runs the main loop of the program, printing the welcome message and processing commands until
     * the exit command is given.
     */
    public void run() {
        ui.printWelcomeMessage();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Runs the command loop that continuously prompts the user for input and executes commands
     * until the "bye" command is given. This method delegates command parsing to the {@link Parser} class
     * and handles the execution of commands and any exceptions that arise.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command = null;
        do {
            try {
                String userInput = ui.getInput();
                command = Parser.parseCommand(userInput, taskList, ui);
                try {
                    command.execute();
                } catch (InvalidCommandException e) {
                    ui.printErrorMessage(e.getMessage());
                } catch (InvalidDateException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            } catch (UnknownCommandException e) {
                ui.printErrorMessage(e.getMessage());
            } catch (Exception e) {
                ui.printErrorMessage("An unexpected error occurred: " + e.getMessage());
            }
        } while (!ByeCommand.isExit(command));
    }

    /**
     * Exits the program, printing a farewell message and terminating the application.
     */
    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }
}
