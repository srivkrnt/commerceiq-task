package com.commerceiq.scraper.enums;

import java.util.HashMap;
import java.util.Map;

public enum RetailerEnum {
    AMAZON((byte) 1, "Amazon");

    private static final Map<String, RetailerEnum> reverseTypeNameMap = new HashMap<>();

    static {
        for (RetailerEnum entityType : values()) {
            reverseTypeNameMap.put(entityType.getName(), entityType);
        }
    }

    byte id;
    String name;

    RetailerEnum(byte id, String typeName) {
        this.id = id;
        this.name = typeName;
    }
    public byte getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public static RetailerEnum typeByName(String name) {
        return reverseTypeNameMap.get(name);
    }
    public static RetailerEnum getById(Byte retailerId) {
        for (RetailerEnum retailer : RetailerEnum.values()) {
            if (retailer.getId() == retailerId) {
                return retailer;
            }
        }
        return null;
    }
}
