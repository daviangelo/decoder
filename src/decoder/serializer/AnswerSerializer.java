package decoder.serializer;

import decoder.model.Answer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author Davi Lessa
 */
public class AnswerSerializer implements JsonSerializer<Answer>, JsonDeserializer<Answer> {
    
    private enum Key{
        KEY_NUMBER ("numero_casas"), 
        TOKEN ("token"), 
        ENCRYPTED ("cifrado"), 
        DECRYPTED("decifrado"), 
        CRYPTOGRAPHYC_RESUME("resumo_criptografico");
        
        private final String description;

        private Key(String description) {
            this.description = description;
        }
        @Override
        public String toString() {
            return description;
        }
        
        
    }
    
    @Override
    public JsonElement serialize(Answer answer, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.addProperty(Key.KEY_NUMBER.toString(), answer.getKeyNumber());
        jsonObject.addProperty(Key.TOKEN.toString(), answer.getToken());
        jsonObject.addProperty(Key.ENCRYPTED.toString(), answer.getEncrypted());
        jsonObject.addProperty(Key.DECRYPTED.toString(), answer.getDecrypted());
        jsonObject.addProperty(Key.CRYPTOGRAPHYC_RESUME.toString(), answer.getCryptographycResume());
        
        return jsonObject;
    }

    @Override
    public Answer deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonObject jsonObject = je.getAsJsonObject();

        final JsonElement jsonKeyNumber = jsonObject.get(Key.KEY_NUMBER.toString());
        final JsonElement jsonToken= jsonObject.get(Key.TOKEN.toString());
        final JsonElement jsonEncrypted= jsonObject.get(Key.ENCRYPTED.toString());
        final JsonElement jsonDecrypted = jsonObject.get(Key.DECRYPTED.toString());
        final JsonElement jsonCryptographicResume = jsonObject.get(Key.CRYPTOGRAPHYC_RESUME.toString());
        
        Answer answer = new Answer();
        
        if (jsonKeyNumber != null) {
            answer.setKeyNumber(jsonKeyNumber.getAsInt());
        }
        if (jsonToken != null) {
            answer.setToken(jsonToken.getAsString());
        }
        if (jsonEncrypted != null) {
            answer.setEncrypted(jsonEncrypted.getAsString());
        }
        if (jsonDecrypted != null) {
            answer.setDecrypted(jsonDecrypted.getAsString());
        }
        if (jsonCryptographicResume != null){
            answer.setCryptographycResume(jsonCryptographicResume.getAsString());
        }
        
        return answer;
    }
    
}
