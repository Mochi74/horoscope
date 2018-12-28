package horoscope;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Signes, Object> {

    public Object handleRequest(final Signes input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        Horoscope horoscope=new Horoscope();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        try {
            /* get horoscope from sign defined in input */
            Out description = horoscope.get(input.getSigne());
            return description;


        } catch (IOException e) {
            return new GatewayResponse(new JSONObject("{ Oups I can't reach your horoscope }"), headers, 500);
        }
    }

}
