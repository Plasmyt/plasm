package plasmyt.plasm.ast.nodes;

import plasmyt.plasm.ast.ASTNode;

public class KeyValueNode extends ASTNode {
    private final String key;
    private final Object value;

    public KeyValueNode(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}