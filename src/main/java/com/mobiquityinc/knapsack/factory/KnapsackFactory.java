package com.mobiquityinc.knapsack.factory;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.knapsack.Knapsack;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.exception.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

public  class KnapsackFactory  {

    private static KnapsackFactory instance;

    private KnapsackFactory() {}

    public static KnapsackFactory getInstance(){
        if(instance == null) {
            instance = new KnapsackFactory();
        }
        return instance;
    }

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
