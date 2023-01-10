package io_streams_exercises;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;

public class ConfigMapHw {

    public String getValueFromConfigMap(Path configMapFilePath, String keyName) throws IOException {
        if (configMapFilePath == null || keyName == null) {
            return null;
        }
	    File file = configMapFilePath.toFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Optional<String> result = bufferedReader.lines().filter(s -> s.contains(keyName)).findFirst();
        if(result.isEmpty()) return null;
        return result.get().split("=")[1];
    }

}
