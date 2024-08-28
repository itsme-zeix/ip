package purrfessordipsy.task;

import java.time.LocalDate;

import static purrfessordipsy.parser.DateParser.*;

/**
 * Represents an event task with a start and end date.
 * An Event task has a description and specific dates that mark the beginning and end of the event.
 * This class extends the base {@link Task} class and adds functionality specific to handling events.
 */
public class Event extends Task {

    /** The start date of the event. */
    LocalDate start;

    /** The end date of the event. */
    LocalDate end;

    /**
     * Constructs a new {@code Event} task with the specified description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new {@code Event} task with the specified description, completion status, start date, and end date.
     *
     * @param description The description of the event task.
     * @param isDone The completion status of the task (true if the task is completed, false otherwise).
     * @param start The start date of the event.
     * @param end The end date of the event.
     */
    public Event(String description, boolean isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the type of the task, which is "Event" for this class.
     * This method is used to identify the specific type of task.
     *
     * @return The string "Event" indicating the type of task.
     */
    @Override
    protected String getTaskType() {
        return "Event";
    }

    /**
     * Returns the relevant date for this task, which is the start date of the event.
     * This method is used to retrieve the most relevant date associated with the event.
     *
     * @return The {@link LocalDate} object representing the start date of the event.
     */
    @Override
    public LocalDate getRelevantDate() {
        return this.start; // Or the most relevant date for your use case
    }

    /**
     * Formats the Event task into a CSV-compatible string for storage, including the start and end dates.
     * This format is used to save the task information to a file.
     *
     * @return A string representing the Event task in CSV format.
     */
    @Override
    public String formatToCSV() {
        String res = super.formatToCSV();
        res += DELIMITER + wrapInQuotes(formatDateForStorage(start));
        res += DELIMITER + wrapInQuotes(formatDateForStorage(end));
        return res;
    }

    /**
     * Returns a string representation of the Event task, including its description, start date, and end date.
     * This method is used to display the task in a human-readable format, showing its type, description, and relevant dates.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateForDisplay(start) +
                " to: " + formatDateForDisplay(end) + ")";
    }
}
