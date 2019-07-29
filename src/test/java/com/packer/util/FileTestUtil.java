package com.packer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileTestUtil {

    private FileTestUtil(){}

    public static String getCurretDiretoryPath(){
        return System.getProperty("user.dir");
    }

    public static String getSeparator(){
        return System.getProperty("file.separator");
    }

    public static String pathGenerator(final String... pathElements){
        return getCurretDiretoryPath() + getSeparator() +
            Arrays.stream(pathElements).collect(Collectors.joining(getSeparator()));
    }

    public static String readFile(final String filePath)  {
        String result = null;
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            result = stream.collect(Collectors.joining("\n"));

        } catch (IOException e) {
            result = "";
        }
        return result;
    }

}
