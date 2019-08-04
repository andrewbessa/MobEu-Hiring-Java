package com.mobiquityinc.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.exception.ExceptionMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack {

    private static final int SELECTED_ITEM = 1;

    private final int maxPacketWeight; 
    private final List<PacketItem> listPacketItems;
    private BigDecimal[][] tblAcumuCost;
    private BigDecimal[][] tblAcumuWeight;
    private int [][] tblSelectItems;
    private List<Integer> betterCombination; //Best Match List Found

    public Knapsack(final int maxPacketWeight, final List<PacketItem> listPacketItems) throws APIException {

        validateFields(maxPacketWeight, listPacketItems);

        this.maxPacketWeight = maxPacketWeight;
        this.listPacketItems = listPacketItems;

        this.tblSelectItems = new int[listPacketItems.size()+1][maxPacketWeight+1];
        this.tblAcumuCost = new BigDecimal[listPacketItems.size()+1][maxPacketWeight+1];
        this.tblAcumuWeight = new BigDecimal[listPacketItems.size()+1][maxPacketWeight+1];
    }

    /**
     * Return list with the best match found that was previously registered
     */
    public List<Integer> obtainBetterCombination(){
        if(betterCombination == null){
            processBetterCombination();
            betterCombination = extractBetterCombination();
        }
        return betterCombination;
    }

    /**
     * Calculates viable combinations for packages with different weight and value.
     */
    private void processBetterCombination(){
        for(int item = 1; item <= listPacketItems.size(); item++ ) {// Item from id 1 to size of item list
            for(int capacity = 1; capacity <= maxPacketWeight; capacity++){ // Capacity from 1 to maxPacketWeight
                PacketItem element = listPacketItems.get(item - 1); //Current element
                updateTableAcumuValues(element, capacity);// Updates the accumulating value of each iteration.
                updateTblAcumuWeightTblSelectItems(element, capacity); // Updates the accumulating weight and selected item  of each iteration.
            }
        }
    }

    /**
     * Calculates and returns list with the best match found
     */
    private List<Integer> extractBetterCombination(){
        int remainingCapacity = maxPacketWeight;
        List<Integer> result = new ArrayList<>();

        for(int index = listPacketItems.size() - 1; index>=0; index--){
            PacketItem element = listPacketItems.get(index);
            if(tblSelectItems[element.getItemId()][remainingCapacity] == SELECTED_ITEM) {//Last cumulative weight value
                result.add(element.getItemId());
                remainingCapacity = calcCapacityRemaining(remainingCapacity, element);
            }
        }
        Collections.sort(result);
        return result;
    }


    /**
     * During processing, the value table must be updated
     *
     * @param packetItem
     * @param capacity
     */
    private void updateTableAcumuValues(final PacketItem packetItem, final int capacity) {
        //Max value without the value of the current element.
        BigDecimal maxValWithoutCurr = tblAcumuCost[packetItem.getItemId()-1][capacity];
        //Max value without the value of the current element.
        BigDecimal maxValWithCurr = calcMaxValWithCurr(packetItem, capacity);
        tblAcumuCost[packetItem.getItemId()][capacity] = maxValWithoutCurr.max(maxValWithCurr);
    }

    /**
     * During processing, the selected weight and item tables selected must be updated at the same time.
     *
     * @param packetItem
     * @param capacity
     */
    private void updateTblAcumuWeightTblSelectItems(final PacketItem packetItem, final int capacity) {
        BigDecimal maxValWithoutCurr = tblAcumuCost[packetItem.getItemId()-1][capacity];
        BigDecimal maxValWithCurr = calcMaxValWithCurr(packetItem, capacity);

        if(maxValWithCurr.compareTo(maxValWithoutCurr) == 1 ){
            tblSelectItems[packetItem.getItemId()][capacity] = 1;
            tblAcumuWeight[packetItem.getItemId()][capacity] =
                tblAcumuWeight[packetItem.getItemId()][capacity].add(
                    tblAcumuWeight[packetItem.getItemId()-1][capacity].add(
                        packetItem.getWeight()));

        } else if(maxValWithCurr == maxValWithoutCurr) {
            int itemId = packetItem.getItemId();
            while( itemId > 0 && tblSelectItems[itemId][capacity] != 1){
                itemId--;
            }

            if( itemId > 0 && packetItem.getWeight().compareTo(listPacketItems.get(itemId-1).getWeight()) == -1){
                tblSelectItems[packetItem.getItemId()][capacity] = 1;
                tblAcumuWeight[packetItem.getItemId()][capacity] =
                    tblAcumuWeight[packetItem.getItemId()][capacity].add(
                        tblAcumuWeight[packetItem.getItemId()-1][capacity].add(
                            packetItem.getWeight()));
            }
        } else {
            tblAcumuWeight[packetItem.getItemId()][capacity] = tblAcumuWeight[packetItem.getItemId()-1][capacity];
        }
    }

    /**
     * Checks if the package can receive the parsed item, if yes its value is added to the package
     *
     * @param packetItem
     * @param capacity
     * @return
     */
    private BigDecimal calcMaxValWithCurr(final PacketItem packetItem, final int capacity){
        BigDecimal result = new BigDecimal(0);
        int remainingCapacity = 0;
        if(packetItem.getWeight().compareTo(BigDecimal.valueOf(capacity)) == 1 ||
            packetItem.getWeight().compareTo(BigDecimal.valueOf(capacity)) == 0){
            result = packetItem.getCost();
            remainingCapacity = calcCapacityRemaining(capacity, packetItem);
            result = result.add(tblAcumuCost[packetItem.getItemId()-1][remainingCapacity]);
        }
        return result;
    }

    /**
     *
     * @param capacity
     * @param packetItem
     * @return the capacity must the integer immediately before or after the remaining capacity
     */
    private int calcCapacityRemaining(final int capacity, final PacketItem packetItem) {
        BigDecimal weightOfCurrent = packetItem.getWeight();
        BigDecimal capacityRemaining = BigDecimal.valueOf(capacity).subtract(weightOfCurrent);
        int result = capacityRemaining.intValue();
        if( capacityRemaining.compareTo(BigDecimal.valueOf(result)) != 0 ){ //Check if the value is integer or float
            //Interger values immediately before and after the remaining capacity value
            int before = capacityRemaining.intValue();
            int after = capacityRemaining.add(BigDecimal.valueOf(1)).intValue();

            //If capacity is greater than acumulate weight
            if (capacityRemaining.compareTo(tblAcumuWeight[packetItem.getItemId()-1][after]) == 1){
                result = after;
            }else{
                result = before;
            }
        }
        return result;
    }

    private void validateFields(final int maxWeight, final List<PacketItem> listPacketItems) throws APIException {

        if(listPacketItems == null){
            throw new APIException(ExceptionMessage.LIST_OF_PACKETS_NULL.getMessage());
        }

        if(listPacketItems.size() > 15){
            throw new APIException(ExceptionMessage.LIST_OF_PACKETS_GREATER_THAN_LIMIT.getMessage());
        }

        if(maxWeight > 100 || maxWeight < 0){
            throw new APIException(ExceptionMessage.PACKET_WEIGHT.getMessage());
        }
    }
}
