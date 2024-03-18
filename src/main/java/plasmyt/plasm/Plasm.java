package plasmyt.plasm;

import java.util.Map;

import plasmyt.plasm.reader.PlasmProcessor;

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
        Plasm plasm = new Plasm(filePath);
        Map<String, Object> data = plasm.getData();
        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("An error occurred while processing the file.");
        }
    }
}