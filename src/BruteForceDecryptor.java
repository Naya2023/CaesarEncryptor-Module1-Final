import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BruteForceDecryptor extends CryptOperations{

    public void bruteForceDecrypt(String filename){
        StringBuilder data = CryptOperations.readFromInitFile(filename);
        String resultFileName;
        StringBuilder resultData = new StringBuilder();
        String symbol;
        int dataPosition;
        int encryptedDataPosition;
        Path decryptedFileName = null;

        //create new filename in the same directory as filename
        try {
            resultFileName = "bruteDecryptedFile" + CryptOperations.getFilePostfix();
            decryptedFileName = Files.createFile(Paths.get(Path.of(filename).getParent().toString(), resultFileName));
        }catch (IOException e){
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(decryptedFileName.toString())) {
            for(int k = 1; k<CryptOperations.ALPHABET_SIZE; k++) {
                resultData.delete(0, data.length());
                for (int i = 0; i < data.length(); i++) {
                    symbol = data.substring(i, i + 1);
                    dataPosition = CryptOperations.ALPHABET.indexOf(symbol);
                    encryptedDataPosition = (dataPosition>=k)?(dataPosition-k):(CryptOperations.ALPHABET_SIZE-Math.abs(dataPosition-k));
                    resultData.append(CryptOperations.ALPHABET.charAt(encryptedDataPosition));
                }
                //check if result data is a valid text
                if(isValidResult(resultData)){
                    writer.write(resultData.toString());
                    break;
                }
            }
            System.out.println("File got decrypted using Brute Force Method! See the result: "+ decryptedFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidResult(StringBuilder data){
        boolean result=true;
        String punctuation = ".,!?:;";
        String strData = data.toString();
        int quotesCount = 0;
        int openBracketCount = 0;
        int closeBracketCount = 0;

        //check if all the quotes are closed
        for(int i=0; i<strData.length()-1; i++)
            if(strData.charAt(i)=='\"')
                quotesCount++;
        if(quotesCount%2!=0)
            return false;

        //check if all the open brackets number and closed brackets number are even
        for(int i=0, j=0; i<strData.length(); i++, j++) {
            if (strData.charAt(i) == '(')
                openBracketCount++;
            if (strData.charAt(j) == ')')
                closeBracketCount++;
        }
        if(openBracketCount!=closeBracketCount)
            return false;

        //check if punctuation is followed by spaces
        for(int i=0; i<strData.length()-1; i++)
            for (int j = 0; j < punctuation.length(); j++)
                if (strData.charAt(i)==punctuation.charAt(j)) {
                    result = strData.charAt(i + 1) == ' ';
                    if(!result)
                        return false;
                }

        return result;
    }
}
