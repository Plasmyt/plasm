package plasmyt.plasm;

import java.io.IOException;
import java.util.Map;

import plasmyt.plasm.ast.ASTBuilder;
import plasmyt.plasm.ast.nodes.PlasmListNode;
import plasmyt.plasm.reader.PlasmProcessor;
import plasmyt.plasm.reader.PlasmReader;

public class Plasm {
    private final String filePath;

    public Plasm(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Object> getData() throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("File path is not defined.");
        }
        PlasmProcessor processor = new PlasmProcessor(filePath);
        return processor.processFile();
    }

    public PlasmListNode buildAST() {
        try (PlasmReader fileReader = new PlasmReader(filePath)) {
            ASTBuilder astBuilder = new ASTBuilder();
            return astBuilder.buildAST(fileReader.readLines());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\usugo\\IdeaProjects\\plasm\\src\\main\\resources\\path\\plasm1.psm";
        String keyToFind = "address";

        try (PlasmReader reader = new PlasmReader(filePath)) {
            Map<String, Object> values = reader.readValues();
            if (values != null) {
                Object value = values.get(keyToFind);
                if (value != null) {
                    System.out.println("Key Value: " + keyToFind);
                    System.out.println("Value: " + value);
                } else {
                    System.out.println("Value not found");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}