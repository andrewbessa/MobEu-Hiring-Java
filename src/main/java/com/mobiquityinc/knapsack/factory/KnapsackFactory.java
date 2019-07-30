package com.mobiquityinc.knapsack.factory;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.knapsack.Knapsack;

public interface KnapsackFactory {

    Knapsack createKnapsack(final String line) throws APIException;
}
