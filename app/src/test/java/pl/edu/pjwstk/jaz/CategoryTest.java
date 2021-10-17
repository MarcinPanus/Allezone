package pl.edu.pjwstk.jaz;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.CategoryRequest;
import pl.edu.pjwstk.jaz.Request.LoginRequest;
import pl.edu.pjwstk.jaz.Request.RegisterRequest;
import pl.edu.pjwstk.jaz.Request.SectionRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class CategoryTest {

    @LocalServerPort
    int port;


    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Before
    public void beforeClass() throws Exception {
        // @formatter:off
        given()
                .body(new RegisterRequest("admin","adminpass"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("user1","userpass1"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        var response = given()
                .when()
                .body(new LoginRequest("admin", "adminpass"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new SectionRequest("Sekcja"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void createCategoryByAdminShouldResponseStatus200() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("admin", "adminpass"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("Kategoria"))
                .contentType(ContentType.JSON)
                .post("/api/sections/Sekcja/addCategory")
                .thenReturn();
        // @formatter:on
    }

    @Test
    public void createCategoryByUserShouldResponseStatus403() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("Kategoria"))
                .contentType(ContentType.JSON)
                .post("/api/sections/Sekcja/addCategory")
                .then()
                .statusCode(403);
        // @formatter:on
    }


    @Test
    public void updateSectionByAdminShouldResponseStatus200() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("admin", "adminpass"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("Kategoria"))
                .contentType(ContentType.JSON)
                .post("/api/sections/Sekcja/addCategory")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("NowaKategoria"))
                .contentType(ContentType.JSON)
                .patch("/api/updateCategory/Kategoria")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void updateSectionByUserShouldResponseStatus403() {
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("admin", "adminpass"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("Kategoria"))
                .contentType(ContentType.JSON)
                .post("/api/sections/Sekcja/addCategory")
                .thenReturn();
        var response2 = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response2.getCookies())
                .body(new CategoryRequest("NowaKategoria"))
                .contentType(ContentType.JSON)
                .patch("/api/updateCategory/Kategoria")
                .then()
                .statusCode(403);
        // @formatter:on
    }
}
