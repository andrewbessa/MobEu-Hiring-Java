package com.mobiquityinc.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;

public class PacketItem {

    private final int itemId;
    private final float weight;
    private final float cost;

    public PacketItem(final int itemId, final float weight, final float cost) throws APIException {

        validate(itemId, weight, cost);

        this.itemId = itemId;
        this.weight = weight;
        this.cost = cost;
    }

    private void validate(int itemId, float weight, float value) throws APIException {

        if(itemId < 0) {
            throw new APIException(ExceptionMessage.ITEM_ID.getMessage());
        }

        if(weight > 100 || weight < 0) {
            throw new APIException(ExceptionMessage.ITEM_WEIGHT.getMessage());
        }

        if(value > 100 || value < 0) {
            throw new APIException(ExceptionMessage.ITEM_COST.getMessage());
        }

    }

    public int getItemId() {
        return itemId;
    }

    public float getWeight() {
        return weight;
    }

    public float getCost() {
        return cost;
    }

}
