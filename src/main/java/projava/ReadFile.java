package projava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        try {
            Path p = Path.of("text.txta");
            String s = Files.readString(p);
            System.out.println(s);
        } catch (NoSuchFileException e) {
            System.out.println("ファイルが見つかりません:" + e.getFile());;
        }
    }
}
