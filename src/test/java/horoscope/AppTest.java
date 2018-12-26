package horoscope;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    GatewayResponse result = (GatewayResponse) app.handleRequest( null, null);
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/json");
    String content = result.getContent();
    assertNotNull(content);
    assertTrue(content.contains("\"horoscope\""));
    //assertTrue(content.contains("\"belier\""));
    //assertTrue(content.contains("\"\""));
  }
}
