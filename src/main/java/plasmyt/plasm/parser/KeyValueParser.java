package plasmyt.plasm.parser;

import java.util.HashMap;
import java.util.Map;

public class KeyValueParser {
    private final ValueParser valueParser;

    public KeyValueParser() {
        this.valueParser = new ValueParser();
    }

    public Map<String, Object> parse(String[] lines) {
        Map<String, Object> values = new HashMap<>();
        for (String line : lines) {
            KeyValue keyValue = parseKeyValue(line);
            if (keyValue != null) {
                values.put(keyValue.getKey(), keyValue.getValue());
            }
        }
        return values;
    }

    private KeyValue parseKeyValue(String line) {
        String[] parts = line.split(":", 2);
        if (parts.length == 2) {
            String key = parts[0].trim();
            String valueString = parts[1].trim();
            Object value = valueParser.parseValue(valueString);
            return new KeyValue(key, value);
        }
        return null;
    }
}