package loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PlasmLister {

    private final String folderPath;
    private PlasmFile[] plasmFiles;

    public PlasmLister(String folderPath) {
        this.folderPath = folderPath;
        listFiles();
    }

    public void listFiles() {
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".psm"));

        if (files != null) {
            System.out.println("Files in " + folderPath + ":");
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    printFileContents(file);
                }
            }
        } else {
            System.out.println("The specified folder does not exist or is not accessible.");
        }
    }

    private void printFileContents(File file) {
        System.out.println("Content of file " + file.getName() + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlasmFile[] getPlasmFiles() {
        return plasmFiles;
    }

}
