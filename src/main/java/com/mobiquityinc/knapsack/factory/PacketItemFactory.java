package com.mobiquityinc.knapsack.factory;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.exception.ExceptionMessage;

public class PacketItemFactory {

    private static PacketItemFactory instance;

    private PacketItemFactory() {}

    public static PacketItemFactory getInstance(){
        if(instance == null){
            instance = new PacketItemFactory();
        }
        return instance;
    }

    public PacketItem createPacketItem(final String item) throws APIException {

        try {

            String[] elements = item.replace("(", "")
                .replace("€", "").split(",");

            int itemId = Integer.valueOf(elements[0].trim());
            float itemWeight = Float.valueOf(elements[1].trim());
            float itemValue = Float.valueOf(elements[2].trim());

            return new PacketItem(itemId, itemWeight, itemValue);

        } catch (NumberFormatException e) {
            throw new APIException(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e);
        }
    }
}
