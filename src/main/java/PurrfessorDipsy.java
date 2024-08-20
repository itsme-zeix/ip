import java.util.Scanner;
import java.util.HashMap;

public class PurrfessorDipsy {
    private static HashMap<String, Boolean> tasks = new HashMap<>(100);

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        printWelcomeMessage();
        while (true) {
            String userInput = inputScanner.nextLine();
            doAction(userInput);
        }
    }

    private static void printTerminalLine() {
        String TERMINAL_LINE = "―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――";
        System.out.println(TERMINAL_LINE);
    }

    private static void printWelcomeMessage() {
        printTerminalLine();
        System.out.println("Meowdy! I'm Purrfessor Dipsy, Keeper of the Cozy Sunbeam " +
                "and Purrtector of the Realm of Naps.\nHow can I purrvide assistance? " +
                "Purrhaps I can lend a paw!");
        printTerminalLine();
    }

    private static void printExitMessage() {
        System.out.println("Fur-well friend, stay paw-sitive!");
        printTerminalLine();
    }

    private static void doAction(String userInput) {
        String trimmedInput = userInput.trim().toLowerCase();

        switch (trimmedInput) {
            case "":
                // do nothing
                break;
            case "bye":
                printExitMessage();
                System.exit(0);
                break;
            case "list":
                printMemory();
                break;
            default:
                echoUserInput(userInput);
                saveToMemory(userInput);
                break;
        }
    }

    private static void echoUserInput(String userInput) {
        printTerminalLine();
        System.out.println(userInput);
        printTerminalLine();
    }

    private static void saveToMemory(String userInput) {
        tasks.put(userInput, false);
    }

    private static void printMemory() {
        int counter = 1;
        printTerminalLine();
        System.out.println("Here are the tasks in your list:");
        for ( String task : tasks.keySet()) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        printTerminalLine();
    }


}
