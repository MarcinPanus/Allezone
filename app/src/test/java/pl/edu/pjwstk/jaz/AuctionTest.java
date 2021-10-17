package pl.edu.pjwstk.jaz;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.Request.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AuctionTest {

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
        given()
                .body(new RegisterRequest("user2","userpass2"))
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
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new CategoryRequest("Kategoria"))
                .contentType(ContentType.JSON)
                .post("/api/sections/Sekcja/addCategory")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void createAuctionByUserShouldResponseStatus200() {

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("wysokosc", "120"));

        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("http://zdjecie", 1));
        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new AuctionRequest("Aukcja", "opis", 300, "Kategoria", parameterRequestList, photoRequestList))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .then()
                .statusCode(200);
        // @formatter:on
    }


    @Test
    public void updateAuctionByUserShouldResponseStatus200() {

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("wysokosc", "120"));

        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("http://zdjecie", 1));

        List<ParameterRequest> updateParameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("wysokosc1", "120"));

        List<PhotoRequest> updatePhotoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("http://zdjecie1", 1));

        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new AuctionRequest("Aukcja", "opis", 300, "Kategoria", parameterRequestList, photoRequestList))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        var response2 = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response2.getCookies())
                .body(new AuctionRequest("Aukcja1", "opis1", 3001, "Kategoria", updateParameterRequestList, updatePhotoRequestList))
                .contentType(ContentType.JSON)
                .patch("/api/updateAuction/Aukcja")
                .then()
                .statusCode(200);
        // @formatter:on
    }

    @Test
    public void updateAuctionByOtherUserShouldResponseStatus403() {

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("wysokosc", "120"));

        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("http://zdjecie", 1));

        List<ParameterRequest> updateParameterRequestList = new ArrayList<>();
        updateParameterRequestList.add(new ParameterRequest("wysokosc", "120"));

        List<PhotoRequest> updatePhotoRequestList = new ArrayList<>();
        updatePhotoRequestList.add(new PhotoRequest("http://zdjecie", 1));

        // @formatter:off
        var response = given()
                .when()
                .body(new LoginRequest("user1", "userpass1"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new AuctionRequest("Aukcja", "opis", 300, "Kategoria", parameterRequestList, photoRequestList))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        var response2 = given()
                .when()
                .body(new LoginRequest("user2", "userpass2"))
                .contentType((ContentType.JSON))
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response2.getCookies())
                .body(new AuctionRequest("Aukcja1", "opis1", 3001, "Kategoria", updateParameterRequestList, updatePhotoRequestList))
                .contentType(ContentType.JSON)
                .patch("/api/updateAuction/Aukcja")
                .then()
                .statusCode(403);
        // @formatter:on
    }
}
