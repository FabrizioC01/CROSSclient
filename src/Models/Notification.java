package Models;

import enums.MarketType;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Notification {
    private String notification;
    private ArrayList<Trade> trades;

    public void printNotifications(){
        System.out.println("[Notification] type : "+notification);
        for(Trade t : trades){
            System.out.println("["+t.getOrderId()+"] "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+((t.getType()==MarketType.ask)?"€":"B")+" x "+t.getSize());
        }
    }
}

class Trade{
    private int orderId;
    private MarketType type;
    private String orderType;
    private int size;
    private int price;
    private Timestamp timestamp;

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
    public Timestamp getTimestamp() {
        return timestamp;
    }

}
