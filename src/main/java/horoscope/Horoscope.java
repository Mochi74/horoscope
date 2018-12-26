package horoscope;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;


public class Horoscope {
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;

    public String call(String nom) throws IOException {
        String aString = "";
        String val;
        /* normalize sign name to suppress accent */
        nom = Normalizer.normalize(nom.toLowerCase(),Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]","");

        /*
         get the Horoscope from www.asiaflash.com
         Sign is provide as part of the URL
         return is xml
         */
        URL url = new URL("https://www.asiaflash.com/horoscope/rss_horojour_" + nom + ".xml");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            val = readStream(in);
        } finally {
            urlConnection.disconnect();
        }

        /* move xml to Json object and retrieve "rss.channel.item.description" string */
        try {
            JSONObject xmlJSONObj = XML.toJSONObject(val);
            aString = xmlJSONObj.getJSONObject("rss").getJSONObject("channel").getJSONObject("item").getString("description");
            System.out.println(aString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        // returm description string */
        return aString;
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





