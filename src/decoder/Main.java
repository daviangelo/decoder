package decoder;

/**
 *
 * @author Davi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Answer answer = new Answer();
        answer.setEncrypted("d oljhlud udsrvd pduurp vdowrx vreuh r fdfkruur fdqvdgr");
        answer.setKeyNumber(3);
        answer.decrypt();
        System.out.println(answer.getDecrypted());
        answer.generateCryptographycResume();
        System.out.println(answer.getCryptographycResume());

    }

}
