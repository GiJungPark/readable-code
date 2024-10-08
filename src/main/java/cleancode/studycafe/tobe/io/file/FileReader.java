package cleancode.studycafe.tobe.io.file;

import cleancode.studycafe.tobe.exception.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    public static List<String> readFrom(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new FileReaderException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
