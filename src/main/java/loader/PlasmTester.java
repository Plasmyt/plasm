package loader;

public class PlasmTester {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\usugo\\IdeaProjects\\plasm\\src\\main\\resources\\path";

        PlasmLister plasmLister = new PlasmLister(folderPath);
        plasmLister.listFiles();
    }
}
