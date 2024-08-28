package PurrfessorDipsy.Task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static final String DELIMITER = "|";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    protected String wrapInQuotes(String str) {
        return "\"" + str + "\"";
    }

    protected String getTaskType() {
        return "Task";
    }

    public LocalDate getRelevantDate() {
        return null; // By default, no relevant date for a generic task
    }

    public String formatToCSV() {
        String[] arr = new String[]{
                wrapInQuotes(this.getStatusIcon()),
                wrapInQuotes(this.getTaskType()),
                wrapInQuotes(this.description)
        };
        return String.join(DELIMITER, arr);
    }

    /**
     * Checks if the task description contains the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in the description.
     * @return true if the keyword is found in the description, false otherwise.
     */
    public boolean hasKeywordInDescription(String keyword) {
        if (keyword == null || description == null) return false;
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}