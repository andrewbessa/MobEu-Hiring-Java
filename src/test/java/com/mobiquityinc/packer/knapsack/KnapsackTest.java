package com.mobiquityinc.packer.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.Knapsack;
import com.mobiquityinc.knapsack.PacketItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class KnapsackTest {

    @Test
    public void caseSuccessTest() throws APIException {


        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float)8.77) ,BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float)81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float)0.96),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float)1.76),BigDecimal.valueOf((float)64)));

        Knapsack knapsack = new Knapsack(10, items);

        Assert.assertEquals("[1, 3]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseWeightEqualPacketSizeTest() throws APIException {


        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float)10.00) ,BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float)81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float)0.96),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float)1.76),BigDecimal.valueOf((float)64)));

        Knapsack knapsack = new Knapsack(10, items);

        Assert.assertEquals("[3, 4]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseWeightIntValuesTest() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float)5.00) ,BigDecimal.valueOf((float)40)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float)4.00),BigDecimal.valueOf((float)10)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float)6.00),BigDecimal.valueOf((float)30)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float)3.00),BigDecimal.valueOf((float)50)));

        Knapsack knapsack = new Knapsack(10, items);

        Assert.assertEquals("[1, 4]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseListItemsTest() throws APIException {
        List<PacketItem> items = new ArrayList<>();
        Knapsack knapsack = new Knapsack(5, items);
        Assert.assertEquals("[]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseEmptyAnswerTest() throws APIException {
        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float)8.77),BigDecimal.valueOf((float)79)));
        Knapsack knapsack = new Knapsack(5, items);
        Assert.assertEquals("[]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseTwoItemsSameValue() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));
        Knapsack knapsack = new Knapsack(56, items);
        Assert.assertEquals("[3, 4]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void caseItemsSameWeight() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float) 16.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float) 16.36),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float)16.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float)16.36),BigDecimal.valueOf((float)64)));
        Knapsack knapsack = new Knapsack(56, items);
        Assert.assertEquals("[1, 3, 4]", knapsack.obtainBetterCombination().toString().trim());
    }

    @Test
    public void listPacketItemsNull() {

        try {
            new Knapsack(10, null);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.LIST_OF_PACKETS_NULL.getMessage(), e.getMessage());
        }
    }

    @Test
    public void listPacketItemsGreaterThanLimit() throws APIException {

        List<PacketItem> items = new ArrayList<>();
        items.add(new PacketItem(1, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(2, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(3, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(4, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));
        items.add(new PacketItem(5, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(6, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(7, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(8, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));
        items.add(new PacketItem(9, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(10, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(11, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(12, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));
        items.add(new PacketItem(13, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(14, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(15, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(16, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));
        items.add(new PacketItem(17, BigDecimal.valueOf((float) 48.77),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(18, BigDecimal.valueOf((float) 81.80),BigDecimal.valueOf((float)45)));
        items.add(new PacketItem(19, BigDecimal.valueOf((float) 19.36),BigDecimal.valueOf((float)79)));
        items.add(new PacketItem(20, BigDecimal.valueOf((float) 6.76),BigDecimal.valueOf((float)64)));

        try {
            new Knapsack(10, items);
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.LIST_OF_PACKETS_GREATER_THAN_LIMIT.getMessage(), e.getMessage());
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
