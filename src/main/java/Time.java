import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    private LocalDateTime dateTime;

    private Time(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Time parseDateTime(String dateTimeStr) {
        return new Time(LocalDateTime.parse(dateTimeStr, formatter));
    }

    public String getDateTime() {
        return this.dateTime.format(printFormatter);
    }
}
