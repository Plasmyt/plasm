package plasmyt.plasm.ast;

import plasmyt.plasm.ast.nodes.PlasmFileNode;
import plasmyt.plasm.ast.nodes.PlasmListNode;
import plasmyt.plasm.parser.ValueParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ASTBuilder {
    private static final Pattern KEY_VALUE_PATTERN = Pattern.compile("([^:]+):\\s*(\"([^\"]*)\"|([^,]+))");

    private final ValueParser valueParser;

    public ASTBuilder() {
        this.valueParser = new ValueParser();
    }

    public PlasmListNode buildAST(String[] lines) {
        List<PlasmFileNode> fileNodes = new ArrayList<>();
        for (String line : lines) {
            PlasmFileNode plasmFileNode = parsePlasmFileNode(line);
            if (plasmFileNode != null) {
                fileNodes.add(plasmFileNode);
            }
        }
        return new PlasmListNode(fileNodes.toArray(new PlasmFileNode[0]));
    }

    private PlasmFileNode parsePlasmFileNode(String line) {
        Matcher matcher = KEY_VALUE_PATTERN.matcher(line);
        if (matcher.matches()) {
            String key = matcher.group(1).trim();
            String valueString = matcher.group(3);
            Object value = valueParser.parseValue(valueString);
            return new PlasmFileNode(key, (String) value);
        }
        return null;
    }
}
