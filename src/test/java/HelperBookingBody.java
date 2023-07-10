import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelperBookingBody {

    private int bookingId;
    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private BookingDates Date;
    private String additionalneeds;
}
