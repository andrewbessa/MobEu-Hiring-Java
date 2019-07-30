package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.Knapsack;
import com.mobiquityinc.knapsack.factory.impl.KnapsackFactoryImpl;
import com.mobiquityinc.knapsack.factory.KnapsackFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Packer {

    private Packer() {
    }

    public static String pack(final String filePath) throws APIException {

        validateField(filePath);
        KnapsackFactory knapsackFactory = KnapsackFactoryImpl.getInstance();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            List<String> packets = new ArrayList<>();
            for (String line : stream.toArray(String[]::new)) {
                Knapsack knapsack = knapsackFactory.createKnapsack(line);
                packets.add(knapsack.obtainBetterCombination().toString());
            }

            return packets.stream().map(value->"[]".equals(value) ? "-" : value).collect(Collectors.joining("\n"));

        } catch (NoSuchFileException e) {
            throw new APIException(ExceptionMessage.NO_SUCH_FILE.getMessage(), e);
        } catch (IOException e) {
            throw new APIException(e.getMessage());
        }

    }


    private static  void validateField(final String filePath) throws APIException {
        if(filePath == null){
            throw new APIException(ExceptionMessage.PATH_FILE_NULL.getMessage());
        }
    }

}
