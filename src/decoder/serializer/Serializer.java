package decoder.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davi Lessa
 */
public class Serializer {

    private final GsonBuilder gsonBuilder;
    private Gson gson;

    private static final Logger LOGGER = Logger.getLogger(Serializer.class.getName());

    public Serializer() {
        gsonBuilder = new GsonBuilder();

    }

    public void addTypeAndClass(Class clazz, Object typeAdaptor) {
        gsonBuilder.registerTypeAdapter(clazz, typeAdaptor);
        gson = gsonBuilder.create();
    }

    public <T extends Object> String serialize(T object) {
        return gson.toJson(object);
    }

    public <T extends Object> void serializeFile(T object, String fileTitle) {
        try {
            Writer writer = new FileWriter(fileTitle);
            gson.toJson(object, writer);
            writer.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Erro de io no arquivo json", ex);
        }
    }

    public <T extends Object> T deserialize(String json, Class<T> clazz) {
        T object = gson.fromJson(json, clazz);
        return object;
    }
}
