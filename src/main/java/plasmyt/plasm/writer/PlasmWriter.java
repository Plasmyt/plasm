package plasmyt.plasm.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class PlasmWriter {
    private final String filePath;

    public PlasmWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(Map<String, Object> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                String line = key + ": " + valueToString(value);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String valueToString(Object value) {
        if (value instanceof String) {
            return "\"" + value + "\"";
        } else if (value instanceof Integer) {
            return value.toString();
        } else if (value instanceof Iterable<?>) {
            StringBuilder sb = new StringBuilder();
            sb.append("* ");
            for (Object item : (Iterable<?>) value) {
                sb.append(item.toString()).append(", ");
            }
            return sb.substring(0, sb.length() - 2);
        }
        return "";
    }
}
