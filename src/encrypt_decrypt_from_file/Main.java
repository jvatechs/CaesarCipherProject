package encrypt_decrypt_from_file;

import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_basic.Encryption;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        readFileAndEncrypt(Path.of("src/Moriarty_letter_to_Sherlock.txt"), 6);
        System.out.println("*".repeat(15));

        readFileAndDecrypt(Path.of("src/encrypted_with_key_6.txt"), 6);
    }

    public static void readFileAndEncrypt(Path path, int key) {
        System.out.println(Encryption.encrypt(helpRead(path, key), key));
    }

    public static void readFileAndDecrypt(Path path, int key) {
        System.out.println(Decryption.decrypt(helpRead(path, key), key));
    }

    private static String helpRead (Path path, int key) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
