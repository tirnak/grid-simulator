import app.GridSimulatorApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * TODO Add tests for actual result, add unit tests, add Rest Assured specification for the endpoints
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = GridSimulatorApplication.class)
public class GridSimulatorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	/**
	 * TODO cleanup file with simulation results after getting endpoint
	 */
	@Test
    public void testSimulateOk() throws Exception {
	    RestAssured.port = Integer.valueOf(port);

	    Map params = Map.of("ticks", 100);
	    String body = new ObjectMapper().writeValueAsString(params);

        given()
	            .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/simulate")
                .then()
                .statusCode(HttpStatus.OK.value());
	}

}