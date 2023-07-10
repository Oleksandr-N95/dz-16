import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.sql.Date.valueOf;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDates {
    private String checkin;
    private String checkout;
    public static String generateDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.valueOf(valueOf(localDate));
    }
}

