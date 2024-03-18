package plasmyt.plasm.reader;

import java.io.IOException;
import java.util.Map;


public class PlasmProcessor {
    private final String filePath;

    public PlasmProcessor(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Object> processFile() {
        try (PlasmReader fileReader = new PlasmReader(filePath)) {
            return fileReader.readValues();
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