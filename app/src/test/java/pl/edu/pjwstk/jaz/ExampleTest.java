package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.LoginRequest;
import pl.edu.pjwstk.jaz.Request.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@IntegrationTest
public class ExampleTest {

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
    public void should_respond_to_readiness_request() {
        var response = given()
                .when()
                .body(new LoginRequest("marcin", "haslo"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
         given()
                .when()
                 .cookies(response.getCookies())
                 .get("/api/auth0/is-ready")
                .thenReturn();
        var statusCode = response.statusCode();
        System.out.println(statusCode);
        assertThat(statusCode).isEqualTo(200);
    }
}