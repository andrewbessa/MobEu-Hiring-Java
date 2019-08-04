package com.mobiquityinc.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;

import java.math.BigDecimal;

public class PacketItem {

    private final int itemId;
    private final BigDecimal weight;
    private final BigDecimal cost;

    public PacketItem(final int itemId, final BigDecimal weight, final BigDecimal cost) throws APIException {

        validateField(itemId, weight, cost);

        this.itemId = itemId;
        this.weight = weight;
        this.cost = cost;
    }

    private void validateField(int itemId, BigDecimal weight, BigDecimal value) throws APIException {

        if(itemId < 0) {
            throw new APIException(ExceptionMessage.ITEM_ID.getMessage());
        }

        if(weight.compareTo(BigDecimal.valueOf(100)) == 1 || weight.compareTo(BigDecimal.valueOf(0)) == -1) {
            throw new APIException(ExceptionMessage.ITEM_WEIGHT.getMessage());
        }

        if(value.compareTo(BigDecimal.valueOf(100)) == 1 || value.compareTo(BigDecimal.valueOf(0)) == -1) {
            throw new APIException(ExceptionMessage.ITEM_COST.getMessage());
        }

    }

    public int getItemId() {
        return itemId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getCost() {
        return cost;
    }

}
