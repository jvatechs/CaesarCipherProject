package brute_force;

import encrypt_decrypt_basic.Common;
import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_from_file.HelpReading;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HackTextKey extends HelpReading {
    public static String encrypted;

    public static int BruteForce(Path path) {
        int countOfKeys = Common.getAlphabet().length() - 1;
        encrypted = helpRead(path);
        int countOfSpaces = 0;
        int key = 0;

        for (int i = 1; i < countOfKeys; i++) {
            int currentSpaces = findSpaceOccurrences(i);
            if (currentSpaces > countOfSpaces) {
                countOfSpaces = currentSpaces;
                key = i;
            }
        }
        return key;
    }

    private static int findSpaceOccurrences(int key) {
        Pattern pattern = Pattern.compile(" ");
        Matcher matcher = pattern.matcher(getDecrypted(key));
        return findMatches(matcher);
    }

    private static String getDecrypted(int key) {
        return Decryption.decrypt(encrypted, key);
    }

    private static int findMatches(Matcher matcher) {
        int currentSpaces = 0;
        while (matcher.find()) {
            currentSpaces++;
        }
        return currentSpaces;
    }
}
