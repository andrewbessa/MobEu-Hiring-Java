package com.mobiquityinc.knapsack.factory.impl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.knapsack.factory.PacketItemFactory;

public class PacketItemFactoryImpl implements PacketItemFactory{

    private static PacketItemFactoryImpl instance;

    private PacketItemFactoryImpl() {}

    public static PacketItemFactoryImpl getInstance(){
        if(instance == null){
            instance = new PacketItemFactoryImpl();
        }
        return instance;
    }

    public PacketItem createPacketItem(final String item) throws APIException {

        try {

            String[] elements = item.replace("(", "")
                .replace("€", "").split(",");

            int itemId = Integer.valueOf(elements[0].trim());
            float itemWeight = Float.valueOf(elements[1].trim());
            float itemCost = Float.valueOf(elements[2].trim());

            return new PacketItem(itemId, itemWeight, itemCost);

        } catch (NumberFormatException e) {
            throw new APIException(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e);
        }
    }
}
