package decoder;

import decoder.http.RequestHTTPbyGET;
import decoder.http.SendHTTPbyPOST;
import decoder.serializer.Serializer;
import decoder.serializer.AnswerSerializer;
import decoder.model.Answer;
import java.io.File;
import java.util.logging.Logger;

/**
 *
 * @author Davi
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String URL_GET = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=afb2f8bd372cdebb3c3e5e5d20199d3b1b522549";
    private static final String URL_POST = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=afb2f8bd372cdebb3c3e5e5d20199d3b1b522549";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RequestHTTPbyGET requestHTTPbyGET = new RequestHTTPbyGET(URL_GET);

        LOGGER.info("Realizando a requisicao HTTTP para: " + URL_GET);
        String resultHTTP = requestHTTPbyGET.getStringByRequest();

        if (resultHTTP != null) {
            LOGGER.info("Retorno da requisicaoHTTP: " + resultHTTP);

            Serializer serializer = new Serializer();
            serializer.addTypeAndClass(Answer.class, new AnswerSerializer());

            Answer answer = serializer.deserialize(resultHTTP, Answer.class);

            if (answer != null) {

                answer.decrypt();
                LOGGER.info("Resultado da decodificação: " + answer.getDecrypted());

                answer.generateCryptographycResume();
                LOGGER.info("Resultado do SHA-1: " + answer.getCryptographycResume());
                
                serializer.serializeFile(answer, "answer.json");
                
                SendHTTPbyPOST sendHTTPbyPOST = new SendHTTPbyPOST(URL_POST);
                
                LOGGER.info("Enviando arquivo json em POST no HTTTP para: " + URL_POST);
                String result = sendHTTPbyPOST.sendFileByPost();
                LOGGER.info("Resultado da requisicao: " + result);
                
            }
        }
    }

}
