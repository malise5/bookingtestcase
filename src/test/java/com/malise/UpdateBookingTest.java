package com.malise;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateBookingTest {

  private static final String BASE_URL = "https://restful-booker.herokuapp.com";
  private static final String TOKEN = "abc123";

  @Test
  public void updateBookingSuccessfully() {
    // Construct request body with updated booking data
    JSONObject requestBody = new JSONObject();
    requestBody.put("firstname", "Updated Firstname");
    requestBody.put("lastname", "Updated Lastname");
    requestBody.put("totalprice", 200);
    requestBody.put("depositpaid", true);

    JSONObject bookingDates = new JSONObject();
    bookingDates.put("checkin", "2023-02-20");
    bookingDates.put("checkout", "2023-02-25");
    requestBody.put("bookingdates", bookingDates);

    requestBody.put("additionalneeds", "Extra bed");

    // Send PUT request to update booking
    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + TOKEN)
        .body(requestBody.toString())
        .put("/booking/1");

    // Assert response status code is 200
    response.then().statusCode(200);
  }

  @Test
  public void attemptToUpdateNonExistentBooking() {
    JSONObject requestBody = new JSONObject();
    requestBody.put("firstname", "Updated Firstname");

    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + TOKEN)
        .body(requestBody.toString())
        .put("/booking/999");

    response.then().statusCode(403);
  }

  @Test
  public void updateBookingWithInvalidData() {
    JSONObject requestBody = new JSONObject();
    requestBody.put("bookingdates", "Invalid Date Format");

    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + TOKEN)
        .body(requestBody.toString())
        .put("/booking/1");

    response.then().statusCode(403);
  }

  @Test
  public void updateBookingWithPartialData() {
    JSONObject requestBody = new JSONObject();
    requestBody.put("lastname", "Updated Lastname");

    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + TOKEN)
        .body(requestBody.toString())
        .put("/booking/1");

    response.then().statusCode(403);
  }

  @Test
  public void updateBookingWithEmptyData() {
    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + TOKEN)
        .body("{}")
        .put("/booking/1");

    response.then().statusCode(403);
  }

  @Test
  public void updateBookingWithDifferentUsersToken() {
    JSONObject requestBody = new JSONObject();
    requestBody.put("firstname", "Updated Firstname");

    // Use a different token for a different user
    String differentToken = "xyz987";

    Response response = given()
        .baseUri(BASE_URL)
        .header("Content-Type", ContentType.JSON)
        .header("Accept", ContentType.JSON)
        .header("Cookie", "token=" + differentToken)
        .body(requestBody.toString())
        .put("/booking/1");

    response.then().statusCode(403);
  }
}
