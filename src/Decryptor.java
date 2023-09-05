import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Decryptor extends CryptOperations{

    public void decrypt(String filename, int key) {
        StringBuilder data = CryptOperations.readFromInitFile(filename);
        String resultFileName;
        char symbol;
        int dataPosition;
        int encryptedDataPosition;
        Path decryptedFileName = null;

        //create new filename in the same directory as filename
        try {
            resultFileName = "decryptedFile" + CryptOperations.getFilePostfix();
            decryptedFileName = Files.createFile(Paths.get(Path.of(filename).getParent().toString(), resultFileName));
        }catch (IOException e){

            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(decryptedFileName.toString())) {
            for (int i = 0; i < data.length(); i++) {
                symbol = data.charAt(i);
                dataPosition = CryptOperations.ALPHABET.indexOf(symbol);
                encryptedDataPosition = (dataPosition>=key)?(dataPosition-key):(CryptOperations.ALPHABET_SIZE-Math.abs(dataPosition-key));
                writer.write(CryptOperations.ALPHABET.charAt(encryptedDataPosition));
            }
            System.out.println("File got decrypted! See the result: "+ decryptedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
