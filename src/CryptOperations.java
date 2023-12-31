import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CryptOperations {
    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪБЮЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъбьэюя.,!?\"()-:; ";
    public static final int ALPHABET_SIZE = ALPHABET.length();

    protected static StringBuilder readFromInitFile(String filename) {
        StringBuilder data = new StringBuilder();
        try (FileReader reader = new FileReader(filename)) {
            int character;
            while ((character = reader.read()) != -1) {
                data.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    //creating unique postfixes for every new file in the directory regardless of how many times the program has run before
    protected static String getFilePostfix() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" dd-MM-yy HH-mm-ss");
        return now.format(format);
    }

    protected static Path createResultFile(String initFileName, String fileNameIndicator, String filePostfix) {
        Path decryptedFileName = null;
        try {
            String resultFileName = fileNameIndicator + filePostfix;
            decryptedFileName = Files.createFile(Paths.get(Path.of(initFileName).getParent().toString(), resultFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decryptedFileName;
    }
}

