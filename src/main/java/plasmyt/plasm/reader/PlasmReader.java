package plasmyt.plasm.reader;

import plasmyt.plasm.parser.KeyValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlasmReader extends Reader {
    private final BufferedReader reader;

    public PlasmReader(String filePath) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public Map<String, Object> readValues() throws IOException {
        Map<String, Object> values = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
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
            Object value = parseValue(valueString);
            return new KeyValue(key, value);
        }
        return null;
    }

    private Object parseValue(String valueString) {
        if (valueString.startsWith("\"") && valueString.endsWith("\"") && valueString.length() > 1) {
            return valueString.substring(1, valueString.length() - 1);
        } else if (valueString.equalsIgnoreCase("true") || valueString.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(valueString);
        } else if (valueString.contains(",")) {
            String[] listItems = valueString.split(",");
            List<Object> list = new ArrayList<>();
            for (String item : listItems) {
                list.add(parseValue(item.trim()));
            }
            return list;
        } else {
            try {
                return Integer.parseInt(valueString);
            } catch (NumberFormatException e) {
                return valueString;
            }
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException("This method is not supported in PlasmReader.");
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}