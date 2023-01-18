package encrypt_decrypt_from_file;

import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_basic.Encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends HelpReading {
    public static void main(String[] args) {
        readFileAndEncrypt(Path.of("src/Moriarty_letter_to_Sherlock.txt"), 0);
        System.out.println("*".repeat(15));

        readFileAndDecrypt(Path.of("src/encrypted_with_key_6.txt"), 6);
    }

    public static void readFileAndEncrypt(Path path, int key) {
        System.out.println(Encryption.encrypt(helpRead(path), key));
    }

    public static void readFileAndDecrypt(Path path, int key) {

        System.out.println(Decryption.decrypt(helpRead(path), key));
    }
}
