package plasmyt.plasm.reader;

import plasmyt.plasm.parser.KeyValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlasmReader implements AutoCloseable {
    private final BufferedReader reader;
    private final Pattern keyValuePattern = Pattern.compile("^\\s*([^:]+)\\s*:\\s*(.*?)\\s*$");

    public PlasmReader(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("Document path is not defined.");
        }
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public String[] readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines.toArray(new String[0]);
    }

    public KeyValue parseLine(String line) throws IOException {
        line = line.trim();
        if (line.startsWith("{")) {
            Map<String, String> nestedMap = parseNestedObject();
            return new KeyValue("nested_object", nestedMap);
        } else if (line.endsWith("}")) {
            return null;
        } else if (line.startsWith("*")) {
            return defaultParser(line.substring(1).trim(), true);
        } else {
            return defaultParser(line);
        }
    }

    private KeyValue defaultParser(String line) throws IOException {
        return defaultParser(line, false);
    }

    private KeyValue defaultParser(String line, boolean isArrayElement) throws IOException {
        Matcher matcher = keyValuePattern.matcher(line);
        if (matcher.matches()) {
            String key = matcher.group(1).trim();
            String valueString = matcher.group(2).trim();
            if (isArrayElement) {
                key = "*" + key;
            }
            return new KeyValue(key, valueString);
        }
        return null;
    }

    public Map<String, String> parseNestedObject() throws IOException {
        Map<String, String> nestedObject = new HashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.equals("}")) {
                break;
            } else if (line.startsWith("*")) {
                addKeyValueToMap(parseLine(line), nestedObject);
            } else {
                addKeyValueToMap(parseLine(line), nestedObject);
            }
        }

        nestedObject.replaceAll((key, value) -> value.replaceAll(",+$", ""));
        return nestedObject;
    }

    private void addKeyValueToMap(KeyValue keyValue, Map<String, String> map) {
        if (keyValue != null) {
            map.put(keyValue.key(), (String) keyValue.value());
        }
    }

    public Map<String, Object> readValues() throws IOException {
        Map<String, Object> values = new HashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            KeyValue keyValue = parseLine(line);
            if (keyValue != null) {
                Object value = parseValue((String) keyValue.value());
                if (keyValue.key().startsWith("*")) {
                    List<Object> array = (List<Object>) values.getOrDefault(keyValue.key(), new ArrayList<>());
                    array.add(value);
                    values.put(keyValue.key(), array);
                } else {
                    values.put(keyValue.key(), value);
                }
            }
        }
        return values;
    }

    private Object parseValue(Object value) {
        if (value instanceof String strValue) {
            if (strValue.startsWith("{")) {
                try {
                    return parseNestedObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (strValue.startsWith("\"") && strValue.endsWith("\"")) {
                return strValue.substring(1, strValue.length() - 1);
            } else if (strValue.startsWith("*")) {
                return "*" + strValue.substring(1);
            }
        }
        return value;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}