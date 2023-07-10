import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class APIBooksTests {
    private int bookingId;
    @BeforeMethod
    public void setUp() {
        CreateTokenBody body = new CreateTokenBody("admin", "password123");
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(body)
                .post("http://restful-booker.herokuapp.com/auth");
        response.then().statusCode(200);
        response.prettyPrint();

        String authToken = response.jsonPath().getString("token");

        RestAssured.baseURI = "http://restful-booker.herokuapp.com/";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addCookie("token", authToken)
                .build();

        Response getAllResponse = RestAssured.given().log().all().get("/booking");
        getAllResponse.then().statusCode(200);
        getAllResponse.prettyPrint();

        List<Integer> bookingIdsList = getAllResponse.jsonPath().getList("bookingid");
        if (!bookingIdsList.isEmpty()) {
            bookingId = bookingIdsList.get(0);
        }
    }
    @Test
    public void createBookingTest() {
        BookingDates bookingdates = new BookingDates("2023-07-01","2023-07-05");
        HelperBookingBody body = new HelperBookingBody().builder()
                .firstname("testName")
                .lastname("testLastName")
                .totalprice(1000)
                .depositpaid(true)
                .bookingdates(bookingdates)
                .additionalneeds("test")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .post("/booking");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getAllBookingIdTest() {
        List<Integer> bookingIdsList = new ArrayList<>();
        if (!bookingIdsList.isEmpty()) {
            bookingId = bookingIdsList.get(0);
        }
        for (int id : bookingIdsList) {
            System.out.println("bookingId:" + id);
        }
    }

    @Test
    public void changePriceTest() {
        HelperBookingBody body = new HelperBookingBody();
        body.setTotalprice(2000);
        Response response = RestAssured.given()
                .body(body)
                .patch("/booking/{id}", bookingId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateBookingTest() {
        BookingDates bookingdates = new BookingDates("2023-07-01","2023-07-05");
        HelperBookingBody body = new HelperBookingBody().builder()
                .firstname("newName")
                .lastname("newLastName")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(bookingdates)
                .additionalneeds("newTest")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .put("/booking/{id}", bookingId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteBookingTest() {
        Response response = RestAssured.delete("/booking/{id}",bookingId);
        response.then().statusCode(201);
        response.prettyPrint();
    }
}
