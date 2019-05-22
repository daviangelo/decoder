package decoder;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 *
 * @author Davi
 */
public class Answer {

    private int keyNumber;
    private String token;
    private String encrypted;
    private String decrypted = "";
    private String cryptographycResume;

    public int getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(int keyNumber) {
        this.keyNumber = keyNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getDecrypted() {
        return decrypted;
    }

    public void setDecrypted(String decrypted) {
        this.decrypted = decrypted;
    }

    public String getCryptographycResume() {
        return cryptographycResume;
    }

    public void setCryptographycResume(String cryptographycResume) {
        this.cryptographycResume = cryptographycResume;
    }

    public void decrypt() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getEncrypted().length(); i++) {

            int ascii = getEncrypted().charAt(i);
            if (ascii >= 97 && ascii <= 122) {
                if (ascii - getKeyNumber() < 97) {
                    int outOfBound = ascii - getKeyNumber();
                    int backToTheBound = outOfBound + 26;

                    appendNewCharacterDecrypted(backToTheBound, sb);
                } else {
                    appendNewCharacterDecrypted(ascii - getKeyNumber(), sb);
                }
            } else {
                appendNewCharacterDecrypted(ascii, sb);
            }
        }
        
        setDecrypted(sb.toString());
    }

    private void appendNewCharacterDecrypted(int ascii, StringBuilder sb) {
        sb.append((char) ascii);
    }

    public void generateCryptographycResume() {
        String sha1 = Hashing.sha1().hashString(getDecrypted(), Charsets.UTF_8).toString();
        setCryptographycResume(sha1);
    }

}
