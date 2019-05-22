package decoder.http;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Davi Lessa
 */
public class SendHTTPbyPOST {

    private String url;

    private static final Logger LOGGER = Logger.getLogger(SendHTTPbyPOST.class.getName());

    public SendHTTPbyPOST(String url) {
        this.url = url;
    }

    public String sendFileByPost() {
        File file = new File ("answer.json");
        HttpEntity entity = MultipartEntityBuilder.create()
                .addPart("answer", new FileBody(file))
                .build();

        HttpPost request = new HttpPost(url);
        request.setEntity(entity);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao executar requisição", e);
        }

        if (response != null){
           return response.toString();
        }
        return null;
    }

}
