package plasmyt.plasm.loader;

import plasmyt.plasm.ast.nodes.PlasmFileNode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlasmFileLoader {
    private final String folderPath;

    public PlasmFileLoader(String folderPath) {
        this.folderPath = folderPath;
    }

    public List<PlasmFileNode> loadFiles() {
        List<PlasmFileNode> plasmFiles = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".psm"));

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    PlasmFileNode plasmFile = loadPlasmFile(file);
                    if (plasmFile != null) {
                        plasmFiles.add(plasmFile);
                    }
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not accessible.");
        }

        return plasmFiles;
    }

    private PlasmFileNode loadPlasmFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String name = file.getName();
            String path = file.getAbsolutePath();
            return new PlasmFileNode(name, path);
        } catch (IOException e) {
            System.err.println("Error while reading file: " + file.getName());
            e.printStackTrace();
            return null;
        }
    }
}