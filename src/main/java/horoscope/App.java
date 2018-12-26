package horoscope;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

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
            String description = horoscope.call(input.getSigne());
            Output output = new Output(description);
            return output;

        } catch (IOException e) {
            return new GatewayResponse("{}", headers, 500);
        }
    }



 /*
    private String getPageContents(String address) throws IOException{
        BufferedReader br = null;
        StringJoiner lines = new StringJoiner(System.lineSeparator());
        try {
            URL url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toString();
    }
*/
}
