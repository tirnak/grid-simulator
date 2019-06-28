import app.GridSimulatorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GridSimulatorApplication.class)
public class GridSimulatorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSimulateOk() throws Exception {
        given()
                .param("ticks", 1)
                .when()
                .put("/simulate")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void testSimulate1() throws Exception {
        String filename = given()
                .param("ticks", 1)
                .when()
                .put("/simulate")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                    .path("filename");

        byte[] bytes = Files.readAllBytes(Paths.get(filename));

        int x = 4 + new Random().nextInt();
    }
}