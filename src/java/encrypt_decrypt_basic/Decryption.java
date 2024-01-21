package encrypt_decrypt_basic;

public class Decryption extends Common {

    public static String decrypt(String text, int key) {
        StringBuilder decrypted = checkKeyAndCreateSBuilder(key);

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (notFromAlphabet(current)) {
                appendNotFromAlphabet(decrypted, current);
            } else {
                buildText(createNewAlphabet(key), alphabet, decrypted, current);
            }
        }

        return decrypted.toString();
    }


}
