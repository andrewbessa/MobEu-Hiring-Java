package com.packer.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.Knapsack;
import com.mobiquityinc.knapsack.PacketItem;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KnapsackTest {

    @Test
    public void caseSuccessTest() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, (float) 8.77,79));
        items.add(new PacketItem(2, (float) 81.80,45));
        items.add(new PacketItem(3, (float) 0.96,79));
        items.add(new PacketItem(4, (float) 1.76,64));

        Knapsack knapsack = new Knapsack(10, items);

        Assert.assertEquals("[1, 3]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseEmptyAnswerTest() throws APIException {
        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, (float) 8.77,79));
        Knapsack knapsack = new Knapsack(5, items);
        Assert.assertEquals("[]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseTwoItemsSameValue() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, (float) 48.77,79));
        items.add(new PacketItem(2, (float) 81.80,45));
        items.add(new PacketItem(3, (float) 19.36,79));
        items.add(new PacketItem(4, (float) 6.76,64));
        Knapsack knapsack = new Knapsack(56, items);
        Assert.assertEquals("[3, 4]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void listPacketItemsNull(){
        try {
            new Knapsack(10, null);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.LIST_OF_PACKETS_NULL.getMessage(), e.getMessage());
        }
    }

    @Test
    public void casePacketWeightGreaterThan100(){
        List<PacketItem> items = new ArrayList<>();
        try {
            new Knapsack(1000, items);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.PACKET_WEIGHT.getMessage(), e.getMessage());
        }
    }

}