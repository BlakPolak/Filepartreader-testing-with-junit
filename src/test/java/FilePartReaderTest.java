import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class FilePartReaderTest {
    FilePartReader filePartReader = new FilePartReader();
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("test_data.txt").getFile());
    String filePath = file.getAbsolutePath();

    @Test
    public void testReadLinesBeforeSetup(){
        assertThrows(FileNotFoundException.class, () -> filePartReader.readLines());

    }

    @Test
    public void testSetupFromLineLT1(){
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("", -1,0));
    }

    @Test
    public void testSetupToLineLTFromLine(){
        assertThrows(IllegalArgumentException.class, () -> filePartReader.setup("", 2,1));
    }

    @Test
    public void testReadLines1_2() throws FileNotFoundException {
        filePartReader.setup(filePath,1,2);
        String lines = filePartReader.readLines();
        String expectedLines = "1a1\n2b 2a";
        assertEquals(lines, expectedLines);
    }

    @Test
    public void testReadLines2_4() throws FileNotFoundException {
        filePartReader.setup(filePath,2,4);
        String lines = filePartReader.readLines();
        String expectedLines = "2b 2a\n" + "3c 3b 3a\n" + "4d 4cr 4bb4 4a";
        assertEquals(lines, expectedLines);
    }

    @Test
    public void testReadLinesAll() throws FileNotFoundException {
        filePartReader.setup(filePath,1,7);
        String lines = filePartReader.readLines();
        String expectedLines = "1a1\n" + "2b 2a\n" + "3c 3b 3a\n" + "4d 4cr 4bb4 4a\n" +
                "5e 5d 5c 5b 5ax\n" + "6f 6ea 6d 6ca 6bb 6a\n" + "7g 7f 7ea";
        assertEquals(lines, expectedLines);
    }

    @Test
    public void testReadLinesPastEof() throws FileNotFoundException {
        filePartReader.setup(filePath,7,7);
        String lines = filePartReader.readLines();
        String expectedLines = "7g 7f 7ea";
        assertEquals(lines, expectedLines);
    }

}