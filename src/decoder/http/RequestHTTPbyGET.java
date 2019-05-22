package decoder.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Davi Lessa
 */
public class RequestHTTPbyGET {

    private URL url;
    private static final Logger LOGGER = Logger.getLogger(RequestHTTPbyGET.class.getName());

    public RequestHTTPbyGET(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE,"Erro ao carregar url", e);
        }
    }

    public String getStringByRequest() {
        String returnedContent = null;
        try {
            HttpURLConnection con = null;
            if (url != null) {
                con = (HttpsURLConnection) url.openConnection();
            }
            if (con != null) {
                if (con.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                    }
                    in.close();
                    returnedContent = sb.toString();
                } else {
                    LOGGER.severe(con.getResponseMessage());
                }
            }
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE,"Erro ao realizar a requisicao http",  ioe);
        }
        
        return returnedContent;
    }

}
