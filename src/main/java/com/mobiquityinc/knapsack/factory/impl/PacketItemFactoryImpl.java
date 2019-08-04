package com.mobiquityinc.knapsack.factory.impl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.knapsack.factory.PacketItemFactory;

import java.math.BigDecimal;

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

            int itemId = Integer.parseInt(elements[0].trim());
            BigDecimal itemWeight = new BigDecimal(elements[1].trim());
            BigDecimal itemCost = new BigDecimal(elements[2].trim());

            return new PacketItem(itemId, itemWeight, itemCost);

        } catch (NumberFormatException e) {
            throw new APIException(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e);
        }
    }
}
