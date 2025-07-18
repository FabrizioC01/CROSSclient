package Models;

import enums.MarketType;

/**
 * Classe che gestisce gli ordini ricevuti da notifiche e lo storico degli ordini
 */
public class Trade{
    private int orderId;
    private MarketType type;
    private String orderType;
    private int size;
    private int price;
    private long timestamp;

    public int getOrderId() {
        return orderId;
    }

    public int getPrice() {
        return price;
    }
    public MarketType getType() {
        return type;
    }
    public String getOrderType() {
        return orderType;
    }
    public int getSize() {
        return size;
    }
    public long getTimestamp() {
        return timestamp;
    }

}
