import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class APIBooksTests {

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://restful-booker.herokuapp.com/";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addCookie("Cookie", "token=abc123")
                .build();
    }

    @Test
    public void createBookingTest() {
        CreateBookingBody body = new CreateBookingBody().builder()
                .firstname("testName")
                .lastname("testLastName")
                .totalprice(1000)
                .depositpaid(true)
                .date(BookingDates.builder().build())
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
        Response response = RestAssured.given().log().all().get("/booking");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void changePriceTest() {
        ChangePriceBody body = new ChangePriceBody();
        body.setTotalprice(2000);
        Response response = RestAssured.given()
                .body(body)
                .patch("/booking/{id}", "92");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateBookingTest() {
        UpdateBookingBody body = new UpdateBookingBody().builder()
                .firstname("newName")
                .lastname("newLastName")
                .totalprice(111)
                .depositpaid(true)
                .date(BookingDates.builder().build())
                .additionalneeds("newTest")
                .build();

        Response response = RestAssured.given()
                .body(body)
                .put("/booking/{id}", "35");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteBookingTest() {
        Response response = RestAssured.delete("/booking/{id}","19");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
