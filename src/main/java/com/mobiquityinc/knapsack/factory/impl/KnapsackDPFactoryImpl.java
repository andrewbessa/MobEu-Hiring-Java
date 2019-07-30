package com.mobiquityinc.knapsack.factory.impl;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.knapsack.Knapsack;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.factory.KnapsackFactory;
import com.mobiquityinc.knapsack.factory.PacketItemFactory;

import java.util.ArrayList;
import java.util.List;

public  class KnapsackDPFactory implements KnapsackFactory {

    private static KnapsackDPFactory instance;

    private KnapsackDPFactory() {}

    public static KnapsackDPFactory getInstance(){
        if(instance == null) {
            instance = new KnapsackDPFactory();
        }
        return instance;
    }

    @Override
    public Knapsack createKnapsack(final String line) throws APIException {
        String[] components = line.split(":");
        int weight = extractMaxWeight(components[0]);
        List<PacketItem> packetItemsList = generatePacketItemList(components[1]);
        return new Knapsack(weight, packetItemsList);
    }

    private List<PacketItem> generatePacketItemList(final String items) throws APIException {
        List<PacketItem> result = new ArrayList<>();
        for (String item: items.split("\\)")) {
            result.add(PacketItemFactory.getInstance().createPacketItem(item));
        }
        return result;
    }

    private int extractMaxWeight(final String weight) throws APIException {
        try{
            return Integer.valueOf(weight.trim());
        } catch (NumberFormatException e){
            throw new APIException(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e);
        }
    }

}
