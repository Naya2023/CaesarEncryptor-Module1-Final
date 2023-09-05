import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encryptor extends CryptOperations{
    private static final int KEY = 6;

    public void encrypt(String filename){
        StringBuilder data = CryptOperations.readFromInitFile(filename);
        String resultFileName;
        String symbol;
        int dataPosition;
        int encryptedDataPosition;
        Path encryptedFileName = null;

        //create new filename in the same directory as filename
        try {
            resultFileName = "encryptedFile" + CryptOperations.getFilePostfix();
            encryptedFileName = Files.createFile(Paths.get(Path.of(filename).getParent().toString(), resultFileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(encryptedFileName.toString())) {
            for (int i = 0; i < data.length(); i++) {
                symbol = data.substring(i, i+1);
                dataPosition = CryptOperations.ALPHABET.indexOf(symbol);
                encryptedDataPosition = (dataPosition+KEY)%CryptOperations.ALPHABET_SIZE;
                writer.write(CryptOperations.ALPHABET.charAt(encryptedDataPosition));
            }
            System.out.println("File got encrypted! See the result: "+ encryptedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
