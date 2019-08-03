package com.mobiquityinc.suite;

import com.mobiquityinc.packer.PackerTest;
import com.mobiquityinc.packer.knapsack.KnapsackTest;
import com.mobiquityinc.packer.knapsack.PacketItemTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({PackerTest.class, KnapsackTest.class, PacketItemTest.class})
public class PackerTestSuite {
}
