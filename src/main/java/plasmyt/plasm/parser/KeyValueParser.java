package plasmyt.plasm.parser;

import java.util.HashMap;
import java.util.Map;
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
            Object value = valueParser.parseValue(valueString);
            return new KeyValue(key, value);
        }
        return null;
    }
}
