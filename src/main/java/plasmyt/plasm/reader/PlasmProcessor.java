package plasmyt.plasm.reader;

import plasmyt.plasm.parser.KeyValue;

import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

public class PlasmProcessor {
    private final String filePath;

    public PlasmProcessor(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Object> processFile() throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path is not defined.");
        }
        try (PlasmReader fileReader = new PlasmReader(filePath)) {
            return fileReader.readValues();
        }
    }
}