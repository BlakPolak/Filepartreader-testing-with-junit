import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader(){
        this.filePath = "pathToFile";
        this.fromLine = 0;
        this.toLine = 1;
    }
    public void setup(String filePath, Integer fromLine, Integer toLine){
        this.filePath = filePath;
        this.fromLine = fromLine;
        this.toLine = toLine;

        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException();
        }
    }

    private String read() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.filePath));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
        } catch (IOException ex){
            throw new FileNotFoundException();
        }
        return sb.toString();
    }

    public String readLines() throws FileNotFoundException {
        try{
            String fileString = read();
            List<String> lines = Arrays.asList(fileString.split("\n"));
            StringBuilder stringLines = new StringBuilder();
            if (this.fromLine != null && this.toLine != null) {
                Integer fromLine = this.fromLine--;
                Integer toLine = this.toLine--;
                if (Objects.equals(this.fromLine, this.toLine)) {
                    return lines.get(fromLine);
                }
                for (Integer iterator = this.fromLine; iterator < toLine; iterator++) {
                    String line = lines.get(iterator) + "\n";
                    stringLines.append(line);
                }
                return stringLines.toString().trim();
            }
        } catch(IOException ex){
            throw new FileNotFoundException();
        }
        return null;
    }
}
