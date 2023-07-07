import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseBookingBody {

    private int bookingId;
    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private BookingDates date;
    private String additionalneeds;
}
