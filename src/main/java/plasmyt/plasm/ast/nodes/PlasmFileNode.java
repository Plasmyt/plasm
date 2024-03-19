package plasmyt.plasm.ast.nodes;

import plasmyt.plasm.ast.ASTNode;

public class PlasmFileNode extends ASTNode {
    private final String name;
    private final String path;

    public PlasmFileNode(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}