package encrypt_decrypt_basic;

public class Encryption extends Common{

    public static void main(String[] args) {
        System.out.println(alphabet.length());
        System.out.println(encrypt("Давай позанимаемсяblabla!?", 40));
    }
    public static String encrypt(String text, int key) {
        StringBuilder encrypted = checkKeyAndCreateSBuilder(key);

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (notFromAlphabet(current)) {
                appendNotFromAlphabet(encrypted, current);
            } else {
                buildText(alphabet, createNewAlphabet(key), encrypted, current);
            }

        }
        return encrypted.toString();
    }

}
