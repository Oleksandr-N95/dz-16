import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDates {
    private CharSequence date;
    private LocalDate localDate;
    private String CheckIn;
    private String CheckOut;
    public void CheckIn(CharSequence date) {
        LocalDate localDate = LocalDate.parse(date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CheckIn.valueOf(localDate);
    }
    public void CheckOut(CharSequence date) {
        LocalDate localDate = LocalDate.parse(date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        CheckOut.valueOf(localDate);
    }
    public LocalDate getLocalDate() {
        return localDate;
    }
}

