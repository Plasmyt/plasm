package plasmyt.plasm.ast.nodes;

import plasmyt.plasm.ast.ASTNode;

public class PlasmFileNode extends ASTNode {
    private final String name;
    private final String path;
    private final String content;

    public PlasmFileNode(String name, String path, String content) {
        this.name = name;
        this.path = path;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }
}