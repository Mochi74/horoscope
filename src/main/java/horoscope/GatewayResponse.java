package horoscope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * POJO containing response object for API Gateway.
 */
public class GatewayResponse {

    private final String content;
    private final Map<String, String> headers;
    private final int statusCode;

    public GatewayResponse(final String content, final Map<String, String> headers, final int statusCode) {
        this.statusCode = statusCode;
        this.content = content;
        this.headers = Collections.unmodifiableMap(new HashMap<>(headers));
    }

    public String getContent() {
        return content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
