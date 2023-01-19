package brute_force;

import java.nio.file.Path;
import static encrypt_decrypt_from_file.Main.readFileAndDecrypt;

public class BruteForcedText {
    public static void main(String[] args) {
        System.out.println(getBruteForcedText(Path.of("encrypted_with_key_6.txt")));
    }

    public static String getBruteForcedText(Path file) {
        int key = HackTextKey.BruteForce(file);
        String aboutKey = "The finded key is : " + key + "\n";
        return aboutKey + readFileAndDecrypt(file, key);
    }
}
