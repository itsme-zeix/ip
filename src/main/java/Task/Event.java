package Task;

public class Event extends Task {
    String start;
    String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String formatForCSV() {
        String res = super.formatToCSV();
        res += DELIMITER + wrapInQuotes(start);
        res += DELIMITER + wrapInQuotes(end);
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
