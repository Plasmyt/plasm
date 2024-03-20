package plasmyt.plasm.parser;

import plasmyt.plasm.parser.KeyValue;
import plasmyt.plasm.parser.ValueParser;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyValueParser {
    private static final Pattern KEY_VALUE_PATTERN = Pattern.compile("([^:]+):\\s*(\"([^\"]*)\"|([^,]+))");

    private final ValueParser valueParser;

    public KeyValueParser() {
        this.valueParser = new ValueParser();
    }

    public Map<String, Object> parse(String[] lines) {
        Map<String, Object> values = new HashMap<>();
        for (String line : lines) {
            KeyValue keyValue = parseKeyValue(line);
            if (keyValue != null) {
                values.put(keyValue.key(), keyValue.value());
            }
        }
        return values;
    }

    private KeyValue parseKeyValue(String line) {
        Matcher matcher = KEY_VALUE_PATTERN.matcher(line);
        if (matcher.matches()) {
            String key = matcher.group(1).trim();
            String valueString = matcher.group(3);
            Object value = parseValue(valueString);
            return new KeyValue(key, value);
        }
        return null;
    }

    private Object parseValue(String valueString) {
        if (valueString.startsWith("{") && valueString.endsWith("}")) {
            return parseListValue(valueString.substring(1, valueString.length() - 1));
        } else if (valueString.startsWith("*")) {
            return valueParser.parseValue(valueString);
        } else {
            return valueString;
        }
    }

    private List<Object> parseListValue(String valueString) {
        List<Object> list = new ArrayList<>();
        StringBuilder currentItem = new StringBuilder();
        boolean insideBraces = false;

        for (String line : valueString.split("\\n")) {
            System.out.println("Current line: " + line);
            if (insideBraces) {
                System.out.println("Inside braces");
                if (line.trim().startsWith("*")) {
                    list.add(valueParser.parseValue(line.trim()));
                } else if (line.trim().startsWith("}")) {
                    insideBraces = false;
                    break;
                }
            } else {
                if (line.trim().startsWith("{")) {
                    System.out.println("Inside braces");
                    insideBraces = true;
                }
            }
        }

        return list;
    }
}