package plasmyt.plasm.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import plasmyt.plasm.parser.KeyValue;
import plasmyt.plasm.parser.ValueParser;

public class PlasmReader implements AutoCloseable {
    private final BufferedReader reader;
    private final ValueParser valueParser;

    public PlasmReader(String filePath) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
        this.valueParser = new ValueParser();
    }

    public Map<String, Object> readValues(Function<String, KeyValue> parser) throws IOException {
        Map<String, Object> values = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            KeyValue keyValue = parser.apply(line);
            if (keyValue != null) {
                values.put(keyValue.key(), parseValue(keyValue.value()));
            }
        }
        return values;
    }

    private Object parseValue(Object value) {
        if (value instanceof String strValue) {
            return valueParser.parseValue(strValue);
        } else if (value instanceof String[] strArray) {
            Object[] parsedItems = new Object[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                parsedItems[i] = valueParser.parseValue(strArray[i]);
            }
            return parsedItems;
        }
        return value;
    }

    public KeyValue defaultParser(String line, String filePath) throws IOException {
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
            String key = parts[0].trim();
            String valueString = parts[1].trim();
            try (PlasmReader reader = new PlasmReader(filePath)) {
                Object value = reader.parseValue(valueString);
                return new KeyValue(key, value);
            }
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
