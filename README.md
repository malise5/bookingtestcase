**RESTful Booking API Test Automation**

---

**Introduction:**

This project aims to automate the testing of the 'Update Booking' endpoint of the RESTful Booker API. It includes various test cases to ensure the endpoint functions correctly under different scenarios.

---

**Prerequisites:**

-   Java Development Kit (JDK) installed on your system
-   Maven installed on your system

---

**Setup Instructions:**

1. Clone the repository to your local machine:

    ```
    git clone git@github.com:malise5/bookingtestcase.git
    ```

2. Navigate to the project directory:

    ```
    cd project_directory
    ```

3. Open the project in your preferred Java IDE.

4. Ensure that all dependencies specified in the `pom.xml` file are resolved.

---

**Test Cases:**

1. **updateBookingSuccessfully():**
    - Description: Update an existing booking with valid data.
    - Expected Result: The booking is successfully updated, and the server returns a status code of 200.
2. **attemptToUpdateNonExistentBooking():**
    - Description: Attempt to update a booking that does not exist.
    - Expected Result: The server returns a status code of 403, indicating that the booking does not exist.
3. **updateBookingWithInvalidData():**
    - Description: Update a booking with invalid data (e.g., invalid date format).
    - Expected Result: The server returns a status code of 403, indicating that the request contains invalid data.
4. **updateBookingWithPartialData():**
    - Description: Update a booking with only some fields provided.
    - Expected Result: The server returns a status code of 403, indicating that the request is incomplete.
5. **updateBookingWithEmptyData():**
    - Description: Attempt to update a booking without providing any data.
    - Expected Result: The server returns a status code of 403, indicating that the request is empty.
6. **updateBookingWithDifferentUsersToken():**
    - Description: Attempt to update a booking with a different user's authentication token.
    - Expected Result: The server returns a status code of 403, indicating that the user is not authorized to perform the action.

---

**Execution:**

-   Run the test suite using your preferred test runner or execute individual test cases as needed.

```
mvn test
```

---

**Contributing:**

Contributions to improve and expand the test suite are welcome.

---

**License:**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Acknowledgments:**

-   Special thanks to the developers of RESTful Booker API for providing the endpoint to test.
-   This project is inspired by the need for robust and comprehensive API testing.

---
