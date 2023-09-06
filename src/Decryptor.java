import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decryptor extends CryptOperations {

    private static final String fileNameIndicator = "decryptedFile";

    public void decrypt(String filename, int key) {
        StringBuilder data = CryptOperations.readFromInitFile(filename);
        char symbol;
        int dataPosition;
        int decryptedDataPosition;
        Path decryptedFileName = null;

        //create new filename in the same directory as filename
        decryptedFileName = CryptOperations.createResultFile(filename, fileNameIndicator, CryptOperations.getFilePostfix());

        try (FileWriter writer = new FileWriter(decryptedFileName.toString())) {
            for (int i = 0; i < data.length(); i++) {
                symbol = data.charAt(i);
                dataPosition = CryptOperations.ALPHABET.indexOf(symbol);
                decryptedDataPosition = getDecryptedDataPosition(dataPosition, key);
                writer.write(CryptOperations.ALPHABET.charAt(decryptedDataPosition));
            }
            System.out.println("File got decrypted! See the result: " + decryptedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getDecryptedDataPosition(int dataPosition, int key) {
        return (dataPosition >= key) ? (dataPosition - key) : (CryptOperations.ALPHABET_SIZE - Math.abs(dataPosition - key));
    }
}
