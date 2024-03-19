package plasmyt.plasm.loader;

import plasmyt.plasm.ast.nodes.PlasmFileNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlasmFileLoader {
    private final String folderPath;

    public PlasmFileLoader(String folderPath) {
        this.folderPath = folderPath;
    }

    public List<PlasmFileNode> loadFiles() {
        List<PlasmFileNode> plasmFileNodes = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".psm"));

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    PlasmFileNode plasmFileNode = loadPlasmFileNode(file);
                    if (plasmFileNode != null) {
                        plasmFileNodes.add(plasmFileNode);
                    }
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not accessible.");
        }

        return plasmFileNodes;
    }

    private PlasmFileNode loadPlasmFileNode(File file) {
        try {
            String name = file.getName();
            String path = file.getAbsolutePath();
            String content = Files.readString(Path.of(path));
            return new PlasmFileNode(name, path, content);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + file.getName());
            e.printStackTrace();
            return null;
        }
    }
}