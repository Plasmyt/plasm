package parser.nodes;

import parser.ASTNode;

import java.util.Collections;
import java.util.List;

public class StringNode extends ASTNode {
    private final String value;

    public StringNode(String value) {
        super(null); // StringNode'un ASTNode kurucusunu çağırmak için null geçiyoruz, çünkü Token gerekli değil.
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public List<ASTNode> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return value;
    }
}