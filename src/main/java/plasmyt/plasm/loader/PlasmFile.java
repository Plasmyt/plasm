package plasmyt.plasm.loader;

class PlasmFile {
    private final String name;
    private final String path;

    public PlasmFile(String name, String path) {
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
