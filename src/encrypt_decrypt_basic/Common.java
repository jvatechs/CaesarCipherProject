package encrypt_decrypt_basic;

class Common {
    protected static final String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? ";

    protected static String createNewAlphabet (int key) {
        return alphabet.substring(key) + alphabet.substring(0, key);
    }

    protected static boolean notFromAlphabet(char current) {
        char upperCased = Character.toUpperCase(current);
        return alphabet.indexOf(upperCased) == -1;
    }

    protected static void appendNotFromAlphabet(StringBuilder sb, char current) {
        sb.append(current);
    }

    protected static boolean isUpperCased(char character) {
        return Character.isUpperCase(character);
    }

    protected static void buildText (String alphabet1, String alphabet2, StringBuilder sb, char current) {
        int index = alphabet1.indexOf(Character.toUpperCase(current));
        char newLetter = alphabet2.charAt(index);
        checkUpperCaseAndAppend(current, sb, newLetter);
    }

    private static void checkUpperCaseAndAppend(char current, StringBuilder sb, char letter) {
        if (!isUpperCased(current)) {
            sb.append(Character.toLowerCase(letter));
        } else {
            sb.append(letter);
        }
    }

    protected static StringBuilder checkKeyAndCreateSBuilder(int anyKey) throws StringIndexOutOfBoundsException {
        if (anyKey >= alphabet.length()) {
            throw new StringIndexOutOfBoundsException("Введите действительный ключ от 0 до " + alphabet.length() + " (НЕВКЛЮЧИТЕЛЬНО) !");
        } else {
            return new StringBuilder();
        }
    }

}
