package time;

import exception.TimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private LocalDateTime dateTime;

    private Time(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Time parseDateTime(String dateTimeStr) throws TimeFormatException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            return new Time(dateTime);
        } catch (Exception e) {
            throw new TimeFormatException("Invalid date/time format. Please use 'dd/MM/yyyy HH:mm' format.");
        }
    }

    public String getDateTime() {
        return this.dateTime.format(printFormatter);
    }
}
