package brute_force;

import encrypt_decrypt_basic.Decryption;

import java.nio.file.Path;

import static brute_force.HackTextKey.encrypted;

public class BruteForcedText {
    public static void main(String[] args) {
        System.out.println(getBruteForcedText(Path.of("src/encrypted_with_key_6.txt")));
    }

    public static String getBruteForcedText(Path file) {
        int key = HackTextKey.BruteForce(file);
        return Decryption.decrypt(encrypted, key);
    }
}
