package plasmyt.plasm;

import java.io.IOException;
import java.util.Map;

import plasmyt.plasm.reader.PlasmProcessor;
import plasmyt.plasm.reader.PlasmReader;

public class Plasm {
    private final String filePath;

    public Plasm(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Object> getData() {
        PlasmProcessor processor = new PlasmProcessor(filePath);
        return processor.processFile();
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\usugo\\IdeaProjects\\plasm\\src\\main\\resources\\path\\plasm1.psm";
        String keyToFind = "name";

        try (PlasmReader reader = new PlasmReader(filePath, null)) {
            String value = reader.getElement(keyToFind);
            if (value != null) {
                System.out.println("Key Value: " + keyToFind);
                System.out.println("Value: " + value);
            } else {
                System.out.println("Value not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}