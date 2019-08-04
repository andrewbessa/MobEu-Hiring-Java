package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

import java.util.logging.Logger;

public class App {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());


    public static void main(String... args) throws APIException {

        for(String path :args){

            String message = Packer.pack(path);
            LOGGER.info(message);
        }
    }
}
