import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        boolean isExit = false;
        //text menu
        System.out.println("******** Caesar Encryptor ********");
        System.out.println("Enter 'ENCRYPT' to encrypt data");
        System.out.println("Enter 'DECRYPT' to decrypt data");
        System.out.println("Enter 'BRUTE' to decrypt data using brute force method");
        System.out.println("Enter 'EXIT' to exit Caesar Encryptor");
        System.out.println("************************");

        //operation choice
        try (Scanner scan = new Scanner(System.in)) {
            while (!isExit) {
                System.out.println("Choose an operation: ");
                String userInput = scan.hasNext()?scan.nextLine():"";
                switch (userInput) {
                    case "EXIT" -> isExit = true;
                    case "ENCRYPT" -> {
                        Encryptor encryptor = new Encryptor();
                        encryptor.encrypt(getValidFileName());
                    }
                    case "DECRYPT" -> {
                        Decryptor decryptor = new Decryptor();
                        System.out.println("Enter decryption key: ");
                        int enterKey = scan.nextInt();
                        decryptor.decrypt(getValidFileName(), enterKey);
                    }
                    case "BRUTE" -> {
                        BruteForceDecryptor brute = new BruteForceDecryptor();
                        brute.bruteForceDecrypt(getValidFileName());
                    }
                }
            }
        }
        System.out.println("Thank you for encrypting with us! Caesar Encryptor is looking forward to working with you again!");
    }

    public static String getValidFileName() {
        String filename = "";
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Enter file path: ");
            filename = scan.nextLine();
            if (Files.exists(Path.of(filename))) {
                break;
            } else
                System.out.println("File not found... Enter another location: ");
        }

        return filename;
    }
}