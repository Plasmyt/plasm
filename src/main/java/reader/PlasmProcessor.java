package reader;

import java.io.IOException;
import java.util.Map;
import parser.KeyValueParser;

public class PlasmProcessor {
    private final String filePath;

    public PlasmProcessor(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Object> processFile() {
        try (PlasmReader fileReader = new PlasmReader(filePath)) {
            String[] lines = fileReader.readLines().toArray(new String[0]);

            KeyValueParser keyValueParser = new KeyValueParser();
            return keyValueParser.parse(lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        String filePath = "C:\\Users\\usugo\\IdeaProjects\\plasm\\src\\main\\resources\\path\\plasm1.psm";
        PlasmProcessor processor = new PlasmProcessor(filePath);
        Map<String, Object> values = processor.processFile();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}