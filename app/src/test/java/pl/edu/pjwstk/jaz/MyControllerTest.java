package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.LoginRequest;
import pl.edu.pjwstk.jaz.Request.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class MyControllerTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        // @formatter:off
        given()
                .body(new RegisterRequest("username","password"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("username2","password2"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        // @formatter:on
    }
    @Test
    public void should_respond_to_springUser_request_if_user_is_admin() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username", "password"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/springUser")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_respond_to_springAdmin_request_if_user_is_admin() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username", "password"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/springAdmin")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_respond_to_springUser_request_if_user_is_default() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username2", "password2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/springUser")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_not_respond_to_springAdmin_request_if_user_is_default_with_status_code_403() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username2", "password2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/springAdmin")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on
    }

    @Test
    public void should_respond_to_filterUser_request_if_user_is_admin() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username", "password"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/filtrUser")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_respond_to_filterAdmin_request_if_user_is_admin() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username", "password"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/filtrAdmin")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_respond_to_filterUser_request_if_user_is_default() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username2", "password2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/filtrUser")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void should_not_respond_to_filterAdmin_request_if_user_is_default_status_code_403() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("username2", "password2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/filtrAdmin")
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
        // @formatter:on
    }

    @Test
    public void should_not_login_if_password_and_username_is_wrong_status_code_401() {
        // @formatter:off
        given()
                .when()
                .body(new LoginRequest("wrongusername", "wrongpassword"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        // @formatter:on
    }

    @Test
    public void should_not_login_if_password_is_wrong_status_code_401() {
        // @formatter:off
        given()
                .when()
                .body(new LoginRequest("username", "wrongpassword"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        // @formatter:on
    }

    @Test
    public void should_not_login_if_username_is_wrong_status_code_401() {
        // @formatter:off
        given()
                .when()
                .body(new LoginRequest("wrongusername", "password"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
        // @formatter:on
    }


}
