import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class FilePartReaderTest {
    @Test
    public void testReadLinesBeforeSetup(){
        FilePartReader filePartReader = new FilePartReader();
        assertThrows(FileNotFoundException.class, filePartReader::readLines);

    }

    @Test
    public void testSetupFromLineLT1(){
        fail("Exception not thrown");
    }

    @Test
    public void testSetupToLineLTFromLine(){
        fail("Exception not thrown");
    }

    @Test
    public void testReadLines1_2(){
        fail("Exception not thrown");
    }

    @Test
    public void testReadLines2_4(){
        fail("Exception not thrown");
    }

    @Test
    public void testReadLinesAll(){
        fail("Exception not thrown");
    }

    @Test
    public void testReadLinesPastEof(){
        fail("Exception not thrown");
    }

}