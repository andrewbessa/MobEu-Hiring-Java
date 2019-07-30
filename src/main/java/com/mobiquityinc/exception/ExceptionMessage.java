package com.mobiquityinc.exception;

public enum  ExceptionMessage {

    LIST_OF_PACKETS_NULL("List of packets is null"),
    PATH_FILE_NULL("Path of file is null"),
    NO_SUCH_FILE("No such file"),
    BAD_NUMBER_FORMAT("Bad number format"),
    ITEM_ID("Item id must be greater than zero "),
    ITEM_WEIGHT("Item weight must to be between 0 and 100"),
    ITEM_COST("Item value must to be between 0 and 100"),
    PACKET_WEIGHT("Item weight must to be between 0 and 100");

    private String message;

    ExceptionMessage(final String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
