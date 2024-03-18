package plasmyt.plasm.parser;

import java.util.ArrayList;
import java.util.List;

public class ValueParser {
    public Object parseValue(String valueString) {
        if (valueString.startsWith("*")) {
            return parseListValue(valueString);
        } else {
            return parseStringValue(valueString);
        }
    }

    private List<Object> parseListValue(String valueString) {
        List<Object> list = new ArrayList<>();
        valueString = valueString.substring(1).trim();
        if (!valueString.isEmpty()) {
            String[] listItems = valueString.split(",");
            for (String item : listItems) {
                item = item.trim();
                if (item.startsWith("\"") && item.endsWith("\"") && item.length() > 1) {
                    item = removeQuotes(item);
                }
                try {
                    list.add(Integer.parseInt(item)); // Eğer integerse ekle
                } catch (NumberFormatException e) {
                    list.add(item); // Değilse string olarak ekle
                }
            }
        }
        return list;
    }

    private String removeQuotes(String item) {
        return item.substring(1, item.length() - 1);
    }

    private Object parseStringValue(String valueString) {
        if (valueString.startsWith("\"") && valueString.endsWith("\"") && valueString.length() > 1) {
            return removeQuotes(valueString);
        } else if (valueString.contains(":")) {
            return valueString;
        } else {
            try {
                return Integer.parseInt(valueString);
            } catch (NumberFormatException e) {
                return valueString;
            }
        }
    }
}