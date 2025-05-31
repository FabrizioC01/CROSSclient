package Models;

import enums.MarketType;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Notification {
    private String notification;
    private ArrayList<Trade> trades;

    public void printNotifications(){
        System.out.println("[Notification] type : "+notification);
        for(Trade t : trades){
            Date d = new Date(t.getTimestamp());
            if(t.getOrderId()==-1) System.out.println("[FAILED] ("+d+") "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+"$ x "+(float)t.getSize()/1000+"BTC");
            else System.out.println("["+t.getOrderId()+"] ("+d+") "+t.getOrderType()+" | "+t.getType().toString().toUpperCase()+" - "+(float) t.getPrice() /1000+"$ x "+(float)t.getSize()/1000+"BTC");
        }
    }
}

class Trade{
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
