package plasmyt.plasm.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueParser {

    private static final Pattern LIST_ITEM_PATTERN = Pattern.compile("\"(.*?)\"|[^,]+");

    public Object parseValue(String valueString) {
        if (valueString.startsWith("*")) {
            return parseListValue(valueString);
        } else {
            return parseSingleValue(valueString);
        }
    }

    private Object parseSingleValue(String valueString) {
        Matcher matcher = LIST_ITEM_PATTERN.matcher(valueString);
        if (matcher.matches()) {
            String value = matcher.group(1);
            return (value != null) ? value : matcher.group();
        } else {
            return null;
        }
    }

    private List<Object> parseListValue(String valueString) {
        List<Object> list = new ArrayList<>();
        Matcher matcher = LIST_ITEM_PATTERN.matcher(valueString.substring(1));
        while (matcher.find()) {
            String item = matcher.group(1);
            if (item != null) {
                list.add(item);
            } else {
                list.add(matcher.group());
            }
        }
        return list;
    }
}
