package com.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.packer.util.FileTestUtil;
import com.mobiquityinc.packer.Packer;
import org.junit.Assert;
import org.junit.Test;

public class PackerTest {

    @Test
    public void loadFileTest() throws APIException {

        String pathFileTest = FileTestUtil.pathGenerator(new String[]{"test_files", "testFile01.txt"});
        String pathResultFileTest = FileTestUtil.pathGenerator(new String[]{"test_files", "resultFile01.txt"});
        String expectedResult = FileTestUtil.readFile(pathResultFileTest);
        String result = Packer.pack(pathFileTest);

        Assert.assertEquals(expectedResult,result);
    }

    @Test
    public void pathFileNullTest() {
        try {
            Packer.pack(null);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.PATH_FILE_NULL.getMessage(), e.getMessage());
        }
    }

    @Test
    public void pathNoExistTest()  {

        String pathFileTest = FileTestUtil.pathGenerator(new String[]{"test_files", "testFileFail.txt"});

        try {
            Packer.pack(pathFileTest);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.NO_SUCH_FILE.getMessage(), e.getMessage());
        }

    }

    @Test
    public void badFormatMaxWeightTest() {

        String pathFileTest = FileTestUtil.pathGenerator(new String[]{"test_files", "testFile01BadFormat.txt"});

        try {
            Packer.pack(pathFileTest);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e.getMessage());
        }
    }

    @Test
    public void badFormatPacketItemTest() {

        String pathFileTest = FileTestUtil.pathGenerator(new String[]{"test_files", "testFile01BadFormat.txt"});

        try {
            Packer.pack(pathFileTest);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e.getMessage());
        }
    }

}
