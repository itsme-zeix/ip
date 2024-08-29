package purrfessordipsy.parser;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateParserTest{
    @Test
    public void parseDate_ValidDate() {
        String dateStr = "2024-08-28";
        LocalDate parsedDate = DateParser.parseDate(dateStr);
        assertEquals(LocalDate.of(2024, 8, 28), parsedDate);
    }

    @Test
    public void parseDate_InvalidDate() {
        String dateStrOutOfBounds = "2024-02-30"; // Date does not exist
        String dateStrWrongFormat = "2024/08/28"; // Incorrect format

        assertThrows(DateTimeParseException.class, () -> DateParser.parseDate(dateStrOutOfBounds));
        assertThrows(DateTimeParseException.class, () -> DateParser.parseDate(dateStrWrongFormat));
    }

    @Test
    public void formatDateForDisplay() {
        LocalDate date = LocalDate.of(2024, 8, 28);
        String formattedDate = DateParser.formatDateForDisplay(date);
        assertEquals("Aug 28 2024", formattedDate);
    }

    @Test
    public void formatDateForStorage() {
        LocalDate date = LocalDate.of(2024, 8, 28);
        String formattedDate = DateParser.formatDateForStorage(date);
        assertEquals("2024-08-28", formattedDate);
    }
}
