package plasmyt.plasm.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plasmyt.plasm.parser.KeyValue;
import plasmyt.plasm.parser.ValueParser;

public class PlasmReader implements AutoCloseable {
    private final BufferedReader reader;
    private final ValueParser valueParser;
    private final Map<String, Object> data;

    public PlasmReader(String filePath, Map<String, Object> data) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
        this.data = data;
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
        Pattern pattern = Pattern.compile("^\\s*([^:]+)\\s*:\\s*(.*?)\\s*$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String key = matcher.group(1).trim();
            String valueString = matcher.group(2).trim();
            try (PlasmReader reader = new PlasmReader(filePath, data)) {
                Object value = reader.parseValue(valueString);
                return new KeyValue(key, value);
            }
        }
        return null;
    }

    public String getElement(String key) throws IOException {
        String line;
        Pattern pattern = Pattern.compile("^\\s*" + key + "\\s*:\\s*(.*)$");
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                return matcher.group(1).trim();
            }
        }
        return null;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}