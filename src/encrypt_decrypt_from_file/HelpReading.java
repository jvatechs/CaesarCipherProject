package encrypt_decrypt_from_file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelpReading {
    protected static String helpRead (Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
