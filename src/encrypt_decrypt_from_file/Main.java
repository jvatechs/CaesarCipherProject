package encrypt_decrypt_from_file;

import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_basic.Encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends HelpReading {
    public static void main(String[] args) {
//        readFileAndEncrypt(Path.of("Moriarty_letter_to_Sherlock.txt"), 6);
//        System.out.println("*".repeat(15));
//
//        readFileAndDecrypt(Path.of("encrypted_with_key_6.txt"), 6);
    }

    public static String readFileAndEncrypt(Path path, int key) {
//        System.out.println(Encryption.encrypt(helpRead(path), key));
        return Encryption.encrypt(helpRead(path), key);
    }

    public static String readFileAndDecrypt(Path path, int key) {
//        System.out.println(Decryption.decrypt(helpRead(path), key));
        return Decryption.decrypt(helpRead(path), key);
    }
}
