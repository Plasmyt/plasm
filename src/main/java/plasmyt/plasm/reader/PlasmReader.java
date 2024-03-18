package plasmyt.plasm.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class PlasmReader extends Reader {
    private final BufferedReader reader;

    public PlasmReader(String filePath) throws IOException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException("This method is not supported in PlasmReader.");
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}