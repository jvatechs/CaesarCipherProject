package brute_force;

import encrypt_decrypt_basic.Common;
import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_from_file.HelpReading;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackTextKey extends HelpReading {
    public static void main(String[] args) {
        int key = BruteForce(Path.of("src/encrypted_with_key_6.txt"));
        System.out.println("The key is :" + key);
    }

    public static int BruteForce(Path path) {
        int countOfKeys = Common.getAlphabet().length() - 1;
        String encrypted = helpRead(path);
        int countOfSpaces = 0;
        int key = 0;

        for (int i = 1; i < countOfKeys; i++) {
            int currentSpaces = findSymbolOccurrences(encrypted, " ", i);
            if (currentSpaces > countOfSpaces) {
                countOfSpaces = currentSpaces;
                key = i;
            }
        }
        return key;
    }

    private static int findSymbolOccurrences(String encrypted, String symbol, int key) {
        Pattern pattern = Pattern.compile(symbol);
        Matcher matcher = pattern.matcher(getDecrypted(encrypted, key));
        return findMatches(matcher, 0);
    }

    private static String getDecrypted(String encrypted, int key) {
        return Decryption.decrypt(encrypted, key);
    }

    private static int findMatches(Matcher matcher, int startCount) {
        int currentSpaces = startCount;
        while (matcher.find()) {
            currentSpaces++;
        }
        return currentSpaces;
    }
}
