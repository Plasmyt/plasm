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

    public Map<String, Object> processFile() {
        try (PlasmReader fileReader = new PlasmReader(filePath)) {
            Function<String, KeyValue> parser = (line) -> {
                try {
                    return fileReader.defaultParser(line, filePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };
            return fileReader.readValues(parser);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
