package io_streams_exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SearchFilesByExtension {
    public long getNumberOfFilesWithExtension(Path pathToStartSearch, String extension) throws IOException {
        if(pathToStartSearch==null||extension==null||extension.isEmpty()) return 0;
        return Files.find(pathToStartSearch, 100,(p, attr)-> String.valueOf(p).endsWith(extension)).count();

    }
}
