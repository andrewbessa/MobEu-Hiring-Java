package com.mobiquityinc.knapsack.factory;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.knapsack.PacketItem;

public interface PacketItemFactory {


    PacketItem createPacketItem(final String item) throws APIException;

}
