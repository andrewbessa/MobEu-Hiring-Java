package com.mobiquityinc.exception;

public enum  ExceptionMessage {

    LIST_OF_PACKETS_NULL("List of packets is null"),
    LIST_OF_PACKETS_GREATER_THAN_LIMIT("List of packets is greater than limit"),
    PATH_FILE_NULL("Path of file is null"),
    NO_SUCH_FILE("No such file"),
    BAD_NUMBER_FORMAT("Bad number format"),
    ITEM_ID("Item id must be greater than zero "),
    ITEM_WEIGHT("Item weight must to be between 0 and 100"),
    ITEM_COST("Item value must to be between 0 and 100"),
    PACKET_WEIGHT("Item weight must to be between 0 and 100"),
    INPUT_LINE_BAD_FORMATED("Input line bad formated");

    private String message;

    ExceptionMessage(final String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
