import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encryptor extends CryptOperations {
    private static final int KEY = 6;

    private static final String fileNameIndicator = "encryptedFile";

    public void encrypt(String filename) {
        StringBuilder data = CryptOperations.readFromInitFile(filename);
        String symbol;
        int dataPosition;
        int encryptedDataPosition;
        Path encryptedFileName = null;

        //create new filename in the same directory as filename
        encryptedFileName = CryptOperations.createResultFile(filename, fileNameIndicator, CryptOperations.getFilePostfix());

        try (FileWriter writer = new FileWriter(encryptedFileName.toString())) {
            for (int i = 0; i < data.length(); i++) {
                symbol = data.substring(i, i + 1);
                dataPosition = CryptOperations.ALPHABET.indexOf(symbol);
                encryptedDataPosition = getEncryptedDataPosition(dataPosition, KEY);
                writer.write(CryptOperations.ALPHABET.charAt(encryptedDataPosition));
            }
            System.out.println("File got encrypted! See the result: " + encryptedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getEncryptedDataPosition(int dataPosition, int KEY) {
        return (dataPosition + KEY) % CryptOperations.ALPHABET_SIZE;
    }


    }
