package encrypt_decrypt_from_file;

import encrypt_decrypt_basic.Decryption;
import encrypt_decrypt_basic.Encryption;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends HelpReading {

    public static String readFileAndEncrypt(Path path, int key) {
        return Encryption.encrypt(helpRead(path), key);
    }

    public static String readFileAndDecrypt(Path path, int key) {
        return Decryption.decrypt(helpRead(path), key);
    }
}
