package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.LoginRequest;
import pl.edu.pjwstk.jaz.Request.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {

    @BeforeClass
    public static void beforeClass() throws Exception {
        // @formatter:off
        given()
                .body(new RegisterRequest("marcin","haslo"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("marcin2","haslo2"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        // @formatter:on
    }

    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("marcin", "haslo"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/average")
                .then()
                .body("message", equalTo("Please put parameters"));
        // @formatter:on
    }


    @Test
    public void should_remove_trailing_zero_case_1() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("marcin", "haslo"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .when()
                .cookies(response.getCookies())
                .param("numbers", "3,4")
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 3.5"));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero_case_2() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("marcin", "haslo"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .when()
                .cookies(response.getCookies())
                .param("numbers", "4,3,1,7,5")
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 4"));
        // @formatter:on
    }

    @Test
    public void should_remove_trailing_zero_case_3() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("marcin", "haslo"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .when()
                .cookies(response.getCookies())
                .param("numbers", "1,1,2")
                .get("/api/average")
                .then()
                .body("message", equalTo("Average equals: 1.33"));
        // @formatter:on
    }

}
