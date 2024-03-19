package plasmyt.plasm.ast.nodes;

import plasmyt.plasm.ast.ASTNode;

public class PlasmListNode extends ASTNode {
    private final PlasmFileNode[] plasmFiles;

    public PlasmListNode(PlasmFileNode[] plasmFiles) {
        this.plasmFiles = plasmFiles;
    }

    public PlasmFileNode[] getPlasmFiles() {
        return plasmFiles;
    }
}