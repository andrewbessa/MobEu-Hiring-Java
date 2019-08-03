package com.mobiquityinc.packer.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.PacketItem;
import org.junit.Assert;
import org.junit.Test;

public class PacketItemTest {


    @Test
    public void caseIdLessThan0()  {
        try {
            new PacketItem(-1, (float) 48.77,79);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.ITEM_ID.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseWeightLessThan0()  {
        try {
            new PacketItem(1, (float) -48.77,79);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.ITEM_WEIGHT.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseWeightGreatThan100()  {
        try {
            new PacketItem(1, (float) 488.77,79);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.ITEM_WEIGHT.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseValueLessThan0()  {
        try {
            new PacketItem(1, (float) 48.77,-79);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.ITEM_COST.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseValueGreatThan100()  {
        try {
            new PacketItem(1, (float) 48.77,-79);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.ITEM_COST.getMessage(), e.getMessage());
        }
    }
}
