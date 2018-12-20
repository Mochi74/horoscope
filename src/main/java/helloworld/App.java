package helloworld;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import org.json.JSONException;
import org.json.JSONObject;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<JSONObject, Object> {

    public Object handleRequest(final JSONObject input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        Horoscope toto=new Horoscope();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");


/*        Signes signe = gson.fromJson(input,Signes.class);*/
        return input.getJSONObject("signe");
        /*try {
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            //String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);
            String output = toto.call("belier");
            System.out.println(output);
            return new GatewayResponse(output, headers, 200);

        } catch (IOException e) {
            return new GatewayResponse("{}", headers, 500);
        }*/
    }

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
}
