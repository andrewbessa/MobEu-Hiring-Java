package com.mobiquityinc.packer.factory;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;
import com.mobiquityinc.knapsack.PacketItem;
import com.mobiquityinc.knapsack.factory.PacketItemFactory;
import com.mobiquityinc.knapsack.factory.impl.PacketItemFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class PacketItemFactoryImplTest {

    @Test
    public void caseWeightBadFormat() {
        PacketItemFactory packetItemFactory = PacketItemFactoryImpl.getInstance();

        try {
            PacketItem item = packetItemFactory.createPacketItem("1, 82.3C, €45");
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseCostBadFormat() {
        PacketItemFactory packetItemFactory = PacketItemFactoryImpl.getInstance();

        try {
            PacketItem item = packetItemFactory.createPacketItem("1, 82.3, €");
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e.getMessage());
        }
    }

    @Test
    public void caseIdBadFormat() {
        PacketItemFactory packetItemFactory = PacketItemFactoryImpl.getInstance();

        try {
            PacketItem item = packetItemFactory.createPacketItem("1C, 82.3, €45");
        } catch (APIException e) {
            Assert.assertEquals(ExceptionMessage.BAD_NUMBER_FORMAT.getMessage(), e.getMessage());
        }
    }
}
