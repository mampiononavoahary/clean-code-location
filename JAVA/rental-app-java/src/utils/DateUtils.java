package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValidRange(LocalDate start, LocalDate end) {
        return start != null && end != null && !end.isBefore(start.plusDays(1));
    }
}
