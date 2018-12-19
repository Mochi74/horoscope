package helloworld;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


public class Horoscope {
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    public String call(String nom) throws IOException {
        String val;
        String jsonPrettyPrintString = "";

        URL url = new URL("https://www.asiaflash.com/horoscope/rss_horojour_" + nom + ".xml");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        /* urlConnection.setRequestProperty("Accept-Encoding", "identity"); */
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            val = readStream(in);
        } finally {
            urlConnection.disconnect();
        }

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(val);
            jsonPrettyPrintString = xmlJSONObj.getJSONObject("rss").getJSONObject("channel").getJSONObject("item").getString("description");
            System.out.println(jsonPrettyPrintString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        return jsonPrettyPrintString;
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}





