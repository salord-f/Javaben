package javaben.io;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    @Test(expected = IOException.class)
    public void nonExistingFileTest() throws IOException {
        String file = FileReader.getFileAsString("src/main/resources/mapping/op.in");
    }

    @Test
    public void simpleFileTest() throws IOException {
        String file = FileReader.getFileAsString("src/main/resources/mapping/GG16.in");
        assertEquals("16 24", file.split("\n")[0]);
        assertEquals("13 15", file.split("\n")[24]);
    }
}
