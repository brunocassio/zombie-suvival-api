package com.zombiesurvival.beans;

import java.util.Map;

/**
 * Created by bru9isk on 11/01/17.
 */
public class TradeBean {

    private String name;
    private Map<String, String> itemsWanted;
    private Map<String, String> payInReturn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getItemsWanted() {
        return itemsWanted;
    }

    public void setItemsWanted(Map<String, String> itemsWanted) {
        this.itemsWanted = itemsWanted;
    }

    public Map<String, String> getPayInReturn() {
        return payInReturn;
    }

    public void setPayInReturn(Map<String, String> payInReturn) {
        this.payInReturn = payInReturn;
    }
}
